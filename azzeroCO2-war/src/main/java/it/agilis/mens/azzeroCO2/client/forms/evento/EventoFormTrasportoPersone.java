package it.agilis.mens.azzeroCO2.client.forms.evento;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.binding.FormBinding;
import com.extjs.gxt.ui.client.data.BeanModel;
import com.extjs.gxt.ui.client.event.*;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.util.Padding;
import com.extjs.gxt.ui.client.widget.BoxComponent;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.ToolButton;
import com.extjs.gxt.ui.client.widget.form.*;
import com.extjs.gxt.ui.client.widget.grid.*;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.layout.*;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Image;
import it.agilis.mens.azzeroCO2.client.AzzeroCO2Resources;
import it.agilis.mens.azzeroCO2.shared.model.evento.TrasportoPersoneModel;

import java.util.ArrayList;
import java.util.List;

public class EventoFormTrasportoPersone extends LayoutContainer {
    private ListStore<TrasportoPersoneModel> trasportoPersoneModel = new ListStore<TrasportoPersoneModel>();
    private final ToolBar toolBar = new ToolBar();
    private final FormPanel panel = createGroupForm();
    private final FormBinding formBindings = new FormBinding(panel, true);

    public EventoFormTrasportoPersone() {
        this.trasportoPersoneModel.setMonitorChanges(true);
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
        cp.setLayout(new RowLayout(Orientation.HORIZONTAL));

        final Grid<TrasportoPersoneModel> grid = createGrid();

        panel.setEnabled(false);


        formBindings.setStore(grid.getStore());

        final FormPanel panelTutte = createGroupForm();
        panelTutte.add(panelTutte);
        panelTutte.setHeading("Tutte le Persone");

        ContentPanel cpCentre = new ContentPanel();
        cpCentre.setHeaderVisible(false);
        final CardLayout cardLayout = new CardLayout();
        cpCentre.setLayout(cardLayout);
        cpCentre.add(panelTutte);
        cpCentre.add(panel);
        grid.setEnabled(false);
        toolBar.setEnabled(false);

        ContentPanel radioContent = new ContentPanel();
        radioContent.setHeaderVisible(false);
        radioContent.setFrame(true);

        Radio tutti = new Radio();
        tutti.addListener(Events.OnClick, new Listener<BaseEvent>() {
            public void handleEvent(BaseEvent be) {
                Info.display("Info", "Categoria Tutte le persone Attiva");
                cardLayout.setActiveItem(panelTutte);
                formBindings.unbind();
                grid.setEnabled(false);
                toolBar.setEnabled(false);
            }
        });
        tutti.setBoxLabel("Tutte le persone");
        tutti.setValue(true);

        Radio personalizzato = new Radio();
        personalizzato.addListener(Events.OnClick, new Listener<BaseEvent>() {
            public void handleEvent(BaseEvent be) {
                Info.display("Info", "Categoria Personalizzate Attiva");
                panel.setHeading("Aggiungi una Categoria o Personalizza quelle esistenti");
                cardLayout.setActiveItem(panel);
                panel.setEnabled(false);
                grid.setEnabled(true);
                toolBar.setEnabled(true);
            }
        });
        personalizzato.setBoxLabel("Personalizzato");

        RadioGroup radioGroup = new RadioGroup();
        radioGroup.add(tutti);
        radioGroup.add(personalizzato);
        radioContent.add(radioGroup);

        ContentPanel cpEst = new ContentPanel();
        cpEst.setHeading("Categorie");

        cpEst.setLayout(new RowLayout(Orientation.VERTICAL));
        cpEst.add(radioContent, new RowData(1, 0.07, new Margins(0, 0, 0, 0)));
        cpEst.add(grid, new RowData(1, .93, new Margins(0, 0, 0, 0)));
        cpEst.setBottomComponent(toolBar);
        cpEst.setButtonAlign(Style.HorizontalAlignment.CENTER);

        cp.add(cpEst, new RowData(.3, .98, new Margins(0, 0, 0, 0)));
        cp.add(cpCentre, new RowData(.7, 1));

        grid.getSelectionModel().setSelectionMode(Style.SelectionMode.SINGLE);
        grid.getSelectionModel().addListener(Events.SelectionChange,
                new Listener<SelectionChangedEvent<TrasportoPersoneModel>>() {
                    public void handleEvent(SelectionChangedEvent<TrasportoPersoneModel> be) {
                        if (be.getSelection().size() > 0) {
                            formBindings.bind(be.getSelection().get(0));
                            panel.setHeading(be.getSelection().get(0).toString());
                            panel.setEnabled(true);
                        } else {
                            formBindings.unbind();
                        }
                    }
                });

        BorderLayoutData centerData = new BorderLayoutData(Style.LayoutRegion.CENTER);
        add(cp, centerData);
    }

    private FormPanel createGroupForm() {

        FormPanel formPanel = new FormPanel();
        formPanel.setFrame(true);
        formPanel.getHeader().addTool(new ToolButton("x-tool-help"));
        formPanel.getHeader().addTool(new ToolButton("x-tool-close"));

        HBoxLayoutData flex = new HBoxLayoutData(new Margins(0, 5, 0, 0));
        {
            LayoutContainer cl = new LayoutContainer();
            HBoxLayout layout = new HBoxLayout();
            layout.setPadding(new Padding(5));
            layout.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.MIDDLE);
            cl.setLayout(layout);

            cl.add(new LabelField("Inserisci il numero di tratte per distanza percorsa e mezzo di trasporto.<br> Es: due pendolari in treno che partecipano a un evento di 4 giorni = 16 tratte."), flex);
            formPanel.add(cl);
        }
        {
            LayoutContainer piu50 = new LayoutContainer();
            HBoxLayout layoutRigaPiu50 = new HBoxLayout();
            layoutRigaPiu50.setPadding(new Padding(7));
            piu50.setLayout(layoutRigaPiu50);

            piu50.add(new LabelField("Distanza percorsa: provinciale"), flex);
            formPanel.add(piu50);

            LayoutContainer piu50input = new LayoutContainer();
            HBoxLayout layoutPiu50input = new HBoxLayout();
            layoutPiu50input.setPadding(new Padding(7));
            layoutPiu50input.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.MIDDLE);
            piu50input.setLayout(layoutPiu50input);

            Image bus = new Image(AzzeroCO2Resources.INSTANCE.bus());
            NumberField busPiu50 = new NumberField();
            // busPiu50.setFormat(NumberFormat.getFormat("0"));
            busPiu50.setName("busPiu50");
            busPiu50.setWidth(60);
            busPiu50.setRegex("[0-9]+");
            busPiu50.getMessages().setRegexText("Inserisci un numero intero");
            busPiu50.setPropertyEditorType(Integer.class);

            Image automobile = new Image(AzzeroCO2Resources.INSTANCE.automobile());
            NumberField autoPiu50 = new NumberField();
            autoPiu50.setName("autoPiu50");
            autoPiu50.setWidth(60);
            autoPiu50.setRegex("[0-9]+");
            autoPiu50.getMessages().setRegexText("Inserisci un numero intero");
            autoPiu50.setPropertyEditorType(Integer.class);


            Image treno = new Image(AzzeroCO2Resources.INSTANCE.treno());
            NumberField trenoPiu50 = new NumberField();
            trenoPiu50.setName("trenoPiu50");
            trenoPiu50.setWidth(60);
            trenoPiu50.setRegex("[0-9]+");
            trenoPiu50.getMessages().setRegexText("Inserisci un numero intero");
            trenoPiu50.setPropertyEditorType(Integer.class);


            Image moto = new Image(AzzeroCO2Resources.INSTANCE.moto());
            NumberField motoPiu50 = new NumberField();
            motoPiu50.setName("motoPiu50");
            motoPiu50.setWidth(60);
            motoPiu50.setRegex("[0-9]+");
            motoPiu50.getMessages().setRegexText("Inserisci un numero intero");
            motoPiu50.setPropertyEditorType(Integer.class);

            piu50input.add(bus);
            piu50input.add(busPiu50, flex);
            piu50input.add(automobile);
            piu50input.add(autoPiu50, flex);
            piu50input.add(treno);
            piu50input.add(trenoPiu50, flex);
            piu50input.add(moto);
            piu50input.add(motoPiu50, flex);


            layoutRigaPiu50.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.MIDDLE);
            formPanel.add(piu50input);
        }
        {
            LayoutContainer piu100 = new LayoutContainer();
            HBoxLayout layoutRigaPiu100 = new HBoxLayout();
            layoutRigaPiu100.setPadding(new Padding(7));
            layoutRigaPiu100.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.MIDDLE);
            piu100.setLayout(layoutRigaPiu100);
            piu100.add(new LabelField("Distanza percorsa: regionale"), flex);
            formPanel.add(piu100);

            LayoutContainer piu100input = new LayoutContainer();
            HBoxLayout layoutPiu100input = new HBoxLayout();
            layoutPiu100input.setPadding(new Padding(7));
            layoutPiu100input.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.MIDDLE);
            piu100input.setLayout(layoutPiu100input);

            Image bus100 = new Image(AzzeroCO2Resources.INSTANCE.bus());
            Image moto100 = new Image(AzzeroCO2Resources.INSTANCE.moto());
            NumberField busPiu100 = new NumberField();
            busPiu100.setName("busPiu100");
            busPiu100.setWidth(60);
            busPiu100.setPropertyEditorType(Integer.class);

            Image automobile100 = new Image(AzzeroCO2Resources.INSTANCE.automobile());
            automobile100.setAltText("Auto");
            busPiu100.setRegex("[0-9]+");
            busPiu100.getMessages().setRegexText("Inserisci un numero intero");



            NumberField autoPiu100 = new NumberField();
            autoPiu100.setName("autoPiu100");
            autoPiu100.setWidth(60);
            Image treno100 = new Image(AzzeroCO2Resources.INSTANCE.treno());
            autoPiu100.setRegex("[0-9]+");
            autoPiu100.getMessages().setRegexText("Inserisci un numero intero");
            autoPiu100.setPropertyEditorType(Integer.class);


            NumberField trenoPiu100 = new NumberField();
            trenoPiu100.setName("trenoPiu100");
            trenoPiu100.setWidth(60);
            trenoPiu100.setRegex("[0-9]+");
            trenoPiu100.getMessages().setRegexText("Inserisci un numero intero");
            trenoPiu100.setPropertyEditorType(Integer.class);


            NumberField motoPiu100 = new NumberField();
            trenoPiu100.setName("motoPiu100");
            motoPiu100.setWidth(60);
            motoPiu100.setRegex("[0-9]+");
            motoPiu100.getMessages().setRegexText("Inserisci un numero intero");
            motoPiu100.setPropertyEditorType(Integer.class);


            piu100input.add(bus100);
            piu100input.add(busPiu100, flex);

            piu100input.add(automobile100);
            piu100input.add(autoPiu100, flex);
            piu100input.add(treno100);
            piu100input.add(trenoPiu100, flex);
            piu100input.add(moto100);
            piu100input.add(motoPiu100, flex);


            formPanel.add(piu100input, new FormData("100%"));
        }
        {
            LayoutContainer piu250 = new LayoutContainer();
            HBoxLayout layoutRigaPiu250 = new HBoxLayout();
            layoutRigaPiu250.setPadding(new Padding(7));
            layoutRigaPiu250.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.MIDDLE);
            piu250.setLayout(layoutRigaPiu250);
            piu250.add(new LabelField("Distanza percorsa: nazionale"), flex);

            formPanel.add(piu250);
            LayoutContainer piu250input = new LayoutContainer();
            HBoxLayout layoutPiu250input = new HBoxLayout();
            layoutPiu250input.setPadding(new Padding(7));
            layoutPiu250input.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.MIDDLE);
            piu250input.setLayout(layoutPiu250input);

            Image bus250 = new Image(AzzeroCO2Resources.INSTANCE.bus());
            NumberField busPiu250 = new NumberField();
            busPiu250.setName("busPiu250");
            busPiu250.setWidth(60);
            busPiu250.setRegex("[0-9]+");
            busPiu250.getMessages().setRegexText("Inserisci un numero intero");
            busPiu250.setPropertyEditorType(Integer.class);

            Image automobile250 = new Image(AzzeroCO2Resources.INSTANCE.automobile());
            NumberField autoPiu250 = new NumberField();
            autoPiu250.setName("autoPiu250");
            autoPiu250.setWidth(60);
            autoPiu250.setRegex("[0-9]+");
            autoPiu250.getMessages().setRegexText("Inserisci un numero intero");
            autoPiu250.setPropertyEditorType(Integer.class);

            Image treno250 = new Image(AzzeroCO2Resources.INSTANCE.treno());
            NumberField trenoPiu250 = new NumberField();
            trenoPiu250.setName("trenoPiu250");
            trenoPiu250.setWidth(60);
            trenoPiu250.setRegex("[0-9]+");
            trenoPiu250.getMessages().setRegexText("Inserisci un numero intero");


            Image aereo250 = new Image(AzzeroCO2Resources.INSTANCE.aereo());
            NumberField aereoPiu250 = new NumberField();
            aereoPiu250.setName("aereoPiu250");
            aereoPiu250.setWidth(60);
            aereoPiu250.setRegex("[0-9]+");
            aereoPiu250.getMessages().setRegexText("Inserisci un numero intero");
            aereoPiu250.setPropertyEditorType(Integer.class);


            piu250input.add(bus250);
            piu250input.add(busPiu250, flex);
            piu250input.add(automobile250);
            piu250input.add(autoPiu250, flex);
            piu250input.add(treno250);
            piu250input.add(trenoPiu250, flex);
            piu250input.add(aereo250);
            piu250input.add(aereoPiu250, flex);
            formPanel.add(piu250input, new FormData("100%"));
        }
        {
            LayoutContainer piu500 = new LayoutContainer();
            HBoxLayout layoutRigaPiu500 = new HBoxLayout();
            layoutRigaPiu500.setPadding(new Padding(7));
            layoutRigaPiu500.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.MIDDLE);
            piu500.setLayout(layoutRigaPiu500);

            piu500.add(new LabelField("Distanza percorsa: europea"), flex);
            formPanel.add(piu500);

            LayoutContainer piu500input = new LayoutContainer();
            HBoxLayout layoutPiu500input = new HBoxLayout();
            layoutPiu500input.setPadding(new Padding(7));
            layoutPiu500input.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.MIDDLE);
            piu500input.setLayout(layoutPiu500input);

            Image bus500 = new Image(AzzeroCO2Resources.INSTANCE.bus());
            NumberField busPiu500 = new NumberField();
            busPiu500.setName("busPiu500");
            busPiu500.setWidth(60);
            busPiu500.setRegex("[0-9]+");
            busPiu500.getMessages().setRegexText("Inserisci un numero intero");
            busPiu500.setPropertyEditorType(Integer.class);

            Image automobile500 = new Image(AzzeroCO2Resources.INSTANCE.automobile());
            NumberField autoPiu500 = new NumberField();
            autoPiu500.setName("autoPiu500");
            autoPiu500.setWidth(60);
            autoPiu500.setRegex("[0-9]+");
            autoPiu500.getMessages().setRegexText("Inserisci un numero intero");
            autoPiu500.setPropertyEditorType(Integer.class);

            Image treno500 = new Image(AzzeroCO2Resources.INSTANCE.treno());
            NumberField trenoPiu500 = new NumberField();
            trenoPiu500.setName("trenoPiu500");
            trenoPiu500.setWidth(60);
            trenoPiu500.setRegex("[0-9]+");
            trenoPiu500.getMessages().setRegexText("Inserisci un numero intero");
            trenoPiu500.setPropertyEditorType(Integer.class);

            Image aereo500 = new Image(AzzeroCO2Resources.INSTANCE.aereo());
            NumberField aereoPiu500 = new NumberField();
            aereoPiu500.setName("aereoPiu500");
            aereoPiu500.setWidth(60);
            aereoPiu500.setRegex("[0-9]+");
            aereoPiu500.getMessages().setRegexText("Inserisci un numero intero");
            aereoPiu500.setPropertyEditorType(Integer.class);

            piu500input.add(bus500);
            piu500input.add(busPiu500, flex);
            piu500input.add(automobile500);
            piu500input.add(autoPiu500, flex);
            piu500input.add(treno500);
            piu500input.add(trenoPiu500, flex);
            piu500input.add(aereo500);
            piu500input.add(aereoPiu500, flex);
            formPanel.add(piu500input);
        }

        {
            LayoutContainer piu500 = new LayoutContainer();
            HBoxLayout layoutRigaPiu500 = new HBoxLayout();
            layoutRigaPiu500.setPadding(new Padding(7));
            layoutRigaPiu500.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.MIDDLE);
            piu500.setLayout(layoutRigaPiu500);

            piu500.add(new LabelField("Distanza percorsa: extra europea"), flex);
            formPanel.add(piu500);

            LayoutContainer piu500input = new LayoutContainer();
            HBoxLayout layoutPiu500input = new HBoxLayout();
            layoutPiu500input.setPadding(new Padding(7));
            layoutPiu500input.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.MIDDLE);
            piu500input.setLayout(layoutPiu500input);

            Image bus500 = new Image(AzzeroCO2Resources.INSTANCE.bus());
            NumberField busPiu500 = new NumberField();
            busPiu500.setName("busPiu500");
            busPiu500.setWidth(60);
            busPiu500.setRegex("[0-9]+");
            busPiu500.getMessages().setRegexText("Inserisci un numero intero");
            busPiu500.setPropertyEditorType(Integer.class);

            Image automobile500 = new Image(AzzeroCO2Resources.INSTANCE.automobile());
            NumberField autoPiu500 = new NumberField();
            autoPiu500.setName("autoPiu500");
            autoPiu500.setWidth(60);
            autoPiu500.setRegex("[0-9]+");
            autoPiu500.getMessages().setRegexText("Inserisci un numero intero");
            autoPiu500.setPropertyEditorType(Integer.class);

            Image treno500 = new Image(AzzeroCO2Resources.INSTANCE.treno());
            NumberField trenoPiu500 = new NumberField();
            trenoPiu500.setName("trenoPiu500");
            trenoPiu500.setWidth(60);
            trenoPiu500.setRegex("[0-9]+");
            trenoPiu500.getMessages().setRegexText("Inserisci un numero intero");
            trenoPiu500.setPropertyEditorType(Integer.class);

            Image aereo500 = new Image(AzzeroCO2Resources.INSTANCE.aereo());
            NumberField aereoPiu500 = new NumberField();
            aereoPiu500.setName("aereoPiu500");
            aereoPiu500.setWidth(60);
            aereoPiu500.setRegex("[0-9]+");
            aereoPiu500.getMessages().setRegexText("Inserisci un numero intero");
            aereoPiu500.setPropertyEditorType(Integer.class);

            piu500input.add(bus500);
            piu500input.add(busPiu500, flex);
            piu500input.add(automobile500);
            piu500input.add(autoPiu500, flex);
            piu500input.add(treno500);
            piu500input.add(trenoPiu500, flex);
            piu500input.add(aereo500);
            piu500input.add(aereoPiu500, flex);
            formPanel.add(piu500input);
        }
        return formPanel;
    }

    private Grid<TrasportoPersoneModel> createGrid() {
        List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

        ColumnConfig column = new ColumnConfig();
        column.setRowHeader(false);
        column.setId("categoria");

        TextField<String> text = new TextField<String>();
        text.setAllowBlank(false);
        column.setEditor(new CellEditor(text));
        column.setWidth(50);
        configs.add(column);

        column = new ColumnConfig();
        column.setRowHeader(false);
        column.setId("Cancella");
        column.setRenderer(new GridCellRenderer<TrasportoPersoneModel>() {
            private boolean init;

            public Object render(final TrasportoPersoneModel model, String property, ColumnData config, final int rowIndex,
                                 final int colIndex, ListStore<TrasportoPersoneModel> store, Grid<TrasportoPersoneModel> grid) {
                if (!init) {
                    init = true;
                    grid.addListener(Events.ColumnResize, new Listener<GridEvent<TrasportoPersoneModel>>() {
                        public void handleEvent(GridEvent<TrasportoPersoneModel> be) {
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
                        Info.display("Info", "<ul><li>Eliminata: " + model.getCategoria() + "</li></ul>");
                        formBindings.unbind();
                        panel.setHeading("Aggiungi una Categoria o Personalizza quelle esistenti");
                        trasportoPersoneModel.remove(model);
                    }
                });
                // b.setWidth(grid.getColumnModel().getColumnWidth(colIndex) - 10);
                b.setToolTip("Elimina Categoria");

                return b;
            }
        });
        column.setWidth(50);
        configs.add(column);

        final RowEditor<BeanModel> re = new RowEditor<BeanModel>();
        re.getMessages().setSaveText("Salva");
        re.getMessages().setCancelText("Annulla");
        re.setClicksToEdit(EditorGrid.ClicksToEdit.TWO);

        final ColumnModel cm = new ColumnModel(configs);
        final Grid<TrasportoPersoneModel> grid = new Grid<TrasportoPersoneModel>(trasportoPersoneModel, cm);

        grid.setAutoExpandColumn("categoria");
        grid.setBorders(true);
        grid.addPlugin(re);
        grid.setHideHeaders(true);
        grid.getSelectionModel().setSelectionMode(Style.SelectionMode.SINGLE);

        Button add = new Button("Aggiungi categoria");
        add.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                TrasportoPersoneModel cate = new TrasportoPersoneModel();
                cate.setCategoria("Nuova Categoria");
                re.stopEditing(false);
                trasportoPersoneModel.insert(cate, 0);
                re.startEditing(trasportoPersoneModel.indexOf(cate), true);
            }

        });
        toolBar.add(add);

        return grid;
    }


    public void clear() {

    }

    public List<TrasportoPersoneModel> getTrasportoPersoneModel() {
        return trasportoPersoneModel.getModels();
    }

    public void setTrasportoPersoneModel(List<TrasportoPersoneModel> trasportoPersoneModel) {
        this.trasportoPersoneModel.removeAll(); // TODO CHECK
        this.trasportoPersoneModel.add(trasportoPersoneModel);
    }
}

