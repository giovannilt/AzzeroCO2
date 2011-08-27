package it.agilis.mens.azzeroCO2.client.mvc.controllers;

import com.extjs.gxt.ui.client.mvc.AppEvent;
import it.agilis.mens.azzeroCO2.client.mvc.events.AzzeroCO2Events;
import it.agilis.mens.azzeroCO2.client.mvc.events.SitoWebEvents;
import it.agilis.mens.azzeroCO2.client.mvc.views.SitoWebView;
import it.agilis.mens.azzeroCO2.shared.model.registrazione.UserInfoModel;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 7/2/11
 * Time: 11:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class SitoWebController extends BaseController {

    private SitoWebView sitoWebView = new SitoWebView(this);

    public SitoWebController() {
        registerEventTypes(AzzeroCO2Events.Init);
        registerEventTypes(AzzeroCO2Events.Error);
        registerEventTypes(SitoWebEvents.Next);
        registerEventTypes(SitoWebEvents.Previous);
        registerEventTypes(AzzeroCO2Events.LoggedIn);

    }

    @Override
    public void handleEvent(AppEvent event) {
        if (event.getType().equals(AzzeroCO2Events.LoggedIn)) {
            setUserInfoModel((UserInfoModel) event.getData());
        } else {
            forwardToView(sitoWebView, event);
        }
    }
}
