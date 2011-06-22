package it.agilis.mens.azzeroCO2.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("huston")
public interface HustonService extends RemoteService {
   // UserInfo logIn(UserInfo userInfo) throws IllegalArgumentException;
   String logIn(String userInfo) throws IllegalArgumentException;
}
