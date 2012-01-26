package it.agilis.mens.azzeroCO2.client.mvc.views;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.extjs.gxt.ui.client.mvc.View;
import com.extjs.gxt.ui.client.util.Padding;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Viewport;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.VBoxLayout;
import com.google.gwt.user.client.ui.RootPanel;
import it.agilis.mens.azzeroCO2.client.components.InfoDialog;
import it.agilis.mens.azzeroCO2.client.components.evento.dialogs.RiepilogoConfermDialog;
import it.agilis.mens.azzeroCO2.client.mvc.events.AzzeroCO2Events;
import it.agilis.mens.azzeroCO2.shared.model.RiepilogoModel;


/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 6/25/11
 * Time: 8:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class AzzeroCO2View extends View {
    private final ContentPanel main = new ContentPanel() {
        @Override
        protected void onLoad() {
            super.onLoad();
            getBody().setStyleAttribute("border-top", "0px");
        }
    };
    private final Viewport viewport = new Viewport();
    private InfoDialog infoDialog = new InfoDialog();
    private RiepilogoConfermDialog riepilogoConfermaDialog = new RiepilogoConfermDialog();

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
        } else if (eventType.equals(AzzeroCO2Events.ShowRiepilogoConfermDialog)) {
            riepilogoConfermaDialog.setModel((RiepilogoModel) event.getData());
            riepilogoConfermaDialog.show();
        } else if (eventType.equals(AzzeroCO2Events.ShowInfoDialog)) {
            infoDialog.show();
        }

    }

    private void onNorthPanelReady(AppEvent event) {
        BorderLayoutData northData = new BorderLayoutData(Style.LayoutRegion.NORTH, 150);
        northData.setCollapsible(false);
        northData.setFloatable(false);
        northData.setHideCollapseTool(false);
        northData.setSplit(false);

        Component northPanel = event.getData();
        main.add(northPanel, northData);
    }

    private void onCentralPanelReady(AppEvent event) {
        BorderLayoutData centerData = new BorderLayoutData(Style.LayoutRegion.CENTER);
        //centerData.setMargins(new Margins(0));
        centerData.setCollapsible(false);
        centerData.setFloatable(false);
        centerData.setHideCollapseTool(false);
        centerData.setSplit(false);

        Component centerPanel = event.getData();
        centerPanel.setWidth("900");
        centerPanel.setId("CENTERPANEL_GIOVANNI");
        main.add(centerPanel, centerData);
    }

    private void onNewsPanelReady(AppEvent event) {


    }

    private void onInit(AppEvent event) {

        main.setSize(1020, 820);    //975 prima di 1040  650

        main.setHeaderVisible(false);

        final BorderLayout layout = new BorderLayout();
        layout.setEnableState(false);
        main.setLayout(layout);
        main.setAnimCollapse(true);

        VBoxLayout layoutCentre = new VBoxLayout();
        layoutCentre.setPadding(new Padding(23,5,5,5));

        layoutCentre.setVBoxLayoutAlign(VBoxLayout.VBoxLayoutAlign.CENTER);
        viewport.setLayout(layoutCentre);

        viewport.setScrollMode(Style.Scroll.AUTO);
        viewport.add(main);

    }

    private void onUIReady(AppEvent event) {
        RootPanel.get().add(viewport);
    }

    private void onError(AppEvent event) {
    }


    public void setInfo(String info) {
        infoDialog.setInfo(info);
        infoDialog.show();
    }
}
