package it.agilis.mens.azzeroCO2.client.mvc.controllers;

import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import it.agilis.mens.azzeroCO2.client.mvc.events.AzzeroCO2Events;
import it.agilis.mens.azzeroCO2.client.mvc.events.CentralEvents;
import it.agilis.mens.azzeroCO2.client.mvc.views.CentralView;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 3/23/11
 * Time: 10:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class CentralController extends Controller {

    private CentralView centralView = new CentralView(this);


    public CentralController() {
        registerEventTypes(AzzeroCO2Events.Init);

        registerEventTypes(CentralEvents.EventoPanelReady);
        registerEventTypes(CentralEvents.ConosciCO2PanelReady);
        registerEventTypes(CentralEvents.UnaPubblicazioneReady);
        registerEventTypes(CentralEvents.UnAnnoDiAttivitaPanelReady);
        registerEventTypes(CentralEvents.WebPanelReady);

        registerEventTypes(CentralEvents.ShowPanel);
    }

    @Override
    public void handleEvent(AppEvent event) {
        forwardToView(centralView, event);
    }
}
