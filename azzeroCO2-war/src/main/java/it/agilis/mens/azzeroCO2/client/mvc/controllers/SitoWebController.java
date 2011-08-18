package it.agilis.mens.azzeroCO2.client.mvc.controllers;

import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import it.agilis.mens.azzeroCO2.client.mvc.events.AzzeroCO2Events;
import it.agilis.mens.azzeroCO2.client.mvc.events.ConosciCO2Events;
import it.agilis.mens.azzeroCO2.client.mvc.events.SitoWebEvents;
import it.agilis.mens.azzeroCO2.client.mvc.views.ConosciCO2View;
import it.agilis.mens.azzeroCO2.client.mvc.views.SitoWebView;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 7/2/11
 * Time: 11:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class SitoWebController extends Controller {

    private SitoWebView sitoWebView= new SitoWebView(this);

    public SitoWebController() {
        registerEventTypes(AzzeroCO2Events.Init);
   	    registerEventTypes(AzzeroCO2Events.Error);
        registerEventTypes(SitoWebEvents.Next);
        registerEventTypes(SitoWebEvents.Previous);

    }

    @Override
    public void handleEvent(AppEvent event) {
        forwardToView(sitoWebView, event);
    }
}
