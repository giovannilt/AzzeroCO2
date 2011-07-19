package it.agilis.mens.azzeroCO2.client.forms;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.util.Padding;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.ToolButton;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.layout.*;
import com.google.gwt.user.client.Element;
import it.agilis.mens.azzeroCO2.shared.model.amministrazione.ProgettiDiCompensazione;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 6/19/11
 * Time: 5:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class EventoFormAcquisto extends LayoutContainer {

    ContentPanel east = new ContentPanel();
    ContentPanel centre = new ContentPanel();


    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);

        BorderLayout layout = new BorderLayout();
        setLayout(layout);
        layout.setEnableState(false);
        setStyleAttribute("padding", "0px");

        createEast();
        east.setHeading("Acquisto");
        BorderLayoutData westData = new BorderLayoutData(Style.LayoutRegion.EAST, 300);
        east.getHeader().addTool(new ToolButton("x-tool-help"));
        east.getHeader().addTool(new ToolButton("x-tool-refresh"));
        westData.setMargins(new Margins(0));
        east.setAutoHeight(true);
        add(east, westData);

        createCentre();
        centre.setHeading("Progetti Di Compensazione");

        BorderLayoutData centerData = new BorderLayoutData(Style.LayoutRegion.CENTER);
        centerData.setMargins(new Margins(0));
        add(centre, centerData);

    }

    private void createEast() {
        VerticalPanel vp = new VerticalPanel();
        //  vp.setSpacing(10);
        east.add(vp);
        vp.setHeight(600);
        FormData formData = new FormData("100%");
        FormPanel panel = new FormPanel();
        panel.setFrame(true);
        panel.setHeaderVisible(false);

        panel.setSize(530, -1);
        panel.setLabelAlign(FormPanel.LabelAlign.LEFT);
        HBoxLayoutData flex = new HBoxLayoutData(new Margins(0, 5, 0, 0));

        {
            LayoutContainer c = new LayoutContainer();
            HBoxLayout layout = new HBoxLayout();
            layout.setPadding(new Padding(5));
            layout.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.BOTTOM);
            c.setLayout(layout);
            LabelField label = new LabelField("Evento: ");
            label.setStyleAttribute("font-size", "16px");
            c.add(label, flex);


            panel.add(c);
        }
        {
            {
                LayoutContainer c = new LayoutContainer();
                HBoxLayout layout = new HBoxLayout();
                layout.setPadding(new Padding(5));
                layout.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.BOTTOM);
                c.setLayout(layout);

                LabelField label = new LabelField("Convegno Kyoto Club <br> Roma XXXXXX ");
                label.setWidth(220);
                c.add(label);

                panel.add(c, new FormData("100%"));
            }
            {
                LayoutContainer c = new LayoutContainer();
                HBoxLayout layout = new HBoxLayout();
                layout.setPadding(new Padding(5));
                layout.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.BOTTOM);
                c.setLayout(layout);

                LabelField label = new LabelField("Kg/CO2");
                label.setWidth(220);
                c.add(label);
                c.add(new LabelField(" 13.00"), flex);

                panel.add(c, new FormData("100%"));
            }

            {    // PROGETTO SCELTO
                LayoutContainer c = new LayoutContainer();
                HBoxLayout layout = new HBoxLayout();
                layout.setPadding(new Padding(5));
                layout.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.BOTTOM);
                c.setLayout(layout);

                LabelField label = new LabelField("Progetto:");
                label.setStyleAttribute("font-size", "16px");
                label.setWidth(220);
                c.add(label);

                panel.add(c, new FormData("100%"));
            }
            {
                LayoutContainer c = new LayoutContainer();
                HBoxLayout layout = new HBoxLayout();
                layout.setPadding(new Padding(5));
                layout.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.BOTTOM);
                c.setLayout(layout);

                LabelField label = new LabelField("XXXXXXXXXXXXXXXXXXXXXXXXX");
                label.setWidth(220);
                c.add(label);

                panel.add(c, new FormData("100%"));
            }

            {
                LayoutContainer c = new LayoutContainer();
                HBoxLayout layout = new HBoxLayout();
                layout.setPadding(new Padding(5));
                layout.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.BOTTOM);
                c.setLayout(layout);

                LabelField label = new LabelField("€ x Kg/CO2 ");
                label.setWidth(220);
                c.add(label);
                label = new LabelField(" 10.00");
                c.add(label);

                panel.add(c, new FormData("100%"));
            }

            { // TOTALE
                LayoutContainer c = new LayoutContainer();
                HBoxLayout layout = new HBoxLayout();
                layout.setPadding(new Padding(5));
                layout.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.BOTTOM);
                c.setLayout(layout);

                LabelField label = new LabelField("Totale € ");
                label.setStyleAttribute("color", "#FF9933");
                label.setStyleAttribute("font-size", "16px");
                label.setWidth(210);
                c.add(label);

                label = new LabelField(" 130.00");
                label.setStyleAttribute("color", "#FF9933");
                label.setStyleAttribute("font-size", "16px");
                c.add(label, flex);

                panel.add(c, new FormData("100%"));
            }
            { // Coupon
                LayoutContainer c = new LayoutContainer();
                HBoxLayout layout = new HBoxLayout();
                layout.setPadding(new Padding(5));
                layout.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.BOTTOM);
                c.setLayout(layout);

                LabelField label = new LabelField("Hai Un Coupon? ");
                label.setWidth(100);
                c.add(label);

                TextField<String> coupon = new TextField<String>();
                coupon.setWidth(170);

                c.add(coupon, flex);

                panel.add(c, new FormData("100%"));
            }
            { // Coupon
                LayoutContainer c = new LayoutContainer();
                c.setHeight(295);
                c.setWidth(290);
                c.setStyleAttribute("background-color", "#FF9933");
                HBoxLayout layout = new HBoxLayout();
                layout.setPadding(new Padding(10));
                layout.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.MIDDLE);
                c.setLayout(layout);

                LabelField label = new LabelField("AzzeroCO2 puo' offrirti <br>consulenza per<br>la riduzione delle emissioni <br>Chiamaci !!");
                label.setStyleAttribute("font-size", "20px");
                label.setWidth(300);
                c.add(label);
                panel.add(c, new FormData("100%"));
            }

        }
        vp.add(panel);
    }


    private void createCentre() {
        final ListStore<ProgettiDiCompensazione> store = new ListStore<ProgettiDiCompensazione>();
        {  //TODO
            store.add(new ProgettiDiCompensazione("Biomassa in Valtellina", "UNO", 123.0,"Si"));
            store.add(new ProgettiDiCompensazione("Forestazione in Italia", "UNO",123.0,"Si"));
            store.add(new ProgettiDiCompensazione("Eolico in India", "UNO",123.0,"Si"));
            store.add(new ProgettiDiCompensazione("Biogas da discarica a Chicago", "UNO",120.0,"Si"));
      }

    List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

        ColumnConfig column = new ColumnConfig();
        column.setId("img");
        column.setWidth(150);
        configs.add(column);

        column = new ColumnConfig();
        column.setId("name");
        column.setWidth(247);
        configs.add(column);

        column = new ColumnConfig();
        column.setId("kgCO2");
        column.setAlignment(Style.HorizontalAlignment.RIGHT);
        column.setWidth(150);
        configs.add(column);

        ColumnModel cm = new ColumnModel(configs);

        Grid<ProgettiDiCompensazione> grid = new Grid<ProgettiDiCompensazione>(store, cm);
        grid.setBorders(true);
        grid.setHideHeaders(true);
        grid.setHeight(600);
        centre.add(grid);


    }


    public void clear() {
    }
}

