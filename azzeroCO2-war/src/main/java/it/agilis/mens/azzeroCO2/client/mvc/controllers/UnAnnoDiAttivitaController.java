package it.agilis.mens.azzeroCO2.client.mvc.controllers;

import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import it.agilis.mens.azzeroCO2.client.mvc.events.AzzeroCO2Events;
import it.agilis.mens.azzeroCO2.client.mvc.views.UnAnnoDiAttivitaView;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 7/2/11
 * Time: 11:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class UnAnnoDiAttivitaController extends Controller {

    private UnAnnoDiAttivitaView unAnnoDiAttivitaView= new UnAnnoDiAttivitaView(this);

    public UnAnnoDiAttivitaController() {
        registerEventTypes(AzzeroCO2Events.Init);
   	    registerEventTypes(AzzeroCO2Events.Error);

    }

    @Override
    public void handleEvent(AppEvent event) {
        forwardToView(unAnnoDiAttivitaView, event);
    }
}
