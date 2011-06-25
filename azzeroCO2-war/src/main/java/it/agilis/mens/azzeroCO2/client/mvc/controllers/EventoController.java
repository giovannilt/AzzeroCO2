package it.agilis.mens.azzeroCO2.client.mvc.controllers;

import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import it.agilis.mens.azzeroCO2.client.components.eventi.evento.EventoDettaglioView;
import it.agilis.mens.azzeroCO2.client.components.eventi.evento.EventoView;
import it.agilis.mens.azzeroCO2.client.mvc.events.EventoEvents;
import it.agilis.mens.azzeroCO2.client.mvc.views.EventoItemDettaglioView;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 3/23/11
 * Time: 10:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class EventoController extends Controller {

    private EventoView eventoView;
    private EventoItemDettaglioView eventoItemDettaglioView;
    private EventoDettaglioView eventoDettaglioView;

    public EventoController(){
        registerEventTypes(EventoEvents.doSubmit);
        registerEventTypes(EventoEvents.showForm);
        registerEventTypes(EventoEvents.next);
        registerEventTypes(EventoEvents.previous);
    }

    public void initialize() {
        super.initialize();
        eventoView = new EventoView(this);
        eventoItemDettaglioView= new EventoItemDettaglioView(this);
        eventoDettaglioView= new EventoDettaglioView(this);
    }
    @Override
    public void handleEvent(AppEvent event) {
         EventType type = event.getType();
        if (type == EventoEvents.showForm) {
            forwardToView(eventoView, event);
        } else if (type == EventoEvents.next) {
            forwardToView(eventoDettaglioView,event);
        } else if (type == EventoEvents.previous) {
            forwardToView(eventoDettaglioView,event);
        } else if (type == EventoEvents.doSubmit) {
          //  event.getData("calcolo");
            this.doSubmit();
        }
    }

    public void doSubmit() {

    }
}
