package it.agilis.mens.azzeroCO2.server.services;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import it.agilis.mens.azzeroCO2.client.services.HustonService;
import it.agilis.mens.azzeroCO2.core.entity.Coupon;
import it.agilis.mens.azzeroCO2.core.register.impl.AzzeroCO2Register;
import it.agilis.mens.azzeroCO2.server.utils.Utils;
import it.agilis.mens.azzeroCO2.shared.model.amministrazione.CouponModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 3/15/11
 * Time: 9:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class HustonServiceImpl extends RemoteServiceServlet implements
        HustonService {

    @Autowired(required = true)
    @Qualifier("azzeroCO2Register")
    private AzzeroCO2Register azzeroCO2Register;

    public AzzeroCO2Register getAzzeroCO2Register() {
        return azzeroCO2Register;
    }

    public void setAzzeroCO2Register(AzzeroCO2Register azzeroCO2Register) {
        this.azzeroCO2Register = azzeroCO2Register;
    }

    @Override
    public String logIn(String userInfo) throws IllegalArgumentException {

        //    userInfo.setProfile(Profile.Administrator);
        return userInfo;
    }

    @Override
    public List<CouponModel> getListOfCoupon() throws IllegalArgumentException {
        return Utils.getListOfCoupon(azzeroCO2Register.getListofCoupon());
    }


    @Override
    public Boolean saveCoupons(List<CouponModel> modifiedRecords) throws IllegalArgumentException {
        try {
            List<Coupon> coupons = new ArrayList<Coupon>();
            for (CouponModel r : modifiedRecords) {
                coupons.add(Utils.getCoupon(r));
            }
            azzeroCO2Register.saveCoupons(coupons);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public void saveCoupon(CouponModel couponModel) throws IllegalArgumentException {
        try {
            azzeroCO2Register.saveCoupon(Utils.getCoupon(couponModel));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
