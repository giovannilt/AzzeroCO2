package it.agilis.mens.azzeroCO2.client.eventi.evento;

import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.extjs.gxt.ui.client.mvc.View;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 3/23/11
 * Time: 10:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class EventoBoxView extends View {
    private EventoBox box;

    public EventoBoxView(Controller controller) {
        super(controller);
    }

    @Override
    protected void initialize() {
        super.initialize();
        box = new EventoBox();
    }

    @Override
    protected void handleEvent(AppEvent event) {
       //forwardtoView(box, event);
   }
}
