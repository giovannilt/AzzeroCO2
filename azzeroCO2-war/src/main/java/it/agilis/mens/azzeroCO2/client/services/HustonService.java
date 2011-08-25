package it.agilis.mens.azzeroCO2.client.services;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import it.agilis.mens.azzeroCO2.shared.model.amministrazione.CouponModel;
import it.agilis.mens.azzeroCO2.shared.model.registrazione.UserInfoModel;

import java.util.List;

@RemoteServiceRelativePath("huston")
public interface HustonService extends RemoteService {
    // UserInfo logIn(UserInfo userInfo) throws IllegalArgumentException;
    String logIn(String userInfo) throws IllegalArgumentException;

    List<CouponModel> getListOfCoupon() throws IllegalArgumentException;

    void saveCoupon(CouponModel coupon) throws IllegalArgumentException;

    Boolean saveCoupons(List<CouponModel> modifiedRecords) throws IllegalArgumentException;

    Boolean createNewUser(UserInfoModel registrazioneModeli) throws IllegalArgumentException;

}
