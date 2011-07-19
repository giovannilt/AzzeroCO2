package it.agilis.mens.azzeroCO2.client.forms.amministrazione;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.grid.*;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Element;
import it.agilis.mens.azzeroCO2.shared.model.amministrazione.Ordine;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 6/19/11
 * Time: 5:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class Ordini extends LayoutContainer {

    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);

        setLayout(new BorderLayout());
        ContentPanel centre = createCentre();
        centre.setHeading("Ordini");
        centre.setFrame(true);
        BorderLayoutData centerData = new BorderLayoutData(Style.LayoutRegion.CENTER);
        centerData.setMargins(new Margins(0));
        add(centre, centerData);
    }

    private ContentPanel createCentre() {
        ContentPanel centre = new ContentPanel();
        final ListStore<Ordine> store = new ListStore<Ordine>();
        {  //TODO
            store.add(new Ordine(null, "Mario Rossi", "Foresta amazzonica", 10.0, 100.0));
            store.add(new Ordine(null, "Giulio Cesare", "Eolico in Calabria", 10.0, 30.0));
            store.add(new Ordine(null, "Pippo Baudo", "Macchia mediterranea", 10.0, 45.0));
            store.add(new Ordine(null, "Bart Simpson", "Alberi nel deserto", 1.4, 130.0));
            store.add(new Ordine(null, "Johnny Beavo", "Solare a Joppolo", 10.0, 87.0));
        }

        final NumberFormat number = NumberFormat.getFormat("0.00");

        List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

        ColumnConfig column = new ColumnConfig("data", "Data Ordine", 100);
        configs.add(column);

        column = new ColumnConfig("cliente", "Cliente", 300);
        configs.add(column);

        column = new ColumnConfig("progetto", "ProgettoDiCompensazione", 200);
        configs.add(column);

        column = new ColumnConfig("kgco2", "KG CO2", 100);
        column.setAlignment((Style.HorizontalAlignment.RIGHT));
        configs.add(column);


        column = new ColumnConfig("Importo", "Importo €", 100);
        column.setAlignment(Style.HorizontalAlignment.RIGHT);
        configs.add(column);

        ColumnModel cm = new ColumnModel(configs);

        AggregationRowConfig<Ordine> somma = new AggregationRowConfig<Ordine>();
        somma.setHtml("name", "Totale");

        somma.setSummaryType("kgCO2", SummaryType.SUM);
        somma.setRenderer("kgCO2", new AggregationRenderer<Ordine>() {
            public Object render(Number value, int colIndex, Grid<Ordine> grid, ListStore<Ordine> store) {
                return number.format(value.doubleValue());
            }
        });
        cm.addAggregationRow(somma);

        somma = new AggregationRowConfig<Ordine>();
        somma.setHtml("name", "kgCO2");

        Grid<Ordine> grid = new Grid<Ordine>(store, cm);
        grid.setBorders(true);
        grid.setAutoHeight(true);

        centre.add(grid);

        return centre;
    }


    public void clear() {
    }
}


