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
import com.extjs.gxt.ui.client.widget.button.ToolButton;
import com.extjs.gxt.ui.client.widget.form.*;
import com.extjs.gxt.ui.client.widget.grid.*;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.layout.*;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.user.client.Element;
import it.agilis.mens.azzeroCO2.client.mvc.events.AzzeroCO2Events;
import it.agilis.mens.azzeroCO2.shared.model.evento.PubblicazioniRilegateModel;
import it.agilis.mens.azzeroCO2.shared.model.evento.TipoDiCartaModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 7/2/11
 * Time: 12:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class FormPubblicazioniRilegate extends LayoutContainer {
    private ListStore<PubblicazioniRilegateModel> pubblicazioniRilegateModel = new ListStore<PubblicazioniRilegateModel>();
    private ToolBar toolBar = new ToolBar();
    private ListStore<TipoDiCartaModel> tipoDiCartaModelListStore = new ListStore<TipoDiCartaModel>();
    private final FormPanel panel = createGroupForm();
    private final FormBinding formBindings = new FormBinding(panel, true);
    private final Grid<PubblicazioniRilegateModel> grid = createGrid();
    private ContentPanel cpEst = new ContentPanel();

    public FormPubblicazioniRilegate() {
        //this.pubblicazioniRilegateModel.setMonitorChanges(true);
        setDefault();
    }


    @SuppressWarnings("rawtypes")
    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);

        BorderLayout layout = new BorderLayout();
        setLayout(layout);
        layout.setEnableState(false);

        ContentPanel cp = new ContentPanel();
        cp.setFrame(true);
        cp.setHeaderVisible(false);
        cp.setLayout(new RowLayout(Style.Orientation.HORIZONTAL));

        ContentPanel textContent = new ContentPanel();
        textContent.setHeaderVisible(false);
        textContent.setLayout(new RowLayout(Style.Orientation.VERTICAL));
        textContent.add(textContent, new RowData(1, 1, new Margins(2, 2, 2, 2)));
        Text testo = new Text(" Si tratta di gruppi di pagine riunite <br> in un volume. Seleziona un tipo di <br>pubblicazione.<br><br>");
        testo.setStyleAttribute("font-size", "9pt");

        Text note = new Text(" Puoi inserire piu' di una pubblicazione ed aggiungere altre categorie. ");
        note.setStyleAttribute("font-size", "9pt");
        note.setStyleAttribute("font-style", "italic");

        textContent.add(testo);
        textContent.add(note);

        cpEst.setFrame(false);
        cpEst.setHeading("Pubblicazioni Rilegate");
        cpEst.setLayout(new RowLayout(Style.Orientation.VERTICAL));
        cpEst.add(textContent, new RowData(1, .28, new Margins(0, 0, 0, 0)));
        cpEst.add(grid, new RowData(1, .75, new Margins(0, 0, 0, 0)));
        cpEst.setBottomComponent(toolBar);
        cpEst.setButtonAlign(Style.HorizontalAlignment.CENTER);

        cp.add(cpEst, new RowData(.35, .98));
        cp.add(panel, new RowData(.65, 1));
        panel.setHeading(pubblicazioniRilegateModel.getModels().get(0).getCategoria());


        ToolButton tool1 = new ToolButton("x-tool-help");
        panel.getHeader().addTool(tool1);
        tool1.addSelectionListener(new SelectionListener<IconButtonEvent>() {
            @Override
            public void componentSelected(IconButtonEvent ce) {
                Dispatcher.forwardEvent(AzzeroCO2Events.ShowInfoDialog, "Si fa riferimento alle emissioni relative al ciclo di produzione di un grammo di carta.");
            }
        });
        ToolButton tool = new ToolButton("x-tool-refresh");
        panel.getHeader().addTool(tool);
        tool.addSelectionListener(new SelectionListener<IconButtonEvent>() {
            @Override
            public void componentSelected(IconButtonEvent ce) {
                clear(true);
            }
        });

        formBindings.setStore(grid.getStore());
        grid.getSelectionModel().setSelectionMode(Style.SelectionMode.SINGLE);
        grid.getSelectionModel().addListener(Events.SelectionChange,
                new Listener<SelectionChangedEvent<PubblicazioniRilegateModel>>() {
                    public void handleEvent(SelectionChangedEvent<PubblicazioniRilegateModel> be) {
                        if (be.getSelection().size() > 0) {
                            formBindings.bind(be.getSelection().get(0));
                            panel.setHeading(be.getSelection().get(0).getCategoria());
                        } else {
                            formBindings.unbind();
                        }
                    }
                });
        BorderLayoutData centerData = new BorderLayoutData(Style.LayoutRegion.CENTER);
        add(cp, centerData);
        grid.getSelectionModel().select(0, true);
    }

    @Override
    protected void onLoad() {
        super.onLoad();
        toolBar.setStyleAttribute("border-bottom", "3px solid orange");
        cpEst.getBody().setStyleAttribute("border-style", "solid");
        cpEst.getBody().setStyleAttribute("border-top", "3px solid orange");
        cpEst.getBody().setStyleAttribute("border-width", "3px 0");
        cpEst.getBody().setStyleAttribute("margin-bottom", "0");
        panel.getBody().setStyleAttribute("border-bottom", "3px solid orange");
        panel.getBody().setStyleAttribute("border-style", "solid");
        panel.getBody().setStyleAttribute("border-top", "3px solid orange");
        panel.getBody().setStyleAttribute("border-width", "3px 0");
        panel.getBody().setStyleAttribute("margin-bottom", "0");


        //To change body of overridden methods use File | Settings | File Templates.
    }

    private FormPanel createGroupForm() {
        FormPanel panel = new FormPanel();
        panel.setFrame(true);

        panel.setLabelAlign(FormPanel.LabelAlign.LEFT);
        HBoxLayoutData flex = new HBoxLayoutData(new Margins(0, 5, 0, 0));

        {
            LayoutContainer c2 = new LayoutContainer();
            HBoxLayout layout2 = new HBoxLayout();
            layout2.setPadding(new Padding(5));
            layout2.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.BOTTOM);
            c2.setLayout(layout2);

            LabelField istruzioni = new LabelField("Inserisci le caratteristiche.");
            istruzioni.setStyleAttribute("font-weight", "bolder");

            c2.add(istruzioni, flex);

            panel.add(c2);
        }
        { // Dimensioni
            {
                LayoutContainer c = new LayoutContainer();
                HBoxLayout layout = new HBoxLayout();
                layout.setPadding(new Padding(5));
                layout.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.BOTTOM);
                c.setLayout(layout);

                NumberField altezza = new NumberField();
                altezza.setWidth(60);
                altezza.setName("altezza");

                LabelField label = new LabelField("Dimensioni ");
                label.setWidth(100);
                c.add(label);
                c.add(altezza, flex);
                c.add(new LabelField("altezza (cm)"), flex);

                panel.add(c, new FormData("100%"));
            }
            {
                LayoutContainer c = new LayoutContainer();
                HBoxLayout layout = new HBoxLayout();
                layout.setPadding(new Padding(5));
                layout.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.BOTTOM);
                c.setLayout(layout);

                NumberField larghezza = new NumberField();
                larghezza.setWidth(60);
                larghezza.setName("larghezza");

                LabelField label = new LabelField("");
                label.setWidth(100);
                c.add(label);
                c.add(larghezza, flex);
                c.add(new LabelField("larghezza (cm)"), flex);

                panel.add(c, new FormData("100%"));
            }
        }
        {   // Materiale
            {
                LayoutContainer c = new LayoutContainer();
                HBoxLayout layout = new HBoxLayout();
                layout.setPadding(new Padding(5));
                layout.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.BOTTOM);
                c.setLayout(layout);

                ComboBox<TipoDiCartaModel> tipoDiCarta = new ComboBox<TipoDiCartaModel>();
                tipoDiCarta.setEmptyText("Seleziona tipo di carta");
                //  tipoDiCarta.setToolTip("TipoDiCarta");
                tipoDiCarta.setWidth(200);
                tipoDiCarta.setDisplayField("nome");
                tipoDiCarta.setTriggerAction(ComboBox.TriggerAction.ALL);
                tipoDiCarta.setName("tipoDiCarta");
                tipoDiCarta.setStore(tipoDiCartaModelListStore);

                LabelField label = new LabelField("Materiale ");
                label.setWidth(100);
                c.add(label);
                c.add(tipoDiCarta, flex);

                panel.add(c, new FormData("100%"));
            }
            {
                LayoutContainer c = new LayoutContainer();
                HBoxLayout layout = new HBoxLayout();
                layout.setPadding(new Padding(5));
                layout.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.BOTTOM);
                c.setLayout(layout);

                NumberField grammatura = new NumberField();
                grammatura.setWidth(60);
                grammatura.setName("grammatura");

                LabelField label = new LabelField("");
                label.setWidth(100);
                c.add(label);
                c.add(grammatura, flex);
                c.add(new LabelField("grammatura"), flex);

                panel.add(c, new FormData("100%"));
            }
        }

        { // Quantita'
            {
                LayoutContainer c = new LayoutContainer();
                HBoxLayout layout = new HBoxLayout();
                layout.setPadding(new Padding(5));
                layout.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.BOTTOM);
                c.setLayout(layout);

                NumberField numeroDiPagine = new NumberField();
                numeroDiPagine.setWidth(60);
                numeroDiPagine.setName("numeroDiPagine");
                numeroDiPagine.setRegex("[0-9]+");
                numeroDiPagine.getMessages().setRegexText("Inserisci un numero intero");
                numeroDiPagine.setPropertyEditorType(Integer.class);

                LabelField label = new LabelField("Quantità ");
                label.setWidth(100);
                c.add(label);
                c.add(numeroDiPagine, flex);
                c.add(new LabelField("numero di pagine"), flex);

                panel.add(c, new FormData("100%"));
            }
            {
                LayoutContainer c = new LayoutContainer();
                HBoxLayout layout = new HBoxLayout();
                layout.setPadding(new Padding(5));
                layout.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.BOTTOM);
                c.setLayout(layout);

                NumberField tiratura = new NumberField();
                tiratura.setWidth(60);
                tiratura.setName("tiratura");
                tiratura.setRegex("[0-9]+");
                tiratura.getMessages().setRegexText("Inserisci un numero intero");
                tiratura.setPropertyEditorType(Integer.class);

                LabelField label = new LabelField("");
                label.setWidth(100);
                c.add(label);
                c.add(tiratura, flex);
                c.add(new LabelField("tiratura"), flex);

                panel.add(c, new FormData("100%"));
            }
        }
        {   // Copertina
            {
                LayoutContainer c = new LayoutContainer();
                HBoxLayout layout = new HBoxLayout();
                layout.setPadding(new Padding(5));
                layout.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.BOTTOM);
                c.setLayout(layout);


                ComboBox<TipoDiCartaModel> tipoDiCarta = new ComboBox<TipoDiCartaModel>();
                tipoDiCarta.setEmptyText("Seleziona tipo di carta");
                //tipoDiCarta.setToolTip("TipoDiCarta");
                tipoDiCarta.setWidth(200);
                tipoDiCarta.setDisplayField("nome");
                tipoDiCarta.setName("tipoDiCartaCopertina");
                tipoDiCarta.setTriggerAction(ComboBox.TriggerAction.ALL);
                tipoDiCarta.setStore(tipoDiCartaModelListStore);

                LabelField label = new LabelField("Materiale Copertina");
                label.setWidth(100);
                c.add(label);
                c.add(tipoDiCarta, flex);

                panel.add(c, new FormData("100%"));
            }
            {
                LayoutContainer c = new LayoutContainer();
                HBoxLayout layout = new HBoxLayout();
                layout.setPadding(new Padding(5));
                layout.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.BOTTOM);
                c.setLayout(layout);

                NumberField grammatura = new NumberField();
                grammatura.setWidth(60);
                grammatura.setName("grammaturaCopertina");

                LabelField label = new LabelField("");
                label.setWidth(100);
                c.add(label);
                c.add(grammatura, flex);
                c.add(new LabelField("grammatura copertina"), flex);

                panel.add(c, new FormData("100%"));
            }
        }

        return panel;
    }

    private Grid<PubblicazioniRilegateModel> createGrid() {

        List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

        ColumnConfig column = new ColumnConfig();
        column.setRowHeader(false);
        column.setId("categoria");

        TextField<String> text = new TextField<String>();
        text.setAllowBlank(false);
        column.setWidth(160);
        column.setEditor(new CellEditor(text));
        configs.add(column);

        column = new ColumnConfig();
        column.setRowHeader(false);
        column.setId("Cancella");
        column.setRenderer(new GridCellRenderer<PubblicazioniRilegateModel>() {
            private boolean init;

            public Object render(final PubblicazioniRilegateModel model, String property, ColumnData config, final int rowIndex,
                                 final int colIndex, ListStore<PubblicazioniRilegateModel> store, Grid<PubblicazioniRilegateModel> grid) {
                if (!init) {
                    init = true;
                    grid.addListener(Events.ColumnResize, new Listener<GridEvent<PubblicazioniRilegateModel>>() {
                        public void handleEvent(GridEvent<PubblicazioniRilegateModel> be) {
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
                        if (pubblicazioniRilegateModel.getModels().size() == 1) {
                            Info.display("Info", "<ul><li>Impossibile Eliminare tutte le categorie </li></ul>");
                        } else {
                            Info.display("Info", "<ul><li>Eliminata: " + model.getCategoria() + "</li></ul>");
                            formBindings.unbind();
                            panel.setHeading("Aggiungi una Categoria o Personalizza quelle esistenti");
                            pubblicazioniRilegateModel.remove(model);
                        }
                    }
                });
                // b.setWidth(grid.getColumnModel().getColumnWidth(colIndex) - 10);
                b.setToolTip("Elimina Categoria");

                return b;
            }
        });
        column.setWidth(50);
        configs.add(column);


        final RowEditor<PubblicazioniRilegateModel> re = new RowEditor<PubblicazioniRilegateModel>();
        re.getMessages().setSaveText("Salva");
        re.getMessages().setCancelText("Annulla");
        re.setClicksToEdit(EditorGrid.ClicksToEdit.TWO);

        final ColumnModel cm = new ColumnModel(configs);
        final Grid<PubblicazioniRilegateModel> grid = new Grid<PubblicazioniRilegateModel>(pubblicazioniRilegateModel, cm);

        // grid.setAutoExpandColumn("categoria");
        grid.setColumnResize(true);
        grid.setBorders(true);
        grid.addPlugin(re);
        grid.setHideHeaders(true);

        Button add = new Button("Aggiungi categoria");
        add.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                PubblicazioniRilegateModel cate = new PubblicazioniRilegateModel();
                cate.setCategoria("Nuova Categoria");
                re.stopEditing(false);
                pubblicazioniRilegateModel.insert(cate, pubblicazioniRilegateModel.getModels().size());
                re.startEditing(pubblicazioniRilegateModel.indexOf(cate), true);
            }
        });
        toolBar.add(add);


        return grid;
    }

    public void clear(boolean setDefault) {
        for (PubblicazioniRilegateModel m : this.pubblicazioniRilegateModel.getModels()) {
            this.pubblicazioniRilegateModel.remove(m);
        }
        if (setDefault) {
            setDefault();
        }
    }

    public List<PubblicazioniRilegateModel> getPubblicazioniRilegateModel() {
        return pubblicazioniRilegateModel.getModels();
    }

    public void setPubblicazioniRilegateModel(List<PubblicazioniRilegateModel> pubblicazioniRilegateModel) {
        clear(false);
        this.pubblicazioniRilegateModel.add(pubblicazioniRilegateModel);
        if (pubblicazioniRilegateModel.size() == 0) {
            setDefault();
        }
        if (grid != null && pubblicazioniRilegateModel != null && pubblicazioniRilegateModel.size() < 0) {
            grid.getSelectionModel().select(pubblicazioniRilegateModel.get(0), true);
        }
    }

    private void setDefault() {
        PubblicazioniRilegateModel catalogo = new PubblicazioniRilegateModel();
        catalogo.setCategoria("catalogo");

        PubblicazioniRilegateModel bilancio = new PubblicazioniRilegateModel();
        bilancio.setCategoria("bilancio");

        PubblicazioniRilegateModel report = new PubblicazioniRilegateModel();
        report.setCategoria("report");

        PubblicazioniRilegateModel libro = new PubblicazioniRilegateModel();
        libro.setCategoria("libro");

        pubblicazioniRilegateModel.add(catalogo);
        pubblicazioniRilegateModel.add(bilancio);
        pubblicazioniRilegateModel.add(report);
        pubblicazioniRilegateModel.add(libro);

        formBindings.bind(catalogo);
    }

    public void setTipoDiCartaModel(List<TipoDiCartaModel> tipoDiCarta) {
        tipoDiCartaModelListStore.add(tipoDiCarta);
    }
}