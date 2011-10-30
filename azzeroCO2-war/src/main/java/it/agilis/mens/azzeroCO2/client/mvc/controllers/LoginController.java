package it.agilis.mens.azzeroCO2.client.mvc.controllers;

import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.widget.Info;
import com.google.gwt.user.client.rpc.AsyncCallback;
import it.agilis.mens.azzeroCO2.client.mvc.events.AzzeroCO2Events;
import it.agilis.mens.azzeroCO2.client.mvc.events.LoginEvents;
import it.agilis.mens.azzeroCO2.client.mvc.views.LoginView;
import it.agilis.mens.azzeroCO2.shared.model.registrazione.UserInfoModel;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 3/17/11
 * Time: 11:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class LoginController extends BaseController {
    private LoginView loginView = new LoginView(this);

    public LoginController() {
        // registra su quali eventi si deve mettere in ascolto
        registerEventTypes(LoginEvents.DoLogin);
        registerEventTypes(LoginEvents.ShowForm);
        registerEventTypes(LoginEvents.HideForm);
        registerEventTypes(LoginEvents.LogOut);

    }

    public void handleEvent(AppEvent event) {
        EventType type = event.getType();
        if (type == LoginEvents.ShowForm) {
            forwardToView(loginView, event);
        } else if (type == LoginEvents.DoLogin) {
            AsyncCallback<UserInfoModel> aCallback = new AsyncCallback<UserInfoModel>() {
                public void onFailure(Throwable caught) {
                    Info.display("Error", "Errore impossibile connettersi al server");
                }

                @Override
                public void onSuccess(UserInfoModel result) {
                    if (result != null) {
                        Info.display("Info", "Benventuo " + result.getNome());
                        if(loginView !=null){
                            loginView.hide();
                        }

                        Dispatcher.forwardEvent(LoginEvents.ShowLogOut);
                        AppEvent event = new AppEvent(AzzeroCO2Events.LoggedIn);
                        event.setData(result);
                        Dispatcher.forwardEvent(event);
                    } else {
                        Info.display("Error", "Username o Password errati.");
                    }
                }
            };
            getHustonService().getUserInfo(event.<String>getData("userName"), event.<String>getData("password"), aCallback);
        } else if (type == LoginEvents.LogOut) {
            AsyncCallback aCallback = new AsyncCallback() {
                public void onFailure(Throwable caught) {
                    Info.display("Error", "Errore impossibile connettersi al server");
                }

                @Override
                public void onSuccess(Object result) {

                    Info.display("LOGOUT", "LOGOUT");
                }
            };
            getHustonService().disconnectUser(aCallback);
        } else {
            forwardToView(loginView, event);
        }
    }

}