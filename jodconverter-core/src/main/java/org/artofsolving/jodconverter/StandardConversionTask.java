//
// JODConverter - Java OpenDocument Converter
// Copyright 2004-2011 Mirko Nasato and contributors
//
// JODConverter is free software: you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public License
// as published by the Free Software Foundation, either version 3 of
// the License, or (at your option) any later version.
//
// JODConverter is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General
// Public License along with JODConverter.  If not, see
// <http://www.gnu.org/licenses/>.
//
package org.artofsolving.jodconverter;

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
import org.artofsolving.jodconverter.document.DocumentFamily;
import org.artofsolving.jodconverter.document.DocumentFormat;
import org.artofsolving.jodconverter.office.OfficeException;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static org.artofsolving.jodconverter.office.OfficeUtils.cast;

public class StandardConversionTask extends AbstractConversionTask {

    private final DocumentFormat outputFormat;

    private Map<String, ?> defaultLoadProperties;
    private DocumentFormat inputFormat;


    public StandardConversionTask(File inputFile, File outputFile, DocumentFormat outputFormat) {
        super(inputFile, outputFile);
        this.outputFormat = outputFormat;
    }

    public void setDefaultLoadProperties(Map<String, ?> defaultLoadProperties) {
        this.defaultLoadProperties = defaultLoadProperties;
    }

    public void setInputFormat(DocumentFormat inputFormat) {
        this.inputFormat = inputFormat;
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

                if (name.equalsIgnoreCase("KgCO2")) {
                    XPropertySet loXPropertySet = UnoRuntime.queryInterface(XPropertySet.class, dependentTextField);
                    loXPropertySet.setPropertyValue("Content", "1000000");
                    loXPropertySet.setPropertyValue("Hint", "fieldHint");
                }  else  if (name.equalsIgnoreCase("NomeAzienda")) {
                    XPropertySet loXPropertySet = UnoRuntime.queryInterface(XPropertySet.class, dependentTextField);
                    loXPropertySet.setPropertyValue("Content", "FINTUS ");
                    loXPropertySet.setPropertyValue("Hint", "fieldHint");
                }  else  if (name.equalsIgnoreCase("NomeEvento")) {
                    XPropertySet loXPropertySet = UnoRuntime.queryInterface(XPropertySet.class, dependentTextField);
                    loXPropertySet.setPropertyValue("Content", "AMMAZZIAMO GLI ANIMALI");
                    loXPropertySet.setPropertyValue("Hint", "fieldHint");
                }
            }

            if (refreshable != null) {
                refreshable.refresh();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Map<String, ?> getLoadProperties(File inputFile) {
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
    protected Map<String, ?> getStoreProperties(File outputFile, XComponent document) {
        DocumentFamily family = OfficeDocumentUtils.getDocumentFamily(document);
        return outputFormat.getStoreProperties(family);
    }


}
