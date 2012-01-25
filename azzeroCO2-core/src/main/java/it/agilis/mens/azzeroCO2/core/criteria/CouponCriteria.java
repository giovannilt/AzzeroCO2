package it.agilis.mens.azzeroCO2.core.criteria;

import it.agilis.mens.azzeroCO2.core.entity.Coupon;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 9/2/11
 * Time: 11:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class CouponCriteria implements Serializable, SelectionCriteria {
    private String codice;
    private Boolean valid;

    @Override
    public DetachedCriteria getDetachedCriteria() {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Coupon.class, "Coupon");
        if (codice != null && !"".equalsIgnoreCase(codice)) {
            checkFieldEq(detachedCriteria, "codice", codice);
        }
        if (valid == true) {
            detachedCriteria.add(Restrictions.eq("stato", valid));
        }
        return detachedCriteria;
    }

    @Override
    public void reset() {
        setCodice(null);
    }

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    private void checkFieldEq(DetachedCriteria detachedCriteria, String name, Object fieldValue) {
        if (fieldValue != null) {
            detachedCriteria.add(Restrictions.eq(name, fieldValue));
        }
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
}
