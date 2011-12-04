package it.agilis.mens.azzeroCO2.client.forms.evento;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.event.*;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.util.Padding;
import com.extjs.gxt.ui.client.widget.BoxComponent;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.ToolButton;
import com.extjs.gxt.ui.client.widget.grid.*;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.layout.*;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.Image;
import it.agilis.mens.azzeroCO2.client.AzzeroCO2Resources;
import it.agilis.mens.azzeroCO2.client.mvc.events.EventoEvents;
import it.agilis.mens.azzeroCO2.shared.model.RiepilogoModel;
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
public class EventoFormRiepilogo extends LayoutContainer {

    private ListStore<RiepilogoModel> store = new ListStore<RiepilogoModel>();
    private Text totaleText = new Text("Totale KG/CO2");
    private Text totale = new Text("0.00");
    private Esito esito = Esito.IN_PAGAMENTO;
    private ContentPanel cp = new ContentPanel();

    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);

        BorderLayout layout_C = new BorderLayout();
        setLayout(layout_C);


        cp.setFrame(true);
        cp.setHeaderVisible(false);
        cp.setLayout(new RowLayout(Style.Orientation.HORIZONTAL));

        ContentPanel cpEst = new ContentPanel();
        cpEst.setFrame(false);
        cpEst.setHeaderVisible(false);
        cpEst.setLayout(new RowLayout(Style.Orientation.VERTICAL));
        cpEst.add(createGrid(), new RowData(1, 0.95));

        cp.add(cpEst, new RowData(1, 1));

        LayoutContainer c = new LayoutContainer();
        c.setHeight(30);
        HBoxLayout layout = new HBoxLayout();
        layout.setPadding(new Padding(1));
        layout.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.MIDDLE);
        c.setLayout(layout);
        HBoxLayoutData flex = new HBoxLayoutData(new Margins(0, 0, 0, 5));
        flex.setFlex(1);
        c.add(totaleText, flex);

        totaleText.setSize(300, 15);
        totaleText.setStyleAttribute("font-family", "tahoma,arial,verdana,sans-serif");
        totaleText.setStyleAttribute("font-size", "14px");
        totaleText.setStyleAttribute("color", "#D38131");
        totale.setSize(250, 15);
        totale.setStyleAttribute("text-align", "right");
        totale.setStyleAttribute("font-family", "tahoma,arial,verdana,sans-serif");
        totale.setStyleAttribute("font-size", "14px");
        totale.setStyleAttribute("color", "#D38131");
        c.add(totale, new HBoxLayoutData(new Margins(0, 20, 0, 0)));

        cpEst.add(c, new RowData(1, 0.05));

        // TODO MIGLIORARE
        cp.setHeight(443);

        BorderLayoutData centerData = new BorderLayoutData(Style.LayoutRegion.CENTER);
        add(cp, centerData);

    }

    protected void onAfterLayout() {
        super.onAfterLayout();
        cp.getBody().setStyleAttribute("border-bottom", "3px solid orange");
        cp.getBody().setStyleAttribute("border-style", "solid");
        //cp.getBody().setStyleAttribute("border-top", "3px solid orange");
        cp.getBody().setStyleAttribute("border-width", "3px 0");
        cp.getBody().setStyleAttribute("margin-bottom", "0");


        //To change body of overridden methods use File | Settings | File Templates.
    }


    private Grid<RiepilogoModel> createGrid() {

        final NumberFormat number = NumberFormat.getFormat("0.00");
        List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

        ColumnConfig column = new ColumnConfig("index", "", 24);
        column.setRenderer(new GridCellRenderer<RiepilogoModel>() {
            @Override
            public Object render(RiepilogoModel model, String property, ColumnData config, int rowIndex, int colIndex, ListStore<RiepilogoModel> riepilogoModelListStore, Grid<RiepilogoModel> riepilogoModelGrid) {
                return new Image(AzzeroCO2Resources.INSTANCE.checkIcon());
            }
        });
        column.setSortable(true);
        configs.add(column);
        column = new ColumnConfig("oggetto", "Oggetto", 230);
        configs.add(column);

        column = new ColumnConfig("dettagli", "Dettagli", 236);
        configs.add(column);

        column = new ColumnConfig("kgCO2", "Kg/CO2", 82);
        column.setAlignment(Style.HorizontalAlignment.RIGHT);
        column.setRenderer(new GridCellRenderer<RiepilogoModel>() {
            @Override
            public Object render(RiepilogoModel model, String property, ColumnData config, int rowIndex, int colIndex, ListStore<RiepilogoModel> riepilogoModelListStore, Grid<RiepilogoModel> riepilogoModelGrid) {
                return number.format(model.<Number>get(property));
            }
        });
        configs.add(column);

        column = new ColumnConfig();
        column.setRowHeader(false);
        column.setId("Cancella");
        column.setRenderer(new GridCellRenderer<RiepilogoModel>() {
            private boolean init;

            public Object render(final RiepilogoModel model, String property, ColumnData config, final int rowIndex,
                                 final int colIndex, final ListStore<RiepilogoModel> store, Grid<RiepilogoModel> grid) {
                if (!init) {
                    init = true;
                    grid.addListener(Events.ColumnResize, new Listener<GridEvent<RiepilogoModel>>() {
                        public void handleEvent(GridEvent<RiepilogoModel> be) {
                            for (int i = 0; i < be.getGrid().getStore().getCount(); i++) {
                                if (be.getGrid().getView().getWidget(i, be.getColIndex()) != null
                                        && be.getGrid().getView().getWidget(i, be.getColIndex()) instanceof BoxComponent) {
                                    ((BoxComponent) be.getGrid().getView().getWidget(i, be.getColIndex())).setWidth(be.getWidth() - 10);
                                }
                            }
                        }
                    });
                }
                ToolButton b = new ToolButton("x-tool-close", new SelectionListener<IconButtonEvent>() {
                    @Override
                    public void componentSelected(IconButtonEvent ce) {
                        if (!Esito.PAGATO.equals(esito)) {
                            store.remove(model);
                            Dispatcher.forwardEvent(EventoEvents.ClearStep, model);
                            setTotale();
                        }
                    }
                });
                b.setToolTip("Elimina");

                LayoutContainer cp = new LayoutContainer();
                cp.setSize(36, 36);
                cp.setLayout(new RowLayout(Style.Orientation.HORIZONTAL));
                cp.add(b, new RowData(-1, 0.5));

                Button edit = new Button("", new SelectionListener<ButtonEvent>() {
                     @Override
                    public void componentSelected(ButtonEvent buttonEvent) {
                        Dispatcher.forwardEvent(EventoEvents.ShowStep, model);
                    }
                });
                edit.setIcon(AbstractImagePrototype.create(AzzeroCO2Resources.INSTANCE.modifica()));
                edit.setSize(20,20);
                //edit.setIconAlign(Style.IconAlign.TOP);
                cp.add(edit, new RowData(-1, 0.5));

                return cp;
            }
        });
        column.setWidth(85);
        configs.add(column);

        ColumnModel cm = new ColumnModel(configs);
        Grid<RiepilogoModel> grid = new Grid<RiepilogoModel>(store, cm);
        grid.setBorders(true);
        grid.setHeight(350);

        return grid;
    }

    public void setEventoRiepilogoInStore(List<RiepilogoModel> models, Esito esito) {
        for (RiepilogoModel r : store.getModels()) {
            store.remove(r);
        }
        this.store.add(models);

        setTotale();
        this.esito = esito;
    }

    private void setTotale() {
        double t = 0;
        for (RiepilogoModel r : store.getModels()) {
            t += r.getKgCO2();
        }
        final NumberFormat number = NumberFormat.getFormat("0.00");
        totale.setText(number.format(t));
    }


    public void clear() {
        store.removeAll();
    }


}


