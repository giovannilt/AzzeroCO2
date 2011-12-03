package it.agilis.mens.azzeroCO2.core.register.impl;

import org.artofsolving.jodconverter.OfficeDocumentConverter;
import org.artofsolving.jodconverter.office.DefaultOfficeManagerConfiguration;
import org.artofsolving.jodconverter.office.OfficeManager;

import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 12/3/11
 * Time: 10:34 AM
 * To change this template use File | Settings | File Templates.
 */
public class OpenOfficeService {

    private static OfficeManager officeManager = new DefaultOfficeManagerConfiguration().buildOfficeManager();

    static {
        officeManager.start();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        officeManager.stop();
    }

    public String creaPDF(String fileToConvert) {

        String fileToConverted= fileToConvert.replace(".odt",".pdf");

        OfficeDocumentConverter converter = new OfficeDocumentConverter(officeManager);
        String template="/Users/giovannilt/Documents/MensAgilisProjects/AzzeroCO2/templateSource/CertificatoTemplate.";
        converter.convert(new File(template+"odt"), new File(template+"pdf"));


        return fileToConverted;
    }


    public String creaODTFromTemplate(){
        String odt= "";



        return odt;
    }
}
