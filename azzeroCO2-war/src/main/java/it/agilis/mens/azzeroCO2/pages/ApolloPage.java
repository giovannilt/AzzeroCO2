package it.agilis.mens.azzeroCO2.pages;

import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import it.agilis.mens.azzeroCO2.client.HustonServiceAsync;
import it.agilis.mens.azzeroCO2.core.entity.UserInfo;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 3/13/11
 * Time: 10:28 AM
 * To change this template use File | Settings | File Templates.
 */
public class ApolloPage  {

    HustonServiceAsync huston;

    public UserInfo logIn(final UserInfo user) {
        initGreetingService();


        huston.logIn(user, new AsyncCallback<UserInfo>(){

            @Override
            public void onFailure(Throwable throwable) {

            }

            @Override
            public void onSuccess(UserInfo userInfo) {
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
