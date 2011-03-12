package it.agilis.mens.azzeroCO2.pages.main;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 3/12/11
 * Time: 2:12 PM
 * To change this template use File | Settings | File Templates.
 */

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.RowData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Image;

public class MainPage extends LayoutContainer {

    protected void onRender(Element target, int index) {
        super.onRender(target, index);
        final LoginDialog loginDialog = new LoginDialog();

        setSize(1024, 768);

        final BorderLayout layout = new BorderLayout();
        setLayout(layout);
        setStyleAttribute("padding", "10px");
        ContentPanel north = new ContentPanel();
        Image azzeroCO2Log = new Image("/imgs/header.png");
        azzeroCO2Log.setWidth("90%");
        north.add(azzeroCO2Log, new RowData(-1, -1, new Margins(5)));
        north.setHeaderVisible(false);

        Button login = new Button();
        login.setText("Login");
        login.addSelectionListener(new SelectionListener<ButtonEvent>() {
            public void componentSelected(ButtonEvent ce) {
                loginDialog.show();
            }

        });
        login.setWidth("8%");
        login.setHeight("85%");
        north.add(login, new RowData(-1, -1, new Margins(5)));
        north.setLayout(new RowLayout(Style.Orientation.HORIZONTAL));

        CenterPanel center = new CenterPanel();
       // center.setHeading("Azzero CO2");
        center.setScrollMode(Scroll.AUTOX);

        ContentPanel west = new ContentPanel();
        west.add(new ListEventMenu(center));

        // ContentPanel east = new ContentPanel();
        ContentPanel south = new ContentPanel();
        south.setHeaderVisible(false);

        BorderLayoutData northData = new BorderLayoutData(LayoutRegion.NORTH, 60);
        northData.setCollapsible(true);
        northData.setFloatable(true);
        northData.setHideCollapseTool(true);
        northData.setSplit(true);
        northData.setMargins(new Margins(0, 0, 5, 0));

        BorderLayoutData westData = new BorderLayoutData(LayoutRegion.WEST, 150);
        westData.setSplit(true);
        westData.setCollapsible(true);
        westData.setMargins(new Margins(0, 5, 0, 0));

        BorderLayoutData centerData = new BorderLayoutData(LayoutRegion.CENTER);
        centerData.setMargins(new Margins(0));

        BorderLayoutData eastData = new BorderLayoutData(LayoutRegion.EAST, 150);
        eastData.setSplit(true);
        eastData.setCollapsible(true);
        eastData.setMargins(new Margins(0, 0, 0, 5));

        BorderLayoutData southData = new BorderLayoutData(LayoutRegion.SOUTH, 30);
        southData.setSplit(true);
        southData.setCollapsible(true);
        southData.setFloatable(true);
        southData.setMargins(new Margins(5, 0, 0, 0));

        add(north, northData);
        add(west, westData);
        add(center, centerData);
        // add(east, eastData);
        add(south, southData);
    }
}
