package it.agilis.mens.azzeroCO2.client.forms;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.DateWrapper;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.RowEditor;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.user.client.Element;
import it.agilis.mens.azzeroCO2.shared.model.CategoriePersone;

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
        west.setTitle("Trasporto Persone");
        BorderLayoutData westData = new BorderLayoutData(Style.LayoutRegion.WEST, 300);
        westData.setMargins(new Margins(0));
        add(west, westData);

        createCentre();
        centre.setTitle("Tutte Le Persone");
        BorderLayoutData centerData = new BorderLayoutData(Style.LayoutRegion.CENTER);
        centerData.setMargins(new Margins(0));
        add(centre, centerData);

    }

    private void createWest() {
        ContentPanel cp = new ContentPanel();
        // cp.setIcon(Resources.ICONS.table());
        cp.setHeading("Edit Plants with RowEditor");
        cp.setFrame(true);
        cp.setSize(600, 300);
        cp.setLayout(new FitLayout());


        ListStore<CategoriePersone> store = new ListStore<CategoriePersone>();
         ColumnModel cm = new ColumnModel(configs);
        final RowEditor<CategoriePersone> re = new RowEditor<CategoriePersone>();
        final Grid<CategoriePersone> grid = new Grid<CategoriePersone>(store, cm);
        grid.setAutoExpandColumn("name");
        grid.setBorders(true);
        grid.addPlugin(checkColumn);
        grid.addPlugin(re);
        grid.getAriaSupport().setLabelledBy(cp.getHeader().getId() + "-label");
        cp.add(grid);

        ToolBar toolBar = new ToolBar();
        Button add = new Button("Add Plant");
        add.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                Plant plant = new Plant();
                plant.setName("New Plant 1");
                plant.setLight("Mostly Shady");
                plant.setPrice(0);
                plant.setAvailable(new DateWrapper().clearTime().asDate());
                plant.setIndoor(false);

                re.stopEditing(false);
                store.insert(plant, 0);
                re.startEditing(store.indexOf(plant), true);

            }

        });
        toolBar.add(add);
        cp.setTopComponent(toolBar);
        cp.setButtonAlign(Style.HorizontalAlignment.CENTER);

    }

}

