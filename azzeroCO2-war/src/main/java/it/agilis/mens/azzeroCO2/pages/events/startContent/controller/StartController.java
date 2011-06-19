package it.agilis.mens.azzeroCO2.pages.events.startContent.controller;

import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import it.agilis.mens.azzeroCO2.pages.Apollo;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 5/15/11
 * Time: 4:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class StartController  extends Apollo {

    public StartController() {
        registerEventTypes(StartEvents.show);
    }

    @Override
    public void handleEvent(AppEvent event) {
        EventType type = event.getType();

        if (type == StartEvents.show)  {

        }

    }
}
