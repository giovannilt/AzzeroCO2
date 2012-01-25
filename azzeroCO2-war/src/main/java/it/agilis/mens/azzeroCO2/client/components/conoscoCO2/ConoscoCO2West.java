package it.agilis.mens.azzeroCO2.client.components.conoscoCO2;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.util.Padding;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.grid.*;
import com.extjs.gxt.ui.client.widget.layout.HBoxLayoutData;
import com.extjs.gxt.ui.client.widget.layout.VBoxLayout;
import com.extjs.gxt.ui.client.widget.layout.VBoxLayoutData;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Image;
import it.agilis.mens.azzeroCO2.client.AzzeroCO2Resources;
import it.agilis.mens.azzeroCO2.client.services.CalcoliHelper;
import it.agilis.mens.azzeroCO2.shared.model.OrdineModel;
import it.agilis.mens.azzeroCO2.shared.model.RiepilogoModel;
import it.agilis.mens.azzeroCO2.shared.model.pagamento.Esito;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 6/19/11
 * Time: 12:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class ConoscoCO2West extends LayoutContainer {
    private Grid<RiepilogoModel> grid;
    private ListStore<RiepilogoModel> store = new ListStore<RiepilogoModel>();
    private Text title = new Text("Conosco la CO2");
    private final String oggettoDiDefault = "Compensa le tue emissioni";
    private final String riepilogoString = "Hai terminato il calcolo! <br>" +
            "Se vuoi modifica i dati inseriti<br>" +
            " cliccando sulla voce relativa.";
    private final String progettoDiCompensazione = "Scegli un progetto di <br>" +
            "compensazione.<br>" +
            "Controlla il preventivo e <br>" +
            "accedi al sistema di <br>" +
            "pagamento.";
    private final String Conferma = "Il Percorso e' finito!";

    private Esito esito;

    public ConoscoCO2West() {
        RiepilogoModel model = new RiepilogoModel();
        model.setOggetto(oggettoDiDefault);
        store.add(model);
    }

    @Override
    protected void onRender(Element target, int index) {
        super.onRender(target, index);

        VBoxLayout layout = new VBoxLayout();
        layout.setPadding(new Padding(5));
        layout.setVBoxLayoutAlign(VBoxLayout.VBoxLayoutAlign.CENTER);
        setLayout(layout);

        HBoxLayoutData flex = new HBoxLayoutData(new Margins(0, 0, 0, 0));
        flex.setFlex(1);

        add(title, new VBoxLayoutData(new Margins(15, 5, 5, 0)));

        ContentPanel panel = new ContentPanel();
        panel.setHeaderVisible(false);
        panel.setBodyStyle("backgroundColor: #d9dadb;");
        panel.setBorders(false);
        panel.add(createGrid(), new VBoxLayoutData(new Margins(0, 5, 2, 0)));

        title.setStyleAttribute("backgroundColor", "#d9dadb");
        title.setStyleAttribute("color", "black");
        title.setStyleAttribute("font-family", "arial");
        title.setStyleAttribute("text-align", "center");
        title.setStyleAttribute("vertical-align ", "middle");
        title.setWidth(250);
        title.setHeight(60);
        title.setStyleAttribute("font-size", "14px");


        panel.setStyleAttribute("backgroundColor", "#E9E9E9");
        add(panel, flex);

    }

    private Grid<RiepilogoModel> createGrid() {
        List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
        ColumnConfig column = new ColumnConfig("img", "img", 24);
        //column.setWidth(30);


        column.setAlignment(Style.HorizontalAlignment.LEFT);
        column.setRenderer(new GridCellRenderer() {
            @Override
            public Object render(ModelData model, String property, ColumnData config, int rowIndex, int colIndex, ListStore listStore, Grid grid) {
                config.style += "background-color: #d9dadb;";
                List<RiepilogoModel> r = listStore.getModels();
                if (r.size() == 1 && r.get(0).getOggetto().equalsIgnoreCase(oggettoDiDefault)) {
                    return null;
                }

                return new Image(AzzeroCO2Resources.INSTANCE.checkIcon());//new ToolButton("x-tool-pin");
            }
        });

        configs.add(column);

        column = new ColumnConfig("oggetto", "oggetto", 218);
        column.setAlignment(Style.HorizontalAlignment.LEFT);
        column.setRenderer(new GridCellRenderer() {
            @Override
            public Object render(ModelData model, String property, ColumnData config, int rowIndex, int colIndex, ListStore listStore, Grid grid) {
                Text text = new Text((String) model.get(property));
                text.setStyleAttribute("backgroundColor", "#d9dadb");
                text.setStyleAttribute("color", "black");
                text.setStyleAttribute("font-family", "arial");

                config.style += "background-color: #d9dadb;";

                return text;
            }
        });
        configs.add(column);


        ColumnModel cm = new ColumnModel(configs);

        grid = new Grid<RiepilogoModel>(store, cm);
        grid.setAutoHeight(true);
        grid.setHeight(400);
        grid.setWidth(245);
        //     grid.setStripeRows(true);
        grid.setHideHeaders(true);
        grid.setStyleAttribute("backgroundColor", "#E9E9E9");
        grid.disableTextSelection(true);
        grid.setTrackMouseOver(false);
        grid.setColumnResize(false);
        //   grid.setLoadMask(true);
        //grid.setBorders(false);


        /* grid.getSelectionModel().setSelectionMode(Style.SelectionMode.SINGLE);
        *//*grid.getSelectionModel().addListener(Events.SelectionChange,
                new Listener<SelectionChangedEvent<RiepilogoModel>>() {
                    public void handleEvent(SelectionChangedEvent<RiepilogoModel> be) {
                        if (be.getSelection().size() > 0) {
                            if (!Esito.PAGATO.equals(esito)) {
                                Dispatcher.forwardEvent(ConoscoCO2Events.ShowStep, be.getSelectedItem());
                            }
                        }
                    }
                });*/

        grid.setBorders(false);

        return grid;
    }


    public void setInStore(OrdineModel riepilogo, Esito esito) {
        store.removeAll();
        List<RiepilogoModel> model = CalcoliHelper.getListOfRiepilogoModelLazy(riepilogo);
        if (model == null || model.size() == 0) {
            RiepilogoModel m = new RiepilogoModel();
            m.setOggetto("Non hai ancora inserito <br> nessuna attività");
            store.add(m);
        } else {
            this.esito = esito;
            store.add(model);
        }
        setTitle(riepilogo);
    }

    public void setTitle(OrdineModel riepilogo) {
        String title = riepilogo.getNome() != null ? riepilogo.getNome() : "Compensa la CO2";

        if (title == null || "".equalsIgnoreCase(title)) {
            this.title.setText("Compensa la CO2");
        } else {
            this.title.setText(title);
        }
    }


    public void clean() {
        setInStore(null, Esito.IN_PAGAMENTO);
        this.title.setTitle(".....");
    }

    public void isInRiepilogo(OrdineModel riepilogo) {
        setTitle(riepilogo);
        store.removeAll();
        RiepilogoModel m = new RiepilogoModel();

        m.setOggetto(riepilogoString);
        store.add(m);
    }

    public void isScegliProgettoCompensazione(OrdineModel riepilogo) {
        setTitle(riepilogo);
        store.removeAll();
        RiepilogoModel m = new RiepilogoModel();

        m.setOggetto(progettoDiCompensazione);
        store.add(m);
    }

    public void isInConferma(OrdineModel riepilogo) {
        setTitle(riepilogo);
        store.removeAll();
        RiepilogoModel m = new RiepilogoModel();

        m.setOggetto(Conferma);
        store.add(m);
    }

}
