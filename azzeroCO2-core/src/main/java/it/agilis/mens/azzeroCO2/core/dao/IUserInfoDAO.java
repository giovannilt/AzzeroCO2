package it.agilis.mens.azzeroCO2.core.dao;

import it.agilis.mens.azzeroCO2.core.entity.UserInfo;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 3/10/11
 * Time: 10:17 PM
 * To change this template use File | Settings | File Templates.
 */
public interface IUserInfoDAO {
    UserInfo save(UserInfo userInfo) throws Exception;

    UserInfo findUserInfo(String userName);

}
