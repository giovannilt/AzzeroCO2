package it.agilis.mens.azzeroCO2.client.mvc.controllers;

import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.widget.Info;
import com.google.gwt.user.client.rpc.AsyncCallback;
import it.agilis.mens.azzeroCO2.client.mvc.events.AzzeroCO2Events;
import it.agilis.mens.azzeroCO2.client.mvc.events.LoginEvents;
import it.agilis.mens.azzeroCO2.client.mvc.events.RegisterEvents;
import it.agilis.mens.azzeroCO2.client.mvc.views.RegisterView;
import it.agilis.mens.azzeroCO2.shared.model.registrazione.UserInfoModel;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 3/17/11
 * Time: 11:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class RegisterController extends BaseController {
    private RegisterView view = new RegisterView(this);


    public RegisterController() {
        registerEventTypes(RegisterEvents.DoRegistration);
        registerEventTypes(RegisterEvents.ShowForm);
        registerEventTypes(RegisterEvents.HideForm);
        registerEventTypes(AzzeroCO2Events.LoggedIn);
    }


    @Override
    public void handleEvent(AppEvent event) {
        if (event.getType().equals(AzzeroCO2Events.LoggedIn)) {
            setUserInfoModel((UserInfoModel) event.getData());
        } else if (event.getType().equals(RegisterEvents.DoRegistration)) {
            AsyncCallback<Boolean> aCallback = new AsyncCallback<Boolean>() {
                public void onFailure(Throwable caught) {
                    Info.display("Error", "Errore nella creazione dell'utente");
                }

                @Override
                public void onSuccess(Boolean result) {
                    if (result) {
                        Dispatcher.forwardEvent(RegisterEvents.HideForm);
                        Dispatcher.forwardEvent(LoginEvents.ShowLogOut);
                        Info.display("Info", "Utente Creato con sucesso");
                        view.hideStatus();
                    } else {
                        Info.display("Error", "Username gia' registrato.");
                        view.hideStatus();
                    }
                }
            };
            getHustonService().createNewUser((UserInfoModel) event.getData(), aCallback);
        } else {
            forwardToView(view, event);
        }

    }
}