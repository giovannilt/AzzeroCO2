package it.agilis.mens.azzeroCO2.core.register.impl;

import it.agilis.mens.azzeroCO2.core.dao.ICouponDAO;
import it.agilis.mens.azzeroCO2.core.dao.IOrdineDAO;
import it.agilis.mens.azzeroCO2.core.dao.IUserInfoDAO;
import it.agilis.mens.azzeroCO2.core.entity.Coupon;
import it.agilis.mens.azzeroCO2.core.entity.Ordine;
import it.agilis.mens.azzeroCO2.core.entity.UserInfo;
import it.agilis.mens.azzeroCO2.core.register.IAzzeroCO2Register;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 3/10/11
 * Time: 10:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class AzzeroCO2Register implements IAzzeroCO2Register {

    @Autowired
    private IUserInfoDAO userInfoDAO;
    @Autowired
    private IOrdineDAO ordineDAO;
    @Autowired
    private ICouponDAO couponDAO;

    public void setUserInfoDAO(IUserInfoDAO userInfoDAO) {
        this.userInfoDAO = userInfoDAO;
    }


    public void setOrdineDAO(IOrdineDAO ordineDAO) {
        this.ordineDAO = ordineDAO;
    }

    public void setCouponDAO(ICouponDAO couponDAO) {
        this.couponDAO = couponDAO;
    }

    public IUserInfoDAO getUserInfoDAO() {
        return userInfoDAO;
    }

    public IOrdineDAO getOrdineDAO() {
        return ordineDAO;
    }

    public ICouponDAO getCouponDAO() {
        return couponDAO;
    }



    @Override
    public List<Ordine> getOrdini()throws Exception {
        return ordineDAO.getListOfOrdini();
    }
    @Override
    public List<Coupon> getListofCoupon() throws Exception{
        return couponDAO.getListOfCoupon();
    }


    @Override
    public void saveCoupon(Coupon coupon) throws Exception {
        couponDAO.save(coupon);
    }

    @Override
    public void saveCoupons(List<Coupon> coupons) throws Exception {
        for (Coupon c : coupons) {
            couponDAO.save(c);
        }
    }

    @Override
    public void saveUserInfo(UserInfo userInfo) throws Exception {
        userInfoDAO.save(userInfo);
    }
}
