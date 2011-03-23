package it.agilis.mens.azzeroCO2.pages.login.controller;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.google.gwt.user.client.rpc.AsyncCallback;
import it.agilis.mens.azzeroCO2.client.HustonServiceAsync;
import it.agilis.mens.azzeroCO2.core.entity.UserInfo;
import it.agilis.mens.azzeroCO2.pages.login.LoginView;

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
            this.doLogin(userInfo);
        }
    }

    public void doLogin(final UserInfo userInfo) {
        final HustonServiceAsync huston = HustonServiceAsync.Util.getInstance();

        huston.logIn(userInfo, new AsyncCallback<UserInfo>() {
            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("Error " + throwable.getMessage());
            }

            @Override
            public void onSuccess(UserInfo user) {

                if (user == null) {
                    AppEvent event = new AppEvent(LoginEvents.showForm);
                    event.setData("message", "Username o password non validi");
                    forwardToView(loginView, event);
                } else {
                    // login con successo. Mette l’oggetto nel registry e
                    // manda alla view un evento per la chiusura
                    // della finestra di login
                    Registry.register("loggedUser", user);

                    userInfo.setProfile(user.getProfile());
                    AppEvent event = new AppEvent(LoginEvents.hideForm);
                    forwardToView(loginView, event);

                    System.out.println("SUCA "+ user.getUserName());
                }

            }
        });

    }
}