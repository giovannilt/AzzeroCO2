package it.agilis.mens.azzeroCO2.client.components;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.util.Padding;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.layout.*;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Element;
import it.agilis.mens.azzeroCO2.shared.model.RiepilogoModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 11/30/11
 * Time: 6:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class EventoCompensatoDialog extends Dialog {

    private ListStore<RiepilogoModel> store = new ListStore<RiepilogoModel>();
    private Text totaleText = new Text("Totale KG/CO2");
    private Text totale = new Text("0.00");

    final NumberFormat number = NumberFormat.getFormat("0.00");

    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);

        BorderLayout layout_C = new BorderLayout();
        setLayout(layout_C);

        ContentPanel cp = new ContentPanel();
        cp.setFrame(true);
        cp.setHeaderVisible(false);
        cp.setLayout(new RowLayout(Style.Orientation.HORIZONTAL));

        ContentPanel cpEst = new ContentPanel();
        cpEst.setFrame(false);
        cpEst.setHeaderVisible(false);
        cpEst.setLayout(new RowLayout(Style.Orientation.VERTICAL));
        cpEst.add(createGrid(), new RowData(1, 0.95));

        cp.add(cpEst, new RowData(1, 1));

        LayoutContainer c = new LayoutContainer();
        c.setHeight(30);
        HBoxLayout layout = new HBoxLayout();
        layout.setPadding(new Padding(1));
        layout.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.MIDDLE);
        c.setLayout(layout);
        HBoxLayoutData flex = new HBoxLayoutData(new Margins(0, 0, 0, 5));
        flex.setFlex(1);
        c.add(totaleText, flex);

        totaleText.setSize(300, 15);
        totaleText.setStyleAttribute("font-family", "arial");
        totaleText.setStyleAttribute("font-size", "14px");
        totaleText.setStyleAttribute("color", "#D38131");
        totale.setSize(250, 15);
        totale.setStyleAttribute("text-align", "right");
        totale.setStyleAttribute("font-family", "arial");
        totale.setStyleAttribute("font-size", "14px");
        totale.setStyleAttribute("color", "#D38131");
        c.add(totale, new HBoxLayoutData(new Margins(0, 20, 0, 0)));

        cpEst.add(c, new RowData(1, 0.05));

        // TODO MIGLIORARE
        cp.setHeight(300);

        this.setHeight(400);
        this.setWidth(600);

        BorderLayoutData centerData = new BorderLayoutData(Style.LayoutRegion.CENTER);
        add(cp, centerData);

    }

    private Grid<RiepilogoModel> createGrid() {


        List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

        ColumnConfig column = new ColumnConfig("oggetto", "Oggetto", 160);
        configs.add(column);

        column = new ColumnConfig("dettagli", "Dettagli", 346);
        configs.add(column);

        ColumnModel cm = new ColumnModel(configs);
        Grid<RiepilogoModel> grid = new Grid<RiepilogoModel>(store, cm);
        grid.setBorders(true);
        grid.setHeight(350);

        return grid;
    }

    public void setInStore(List<RiepilogoModel> models) {
        this.store.removeAll();
        this.store.add(models);
    }

    public void setTotale(Double totale) {
        this.totale.setText(number.format(totale));
    }

    @Override
    protected void createButtons() {
       // super.createButtons();    //To change body of overridden methods use File | Settings | File Templates.
    }
}
