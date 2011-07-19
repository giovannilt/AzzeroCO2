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
import it.agilis.mens.azzeroCO2.shared.model.Coupon;
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
public class AmministrazioneCouponPage extends LayoutContainer {

    ContentPanel centre = new ContentPanel();
    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);

        BorderLayout layout = new BorderLayout();
        setLayout(layout);

        createCentre();
        centre.setHeading("Gestione coupon");
        centre.setHeight(650);

        BorderLayoutData centerData = new BorderLayoutData(Style.LayoutRegion.CENTER);
        centerData.setMargins(new Margins(0));
        add(centre, centerData);

    }

    private void createCentre() {
        final ListStore<Coupon> store = new ListStore<Coupon>();
        {  //TODO
            store.add(new Coupon("765489000", "Sconto 10% sig. Rossi", "%",10.0,null,null,null));
            store.add(new Coupon("98UUGB765", "Sconto 250 euro a Cesare", "%",250.0,null,null,null));
            //store.add(new Coupon("Manifesti, pieghevoli, fogli / programma", "Energia Elettrica XX <br> Gasolio YY", 10.0));
        }

        final NumberFormat number = NumberFormat.getFormat("0.00");

        List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

        ColumnConfig column = new ColumnConfig("codice", "Codice", 100);
        configs.add(column);

        column = new ColumnConfig("descrizione", "Descrizione", 200);
        configs.add(column);

        column = new ColumnConfig("tipo", "Tipo", 70);
        configs.add(column);


        column = new ColumnConfig("valore", "Valore", 100);
        column.setAlignment(Style.HorizontalAlignment.RIGHT);
        configs.add(column);

        column = new ColumnConfig("dataInizio", "Inizio validità", 100);
        configs.add(column);

        column = new ColumnConfig("dataFine", "Fine Validità", 100);
        configs.add(column);

        column = new ColumnConfig("attivo", "Attivo", 70);
        configs.add(column);



        ColumnModel cm = new ColumnModel(configs);






        Grid<Coupon> grid = new Grid<Coupon>(store, cm);
        grid.setBorders(true);
        grid.setAutoHeight(true);

        centre.add(grid);


    }

    public void clear() {
    }
}


