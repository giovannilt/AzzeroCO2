package it.agilis.mens.azzeroCO2.client.forms.amministrazione;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.grid.*;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Element;
import it.agilis.mens.azzeroCO2.shared.model.OrdineModel;
import it.agilis.mens.azzeroCO2.shared.model.RiepilogoModel;
import it.agilis.mens.azzeroCO2.shared.model.evento.ManifestiPieghevoliFogliModel;

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

    private final ListStore<OrdineModel> store = new ListStore<OrdineModel>();

    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);

        setLayout(new BorderLayout());
        ContentPanel centre = createCentre();
        centre.setHeaderVisible(false);
        centre.setFrame(true);
        centre.setHeight(477);
        centre.setFrame(true);
        BorderLayoutData centerData = new BorderLayoutData(Style.LayoutRegion.CENTER);
        centerData.setMargins(new Margins(0));
        add(centre, centerData);
    }

    private ContentPanel createCentre() {
        ContentPanel centre = new ContentPanel();
        final NumberFormat number = NumberFormat.getFormat("0.00");
        List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
        ColumnConfig column = new ColumnConfig("data", "Data Ordine", 100);
        configs.add(column);

        column = new ColumnConfig("cliente", "Cliente", 300);
        configs.add(column);

        column = new ColumnConfig("programma", "ProgettoDiCompensazione", 200);
        configs.add(column);

        column = new ColumnConfig("kgco2", "KG CO2", 100);
        column.setAlignment((Style.HorizontalAlignment.RIGHT));
        column.setRenderer(new GridCellRenderer<RiepilogoModel>() {
            @Override
            public Object render(RiepilogoModel model, String property, ColumnData config, int rowIndex, int colIndex, ListStore<RiepilogoModel> riepilogoModelListStore, Grid<RiepilogoModel> riepilogoModelGrid) {
                return number.format(model.<Number>get(property));
            }
        });
        configs.add(column);

        column = new ColumnConfig("Importo", "Importo ", 100);
        column.setAlignment(Style.HorizontalAlignment.RIGHT);
        configs.add(column);

        ColumnModel cm = new ColumnModel(configs);

        Grid<OrdineModel> grid = new Grid<OrdineModel>(store, cm);
        grid.setBorders(true);
        // grid.setAutoHeight(true);
        grid.setHeight(430);
        //    centre.setBottomComponent(toolBar);

        grid.getSelectionModel().setSelectionMode(Style.SelectionMode.MULTI);
        grid.getSelectionModel().addListener(Events.SelectionChange,
                new Listener<SelectionChangedEvent<ManifestiPieghevoliFogliModel>>() {
                    public void handleEvent(SelectionChangedEvent<ManifestiPieghevoliFogliModel> be) {
                        if (be.getSelection().size() > 0) {
                           Info.display("Info", "Upload-Evento!!!!");
                        }
                    }
                });

        centre.add(grid);

        return centre;
    }

    public void setOrdiniInStore(List<OrdineModel> ordiniModels) {
        store.removeAll();
        store.add(ordiniModels);
    }


    public void clear() {
    }
}


