package it.agilis.mens.azzeroCO2.client.components.main;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.util.Padding;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.*;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Image;
import it.agilis.mens.azzeroCO2.client.AzzeroCO2Resources;
import it.agilis.mens.azzeroCO2.client.mvc.events.*;
import it.agilis.mens.azzeroCO2.shared.Eventi;
import it.agilis.mens.azzeroCO2.shared.Profile;
import it.agilis.mens.azzeroCO2.shared.model.registrazione.UserInfoModel;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 5/15/11
 * Time: 11:40 AM
 * To change this template use File | Settings | File Templates.
 */
public class NorthPanel extends LayoutContainer {
    private Button registrati = new Button();
    private Button amministrazione = new Button();
    // private Button pagamento = new Button();
    private Button login = new Button();
    private Boolean islogedIn = false;
    private Button info = new Button();
    private LayoutContainer c = new LayoutContainer();
    private HBoxLayoutData layoutData = new HBoxLayoutData(new Margins(0, 5, 0, 0));
    private UserInfoModel userInfoModel;
    private Button home = new Button("Home");


    protected void onRender(Element target, int index) {
        super.onRender(target, index);

        HBoxLayout layout = new HBoxLayout();
        layout.setPadding(new Padding(1));
        layout.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.MIDDLE);
        layout.setPack(BoxLayout.BoxLayoutPack.END);
        c.setLayout(layout);

        info.setText("Info");
        info.addSelectionListener(new SelectionListener<ButtonEvent>() {
            public void componentSelected(ButtonEvent ce) {
                Dispatcher.forwardEvent(AzzeroCO2Events.ShowInfo);
            }
        });
        info.setVisible(false);
        registrati = new Button("Registrati");
        registrati.addSelectionListener(new SelectionListener<ButtonEvent>() {
            public void componentSelected(ButtonEvent ce) {
                Dispatcher.forwardEvent(LoginEvents.HideForm, ce);
                Dispatcher.forwardEvent(RegisterEvents.ShowForm, ce);
            }
        });

        home.addSelectionListener(new SelectionListener<ButtonEvent>() {
            public void componentSelected(ButtonEvent ce) {
                Dispatcher.forwardEvent(CentralEvents.ShowPanel, Eventi.MAIN);
            }
        });
        amministrazione.setVisible(false);
        amministrazione.setText("Area personale");
        amministrazione.addSelectionListener(new SelectionListener<ButtonEvent>() {
            public void componentSelected(ButtonEvent ce) {
                Dispatcher.forwardEvent(AmministrazioneEvents.ShowAmministrazione);
                Dispatcher.forwardEvent(CentralEvents.ShowPanel, Eventi.AMMINISTRAZIONE);
            }
        });
        login.setText("Login");
        login.addSelectionListener(new SelectionListener<ButtonEvent>() {
            public void componentSelected(ButtonEvent ce) {
                if (!islogedIn) {
                    Dispatcher.forwardEvent(LoginEvents.ShowForm);
                } else {
                    Dispatcher.forwardEvent(LoginEvents.LogOut);
                    showLogin();
                    Window.open(GWT.getHostPageBaseURL(), "_self", "");

                }
            }
        });
        login.setWidth(70);

        /*   pagamento.setText("pagamento");
   pagamento.addSelectionListener(new SelectionListener<ButtonEvent>() {
       public void componentSelected(ButtonEvent ce) {
           Dispatcher.forwardEvent(PagamentoSellaEvents.ShowForm);
       }
   });
    c.add(pagamento, layoutData);   */

        c.add(info, layoutData);
        c.add(amministrazione, layoutData);

        c.add(registrati, layoutData);
        c.add(login, layoutData);
        c.add(home, layoutData);

        add(c, new FlowData(1));

        Image azzeroCO2Log = new Image(AzzeroCO2Resources.INSTANCE.header());
        add(azzeroCO2Log, layoutData);

    }

    public void showLogout() {
        login.setText("LogOut");
        islogedIn = true;
        registrati.setVisible(false);
        //  c.remove(registrati);
    }

    public void showLogin() {
        login.setText("LogIn");
        islogedIn = false;
        registrati.setVisible(true);
        //  c.add(registrati, layoutData);
    }

    public void setUserInfo(UserInfoModel userInfoModel) {
        this.userInfoModel = userInfoModel;
        if (userInfoModel != null &&
                userInfoModel.getProfilo() != null) {
            if (userInfoModel.getProfilo().intValue() == Profile.Administrator.ordinal()) {
                // removeALL();
                info.setVisible(false);
                amministrazione.setVisible(true);
                showLogout();
            } else if (userInfoModel.getProfilo().intValue() == Profile.SuperAdministrator.ordinal()) {
                // removeALL();
                info.setVisible(true);
                amministrazione.setVisible(true);
                showLogout();
            } else if (userInfoModel.getProfilo().intValue() == Profile.User.ordinal()) {
                //   removeALL();
                amministrazione.setVisible(true);
                showLogout();
            } else {
                info.setVisible(false);
                amministrazione.setVisible(false);
            }
            c.layout(true);
        }
    }
}
