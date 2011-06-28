package it.agilis.mens.azzeroCO2.client.components.eventi.evento;

import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.extjs.gxt.ui.client.mvc.View;
import it.agilis.mens.azzeroCO2.client.mvc.events.EventoEvents;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 3/23/11
 * Time: 10:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class EventoDettaglioView extends View {
    private EventoDettaglio dettaglio;

    public EventoDettaglioView(Controller controller) {
        super(controller);
    }

    @Override
    protected void initialize() {
        super.initialize();
        dettaglio = new EventoDettaglio();
    }

    @Override
    protected void handleEvent(AppEvent event) {
        if (event.getType() == EventoEvents.next) {
          dettaglio.next();
        } else if (event.getType() == EventoEvents.previous) {
           dettaglio.previus();
        }
    }
}
