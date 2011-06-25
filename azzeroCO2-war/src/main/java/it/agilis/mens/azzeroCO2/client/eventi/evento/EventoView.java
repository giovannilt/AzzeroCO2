package it.agilis.mens.azzeroCO2.client.eventi.evento;

import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.extjs.gxt.ui.client.mvc.View;
import it.agilis.mens.azzeroCO2.client.mvc.events.EventoEvents;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 3/23/11
 * Time: 10:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class EventoView extends View {
    private Evento evento;

    public EventoView(Controller controller) {
        super(controller);
    }

    @Override
    protected void initialize() {
        evento = new Evento();
    }

    @Override
    protected void handleEvent(AppEvent event) {
        if(event.getType() == EventoEvents.showForm){
            evento.show();
        } else if(event.getType() == EventoEvents.hideForm){
            evento.hide();
        } else if(event.getType() == EventoEvents.next){

        }
    }
}
