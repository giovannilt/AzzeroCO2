package it.agilis.mens.azzeroCO2.pages;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.extjs.gxt.ui.client.mvc.View;
import com.google.gwt.user.client.rpc.AsyncCallback;
import it.agilis.mens.azzeroCO2.client.HustonServiceAsync;
import it.agilis.mens.azzeroCO2.core.entity.UserInfo;
import it.agilis.mens.azzeroCO2.pages.login.controller.LoginEvents;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 3/13/11
 * Time: 10:28 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class Apollo extends Controller {

    private static HustonServiceAsync huston = null;

    public UserInfo doLogin(final UserInfo user, final View view) {
        initGreetingService();
        huston.logIn(user, new AsyncCallback<UserInfo>(){

            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("Error " + throwable.getMessage());
            }

            @Override
            public void onSuccess(UserInfo userInfo) {
                 if (user == null) {
                    AppEvent event = new AppEvent(LoginEvents.showForm);
                    event.setData("message", "Username o password non validi");
                    forwardToView(view, event);
                } else {
                    // login con successo. Mette l’oggetto nel registry e
                    // manda alla view un evento per la chiusura
                    // della finestra di login
                    Registry.register("loggedUser", user);

                    userInfo.setProfile(user.getProfile());
                    AppEvent event = new AppEvent(LoginEvents.hideForm);
                    forwardToView(view, event);
                }

                 user.setProfile(userInfo.getProfile());
            }
        });

        return user;
    }

    private void initGreetingService() {

        if(huston==null){
            huston = HustonServiceAsync.Util.getInstance();

        }
    }
}