package it.agilis.mens.azzeroCO2.core.register.impl;

import org.artofsolving.jodconverter.OfficeDocumentConverter;
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

    public String creaPDF(String fileToConvert) {
        File dir=new File(certificatiFolder);
        if(!dir.exists()){
            dir.mkdirs();
        }

        String fileToConverted =  certificatiFolder+ new Date().getTime() + ".pdf";

        OfficeDocumentConverter converter = new OfficeDocumentConverter(officeManager);
        converter.convert(new File(certificatoTemplateODT), new File(fileToConverted));

        return fileToConverted;
    }


}
