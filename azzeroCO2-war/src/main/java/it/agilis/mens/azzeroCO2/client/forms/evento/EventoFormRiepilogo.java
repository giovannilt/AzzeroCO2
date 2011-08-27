package it.agilis.mens.azzeroCO2.client.forms.evento;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.Margins;
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

    private final ListStore<RiepilogoModel> store = new ListStore<RiepilogoModel>();
    private Grid<RiepilogoModel> grid;

    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);

        BorderLayout layout = new BorderLayout();
        setLayout(layout);


        ContentPanel cp = new ContentPanel();
        cp.setFrame(true);
        cp.setHeading("Riepilogo");
        cp.setLayout(new RowLayout(Style.Orientation.VERTICAL));
        grid = createGrid();
        cp.add(grid, new RowData(1, 1, new Margins(0, 0, 0, 0)));

        BorderLayoutData centerData = new BorderLayoutData(Style.LayoutRegion.CENTER);
        centerData.setMargins(new Margins(0, 0, 0, 0));
        add(cp, centerData);

    }

    private Grid<RiepilogoModel> createGrid() {

        {  //TODO
            store.add(new RiepilogoModel("Energia", "Energia Elettrica XX <br> Gasolio YY", 10.0));
            store.add(new RiepilogoModel("Trasporto Persone/ relatori", "Energia Elettrica XX <br> Gasolio YY", 10.0));
            store.add(new RiepilogoModel("Trasporto Persone", "Energia Elettrica XX <br> Gasolio YY", 10.0));
            store.add(new RiepilogoModel("Pubblicazione rilegate", "Energia Elettrica XX <br> Gasolio YY", 1.4));
            store.add(new RiepilogoModel("Manifesti, pieghevoli, fogli / programma", "Energia Elettrica XX <br> Gasolio YY", 10.0));
        }

        final NumberFormat number = NumberFormat.getFormat("0.00");

        List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

        ColumnConfig column = new ColumnConfig("oggetto", "Oggetto", 290);
        configs.add(column);

        column = new ColumnConfig("dettagli", "Dettagli", 440);
        configs.add(column);

        column = new ColumnConfig("kgCO2", "Kg/CO2", 100);
        column.setAlignment(Style.HorizontalAlignment.RIGHT);
        configs.add(column);

        ColumnModel cm = new ColumnModel(configs);

        AggregationRowConfig<RiepilogoModel> somma = new AggregationRowConfig<RiepilogoModel>();
        somma.setHtml("name", "Somma");

        somma.setSummaryType("kgCO2", SummaryType.SUM);
        somma.setRenderer("kgCO2", new AggregationRenderer<RiepilogoModel>() {
            public Object render(Number value, int colIndex, Grid<RiepilogoModel> grid, ListStore<RiepilogoModel> store) {
                return number.format(value.doubleValue());
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

    public void clear() {
    }
}


