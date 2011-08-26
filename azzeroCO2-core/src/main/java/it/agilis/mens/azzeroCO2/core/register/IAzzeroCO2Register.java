package it.agilis.mens.azzeroCO2.core.register;

import it.agilis.mens.azzeroCO2.core.entity.Coupon;
import it.agilis.mens.azzeroCO2.core.entity.Ordine;
import it.agilis.mens.azzeroCO2.core.entity.UserInfo;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 3/11/11
 * Time: 3:27 PM
 * To change this template use File | Settings | File Templates.
 */
public interface IAzzeroCO2Register {

    void saveCoupon(Coupon coupon) throws Exception;

    void saveCoupons(List<Coupon> coupons) throws Exception;

    void saveUserInfo(UserInfo userInfo) throws Exception;

    List<Ordine> getOrdini() throws Exception;

    List<Coupon> getListofCoupon() throws Exception;

    UserInfo getUserInfo(String userInfo) throws Exception;
}

