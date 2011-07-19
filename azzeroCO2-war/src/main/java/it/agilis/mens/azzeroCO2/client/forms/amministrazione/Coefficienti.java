package it.agilis.mens.azzeroCO2.client.forms.amministrazione;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Element;
import it.agilis.mens.azzeroCO2.shared.model.amministrazione.Coefficiente;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 6/19/11
 * Time: 5:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class Coefficienti extends LayoutContainer {
    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);

        BorderLayout layout = new BorderLayout();
        setLayout(layout);

        ContentPanel centre= createCentre();
        centre.setHeading("Coefficienti");
      //  centre.setHeight(650);

        BorderLayoutData centerData = new BorderLayoutData(Style.LayoutRegion.CENTER);
        centerData.setMargins(new Margins(0));
        add(centre, centerData);

    }

   private ContentPanel createCentre() {
        ContentPanel centre = new ContentPanel();
        final ListStore<Coefficiente> store = new ListStore<Coefficiente>();
        {  //TODO
            store.add(new Coefficiente("Gasolio", "Energia", 0.034));
            store.add(new Coefficiente("Gas", "Energia", 0.015));
            store.add(new Coefficiente("Notti", "Pernottamenti", 0.025));
            store.add(new Coefficiente("Tratta bus 50km", "Trasporto persone", 0.12));
            store.add(new Coefficiente("Tratta, aereo 500km", "Trasporto persone", 0.24));
        }

        final NumberFormat number = NumberFormat.getFormat("0.00");

        List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

        ColumnConfig column = new ColumnConfig("coefficiente", "Coefficiente", 300);
        configs.add(column);

        column = new ColumnConfig("tipologia", "Tipologia", 445);
        configs.add(column);

        column = new ColumnConfig("valore", "Valore", 100);
        column.setAlignment(Style.HorizontalAlignment.RIGHT);
        configs.add(column);

        ColumnModel cm = new ColumnModel(configs);

        Grid<Coefficiente> grid = new Grid<Coefficiente>(store, cm);
        grid.setBorders(true);
        grid.setAutoHeight(true);

        centre.add(grid);

         return centre;
    }

    public void clear() {
    }
}


