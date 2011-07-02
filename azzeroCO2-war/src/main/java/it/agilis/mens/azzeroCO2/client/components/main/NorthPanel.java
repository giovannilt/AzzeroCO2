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
import it.agilis.mens.azzeroCO2.client.components.eventi.Eventi;
import it.agilis.mens.azzeroCO2.client.mvc.events.AzzeroCO2Events;
import it.agilis.mens.azzeroCO2.client.mvc.events.CentralEvents;
import it.agilis.mens.azzeroCO2.client.mvc.events.LoginEvents;

import javax.xml.ws.Dispatch;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 5/15/11
 * Time: 11:40 AM
 * To change this template use File | Settings | File Templates.
 */
public class NorthPanel extends LayoutContainer {

    protected void onRender(Element target, int index) {
        super.onRender(target, index);

        LayoutContainer c = new LayoutContainer();
        HBoxLayout layout = new HBoxLayout();
        layout.setPadding(new Padding(1));
        layout.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.MIDDLE);
        layout.setPack(BoxLayout.BoxLayoutPack.END);
        c.setLayout(layout);

        HBoxLayoutData layoutData = new HBoxLayoutData(new Margins(0, 5, 0, 0));

        Button login = new Button();
        login.setText("Login");
       // login.setSize("100px","100px");
        login.addSelectionListener(new SelectionListener<ButtonEvent>() {
            public void componentSelected(ButtonEvent ce) {
              //  session.getAzzeroCO2Register();
               // loginDialog.show();
                Dispatcher.forwardEvent(LoginEvents.ShowForm);
            }

        });
        c.add(login, layoutData);
        Button home= new Button("Home");
        home.addSelectionListener(new SelectionListener<ButtonEvent>() {
            public void componentSelected(ButtonEvent ce) {
                //Dispatcher.forwardEvent(AzzeroCO2Events.ShowHome);
                 Dispatcher.forwardEvent(CentralEvents.ShowPanel, Eventi.MAIN);
            }

        });

        c.add(home, layoutData);
        c.add(new Button("Registrati"), layoutData);

        HBoxLayoutData layoutData2 = new HBoxLayoutData(new Margins(0));
        c.add(new Button("Into"), layoutData2);
        add(c, new FlowData(1));

        Image azzeroCO2Log = new Image("/azzeroCO2/imgs/header.png");
        azzeroCO2Log.setWidth("100%");
        add(azzeroCO2Log, new FlowData(1));
    }

}
