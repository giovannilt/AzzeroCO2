package it.agilis.mens.azzeroCO2.client.mvc.views;

import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.extjs.gxt.ui.client.mvc.View;
import it.agilis.mens.azzeroCO2.client.components.eventi.evento.tabs.EventoItemDettaglio;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 3/23/11
 * Time: 10:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class EventoItemDettaglioView extends View {
    private EventoItemDettaglio dettaglio;

    public EventoItemDettaglioView(Controller controller) {
        super(controller);
    }

    @Override
    protected void initialize() {
        super.initialize();
        dettaglio = new EventoItemDettaglio();
    }

    @Override
    protected void handleEvent(AppEvent event) {

    }
}
