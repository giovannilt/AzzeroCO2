package it.agilis.mens.azzeroCO2.client.mvc.controllers;

import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.extjs.gxt.ui.client.mvc.View;
import it.agilis.mens.azzeroCO2.client.mvc.views.AzzeroCO2View;
import it.agilis.mens.azzeroCO2.client.mvc.events.*;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 6/25/11
 * Time: 8:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class AzzeroCO2Controller extends Controller {
    View azzeroCO2View;

    public AzzeroCO2Controller(){
        registerEventTypes(AzzeroCO2Events.Init);
   	    registerEventTypes(AzzeroCO2Events.Error);
		registerEventTypes(AzzeroCO2Events.UIReady);
    }

    @Override
    public void handleEvent(AppEvent event) {
       forwardToView(azzeroCO2View, event);
    }

    @Override
    public void initialize(){
        super.initialize();
        azzeroCO2View= new AzzeroCO2View(this);
    }
}
