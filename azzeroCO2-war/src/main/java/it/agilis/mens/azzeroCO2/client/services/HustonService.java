package it.agilis.mens.azzeroCO2.client.services;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import it.agilis.mens.azzeroCO2.shared.model.amministrazione.CouponModel;
import it.agilis.mens.azzeroCO2.shared.model.registrazione.UserInfoModel;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RemoteServiceRelativePath("huston")
public interface HustonService extends RemoteService {

    List<CouponModel> getListOfCoupon() throws IllegalArgumentException;

    void saveCoupon(CouponModel coupon) throws IllegalArgumentException;

    Boolean saveCoupons(List<CouponModel> modifiedRecords) throws IllegalArgumentException;

    Boolean createNewUser(UserInfoModel registrazioneModeli) throws IllegalArgumentException;

    UserInfoModel getUserInfo(String userInfo, String password) throws IllegalArgumentException;


     void disconnectUser()  throws IllegalArgumentException;
}
