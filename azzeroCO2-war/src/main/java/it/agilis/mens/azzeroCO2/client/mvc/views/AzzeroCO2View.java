package it.agilis.mens.azzeroCO2.client.mvc.views;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.extjs.gxt.ui.client.mvc.View;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Viewport;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import it.agilis.mens.azzeroCO2.client.mvc.events.AzzeroCO2Events;
import it.agilis.mens.azzeroCO2.client.mvc.events.LoginEvents;


/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 6/25/11
 * Time: 8:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class AzzeroCO2View extends View {
    private final ContentPanel main = new ContentPanel();
    private final Viewport viewport = new Viewport();

    public AzzeroCO2View(Controller controller) {
        super(controller);
    }

    @Override
    protected void handleEvent(AppEvent event) {
        EventType eventType = event.getType();
        if (eventType.equals(AzzeroCO2Events.Init)) {
            onInit(event);
        } else if (eventType.equals(AzzeroCO2Events.Error)) {
            onError(event);
        } else if (eventType.equals(AzzeroCO2Events.UIReady)) {
            onUIReady(event);
        } else if (eventType.equals(AzzeroCO2Events.NorthPanelReady)) {
            onNorthPanelReady(event);
        } else if (eventType.equals(AzzeroCO2Events.CentralPanelReady)) {
            onCentralPanelReady(event);
        } else if (eventType.equals(AzzeroCO2Events.NewsPanelReady)) {
            onNewsPanelReady(event);
         }

    }

    private void onNorthPanelReady(AppEvent event) {
        BorderLayoutData northData = new BorderLayoutData(Style.LayoutRegion.NORTH, 90);
        northData.setCollapsible(false);
        northData.setFloatable(false);
        northData.setHideCollapseTool(false);
        northData.setSplit(false);
        northData.setMargins(new Margins(0, 0, 0, 0));
        Component northPanel = event.getData();
        main.add(northPanel, northData);
    }

    private void onCentralPanelReady(AppEvent event) {
        BorderLayoutData centerData = new BorderLayoutData(Style.LayoutRegion.CENTER);
        centerData.setMargins(new Margins(0));
        Component centerPanel = event.getData();
        main.add(centerPanel, centerData);
    }

    private void onNewsPanelReady(AppEvent event) {


    }

    private void onInit(AppEvent event) {

        main.setSize(1024, 800);
        main.setHeaderVisible(false);

        final BorderLayout layout = new BorderLayout();
        layout.setEnableState(false);
        main.setLayout(layout);
        main.setStyleAttribute("padding", "5px");
        main.setAnimCollapse(true);


        /* VBoxLayout layoutCentre = new VBoxLayout();
                layoutCentre.setPadding(new Padding(5));
                layoutCentre.setVBoxLayoutAlign(VBoxLayout.VBoxLayoutAlign.CENTER);
                //viewport.setLayout(layoutCentre);
                viewport.setLayout(new CenterLayout());
        */
        // BorderLayoutData centerData = new BorderLayoutData(Style.LayoutRegion.CENTER);
        viewport.setScrollMode(Style.Scroll.AUTO);
        viewport.add(main);

    }

    private void onUIReady(AppEvent event) {
        RootPanel.get().add(viewport);
    }

    private void onError(AppEvent event) {
    }
}
