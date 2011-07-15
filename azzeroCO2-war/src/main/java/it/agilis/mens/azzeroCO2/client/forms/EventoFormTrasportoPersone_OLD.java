package it.agilis.mens.azzeroCO2.client.forms;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.binding.FormBinding;
import com.extjs.gxt.ui.client.data.BeanModel;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.*;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.util.Padding;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.ToolButton;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.*;
import com.extjs.gxt.ui.client.widget.layout.*;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Image;
import it.agilis.mens.azzeroCO2.client.AzzeroCO2Resources;

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
public class EventoFormTrasportoPersone_OLD extends TabItem {

    private ContentPanel west = new ContentPanel();
    private ContentPanel centre = new ContentPanel();

    private FormBinding binding;
    private FormPanel formPanel = new FormPanel();
    private ListStore<BeanModel> storeTutteLePersone = new ListStore<BeanModel>();
    private ListStore<BeanModel> storeCustom = new ListStore<BeanModel>();

    public EventoFormTrasportoPersone_OLD(ListStore<BeanModel> storeTutteLePersone, ListStore<BeanModel> storeCustom) {
        this.storeTutteLePersone = storeTutteLePersone;
        this.storeCustom = storeCustom;
    }

    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);

        BorderLayout layout = new BorderLayout();
        setLayout(layout);
        layout.setEnableState(false);


        createCentre();
        binding = new FormBinding(formPanel);
        centre.setHeading("/ Tutte le Persone");
        centre.getHeader().addTool(new ToolButton("x-tool-help"));
        centre.getHeader().addTool(new ToolButton("x-tool-close"));
        BorderLayoutData centerData = new BorderLayoutData(Style.LayoutRegion.CENTER);
        centerData.setMargins(new Margins(0));
        add(centre, centerData);

        createWest();
        west.setHeading("Trasporto Persone");
        BorderLayoutData westData = new BorderLayoutData(Style.LayoutRegion.WEST, 300);
        westData.setMargins(new Margins(0));
        add(west, westData);


    }

    private void createWest() {
        {
            List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

            ColumnConfig column = new ColumnConfig();
            column.setId("name");
            column.setWidth(299);
            column.setRowHeader(false);

            TextField<String> text = new TextField<String>();
            text.setAllowBlank(false);
            column.setEditor(new CellEditor(text));
            configs.add(column);

            ColumnModel cm = new ColumnModel(configs);

            ContentPanel cp = new ContentPanel();
            cp.setHeading("Opzione A: scegli questa opzione per catalogare il numerodi persone totaliche si sono spostate per l'evento ");
            cp.setFrame(true);
            cp.setSize(299, 100);
            cp.setLayout(new FitLayout());


            final Grid grid = new Grid<BeanModel>(storeTutteLePersone, cm);
            grid.getSelectionModel().addListener(Events.SelectionChange,
                    new Listener<SelectionChangedEvent<BeanModel>>() {
                        public void handleEvent(SelectionChangedEvent<BeanModel> be) {
                            if (be.getSelection().size() > 0) {
                                ModelData selectedModel = (ModelData) be.getSelection().get(0);
                                binding.bind(selectedModel);
                            } else {
                                binding.unbind();
                            }
                        }
                    });
            grid.setAutoExpandColumn("name");
            grid.setBorders(false);
            grid.setHideHeaders(true);
            //   grid.getAriaSupport().setLabelledBy(cp.getHeader().getId() + "-label");
            cp.add(grid);
            // cp.setButtonAlign(Style.HorizontalAlignment.CENTER);
            west.add(cp);

        }

        {
            List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

            ColumnConfig column = new ColumnConfig();
            column.setRowHeader(false);
            column.setId("name");
            column.setWidth(299);

            TextField<String> text = new TextField<String>();
            text.setAllowBlank(false);
            column.setEditor(new CellEditor(text));
            configs.add(column);

            ColumnModel cm = new ColumnModel(configs);

            ContentPanel cp = new ContentPanel();
            cp.setHeading("Opzione B: se invece vuoi dettagliare i trasporti per categoria di persone, compila queste categorie: ");
            cp.setFrame(true);
            cp.setSize(299, 300);
            cp.setLayout(new FitLayout());

            final RowEditor<BeanModel> re = new RowEditor<BeanModel>();
            re.getMessages().setSaveText("Salva");
            re.getMessages().setCancelText("Annulla");
            re.setClicksToEdit(EditorGrid.ClicksToEdit.TWO);

            final Grid<BeanModel> grid = new Grid<BeanModel>(storeCustom, cm);
            grid.setAutoExpandColumn("name");
            grid.setBorders(true);
            grid.addPlugin(re);
            grid.setHideHeaders(true);
            grid.getSelectionModel().setSelectionMode(Style.SelectionMode.SINGLE);

            grid.getSelectionModel().addListener(Events.SelectionChange,
                    new Listener<SelectionChangedEvent<BeanModel>>() {
                        public void handleEvent(SelectionChangedEvent<BeanModel> be) {
                            if (be.getSelection().size() > 0) {
                                ModelData selectedModel = (ModelData) be.getSelection().get(0);
                                binding.bind(selectedModel);
                            } else {
                                binding.unbind();
                            }
                        }
                    });

            cp.add(grid);

            ToolBar toolBar = new ToolBar();
            Button add = new Button("Aggiungi categoria");
            add.addSelectionListener(new SelectionListener<ButtonEvent>() {

                @Override
                public void componentSelected(ButtonEvent ce) {
                   /* EventoCategoriePersoneDTO cate = new EventoCategoriePersoneDTO("custom");

                    re.stopEditing(false);
                    storeCustom.insert(beanModelFactory.createModel(cate), 0);
                    re.startEditing(storeCustom.indexOf(beanModelFactory.createModel(cate)), true);
*/
                }

            });

            toolBar.add(add);
            cp.setBottomComponent(toolBar);
            cp.setButtonAlign(Style.HorizontalAlignment.CENTER);
            west.add(cp);
            binding.setStore(grid.getStore());
        }
    }

    private void createCentre() {

        //    FormData formData = new FormData("100%");

        formPanel.setFrame(true);
        formPanel.setHeaderVisible(false);

        centre.add(formPanel);

        formPanel.setHeight(600);
        formPanel.setLabelAlign(FormPanel.LabelAlign.LEFT);

        HBoxLayoutData flex = new HBoxLayoutData(new Margins(0, 5, 0, 0));
        LayoutContainer c2 = new LayoutContainer();
        HBoxLayout layout2 = new HBoxLayout();
        layout2.setPadding(new Padding(10));
        layout2.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.MIDDLE);
        c2.setLayout(layout2);

        c2.add(new LabelField("Inserisci il numero di tratte per distanza percorsa e mezzo di trasporto.<br> Es: due pendolari in treno che partecipano a un evento di 4 giorni = 16 tratte."), flex);
        formPanel.add(c2);

        //HBoxLayoutData flex = new HBoxLayoutData(new Margins(0, 5, 0, 0));
        LayoutContainer piu50 = new LayoutContainer();
        HBoxLayout layoutRigaPiu50 = new HBoxLayout();
        layoutRigaPiu50.setPadding(new Padding(10));
        //layoutRigaPiu50.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.MIDDLE);
        piu50.setLayout(layoutRigaPiu50);

        piu50.add(new LabelField("Distanza percorsa >50 km"), flex);

        formPanel.add(piu50);


        LayoutContainer piu50input = new LayoutContainer();
        HBoxLayout layoutPiu50input = new HBoxLayout();
        layoutPiu50input.setPadding(new Padding(10));
        layoutPiu50input.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.MIDDLE);
        piu50input.setLayout(layoutPiu50input);


        Image bus = new Image(AzzeroCO2Resources.INSTANCE.bus());

        bus.setAltText("Bus");
        bus.setSize("46", "46");

        NumberField busPiu50 = new NumberField();
        busPiu50.setWidth(60);


        Image automobile = new Image(AzzeroCO2Resources.INSTANCE.automobile());
        bus.setAltText("Auto");
        automobile.setSize("46", "46");

        NumberField autoPiu50 = new NumberField();
        autoPiu50.setWidth(60);


        Image treno = new Image(AzzeroCO2Resources.INSTANCE.treno());
        bus.setAltText("Treno");
        treno.setSize("46", "46");

        NumberField trenoPiu50 = new NumberField();
        trenoPiu50.setWidth(60);


        piu50input.add(bus);
        piu50input.add(busPiu50, flex);

        piu50input.add(automobile);
        piu50input.add(autoPiu50, flex);

        piu50input.add(treno);
        piu50input.add(trenoPiu50, flex);

        layoutRigaPiu50.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.MIDDLE);
        formPanel.add(piu50input, new FormData("100%"));


        LayoutContainer piu100 = new LayoutContainer();
        HBoxLayout layoutRigaPiu100 = new HBoxLayout();
        layoutRigaPiu100.setPadding(new Padding(10));
        layoutRigaPiu100.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.MIDDLE);
        piu100.setLayout(layoutRigaPiu100);

        piu100.add(new LabelField("Distanza percorsa >100 km"), flex);

        formPanel.add(piu100);


        LayoutContainer piu100input = new LayoutContainer();
        HBoxLayout layoutPiu100input = new HBoxLayout();
        layoutPiu100input.setPadding(new Padding(10));
        layoutPiu100input.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.MIDDLE);
        piu100input.setLayout(layoutPiu100input);


        Image bus100 = new Image(AzzeroCO2Resources.INSTANCE.bus());
        bus100.setAltText("Bus");
        bus100.setSize("46", "46");

        NumberField busPiu100 = new NumberField();
        busPiu100.setWidth(60);


        Image automobile100 = new Image(AzzeroCO2Resources.INSTANCE.automobile());
        automobile100.setAltText("Auto");
        automobile100.setSize("46", "46");

        NumberField autoPiu100 = new NumberField();
        autoPiu100.setWidth(60);


        Image treno100 = new Image(AzzeroCO2Resources.INSTANCE.treno());
        treno100.setAltText("Treno");
        treno100.setSize("46", "46");

        NumberField trenoPiu100 = new NumberField();
        trenoPiu100.setWidth(60);


        piu100input.add(bus100);
        piu100input.add(busPiu100, flex);

        piu100input.add(automobile100);
        piu100input.add(autoPiu100, flex);

        piu100input.add(treno100);
        piu100input.add(trenoPiu100, flex);

        formPanel.add(piu100input, new FormData("100%"));


        LayoutContainer piu250 = new LayoutContainer();
        HBoxLayout layoutRigaPiu250 = new HBoxLayout();
        layoutRigaPiu250.setPadding(new Padding(10));
        layoutRigaPiu250.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.MIDDLE);
        piu250.setLayout(layoutRigaPiu250);

        piu250.add(new LabelField("Distanza percorsa >250 km"), flex);

        formPanel.add(piu250);


        LayoutContainer piu250input = new LayoutContainer();
        HBoxLayout layoutPiu250input = new HBoxLayout();
        layoutPiu250input.setPadding(new Padding(10));
        layoutPiu250input.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.MIDDLE);
        piu250input.setLayout(layoutPiu250input);


        Image bus250 = new Image(AzzeroCO2Resources.INSTANCE.bus());
        bus250.setAltText("Bus");
        bus250.setSize("46", "46");

        NumberField busPiu250 = new NumberField();
        busPiu250.setWidth(60);


        Image automobile250 = new Image(AzzeroCO2Resources.INSTANCE.automobile());
        automobile250.setAltText("Auto");
        automobile250.setSize("46", "46");

        NumberField autoPiu250 = new NumberField();
        autoPiu250.setWidth(60);


        Image treno250 = new Image(AzzeroCO2Resources.INSTANCE.treno());
        treno250.setAltText("Treno");
        treno250.setSize("46", "46");

        NumberField trenoPiu250 = new NumberField();
        trenoPiu250.setWidth(60);


        Image aereo250 = new Image(AzzeroCO2Resources.INSTANCE.aereo());
        aereo250.setAltText("Aereo");
        aereo250.setSize("46", "46");

        NumberField aereoPiu250 = new NumberField();
        aereoPiu250.setWidth(60);


        piu250input.add(bus250);
        piu250input.add(busPiu250, flex);

        piu250input.add(automobile250);
        piu250input.add(autoPiu250, flex);

        piu250input.add(treno250);
        piu250input.add(trenoPiu250, flex);

        piu250input.add(aereo250);
        piu250input.add(aereoPiu250, flex);


        formPanel.add(piu250input, new FormData("100%"));


        LayoutContainer piu500 = new LayoutContainer();
        HBoxLayout layoutRigaPiu500 = new HBoxLayout();
        layoutRigaPiu500.setPadding(new Padding(10));
        layoutRigaPiu500.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.MIDDLE);
        piu500.setLayout(layoutRigaPiu500);

        piu500.add(new LabelField("Distanza percorsa >500 km"), flex);

        formPanel.add(piu500);

        LayoutContainer piu500input = new LayoutContainer();
        HBoxLayout layoutPiu500input = new HBoxLayout();
        layoutPiu500input.setPadding(new Padding(10));
        layoutPiu500input.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.MIDDLE);
        piu500input.setLayout(layoutPiu500input);


        Image bus500 = new Image(AzzeroCO2Resources.INSTANCE.bus());
        bus500.setAltText("Bus");
        bus500.setSize("46", "46");

        NumberField busPiu500 = new NumberField();
        busPiu500.setWidth(60);


        Image automobile500 = new Image(AzzeroCO2Resources.INSTANCE.automobile());
        automobile500.setAltText("Auto");
        automobile500.setSize("46", "46");

        NumberField autoPiu500 = new NumberField();
        autoPiu500.setWidth(60);


        Image treno500 =
                new Image(AzzeroCO2Resources.INSTANCE.treno());
        treno500.setAltText("Treno");
        treno500.setSize("46", "46");

        NumberField trenoPiu500 = new NumberField();
        trenoPiu500.setWidth(60);


        Image aereo500 = new Image(AzzeroCO2Resources.INSTANCE.aereo());
        aereo500.setAltText("Aereo");
        aereo500.setSize("46", "46");

        NumberField aereoPiu500 = new NumberField();
        aereoPiu500.setWidth(60);


        piu500input.add(bus500);
        piu500input.add(busPiu500, flex);

        piu500input.add(automobile500);
        piu500input.add(autoPiu500, flex);

        piu500input.add(treno500);
        piu500input.add(trenoPiu500, flex);

        piu500input.add(aereo500);
        piu500input.add(aereoPiu500, flex);


        formPanel.add(piu500input, new FormData("100%"));


    }

    public void clear() {
        /*storeTutteLePersone.remove(0);
        storeTutteLePersone.add(beanModelFactory.createModel(new EventoCategoriePersoneDTO("tutte le persone")));

        for (int i = 0; i < storeCustom.getModels().size(); i++) {
            storeCustom.remove(i);
        }
        storeCustom.add(beanModelFactory.createModel(new EventoCategoriePersoneDTO("relatori")));
        storeCustom.add(beanModelFactory.createModel(new EventoCategoriePersoneDTO("spettatori")));
        storeCustom.add(beanModelFactory.createModel(new EventoCategoriePersoneDTO("staff")));
        storeCustom.add(beanModelFactory.createModel(new EventoCategoriePersoneDTO("fornitori")));
*/
        binding.unbind();
    }
}
