package it.agilis.mens.azzeroCO2.core.register.impl;

import it.agilis.mens.azzeroCO2.core.entity.Ordine;
import it.agilis.mens.azzeroCO2.core.register.ConversionTask.ConversionTask;
import org.apache.commons.io.FilenameUtils;
import org.artofsolving.jodconverter.OfficeDocumentConverter;
import org.artofsolving.jodconverter.document.DocumentFormat;
import org.artofsolving.jodconverter.office.DefaultOfficeManagerConfiguration;
import org.artofsolving.jodconverter.office.OfficeManager;

import java.io.File;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 12/3/11
 * Time: 10:34 AM
 * To change this template use File | Settings | File Templates.
 */
public class OpenOfficeService {

    private static OfficeManager officeManager = new DefaultOfficeManagerConfiguration().buildOfficeManager();
    private String certificatoTemplateODT;
    private String certificatiFolder;

    public void setCertificatoTemplateODT(String certificatoTemplateODT) {
        this.certificatoTemplateODT = certificatoTemplateODT;
    }

    public String getCertificatoTemplateODT() {
        return certificatoTemplateODT;
    }

    public void setCertificatiFolder(String certificatiFolder) {
        this.certificatiFolder = certificatiFolder;
    }

    public String getCertificatiFolder() {
        return certificatiFolder;
    }

    static {
        officeManager.start();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        officeManager.stop();
    }

    public String creaPDF(Ordine ordine) {
        File dir=new File(certificatiFolder);
        if(!dir.exists()){
            dir.mkdirs();
        }

        String nomeFile = new Date().getTime() + ".pdf";
        String fileToConverted = certificatiFolder + nomeFile;

        OfficeDocumentConverter converter = new OfficeDocumentConverter(officeManager);

        File inputFile =new File(certificatoTemplateODT);

        ConversionTask task= new  ConversionTask(ordine);
        converter.convert(inputFile, new File(fileToConverted), task);

        return nomeFile;
    }


}
