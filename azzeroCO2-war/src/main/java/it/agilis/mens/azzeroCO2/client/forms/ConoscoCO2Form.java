package it.agilis.mens.azzeroCO2.client.forms;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.util.Padding;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.layout.*;
import com.google.gwt.user.client.Element;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 7/2/11
 * Time: 12:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class ConoscoCO2Form extends LayoutContainer {

    private FormPanel panel = new FormPanel();

    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);

        setLayout(new BorderLayout());
        BorderLayoutData centerData = new BorderLayoutData(Style.LayoutRegion.CENTER);
        centerData.setMargins(new Margins(0));
        add(panel, centerData);
            panel.setFrame(true);
        createColumnForm();

    }


    private void createColumnForm() {
        FormData formData = new FormData("100%");
        panel.setHeading("Kg CO2");
        panel.setHeight(650);
        panel.setLabelAlign(FormPanel.LabelAlign.LEFT);


        HBoxLayoutData flex = new HBoxLayoutData(new Margins(0, 5, 0, 0));

        LayoutContainer kgCO2 = new LayoutContainer();
        HBoxLayout layoutKgCO2 = new HBoxLayout();
        layoutKgCO2.setPadding(new Padding(10));
        layoutKgCO2.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.BOTTOM);
        kgCO2.setLayout(layoutKgCO2);

        NumberField kgCO2Field = new NumberField();
        kgCO2Field.setWidth(60);


        kgCO2.add(new LabelField("Kg di CO2"), flex);
        kgCO2.add(kgCO2Field, flex);

        panel.add(kgCO2, new FormData("100%"));


    }

}
