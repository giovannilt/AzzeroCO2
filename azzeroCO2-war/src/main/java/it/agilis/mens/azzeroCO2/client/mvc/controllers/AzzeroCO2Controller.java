package it.agilis.mens.azzeroCO2.client.mvc.controllers;

import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.extjs.gxt.ui.client.mvc.View;
import it.agilis.mens.azzeroCO2.client.mvc.events.AzzeroCO2Events;
import it.agilis.mens.azzeroCO2.client.mvc.events.CentralEvents;
import it.agilis.mens.azzeroCO2.client.mvc.views.AzzeroCO2View;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 6/25/11
 * Time: 8:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class AzzeroCO2Controller extends Controller {
    View azzeroCO2View= new AzzeroCO2View(this);

    public AzzeroCO2Controller(){
        registerEventTypes(AzzeroCO2Events.Init);
   	    registerEventTypes(AzzeroCO2Events.Error);
		registerEventTypes(AzzeroCO2Events.UIReady);
		registerEventTypes(AzzeroCO2Events.NorthPanelReady);
		registerEventTypes(AzzeroCO2Events.CentralPanelReady);
       registerEventTypes(AzzeroCO2Events.NewsPanelReady);
    }

    @Override
    public void handleEvent(AppEvent event) {
       forwardToView(azzeroCO2View, event);
    }

}
