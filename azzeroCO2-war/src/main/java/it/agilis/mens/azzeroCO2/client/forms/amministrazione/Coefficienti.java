package it.agilis.mens.azzeroCO2.client.forms.amministrazione;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.data.PagingModelMemoryProxy;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.grid.*;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Element;
import it.agilis.mens.azzeroCO2.client.mvc.events.AmministrazioneEvents;
import it.agilis.mens.azzeroCO2.shared.model.amministrazione.CoefficienteModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 6/19/11
 * Time: 5:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class Coefficienti extends LayoutContainer {

    private ListStore<CoefficienteModel> store = new ListStore<CoefficienteModel>();

    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);

        BorderLayout layout = new BorderLayout();
        setLayout(layout);

        ContentPanel centre = createCentre();
        centre.setHeaderVisible(false);
        centre.setHeight(700);
        centre.setFrame(true);

        BorderLayoutData centerData = new BorderLayoutData(Style.LayoutRegion.CENTER);
        centerData.setMargins(new Margins(0));
        add(centre, centerData);

    }

    private ContentPanel createCentre() {
        ContentPanel centre = new ContentPanel();
        // add paging support for a local collection of models
        PagingModelMemoryProxy proxy = new PagingModelMemoryProxy(CoefficienteModel.class);

        /*  // loader
                PagingLoader<PagingLoadResult<ModelData>> loader = new BasePagingLoader<PagingLoadResult<ModelData>>(proxy);
                loader.setRemoteSort(true);
                final PagingToolBar toolBar = new PagingToolBar(10);
                toolBar.bind(loader);
                loader.load(0, 10);
        */
        final NumberFormat number = NumberFormat.getFormat("0.00");

        List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
        ColumnConfig column = new ColumnConfig("tipologia", "Tipologia", 300);
        configs.add(column);
        column = new ColumnConfig("nome", "Coefficiente", 445);
        configs.add(column);

        column = new ColumnConfig("valore", "Valore", 100);
        column.setAlignment(Style.HorizontalAlignment.RIGHT);
        column.setEditor(new CellEditor(new NumberField()));
        configs.add(column);

        final RowEditor<CoefficienteModel> re = new RowEditor<CoefficienteModel>();
        re.getMessages().setSaveText("Salva");
        re.getMessages().setCancelText("Annulla");
        re.setClicksToEdit(EditorGrid.ClicksToEdit.TWO);

        ColumnModel cm = new ColumnModel(configs);

        Grid<CoefficienteModel> grid = new Grid<CoefficienteModel>(store, cm);
        grid.setBorders(true);
        grid.addPlugin(re);
        //   grid.setAutoHeight(true);
        grid.setHeight(650);


        Button saveButton = new Button("Salva");
        saveButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                Dispatcher.forwardEvent(AmministrazioneEvents.SaveCoefficienti, store.getModels());
            }
        });

        //  centre.setBottomComponent(toolBar);
        ToolBar toolbar = new ToolBar();
        toolbar.add(saveButton);
        centre.setTopComponent(toolbar);
        centre.add(grid);
        return centre;
    }

    public void clear() {
    }

    public void setCoefficentiInStore(List<CoefficienteModel> coefficienteModels) {
        try {
            store.removeAll();
            store.add(coefficienteModels);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


