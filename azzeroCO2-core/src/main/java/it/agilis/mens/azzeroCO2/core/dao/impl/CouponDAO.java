package it.agilis.mens.azzeroCO2.core.dao.impl;

import it.agilis.mens.azzeroCO2.core.dao.DAOSupport;
import it.agilis.mens.azzeroCO2.core.dao.ICouponDAO;
import it.agilis.mens.azzeroCO2.core.entity.Coupon;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 8/18/11
 * Time: 8:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class CouponDAO  extends DAOSupport implements ICouponDAO{
    @Override
    public List<Coupon> getListOfCoupon() {
          DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Coupon.class, "coupon");
        return (List<Coupon>) getList(detachedCriteria, true);
    }

    @Override
    public void save(Coupon coupon) throws Exception {

        saveObject(coupon);
    }
}
