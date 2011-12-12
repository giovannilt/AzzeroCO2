package it.agilis.mens.azzeroCO2.client.mvc.controllers;

import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.widget.Info;
import com.google.gwt.user.client.rpc.AsyncCallback;
import it.agilis.mens.azzeroCO2.client.mvc.events.AzzeroCO2Events;
import it.agilis.mens.azzeroCO2.client.mvc.events.LoginEvents;
import it.agilis.mens.azzeroCO2.client.mvc.events.RegisterEvents;
import it.agilis.mens.azzeroCO2.client.mvc.views.RegisterView;
import it.agilis.mens.azzeroCO2.shared.Profile;
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
        registerEventTypes(RegisterEvents.SaveUserInfo);
        registerEventTypes(RegisterEvents.ShowForm);
        registerEventTypes(RegisterEvents.HideForm);
        registerEventTypes(AzzeroCO2Events.LoggedIn);
    }


    @Override
    public void handleEvent(AppEvent event) {
        if (event.getType().equals(AzzeroCO2Events.LoggedIn)) {
            setUserInfoModel((UserInfoModel) event.getData());
        } else if (event.getType().equals(RegisterEvents.SaveUserInfo)) {
            AsyncCallback<Boolean> aCallback = new AsyncCallback<Boolean>() {
                public void onFailure(Throwable caught) {
                    Info.display("Error", "Errore nella creazione dell'utente");
                }

                @Override
                public void onSuccess(Boolean result) {
                    if (result) {
                        Info.display("Info", "Username salvato.");

                    } else {
                        Info.display("Error", "Errore nel salvataggio.");
                    }
                }
            };
            UserInfoModel data = (UserInfoModel) event.getData();
            //  data.setProfilo(Profile.User.ordinal());
            getHustonService().saveUserInfo(data, aCallback);
        } else if (event.getType().equals(RegisterEvents.DoRegistration)) {
            AsyncCallback<UserInfoModel> aCallback = new AsyncCallback<UserInfoModel>() {
                public void onFailure(Throwable caught) {
                    Info.display("Error", "Errore nella creazione dell'utente");
                }

                @Override
                public void onSuccess(UserInfoModel result) {
                    if (result!=null) {
                        Dispatcher.forwardEvent(RegisterEvents.HideForm);
                        Dispatcher.forwardEvent(LoginEvents.ShowLogOut);
                        Info.display("Info", "Utente Creato con sucesso");
                        view.hideStatus();

                        AppEvent event = new AppEvent(AzzeroCO2Events.LoggedIn);
                        event.setData(result);
                        Dispatcher.forwardEvent(event);
                    } else {
                        Info.display("Error", "Username gia' registrato.");
                        view.hideStatus();
                    }
                }
            };
            UserInfoModel data = (UserInfoModel) event.getData();
            data.setProfilo(Profile.User.ordinal());
            getHustonService().createNewUser(data, aCallback);
        } else {
            forwardToView(view, event);
        }

    }
}