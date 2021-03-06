package it.agilis.mens.azzeroCO2.client.forms.amministrazione;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.binding.FormBinding;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.KeyListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.google.gwt.user.client.Element;
import it.agilis.mens.azzeroCO2.client.mvc.events.RegisterEvents;
import it.agilis.mens.azzeroCO2.shared.model.registrazione.UserInfoModel;

/**
 * Created by IntelliJ IDEA.
 * User: serenadimaida
 * Date: 26/08/11
 * Time: 22:25
 * To change this template use File | Settings | File Templates.
 */

public class UserInfo extends LayoutContainer {
    private FormBinding binding;
    private FormPanel centre;
    private UserInfoModel userInfoModel = new UserInfoModel();

    protected TextField<String> userName;
    protected TextField<String> password;
    protected TextField<String> repassword;
    protected TextField<String> nome;
    protected TextField<String> cognome;
    protected TextField<String> ragioneSociale;
    protected TextField<String> indirizzo;
    protected TextField<String> citta;
    protected TextField<String> provincia;
    protected TextField<String> cap;
    protected TextField<String> telefono;
    protected TextField<String> fax;
    protected TextField<String> cellulare;
    protected TextField<String> email;
    protected TextField<String> reemail;
    protected TextField<String> partitaIvaCF;


    public UserInfo() {
        centre = createForm();
        binding = new FormBinding(centre, true);
        binding.bind(userInfoModel);
    }

    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);

        BorderLayout layout = new BorderLayout();
        setLayout(layout);

        //    centre.setHeading("CouponModel");
        centre.setHeaderVisible(false);
        centre.setScrollMode(Style.Scroll.AUTO);
        centre.setHeight(700);

        centre.setFrame(true);

        BorderLayoutData centerData = new BorderLayoutData(Style.LayoutRegion.CENTER);
        centerData.setMargins(new Margins(0));
        add(centre, centerData);

    }

    private FormPanel createForm() {
        FormPanel formPanel = new FormPanel();
        formPanel.setWidth(1);
        formPanel.setHeaderVisible(false);
        formPanel.setScrollMode(Style.Scroll.AUTO);

        formPanel.setButtonAlign(Style.HorizontalAlignment.LEFT);

        KeyListener keyListener = new KeyListener() {
            public void componentKeyUp(ComponentEvent event) {
                validate();
            }
        };

        userName = new TextField<String>();
        userName.setFieldLabel("Username");
        userName.setName("userName");
        userName.setEnabled(false);
        userName.setMinLength(6);
        userName.getMessages().setMinLengthText("la username deve essere di almeno 6 caratteri");

        userName.addKeyListener(keyListener);
        userName.setLabelStyle("white-space:nowrap");
        userName.setStyleAttribute("padding-left", "80px");
        //userName.setLabelStyle("width:150");
        formPanel.add(userName);

        password = new TextField<String>();
        password.setMinLength(6);
        password.getMessages().setMinLengthText("la password deve essere di almeno 6 caratteri");
        password.setPassword(true);
        password.setFieldLabel("Password");
        password.setName("password");
        password.addKeyListener(keyListener);
        password.setLabelStyle("white-space:nowrap");
        password.setStyleAttribute("padding-left", "80px");
        //password.setLabelStyle("width:150");
        formPanel.add(password);

        repassword = new TextField<String>();
        repassword.setMinLength(6);
        repassword.getMessages().setMinLengthText("Ripeti la password");
        repassword.setPassword(true);
        repassword.setFieldLabel("Ripeti password");
        repassword.setName("repassword");
        repassword.addKeyListener(keyListener);
        repassword.setLabelStyle("white-space:nowrap");
        repassword.setStyleAttribute("padding-left", "80px");
        //repassword.setLabelStyle("width:150");
        formPanel.add(repassword);

        nome = new TextField<String>();
        nome.setMinLength(2);
        nome.getMessages().setMinLengthText("Nome troppo breve");

        nome.setFieldLabel("Nome");
        nome.setName("nome");
        nome.addKeyListener(keyListener);
        nome.setLabelStyle("white-space:nowrap");
        nome.setStyleAttribute("padding-left", "80px");
        //nome.setLabelStyle("width:150");
        formPanel.add(nome);

        cognome = new TextField<String>();
        cognome.setMinLength(2);
        cognome.getMessages().setMinLengthText("Cognome troppo breve");

        cognome.setFieldLabel("Cognome");
        cognome.setName("cognome");
        cognome.addKeyListener(keyListener);
        cognome.setLabelStyle("white-space:nowrap");
        cognome.setStyleAttribute("padding-left", "80px");
        //cognome.setLabelStyle("width:150");
        formPanel.add(cognome);

        ragioneSociale = new TextField<String>();
        ragioneSociale.setMinLength(2);
        ragioneSociale.getMessages().setMinLengthText("Ragione sociale troppo breve");

        ragioneSociale.setFieldLabel("Rag. sociale");
        ragioneSociale.setName("ragioneSociale");
        ragioneSociale.addKeyListener(keyListener);
        ragioneSociale.setLabelStyle("white-space:nowrap");
        ragioneSociale.setStyleAttribute("padding-left", "80px");
        //ragioneSociale.setLabelStyle("width:150");
        formPanel.add(ragioneSociale);

        indirizzo = new TextField<String>();
        indirizzo.setMinLength(2);
        indirizzo.getMessages().setMinLengthText("Indirizzo troppo breve");

        indirizzo.setFieldLabel("Indirizzo");
        indirizzo.setName("indirizzo");
        indirizzo.addKeyListener(keyListener);
        indirizzo.setLabelStyle("white-space:nowrap");
        indirizzo.setStyleAttribute("padding-left", "80px");
        //indirizzo.setLabelStyle("width:150");
        formPanel.add(indirizzo);

        citta = new TextField<String>();
        citta.setMinLength(2);
        citta.getMessages().setMinLengthText("Nome città troppo breve");

        citta.setFieldLabel("Città");
        citta.setName("citta");
        citta.addKeyListener(keyListener);
        citta.setLabelStyle("white-space:nowrap");
        citta.setStyleAttribute("padding-left", "80px");
        //citta.setLabelStyle("width:150");
        formPanel.add(citta);

        provincia = new TextField<String>();
        provincia.setMinLength(2);
        provincia.setMaxLength(2);
        provincia.getMessages().setMaxLengthText("Inserisci la sigla della provincia");

        provincia.setFieldLabel("Provincia");
        provincia.setName("provincia");
        provincia.addKeyListener(keyListener);
        provincia.setLabelStyle("white-space:nowrap");
        provincia.setStyleAttribute("padding-left", "80px");
        //provincia.setLabelStyle("width:150");
        formPanel.add(provincia);

        cap = new TextField<String>();
        cap.setMinLength(5);
        cap.setMaxLength(5);
        cap.getMessages().setMinLengthText("Il CAP deve essere di 5 cifre");
        cap.setRegex("[0-9]+");
        cap.getMessages().setRegexText("Inserisci 5 cifre");
        cap.setFieldLabel("CAP");
        cap.setName("cap");
        cap.addKeyListener(keyListener);
        cap.setLabelStyle("white-space:nowrap");
        cap.setStyleAttribute("padding-left", "80px");
        //cap.setLabelStyle("width:150");
        formPanel.add(cap);

        partitaIvaCF = new TextField<String>();
        partitaIvaCF.setMinLength(11);
        partitaIvaCF.setMaxLength(17);
        partitaIvaCF.setName("pivaCF");
        partitaIvaCF.getMessages().setMinLengthText("La partita iva deve essere lunga 11 cifre e il codice fiscale 17 caratteri");
        partitaIvaCF.setFieldLabel("P.Iva/CF");
        partitaIvaCF.addKeyListener(keyListener);
        partitaIvaCF.setLabelStyle("white-space:nowrap");
        partitaIvaCF.setStyleAttribute("padding-left", "80px");
        //partitaIvaCF.setLabelStyle("width:150");
        formPanel.add(partitaIvaCF);

        telefono = new TextField<String>();
        telefono.setMinLength(2);
        telefono.getMessages().setMinLengthText("Numero troppo corto");
        telefono.setFieldLabel("Telefono");
        telefono.setName("telefono");
        telefono.addKeyListener(keyListener);
        telefono.setRegex("[0-9]+");
        telefono.getMessages().setRegexText("Solo valori numerici");
        telefono.setLabelStyle("white-space:nowrap");
        telefono.setStyleAttribute("padding-left", "80px");
        //telefono.setLabelStyle("width:150");
        formPanel.add(telefono);

        fax = new TextField<String>();
        fax.setMinLength(2);
        fax.getMessages().setMinLengthText("Numero fax troppo corto");
        fax.setFieldLabel("Fax");
        fax.setName("fax");
        fax.addKeyListener(keyListener);
        fax.setRegex("^[0-9]+");
        fax.getMessages().setRegexText("Solo valori numerici");
        fax.setLabelStyle("white-space:nowrap");
        fax.setStyleAttribute("padding-left", "80px");
        //fax.setLabelStyle("width:150");
        formPanel.add(fax);

        cellulare = new TextField<String>();
        cellulare.setMinLength(2);
        cellulare.getMessages().setMinLengthText("Numero cellulare troppo breve");
        cellulare.setFieldLabel("Cellulare");
        cellulare.setName("cellulare");
        cellulare.addKeyListener(keyListener);
        cellulare.setRegex("^[0-9]+");
        cellulare.getMessages().setRegexText("Solo valori numerici");
        cellulare.setLabelStyle("white-space:nowrap");
        cellulare.setStyleAttribute("padding-left", "80px");
        //cellulare.setLabelStyle("width:150");
        formPanel.add(cellulare);

        email = new TextField<String>();
        email.setMinLength(2);
        email.setFieldLabel("Email");
        email.setName("email");
        email.addKeyListener(keyListener);
        email.setRegex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
        email.getMessages().setRegexText("Email errata");
        email.setLabelStyle("white-space:nowrap");
        email.setStyleAttribute("padding-left", "80px");
        //email.setLabelStyle("width:150");
        formPanel.add(email);

        reemail = new TextField<String>();
        reemail.setMinLength(2);
        reemail.setFieldLabel("Ripeti email");
        reemail.setName("reemail");
        reemail.addKeyListener(keyListener);
        reemail.setRegex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
        reemail.getMessages().setRegexText("Ripeti emil");
        reemail.setLabelStyle("white-space:nowrap");
        reemail.setStyleAttribute("padding-left", "80px");
        //reemail.setLabelStyle("width:150");
        formPanel.add(reemail);

        Button conferma = new Button("Conferma");

        conferma.addSelectionListener(new SelectionListener<ButtonEvent>() {
            public void componentSelected(ButtonEvent ce) {
                if (binding != null) {
                    Dispatcher.forwardEvent(RegisterEvents.SaveUserInfo, binding.getModel());
                }
            }
        });

        formPanel.add(conferma);
        return formPanel;
    }


    protected boolean hasValue(TextField<String> field) {
        return field.getValue() != null && field.getValue().length() > 0;
    }

    protected void validate() {
        Button saveButton = new Button("Modifica profilo");
        saveButton.setEnabled(
                hasValue(userName)
                        && hasValue(password)
                        && hasValue(partitaIvaCF)
                        && hasValue(reemail)
                        && hasValue(repassword)
                        && hasValue(citta)
                        && hasValue(email)
                        && hasValue(cap)
                        && hasValue(indirizzo)
                        && hasValue(provincia)
                        && ((hasValue(nome) && hasValue(cognome)) || hasValue(ragioneSociale))
                        && email.getValue().equals(reemail.getValue())
                        && password.getValue().equals(repassword.getValue())
        );
    }

    public void setUserInStore(UserInfoModel userInfoModel) {
        binding.bind(userInfoModel);
    }

    public void clear() {
        binding.bind(userInfoModel);
    }

}
