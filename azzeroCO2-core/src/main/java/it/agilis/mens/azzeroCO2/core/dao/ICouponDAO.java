package it.agilis.mens.azzeroCO2.core.dao;

import it.agilis.mens.azzeroCO2.core.criteria.CouponCriteria;
import it.agilis.mens.azzeroCO2.core.entity.Coupon;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 8/18/11
 * Time: 8:39 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ICouponDAO {
    List<Coupon> getListOfCoupon();

    List<Coupon> getListOfCoupon(CouponCriteria criteria);

    void save(Coupon coupon) throws Exception;
}
