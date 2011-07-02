package it.agilis.mens.azzeroCO2.client.forms;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.util.Padding;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.TabItem;
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
public class EventoFormPernottamenti extends TabItem {

    private FormPanel panel = new FormPanel();

    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);

        setLayout(new BorderLayout());
        BorderLayoutData centerData = new BorderLayoutData(Style.LayoutRegion.CENTER);
        centerData.setMargins(new Margins(0));
        add(panel, centerData);

        createColumnForm();

    }


    private void createColumnForm() {
        FormData formData = new FormData("100%");

        panel.setFrame(true);

        panel.setHeading("Pernottamenti");
        panel.setSize(600, -1);
        panel.setLabelAlign(FormPanel.LabelAlign.LEFT);


        HBoxLayoutData flex = new HBoxLayoutData(new Margins(0, 5, 0, 0));

        LayoutContainer notti = new LayoutContainer();
        HBoxLayout layoutNotti = new HBoxLayout();
        layoutNotti.setPadding(new Padding(10));
        layoutNotti.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.BOTTOM);
        notti.setLayout(layoutNotti);

        NumberField numeroNotti = new NumberField();
        numeroNotti.setWidth(60);


        notti.add(new LabelField("notti"), flex);
        notti.add(numeroNotti, flex);

        panel.add(notti, new FormData("100%"));


    }

}
