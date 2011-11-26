package it.agilis.mens.azzeroCO2.client.forms.evento;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.binding.FormBinding;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.util.Padding;
import com.extjs.gxt.ui.client.widget.BoxComponent;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.ToolButton;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.*;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.layout.*;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;
import it.agilis.mens.azzeroCO2.shared.model.RiepilogoModel;
import it.agilis.mens.azzeroCO2.shared.model.amministrazione.ProgettoDiCompensazioneModel;
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
public class EventoFormAcquisto extends LayoutContainer {
    private ContentPanel east = new ContentPanel();
    private ContentPanel centre = new ContentPanel();
    private ListStore<ProgettoDiCompensazioneModel> store = new ListStore<ProgettoDiCompensazioneModel>();
    private FormBinding binding = null;

    private final NumberFormat number = NumberFormat.getFormat("0.00");
    private double totaleKC02 = 0;

    private LabelField totaleKC02Label = new LabelField();
    private LabelField titoloEvento = new LabelField(" - ");
    private LabelField luogoEvento = new LabelField(" - ");
    private LabelField inizioEvento = new LabelField(" - ");
    private LabelField fineEvento = new LabelField(" - ");


    private LabelField kcO2Evento = new LabelField("Kg C02");
    private final LabelField titoloProgettoScelto = new LabelField("ProgettoScelto");
    private final LabelField euroPerKCo2Progetto = new LabelField(" 0.00");
    private final LabelField totale = new LabelField(" 0.00");
    private ProgettoDiCompensazioneModel selectedModel;
    private Grid<ProgettoDiCompensazioneModel> grid;
    private FormPanel form = createForm();

    private DateTimeFormat dateFormat = DateTimeFormat.getFormat("dd.MM.y");

    public EventoFormAcquisto() {
        grid = createGrid();
        binding = new FormBinding(form, true);
        binding.setStore(grid.getStore());
    }

    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);

        BorderLayout layout = new BorderLayout();
        setLayout(layout);
        layout.setEnableState(false);
        setStyleAttribute("padding", "0px");

        VerticalPanel vp = new VerticalPanel();
        vp.setHeight(622);
        vp.add(form);
        east.add(vp);

        grid.getSelectionModel().addListener(Events.SelectionChange,
                new Listener<SelectionChangedEvent<ProgettoDiCompensazioneModel>>() {
                    public void handleEvent(SelectionChangedEvent<ProgettoDiCompensazioneModel> be) {
                        if (be.getSelection().size() > 0) {
                            titoloProgettoScelto.setText(be.getSelection().get(0).getNome());
                            euroPerKCo2Progetto.setText(number.format(be.getSelection().get(0).getPrezzo()));
                            totale.setText(number.format((totaleKC02 * be.getSelection().get(0).getPrezzo())));
                            totaleKC02Label.setText(number.format(totaleKC02) + " kg/CO2?");
                            binding.bind(be.getSelection().get(0));
                            selectedModel = be.getSelection().get(0);
                        } else {
                            titoloProgettoScelto.setText("Titolo ProgettoScelto");
                            euroPerKCo2Progetto.setText("0.0");
                            totale.setText("0.0");
                            totaleKC02Label.setText("0.0 kg/CO2?");
                            binding.unbind();
                        }
                    }
                });
        east.setHeading("Acquisto");
        BorderLayoutData westData = new BorderLayoutData(Style.LayoutRegion.EAST, 300);
        east.getHeader().addTool(new ToolButton("x-tool-help"));
        east.getHeader().addTool(new ToolButton("x-tool-refresh"));
        westData.setMargins(new Margins(0));
        east.setAutoHeight(true);
        add(east, westData);

        centre.setLayout(new RowLayout(Style.Orientation.HORIZONTAL));
        centre.add(grid, new RowData(1, 1));
        centre.setHeading("Progetti Di Compensazione");
        centre.setHeight(440);

        BorderLayoutData centerData = new BorderLayoutData(Style.LayoutRegion.CENTER);
        centerData.setMargins(new Margins(0));
        add(centre, centerData);

    }

    private FormPanel createForm() {
        FormPanel panel = new FormPanel();
        panel.setFrame(true);
        panel.setHeaderVisible(false);
        panel.setSize(300, 500);  //300,-1 sd
        panel.setLabelAlign(FormPanel.LabelAlign.LEFT);

        {
            LayoutContainer c = new LayoutContainer();
            HBoxLayout layout = new HBoxLayout();
            layout.setPadding(new Padding(1));
            layout.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.BOTTOM);
            c.setLayout(layout);
            LabelField label = new LabelField("Evento: ");
            label.setStyleAttribute("font-size", "16px");
            c.add(label);

            panel.add(c);
        }
        {
            {
                LayoutContainer c = new LayoutContainer();
                HBoxLayout layout = new HBoxLayout();
                layout.setPadding(new Padding(2));
                layout.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.BOTTOM);
                c.setLayout(layout);
                titoloEvento.setWidth(220);
                c.add(titoloEvento);
                panel.add(c);
            }
            {
                LayoutContainer c = new LayoutContainer();
                HBoxLayout layout = new HBoxLayout();
                layout.setPadding(new Padding(2));
                layout.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.BOTTOM);
                c.setLayout(layout);
                luogoEvento.setWidth(220);
                c.add(luogoEvento);

                panel.add(c);
            }
            {
                LayoutContainer c = new LayoutContainer();
                c.setLayout(new FillLayout(Style.Orientation.HORIZONTAL));

                LabelField label = new LabelField("Kg/CO2");
                //   label.setWidth(220);
                c.setHeight(50);
                c.add(label, new FillData(2, 20, 2, 0));
                c.add(kcO2Evento, new FillData(2, 0, 2, 50));

                panel.add(c, new FormData("100%"));
            }
            {    // PROGETTO SCELTO
                LayoutContainer c = new LayoutContainer();
                HBoxLayout layout = new HBoxLayout();
                layout.setPadding(new Padding(1));
                layout.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.BOTTOM);
                c.setLayout(layout);

                LabelField label = new LabelField("Progetto:");
                label.setStyleAttribute("font-size", "16px");
                label.setWidth(220);
                c.add(label);

                panel.add(c, new FormData("100%"));
            }
            {
                LayoutContainer c = new LayoutContainer();
                HBoxLayout layout = new HBoxLayout();
                layout.setPadding(new Padding(2));
                layout.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.BOTTOM);
                c.setLayout(layout);

                titoloProgettoScelto.setWidth(220);
                c.add(titoloProgettoScelto);

                panel.add(c, new FormData("100%"));
            }
            {
                LayoutContainer c = new LayoutContainer();
                c.setLayout(new FillLayout(Style.Orientation.HORIZONTAL));
                c.setHeight(50);

                LabelField label = new LabelField("€ x Kg/CO2 ");

                c.add(label, new FillData(2, 20, 2, 0));
                c.add(euroPerKCo2Progetto, new FillData(2, 0, 2, 50));

                panel.add(c, new FormData("100%"));
            }
            { // TOTALE
                LayoutContainer c = new LayoutContainer();
                c.setLayout(new FillLayout(Style.Orientation.HORIZONTAL));
                c.setHeight(60);

                LabelField label = new LabelField("Totale € ");
                label.setStyleAttribute("color", "#FF9933");
                label.setStyleAttribute("font-size", "16px");
                c.add(label, new FillData(2, 20, 2, 0));

                totale.setStyleAttribute("color", "#FF9933");
                totale.setStyleAttribute("font-size", "16px");

                c.add(totale, new FillData(2, 0, 2, 50));

                panel.add(c, new FormData("100%"));
            }
            { // Coupon
                LayoutContainer c = new LayoutContainer();
                c.setLayout(new FillLayout(Style.Orientation.HORIZONTAL));
                c.setHeight(30);
                LabelField label = new LabelField("Hai Un Coupon? ");

                c.add(label, new FillData(2, 20, 2, 0));
                TextField<String> coupon = new TextField<String>();
                c.add(coupon, new FillData(0, 0, 0, 0));
                panel.add(c, new FormData("100%"));
            }


            { // Coupon
                LayoutContainer c = new LayoutContainer();
                c.setLayout(new FillLayout(Style.Orientation.HORIZONTAL));

                com.extjs.gxt.ui.client.widget.button.Button ricalcola;
                ricalcola = new com.extjs.gxt.ui.client.widget.button.Button("Calcola sconto");
                //TODO fare funzionare sto pulsantone di calcolo del coupon
                c.add(ricalcola);
                panel.add(c); //, new FormData("100%"));
            }


            /*{
               LayoutContainer c = new LayoutContainer();
               c.setHeight(190);
               c.setWidth(290);
               c.setStyleAttribute("background-color", "#FF9933");
               VBoxLayout layout = new VBoxLayout();
               layout.setPadding(new Padding(2));
               layout.setVBoxLayoutAlign(VBoxLayout.VBoxLayoutAlign.LEFT);
               c.setLayout(layout);

               totaleKC02Label.setStyleAttribute("font-size", "18px");
               totaleKC02Label.setStyleAttribute("color", "#2F3645");
               totaleKC02Label.setWidth("");
               c.add(totaleKC02Label, new VBoxLayoutData(new Margins(5, 0, 10, 0)));
               LabelField label = new LabelField("AzzeroCO2 puo' offrirti consulenza <br> per la riduzione delle emissioni <br>Chiamaci !!");
               label.setStyleAttribute("font-size", "16px");
               label.setStyleAttribute("color", "#2F3645");
               //label.setWidth(290);
               c.add(label, new VBoxLayoutData(new Margins(15, 0, 5, 0)));
               panel.add(c, new FormData("100%"));
           } */
        }
        return panel;
    }

    private Grid<ProgettoDiCompensazioneModel> createGrid() {

        GridCellRenderer<ProgettoDiCompensazioneModel> buttonRenderer = new GridCellRenderer<ProgettoDiCompensazioneModel>() {

            private boolean init;

            public Object render(final ProgettoDiCompensazioneModel model, String property, ColumnData config, final int rowIndex,
                                 final int colIndex, ListStore<ProgettoDiCompensazioneModel> store, Grid<ProgettoDiCompensazioneModel> grid) {
                if (!init) {
                    init = true;
                    grid.addListener(Events.ColumnResize, new Listener<GridEvent<ProgettoDiCompensazioneModel>>() {

                        public void handleEvent(GridEvent<ProgettoDiCompensazioneModel> be) {
                            for (int i = 0; i < be.getGrid().getStore().getCount(); i++) {
                                if (be.getGrid().getView().getWidget(i, be.getColIndex()) != null
                                        && be.getGrid().getView().getWidget(i, be.getColIndex()) instanceof BoxComponent) {
                                    ((BoxComponent) be.getGrid().getView().getWidget(i, be.getColIndex())).setWidth(be.getWidth() - 10);
                                }
                            }
                        }
                    });
                }
                final Image b = new Image();
                b.addClickListener(new ClickListener() {
                    public void onClick(Widget sender) {

                        // TODO: aprire finestra di info su progetto...
                        //   b.setVisibleRect(70, 0, 47, 110);
                    }
                });
                b.setWidth("" + (grid.getColumnModel().getColumnWidth(colIndex) - 10) + "px");

                if (model.getImageUrl() != null && !"".equalsIgnoreCase(model.getImageUrl())) {
                    String baseUrl = GWT.getHostPageBaseURL().replace(GWT.getModuleName(), "ImmaginiProgetti");
                    b.setUrl(baseUrl + model.getImageUrl());
                    b.setUrl(GWT.getHostPageBaseURL() + model.getImageUrl());
                }else{
                    b.setVisible(false);
				}
				
                if(model.getImageUrl()!=null && !"".equalsIgnoreCase(model.getImageUrl())) {
                     String baseUrl= GWT.getHostPageBaseURL().replace(GWT.getModuleName(), "azzeroCO2_IMG");
                    b.setUrl(baseUrl+ model.getImageUrl());
                    b.setUrl(GWT.getHostPageBaseURL() + model.getImageUrl());
                }
                return b;
            }


        };


        List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

        ColumnConfig column = new ColumnConfig("nome", "Progetto", 100);
        configs.add(column);

        column = new ColumnConfig("Immagine", "Progetto", 210);
        column.setRenderer(buttonRenderer);
        configs.add(column);


        column = new ColumnConfig("prezzo", "Euro", 60);
        column.setAlignment(Style.HorizontalAlignment.RIGHT);
        configs.add(column);

        ColumnModel cm = new ColumnModel(configs);

        Grid<ProgettoDiCompensazioneModel> grid = new Grid<ProgettoDiCompensazioneModel>(store, cm);
        grid.setBorders(true);
        grid.getSelectionModel().setSelectionMode(Style.SelectionMode.SINGLE);
        //grid.setHideHeaders(true);
        grid.setHeight(402);

        return grid;
    }

    public void clear() {
        for (ProgettoDiCompensazioneModel m : store.getModels()) {
            this.store.remove(m);
        }
    }

    public void setInStore(List<ProgettoDiCompensazioneModel> progettoDiCompensazioneModel) {
        clear();
        this.store.add(progettoDiCompensazioneModel);
        select(selectedModel);
    }

    public void setRiepilogo(List<RiepilogoModel> eventoRiepilogoModels, DettaglioModel riepilogo) {
        double totale = 0;
        for (RiepilogoModel r : eventoRiepilogoModels) {
            totale += r.getKgCO2();
        }
        this.totaleKC02 = totale;

        kcO2Evento.setText(number.format(totale));
        totaleKC02Label.setText(number.format(totale) + " kg/CO2?");
        titoloEvento.setText(riepilogo.getNome());
        if (riepilogo.getInizio() != null && riepilogo.getFine() != null) {
            luogoEvento.setText(riepilogo.getDove() + "<br>dal " + dateFormat.format(riepilogo.getInizio()) + " al " + dateFormat.format(riepilogo.getFine()));
        } else {
            luogoEvento.setText(riepilogo.getDove());
        }
        if (riepilogo.getInizio() != null) {
            inizioEvento.setText(dateFormat.format(riepilogo.getInizio()));
        }
        if (riepilogo.getFine() != null) {
            fineEvento.setText(dateFormat.format(riepilogo.getFine()));
        }
    }

    public ProgettoDiCompensazioneModel getProgettoDiCompensazioneModel() {
        return selectedModel;
    }

    public void setProgettoDiCompensazione(ProgettoDiCompensazioneModel model) {
        selectedModel = model;

        if (this.store.getModels().size() == 0) {
            grid.getSelectionModel().select(model, true);
            return;
        }

        select(model);
    }

    private void select(ProgettoDiCompensazioneModel model) {
        for (ProgettoDiCompensazioneModel m : this.store.getModels()) {
            if (m == null || model == null) {
                return;
            }
            if (m.getId() == model.getId()) {
                grid.getSelectionModel().select(m, true);
                break;
            }
        }
    }


}




