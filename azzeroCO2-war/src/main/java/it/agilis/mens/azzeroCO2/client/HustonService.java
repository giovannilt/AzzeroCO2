package it.agilis.mens.azzeroCO2.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import it.agilis.mens.azzeroCO2.core.entity.UserInfo;

@RemoteServiceRelativePath("huston")
public interface HustonService extends RemoteService {
    UserInfo logIn(UserInfo userInfo) throws IllegalArgumentException;
}
