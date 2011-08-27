package it.agilis.mens.azzeroCO2.client.mvc.controllers;

import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.View;
import it.agilis.mens.azzeroCO2.client.mvc.events.AzzeroCO2Events;
import it.agilis.mens.azzeroCO2.client.mvc.events.LoginEvents;
import it.agilis.mens.azzeroCO2.client.mvc.views.AzzeroCO2View;
import it.agilis.mens.azzeroCO2.shared.model.registrazione.UserInfoModel;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 6/25/11
 * Time: 8:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class AzzeroCO2Controller extends BaseController {
    private View azzeroCO2View = new AzzeroCO2View(this);


    public AzzeroCO2Controller() {
        registerEventTypes(AzzeroCO2Events.Init);
        registerEventTypes(AzzeroCO2Events.Error);
        registerEventTypes(AzzeroCO2Events.UIReady);
        registerEventTypes(AzzeroCO2Events.NorthPanelReady);
        registerEventTypes(AzzeroCO2Events.CentralPanelReady);
        registerEventTypes(AzzeroCO2Events.NewsPanelReady);
        registerEventTypes(LoginEvents.DoLogin);
        registerEventTypes(LoginEvents.LogOut);
        registerEventTypes(AzzeroCO2Events.LoggedIn);
    }

    @Override
    public void handleEvent(AppEvent event) {
        if (event.getType().equals(AzzeroCO2Events.LoggedIn)) {
            setUserInfoModel((UserInfoModel) event.getData());
        } else {
            forwardToView(azzeroCO2View, event);
        }
    }


}
