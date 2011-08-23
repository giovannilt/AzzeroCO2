package it.agilis.mens.azzeroCO2.client.mvc.controllers;

import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import it.agilis.mens.azzeroCO2.client.mvc.events.RegisterEvents;
import it.agilis.mens.azzeroCO2.client.mvc.views.RegisterView;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 3/17/11
 * Time: 11:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class RegisterController extends Controller {
    private RegisterView view = new RegisterView(this);

    public RegisterController() {
        registerEventTypes(RegisterEvents.DoRegistration);
        registerEventTypes(RegisterEvents.ShowForm);
        registerEventTypes(RegisterEvents.HideForm);
    }


    public void handleEvent(AppEvent event) {
        forwardToView(view, event);
    }
}