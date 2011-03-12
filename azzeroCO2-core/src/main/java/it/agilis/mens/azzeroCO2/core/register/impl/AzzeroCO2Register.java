package it.agilis.mens.azzeroCO2.core.register.impl;

import it.agilis.mens.azzeroCO2.core.dao.impl.UserInfoDAO;
import it.agilis.mens.azzeroCO2.core.register.IAzzeroCO2Register;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 3/10/11
 * Time: 10:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class AzzeroCO2Register implements IAzzeroCO2Register {
    private UserInfoDAO userInfoDAO;

    public void setUserInfoDAO(UserInfoDAO userInfoDAO) {
        this.userInfoDAO = userInfoDAO;
    }

    public UserInfoDAO getUserInfoDAO() {
        return userInfoDAO;
    }
}
