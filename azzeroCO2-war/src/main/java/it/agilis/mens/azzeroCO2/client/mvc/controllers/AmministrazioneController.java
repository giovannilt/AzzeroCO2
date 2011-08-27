package it.agilis.mens.azzeroCO2.client.mvc.controllers;

import com.extjs.gxt.ui.client.mvc.AppEvent;
import it.agilis.mens.azzeroCO2.client.mvc.events.AmministrazioneEvents;
import it.agilis.mens.azzeroCO2.client.mvc.events.AzzeroCO2Events;
import it.agilis.mens.azzeroCO2.client.mvc.views.AmministrazioneView;
import it.agilis.mens.azzeroCO2.shared.model.registrazione.UserInfoModel;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 6/25/11
 * Time: 8:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class AmministrazioneController extends BaseController {
    AmministrazioneView amministrazioneView = new AmministrazioneView(this);

    public AmministrazioneController() {
        registerEventTypes(AzzeroCO2Events.Init);
        registerEventTypes(AzzeroCO2Events.LoggedIn);
        registerEventTypes(AmministrazioneEvents.Error);
    }

    @Override
    public void handleEvent(AppEvent event) {
        if (event.getType().equals(AzzeroCO2Events.LoggedIn)) {
            setUserInfoModel((UserInfoModel) event.getData());
        } else {
            forwardToView(amministrazioneView, event);
        }
    }


}
