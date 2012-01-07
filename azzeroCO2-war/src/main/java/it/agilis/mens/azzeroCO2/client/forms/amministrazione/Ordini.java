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
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Element;
import it.agilis.mens.azzeroCO2.client.mvc.events.AmministrazioneEvents;
import it.agilis.mens.azzeroCO2.client.mvc.events.CentralEvents;
import it.agilis.mens.azzeroCO2.client.mvc.events.EventoEvents;
import it.agilis.mens.azzeroCO2.shared.Eventi;
import it.agilis.mens.azzeroCO2.shared.model.OrdineModel;
import it.agilis.mens.azzeroCO2.shared.model.pagamento.Esito;

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
        centre.setHeight(478);
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

        column = new ColumnConfig("lastUpdate", "Ultima Modifica", 120);
        column.setDateTimeFormat(DateTimeFormat.getFormat("dd.MM.y HH:mm"));
        configs.add(column);

        column = new ColumnConfig("euro", "Euro", 150);
        column.setAlignment((Style.HorizontalAlignment.RIGHT));
        column.setRenderer(new GridCellRenderer<OrdineModel>() {
            public String render(OrdineModel model, String property, ColumnData config, int rowIndex, int colIndex,
                                 ListStore<OrdineModel> store, Grid<OrdineModel> grid) {
                if (model.getPagamentoModel() != null) {
                    return model.getPagamentoModel().getIMPORTO();
                } else {
                    return "-";
                }
            }
        });
        configs.add(column);

        column = new ColumnConfig("kgCO2", "kgCO2", 150);
        column.setAlignment((Style.HorizontalAlignment.RIGHT));
        column.setRenderer(new GridCellRenderer<OrdineModel>() {
            public String render(OrdineModel model, String property, ColumnData config, int rowIndex, int colIndex,
                                 ListStore<OrdineModel> store, Grid<OrdineModel> grid) {
                if (model.getPagamentoModel() != null && model.getPagamentoModel().getKgCO2() != null) {
                    return number.format(model.getPagamentoModel().getKgCO2());
                } else {
                    return "-";
                }
            }
        });
        column.setAlignment((Style.HorizontalAlignment.RIGHT));
        configs.add(column);

        column = new ColumnConfig("compensato", "Status", 150);
        column.setRenderer(new GridCellRenderer<OrdineModel>() {
            public String render(OrdineModel model, String property, ColumnData config, int rowIndex, int colIndex,
                                 ListStore<OrdineModel> store, Grid<OrdineModel> grid) {
                if (model.getPagamentoModel() != null && model.getPagamentoModel().getEsito() != null) {
                    return model.getPagamentoModel().getEsito().toString();
                } else {
                    return "NO";
                }
            }
        });
        configs.add(column);
        ColumnModel cm = new ColumnModel(configs);
        Grid<OrdineModel> grid = new Grid<OrdineModel>(store, cm);
        grid.setBorders(true);
        grid.setHeight(430);

        grid.getSelectionModel().setSelectionMode(Style.SelectionMode.SINGLE);
        grid.getSelectionModel().addListener(Events.SelectionChange,
                new Listener<SelectionChangedEvent<OrdineModel>>() {
                    public void handleEvent(SelectionChangedEvent<OrdineModel> be) {
                        if (be.getSelection().size() > 0) {
                            if (be.getSelectedItem().getPagamentoModel() != null &&
                                    be.getSelectedItem().getPagamentoModel().getEsito().equals(Esito.PAGATO.toString())) {
                                Dispatcher.forwardEvent(AmministrazioneEvents.ShowEventoCompensatoDialog, be.getSelectedItem());

                            } else {
                                Dispatcher.forwardEvent(EventoEvents.LoadEvento, be.getSelectedItem());

                                Dispatcher.forwardEvent(CentralEvents.ShowPanel, Eventi.EVENTO);
                                Dispatcher.forwardEvent(EventoEvents.ShowRiepilogo);

                                Info.display("Info", "Uploading Evento... " + be.getSelectedItem().getNome());
                            }
                        }
                    }
                });
        centre.add(grid);

        return centre;
    }

    public void setOrdiniInStore(List<OrdineModel> ordiniModels) {
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


