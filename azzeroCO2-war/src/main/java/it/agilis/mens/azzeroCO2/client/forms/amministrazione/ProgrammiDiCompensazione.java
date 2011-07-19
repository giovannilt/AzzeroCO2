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
public class ProgrammiDiCompensazione extends LayoutContainer {

    ContentPanel centre = new ContentPanel();

    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);

        BorderLayout layout = new BorderLayout();
        setLayout(layout);

        ContentPanel centre = createCentre();
        centre.setHeading("Programmi Di Compensazione");
        //  centre.setHeight(650);

        BorderLayoutData centerData = new BorderLayoutData(Style.LayoutRegion.CENTER);
        centerData.setMargins(new Margins(0));
        add(centre, centerData);

    }

    private ContentPanel createCentre() {
        ContentPanel centre = new ContentPanel();
        final ListStore<ProgettiDiCompensazione> store = new ListStore<ProgettiDiCompensazione>();
        {  //TODO
            store.add(new ProgettiDiCompensazione("Eolico a Vigata", "Enargia", 10.0, "Si"));
            store.add(new ProgettiDiCompensazione("Foresta a Fela", "Vegetazione", 15.0, "Si"));
            store.add(new ProgettiDiCompensazione("Solare a Montelusa", "Energia", 20.0, "No"));
        }

        final NumberFormat number = NumberFormat.getFormat("0.00");

        List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

        ColumnConfig column = new ColumnConfig("neme", "Progetto di compensazione", 300);
        configs.add(column);

        column = new ColumnConfig("kgCO2", "Prezzo kg/CO2", 100);
        column.setAlignment(Style.HorizontalAlignment.RIGHT);
        configs.add(column);

        column = new ColumnConfig("attivo", "Attivo", 100);
        configs.add(column);

        ColumnModel cm = new ColumnModel(configs);


        Grid<ProgettiDiCompensazione> grid = new Grid<ProgettiDiCompensazione>(store, cm);
        grid.setBorders(true);
        grid.setAutoHeight(true);

        centre.add(grid);

        return centre;
    }

    public void clear() {
    }
}


