package it.agilis.mens.azzeroCO2.client.mvc.views;

import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.mvc.View;
import it.agilis.mens.azzeroCO2.client.components.main.NorthPanel;
import it.agilis.mens.azzeroCO2.client.mvc.events.AzzeroCO2Events;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 3/23/11
 * Time: 10:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class NorthView extends View {
    private NorthPanel northPanel= new NorthPanel();

    public NorthView(Controller controller) {
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
		Dispatcher.forwardEvent(new AppEvent(AzzeroCO2Events.NorthPanelReady,
                northPanel));
	}
}
