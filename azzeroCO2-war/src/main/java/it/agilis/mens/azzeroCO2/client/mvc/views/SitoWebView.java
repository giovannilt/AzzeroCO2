package it.agilis.mens.azzeroCO2.client.mvc.views;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.mvc.View;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import it.agilis.mens.azzeroCO2.client.components.sitoWeb.SitoWeb;
import it.agilis.mens.azzeroCO2.client.components.sitoWeb.SitoWebNorth;
import it.agilis.mens.azzeroCO2.client.components.sitoWeb.SitoWebSouth;
import it.agilis.mens.azzeroCO2.client.components.sitoWeb.SitoWebWest;
import it.agilis.mens.azzeroCO2.client.mvc.events.AzzeroCO2Events;
import it.agilis.mens.azzeroCO2.client.mvc.events.CentralEvents;
import it.agilis.mens.azzeroCO2.client.mvc.events.SitoWebEvents;
import it.agilis.mens.azzeroCO2.shared.Eventi;


/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 7/2/11
 * Time: 11:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class SitoWebView extends View {
    private ContentPanel sitoWebpanel = new ContentPanel();

    private SitoWeb sitoWeb = new SitoWeb();
    private ContentPanel center = new ContentPanel();
    private SitoWebSouth south = new SitoWebSouth();


    public SitoWebView(Controller controller) {
        super(controller);
    }


    @Override
    protected void handleEvent(AppEvent event) {
        EventType eventType = event.getType();
        if (eventType.equals(AzzeroCO2Events.Init)) {
            onInit(event);
        } else if (eventType.equals(SitoWebEvents.Next)) {
            sitoWeb.nextTab(event);
        } else if (eventType.equals(SitoWebEvents.Previous)) {
            sitoWeb.previusTab(event);
        }
    }


    private void onInit(AppEvent event) {
        final BorderLayout layout = new BorderLayout();
        layout.setEnableState(false);
        sitoWebpanel.setHeaderVisible(false);
        sitoWebpanel.setLayout(layout);
        sitoWebpanel.setStyleAttribute("padding", "1px");

        BorderLayoutData northData = new BorderLayoutData(Style.LayoutRegion.NORTH, 25);
        northData.setCollapsible(false);
        northData.setFloatable(false);
        northData.setHideCollapseTool(false);
        northData.setSplit(false);
        northData.setMargins(new Margins(0, 0, 0, 0));
        sitoWebpanel.add(new SitoWebNorth(), northData);


        BorderLayoutData westData = new BorderLayoutData(Style.LayoutRegion.WEST, 250);
        westData.setCollapsible(false);
        westData.setFloatable(false);
        westData.setHideCollapseTool(false);
        westData.setSplit(false);
        westData.setMargins(new Margins(0, 0, 0, 0));

        sitoWebpanel.add(new SitoWebWest(), westData);

        BorderLayoutData centerData = new BorderLayoutData(Style.LayoutRegion.CENTER);
        {
            final BorderLayout layout2 = new BorderLayout();
            center.setLayout(layout2);
            center.setStyleAttribute("padding", "1px");

            BorderLayoutData center2Data = new BorderLayoutData(Style.LayoutRegion.CENTER);
            center2Data.setMargins(new Margins(0, 0, 0, 0));
            center.add(sitoWeb, center2Data);

            BorderLayoutData southData = new BorderLayoutData(Style.LayoutRegion.SOUTH, 33);
            southData.setMargins(new Margins(0, 0, 0, 0));
            center.add(south, southData);
        }
        center.setHeaderVisible(false);
        sitoWebpanel.add(center, centerData);

        sitoWebpanel.setTitle(Eventi.WEB.name());
        Dispatcher.forwardEvent(new AppEvent(CentralEvents.SitoWebPanelReady,
                sitoWebpanel));
    }

}
