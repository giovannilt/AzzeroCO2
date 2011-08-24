package it.agilis.mens.azzeroCO2.client.mvc.controllers;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.extjs.gxt.ui.client.widget.Info;
import com.google.gwt.user.client.rpc.AsyncCallback;
import it.agilis.mens.azzeroCO2.client.mvc.events.RegisterEvents;
import it.agilis.mens.azzeroCO2.client.mvc.views.RegisterView;
import it.agilis.mens.azzeroCO2.client.services.AzzeroCO2Constants;
import it.agilis.mens.azzeroCO2.client.services.HustonServiceAsync;
import it.agilis.mens.azzeroCO2.shared.model.registrazione.RegistrazioneModel;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 3/17/11
 * Time: 11:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class RegisterController extends Controller {
    private RegisterView view = new RegisterView(this);
    private HustonServiceAsync hustonService = Registry.get(AzzeroCO2Constants.HUSTON_SERVICE);

    public RegisterController() {
        registerEventTypes(RegisterEvents.DoRegistration);
        registerEventTypes(RegisterEvents.ShowForm);
        registerEventTypes(RegisterEvents.HideForm);
    }


    public void handleEvent(AppEvent event) {
        if (event.equals(RegisterEvents.DoRegistration)) {
            AsyncCallback<Boolean> aCallback = new AsyncCallback<Boolean>() {
                public void onFailure(Throwable caught) {
                    Info.display("Error", "Errore nella creazione dell'utente");
                }

                @Override
                public void onSuccess(Boolean result) {
                    Info.display("Info", "Utente Creato con sucesso");
                }
            };
            hustonService.createNewUser((RegistrazioneModel) event.getData(), aCallback);
        } else {
            forwardToView(view, event);
        }

    }
}