package it.agilis.mens.azzeroCO2.pages.events.startContent.controller;

import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 5/15/11
 * Time: 4:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class StartController  extends Controller {

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
