package it.agilis.mens.azzeroCO2.client.forms.amministrazione;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.*;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Element;
import it.agilis.mens.azzeroCO2.client.mvc.events.AmministrazioneEvents;
import it.agilis.mens.azzeroCO2.shared.model.amministrazione.ProgettoDiCompensazioneModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 6/19/11
 * Time: 5:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class ProgettiDiCompensazione extends LayoutContainer {

    final ListStore<ProgettoDiCompensazioneModel> store = new ListStore<ProgettoDiCompensazioneModel>();


    protected ProgettoDiCompensazioneModel createProgetto() {
        ProgettoDiCompensazioneModel progetto = new ProgettoDiCompensazioneModel();
        progetto.setName("Nuovo progetto");
        progetto.setAttivo(false);
        progetto.setPrezzo(17.6);
        progetto.setKgCO2(0.00);
        return progetto;
    }


    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);

        BorderLayout layout = new BorderLayout();
        setLayout(layout);

        ContentPanel centre = createCentre();
        centre.setHeaderVisible(false);
        //   centre.setHeading("Programmi Di Compensazione");
        centre.setHeight(637);
        centre.setFrame(true);

        BorderLayoutData centerData = new BorderLayoutData(Style.LayoutRegion.CENTER);
        centerData.setMargins(new Margins(0));
        add(centre, centerData);

    }

    private ContentPanel createCentre() {
        ContentPanel centre = new ContentPanel();

        /*    // add paging support for a local collection of models
                PagingModelMemoryProxy proxy = new PagingModelMemoryProxy(ProgettoDiCompensazioneModel.class);

                // loader
                PagingLoader<PagingLoadResult<ModelData>> loader = new BasePagingLoader<PagingLoadResult<ModelData>>(proxy);
                loader.setRemoteSort(true);

                final PagingToolBar toolBar = new PagingToolBar(10);
                toolBar.bind(loader);

                loader.load(0, 10);
        */

        final NumberFormat number = NumberFormat.getFormat("0.00");

        List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

        ColumnConfig column = new ColumnConfig("type", "Tipo progetto", 300);
        TextField<String> textTipoProg = new TextField<String>();
        column.setEditor(new CellEditor(textTipoProg));
        configs.add(column);

        column = new ColumnConfig("name", "Progetto", 200);
        TextField<String> textProg = new TextField<String>();
        column.setEditor(new CellEditor(textProg));
        configs.add(column);

        column = new ColumnConfig("kgCO2", "Prezzo kg/CO2", 100);
        column.setAlignment(Style.HorizontalAlignment.RIGHT);
        column.setEditor(new CellEditor(new NumberField()));
        configs.add(column);

        CheckColumnConfig columnCh = new CheckColumnConfig("attivo", "Attivo", 55);
        CellEditor checkBoxEditor = new CellEditor(new CheckBox());
        columnCh.setEditor(checkBoxEditor);
        configs.add(columnCh);

        final RowEditor<ProgettoDiCompensazioneModel> re = new RowEditor<ProgettoDiCompensazioneModel>();
        re.getMessages().setSaveText("Salva");
        re.getMessages().setCancelText("Annulla");
        re.setClicksToEdit(EditorGrid.ClicksToEdit.TWO);

        ColumnModel cm = new ColumnModel(configs);

        Grid<ProgettoDiCompensazioneModel> grid = new Grid<ProgettoDiCompensazioneModel>(store, cm);
        grid.setBorders(true);
        grid.setAutoHeight(true);
        grid.addPlugin(re);

        centre.add(grid);

        ToolBar toolbar = new ToolBar();
        Button add = new Button("Aggiungi progetto");
        add.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                ProgettoDiCompensazioneModel prog = new ProgettoDiCompensazioneModel("Progetto", 0.0, true, 0.0);
                re.stopEditing(false);
                store.insert(createProgetto(), 0);
                re.startEditing(store.indexOf(prog), true);
            }
        });

        Button saveButton = new Button("Salva");
        saveButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                /*List<CouponModel> coupons = new ArrayList<CouponModel>();
                for (Record r : store.getModifiedRecords()) {
                    coupons.add((CouponModel) r.getModel());
                }*/
                Dispatcher.forwardEvent(AmministrazioneEvents.SaveProgrammiDiCompensazione, store.getModels());

            }
        });
        centre.setButtonAlign(Style.HorizontalAlignment.CENTER);

        toolbar.add(add);
        toolbar.add(saveButton);
        centre.setTopComponent(toolbar);

        return centre;
    }

    public void setProgettiDicompensazioneInStore(List<ProgettoDiCompensazioneModel> progettiDiCompensazioneModels) {
        store.removeAll();
        store.add(progettiDiCompensazioneModels);
    }

    public void clear() {
    }
}


