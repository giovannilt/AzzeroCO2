package it.agilis.mens.azzeroCO2.client.forms.amministrazione;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.grid.*;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Element;
import it.agilis.mens.azzeroCO2.shared.model.amministrazione.OrdineModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 6/19/11
 * Time: 5:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class Ordini extends LayoutContainer {

    private final ListStore<OrdineModel> store = new ListStore<OrdineModel>();

    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);

        setLayout(new BorderLayout());
        ContentPanel centre = createCentre();
      //  centre.setHeading("Ordini");
       centre.setHeaderVisible(false);
        centre.setFrame(true);
        centre.setHeight(637);
        centre.setFrame(true);
        BorderLayoutData centerData = new BorderLayoutData(Style.LayoutRegion.CENTER);
        centerData.setMargins(new Margins(0));
        add(centre, centerData);
    }

    private ContentPanel createCentre() {
        ContentPanel centre = new ContentPanel();

        /*    PagingModelMemoryProxy proxy = new PagingModelMemoryProxy(CouponModel.class);

                // loader
                PagingLoader<PagingLoadResult<ModelData>> loader = new BasePagingLoader<PagingLoadResult<ModelData>>(proxy);
                loader.setRemoteSort(true);

                final PagingToolBar toolBar = new PagingToolBar(2);
                toolBar.bind(loader);


                loader.load(0, 10);
        */

        final NumberFormat number = NumberFormat.getFormat("0.00");

        List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

        ColumnConfig column = new ColumnConfig("data", "Data OrdineModel", 100);
        configs.add(column);

        column = new ColumnConfig("cliente", "Cliente", 300);
        configs.add(column);

        column = new ColumnConfig("programma", "ProgettoDiCompensazione", 200);
        configs.add(column);

        column = new ColumnConfig("kgco2", "KG CO2", 100);
        column.setAlignment((Style.HorizontalAlignment.RIGHT));
        configs.add(column);

        column = new ColumnConfig("Importo", "Importo ", 100);
        column.setAlignment(Style.HorizontalAlignment.RIGHT);
        configs.add(column);

        ColumnModel cm = new ColumnModel(configs);

        AggregationRowConfig<OrdineModel> somma = new AggregationRowConfig<OrdineModel>();
        somma.setHtml("name", "Totale");

        somma.setSummaryType("kgCO2", SummaryType.SUM);
        somma.setRenderer("kgCO2", new AggregationRenderer<OrdineModel>() {
            public Object render(Number value, int colIndex, Grid<OrdineModel> grid, ListStore<OrdineModel> store) {
                return number.format(value.doubleValue());
            }
        });
        cm.addAggregationRow(somma);

        somma = new AggregationRowConfig<OrdineModel>();
        somma.setHtml("name", "kgCO2");

        Grid<OrdineModel> grid = new Grid<OrdineModel>(store, cm);
        grid.setBorders(true);
        grid.setAutoHeight(true);
        //    centre.setBottomComponent(toolBar);
        centre.add(grid);

        return centre;
    }

    public void setOrdiniInStore(List<OrdineModel> ordiniModels) {
        store.removeAll();
        store.add(ordiniModels);
    }


    public void clear() {
    }
}


