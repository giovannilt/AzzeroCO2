package it.agilis.mens.azzeroCO2.client.mvc.controllers;

import com.extjs.gxt.ui.client.mvc.AppEvent;
import it.agilis.mens.azzeroCO2.client.mvc.events.AzzeroCO2Events;
import it.agilis.mens.azzeroCO2.client.mvc.events.CentralEvents;
import it.agilis.mens.azzeroCO2.client.mvc.views.CentralView;
import it.agilis.mens.azzeroCO2.shared.Profile;
import it.agilis.mens.azzeroCO2.shared.model.registrazione.UserInfoModel;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 3/23/11
 * Time: 10:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class CentralController extends BaseController {
    private CentralView centralView = new CentralView(this);

    public CentralController() {
        registerEventTypes(AzzeroCO2Events.Init);
        registerEventTypes(CentralEvents.EventoPanelReady);
        registerEventTypes(CentralEvents.ConosciCO2PanelReady);
        registerEventTypes(CentralEvents.SitoWebPanelReady);
        registerEventTypes(CentralEvents.UnaPubblicazioneReady);
        registerEventTypes(CentralEvents.UnAnnoDiAttivitaPanelReady);
        registerEventTypes(CentralEvents.WebPanelReady);
        registerEventTypes(CentralEvents.AmministrazioneReady);
        registerEventTypes(AzzeroCO2Events.LoggedIn);
        registerEventTypes(CentralEvents.ShowPanel);
    }

    @Override
    public void handleEvent(AppEvent event) {
        if (event.getType().equals(AzzeroCO2Events.LoggedIn)) {
            setUserInfoModel((UserInfoModel) event.getData());
            if (((UserInfoModel) event.getData()).getProfilo() != Profile.Guest.ordinal()) {
                centralView.enableDisableLoginForm(Profile.values()[((UserInfoModel) event.getData()).getProfilo()]);
            }
        } else {
            forwardToView(centralView, event);
        }
    }


}
