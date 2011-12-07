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
import it.agilis.mens.azzeroCO2.client.components.conoscoCO2.ConoscoCO2;
import it.agilis.mens.azzeroCO2.client.components.conoscoCO2.ConoscoCO2North;
import it.agilis.mens.azzeroCO2.client.components.conoscoCO2.ConoscoCO2South;
import it.agilis.mens.azzeroCO2.client.components.conoscoCO2.ConoscoCO2West;
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
public class ConosciCO2View extends View {
    private ContentPanel conoscoCO2panel = new ContentPanel();

    private ConoscoCO2 conoscoCO2 = new ConoscoCO2();
    private ContentPanel center = new ContentPanel();
    private ConoscoCO2South south = new ConoscoCO2South();


    public ConosciCO2View(Controller controller) {
        super(controller);
    }


    @Override
    protected void handleEvent(AppEvent event) {
        EventType eventType = event.getType();
        if (eventType.equals(AzzeroCO2Events.Init)) {
            onInit(event);
        } else if (eventType.equals(ConosciCO2Events.Next)) {
            conoscoCO2.nextTab(event);
        } else if (eventType.equals(ConosciCO2Events.Previous)) {
            conoscoCO2.previusTab(event);
        }
    }


    private void onInit(AppEvent event) {
        final BorderLayout layout = new BorderLayout();
        layout.setEnableState(false);
        conoscoCO2panel.setHeaderVisible(false);
        conoscoCO2panel.setLayout(layout);
        conoscoCO2panel.setStyleAttribute("padding", "1px");

        BorderLayoutData northData = new BorderLayoutData(Style.LayoutRegion.NORTH, 25);
        northData.setCollapsible(false);
        northData.setFloatable(false);
        northData.setHideCollapseTool(false);
        northData.setSplit(false);
        northData.setMargins(new Margins(0, 0, 0, 0));
        conoscoCO2panel.add(new ConoscoCO2North(), northData);


        BorderLayoutData westData = new BorderLayoutData(Style.LayoutRegion.WEST, 250);
        westData.setCollapsible(false);
        westData.setFloatable(false);
        westData.setHideCollapseTool(false);
        westData.setSplit(false);
        westData.setMargins(new Margins(0, 0, 0, 0));

        conoscoCO2panel.add(new ConoscoCO2West(), westData);

        BorderLayoutData centerData = new BorderLayoutData(Style.LayoutRegion.CENTER);
        {
            final BorderLayout layout2 = new BorderLayout();
            center.setLayout(layout2);
            center.setStyleAttribute("padding", "1px");

            BorderLayoutData center2Data = new BorderLayoutData(Style.LayoutRegion.CENTER);
            center2Data.setMargins(new Margins(0, 0, 0, 0));
            center.add(conoscoCO2, center2Data);

            BorderLayoutData southData = new BorderLayoutData(Style.LayoutRegion.SOUTH, 33);
            southData.setMargins(new Margins(0, 0, 0, 0));
            center.add(south, southData);
        }
        center.setHeaderVisible(false);
        conoscoCO2panel.add(center, centerData);

        conoscoCO2panel.setTitle(Eventi.CONOSCI_CO2.name());
        Dispatcher.forwardEvent(new AppEvent(CentralEvents.ConosciCO2PanelReady,
                conoscoCO2panel));
    }

}
