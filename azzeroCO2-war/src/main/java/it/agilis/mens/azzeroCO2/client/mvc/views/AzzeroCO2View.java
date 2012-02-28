package it.agilis.mens.azzeroCO2.client.mvc.views;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.extjs.gxt.ui.client.mvc.View;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.RowData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
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
    // private final Viewport viewport = new Viewport();
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
        BorderLayoutData northData = new BorderLayoutData(Style.LayoutRegion.NORTH, 170);
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
        // centerData.setMargins(new Margins(10, 0, 0, 0));

        Component centerPanel = event.getData();

        LayoutContainer c = new LayoutContainer();

        c.setLayout(new RowLayout(Style.Orientation.HORIZONTAL));

        c.add(centerPanel, new RowData(.96, 1));
        main.add(c, centerData);

    }

    private void onNewsPanelReady(AppEvent event) {


    }

    private void onInit(AppEvent event) {
        final BorderLayout layout = new BorderLayout();
        main.setSize(1020, 820);
        main.setHeaderVisible(false);
        main.setLayout(layout);
        main.setAnimCollapse(true);
    }

    private void onUIReady(AppEvent event) {
        RootPanel root = RootPanel.get();

        ScrollPanel sc = new ScrollPanel();

        /*VBoxLayout layoutCentre = new VBoxLayout();
        layoutCentre.setPadding(new Padding(23, 5, 5, 5));
        layoutCentre.setVBoxLayoutAlign(VBoxLayout.VBoxLayoutAlign.CENTER);
        root.setLayoutData(layoutCentre);*/
        sc.add(main);

        //   sc.add(viewport);

        sc.getElement().setAttribute("Style", "overflow: auto; position: absolute;\n" +
                "top: 2%;\n" +
                "left: 50%; \n" +
                "width:1100px;\n" +
                "height: 820px;\n" +
                "margin-left:-512px;\n" +
                "margin-top:-10px;");

        root.add(sc);

    }

    private void onError(AppEvent event) {
    }


    public void setInfo(String info) {
        infoDialog.setInfo(info);
        infoDialog.show();
    }
}
