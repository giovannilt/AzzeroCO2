package it.agilis.mens.azzeroCO2.client.forms;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.util.Padding;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.ToolButton;
import com.extjs.gxt.ui.client.widget.form.*;
import com.extjs.gxt.ui.client.widget.grid.*;
import com.extjs.gxt.ui.client.widget.layout.*;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.user.client.Element;
import it.agilis.mens.azzeroCO2.shared.model.CategoriePubblicazioni;
import it.agilis.mens.azzeroCO2.shared.model.GrammaturaDiCarta;
import it.agilis.mens.azzeroCO2.shared.model.TipoDiCarta;

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

    ContentPanel west = new ContentPanel();
    ContentPanel centre = new ContentPanel();


    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);

        BorderLayout layout = new BorderLayout();
        setLayout(layout);
        layout.setEnableState(false);
        setStyleAttribute("padding", "0px");

        createWest();
        west.setHeading("Pubblicazioni rilegate");
        BorderLayoutData westData = new BorderLayoutData(Style.LayoutRegion.WEST, 300);
        westData.setMargins(new Margins(0));
        add(west, westData);

        createCentre();
        centre.setHeading("/ catalogo");

        centre.getHeader().addTool(new ToolButton("x-tool-help"));
        centre.getHeader().addTool(new ToolButton("x-tool-close"));

        BorderLayoutData centerData = new BorderLayoutData(Style.LayoutRegion.CENTER);
        centerData.setMargins(new Margins(0));
        add(centre, centerData);

    }

    private void createWest() {
        {
            List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

            ColumnConfig column = new ColumnConfig();
            column.setRowHeader(false);
            column.setId("name");

            //column.setHeader("Common Name");
            column.setWidth(299);

            TextField<String> text = new TextField<String>();
            text.setAllowBlank(false);
            column.setEditor(new CellEditor(text));
            configs.add(column);


            final ListStore<CategoriePubblicazioni> store = new ListStore<CategoriePubblicazioni>();
            // TODO
            {
                store.add(new CategoriePubblicazioni("catalogo"));
                store.add(new CategoriePubblicazioni("bilancio"));
                store.add(new CategoriePubblicazioni("report"));
                store.add(new CategoriePubblicazioni("libro"));
            }

            ColumnModel cm = new ColumnModel(configs);

            ContentPanel cp = new ContentPanel();
            cp.setHeading("Si tratta di gruppi di pagine riunite in un volume. Seleziona un tipo di pubblicazione.<br> Puoi inserire piu' di un pubblicazione ed aggiungere altre categorie.");
            cp.setFrame(true);
            cp.setSize(299, 300);
            cp.setLayout(new FitLayout());

            final RowEditor<CategoriePubblicazioni> re = new RowEditor<CategoriePubblicazioni>();
            re.getMessages().setSaveText("Salva");
            re.getMessages().setCancelText("Annulla");


            re.setClicksToEdit(EditorGrid.ClicksToEdit.TWO);

            final Grid<CategoriePubblicazioni> grid = new Grid<CategoriePubblicazioni>(store, cm);

            grid.setAutoExpandColumn("name");
            grid.setBorders(true);
            grid.addPlugin(re);
            grid.setHideHeaders(true);
            // grid.getAriaSupport().setLabelledBy(cp.getHeader().getId() + "-label");
            cp.add(grid);

            ToolBar toolBar = new ToolBar();
            Button add = new Button("Aggiungi categoria");
            add.addSelectionListener(new SelectionListener<ButtonEvent>() {

                @Override
                public void componentSelected(ButtonEvent ce) {
                    CategoriePubblicazioni cate = new CategoriePubblicazioni("custom");

                    re.stopEditing(false);
                    store.insert(cate, 0);
                    re.startEditing(store.indexOf(cate), true);

                }

            });

            toolBar.add(add);
            cp.setBottomComponent(toolBar);
            cp.setButtonAlign(Style.HorizontalAlignment.CENTER);
            west.add(cp);
        }
    }

    private void createCentre() {


        FormData formData = new FormData("100%");
        FormPanel panel = new FormPanel();
        panel.setFrame(true);
        panel.setHeaderVisible(false);

        panel.setHeight(600);
        panel.setLabelAlign(FormPanel.LabelAlign.LEFT);
        HBoxLayoutData flex = new HBoxLayoutData(new Margins(0, 5, 0, 0));

        {
            LayoutContainer c2 = new LayoutContainer();
            HBoxLayout layout2 = new HBoxLayout();
            layout2.setPadding(new Padding(10));
            layout2.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.BOTTOM);
            c2.setLayout(layout2);

            c2.add(new LabelField("Inserisci il numero di tratte per distanza percorsa e mezzo di trasporto.<br> Es: due pendolari in treno che partecipano a un evento di 4 giorni = 16 tratte."), flex);

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
        centre.add(panel);

    }

    public void clear() {
    }
}