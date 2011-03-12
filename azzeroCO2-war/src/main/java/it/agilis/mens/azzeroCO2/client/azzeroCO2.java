package it.agilis.mens.azzeroCO2.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import it.agilis.mens.azzeroCO2.pages.main.MainPage;

public class azzeroCO2 implements EntryPoint {

    public void onModuleLoad() {
        MainPage hello = new MainPage();
        RootPanel.get().add(hello);

    }
}
