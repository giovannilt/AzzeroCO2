package it.agilis.mens.azzeroCO2.client.forms;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.binding.FormBinding;
import com.extjs.gxt.ui.client.event.*;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.util.Padding;
import com.extjs.gxt.ui.client.widget.*;
import com.extjs.gxt.ui.client.widget.button.Button;
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
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;
import it.agilis.mens.azzeroCO2.client.mvc.events.*;
import it.agilis.mens.azzeroCO2.shared.Eventi;
import it.agilis.mens.azzeroCO2.shared.model.CouponType;
import it.agilis.mens.azzeroCO2.shared.model.OrdineModel;
import it.agilis.mens.azzeroCO2.shared.model.RiepilogoModel;
import it.agilis.mens.azzeroCO2.shared.model.amministrazione.CouponModel;
import it.agilis.mens.azzeroCO2.shared.model.amministrazione.ProgettoDiCompensazioneModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 6/19/11
 * Time: 5:19 PM
 * To change this template use File | Settings | File Templates.
 */

public class FormAcquisto extends LayoutContainer {
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
    private final LabelField titoloProgettoScelto = new LabelField("Progetto scelto");
    private final LabelField euroPerKCo2Progetto = new LabelField(" 0.00");
    private final LabelField totale = new LabelField(" 0.00");
    private ProgettoDiCompensazioneModel selectedModel;
    private Grid<ProgettoDiCompensazioneModel> grid;
    private FormPanel form = createForm();

    private DateTimeFormat dateFormat = DateTimeFormat.getFormat("dd.MM.y");
    private OrdineModel riepilogo;
    private List<RiepilogoModel> eventoRiepilogoModels;
    private CouponModel coupon;

    public FormAcquisto() {
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
        //vp.setHeight(541); //DIM   forse non serve
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
        //east.getHeader().addTool(new ToolButton("x-tool-help"));
        //east.getHeader().addTool(new ToolButton("x-tool-refresh"));
        westData.setMargins(new Margins(0));
        //east.setAutoHeight(true)
        east.setHeight(570);//DIM
        add(east, westData);

        centre.setLayout(new RowLayout(Style.Orientation.HORIZONTAL));
        centre.add(grid, new RowData(1, 1));
        centre.setHeading("Progetti di compensazione");
        centre.setHeight(546); //DIM
        //east.setHeight(23);
        centre.setStyleAttribute("background-color", "#555557");
        east.setStyleAttribute("background-color", "#555557");
        setStyleAttribute("background-color", "#555557");

        BorderLayoutData centerData = new BorderLayoutData(Style.LayoutRegion.CENTER);
        centerData.setMargins(new Margins(0));
        add(centre, centerData);

    }

    private FormPanel createForm() {
        FormPanel panel = new FormPanel();
        panel.setFrame(true);
        panel.setHeaderVisible(false);
        panel.setSize(290, 516);  //300,-1 sd
        panel.setStyleAttribute("border-bottom","solid");
        panel.setStyleAttribute("border-bottom-color","#f8b333");
        panel.setLabelAlign(FormPanel.LabelAlign.LEFT);
        final TextField<String> coupon = new TextField<String>();

        {
            LayoutContainer c = new LayoutContainer();
            HBoxLayout layout = new HBoxLayout();
            layout.setPadding(new Padding(1));
            layout.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.BOTTOM);
            c.setLayout(layout);
            LabelField label = new LabelField("Calcolo: ");
            label.setStyleAttribute("font-size", "16px");

            c.add(label);
            c.setStyleAttribute("padding-top","20px");
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
                titoloEvento.setStyleAttribute("font-weight", "bold");
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
                c.setStyleAttribute("padding-top","10px");

                panel.add(c);
            }
            {
                LayoutContainer c = new LayoutContainer();
                c.setLayout(new FillLayout(Style.Orientation.HORIZONTAL));

                LabelField label = new LabelField("Kg/CO2");
                //   label.setWidth(220);
                c.setHeight(50);
                c.add(label, new FillData(2, 20, 2, 0));
                kcO2Evento.setStyleAttribute("text-align", "right");

                c.add(kcO2Evento, new FillData(2, 0, 2, 40));
                c.setStyleAttribute("padding-top","20px");
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
                c.setStyleAttribute("padding-top","20px");
                panel.add(c, new FormData("100%"));
            }
            {
                LayoutContainer c = new LayoutContainer();
                HBoxLayout layout = new HBoxLayout();
                layout.setPadding(new Padding(2));
                layout.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.BOTTOM);
                c.setLayout(layout);

                titoloProgettoScelto.setWidth(220);
                titoloProgettoScelto.setStyleAttribute("padding-bottom", "30px");
                c.add(titoloProgettoScelto);
                panel.add(c, new FormData("100%"));
            }
            {
                LayoutContainer c = new LayoutContainer();
                c.setLayout(new FillLayout(Style.Orientation.HORIZONTAL));
                c.setHeight(50);

                LabelField label = new LabelField("€ x Kg/CO2 ");

                c.add(label, new FillData(2, 20, 2, 0));
                euroPerKCo2Progetto.setStyleAttribute("text-align", "right");

                c.add(euroPerKCo2Progetto, new FillData(2, 0, 2, 40));
                c.setStyleAttribute("padding-top","20px");
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
                totale.setStyleAttribute("text-align", "right");
                c.add(totale, new FillData(2, 0, 2, 20));
                c.setStyleAttribute("padding-top","20px");
                panel.add(c, new FormData("100%"));
            }
            { // Coupon
                LayoutContainer c = new LayoutContainer();
                c.setLayout(new FillLayout(Style.Orientation.HORIZONTAL));
                c.setHeight(60);
                LabelField label = new LabelField("Se hai un coupon inseriscilo ");

                c.add(label, new FillData(2, 20, 2, 0));

                c.add(coupon, new FillData(0, 0, 0, 0));
                c.setStyleAttribute("padding-top","20px");
                panel.add(c, new FormData("100%"));
            }


            { // Coupon
                LayoutContainer c = new LayoutContainer();
                c.setLayout(new FillLayout(Style.Orientation.HORIZONTAL));

                Button ricalcola = new Button("Calcola");
                ricalcola.addSelectionListener(new SelectionListener<ButtonEvent>() {
                    @Override
                    public void componentSelected(ButtonEvent ce) {
                        if (Eventi.EVENTO == Eventi.valueOf(riepilogo.getEventiType())) {
                            Dispatcher.forwardEvent(EventoEvents.UseCoupon, coupon.getValue());
                        } else if (Eventi.CONOSCI_CO2 == Eventi.valueOf(riepilogo.getEventiType())) {
                            Dispatcher.forwardEvent(ConoscoCO2Events.UseCoupon, coupon.getValue());
                        } else if (Eventi.ANNO_DI_ATTIVITA == Eventi.valueOf(riepilogo.getEventiType())) {
                            Dispatcher.forwardEvent(UnAnnoDiAttivitaEvents.UseCoupon, coupon.getValue());
                        } else if (Eventi.UNA_PUBBLICAZIONE == Eventi.valueOf(riepilogo.getEventiType())) {
                            Dispatcher.forwardEvent(PubblicazioniEvents.UseCoupon, coupon.getValue());
                        } else if (Eventi.WEB == Eventi.valueOf(riepilogo.getEventiType())) {
                            Dispatcher.forwardEvent(SitoWebEvents.UseCoupon, coupon.getValue());
                        }

                    }
                });
                ricalcola.setStyleAttribute("padding-top", "5px");
                c.add(ricalcola);

                panel.add(c, new FillData(0, 0, 0, 135)); //, new FormData("100%"));
            }
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
                    String baseUrl = GWT.getHostPageBaseURL().replace("azzeroCO2", "ImmaginiProgetti");
                    b.setUrl(baseUrl + model.getImageUrl());
                } else {
                    b.setVisible(false);
                }
                config.style = "border-bottom:1px solid gray !important;";
                config.style = "width:100px";

                return b;
            }
        };
        List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

        ColumnConfig column = new ColumnConfig("Immagine", "Progetto", 100);
        column.setRenderer(buttonRenderer);
        configs.add(column);

        column = new ColumnConfig("nome", "Descrizione", 280);
        column.setRenderer(new GridCellRenderer<ProgettoDiCompensazioneModel>() {
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
                String descr = model.getDescrizione() != null || model.getDescrizione().length() > 0 ? model.getDescrizione() : "";
                grid.setStyleAttribute("white-space", "normal");
                config.style = "border-bottom:1px solid gray !important;";
                return new HTML("<table><tbody><tr><td style='white-space:pre-wrap'>" + model.getNome() + "</td></tr>" +
                        "<tr><td style='font-size:11px;white-space:pre-wrap;'>" + descr + "</td></tr></tbody></table>");
            }
        });
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

    public void setRiepilogo(List<RiepilogoModel> eventoRiepilogoModels, OrdineModel riepilogo) {
        if (riepilogo != null)
            this.riepilogo = riepilogo;
        double totale = 0;
        if (eventoRiepilogoModels != null) {
            this.eventoRiepilogoModels = eventoRiepilogoModels;
            for (RiepilogoModel r : eventoRiepilogoModels) {
                totale += r.getKgCO2();
            }
            if (coupon != null && !"".equalsIgnoreCase(coupon.getTipo())) {
                try {
                    // CALCOLARE IN BASE AL TIPO DI COUPON
                    if (riepilogo.getProgettoDiCompensazioneModel() != null) {
                        Double d = riepilogo.getProgettoDiCompensazioneModel().getPrezzo();

                        if (coupon.getTipo().equalsIgnoreCase(CouponType.EURO.toString())) {
                            Double val = (d * totale) - coupon.getValore();
                            if (val < 0) {
                                val = 0.0;
                            }
                            this.totale.setText(number.format(val));
                        } else if (coupon.getTipo().equalsIgnoreCase(CouponType.PERCENTO.toString())) {
                            this.totale.setText(number.format(coupon.getValore() * d * totale / 100));
                        } else if (coupon.getTipo().equalsIgnoreCase(CouponType.OMAGGIO.toString())) {
                            this.totale.setText("0,0");
                        }
                    }
                } catch (Exception e) {
                    Info.display("ERROR", e.getMessage());
                }
            }
            this.totaleKC02 = totale;

        }

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

    @Override
    protected void onAfterLayout() {
        super.onAfterLayout();
        centre.getBody().setStyleAttribute("border-bottom", "3px solid #f8b333");
        centre.getBody().setStyleAttribute("border-style", "solid");
        centre.getBody().setStyleAttribute("border-top", "3px solid #f8b333");
        centre.getBody().setStyleAttribute("border-width", "3px 0");
        centre.getBody().setStyleAttribute("margin", "0");

        east.getBody().setStyleAttribute("border-bottom", "3px solid #f8b333");
        east.getBody().setStyleAttribute("border-style", "solid");
        east.getBody().setStyleAttribute("border-top", "3px solid #f8b333");
        east.getBody().setStyleAttribute("border-width", "3px 0");
        east.getBody().setStyleAttribute("margin-bottom", "0");
    }

    public void setCouponModel(CouponModel coupon) {

        if (coupon != null && riepilogo != null && eventoRiepilogoModels != null) {
            this.coupon = coupon;
            setRiepilogo(eventoRiepilogoModels, riepilogo);
        }
    }

    public CouponModel getCouponModel() {
        return coupon;
    }
}




