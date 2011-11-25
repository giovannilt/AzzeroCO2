package it.agilis.mens.azzeroCO2.server.services;

import it.agilis.mens.azzeroCO2.server.fIleUpload.FileExceedsLimitException;
import it.agilis.mens.azzeroCO2.server.fIleUpload.FileUploadItem;
import it.agilis.mens.azzeroCO2.server.fIleUpload.UploadState;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.codehaus.jackson.map.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.logging.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 11/25/11
 * Time: 7:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class UploadServlet extends HttpServlet {
    private static final String ENCODING = "utf-8";
    private static final long MAX_FILE_SIZE = 1024 * 1024 * 2; // DUE MEGA
    private static final Logger log = Logger.getLogger(UploadServlet.class.getName());
    private static final String PATH_TO_SAVE="";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding(ENCODING);
        response.setCharacterEncoding(ENCODING);
        response.setContentType("text/html");

        FileUploadItem model = new FileUploadItem();
        try {
            ServletFileUpload upload = new ServletFileUpload();
            FileItemIterator iter = upload.getItemIterator(request);
            while (iter.hasNext()) {
                FileItemStream item = iter.next();

                File file = new File(PATH_TO_SAVE+item.getName());
                log.info(file.getAbsolutePath());
                flow(item.openStream(), new FileOutputStream(file), new byte[1024]);

                processFileItem(request, model, item);
            }
        } catch (Exception e) {
            model.setState(UploadState.ERROR.name());
            model.setMessage(UploadState.ERROR.getLabel().concat(e.getMessage()));
            throw new ServletException(e);
        } finally {
            try {
                ObjectMapper mapper = new ObjectMapper();
                mapper.writeValue(response.getOutputStream(), model);
            } catch (Exception e) {
                throw new ServletException(e);
            }
        }
    }

    private void processFileItem(HttpServletRequest request, FileUploadItem model, FileItemStream item) throws FileExceedsLimitException {
        if (!item.isFormField()) {
            model.setName(item.getName());
            model.setState(UploadState.OK.name());
            model.setMessage(UploadState.OK.getLabel());
            checkFileSizeLimit(request);
            log.info("Uploaded file: " + model);
        }
    }

    private void checkFileSizeLimit(HttpServletRequest request) throws FileExceedsLimitException {
        if (request.getContentLength() > MAX_FILE_SIZE) {
            throw new FileExceedsLimitException("File (" + request.getContentLength() + " bytes)exceeds limit " + MAX_FILE_SIZE + " bytes.");
        }
    }

    private void flow(InputStream is, OutputStream os, byte[] buf) throws IOException {
        int numRead;
        while ((numRead = is.read(buf)) >= 0) {
            os.write(buf, 0, numRead);
        }
        os.flush();
        os.close();
        is.close();
    }
}
