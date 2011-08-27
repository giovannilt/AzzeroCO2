package it.agilis.mens.azzeroCO2.client.mvc.controllers;

import com.extjs.gxt.ui.client.mvc.AppEvent;
import it.agilis.mens.azzeroCO2.client.mvc.events.AzzeroCO2Events;
import it.agilis.mens.azzeroCO2.client.mvc.events.ConosciCO2Events;
import it.agilis.mens.azzeroCO2.client.mvc.views.ConosciCO2View;
import it.agilis.mens.azzeroCO2.shared.model.registrazione.UserInfoModel;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 7/2/11
 * Time: 11:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class ConosciCO2Controller extends BaseController {

    private ConosciCO2View conosciCO2View = new ConosciCO2View(this);

    public ConosciCO2Controller() {
        registerEventTypes(AzzeroCO2Events.Init);
        registerEventTypes(AzzeroCO2Events.Error);
        registerEventTypes(ConosciCO2Events.Next);
        registerEventTypes(ConosciCO2Events.Previous);
                registerEventTypes(AzzeroCO2Events.LoggedIn);

    }

    @Override
    public void handleEvent(AppEvent event) {
        if (event.getType().equals(AzzeroCO2Events.LoggedIn)) {
            setUserInfoModel((UserInfoModel) event.getData());
        } else {
            forwardToView(conosciCO2View, event);
        }
    }


}
