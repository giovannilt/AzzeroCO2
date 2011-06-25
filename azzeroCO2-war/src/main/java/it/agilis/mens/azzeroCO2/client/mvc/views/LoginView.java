package it.agilis.mens.azzeroCO2.client.mvc.views;

import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.extjs.gxt.ui.client.mvc.View;
import it.agilis.mens.azzeroCO2.client.login.LoginDialog;
import it.agilis.mens.azzeroCO2.client.mvc.events.LoginEvents;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 3/17/11
 * Time: 11:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class LoginView extends View {
    // la finestra di login è una  dialog modale
    private LoginDialog loginDialog;

    public LoginView(Controller controller) {
        super(controller);
    }

    @Override
    protected void initialize() {
        loginDialog = new LoginDialog();
    }

    @Override
    protected void handleEvent(AppEvent event) {
        if (event.getType() == LoginEvents.showForm) {
            // mostra la finestra di login
            loginDialog.show();
        } else if (event.getType() == LoginEvents.hideForm) {
            // nscaonde la finestra di login
            loginDialog.hide();
        }
    }
}