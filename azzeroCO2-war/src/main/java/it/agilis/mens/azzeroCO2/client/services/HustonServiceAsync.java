package it.agilis.mens.azzeroCO2.client.services;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface HustonServiceAsync {
    // UserInfo logIn(UserInfo userInfo) throws IllegalArgumentException;
    void logIn(String userInfo, AsyncCallback<String> async);
}
