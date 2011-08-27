package it.agilis.mens.azzeroCO2.client.mvc.controllers;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.mvc.Controller;
import it.agilis.mens.azzeroCO2.client.services.AzzeroCO2Constants;
import it.agilis.mens.azzeroCO2.client.services.HustonServiceAsync;
import it.agilis.mens.azzeroCO2.shared.model.registrazione.UserInfoModel;

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
}
