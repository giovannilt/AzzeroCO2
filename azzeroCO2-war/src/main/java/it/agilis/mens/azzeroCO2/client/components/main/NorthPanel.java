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
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Image;
import it.agilis.mens.azzeroCO2.client.AzzeroCO2Resources;
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
    private Button logOut = new Button();

    protected void onRender(Element target, int index) {
        super.onRender(target, index);

        LayoutContainer c = new LayoutContainer();
        HBoxLayout layout = new HBoxLayout();
        layout.setPadding(new Padding(1));
        layout.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.MIDDLE);
        layout.setPack(BoxLayout.BoxLayoutPack.END);
        c.setLayout(layout);

        HBoxLayoutData layoutData = new HBoxLayoutData(new Margins(0, 5, 0, 0));
        Button home = new Button("Home");
        home.addSelectionListener(new SelectionListener<ButtonEvent>() {
            public void componentSelected(ButtonEvent ce) {
                Dispatcher.forwardEvent(CentralEvents.ShowPanel, Eventi.MAIN);
            }
        });

        c.add(home, layoutData);
        login.setText("Login");
        login.addSelectionListener(new SelectionListener<ButtonEvent>() {
            public void componentSelected(ButtonEvent ce) {
                Dispatcher.forwardEvent(LoginEvents.ShowForm);
            }
        });
        c.add(login, layoutData);

        logOut.setText("LogOut");
        logOut.addSelectionListener(new SelectionListener<ButtonEvent>() {
            public void componentSelected(ButtonEvent ce) {
                showLogin();
                Dispatcher.forwardEvent(LoginEvents.LogOut);
            }
        });
        c.add(logOut, layoutData);
        logOut.setVisible(false);

        registrati = new Button("Registrati");
        registrati.addSelectionListener(new SelectionListener<ButtonEvent>() {
            public void componentSelected(ButtonEvent ce) {
                Dispatcher.forwardEvent(LoginEvents.HideForm, ce);
                Dispatcher.forwardEvent(RegisterEvents.ShowForm, ce);
            }
        });
        c.add(registrati, layoutData);


        amministrazione.setText("Amministrazione");
        amministrazione.addSelectionListener(new SelectionListener<ButtonEvent>() {
            public void componentSelected(ButtonEvent ce) {
                Dispatcher.forwardEvent(CentralEvents.ShowPanel, Eventi.AMMINISTRAZIONE);
            }
        });
        c.add(amministrazione, layoutData);

        add(c, new FlowData(1));

        Image azzeroCO2Log = new Image(AzzeroCO2Resources.INSTANCE.header());
        add(azzeroCO2Log, layoutData);
    }

    public void showLogout() {
        login.setVisible(false);
        logOut.setVisible(true);
    }

    public void showLogin() {
        // TODO eliminare la sessione
        login.setVisible(true);
        logOut.setVisible(false);
    }
}
