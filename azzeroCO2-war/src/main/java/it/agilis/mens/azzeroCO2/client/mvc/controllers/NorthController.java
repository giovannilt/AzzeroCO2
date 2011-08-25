package it.agilis.mens.azzeroCO2.client.mvc.controllers;

import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import it.agilis.mens.azzeroCO2.client.mvc.events.AzzeroCO2Events;
import it.agilis.mens.azzeroCO2.client.mvc.events.LoginEvents;
import it.agilis.mens.azzeroCO2.client.mvc.views.NorthView;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 3/23/11
 * Time: 10:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class NorthController extends Controller {

    private NorthView northView= new NorthView(this);


    public NorthController(){
        registerEventTypes(AzzeroCO2Events.Init);
        registerEventTypes(LoginEvents.ShowLogOut);

    }

    @Override
    public void handleEvent(AppEvent event) {
       forwardToView(northView, event);
    }


}
