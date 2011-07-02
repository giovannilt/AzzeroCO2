package it.agilis.mens.azzeroCO2.client.forms;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.util.Padding;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.*;
import com.extjs.gxt.ui.client.widget.layout.*;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.user.client.Element;
import it.agilis.mens.azzeroCO2.shared.model.CategoriePersone;

import java.util.ArrayList;
import java.util.List;

//import com.extjs.gxt.samples.resources.client.Resources;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 6/19/11
 * Time: 5:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class EventoFormTrasportoPersone extends TabItem {

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
        west.setHeading("Trasporto Persone");
        BorderLayoutData westData = new BorderLayoutData(Style.LayoutRegion.WEST, 300);
        westData.setMargins(new Margins(0));
        add(west, westData);

        createCentre();
        centre.setHeading("Tutte Le Persone");
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


            final ListStore<CategoriePersone> store = new ListStore<CategoriePersone>();
            // TODO
            {
                store.add(new CategoriePersone("tutte le persone"));
            }

            ColumnModel cm = new ColumnModel(configs);

            ContentPanel cp = new ContentPanel();
            cp.setHeading("Opzione A: scegli questa opzione per catalogare il numerodi persone totaliche si sono spostate per l'evento ");
            cp.setFrame(true);
            cp.setSize(299, 98);
            cp.setLayout(new FitLayout());


            final Grid<CategoriePersone> grid = new Grid<CategoriePersone>(store, cm);

            grid.setAutoExpandColumn("name");
            grid.setBorders(true);
            grid.setHideHeaders(true);
            //   grid.getAriaSupport().setLabelledBy(cp.getHeader().getId() + "-label");
            cp.add(grid);

            cp.setButtonAlign(Style.HorizontalAlignment.CENTER);
            west.add(cp);
        }

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


            final ListStore<CategoriePersone> store = new ListStore<CategoriePersone>();
            // TODO
            {
                store.add(new CategoriePersone("relatori"));
                store.add(new CategoriePersone("spettatori"));
                store.add(new CategoriePersone("staff"));
                store.add(new CategoriePersone("fornitori"));
            }

            ColumnModel cm = new ColumnModel(configs);

            ContentPanel cp = new ContentPanel();
            cp.setHeading("Opzione B: se invece vuoi dettagliare i trasporti per categoria di persone, compila queste categorie: ");
            cp.setFrame(true);
            cp.setSize(299, 550);
            cp.setLayout(new FitLayout());

            final RowEditor<CategoriePersone> re = new RowEditor<CategoriePersone>();
            re.getMessages().setSaveText("Salva");
            re.getMessages().setCancelText("Annulla");


            re.setClicksToEdit(EditorGrid.ClicksToEdit.TWO);

            final Grid<CategoriePersone> grid = new Grid<CategoriePersone>(store, cm);

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
                    CategoriePersone cate = new CategoriePersone("custom");

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

        VerticalPanel vp;

        vp = new VerticalPanel();
        vp.setSpacing(10);
        centre.add(vp);

        FormData formData = new FormData("100%");
        FormPanel panel = new FormPanel();
        panel.setFrame(true);
        panel.setHeaderVisible(false);

        panel.setSize(530, -1);
        panel.setLabelAlign(FormPanel.LabelAlign.LEFT);

        HBoxLayoutData flex = new HBoxLayoutData(new Margins(0, 5, 0, 0));
        LayoutContainer c2 = new LayoutContainer();
        HBoxLayout layout2 = new HBoxLayout();
        layout2.setPadding(new Padding(10));
        layout2.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.BOTTOM);
        c2.setLayout(layout2);

        c2.add(new LabelField("Inserisci il numero di tratte per distanza percorsa e mezzo di trasporto.<br> Es: due pendolari in treno che partecipano a un evento di 4 giorni = 16 tratte."), flex);

        panel.add(c2);

        LayoutContainer c = new LayoutContainer();
        HBoxLayout layout = new HBoxLayout();
        layout.setPadding(new Padding(10));
        layout.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.BOTTOM);
        c.setLayout(layout);

        NumberField energiaElettrica = new NumberField();
        energiaElettrica.setWidth(60);
        energiaElettrica.setName("energiaElettrica");

        LabelField label = new LabelField("Energia Elettrica ");
        label.setWidth(100);
        c.add(label);
        c.add(energiaElettrica, flex);
        c.add(new LabelField("Kw/h"), flex);
        panel.add(c, new FormData("100%"));

        LayoutContainer c3 = new LayoutContainer();
        HBoxLayout layout3 = new HBoxLayout();
        layout3.setPadding(new Padding(10));
        layout3.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.BOTTOM);
        c3.setLayout(layout3);

        c3.add(new LabelField("Non riempire in caso di energia da fonti rinnovabili."), flex);

        panel.add(c3);


        LayoutContainer cGas = new LayoutContainer();
        HBoxLayout layoutGas = new HBoxLayout();
        layoutGas.setPadding(new Padding(10));
        layoutGas.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.BOTTOM);
        cGas.setLayout(layoutGas);

        NumberField gasMetano = new NumberField();
        gasMetano.setWidth(60);

        label = new LabelField("Gas Metano ");
        label.setWidth(100);
        cGas.add(label);
        cGas.add(gasMetano, flex);
        cGas.add(new LabelField("m<sup>3</sup>"), flex);

        panel.add(cGas, new FormData("100%"));

        LayoutContainer cGasoline = new LayoutContainer();
        HBoxLayout layoutGasoline = new HBoxLayout();
        layoutGasoline.setPadding(new Padding(10));
        layoutGasoline.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.BOTTOM);
        cGasoline.setLayout(layoutGasoline);

        NumberField gasolio = new NumberField();
        gasolio.setWidth(60);

        label = new LabelField("Gasolio ");
        label.setWidth(100);
        cGasoline.add(label);
        cGasoline.add(gasolio, flex);
        cGasoline.add(new LabelField("Litri"), flex);

        panel.add(cGasoline, new FormData("100%"));
        vp.add(panel);

    }
}

