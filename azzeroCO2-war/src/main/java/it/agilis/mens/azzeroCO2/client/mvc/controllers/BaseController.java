package it.agilis.mens.azzeroCO2.client.mvc.controllers;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.extjs.gxt.ui.client.widget.Info;
import com.google.gwt.user.client.rpc.AsyncCallback;
import it.agilis.mens.azzeroCO2.client.services.AzzeroCO2Constants;
import it.agilis.mens.azzeroCO2.client.services.HustonServiceAsync;
import it.agilis.mens.azzeroCO2.shared.model.amministrazione.CoefficienteModel;
import it.agilis.mens.azzeroCO2.shared.model.amministrazione.ProgettoDiCompensazioneModel;
import it.agilis.mens.azzeroCO2.shared.model.registrazione.UserInfoModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 8/27/11
 * Time: 3:40 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class BaseController extends Controller {

    private HustonServiceAsync hustonService = Registry.get(AzzeroCO2Constants.HUSTON_SERVICE);
    private UserInfoModel userInfoModel;
    private Map<String, CoefficienteModel> coefficientiMAP = new HashMap<String, CoefficienteModel>();
    private List<ProgettoDiCompensazioneModel> progettiDiCompensazioneList = new ArrayList<ProgettoDiCompensazioneModel>();

    public UserInfoModel getUserInfoModel() {
        return userInfoModel;
    }

    public void setUserInfoModel(UserInfoModel userInfoModel) {
        this.userInfoModel = userInfoModel;
    }

    public HustonServiceAsync getHustonService() {
        return hustonService;
    }

    public void setHustonService(HustonServiceAsync hustonService) {
        this.hustonService = hustonService;
    }

    public void setCoefficienti() {
        AsyncCallback<Map<String, CoefficienteModel>> aCallback = new AsyncCallback<Map<String, CoefficienteModel>>() {
            public void onFailure(Throwable caught) {
                Info.display("Error", "Errore impossibile connettersi al server");
            }
            @Override
            public void onSuccess(Map<String, CoefficienteModel> result) {
                coefficientiMAP.clear();
                coefficientiMAP.putAll(result);
            }
        };
        hustonService.getCoefficienti(aCallback);
    }
    public void setProgettiDiCompensazione(){
        AsyncCallback<List<ProgettoDiCompensazioneModel>> aCallback = new AsyncCallback<List<ProgettoDiCompensazioneModel>>() {
            public void onFailure(Throwable caught) {
                Info.display("Error", "Errore impossibile connettersi al server");
            }
            @Override
            public void onSuccess(List<ProgettoDiCompensazioneModel> result) {
                progettiDiCompensazioneList.clear();
                progettiDiCompensazioneList.addAll(result);
            }
        };
        hustonService.getListOfProgettoDiCompensazione(aCallback);
    }

    public Map<String, CoefficienteModel> getCoefficientiMAP() {
        return coefficientiMAP;
    }

    public void setCoefficientiMAP(Map<String, CoefficienteModel> coefficientiMAP) {
        this.coefficientiMAP = coefficientiMAP;
    }

    public List<ProgettoDiCompensazioneModel> getProgettiDiCompensazioneList() {
        return progettiDiCompensazioneList;
    }

    public void setProgettiDiCompensazioneList(List<ProgettoDiCompensazioneModel> progettiDiCompensazioneList) {
        this.progettiDiCompensazioneList = progettiDiCompensazioneList;
    }
}
