package it.agilis.mens.azzeroCO2.client.forms.evento;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.grid.*;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.RowData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Element;
import it.agilis.mens.azzeroCO2.shared.model.RiepilogoModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 6/19/11
 * Time: 5:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class EventoFormRiepilogo extends LayoutContainer {

    private ListStore<RiepilogoModel> store = new ListStore<RiepilogoModel>();

    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);

        BorderLayout layout = new BorderLayout();
        setLayout(layout);

        ContentPanel cp = new ContentPanel();
        cp.setFrame(true);
        cp.setHeaderVisible(false);
        cp.setLayout(new RowLayout(Style.Orientation.HORIZONTAL));

        ContentPanel cpEst = new ContentPanel();
        cpEst.setFrame(false);
        cpEst.setHeaderVisible(false);
        cpEst.setLayout(new RowLayout(Style.Orientation.VERTICAL));
        cpEst.add(createGrid(), new RowData(1, 1));

        cp.add(cpEst, new RowData(1, 1));

        cp.setHeading("Riepilogo");

        // TODO MIGLIORARE
        cp.setHeight(450);

        BorderLayoutData centerData = new BorderLayoutData(Style.LayoutRegion.CENTER);
        add(cp, centerData);

    }

    private Grid<RiepilogoModel> createGrid() {

        final NumberFormat number = NumberFormat.getFormat("0.00");
        List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

        ColumnConfig column = new ColumnConfig("oggetto", "Oggetto", 190);
        configs.add(column);

        column = new ColumnConfig("dettagli", "Dettagli", 410);
        configs.add(column);

        column = new ColumnConfig("kgCO2", "Kg/CO2", 60);
        column.setAlignment(Style.HorizontalAlignment.RIGHT);

        configs.add(column);

        ColumnModel cm = new ColumnModel(configs);

        AggregationRowConfig<RiepilogoModel> somma = new AggregationRowConfig<RiepilogoModel>();
        somma.setHtml("name", "Somma");
        somma.setSummaryType("kgCO2", SummaryType.SUM);
        somma.setRenderer("kgCO2", new AggregationRenderer<RiepilogoModel>() {
            public Object render(Number value, int colIndex, Grid<RiepilogoModel> grid, ListStore<RiepilogoModel> store) {
                Double v = (Double) value;
                if (v == null) {
                    return 0;
                }
                return number.format(v);
            }
        });
        cm.addAggregationRow(somma);

        somma = new AggregationRowConfig<RiepilogoModel>();
        somma.setHtml("name", "kgCO2");


        Grid<RiepilogoModel> grid = new Grid<RiepilogoModel>(store, cm);
        grid.setBorders(true);
        grid.setAutoHeight(true);

        return grid;
    }

    public void setEventoRiepilogoInStore(List<RiepilogoModel> models) {
        for (RiepilogoModel r : store.getModels()) {
            store.remove(r);
        }
        this.store.add(models);
    }

    public void clear() {
    }
}


