package it.agilis.mens.azzeroCO2.core.register.ConversionTask;

import com.sun.star.beans.PropertyVetoException;
import com.sun.star.beans.UnknownPropertyException;
import com.sun.star.beans.XPropertySet;
import com.sun.star.container.NoSuchElementException;
import com.sun.star.container.XEnumeration;
import com.sun.star.lang.WrappedTargetException;
import com.sun.star.lang.XComponent;
import com.sun.star.text.XDependentTextField;
import com.sun.star.text.XTextFieldsSupplier;
import com.sun.star.uno.UnoRuntime;
import com.sun.star.util.XRefreshable;
import it.agilis.mens.azzeroCO2.core.entity.Ordine;
import org.artofsolving.jodconverter.AbstractConversionTask;
import org.artofsolving.jodconverter.OfficeDocumentUtils;
import org.artofsolving.jodconverter.document.DocumentFamily;
import org.artofsolving.jodconverter.document.DocumentFormat;
import org.artofsolving.jodconverter.office.OfficeException;

import java.io.File;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import static org.artofsolving.jodconverter.office.OfficeUtils.cast;

public class ConversionTask extends AbstractConversionTask {

    private DocumentFormat outputFormat;
    private DocumentFormat inputFormat;
    private Map<String, ?> defaultLoadProperties;

    private Ordine ordine;

    public ConversionTask(File inputFile, File outputFile, Ordine ordine) {
        super(inputFile, outputFile);
        this.ordine = ordine;
    }

    public void setDefaultLoadProperties(Map<String, ?> defaultLoadProperties) {
        this.defaultLoadProperties = defaultLoadProperties;
    }

    public void setInputFormat(DocumentFormat inputFormat) {

        this.inputFormat = inputFormat;
    }

    public void setOutputFormat(DocumentFormat outputFormat) {
        this.outputFormat = outputFormat;
    }

    @Override
    protected void modifyDocument(XComponent document) throws OfficeException, NoSuchElementException, WrappedTargetException, UnknownPropertyException, com.sun.star.lang.IllegalArgumentException, PropertyVetoException {
        try {
            XRefreshable refreshable = cast(XRefreshable.class, document);

            XTextFieldsSupplier fieldSupplier = (XTextFieldsSupplier)
                    UnoRuntime.queryInterface(XTextFieldsSupplier.class, document);
            XEnumeration enumeration = fieldSupplier.getTextFields().createEnumeration();
            while (enumeration.hasMoreElements()) {
                Object o = enumeration.nextElement();

                XDependentTextField dependentTextField = (XDependentTextField) UnoRuntime.queryInterface(XDependentTextField.class, o);
                XPropertySet propertySet = dependentTextField.getTextFieldMaster();
                String name = (String) propertySet.getPropertyValue("Name");

                XPropertySet loXPropertySet = UnoRuntime.queryInterface(XPropertySet.class, dependentTextField);

                if (name.equalsIgnoreCase("KgCO2")) {
                    DecimalFormat decimalFormat = new DecimalFormat("0.00");
                    String co2= decimalFormat.format( ordine.getRicevutaDiPagamento().getKgCO2()) + " kg";
                    if(ordine.getRicevutaDiPagamento().getKgCO2()>1000){
                         co2=  decimalFormat.format( ordine.getRicevutaDiPagamento().getKgCO2() /1000) +  "Ton";
                    }
                    loXPropertySet.setPropertyValue("Content", co2);
                } else if (name.equalsIgnoreCase("NomeAzienda")) {
                    loXPropertySet.setPropertyValue("Content", ordine.getUtente().getNome());
                } else if (name.equalsIgnoreCase("NomeEvento")) {
                    loXPropertySet.setPropertyValue("Content", ordine.getEvento().getNome());
                }
                loXPropertySet.setPropertyValue("Hint", "fieldHint");
            }

            if (refreshable != null) {
                refreshable.refresh();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Map<String, ?> getLoadProperties(File inputFile) {
        Map<String, Object> loadProperties = new HashMap<String, Object>();
        if (defaultLoadProperties != null) {
            loadProperties.putAll(defaultLoadProperties);
        }
        if (inputFormat != null && inputFormat.getLoadProperties() != null) {
            loadProperties.putAll(inputFormat.getLoadProperties());
        }
        return loadProperties;
    }

    @Override
    public Map<String, ?> getStoreProperties(File outputFile, XComponent document) {
        DocumentFamily family = OfficeDocumentUtils.getDocumentFamily(document);
        return outputFormat.getStoreProperties(family);
    }
}
