package it.agilis.mens.azzeroCO2.core.register.impl;

import it.agilis.mens.azzeroCO2.core.dao.impl.OrdineDAO;
import it.agilis.mens.azzeroCO2.core.dao.impl.UserInfoDAO;
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
    private UserInfoDAO userInfoDAO;
    private OrdineDAO ordineDAO;

    public void setUserInfoDAO(UserInfoDAO userInfoDAO) {
        this.userInfoDAO = userInfoDAO;
    }

    public UserInfoDAO getUserInfoDAO() {
        return userInfoDAO;
    }

    public void setOrdineDAO(OrdineDAO ordineDAO) {
        this.ordineDAO = ordineDAO;
    }

    public OrdineDAO getOrdineDAO() {
        return ordineDAO;
    }


    public List<Ordine> getOrdini() {
        return ordineDAO.getListOrdini();
    }
}
