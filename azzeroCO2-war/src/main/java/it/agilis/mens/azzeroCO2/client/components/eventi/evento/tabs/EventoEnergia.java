package it.agilis.mens.azzeroCO2.client.components.eventi.evento.tabs;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.layout.*;
import com.google.gwt.user.client.Element;

//import com.extjs.gxt.samples.resources.client.Resources;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 6/19/11
 * Time: 5:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class EventoEnergia extends TabItem {


    private VerticalPanel vp;

    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);

        vp = new VerticalPanel();
        vp.setSpacing(10);
        createColumnForm();

        createTabForm();
        add(vp);
    }

    private void createTabForm() {
        FormData formData = new FormData("100%");
        FormPanel panel = new FormPanel();
        panel.setBodyStyleName("example-bg");
        panel.setPadding(0);
        panel.setFrame(false);
        panel.setHeaderVisible(false);
        panel.setBodyBorder(false);

        panel.setButtonAlign(HorizontalAlignment.CENTER);
        panel.setLayout(new FitLayout());

        //final TabPanel tabs = new TabPanel();

        //TabItem evento = new TabItem();
        //personal.setStyleAttribute("padding", "10px");
        //personal.setText("Personal Details");
        //personal.setLayout(new FormLayout());


        panel.setSize(500, 200);


        vp.add(panel);
    }

    private void createColumnForm() {
        FormData formData = new FormData("100%");
        FormPanel panel = new FormPanel();
        panel.setFrame(true);

        panel.setHeading("Dettagli Evento");
        panel.setSize(600, -1);
        panel.setLabelAlign(LabelAlign.TOP);

        panel.setButtonAlign(HorizontalAlignment.CENTER);

        LayoutContainer main = new LayoutContainer();
        main.setLayout(new ColumnLayout());

        LayoutContainer left = new LayoutContainer();
        left.setStyleAttribute("paddingRight", "10px");

        FormLayout layout = new FormLayout();
        layout.setLabelAlign(LabelAlign.LEFT);
        left.setLayout(layout);

        NumberField energiaElettrica = new NumberField();
        energiaElettrica.setFieldLabel("Energia Elettrica");

        left.add(energiaElettrica, formData);


        NumberField gasMetano = new NumberField();
        gasMetano.setFieldLabel("Gas Metano");
        left.add(gasMetano, formData);


        NumberField gasolio = new NumberField();
        gasolio.setFieldLabel("Gasolio");
        left.add(gasolio, formData);


        LayoutContainer right = new LayoutContainer();
        right.setStyleAttribute("paddingLeft", "10px");
        layout = new FormLayout();
        layout.setLabelAlign(LabelAlign.TOP);
        right.setLayout(layout);


        LabelField kwh = new LabelField();
        kwh.setValue("Kw/h");
        right.add(kwh, formData);


        LabelField m3 = new LabelField();
        m3.setValue("metri cubi");
        right.add(m3, formData);

        LabelField litri = new LabelField();
        litri.setValue("litri");
        right.add(litri, formData);


        main.add(left, new ColumnData(.5));
        main.add(right, new ColumnData(.5));

        panel.add(main, new FormData("100%"));
        vp.add(panel);
    }

}

