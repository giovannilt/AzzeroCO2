package it.agilis.mens.azzeroCO2.client.forms;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.binding.FormBinding;
import com.extjs.gxt.ui.client.event.*;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.util.Padding;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.*;
import com.extjs.gxt.ui.client.widget.grid.*;
import com.extjs.gxt.ui.client.widget.layout.*;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.user.client.Element;
import it.agilis.mens.azzeroCO2.shared.model.GrammaturaDiCarta;
import it.agilis.mens.azzeroCO2.shared.model.TipoDiCarta;
import it.agilis.mens.azzeroCO2.shared.model.evento.PubblicazioniRilegateModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 7/2/11
 * Time: 12:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class EventoFormPubblicazioniRilegateNEW extends LayoutContainer {
    private ListStore<PubblicazioniRilegateModel> storeCustom;
    private ToolBar toolBar = new ToolBar();

    public EventoFormPubblicazioniRilegateNEW(ListStore<PubblicazioniRilegateModel> storeCustom) {
        this.storeCustom = storeCustom;
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

        final Grid<PubblicazioniRilegateModel> grid = createGrid();

        ContentPanel cpEst = new ContentPanel();
        cpEst.setHeading("Si tratta di gruppi di pagineriunite <br> in un volume. Seleziona un tipo di <br>pubblicazione.<br>Puoi inserire piu' di una pubblicazione e aggiungere altre categorie.");
        cpEst.setFrame(true);
        cpEst.setLayout(new RowLayout(Style.Orientation.HORIZONTAL));
        cpEst.add(grid, new RowData(1, 1));

        cpEst.setBottomComponent(toolBar);
        cpEst.setButtonAlign(Style.HorizontalAlignment.CENTER);

        cp.add(cpEst, new RowData(.3, 1));

        final FormPanel panel = createForm();
        cp.add(panel, new RowData(.7, 1));

        final FormBinding formBindings = new FormBinding(panel, true);
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
    }

    private FormPanel createForm() {
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

            c2.add(new LabelField("Definisci le caratteristiche della pubblicazione."), flex);

            panel.add(c2);
        }
        { // Dimensioni
            {
                LayoutContainer c = new LayoutContainer();
                HBoxLayout layout = new HBoxLayout();
                layout.setPadding(new Padding(10));
                layout.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.BOTTOM);
                c.setLayout(layout);

                NumberField altezza = new NumberField();
                altezza.setWidth(60);
                altezza.setName("dimensioni");

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
                layout.setPadding(new Padding(10));
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
                layout.setPadding(new Padding(10));
                layout.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.BOTTOM);
                c.setLayout(layout);

                ListStore<TipoDiCarta> store = new ListStore<TipoDiCarta>();
                { //TODO
                    store.add(new TipoDiCarta("TIPO1"));
                    store.add(new TipoDiCarta("TIPO2"));

                }

                ComboBox<TipoDiCarta> tipoDiCarta = new ComboBox<TipoDiCarta>();
                tipoDiCarta.setDisplayField("dipoDiCarta");
                tipoDiCarta.setTriggerAction(ComboBox.TriggerAction.ALL);
                tipoDiCarta.setStore(store);

                LabelField label = new LabelField("Materiale ");
                label.setWidth(100);
                c.add(label);
                c.add(tipoDiCarta, flex);

                panel.add(c, new FormData("100%"));
            }
            {
                LayoutContainer c = new LayoutContainer();
                HBoxLayout layout = new HBoxLayout();
                layout.setPadding(new Padding(10));
                layout.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.BOTTOM);
                c.setLayout(layout);

                ListStore<GrammaturaDiCarta> store = new ListStore<GrammaturaDiCarta>();
                { //TODO
                    store.add(new GrammaturaDiCarta("TIPO1"));
                    store.add(new GrammaturaDiCarta("TIPO2"));
                }

                ComboBox<GrammaturaDiCarta> grammaturaDiCarta = new ComboBox<GrammaturaDiCarta>();
                grammaturaDiCarta.setDisplayField("dipoDiCarta");
                grammaturaDiCarta.setTriggerAction(ComboBox.TriggerAction.ALL);
                grammaturaDiCarta.setStore(store);
                c.add(grammaturaDiCarta, flex);

                LabelField label = new LabelField("");
                label.setWidth(100);

                c.add(label);
                c.add(grammaturaDiCarta);
                panel.add(c, new FormData("100%"));
            }
        }

        { // Quantita'
            {
                LayoutContainer c = new LayoutContainer();
                HBoxLayout layout = new HBoxLayout();
                layout.setPadding(new Padding(10));
                layout.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.BOTTOM);
                c.setLayout(layout);

                NumberField numeroDiPagine = new NumberField();
                numeroDiPagine.setWidth(60);
                numeroDiPagine.setName("quantita");

                LabelField label = new LabelField("Quantita' ");
                label.setWidth(100);
                c.add(label);
                c.add(numeroDiPagine, flex);
                c.add(new LabelField("numero di pagine"), flex);

                panel.add(c, new FormData("100%"));
            }
            {
                LayoutContainer c = new LayoutContainer();
                HBoxLayout layout = new HBoxLayout();
                layout.setPadding(new Padding(10));
                layout.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.BOTTOM);
                c.setLayout(layout);

                NumberField taratura = new NumberField();
                taratura.setWidth(60);
                taratura.setName("taratura");

                LabelField label = new LabelField("");
                label.setWidth(100);
                c.add(label);
                c.add(taratura, flex);
                c.add(new LabelField("taratura"), flex);

                panel.add(c, new FormData("100%"));
            }
        }
        {   // Copertina
            {
                LayoutContainer c = new LayoutContainer();
                HBoxLayout layout = new HBoxLayout();
                layout.setPadding(new Padding(10));
                layout.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.BOTTOM);
                c.setLayout(layout);

                ListStore<TipoDiCarta> store = new ListStore<TipoDiCarta>();
                { //TODO
                    store.add(new TipoDiCarta("TIPO1"));
                    store.add(new TipoDiCarta("TIPO2"));

                }

                ComboBox<TipoDiCarta> tipoDiCarta = new ComboBox<TipoDiCarta>();
                tipoDiCarta.setDisplayField("dipoDiCarta");
                tipoDiCarta.setTriggerAction(ComboBox.TriggerAction.ALL);
                tipoDiCarta.setStore(store);

                LabelField label = new LabelField("Materiale ");
                label.setWidth(100);
                c.add(label);
                c.add(tipoDiCarta, flex);

                panel.add(c, new FormData("100%"));
            }
            {
                LayoutContainer c = new LayoutContainer();
                HBoxLayout layout = new HBoxLayout();
                layout.setPadding(new Padding(10));
                layout.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.BOTTOM);
                c.setLayout(layout);

                ListStore<GrammaturaDiCarta> store = new ListStore<GrammaturaDiCarta>();
                { //TODO
                    store.add(new GrammaturaDiCarta("TIPO1"));
                    store.add(new GrammaturaDiCarta("TIPO2"));
                }

                ComboBox<GrammaturaDiCarta> grammaturaDiCarta = new ComboBox<GrammaturaDiCarta>();
                grammaturaDiCarta.setDisplayField("dipoDiCarta");
                grammaturaDiCarta.setTriggerAction(ComboBox.TriggerAction.ALL);
                grammaturaDiCarta.setStore(store);
                c.add(grammaturaDiCarta, flex);

                LabelField label = new LabelField("");
                label.setWidth(100);

                c.add(label);
                c.add(grammaturaDiCarta);
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
        column.setEditor(new CellEditor(text));
        configs.add(column);

        final RowEditor<PubblicazioniRilegateModel> re = new RowEditor<PubblicazioniRilegateModel>();
        re.getMessages().setSaveText("Salva");
        re.getMessages().setCancelText("Annulla");
        re.setClicksToEdit(EditorGrid.ClicksToEdit.TWO);

        final ColumnModel cm = new ColumnModel(configs);
        final Grid<PubblicazioniRilegateModel> grid = new Grid<PubblicazioniRilegateModel>(storeCustom, cm);

        grid.setAutoExpandColumn("categoria");
        grid.setBorders(true);
        grid.addPlugin(re);
        grid.setHideHeaders(true);

        Button add = new Button("Aggiungi categoria");
        add.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                PubblicazioniRilegateModel cate = new PubblicazioniRilegateModel("Nuova Categoria");
                re.stopEditing(false);
                storeCustom.insert(cate, 0);
                re.startEditing(storeCustom.indexOf(cate), true);
            }
        });
        toolBar.add(add);
        return grid;
    }

    public void clear() {
    }
}