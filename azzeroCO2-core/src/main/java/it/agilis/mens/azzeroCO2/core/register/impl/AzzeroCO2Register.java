package it.agilis.mens.azzeroCO2.core.register.impl;

import it.agilis.mens.azzeroCO2.core.dao.ICouponDAO;
import it.agilis.mens.azzeroCO2.core.dao.IOrdineDAO;
import it.agilis.mens.azzeroCO2.core.dao.IUserInfoDAO;
import it.agilis.mens.azzeroCO2.core.entity.Coupon;
import it.agilis.mens.azzeroCO2.core.entity.Ordine;
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

    public IUserInfoDAO getUserInfoDAO() {
        return userInfoDAO;
    }

    public void setOrdineDAO(IOrdineDAO ordineDAO) {
        this.ordineDAO = ordineDAO;
    }

    public IOrdineDAO getOrdineDAO() {
        return ordineDAO;
    }


    public List<Ordine> getOrdini() {
        return ordineDAO.getListOfOrdini();
    }

    public List<Coupon> getListofCoupon() {
        return couponDAO.getListOfCoupon();
    }

    public void setCouponDAO(ICouponDAO couponDAO) {
        this.couponDAO = couponDAO;
    }

    public ICouponDAO getCouponDAO() {
        return couponDAO;
    }

    public void saveCoupon(Coupon coupon) throws Exception {
        couponDAO.save(coupon);
    }

    public void saveCoupons(List<Coupon> coupons) throws Exception {
        for(Coupon c: coupons){
            couponDAO.save(c);
        }
    }
}
