package it.agilis.mens.azzeroCO2.client.forms.amministrazione;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
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
import it.agilis.mens.azzeroCO2.client.mvc.events.CentralEvents;
import it.agilis.mens.azzeroCO2.client.mvc.events.EventoEvents;
import it.agilis.mens.azzeroCO2.shared.Eventi;
import it.agilis.mens.azzeroCO2.shared.model.evento.DettaglioModel;

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

    private final ListStore<DettaglioModel> store = new ListStore<DettaglioModel>();

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


        ColumnConfig column = new ColumnConfig("ordineId", "Num. Ordine", 100);
        configs.add(column);


        column = new ColumnConfig("nome", "Descrizione", 200);
        configs.add(column);

        /*column = new ColumnConfig("kgco2", "KG CO2", 100);
        column.setAlignment((Style.HorizontalAlignment.RIGHT));
        column.setRenderer(new GridCellRenderer<DettaglioModel>() {
            @Override
            public Object render(DettaglioModel model, String property, ColumnData config, int rowIndex, int colIndex, ListStore<DettaglioModel> riepilogoModelListStore, Grid<DettaglioModel> riepilogoModelGrid) {
                return number.format(model.<Number>get(property));
            }
        });
        configs.add(column);*/

        column = new ColumnConfig("dataLastUpd", "Ultima modifica", 90);
        configs.add(column);

        column = new ColumnConfig("kgco2", "Kg CO2", 50);
        column.setAlignment((Style.HorizontalAlignment.RIGHT));
        configs.add(column);

        column = new ColumnConfig("importo", "Importo", 50);
        column.setAlignment((Style.HorizontalAlignment.RIGHT));
        configs.add(column);

        column = new ColumnConfig("flag compensato", "Compensato", 50);
        configs.add(column);
        // TODO popolare le colonne non ancora popolate


        ColumnModel cm = new ColumnModel(configs);




        Grid<DettaglioModel> grid = new Grid<DettaglioModel>(store, cm);
        grid.setBorders(true);
        // grid.setAutoHeight(true);
        grid.setHeight(430);
        //    centre.setBottomComponent(toolBar);

        grid.getSelectionModel().setSelectionMode(Style.SelectionMode.SINGLE);
        grid.getSelectionModel().addListener(Events.SelectionChange,
                new Listener<SelectionChangedEvent<DettaglioModel>>() {
                    public void handleEvent(SelectionChangedEvent<DettaglioModel> be) {
                        if (be.getSelection().size() > 0) {
                            //TODO... dispecchare evento per il load corretto
                            Dispatcher.forwardEvent(EventoEvents.LoadEvento, be.getSelectedItem());
                            Dispatcher.forwardEvent(CentralEvents.ShowPanel, Eventi.EVENTO);
                            Info.display("Info", "Uploading Evento... " + be.getSelectedItem().getNome());
                        }
                    }
                });

        centre.add(grid);

        return centre;
    }

    public void setOrdiniInStore(List<DettaglioModel> ordiniModels) {
        try {
            store.removeAll();
            store.add(ordiniModels);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void clear() {
    }
}


