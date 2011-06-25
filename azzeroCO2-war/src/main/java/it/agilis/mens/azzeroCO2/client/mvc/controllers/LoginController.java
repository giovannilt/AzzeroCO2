package it.agilis.mens.azzeroCO2.client.mvc.controllers;

import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import it.agilis.mens.azzeroCO2.client.mvc.views.LoginView;
import it.agilis.mens.azzeroCO2.client.mvc.events.LoginEvents;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 3/17/11
 * Time: 11:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class LoginController extends Controller {
    private LoginView loginView;

    public LoginController() {
        // registra su quali eventi si deve mettere in ascolto
        registerEventTypes(LoginEvents.DoLogin);
        registerEventTypes(LoginEvents.ShowForm);
    }

    public void initialize() {
        loginView = new LoginView(this);
    }

    public void handleEvent(AppEvent event) {
        EventType type = event.getType();
        if (type == LoginEvents.ShowForm) {
            // inoltra l’evento alla view per visualizzare la form di login
            forwardToView(loginView, event);
        } else if (type == LoginEvents.DoLogin) {
            // esegue il login
            //UserInfo userInfo = new UserInfo();

          //  userInfo.setUserName(event.<String>getData("userName"));
          //  userInfo.setPassword(event.<String>getData("password"));
           // userInfo = doLogin(userInfo, loginView);
        }
    }

}