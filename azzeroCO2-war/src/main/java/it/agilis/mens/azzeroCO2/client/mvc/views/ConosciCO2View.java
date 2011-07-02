package it.agilis.mens.azzeroCO2.client.mvc.views;

import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.mvc.View;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import it.agilis.mens.azzeroCO2.client.mvc.events.AzzeroCO2Events;
import it.agilis.mens.azzeroCO2.client.mvc.events.CentralEvents;
import it.agilis.mens.azzeroCO2.shared.Eventi;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 7/2/11
 * Time: 11:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class ConosciCO2View extends View {
    private ContentPanel conosciCO2 = new ContentPanel();

    public ConosciCO2View(Controller controller) {
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
        conosciCO2.setTitle(Eventi.CONOSCI_CO2.name());
        Dispatcher.forwardEvent(new AppEvent(CentralEvents.ConosciCO2PanelReady,
                conosciCO2));
    }
}
