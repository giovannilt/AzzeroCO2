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
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;
import it.agilis.mens.azzeroCO2.client.AzzeroCO2Resources;
import it.agilis.mens.azzeroCO2.client.mvc.events.*;
import it.agilis.mens.azzeroCO2.shared.Eventi;
import it.agilis.mens.azzeroCO2.shared.model.registrazione.UserInfoModel;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 3/23/11
 * Time: 10:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class CentralView extends View {
    private final LayoutContainer centralPanel = new LayoutContainer();
    private CardLayout layout = new CardLayout();
    private LayoutContainer logInLogOut = new LayoutContainer();
    private Text benvenuto;


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
        _return.setLayout(new RowLayout(Style.Orientation.HORIZONTAL));
        _return.setStyleAttribute("padding", "2px");

        ContentPanel center = new ContentPanel() {
            @Override
            protected void onLoad() {
                super.onLoad();
                getBody().setStyleAttribute("border-style", "solid");
                getBody().setStyleAttribute("border-top", "3px solid #f8b333");
                getBody().setStyleAttribute("border-width", "3px 0");
            }
        };
        center.setHeading("Compensa le emissioni delle tue attività");

        center.setScrollMode(Style.Scroll.AUTOX);
        _return.add(center, new RowData(.66, 1));

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
                    Dispatcher.forwardEvent(EventoEvents.NextText, "Energia");
                    Dispatcher.forwardEvent(EventoEvents.GoToBegin);

                }
            });
            unEvento.setSize(351, 332); //(319, 300);
            //   layoutData.setFlex(1);
            c.add(unEvento, layoutData);
            Button unAnno = new Button("Un Anno Di attivita'");
            unAnno.setIcon(AbstractImagePrototype.create(AzzeroCO2Resources.INSTANCE.unAnnoDiAttivita()));
            unAnno.setIconAlign(Style.IconAlign.TOP);

            unAnno.addSelectionListener(new SelectionListener<ButtonEvent>() {
                public void componentSelected(ButtonEvent ce) {
                    Dispatcher.forwardEvent(UnAnnoDiAttivitaEvents.CaricaCoefficienti);
                    Dispatcher.forwardEvent(UnAnnoDiAttivitaEvents.CaricaProgettiDiCompensazione);
                    setActiveItem(Eventi.ANNO_DI_ATTIVITA);
                    Dispatcher.forwardEvent(UnAnnoDiAttivitaEvents.NextText, "Energia");
                    Dispatcher.forwardEvent(UnAnnoDiAttivitaEvents.GoToBegin);
                }
            });
            //unAnno.setSize(320, 280);
            unAnno.setSize(353, 333);//(319, 300);
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
                    Dispatcher.forwardEvent(PubblicazioniEvents.CaricaCoefficienti);
                    Dispatcher.forwardEvent(PubblicazioniEvents.CaricaProgettiDiCompensazione);
                    setActiveItem(Eventi.UNA_PUBBLICAZIONE);
                    Dispatcher.forwardEvent(PubblicazioniEvents.NextText, "Manifesti pieghevoli e fogli");
                    Dispatcher.forwardEvent(PubblicazioniEvents.GoToBegin);
                }
            });
            pubblicazione.setSize(212, 200);//(213, 210);
            c.add(pubblicazione, layoutData);

            Button web = new Button("Un sito web");
            web.setIcon(AbstractImagePrototype.create(AzzeroCO2Resources.INSTANCE.unSitoWeb()));
            web.setIconAlign(Style.IconAlign.TOP);
            web.addSelectionListener(new SelectionListener<ButtonEvent>() {
                public void componentSelected(ButtonEvent ce) {
                    Dispatcher.forwardEvent(SitoWebEvents.CaricaCoefficienti);
                    Dispatcher.forwardEvent(SitoWebEvents.CaricaProgettiDiCompensazione);
                    Dispatcher.forwardEvent(SitoWebEvents.NextText, "Riepilogo");
                    Dispatcher.forwardEvent(SitoWebEvents.GoToBegin);
                    setActiveItem(Eventi.WEB);
                }
            });
            web.setSize(212, 200);//(213, 210);
            c.add(web, layoutData);


            Button co2 = new Button("Conosco la CO2");
            co2.setIcon(AbstractImagePrototype.create(AzzeroCO2Resources.INSTANCE.conoscoLaCO2()));
            co2.setIconAlign(Style.IconAlign.TOP);
            co2.addSelectionListener(new SelectionListener<ButtonEvent>() {
                public void componentSelected(ButtonEvent ce) {
                    Dispatcher.forwardEvent(ConoscoCO2Events.CaricaCoefficienti);
                    Dispatcher.forwardEvent(ConoscoCO2Events.CaricaProgettiDiCompensazione);
                    Dispatcher.forwardEvent(ConoscoCO2Events.NextText, "Riepilogo");
                    Dispatcher.forwardEvent(ConoscoCO2Events.GoToBegin);
                    setActiveItem(Eventi.CONOSCI_CO2);
                }
            });
            co2.setSize(212, 200);
            c.add(co2, layoutData);
            center.setStyleAttribute("padding-top","3px");
            center.add(c, new FlowData(1));
        }

        ContentPanel east = new ContentPanel();
        east.setHeaderVisible(false);

        _return.add(east, new RowData(.30, 1));

        {
            ContentPanel c = new ContentPanel();
            c.setHeight(530);
            c.setHeaderVisible(false);
            c.setLayout((new RowLayout(Style.Orientation.VERTICAL)));
            {
                logInLogOut.setLayout(new CardLayout());
                {
                    final FormPanel login = new FormPanel() {
                        @Override
                        protected void onLoad() {
                            super.onLoad();
                            //getBody().setStyleAttribute("border-bottom", "3px solid orange");
                            getBody().setStyleAttribute("border-style", "solid");
                            getBody().setStyleAttribute("border-top", "3px solid #22729E");
                            getBody().setStyleAttribute("border-width", "3px 0");
                        }
                    };

                    LayoutContainer rigo = new LayoutContainer();
                    rigo.setLayout(new RowLayout(Style.Orientation.HORIZONTAL));

                    login.setLayout(new RowLayout(Style.Orientation.VERTICAL));
                    //  login.setWidth(290);
                    //login.getHeader().setTextStyle("x-panel-header-white");
                    login.setHeading("Login");
                    login.getHeader().setStyleAttribute("padding-left", "20px");
                    login.getHeader().setStyleName("x-panel-header-white");

                    //login.getHeader().setStyleAttribute("font-color","white !important");
                    KeyListener keyListener = new KeyListener() {
                        public void componentKeyUp(ComponentEvent event) {
                            //  validate();
                        }


                    };

                    LabelField label = new LabelField("Utente: ");
                    label.setStyleAttribute("font-family", "arial");
                    label.setWidth(80);

                    final TextField<String> userName = new TextField<String>();
                    userName.setWidth(45);
                    userName.setMinLength(4);
                    userName.setName("userName");
                    userName.addKeyListener(keyListener);

                    rigo.add(label, new RowData(0.3, 1, new Margins(4, 1, 4, 10)));
                    rigo.add(userName, new RowData(0.7, 1, new Margins(4, 1, 4, 1)));

                    login.add(rigo, new RowData(1, 0.5));

                    rigo = new LayoutContainer();
                    rigo.setLayout(new RowLayout(Style.Orientation.HORIZONTAL));

                    label = new LabelField("Password: ");
                    label.setStyleAttribute("font-family", "arial");
                    label.setWidth(80);

                    final TextField<String> password = new TextField<String>();
                    password.setWidth(45);
                    password.setMinLength(4);
                    password.setPassword(true);
                    password.setName("password");
                    password.addKeyListener(keyListener);

                    rigo.add(label, new RowData(0.3, 1, new Margins(4, 1, 4, 10)));
                    rigo.add(password, new RowData(0.7, 1, new Margins(4, 1, 4, 1)));

                    Button btn = new Button("Invia");
                    btn.addSelectionListener(new SelectionListener<ButtonEvent>() {
                        @Override
                        public void componentSelected(ButtonEvent ce) {
                            AppEvent event = new AppEvent(LoginEvents.DoLogin);
                            event.setData("userName", userName.getValue());
                            event.setData("password", password.getValue());

                            Dispatcher.forwardEvent(event);
                        }
                    });

                    login.add(rigo, new RowData(1, 0.5));
                    login.addButton(btn);
                    Button registstra = new Button("Registrati");
                    registstra.addSelectionListener(new SelectionListener<ButtonEvent>() {
                        @Override
                        public void componentSelected(ButtonEvent ce) {
                            Dispatcher.forwardEvent(RegisterEvents.ShowForm, ce);
                        }
                    });
                    login.addButton(registstra);
                    logInLogOut.add(login);
                }

                {
                    LayoutContainer rigo = new LayoutContainer();
                    rigo.setLayout(new RowLayout(Style.Orientation.HORIZONTAL));

                    benvenuto = new Text("Ad oggi hai compensato NUMBER Kg di CO2");
                    benvenuto.setStyleAttribute("font-family", "arial");

                    benvenuto.setBounds(30, 30, 250, 180);
                    benvenuto.setStyleAttribute("padding-top", "10px");
                    benvenuto.setStyleAttribute("padding-left", "12px");
                    benvenuto.setStyleAttribute("font-size", "14px");
                    benvenuto.setStyleAttribute("border-top", "3px solid");
                    benvenuto.setStyleAttribute("border-color", "#22729E");


                    rigo.add(benvenuto, new RowData(1, 1, new Margins(29, 10, 10, 10)));
                    //benvenuto.setStyleAttribute("top","29px");
                    logInLogOut.add(rigo);

                }
                c.add(logInLogOut, new RowData(1, 0.3, new Margins(1, 1, 1, 1)));
            }
            {
                ContentPanel compensazione = new ContentPanel() {
                    @Override
                    protected void onLoad() {
                        super.onLoad();
                        //getBody().setStyleAttribute("border-bottom", "3px solid orange");
                        getBody().setStyleAttribute("border-style", "solid");
                        getBody().setStyleAttribute("border-top", "3px solid #22729E");
                        getBody().setStyleAttribute("border-width", "3px 0");
                        //getBody().setStyleAttribute("margin-bottom", "0");

                        //To change body of overridden methods use File | Settings | File Templates.
                    }
                };


                compensazione.setHeading("Cos'e' la compensazione?");
                compensazione.setStyleAttribute("padding-top", "40");
                compensazione.getHeader().setStyleAttribute("padding-top", "30px");
                compensazione.getHeader().setStyleAttribute("padding-left", "20px");
                compensazione.getHeader().setStyleName("x-panel-header-white");
                VBoxLayout layoutCompensazione = new VBoxLayout();
                layoutCompensazione.setPadding(new Padding(5));
                layoutCompensazione.setVBoxLayoutAlign(VBoxLayout.VBoxLayoutAlign.CENTER);
                compensazione.setLayout(layoutCompensazione);

                Text testo = new Text("Compensare significa ridurre la quantità di CO2 che viene immessa in atmosfera tramite interventi di forestazione  o generando  energia da fonti rinnovabili. Nel primo caso la CO2 viene assorbita dagli alberi, nel secondo non viene prodotta.");
                testo.setStyleAttribute("font-family", "arial");
                testo.setStyleAttribute("font-color", "white");
                testo.setStyleAttribute("font-size", "14px");
                testo.setBounds(250, 250, 250, 180);
                Image azzeroCO2Stemp = new Image(AzzeroCO2Resources.INSTANCE.azzeroCO2Stemp());
                azzeroCO2Stemp.setAltText("AzzeroCO2");

                compensazione.add(testo, new VBoxLayoutData(new Margins(2, 2, 2, 2)));
                compensazione.add(azzeroCO2Stemp, new VBoxLayoutData(new Margins(0, 0, 0, 0)));
                c.add(compensazione, new RowData(1, 0.7, new Margins(1, 1, 1, 1)));
            }
            east.setStyleAttribute("padding-top","3px");
            east.add(c, new FlowData(0));
        }
        _return.setTitle(Eventi.MAIN.name());
        return _return;


    }

    public void enableDisableLoginForm(UserInfoModel userInfoModel) {

        final NumberFormat number = NumberFormat.getFormat("0.00");
        CardLayout layout1 = (CardLayout) logInLogOut.getLayout();
        if (layout1.getActiveItem() instanceof FormPanel) {
            layout1.setActiveItem(logInLogOut.getItem(1));
            benvenuto.setText(benvenuto.getText().replaceFirst("NUMBER", number.format(userInfoModel.getKCO2Compensati())));
        } else {
            layout1.setActiveItem(logInLogOut.getItem(0));
        }
    }


    private void onInit(AppEvent event) {
        centralPanel.setLayout(layout);
        layout.setActiveItem(centralPanel.getItem(Eventi.MAIN.ordinal()));
        ContentPanel startContent = getStartContent();
        centralPanel.add(startContent);

        LayoutContainer l = new LayoutContainer() {
            @Override
            protected void onLoad() {
                super.onLoad();
                setStyleAttribute("border-left", "23px #C1DCE7 solid ");
                setStyleAttribute("border-right", "22px #C1DCE7 solid");
                setStyleAttribute("border-top", "0px");
            }
        };

        l.setLayout(new RowLayout(Style.Orientation.HORIZONTAL));
        l.add(centralPanel, new RowData(1, 1));
        Dispatcher.forwardEvent(new AppEvent(AzzeroCO2Events.CentralPanelReady, l));
    }


}
