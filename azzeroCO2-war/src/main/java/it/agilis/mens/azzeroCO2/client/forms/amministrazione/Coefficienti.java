package it.agilis.mens.azzeroCO2.client.forms.amministrazione;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.data.PagingModelMemoryProxy;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.grid.*;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Element;
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
@SuppressWarnings({"ALL"})
public class Coefficienti extends LayoutContainer {

    private ListStore<CoefficienteModel> store = new ListStore<CoefficienteModel>();

    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);

        BorderLayout layout = new BorderLayout();
        setLayout(layout);

        ContentPanel centre = createCentre();
        centre.setHeading("Coefficiente");
        centre.setHeight(637);
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


        column = new ColumnConfig("coefficiente", "Coefficiente", 445);
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
        grid.setAutoHeight(true);

      //  centre.setBottomComponent(toolBar);


        centre.add(grid);

        return centre;
    }

    public void clear() {
    }

    public void setCoefficentiInStore(List<CoefficienteModel> coefficienteModels) {
        store.add(coefficienteModels);
    }
}


