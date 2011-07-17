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
import it.agilis.mens.azzeroCO2.client.components.amministrazione.Amministrazione;
import it.agilis.mens.azzeroCO2.client.components.amministrazione.AmministrazioneNorth;
import it.agilis.mens.azzeroCO2.client.components.amministrazione.AmministrazioneSouth;
import it.agilis.mens.azzeroCO2.client.components.amministrazione.AmministrazioneWest;

import it.agilis.mens.azzeroCO2.client.mvc.events.AzzeroCO2Events;
import it.agilis.mens.azzeroCO2.client.mvc.events.CentralEvents;
import it.agilis.mens.azzeroCO2.client.mvc.events.ConosciCO2Events;
import it.agilis.mens.azzeroCO2.shared.Eventi;


/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 7/2/11
 * Time: 11:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class AmministrazioneView extends View {
    private ContentPanel amministrazionePanel = new ContentPanel();

    private Amministrazione amministrazione = new Amministrazione();
    private ContentPanel center = new ContentPanel();
    private AmministrazioneSouth south = new AmministrazioneSouth();


    public AmministrazioneView(Controller controller) {
        super(controller);
    }

    @Override
    protected void handleEvent(AppEvent event) {
        EventType eventType = event.getType();
        if (eventType.equals(AzzeroCO2Events.Init)) {
            onInit(event);
        }
    }


    private void onInit(AppEvent event) {
        final BorderLayout layout = new BorderLayout();
        layout.setEnableState(false);
        amministrazionePanel.setHeaderVisible(false);
        amministrazionePanel.setLayout(layout);
        amministrazionePanel.setStyleAttribute("padding", "1px");
        amministrazionePanel.setTitle(Eventi.AMMINISTRAZIONE.name());

        BorderLayoutData northData = new BorderLayoutData(Style.LayoutRegion.NORTH, 25);
        northData.setCollapsible(false);
        northData.setFloatable(false);
        northData.setHideCollapseTool(false);
        northData.setSplit(false);
        northData.setMargins(new Margins(0, 0, 0, 0));
        amministrazionePanel.add(new AmministrazioneNorth(), northData);


        BorderLayoutData westData = new BorderLayoutData(Style.LayoutRegion.WEST, 150);
        westData.setCollapsible(false);
        westData.setFloatable(false);
        westData.setHideCollapseTool(false);
        westData.setSplit(false);
        westData.setMargins(new Margins(0, 0, 0, 0));

        amministrazionePanel.add(new AmministrazioneWest(), westData);

        BorderLayoutData centerData = new BorderLayoutData(Style.LayoutRegion.CENTER);
        {
            final BorderLayout layout2 = new BorderLayout();
            center.setLayout(layout2);
            center.setStyleAttribute("padding", "1px");

            BorderLayoutData center2Data = new BorderLayoutData(Style.LayoutRegion.CENTER);
            center2Data.setMargins(new Margins(0, 0, 0, 0));
            center.add(amministrazione, center2Data);

            BorderLayoutData southData = new BorderLayoutData(Style.LayoutRegion.SOUTH, 15);
            southData.setMargins(new Margins(0, 0, 0, 0));
            center.add(south, southData);
        }
        center.setHeaderVisible(false);
        amministrazionePanel.add(center, centerData);

        amministrazionePanel.setTitle(Eventi.AMMINISTRAZIONE.name());
        Dispatcher.forwardEvent(new AppEvent(CentralEvents.AmministrazioneReady,
                amministrazionePanel));
    }

}
