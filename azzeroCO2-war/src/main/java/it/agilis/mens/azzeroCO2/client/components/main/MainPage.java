package it.agilis.mens.azzeroCO2.client.components.main;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 3/12/11
 * Time: 2:12 PM
 * To change this template use File | Settings | File Templates.
 */

import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.google.gwt.user.client.Element;

public class MainPage extends LayoutContainer {
    private CenterPanel center = new CenterPanel();


    protected void onRender(Element target, int index) {
        super.onRender(target, index);
        //  final LoginDialog loginDialog = new LoginDialog();

        setSize(1024, 768);


        final BorderLayout layout = new BorderLayout();
        layout.setEnableState(false);
        setLayout(layout);
        setStyleAttribute("padding", "5px");

        BorderLayoutData northData = new BorderLayoutData(LayoutRegion.NORTH,90);
        northData.setCollapsible(false);
        northData.setFloatable(false);
        northData.setHideCollapseTool(false);
        northData.setSplit(false);
        northData.setMargins(new Margins(0, 0, 0, 0));
        add(new NorthPanel(), northData);

        BorderLayoutData centerData = new BorderLayoutData(LayoutRegion.CENTER);
        centerData.setMargins(new Margins(0));

         //center.setScrollMode(Scroll.AUTOX);
        add(center, centerData);

        /*ContentPanel west = new ContentPanel();
        west.add(new EstPanel(center));

        // ContentPanel east = new ContentPanel();
        ContentPanel south = new ContentPanel();
        south.setHeaderVisible(false);



        BorderLayoutData westData = new BorderLayoutData(LayoutRegion.WEST, 150);
        westData.setSplit(true);
        westData.setCollapsible(true);
        westData.setMargins(new Margins(0, 5, 0, 0));



        BorderLayoutData eastData = new BorderLayoutData(LayoutRegion.EAST, 150);
        eastData.setSplit(true);
        eastData.setCollapsible(true);
        eastData.setMargins(new Margins(0, 0, 0, 5));

        BorderLayoutData southData = new BorderLayoutData(LayoutRegion.SOUTH, 30);
        southData.setSplit(true);
        southData.setCollapsible(true);
        southData.setFloatable(true);
        southData.setMargins(new Margins(5, 0, 0, 0));

        add(new NorthPanel(), northData);
        add(west, westData);
        add(center, centerData);
        // add(east, eastData);
        add(south, southData);  */

    }

    public void mainPage(AppEvent event) {
        center.setMainActive();
    }
}
