package it.agilis.mens.azzeroCO2.client.mvc.controllers;

import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import it.agilis.mens.azzeroCO2.client.mvc.events.AzzeroCO2Events;
import it.agilis.mens.azzeroCO2.client.mvc.views.CentralView;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 3/23/11
 * Time: 10:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class CentralController extends Controller {

    private CentralView centralView;


    public CentralController(){
        registerEventTypes(AzzeroCO2Events.Init);
    }

    public void initialize() {
        super.initialize();
        centralView = new CentralView(this);

    }
    @Override
    public void handleEvent(AppEvent event) {
        forwardToView(centralView, event);
    }
}
