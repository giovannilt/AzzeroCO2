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
import it.agilis.mens.azzeroCO2.shared.model.amministrazione.CoefficienteModel;
import it.agilis.mens.azzeroCO2.shared.model.amministrazione.CouponModel;
import it.agilis.mens.azzeroCO2.shared.model.amministrazione.OrdineModel;
import it.agilis.mens.azzeroCO2.shared.model.amministrazione.ProgettoDiCompensazioneModel;
import it.agilis.mens.azzeroCO2.shared.model.registrazione.UserInfoModel;

import java.util.List;
import java.util.Map;

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
        registerEventTypes(AmministrazioneEvents.ShowAmministrazione);
        registerEventTypes(AmministrazioneEvents.SaveCoupons);
        registerEventTypes(AmministrazioneEvents.SaveCoefficienti);
        registerEventTypes(AmministrazioneEvents.SaveProgrammiDiCompensazione);
    }

    @Override
    public void handleEvent(AppEvent event) {
        if (event.getType().equals(AmministrazioneEvents.SaveCoefficienti)) {
            List<CoefficienteModel> coefficienteModels = event.getData();
            HustonServiceAsync hustonService = Registry.get(AzzeroCO2Constants.HUSTON_SERVICE);
            AsyncCallback<Boolean> aCallback = new AsyncCallback<Boolean>() {
                public void onFailure(Throwable caught) {
                    Info.display("Error", "Errore impossibile connettersi al server");
                }
                @Override
                public void onSuccess(Boolean result) {
                    Info.display("Info", "Coefficienti Salvati");
                }
            };
            hustonService.saveCoefficienti(coefficienteModels, aCallback);
        } else if (event.getType().equals(AmministrazioneEvents.SaveCoupons)) {
            List<CouponModel> coupons = event.getData();
            HustonServiceAsync hustonService = Registry.get(AzzeroCO2Constants.HUSTON_SERVICE);
            AsyncCallback<Boolean> aCallback = new AsyncCallback<Boolean>() {
                public void onFailure(Throwable caught) {
                    Info.display("Error", "Errore impossibile connettersi al server");
                }
                @Override
                public void onSuccess(Boolean result) {
                    Info.display("Info", "Coupons Salvati");
                }
            };
            hustonService.saveCoupons(coupons, aCallback);
        } else if (event.getType().equals(AmministrazioneEvents.ShowAmministrazione)) {
            if (getUserInfoModel().getProfilo().equalsIgnoreCase("Administrator")) {
                getCoefficienti();
                getCoupons();
                getProgettiDiCompensazione();
            }
            getOrdini();
            amministrazioneView.setUserInfo(getUserInfoModel());
        } else if (event.getType().equals(AzzeroCO2Events.LoggedIn)) {
            setUserInfoModel((UserInfoModel) event.getData());
        } else {
            forwardToView(amministrazioneView, event);
        }
    }


    // SETTANO
    private void getOrdini() {
        HustonServiceAsync hustonService = Registry.get(AzzeroCO2Constants.HUSTON_SERVICE);
        AsyncCallback<List<OrdineModel>> aCallback = new AsyncCallback<List<OrdineModel>>() {
            public void onFailure(Throwable caught) {
                Info.display("Error", "Errore impossibile connettersi al server");
            }

            @Override
            public void onSuccess(List<OrdineModel> result) {
                amministrazioneView.setOrdini(result);
            }
        };
        hustonService.getListOfOrdini(getUserInfoModel(), aCallback);
    }

    private void getCoefficienti() {
        HustonServiceAsync hustonService = Registry.get(AzzeroCO2Constants.HUSTON_SERVICE);
        AsyncCallback<Map<String, CoefficienteModel>> aCallback = new AsyncCallback<Map<String, CoefficienteModel>>() {
            public void onFailure(Throwable caught) {
                Info.display("Error", "Errore impossibile connettersi al server");
            }

            @Override
            public void onSuccess(Map<String, CoefficienteModel> result) {
                amministrazioneView.setCoefficienti(result);
            }
        };
        hustonService.getCoefficienti(aCallback);
    }

    private void getCoupons() {
        HustonServiceAsync hustonService = Registry.get(AzzeroCO2Constants.HUSTON_SERVICE);
        AsyncCallback<List<CouponModel>> aCallback = new AsyncCallback<List<CouponModel>>() {
            public void onFailure(Throwable caught) {
                Info.display("Error", "Errore impossibile connettersi al server");
            }

            @Override
            public void onSuccess(List<CouponModel> result) {
                amministrazioneView.setCoupon(result);
            }
        };
        hustonService.getListOfCoupon(aCallback);
    }

    public void getProgettiDiCompensazione() {
        HustonServiceAsync hustonService = Registry.get(AzzeroCO2Constants.HUSTON_SERVICE);
        AsyncCallback<List<ProgettoDiCompensazioneModel>> aCallback = new AsyncCallback<List<ProgettoDiCompensazioneModel>>() {
            public void onFailure(Throwable caught) {
                Info.display("Error", "Errore impossibile connettersi al server");
            }

            @Override
            public void onSuccess(List<ProgettoDiCompensazioneModel> result) {
                amministrazioneView.setProgettiDiCompensazione(result);
            }
        };
        hustonService.getListOfProgettoDiCompensazione(aCallback);
    }
}
