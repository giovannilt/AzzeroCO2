package it.agilis.mens.azzeroCO2.server.services;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import it.agilis.mens.azzeroCO2.client.services.HustonService;

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
    public String logIn(String userInfo) throws IllegalArgumentException {

    //    userInfo.setProfile(Profile.Administrator);
        return userInfo;
    }
}
