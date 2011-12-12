package it.agilis.mens.azzeroCO2.core.criteria;

import it.agilis.mens.azzeroCO2.core.entity.Ordine;
import it.agilis.mens.azzeroCO2.core.entity.UserInfo;
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
public class OrdineCriteria implements Serializable, SelectionCriteria {
    private UserInfo userInfo;

    @Override
    public DetachedCriteria getDetachedCriteria() {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Ordine.class, "Ordine");
        if (userInfo != null) {
            checkFieldEq(detachedCriteria, "utente", userInfo);
        }
        return detachedCriteria;
    }

    @Override
    public void reset() {
        setUserInfo(null);
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }


    private void checkFieldEq(DetachedCriteria detachedCriteria, String name, Object fieldValue) {
        if (fieldValue != null) {
            detachedCriteria.add(Restrictions.eq(name, fieldValue));
        }
    }
}
