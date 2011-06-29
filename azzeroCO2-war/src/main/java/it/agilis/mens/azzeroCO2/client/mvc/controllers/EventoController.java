package it.agilis.mens.azzeroCO2.client.mvc.controllers;

import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import it.agilis.mens.azzeroCO2.client.mvc.events.AzzeroCO2Events;
import it.agilis.mens.azzeroCO2.client.mvc.events.EventoEvents;
import it.agilis.mens.azzeroCO2.client.mvc.views.EventoView;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 3/23/11
 * Time: 10:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class EventoController extends Controller {

    private EventoView eventoView= new EventoView(this);

    public EventoController() {
        registerEventTypes(AzzeroCO2Events.Init);
   	    registerEventTypes(AzzeroCO2Events.Error);
        registerEventTypes(EventoEvents.Next);
        registerEventTypes(EventoEvents.Previous);

    }

    @Override
    public void handleEvent(AppEvent event) {
        forwardToView(eventoView, event);
    }
}
