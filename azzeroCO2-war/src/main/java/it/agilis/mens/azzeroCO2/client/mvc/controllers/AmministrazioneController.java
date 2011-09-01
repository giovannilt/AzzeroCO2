package it.agilis.mens.azzeroCO2.client.mvc.controllers;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.widget.Info;
import com.google.gwt.user.client.rpc.AsyncCallback;
import it.agilis.mens.azzeroCO2.client.mvc.events.AmministrazioneEvents;
import it.agilis.mens.azzeroCO2.client.mvc.events.AzzeroCO2Events;
import it.agilis.mens.azzeroCO2.client.mvc.views.AmministrazioneView;
import it.agilis.mens.azzeroCO2.client.services.AzzeroCO2Constants;
import it.agilis.mens.azzeroCO2.client.services.HustonServiceAsync;
import it.agilis.mens.azzeroCO2.shared.model.amministrazione.CouponModel;
import it.agilis.mens.azzeroCO2.shared.model.registrazione.UserInfoModel;

import java.util.List;

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
        registerEventTypes(AmministrazioneEvents.SaveCoupons);
        registerEventTypes(AmministrazioneEvents.SaveCoefficienti);
    }

    @Override
    public void handleEvent(AppEvent event) {
        if (event.getType().equals(AmministrazioneEvents.SaveCoefficienti)) {
        } else if (event.getType().equals(AmministrazioneEvents.SaveCoupons)) {

            List<CouponModel> coupons = event.getData();
            HustonServiceAsync hustonService = Registry.get(AzzeroCO2Constants.HUSTON_SERVICE);

            AsyncCallback<Boolean> aCallback = new AsyncCallback<Boolean>() {
                public void onFailure(Throwable caught) {
                }

                @Override
                public void onSuccess(Boolean result) {
                    Info.display("Info", "Coupons Salvati");
                }
            };
            hustonService.saveCoupons(coupons, aCallback);
        } else if (event.getType().equals(AzzeroCO2Events.LoggedIn)) {
            setUserInfoModel((UserInfoModel) event.getData());
        } else {
            forwardToView(amministrazioneView, event);
        }
    }


}
