package it.agilis.mens.azzeroCO2.client.mvc.views;

import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.View;
import it.agilis.mens.azzeroCO2.client.components.login.RegisterDialog;
import it.agilis.mens.azzeroCO2.client.mvc.controllers.RegisterController;
import it.agilis.mens.azzeroCO2.client.mvc.events.RegisterEvents;

/**
 * Created by IntelliJ IDEA.
 * User: serenadimaida
 * Date: 23/08/11
 * Time: 16:47
 * To change this template use File | Settings | File Templates.
 */
public class RegisterView extends View {
    private RegisterDialog registerDialog;

    public RegisterView(RegisterController registerController) {
        super(registerController);
    }

    @Override
    protected void initialize() {
        super.initialize();
        registerDialog = new RegisterDialog();
    }

    @Override
    protected void handleEvent(AppEvent event) {
        if (event.getType() == RegisterEvents.ShowForm) {
            registerDialog.show();
        } else if (event.getType() == RegisterEvents.HideForm) {
            registerDialog.hide();
        }
    }

    public void hideStatus() {
        registerDialog.hideStatus();
    }
}
