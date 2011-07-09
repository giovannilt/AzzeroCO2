package it.agilis.mens.azzeroCO2.client.forms;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.binding.FormBinding;
import com.extjs.gxt.ui.client.data.BeanModel;
import com.extjs.gxt.ui.client.data.BeanModelFactory;
import com.extjs.gxt.ui.client.data.BeanModelLookup;
import com.extjs.gxt.ui.client.event.*;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.util.Padding;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
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
import it.agilis.mens.azzeroCO2.shared.model.Stock;

import java.util.ArrayList;
import java.util.List;

public class GridStoreBinding extends LayoutContainer {

   // private BeanModelFactory beanModelFactory = BeanModelLookup.get().getFactory(EventoCategoriePersoneDTO.class);
    private BeanModelFactory beanModelFactory = BeanModelLookup.get().getFactory(Stock.class);
    private ListStore<Stock> storeCustom = new ListStore<Stock>();
    private ToolBar toolBar = new ToolBar();

    public GridStoreBinding(ListStore<Stock> storeCustom) {
        this.storeCustom = storeCustom;
        this.storeCustom.setMonitorChanges(true);
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

        final Grid<Stock> grid = createGrid();

        ContentPanel cpEst = new ContentPanel();
        cpEst.setHeading("Opzione A: scegli questa opzione per catalogare il numerodi persone totaliche si sono spostate per l'evento");
        cpEst.setFrame(true);
        cpEst.setLayout(new RowLayout(Orientation.HORIZONTAL));
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
                new Listener<SelectionChangedEvent<BeanModel>>() {
                    public void handleEvent(SelectionChangedEvent<BeanModel> be) {
                        if (be.getSelection().size() > 0) {
                            //  grid.getStore().commitChanges();
                            formBindings.bind(be.getSelection().get(0));
                       //     panel.setHeading(be.getSelection().get(0).<EventoCategoriePersoneDTO>getBean().getName());
                            panel.setHeading(be.getSelection().get(0).<Stock>getBean().getName());

                        } else {
                            formBindings.unbind();
                        }
                    }
                });


        BorderLayoutData centerData = new BorderLayoutData(Style.LayoutRegion.CENTER);
        add(cp, centerData);
    }

    private FormPanel createForm() {

        FormPanel formPanel = new FormPanel();
        formPanel.setFrame(true);
        formPanel.getHeader().addTool(new ToolButton("x-tool-help"));
        formPanel.getHeader().addTool(new ToolButton("x-tool-close"));

        HBoxLayoutData flex = new HBoxLayoutData(new Margins(0, 5, 0, 0));

        {
            LayoutContainer cl = new LayoutContainer();
            HBoxLayout layout = new HBoxLayout();
            layout.setPadding(new Padding(10));
            layout.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.MIDDLE);
            cl.setLayout(layout);

            cl.add(new LabelField("Inserisci il numero di tratte per distanza percorsa e mezzo di trasporto.<br> Es: due pendolari in treno che partecipano a un evento di 4 giorni = 16 tratte."), flex);
            formPanel.add(cl);
        }
        {
            LayoutContainer piu50 = new LayoutContainer();
            HBoxLayout layoutRigaPiu50 = new HBoxLayout();
            layoutRigaPiu50.setPadding(new Padding(10));
            piu50.setLayout(layoutRigaPiu50);

            piu50.add(new LabelField("Distanza percorsa >50 km"), flex);
            {
                TextField<String> nomeEvento = new TextField<String>();
                nomeEvento.setFieldLabel("Nome dell'evento");
                nomeEvento.setName("name");
                piu50.add(nomeEvento);
            }
            formPanel.add(piu50);

            LayoutContainer piu50input = new LayoutContainer();
            HBoxLayout layoutPiu50input = new HBoxLayout();
            layoutPiu50input.setPadding(new Padding(10));
            layoutPiu50input.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.MIDDLE);
            piu50input.setLayout(layoutPiu50input);

            Image bus = new Image(AzzeroCO2Resources.INSTANCE.bus());
            NumberField busPiu50 = new NumberField();
            busPiu50.setName("busPiu50");
            busPiu50.setWidth(60);

            Image automobile = new Image(AzzeroCO2Resources.INSTANCE.automobile());
            NumberField autoPiu50 = new NumberField();
            autoPiu50.setName("autoPiu50");
            autoPiu50.setWidth(60);

            Image treno = new Image(AzzeroCO2Resources.INSTANCE.treno());
            NumberField trenoPiu50 = new NumberField();
            trenoPiu50.setName("trenoPiu50");
            trenoPiu50.setWidth(60);

            piu50input.add(bus);
            piu50input.add(busPiu50, flex);
            piu50input.add(automobile);
            piu50input.add(autoPiu50, flex);
            piu50input.add(treno);
            piu50input.add(trenoPiu50, flex);

            layoutRigaPiu50.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.MIDDLE);
            formPanel.add(piu50input);
        }
        {
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
            NumberField busPiu100 = new NumberField();
            busPiu100.setName("busPiu100");
            busPiu100.setWidth(60);
            Image automobile100 = new Image(AzzeroCO2Resources.INSTANCE.automobile());
            automobile100.setAltText("Auto");

            NumberField autoPiu100 = new NumberField();
            autoPiu100.setName("autoPiu100");
            autoPiu100.setWidth(60);
            Image treno100 = new Image(AzzeroCO2Resources.INSTANCE.treno());

            NumberField trenoPiu100 = new NumberField();
            trenoPiu100.setName("trenoPiu100");
            trenoPiu100.setWidth(60);
            piu100input.add(bus100);
            piu100input.add(busPiu100, flex);

            piu100input.add(automobile100);
            piu100input.add(autoPiu100, flex);
            piu100input.add(treno100);
            piu100input.add(trenoPiu100, flex);

            formPanel.add(piu100input, new FormData("100%"));
        }
        {
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
            NumberField busPiu250 = new NumberField();
            busPiu250.setName("busPiu250");
            busPiu250.setWidth(60);


            Image automobile250 = new Image(AzzeroCO2Resources.INSTANCE.automobile());
            NumberField autoPiu250 = new NumberField();
            autoPiu250.setName("autoPiu250");
            autoPiu250.setWidth(60);

            Image treno250 = new Image(AzzeroCO2Resources.INSTANCE.treno());
            NumberField trenoPiu250 = new NumberField();
            trenoPiu250.setName("trenoPiu250");
            trenoPiu250.setWidth(60);


            Image aereo250 = new Image(AzzeroCO2Resources.INSTANCE.aereo());
            NumberField aereoPiu250 = new NumberField();
            aereoPiu250.setName("aereoPiu250");
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
        }
        {
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
            NumberField busPiu500 = new NumberField();
            busPiu500.setName("busPiu500");
            busPiu500.setWidth(60);

            Image automobile500 = new Image(AzzeroCO2Resources.INSTANCE.automobile());
            NumberField autoPiu500 = new NumberField();
            autoPiu500.setName("autoPiu500");
            autoPiu500.setWidth(60);

            Image treno500 = new Image(AzzeroCO2Resources.INSTANCE.treno());
            NumberField trenoPiu500 = new NumberField();
            trenoPiu500.setName("trenoPiu500");
            trenoPiu500.setWidth(60);

            Image aereo500 = new Image(AzzeroCO2Resources.INSTANCE.aereo());
            NumberField aereoPiu500 = new NumberField();
            aereoPiu500.setName("aereoPiu500");
            aereoPiu500.setWidth(60);

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

    private Grid<Stock> createGrid() {
        List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

        ColumnConfig column = new ColumnConfig();
        column.setRowHeader(false);
        column.setId("name");
        column.setWidth(200);

        TextField<String> text = new TextField<String>();
        text.setAllowBlank(false);
        column.setEditor(new CellEditor(text));
        configs.add(column);

        column = new ColumnConfig();
        column.setRowHeader(false);
        column.setId("buspiu");
        column.setWidth(99);
        configs.add(column);

        final RowEditor<BeanModel> re = new RowEditor<BeanModel>();
        re.getMessages().setSaveText("Salva");
        re.getMessages().setCancelText("Annulla");
        re.setClicksToEdit(EditorGrid.ClicksToEdit.TWO);

        final ColumnModel cm = new ColumnModel(configs);
        final Grid<Stock> grid = new Grid<Stock>(storeCustom, cm);

        grid.setAutoExpandColumn("name");
        grid.setBorders(true);
        grid.addPlugin(re);
        grid.setHideHeaders(true);
        grid.getSelectionModel().setSelectionMode(Style.SelectionMode.SINGLE);

        Button add = new Button("Aggiungi categoria");
        add.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                //EventoCategoriePersoneDTO cate = new EventoCategoriePersoneDTO("custom");
                Stock cate = new Stock("custom",10);
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

