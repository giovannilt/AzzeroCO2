package it.agilis.mens.azzeroCO2.pages.login.controller;

import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import it.agilis.mens.azzeroCO2.core.entity.UserInfo;
import it.agilis.mens.azzeroCO2.pages.Apollo;
import it.agilis.mens.azzeroCO2.pages.login.LoginView;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 3/17/11
 * Time: 11:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class LoginController extends Apollo {
    private LoginView loginView;

    public LoginController() {
        // registra su quali eventi si deve mettere in ascolto
        registerEventTypes(LoginEvents.doLogin);
        registerEventTypes(LoginEvents.showForm);
    }

    public void initialize() {
        loginView = new LoginView(this);
    }

    public void handleEvent(AppEvent event) {
        EventType type = event.getType();
        if (type == LoginEvents.showForm) {
            // inoltra l’evento alla view per visualizzare la form di login
            forwardToView(loginView, event);
        } else if (type == LoginEvents.doLogin) {
            // esegue il login
            UserInfo userInfo = new UserInfo();

            userInfo.setUserName(event.<String>getData("userName"));
            userInfo.setPassword(event.<String>getData("password"));
            userInfo = doLogin(userInfo, loginView);
        }
    }

}