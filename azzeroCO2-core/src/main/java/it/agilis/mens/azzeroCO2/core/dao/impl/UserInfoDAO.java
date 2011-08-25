package it.agilis.mens.azzeroCO2.core.dao.impl;

import it.agilis.mens.azzeroCO2.core.criteria.UserInfoCriteria;
import it.agilis.mens.azzeroCO2.core.dao.DAOSupport;
import it.agilis.mens.azzeroCO2.core.dao.IUserInfoDAO;
import it.agilis.mens.azzeroCO2.core.entity.UserInfo;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 3/10/11
 * Time: 10:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserInfoDAO extends DAOSupport implements IUserInfoDAO {
    @Override
    public void save(UserInfo userInfo) throws Exception {
        saveObject(userInfo);
    }

    @Override
    public UserInfo findUserInfo(String userName) {
        UserInfoCriteria userCriteria = new UserInfoCriteria();
        userCriteria.setUsername(userName);

        List<UserInfo> userInfoList = (List<UserInfo>) getList(userCriteria.getDetachedCriteria(), true);
        if (userInfoList.size() > 0) {
            return userInfoList.get(0);
        }

        return null;
    }
}
