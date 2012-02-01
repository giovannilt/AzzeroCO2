package it.agilis.mens.azzeroCO2.client.forms;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.binding.FormBinding;
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
import com.extjs.gxt.ui.client.widget.form.*;
import com.extjs.gxt.ui.client.widget.grid.*;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.layout.*;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.user.client.Element;
import it.agilis.mens.azzeroCO2.client.MyInfo;
import it.agilis.mens.azzeroCO2.client.mvc.events.AzzeroCO2Events;
import it.agilis.mens.azzeroCO2.shared.model.evento.ManifestiPieghevoliFogliModel;
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
public class FormManifestiPieghevoliFogli extends LayoutContainer {
    private ListStore<ManifestiPieghevoliFogliModel> manifestiPieghevoliFogliModel = new ListStore<ManifestiPieghevoliFogliModel>();
    private ToolBar toolBar = new ToolBar();
    private ListStore<TipoDiCartaModel> tipoDiCartaModelListStore = new ListStore<TipoDiCartaModel>();
    private final FormPanel panel = createGroupForm();
    private final FormBinding formBindings = new FormBinding(panel, true);
    private final Grid<ManifestiPieghevoliFogliModel> grid = createGrid();
    private ContentPanel cpEst = new ContentPanel();

    public FormManifestiPieghevoliFogli() {
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
        Text testo = new Text(" Si tratta di pubblicazioni non rilegate. <br><br>");
        testo.setStyleAttribute("font-size", "9pt");
        testo.setStyleAttribute("padding-top", "20px");

        Text note = new Text("Puoi inserire più di un formato. ");
        note.setStyleAttribute("font-size", "9pt");
        note.setStyleAttribute("font-style", "italic");

        textContent.add(testo);
        textContent.add(note);

        cpEst.setFrame(false);
        cpEst.setHeading("Manifesti pieghevoli e fogli");
        cpEst.setLayout(new RowLayout(Style.Orientation.VERTICAL));
        cpEst.add(textContent, new RowData(1, .15, new Margins(0, 0, 0, 0)));
        cpEst.add(grid, new RowData(1, .85, new Margins(0, 0, 0, 0)));
        cpEst.setBottomComponent(toolBar);
        cpEst.setButtonAlign(Style.HorizontalAlignment.CENTER);

        cp.add(cpEst, new RowData(.35, .946));
        cp.add(panel, new RowData(.642, 0.96));
        panel.setHeading(manifestiPieghevoliFogliModel.getModels().get(0).getCategoria());

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
                new Listener<SelectionChangedEvent<ManifestiPieghevoliFogliModel>>() {
                    public void handleEvent(SelectionChangedEvent<ManifestiPieghevoliFogliModel> be) {
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
        toolBar.setStyleAttribute("border-bottom", "3px solid #f8b333");
        cpEst.getBody().setStyleAttribute("border-style", "solid");
        cpEst.getBody().setStyleAttribute("border-top", "3px solid #f8b333");
        cpEst.getBody().setStyleAttribute("border-width", "3px 0");
        cpEst.getBody().setStyleAttribute("margin-bottom", "0");
        panel.getBody().setStyleAttribute("border-bottom", "3px solid #f8b333");
        panel.getBody().setStyleAttribute("border-style", "solid");
        panel.getBody().setStyleAttribute("border-top", "3px solid #f8b333");
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
            layout2.setPadding(new Padding(10));
            layout2.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.BOTTOM);
            c2.setLayout(layout2);

            LabelField istruzioni = new LabelField("Inserisci le caratteristiche.");
            istruzioni.setStyleAttribute("font-weight", "bolder");
            c2.add(istruzioni, flex);

            panel.add(c2);
        }
        { // Formato Aperto
            {
                LayoutContainer c = new LayoutContainer();
                HBoxLayout layout = new HBoxLayout();
                layout.setPadding(new Padding(10));
                layout.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.BOTTOM);
                c.setLayout(layout);

                NumberField altezza = new NumberField();
                altezza.setWidth(60);
                altezza.setName("altezza");

                LabelField label = new LabelField("Formato Aperto ");
                label.setWidth(120);
                c.add(label);
                c.add(altezza, flex);
                c.add(new LabelField("altezza (cm)"), flex);
                c.setStyleAttribute("padding-top", "10px");
                panel.add(c, new FormData("100%"));
            }
            {
                LayoutContainer c = new LayoutContainer();
                HBoxLayout layout = new HBoxLayout();
                layout.setPadding(new Padding(10));
                layout.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.BOTTOM);
                c.setLayout(layout);


                LabelField label = new LabelField(" ");
                label.setWidth(120);
                c.add(label);

                NumberField larghezza = new NumberField();
                larghezza.setWidth(60);
                larghezza.setName("larghezza");
                c.setStyleAttribute("padding-top", "10px");
                c.add(larghezza, flex);
                c.add(new LabelField("larghezza (cm)"), flex);

                panel.add(c, new FormData("100%"));
            }
        }
        {   // Materiale
            {
                LayoutContainer c = new LayoutContainer();
                HBoxLayout layout = new HBoxLayout();
                layout.setPadding(new Padding(10));
                layout.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.BOTTOM);
                c.setLayout(layout);

                ComboBox<TipoDiCartaModel> tipoDiCarta = new ComboBox<TipoDiCartaModel>();
                tipoDiCarta.setEmptyText("Seleziona tipo di carta");
                tipoDiCarta.setToolTip("TipoDiCarta");
                tipoDiCarta.setDisplayField("parametro");
                tipoDiCarta.setWidth(200);
                tipoDiCarta.setDisplayField("nome");
                tipoDiCarta.setName("tipoDiCarta");
                tipoDiCarta.setTriggerAction(ComboBox.TriggerAction.ALL);
                tipoDiCarta.setStore(tipoDiCartaModelListStore);

                LabelField label = new LabelField("Materiale ");
                label.setWidth(120);
                c.add(label);
                c.add(tipoDiCarta, flex);
                c.setStyleAttribute("padding-top", "10px");
                panel.add(c, new FormData("100%"));
            }
            {
                LayoutContainer c = new LayoutContainer();
                HBoxLayout layout = new HBoxLayout();
                layout.setPadding(new Padding(10));
                layout.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.BOTTOM);
                c.setLayout(layout);

                NumberField grammatura = new NumberField();
                grammatura.setWidth(60);
                grammatura.setName("grammatura");

                LabelField label = new LabelField("");
                label.setWidth(120);
                c.add(label);
                c.add(grammatura, flex);
                c.add(new LabelField("grammatura"), flex);
                c.setStyleAttribute("padding-top", "10px");
                panel.add(c, new FormData("100%"));
            }
        }

        { // Tiratura
            {
                LayoutContainer c = new LayoutContainer();
                HBoxLayout layout = new HBoxLayout();
                layout.setPadding(new Padding(10));
                layout.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.BOTTOM);
                c.setLayout(layout);

                NumberField tiratura = new NumberField();
                tiratura.setWidth(60);
                tiratura.setName("tiratura");
                tiratura.setRegex("[0-9]+");
                tiratura.getMessages().setRegexText("Inserisci un numero intero");
                tiratura.setPropertyEditorType(Integer.class);

                LabelField label = new LabelField("Tiratura ");
                label.setWidth(120);
                c.add(label);
                c.add(tiratura, flex);
                c.add(new LabelField("copie"), flex);
                c.setStyleAttribute("padding-top", "10px");
                panel.add(c, new FormData("100%"));
            }
        }
        return panel;
    }

    private Grid<ManifestiPieghevoliFogliModel> createGrid() {

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
        column.setRenderer(new GridCellRenderer<ManifestiPieghevoliFogliModel>() {
            private boolean init;

            public Object render(final ManifestiPieghevoliFogliModel model, String property, ColumnData config, final int rowIndex,
                                 final int colIndex, ListStore<ManifestiPieghevoliFogliModel> store, Grid<ManifestiPieghevoliFogliModel> grid) {
                if (!init) {
                    init = true;
                    grid.addListener(Events.ColumnResize, new Listener<GridEvent<ManifestiPieghevoliFogliModel>>() {
                        public void handleEvent(GridEvent<ManifestiPieghevoliFogliModel> be) {
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
                        if (manifestiPieghevoliFogliModel.getModels().size() == 1) {
                            MyInfo.show("<ul><li>Impossibile Eliminare tutte le categorie </li></ul>");
                        } else {
                            MyInfo.show("<ul><li>Eliminata: " + model.getCategoria() + "</li></ul>");
                            formBindings.unbind();
                            panel.setHeading("Aggiungi una Categoria o Personalizza quelle esistenti");
                            manifestiPieghevoliFogliModel.remove(model);
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
        final Grid<ManifestiPieghevoliFogliModel> grid = new Grid<ManifestiPieghevoliFogliModel>(manifestiPieghevoliFogliModel, cm);

        //  grid.setAutoExpandColumn("categoria");
        grid.setColumnResize(true);
        grid.setBorders(true);
        grid.addPlugin(re);
        grid.setHideHeaders(true);

        Button add = new Button("Aggiungi categoria");
        add.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                ManifestiPieghevoliFogliModel cate = new ManifestiPieghevoliFogliModel();
                cate.setCategoria("NuovaCategoria");
                re.stopEditing(false);
                manifestiPieghevoliFogliModel.insert(cate, manifestiPieghevoliFogliModel.getModels().size());
                re.startEditing(manifestiPieghevoliFogliModel.indexOf(cate), true);
            }
        });
        toolBar.add(add);


        return grid;
    }

    public void clear(boolean setDefault) {
        for (ManifestiPieghevoliFogliModel m : this.manifestiPieghevoliFogliModel.getModels()) {
            this.manifestiPieghevoliFogliModel.remove(m);
        }
        if (setDefault) {
            setDefault();
        }
    }

    public List<ManifestiPieghevoliFogliModel> getManifestiPieghevoliFogliModel() {
        return manifestiPieghevoliFogliModel.getModels();
    }

    public void setManifestiPieghevoliFogliModel(List<ManifestiPieghevoliFogliModel> manifestiPieghevoliFogliModel) {
        clear(false);
        this.manifestiPieghevoliFogliModel.add(manifestiPieghevoliFogliModel);
        if (manifestiPieghevoliFogliModel.size() == 0) {
            setDefault();
        }
        if (grid != null && manifestiPieghevoliFogliModel != null && manifestiPieghevoliFogliModel.size() < 0) {
            grid.getSelectionModel().select(manifestiPieghevoliFogliModel.get(0), true);
        }

    }

    private void setDefault() {
        ManifestiPieghevoliFogliModel manifesti = new ManifestiPieghevoliFogliModel();
        manifesti.setCategoria("manifesti");

        ManifestiPieghevoliFogliModel pieghevoli = new ManifestiPieghevoliFogliModel();
        pieghevoli.setCategoria("pieghevoli");

        ManifestiPieghevoliFogliModel fogli = new ManifestiPieghevoliFogliModel();
        fogli.setCategoria("fogli");

        manifestiPieghevoliFogliModel.add(manifesti);
        manifestiPieghevoliFogliModel.add(pieghevoli);
        manifestiPieghevoliFogliModel.add(fogli);

        formBindings.bind(manifesti);
    }

    public void setTipoDiCartaModel(List<TipoDiCartaModel> tipoDiCarta) {
        tipoDiCartaModelListStore.add(tipoDiCarta);
    }


}