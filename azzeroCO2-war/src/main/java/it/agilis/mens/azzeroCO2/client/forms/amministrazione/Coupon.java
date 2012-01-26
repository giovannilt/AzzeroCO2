package it.agilis.mens.azzeroCO2.client.forms.amministrazione;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.*;
import com.extjs.gxt.ui.client.widget.grid.*;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Element;
import it.agilis.mens.azzeroCO2.client.mvc.events.AmministrazioneEvents;
import it.agilis.mens.azzeroCO2.shared.model.amministrazione.CouponModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 6/19/11
 * Time: 5:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class Coupon extends LayoutContainer {

    private ListStore<CouponModel> store = new ListStore<CouponModel>();

    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);

        BorderLayout layout = new BorderLayout();
        setLayout(layout);

        ContentPanel centre = createCentre(store);
        //  centre.setHeading("Coupon");
        centre.setHeaderVisible(false);
        centre.setHeight(700);
        centre.setFrame(true);

        BorderLayoutData centerData = new BorderLayoutData(Style.LayoutRegion.CENTER);
        centerData.setMargins(new Margins(0));
        add(centre, centerData);


    }

    private ContentPanel createCentre(final ListStore<CouponModel> store) {
        ContentPanel centre = new ContentPanel();

        /*  PagingModelMemoryProxy proxy = new PagingModelMemoryProxy(CouponModel.class);

                // loader
                PagingLoader<PagingLoadResult<ModelData>> loader = new BasePagingLoader<PagingLoadResult<ModelData>>(proxy);
                loader.setRemoteSort(true);


                final PagingToolBar toolBar = new PagingToolBar(10);
                toolBar.bind(loader);

                loader.load(0, 10);
        */

        final NumberFormat number = NumberFormat.getFormat("0.00");

        List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

        ColumnConfig column = new ColumnConfig("codice", "Codice", 100);
        TextField<String> textCodice = new TextField<String>();
        column.setEditor(new CellEditor(textCodice));
        configs.add(column);

        column = new ColumnConfig("descrizione", "Descrizione", 200);
        TextField<String> textDescr = new TextField<String>();
        column.setEditor(new CellEditor(textDescr));
        configs.add(column);


        final SimpleComboBox<String> combo = new SimpleComboBox<String>();
        //combo.setForceSelection(true);
        combo.setTriggerAction(ComboBox.TriggerAction.ALL);
        combo.add("%");
        combo.add("€");
        combo.add("Omaggio");

        CellEditor editorCombo = new CellEditor(combo) {
            @Override
            public Object preProcessValue(Object value) {
                if (value == null) {
                    return value;
                }
                return combo.findModel(value.toString());
            }

            @Override
            public Object postProcessValue(Object value) {
                if (value == null) {
                    return value;
                }
                return ((ModelData) value).get("value");
            }
        };


        column = new ColumnConfig("tipo", "Tipo", 70);
        TextField<String> textTipo = new TextField<String>();
        column.setEditor(editorCombo);
        //column.setEditor(new CellEditor(new TextField()));
        configs.add(column);


        column = new ColumnConfig("valore", "Valore", 100);
        column.setAlignment(Style.HorizontalAlignment.RIGHT);
        column.setEditor(new CellEditor(new NumberField()));
        configs.add(column);

        DateField dateStart = new DateField();
        dateStart.getPropertyEditor().setFormat(DateTimeFormat.getFormat("dd/MM/y"));

        column = new ColumnConfig("dataInizio", "Inizio validita'", 100);
        column.setEditor(new CellEditor(dateStart));
        configs.add(column);

        DateField dateEndld = new DateField();
        dateStart.getPropertyEditor().setFormat(DateTimeFormat.getFormat("dd/MM/y"));

        column = new ColumnConfig("dataFine", "Fine Validità", 100);
        column.setEditor(new CellEditor(dateEndld));
        configs.add(column);

        CheckColumnConfig columnCh = new CheckColumnConfig("attivo", "Attivo", 55);
        CheckBox chAttivo = new CheckBox();
        columnCh.setEditor(new CellEditor(chAttivo));
        configs.add(columnCh);

        final RowEditor<CouponModel> re = new RowEditor<CouponModel>();
        re.getMessages().setSaveText("Salva");
        re.getMessages().setCancelText("Annulla");
        re.setClicksToEdit(EditorGrid.ClicksToEdit.TWO);


        ColumnModel cm = new ColumnModel(configs);
        final Grid<CouponModel> grid = new Grid<CouponModel>(store, cm);
        grid.setBorders(true);
        //  grid.setAutoHeight(true);
        grid.addPlugin(re);
        grid.setHeight(650);
        centre.add(grid);

        ToolBar toolbar = new ToolBar();
        Button add = new Button("Aggiungi coupon");
        add.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                CouponModel coup = new CouponModel("Codice", "Nuovo Coupon", "", 0.00, new Date(), new Date(), false);
                re.stopEditing(false);
                store.insert(coup, 0);
                re.startEditing(store.indexOf(coup), true);
            }
        });

        Button saveButton = new Button("Salva Coupons");
        saveButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                /*List<CouponModel> coupons = new ArrayList<CouponModel>();
                for (Record r : store.getModifiedRecords()) {
                    coupons.add((CouponModel) r.getModel());
                }*/
                Dispatcher.forwardEvent(AmministrazioneEvents.SaveCoupons, store.getModels());

            }
        });

        centre.setButtonAlign(Style.HorizontalAlignment.CENTER);
        /*  grid.getAriaSupport().setDescribedBy(toolBar.getId() + "-display");
          centre.setBottomComponent(toolBar);
        */
        toolbar.add(add);
        toolbar.add(saveButton);
        centre.setTopComponent(toolbar);

        return centre;

    }


    public void setCouponInStore(List<CouponModel> couponModels) {
        try {
            store.removeAll();
            store.add(couponModels);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clear() {
    }
}


