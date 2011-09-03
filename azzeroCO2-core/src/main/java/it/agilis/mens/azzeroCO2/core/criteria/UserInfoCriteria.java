package it.agilis.mens.azzeroCO2.core.criteria;

import it.agilis.mens.azzeroCO2.core.entity.UserInfo;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 8/25/11
 * Time: 7:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserInfoCriteria implements Serializable, SelectionCriteria {
    private String userName;

    public DetachedCriteria getDetachedCriteria() {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(UserInfo.class, "UserInfo");
        if(userName!=null && userName.length()>0){
        	detachedCriteria.add(Restrictions.eq("userName", getUserName()).ignoreCase());
        }
        return  detachedCriteria;
    }

    public void setUsername(String userName) {
        this.userName=userName;
    }

    public String getUserName() {
        return userName;
    }

    @Override
    public void reset() {
       setUsername(null);
    }
}
