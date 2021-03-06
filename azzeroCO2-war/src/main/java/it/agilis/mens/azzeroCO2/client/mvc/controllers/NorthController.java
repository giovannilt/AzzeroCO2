package it.agilis.mens.azzeroCO2.client.mvc.controllers;

import com.extjs.gxt.ui.client.mvc.AppEvent;
import it.agilis.mens.azzeroCO2.client.mvc.events.AzzeroCO2Events;
import it.agilis.mens.azzeroCO2.client.mvc.events.LoginEvents;
import it.agilis.mens.azzeroCO2.client.mvc.views.NorthView;
import it.agilis.mens.azzeroCO2.shared.model.registrazione.UserInfoModel;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 3/23/11
 * Time: 10:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class NorthController extends BaseController {

    private NorthView northView = new NorthView(this);

     public NorthController() {
        registerEventTypes(AzzeroCO2Events.Init);
        registerEventTypes(LoginEvents.ShowLogOut);
        registerEventTypes(AzzeroCO2Events.LoggedIn);
    }

    @Override
    public void handleEvent(AppEvent event) {
        if (event.getType().equals(AzzeroCO2Events.LoggedIn)) {
            setUserInfoModel((UserInfoModel) event.getData());
            northView.setUserInfo((UserInfoModel) event.getData());
        } else {
            forwardToView(northView, event);
        }
    }


}
