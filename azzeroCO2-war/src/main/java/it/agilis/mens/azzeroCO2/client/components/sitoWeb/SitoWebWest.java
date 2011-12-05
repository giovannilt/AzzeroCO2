package it.agilis.mens.azzeroCO2.client.components.sitoWeb;


import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
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
import it.agilis.mens.azzeroCO2.client.mvc.events.EventoEvents;
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
public class SitoWebWest extends LayoutContainer {
    private Grid<RiepilogoModel> grid;
    private ListStore<RiepilogoModel> store = new ListStore<RiepilogoModel>();
    private Text title = new Text("Sito web");
    private final String oggettoDiDefault="Compensa le emissioni </br> del tuo sito web'";
    private Esito esito;

    public SitoWebWest() {
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
        panel.setBodyStyle("backgroundColor: silver;");
        panel.setBorders(false);
        panel.add(createGrid(), new VBoxLayoutData(new Margins(0, 5, 2, 0)));

        title.setStyleAttribute("backgroundColor", "silver");
        title.setStyleAttribute("color", "black");
        title.setStyleAttribute("font-family", "arial");
        title.setStyleAttribute("text-align", "center");
        title.setStyleAttribute("vertical-align ", "middle");
        title.setWidth(250);
        title.setHeight(60);
        title.setStyleAttribute("font-size","14px");


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
                config.style += "background-color: silver;";
                List<RiepilogoModel> r = listStore.getModels();
                if(r.size()==1 && r.get(0).getOggetto().equalsIgnoreCase(oggettoDiDefault)){
                    return null;
                }

                return new Image(AzzeroCO2Resources.INSTANCE.checkIcon());//new ToolButton("x-tool-pin");
            }
        });

        configs.add(column);

        column = new ColumnConfig("oggetto", "oggetto", 220);
        column.setAlignment(Style.HorizontalAlignment.LEFT);
        column.setRenderer(new GridCellRenderer() {
            @Override
            public Object render(ModelData model, String property, ColumnData config, int rowIndex, int colIndex, ListStore listStore, Grid grid) {
                Text text = new Text((String) model.get(property));
                text.setStyleAttribute("backgroundColor", "silver");
                text.setStyleAttribute("color", "black");
                text.setStyleAttribute("font-family", "tahoma,arial,verdana,sans-serif");

                config.style += "background-color: silver;";

                return text;
            }
        });
        configs.add(column);


        ColumnModel cm = new ColumnModel(configs);

        grid = new Grid<RiepilogoModel>(store, cm);
        grid.setAutoHeight(true);
        grid.setHeight(400);
        grid.setWidth(244);
        //     grid.setStripeRows(true);
        grid.setHideHeaders(true);
        grid.setStyleAttribute("backgroundColor", "#E9E9E9");
        grid.disableTextSelection(true);
        grid.setTrackMouseOver(false);
        //   grid.setLoadMask(true);
        //grid.setBorders(false);


        grid.getSelectionModel().setSelectionMode(Style.SelectionMode.SINGLE);
        grid.getSelectionModel().addListener(Events.SelectionChange,
                new Listener<SelectionChangedEvent<RiepilogoModel>>() {
                    public void handleEvent(SelectionChangedEvent<RiepilogoModel> be) {
                        if (be.getSelection().size() > 0) {
                            if(!Esito.PAGATO.equals(esito)){
                                Dispatcher.forwardEvent(EventoEvents.ShowStep, be.getSelectedItem());  //TODO che roba è
                            }
                        }
                    }
                });

        grid.setBorders(false);

        return grid;
    }


    public void setInStore(List<RiepilogoModel> model, Esito esito) {
        store.removeAll();
        if (model == null || model.size() == 0) {
            RiepilogoModel m = new RiepilogoModel();
            m.setOggetto("Compensa le emissioni </br> del tuo sito web");
            store.add(m);
        } else {
             this.esito=esito;
            store.add(model);
        }
    }

    public void setTitle(String title) {
        if (title == null || "".equalsIgnoreCase(title)) {
            this.title.setText("Sito Web");
        } else {
            this.title.setText(title);
        }
    }

    public void clean(){
        setInStore(null, Esito.IN_PAGAMENTO);
        setTitle(null);
    }



}
