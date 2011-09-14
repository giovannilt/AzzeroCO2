package it.agilis.mens.azzeroCO2.client.mvc.views;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.event.*;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.mvc.View;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.util.Padding;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.*;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;
import it.agilis.mens.azzeroCO2.client.AzzeroCO2Resources;
import it.agilis.mens.azzeroCO2.client.mvc.events.AzzeroCO2Events;
import it.agilis.mens.azzeroCO2.client.mvc.events.CentralEvents;
import it.agilis.mens.azzeroCO2.client.mvc.events.EventoEvents;
import it.agilis.mens.azzeroCO2.shared.Eventi;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 3/23/11
 * Time: 10:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class CentralView extends View {
    private final ContentPanel centralPanel = new ContentPanel();
    private CardLayout layout = new CardLayout();

    public CentralView(Controller controller) {
        super(controller);
    }

    @Override
    protected void handleEvent(AppEvent event) {
        EventType eventType = event.getType();
        if (eventType.equals(AzzeroCO2Events.Init)) {
            onInit(event);
        } else if (eventType.equals(CentralEvents.EventoPanelReady)) {
            onContentReady(event);
        } else if (eventType.equals(CentralEvents.UnAnnoDiAttivitaPanelReady)) {
            onContentReady(event);
        } else if (eventType.equals(CentralEvents.UnaPubblicazioneReady)) {
            onContentReady(event);
        } else if (eventType.equals(CentralEvents.WebPanelReady)) {
            onContentReady(event);
        } else if (eventType.equals(CentralEvents.ConosciCO2PanelReady)) {
            onContentReady(event);
        } else if (eventType.equals(CentralEvents.SitoWebPanelReady)) {
            onContentReady(event);
        } else if (eventType.equals(CentralEvents.AmministrazioneReady)) {
            onContentReady(event);
        } else if (eventType.equals(CentralEvents.ShowPanel)) {
            setActiveItem(event.<Eventi>getData());
        }
    }

    private void onContentReady(AppEvent event) {
        centralPanel.add(event.<Widget>getData());
    }

    private void setActiveItem(Eventi evento) {
        for (Component item : centralPanel.getItems()) {
            if (item.getTitle().equalsIgnoreCase(evento.name())) {
                layout.setActiveItem(item);
            }
        }
    }

    private ContentPanel getStartContent() {
        final ContentPanel _return = new ContentPanel();
        _return.setHeaderVisible(false);
        final BorderLayout layoutBorder = new BorderLayout();
        _return.setLayout(layoutBorder);
        _return.setStyleAttribute("padding", "1px");

        ContentPanel center = new ContentPanel();
        center.setHeading("Compensa le emissioni delle tue attivita'!");

        center.setScrollMode(Style.Scroll.AUTOX);
        BorderLayoutData centerData = new BorderLayoutData(Style.LayoutRegion.CENTER);
        centerData.setMargins(new Margins(0));
        _return.add(center, centerData);

        {  // Primo Rigo "EVENTI"
            LayoutContainer c = new LayoutContainer();
            HBoxLayout layout = new HBoxLayout();
            layout.setPadding(new Padding(0));
            layout.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.STRETCH);
            // layout.setPack(BoxLayout.BoxLayoutPack.START);
            c.setLayout(layout);
            HBoxLayoutData layoutData = new HBoxLayoutData(new Margins(1, 1, 1, 1));
            Button unEvento = new Button("Un Evento");

            unEvento.setIcon(AbstractImagePrototype.create(AzzeroCO2Resources.INSTANCE.evento()));
            unEvento.setIconAlign(Style.IconAlign.TOP);

            unEvento.addSelectionListener(new SelectionListener<ButtonEvent>() {
                public void componentSelected(ButtonEvent ce) {
                    Dispatcher.forwardEvent(EventoEvents.CaricaCoefficienti);
                    Dispatcher.forwardEvent(EventoEvents.CaricaProgettiDiCompensazione);
                    setActiveItem(Eventi.EVENTO);
                }
            });
            unEvento.setSize(319, 300);
            //   layoutData.setFlex(1);
            c.add(unEvento, layoutData);
            Button unAnno = new Button("Un Anno Di attivita'");
            unAnno.setIcon(AbstractImagePrototype.create(AzzeroCO2Resources.INSTANCE.unAnnoDiAttivita()));
            unAnno.setIconAlign(Style.IconAlign.TOP);


            unAnno.addSelectionListener(new SelectionListener<ButtonEvent>() {
                public void componentSelected(ButtonEvent ce) {
                    Dispatcher.forwardEvent(EventoEvents.CaricaCoefficienti);
                    Dispatcher.forwardEvent(EventoEvents.CaricaProgettiDiCompensazione);
                    setActiveItem(Eventi.ANNO_DI_ATTIVITA);
                }
            });
            //unAnno.setSize(320, 280);
            unAnno.setSize(319, 300);
            c.setSize(640, 300);
            //c.setSize(640, 280);
            layoutData.setFlex(1);
            c.add(unAnno, layoutData);
            center.add(c, new FlowData(1));
        }

        {  // Secondo Rigo "EVENTI"
            LayoutContainer c = new LayoutContainer();
            HBoxLayout layout = new HBoxLayout();
            layout.setPadding(new Padding(0));
            layout.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.MIDDLE);
            layout.setPack(BoxLayout.BoxLayoutPack.START);
            c.setLayout(layout);
            HBoxLayoutData layoutData = new HBoxLayoutData(new Margins(1, 1, 1, 1));

            Button pubblicazione = new Button("Una pubblicazione");
            pubblicazione.setIcon(AbstractImagePrototype.create(AzzeroCO2Resources.INSTANCE.unaPubblicazione()));
            pubblicazione.setIconAlign(Style.IconAlign.TOP);
            pubblicazione.addSelectionListener(new SelectionListener<ButtonEvent>() {
                public void componentSelected(ButtonEvent ce) {
                    Dispatcher.forwardEvent(EventoEvents.CaricaCoefficienti);
                    Dispatcher.forwardEvent(EventoEvents.CaricaProgettiDiCompensazione);
                    setActiveItem(Eventi.UNA_PUBBLICAZIONE);
                }
            });
            pubblicazione.setSize(213, 210);
            c.add(pubblicazione, layoutData);


            Button web = new Button("Un sito web");
            web.setIcon(AbstractImagePrototype.create(AzzeroCO2Resources.INSTANCE.unSitoWeb()));
            web.setIconAlign(Style.IconAlign.TOP);
            web.addSelectionListener(new SelectionListener<ButtonEvent>() {
                public void componentSelected(ButtonEvent ce) {
                    Dispatcher.forwardEvent(EventoEvents.CaricaCoefficienti);
                    Dispatcher.forwardEvent(EventoEvents.CaricaProgettiDiCompensazione);
                    setActiveItem(Eventi.WEB);
                }
            });
            web.setSize(213, 210);
            c.add(web, layoutData);


            Button co2 = new Button("Conosco la CO2");
            co2.setIcon(AbstractImagePrototype.create(AzzeroCO2Resources.INSTANCE.conoscoLaCO2()));
            co2.setIconAlign(Style.IconAlign.TOP);
            co2.addSelectionListener(new SelectionListener<ButtonEvent>() {
                public void componentSelected(ButtonEvent ce) {
                    Dispatcher.forwardEvent(EventoEvents.CaricaCoefficienti);
                    Dispatcher.forwardEvent(EventoEvents.CaricaProgettiDiCompensazione);
                    setActiveItem(Eventi.CONOSCI_CO2);
                }
            });
            co2.setSize(213, 210);
            c.add(co2, layoutData);
            center.add(c, new FlowData(1));
        }

        ContentPanel east = new ContentPanel();
        east.setHeaderVisible(false);
        BorderLayoutData eastData = new BorderLayoutData(Style.LayoutRegion.EAST, 300);
        eastData.setSplit(false);
        eastData.setMargins(new Margins(0));

        _return.add(east, eastData);

        {
            ContentPanel c = new ContentPanel();
            c.setHeight(530);

            c.setHeaderVisible(false);
            c.setLayout((new RowLayout(Style.Orientation.VERTICAL)));

            {
                LayoutContainer rigo = new LayoutContainer();
                rigo.setLayout(new RowLayout(Style.Orientation.HORIZONTAL));

                // Login
                FormPanel login = new FormPanel();
                login.setLayout(new RowLayout(Style.Orientation.VERTICAL));
                //  login.setWidth(290);
                login.setHeading("Login");

                KeyListener keyListener = new KeyListener() {
                    public void componentKeyUp(ComponentEvent event) {
                        //  validate();
                    }
                };

                LabelField label = new LabelField("UserName: ");
                label.setStyleAttribute("font-family", "tahoma,arial,verdana,sans-serif");
                label.setWidth(100);

                TextField<String> userName = new TextField<String>();
                userName.setWidth(45);
                userName.setMinLength(4);
                userName.setName("userName");
                userName.addKeyListener(keyListener);

                rigo.add(label, new RowData(0.3, 1, new Margins(4, 1, 4, 1)));
                rigo.add(userName, new RowData(0.7, 1, new Margins(4, 1, 4, 1)));

                login.add(rigo, new RowData(1, 0.5));

                rigo = new LayoutContainer();
                rigo.setLayout(new RowLayout(Style.Orientation.HORIZONTAL));

                label = new LabelField("Password: ");
                label.setStyleAttribute("font-family", "tahoma,arial,verdana,sans-serif");
                label.setWidth(100);

                TextField<String> password = new TextField<String>();
                password.setWidth(45);
                password.setMinLength(4);
                password.setPassword(true);
                password.setName("password");
                password.addKeyListener(keyListener);

                rigo.add(label, new RowData(0.3, 1, new Margins(4, 1, 4, 1)));
                rigo.add(password, new RowData(0.7, 1, new Margins(4, 1,4, 1)));

                Button btn = new Button("Submit");
                btn.addSelectionListener(new SelectionListener<ButtonEvent>() {
                    @Override
                    public void componentSelected(ButtonEvent ce) {

                    }
                });

                login.add(rigo, new RowData(1, 0.5));
                login.addButton(btn);


                c.add(login, new RowData(1, 0.3, new Margins(1, 1, 1, 1)));
            }
            {
                ContentPanel compensazione = new ContentPanel();
                compensazione.setHeading("Cos'e' la compensazione?");
                VBoxLayout layoutCompensazione = new VBoxLayout();
                layoutCompensazione.setPadding(new Padding(5));
                layoutCompensazione.setVBoxLayoutAlign(VBoxLayout.VBoxLayoutAlign.CENTER);
                compensazione.setLayout(layoutCompensazione);

                Text testo = new Text("La combustione di fonti energetiche fossili (carbone, benzina, cherosene) sprigiona biossido di carbonio (CO2). Il CO2 è il maggior responsabile dei gas ad effetto serra provocati dall’uomo. Blabla blabla bla bla blal");
                testo.setStyleAttribute("font-family", "tahoma,arial,verdana,sans-serif");
                Image azzeroCO2Stemp = new Image(AzzeroCO2Resources.INSTANCE.azzeroCO2Stemp());
                azzeroCO2Stemp.setAltText("AzzeroCO2");

                compensazione.add(testo, new VBoxLayoutData(new Margins(1, 1, 1, 1)));
                compensazione.add(azzeroCO2Stemp, new VBoxLayoutData(new Margins(0, 0, 0, 0)));
                c.add(compensazione, new RowData(1, 0.7, new Margins(1, 1, 1, 1)));
            }
            east.add(c, new FlowData(0));
        }
        _return.setTitle(Eventi.MAIN.name());
        return _return;


    }


    private void onInit(AppEvent event) {
        centralPanel.setHeaderVisible(false);
        centralPanel.setButtonAlign(Style.HorizontalAlignment.CENTER);
        centralPanel.setLayout(layout);
        layout.setActiveItem(centralPanel.getItem(Eventi.MAIN.ordinal()));
        centralPanel.add(getStartContent());

        Dispatcher.forwardEvent(new AppEvent(AzzeroCO2Events.CentralPanelReady,
                centralPanel));
    }
}
