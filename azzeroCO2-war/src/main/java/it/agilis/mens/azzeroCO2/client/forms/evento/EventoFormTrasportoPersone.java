package it.agilis.mens.azzeroCO2.client.forms.evento;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.binding.FormBinding;
import com.extjs.gxt.ui.client.data.BeanModel;
import com.extjs.gxt.ui.client.event.*;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.util.Padding;
import com.extjs.gxt.ui.client.widget.*;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.ToolButton;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.*;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.layout.*;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Image;
import it.agilis.mens.azzeroCO2.client.AzzeroCO2Resources;
import it.agilis.mens.azzeroCO2.client.mvc.events.EventoEvents;
import it.agilis.mens.azzeroCO2.shared.model.evento.TrasportoPersoneModel;

import java.util.ArrayList;
import java.util.List;

public class EventoFormTrasportoPersone extends LayoutContainer {
    private ListStore<TrasportoPersoneModel> trasportoPersoneModel = new ListStore<TrasportoPersoneModel>();
    private final ToolBar toolBar = new ToolBar();
    private final FormPanel panel = createGroupForm();
    private final FormBinding formBindings = new FormBinding(panel, true);
    private Grid<TrasportoPersoneModel> grid;
    private ContentPanel cpEst = new ContentPanel();

    public EventoFormTrasportoPersone() {
        this.trasportoPersoneModel.setMonitorChanges(true);
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
        cp.setLayout(new RowLayout(Orientation.HORIZONTAL));

        final Grid<TrasportoPersoneModel> grid = createGrid();

        panel.setEnabled(false);

        formBindings.setStore(grid.getStore());

        ContentPanel cpCentre = new ContentPanel();
        cpCentre.setHeaderVisible(false);
        final CardLayout cardLayout = new CardLayout();
        cpCentre.setLayout(cardLayout);
        cpCentre.add(panel);

        ContentPanel textContent = new ContentPanel();
        textContent.setHeaderVisible(false);
        textContent.setLayout(new RowLayout(Style.Orientation.VERTICAL));
        textContent.add(textContent, new RowData(1, 1, new Margins(2, 2, 2, 2)));
        Text testo = new Text(" Scegli 'Tutte le persone' per calcolare le emissioni di tutte le persone che si sono spostate per l'evento.<br><br>Se invece vuoi dettagliare i trasporti per categoria di persone, non compilare 'Tutte le persone' ma usa le altre categorie disponibili o creane nuove.");
        testo.setStyleAttribute("font-size", "9pt");
        textContent.add(testo);


        cpEst.setFrame(false);
        cpEst.setHeading("Trasporto persone");

        cpEst.setLayout(new RowLayout(Orientation.VERTICAL));
        cpEst.add(textContent, new RowData(1, .35, new Margins(0, 0, 0, 0)));
        cpEst.add(grid, new RowData(1, .65, new Margins(0, 0, 0, 0)));

        cpEst.setBottomComponent(toolBar);
        cpEst.setButtonAlign(Style.HorizontalAlignment.CENTER);

        cp.add(cpEst, new RowData(.35, .98));
        cp.add(panel, new RowData(.65, 1));

        cp.add(cpCentre, new RowData(.65, 1));

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
        grid.getSelectionModel().select(0, true);
        add(cp, centerData);
    }

    private FormPanel createGroupForm() {

        FormPanel formPanel = new FormPanel();
        formPanel.setFrame(true);

        ToolButton tool1 = new ToolButton("x-tool-help");
        formPanel.getHeader().addTool(tool1);
        tool1.addSelectionListener(new SelectionListener<IconButtonEvent>() {
            @Override
            public void componentSelected(IconButtonEvent ce) {
                Dispatcher.forwardEvent(EventoEvents.ShowInfoDialog);
            }
        });
        ToolButton tool = new ToolButton("x-tool-refresh");
        formPanel.getHeader().addTool(tool);

        tool.addSelectionListener(new SelectionListener<IconButtonEvent>() {
            @Override
            public void componentSelected(IconButtonEvent ce) {
                clear(true);
            }
        });

        HBoxLayoutData flex = new HBoxLayoutData(new Margins(0, 5, 0, 0));
        {
            LayoutContainer cl = new LayoutContainer();
            HBoxLayout layout = new HBoxLayout();
            layout.setPadding(new Padding(5));
            layout.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.MIDDLE);
            cl.setLayout(layout);

            LabelField istruzioni = new LabelField("Inserisci il numero di tratte per distanza percorsa e mezzo di trasporto.<br> ");
            LabelField note = new LabelField("Es: due pendolari in treno che partecipano a un evento di 4 giorni = 16 tratte.");

            istruzioni.setStyleAttribute("font-weight", "bolder");
            note.setStyleAttribute("font-style", "italic");


            cl.add(istruzioni, flex);

            formPanel.add(cl);


            LayoutContainer c2 = new LayoutContainer();
            HBoxLayout layout2 = new HBoxLayout();
            layout2.setPadding(new Padding(5));
            layout2.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.MIDDLE);
            c2.setLayout(layout2);

            c2.add(note, flex);


            formPanel.add(c2);

        }
        {
            LayoutContainer km60 = new LayoutContainer();
            HBoxLayout layoutRigaKm60 = new HBoxLayout();
            layoutRigaKm60.setPadding(new Padding(2));
            km60.setLayout(layoutRigaKm60);

            km60.add(new LabelField("Distanza percorsa: provinciale"), flex);
            formPanel.add(km60);

            LayoutContainer km60input = new LayoutContainer();
            HBoxLayout layoutKm60input = new HBoxLayout();
            layoutKm60input.setPadding(new Padding(7));
            layoutKm60input.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.MIDDLE);
            km60input.setLayout(layoutKm60input);

            Image bus = new Image(AzzeroCO2Resources.INSTANCE.bus());
            NumberField busKm60 = new NumberField();
            // busKm60.setFormat(NumberFormat.getFormat("0"));
            busKm60.setName("busKm60");
            busKm60.setWidth(60);
            busKm60.setRegex("[0-9]+");
            busKm60.getMessages().setRegexText("Inserisci un numero intero");
            busKm60.setPropertyEditorType(Integer.class);

            Image automobile = new Image(AzzeroCO2Resources.INSTANCE.automobile());
            NumberField autoKm60 = new NumberField();
            autoKm60.setName("autoKm60");
            autoKm60.setWidth(60);
            autoKm60.setRegex("[0-9]+");
            autoKm60.getMessages().setRegexText("Inserisci un numero intero");
            autoKm60.setPropertyEditorType(Integer.class);


            Image treno = new Image(AzzeroCO2Resources.INSTANCE.treno());
            NumberField trenoKm60 = new NumberField();
            trenoKm60.setName("trenoKm60");
            trenoKm60.setWidth(60);
            trenoKm60.setRegex("[0-9]+");
            trenoKm60.getMessages().setRegexText("Inserisci un numero intero");
            trenoKm60.setPropertyEditorType(Integer.class);


            Image moto = new Image(AzzeroCO2Resources.INSTANCE.moto());
            NumberField motoKm60 = new NumberField();
            motoKm60.setName("motoKm60");
            motoKm60.setWidth(60);
            motoKm60.setRegex("[0-9]+");
            motoKm60.getMessages().setRegexText("Inserisci un numero intero");
            motoKm60.setPropertyEditorType(Integer.class);

            km60input.add(bus);
            km60input.add(busKm60, flex);
            km60input.add(automobile);
            km60input.add(autoKm60, flex);
            km60input.add(treno);
            km60input.add(trenoKm60, flex);
            km60input.add(moto);
            km60input.add(motoKm60, flex);


            layoutRigaKm60.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.MIDDLE);
            formPanel.add(km60input);
        }
        {
            LayoutContainer km300 = new LayoutContainer();
            HBoxLayout layoutRigaKm300 = new HBoxLayout();
            layoutRigaKm300.setPadding(new Padding(2));
            layoutRigaKm300.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.MIDDLE);
            km300.setLayout(layoutRigaKm300);
            km300.add(new LabelField("Distanza percorsa: regionale"), flex);
            formPanel.add(km300);

            LayoutContainer km300input = new LayoutContainer();
            HBoxLayout layoutKm300input = new HBoxLayout();
            layoutKm300input.setPadding(new Padding(2));
            layoutKm300input.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.MIDDLE);
            km300input.setLayout(layoutKm300input);

            Image bus300 = new Image(AzzeroCO2Resources.INSTANCE.bus());
            Image moto300 = new Image(AzzeroCO2Resources.INSTANCE.moto());
            Image treno300 = new Image(AzzeroCO2Resources.INSTANCE.treno());
            NumberField busKm300 = new NumberField();
            busKm300.setName("busKm300");
            busKm300.setWidth(60);
            busKm300.setPropertyEditorType(Integer.class);

            Image automobile300 = new Image(AzzeroCO2Resources.INSTANCE.automobile());
            automobile300.setAltText("Auto");
            busKm300.setRegex("[0-9]+");
            busKm300.getMessages().setRegexText("Inserisci un numero intero");


            NumberField autoKm300 = new NumberField();
            autoKm300.setName("autoKm300");
            autoKm300.setWidth(60);
            autoKm300.setRegex("[0-9]+");
            autoKm300.getMessages().setRegexText("Inserisci un numero intero");
            autoKm300.setPropertyEditorType(Integer.class);


            NumberField trenoKm300 = new NumberField();
            trenoKm300.setName("trenoKm300");
            trenoKm300.setWidth(60);
            trenoKm300.setRegex("[0-9]+");
            trenoKm300.getMessages().setRegexText("Inserisci un numero intero");
            trenoKm300.setPropertyEditorType(Integer.class);


            NumberField motoKm300 = new NumberField();
            motoKm300.setName("motoKm300");
            motoKm300.setWidth(60);
            motoKm300.setRegex("[0-9]+");
            motoKm300.getMessages().setRegexText("Inserisci un numero intero");
            motoKm300.setPropertyEditorType(Integer.class);


            km300input.add(bus300);
            km300input.add(busKm300, flex);
            km300input.add(automobile300);
            km300input.add(autoKm300, flex);
            km300input.add(treno300);
            km300input.add(trenoKm300, flex);
            km300input.add(moto300);
            km300input.add(motoKm300, flex);


            formPanel.add(km300input, new FormData("100%"));
        }
        {
            LayoutContainer km1000 = new LayoutContainer();
            HBoxLayout layoutRigaKm1000 = new HBoxLayout();
            layoutRigaKm1000.setPadding(new Padding(2));
            layoutRigaKm1000.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.MIDDLE);
            km1000.setLayout(layoutRigaKm1000);
            km1000.add(new LabelField("Distanza percorsa: nazionale"), flex);

            formPanel.add(km1000);
            LayoutContainer km1000input = new LayoutContainer();
            HBoxLayout layoutKm1000input = new HBoxLayout();
            layoutKm1000input.setPadding(new Padding(2));
            layoutKm1000input.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.MIDDLE);
            km1000input.setLayout(layoutKm1000input);

            Image bus1000 = new Image(AzzeroCO2Resources.INSTANCE.bus());
            NumberField busKm1000 = new NumberField();
            busKm1000.setName("busKm1000");
            busKm1000.setWidth(60);
            busKm1000.setRegex("[0-9]+");
            busKm1000.getMessages().setRegexText("Inserisci un numero intero");
            busKm1000.setPropertyEditorType(Integer.class);

            Image automobile1000 = new Image(AzzeroCO2Resources.INSTANCE.automobile());
            NumberField autoKm1000 = new NumberField();
            autoKm1000.setName("autoKm1000");
            autoKm1000.setWidth(60);
            autoKm1000.setRegex("[0-9]+");
            autoKm1000.getMessages().setRegexText("Inserisci un numero intero");
            autoKm1000.setPropertyEditorType(Integer.class);

            Image treno1000 = new Image(AzzeroCO2Resources.INSTANCE.treno());
            NumberField trenoKm1000 = new NumberField();
            trenoKm1000.setName("trenoKm1000");
            trenoKm1000.setWidth(60);
            trenoKm1000.setRegex("[0-9]+");
            trenoKm1000.getMessages().setRegexText("Inserisci un numero intero");
            trenoKm1000.setPropertyEditorType(Integer.class);

            Image aereo1000 = new Image(AzzeroCO2Resources.INSTANCE.aereo());
            NumberField aereoKm1000 = new NumberField();
            aereoKm1000.setName("aereoKm1000");
            aereoKm1000.setWidth(60);
            aereoKm1000.setRegex("[0-9]+");
            aereoKm1000.getMessages().setRegexText("Inserisci un numero intero");
            aereoKm1000.setPropertyEditorType(Integer.class);

            km1000input.add(bus1000);
            km1000input.add(busKm1000, flex);
            km1000input.add(automobile1000);
            km1000input.add(autoKm1000, flex);
            km1000input.add(treno1000);
            km1000input.add(trenoKm1000, flex);
            km1000input.add(aereo1000);
            km1000input.add(aereoKm1000, flex);
            formPanel.add(km1000input, new FormData("100%"));
        }
        {
            LayoutContainer km3000 = new LayoutContainer();
            HBoxLayout layoutRigaKm3000 = new HBoxLayout();
            layoutRigaKm3000.setPadding(new Padding(2));
            layoutRigaKm3000.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.MIDDLE);
            km3000.setLayout(layoutRigaKm3000);

            km3000.add(new LabelField("Distanza percorsa: europea"), flex);
            formPanel.add(km3000);

            LayoutContainer km3000input = new LayoutContainer();
            HBoxLayout layoutKm3000input = new HBoxLayout();
            layoutKm3000input.setPadding(new Padding(2));
            layoutKm3000input.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.MIDDLE);
            km3000input.setLayout(layoutKm3000input);

            Image bus3000 = new Image(AzzeroCO2Resources.INSTANCE.bus());
            NumberField busKm3000 = new NumberField();
            busKm3000.setName("busKm3000");
            busKm3000.setWidth(60);
            busKm3000.setRegex("[0-9]+");
            busKm3000.getMessages().setRegexText("Inserisci un numero intero");
            busKm3000.setPropertyEditorType(Integer.class);

            Image automobile3000 = new Image(AzzeroCO2Resources.INSTANCE.automobile());
            NumberField autoKm3000 = new NumberField();
            autoKm3000.setName("autoKm3000");
            autoKm3000.setWidth(60);
            autoKm3000.setRegex("[0-9]+");
            autoKm3000.getMessages().setRegexText("Inserisci un numero intero");
            autoKm3000.setPropertyEditorType(Integer.class);

            Image treno3000 = new Image(AzzeroCO2Resources.INSTANCE.treno());
            NumberField trenoKm3000 = new NumberField();
            trenoKm3000.setName("trenoKm3000");
            trenoKm3000.setWidth(60);
            trenoKm3000.setRegex("[0-9]+");
            trenoKm3000.getMessages().setRegexText("Inserisci un numero intero");
            trenoKm3000.setPropertyEditorType(Integer.class);

            Image aereo3000 = new Image(AzzeroCO2Resources.INSTANCE.aereo());
            NumberField aereoKm3000 = new NumberField();
            aereoKm3000.setName("aereoKm3000");
            aereoKm3000.setWidth(60);
            aereoKm3000.setRegex("[0-9]+");
            aereoKm3000.getMessages().setRegexText("Inserisci un numero intero");
            aereoKm3000.setPropertyEditorType(Integer.class);

            km3000input.add(bus3000);
            km3000input.add(busKm3000, flex);
            km3000input.add(automobile3000);
            km3000input.add(autoKm3000, flex);
            km3000input.add(treno3000);
            km3000input.add(trenoKm3000, flex);
            km3000input.add(aereo3000);
            km3000input.add(aereoKm3000, flex);
            formPanel.add(km3000input);
        }

        {
            LayoutContainer km9000 = new LayoutContainer();
            HBoxLayout layoutRigaKm9000 = new HBoxLayout();
            layoutRigaKm9000.setPadding(new Padding(2));
            layoutRigaKm9000.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.MIDDLE);
            km9000.setLayout(layoutRigaKm9000);

            km9000.add(new LabelField("Distanza percorsa: extra europea"), flex);
            formPanel.add(km9000);

            LayoutContainer km9000input = new LayoutContainer();
            HBoxLayout layoutKm9000input = new HBoxLayout();
            layoutKm9000input.setPadding(new Padding(2));
            layoutKm9000input.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.MIDDLE);
            km9000input.setLayout(layoutKm9000input);

            Image aereo9000 = new Image(AzzeroCO2Resources.INSTANCE.aereo());
            NumberField aereoKm9000 = new NumberField();
            aereoKm9000.setName("aereoKm9000");
            aereoKm9000.setWidth(60);
            aereoKm9000.setRegex("[0-9]+");
            aereoKm9000.getMessages().setRegexText("Inserisci un numero intero");
            aereoKm9000.setPropertyEditorType(Integer.class);

            km9000input.add(aereo9000);
            km9000input.add(aereoKm9000, flex);
            formPanel.add(km9000input);
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
        column.setWidth(160);
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
                        if (trasportoPersoneModel.getModels().size() == 1) {
                            Info.display("Info", "<ul><li>Impossibile Eliminare tutte le categorie </li></ul>");
                        } else {
                            Info.display("Info", "<ul><li>Eliminata: " + model.getCategoria() + "</li></ul>");
                            formBindings.unbind();
                            panel.setHeading("Aggiungi una Categoria o Personalizza quelle esistenti");
                            trasportoPersoneModel.remove(model);
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

        final RowEditor<BeanModel> re = new RowEditor<BeanModel>();
        re.getMessages().setSaveText("Salva");
        re.getMessages().setCancelText("Annulla");
        re.setClicksToEdit(EditorGrid.ClicksToEdit.TWO);

        final ColumnModel cm = new ColumnModel(configs);
        grid = new Grid<TrasportoPersoneModel>(trasportoPersoneModel, cm);

      //  grid.setAutoExpandColumn("categoria");
        grid.setColumnResize(true);
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


    public void clear(boolean setDefault) {

        trasportoPersoneModel.removeAll();
        if (grid != null) {
            grid.getSelectionModel().bind(trasportoPersoneModel);
        }
        if (setDefault) {
            setDefault();
        }

    }

    private void setDefault() {
        TrasportoPersoneModel tpm = new TrasportoPersoneModel();
        tpm.setCategoria("Tutte le persone");
        TrasportoPersoneModel tpmRealtori = new TrasportoPersoneModel();
        tpmRealtori.setCategoria("Relatori");
        TrasportoPersoneModel tpmStaff = new TrasportoPersoneModel();
        tpmStaff.setCategoria("Staff");
        TrasportoPersoneModel tpmSpettatori = new TrasportoPersoneModel();
        tpmSpettatori.setCategoria("Spettatori");
        TrasportoPersoneModel tpmFornitori = new TrasportoPersoneModel();
        tpmFornitori.setCategoria("Fornitori");

        trasportoPersoneModel.add(tpm);
        trasportoPersoneModel.add(tpmRealtori);
        trasportoPersoneModel.add(tpmSpettatori);
        trasportoPersoneModel.add(tpmStaff);
        trasportoPersoneModel.add(tpmFornitori);
        formBindings.bind(tpm);
        if (grid != null) {
            grid.getSelectionModel().bind(trasportoPersoneModel);
        }
    }

    public ArrayList<TrasportoPersoneModel> getTrasportoPersoneModel() {
        return (ArrayList) trasportoPersoneModel.getModels();
    }

    public void setTrasportoPersoneModel(List<TrasportoPersoneModel> trasportoPersoneModel) {
        clear(false);
        this.trasportoPersoneModel.add(trasportoPersoneModel);
        if (trasportoPersoneModel.size() == 0) {
            setDefault();
        }
        if (grid != null) {
            grid.getSelectionModel().select(trasportoPersoneModel.get(0), true);
        }
    }
}

