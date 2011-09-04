package it.agilis.mens.azzeroCO2.client.components.main;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.util.Padding;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.BoxLayout;
import com.extjs.gxt.ui.client.widget.layout.FlowData;
import com.extjs.gxt.ui.client.widget.layout.HBoxLayout;
import com.extjs.gxt.ui.client.widget.layout.HBoxLayoutData;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Image;
import it.agilis.mens.azzeroCO2.client.AzzeroCO2Resources;
import it.agilis.mens.azzeroCO2.client.mvc.events.AmministrazioneEvents;
import it.agilis.mens.azzeroCO2.client.mvc.events.CentralEvents;
import it.agilis.mens.azzeroCO2.client.mvc.events.LoginEvents;
import it.agilis.mens.azzeroCO2.client.mvc.events.RegisterEvents;
import it.agilis.mens.azzeroCO2.shared.Eventi;

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
    private Button login = new Button();
    private Boolean islogedIn = false;
    private LayoutContainer c = new LayoutContainer();
    private HBoxLayoutData layoutData = new HBoxLayoutData(new Margins(0, 5, 0, 0));

    protected void onRender(Element target, int index) {
        super.onRender(target, index);


        HBoxLayout layout = new HBoxLayout();
        layout.setPadding(new Padding(1));
        layout.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.MIDDLE);
        layout.setPack(BoxLayout.BoxLayoutPack.END);
        c.setLayout(layout);

        Button home = new Button("Home");
        home.addSelectionListener(new SelectionListener<ButtonEvent>() {
            public void componentSelected(ButtonEvent ce) {
                Dispatcher.forwardEvent(CentralEvents.ShowPanel, Eventi.MAIN);
            }
        });
        registrati = new Button("Registrati");
        registrati.addSelectionListener(new SelectionListener<ButtonEvent>() {
            public void componentSelected(ButtonEvent ce) {
                Dispatcher.forwardEvent(LoginEvents.HideForm, ce);
                Dispatcher.forwardEvent(RegisterEvents.ShowForm, ce);
            }
        });

        c.add(home, layoutData);

        amministrazione.setText("Impostazioni");
        amministrazione.addSelectionListener(new SelectionListener<ButtonEvent>() {
            public void componentSelected(ButtonEvent ce) {
                Dispatcher.forwardEvent(AmministrazioneEvents.ShowAmministrazione);
                Dispatcher.forwardEvent(CentralEvents.ShowPanel, Eventi.AMMINISTRAZIONE);

            }
        });
        c.add(amministrazione, layoutData);


        c.add(registrati, layoutData);

        login.setText("Login");
        login.addSelectionListener(new SelectionListener<ButtonEvent>() {
            public void componentSelected(ButtonEvent ce) {
                if (!islogedIn) {
                    Dispatcher.forwardEvent(LoginEvents.ShowForm);
                } else {
                    Dispatcher.forwardEvent(LoginEvents.LogOut);
                    showLogin();
                    Window.open(GWT.getModuleBaseURL(), "_self", "");

                }
            }
        });
        c.add(login, layoutData);

        add(c, new FlowData(1));

        Image azzeroCO2Log = new Image(AzzeroCO2Resources.INSTANCE.header());
        add(azzeroCO2Log, layoutData);
    }

    public void showLogout() {
        login.setText("LogOut");
        islogedIn = true;
        //registrati.setVisible(false);
        c.remove(registrati);
    }

    public void showLogin() {
        login.setText("LogIn");
        islogedIn = false;
        c.add(registrati, layoutData);
    }
}
