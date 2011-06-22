package it.agilis.mens.azzeroCO2.pages.events.evento.controller;

import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import it.agilis.mens.azzeroCO2.pages.events.evento.EventoView;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 3/23/11
 * Time: 10:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class EventoController extends Controller {

    private EventoView eventoView;

    public EventoController(){
        registerEventTypes(EventoEvents.doSubmit);
        registerEventTypes(EventoEvents.showForm);
    }

    public void initialize() {
        eventoView = new EventoView(this);
    }
    @Override
    public void handleEvent(AppEvent event) {
         EventType type = event.getType();
        if (type == EventoEvents.showForm) {
            forwardToView(eventoView, event);
        } else if (type == EventoEvents.doSubmit) {
            event.getData("calcolo");
            this.doSubmit();
        }
    }

    public void doSubmit() {

    }
}
