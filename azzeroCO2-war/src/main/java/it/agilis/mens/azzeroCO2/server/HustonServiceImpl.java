package it.agilis.mens.azzeroCO2.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import it.agilis.mens.azzeroCO2.client.HustonService;
import it.agilis.mens.azzeroCO2.core.entity.Profile;
import it.agilis.mens.azzeroCO2.core.entity.UserInfo;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 3/15/11
 * Time: 9:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class HustonServiceImpl extends RemoteServiceServlet implements
        HustonService {

    @Override
    public UserInfo logIn(UserInfo userInfo) throws IllegalArgumentException {

        userInfo.setProfile(Profile.Administrator);
        return userInfo;
    }
}
