package it.agilis.mens.azzeroCO2.client.services;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import it.agilis.mens.azzeroCO2.shared.dto.CoefficientiDTO;
import it.agilis.mens.azzeroCO2.shared.model.amministrazione.CouponModel;
import it.agilis.mens.azzeroCO2.shared.model.evento.TipoDiCartaModel;
import it.agilis.mens.azzeroCO2.shared.model.registrazione.UserInfoModel;

import java.util.List;
import java.util.Map;

@RemoteServiceRelativePath("huston")
public interface HustonService extends RemoteService {

    List<CouponModel> getListOfCoupon() throws IllegalArgumentException;

    void saveCoupon(CouponModel coupon) throws IllegalArgumentException;

    Boolean saveCoupons(List<CouponModel> modifiedRecords) throws IllegalArgumentException;

    Boolean createNewUser(UserInfoModel registrazioneModeli) throws IllegalArgumentException;

    UserInfoModel getUserInfo(String userInfo, String password) throws IllegalArgumentException;


    //List<GrammaturaModel> getGrammatura() throws IllegalArgumentException;
    List<TipoDiCartaModel> getTipoDiCarta() throws IllegalArgumentException;



    // TODO mmm Migliorare la gestione dello USER
    void disconnectUser() throws IllegalArgumentException;

    Map<String, CoefficientiDTO> getCoefficienti();
}
