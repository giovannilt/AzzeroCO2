package it.agilis.mens.azzeroCO2.client.pagAmministrazione;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.grid.*;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Element;
import it.agilis.mens.azzeroCO2.shared.model.Riepilogo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 6/19/11
 * Time: 5:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class AmministrazioneProgrammiPage extends LayoutContainer {

    ContentPanel centre = new ContentPanel();
    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);

        BorderLayout layout = new BorderLayout();
        setLayout(layout);

        createCentre();
        centre.setHeading("Riepilogo");
        centre.setHeight(650);

        BorderLayoutData centerData = new BorderLayoutData(Style.LayoutRegion.CENTER);
        centerData.setMargins(new Margins(0));
        add(centre, centerData);

    }

    private void createCentre() {
        final ListStore<Riepilogo> store = new ListStore<Riepilogo>();
        {  //TODO
            store.add(new Riepilogo("Energia", "Energia Elettrica XX <br> Gasolio YY", 10.0));
            store.add(new Riepilogo("Trasporto Persone/ relatori", "Energia Elettrica XX <br> Gasolio YY", 10.0));
            store.add(new Riepilogo("Trasporto Persone", "Energia Elettrica XX <br> Gasolio YY", 10.0));
            store.add(new Riepilogo("Pubblicazione rilegate", "Energia Elettrica XX <br> Gasolio YY", 1.4));
            store.add(new Riepilogo("Manifesti, pieghevoli, fogli / programma", "Energia Elettrica XX <br> Gasolio YY", 10.0));
        }

        final NumberFormat number = NumberFormat.getFormat("0.00");

        List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

        ColumnConfig column = new ColumnConfig("oggetto", "Oggetto", 300);
        configs.add(column);

        column = new ColumnConfig("dettagli", "Dettagli", 445);
        configs.add(column);

        column = new ColumnConfig("kgCO2", "Kg/CO2", 100);
        column.setAlignment(Style.HorizontalAlignment.RIGHT);
        configs.add(column);

        ColumnModel cm = new ColumnModel(configs);

        AggregationRowConfig<Riepilogo> somma = new AggregationRowConfig<Riepilogo>();
        somma.setHtml("name", "Somma");

        somma.setSummaryType("kgCO2", SummaryType.SUM);
        somma.setRenderer("kgCO2", new AggregationRenderer<Riepilogo>() {
            public Object render(Number value, int colIndex, Grid<Riepilogo> grid, ListStore<Riepilogo> store) {
                return number.format(value.doubleValue());
            }
        });
        cm.addAggregationRow(somma);

        somma = new AggregationRowConfig<Riepilogo>();
        somma.setHtml("name", "kgCO2");

        Grid<Riepilogo> grid = new Grid<Riepilogo>(store, cm);
        grid.setBorders(true);
        grid.setAutoHeight(true);

        centre.add(grid);


    }

    public void clear() {
    }
}


