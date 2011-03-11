package it.agilis.mens.azzeroCO2.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

public class azzeroCO2 implements EntryPoint {

    public void onModuleLoad() {
        BorderLayoutExample hello = new BorderLayoutExample();
        RootPanel.get().add(hello);

    }
}
