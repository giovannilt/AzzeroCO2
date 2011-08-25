package it.agilis.mens.azzeroCO2.client.components.login;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.binding.FormBinding;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.KeyListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.util.IconHelper;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.Status;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.toolbar.FillToolItem;
import it.agilis.mens.azzeroCO2.client.mvc.events.RegisterEvents;
import it.agilis.mens.azzeroCO2.shared.model.registrazione.RegistrazioneModel;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 23/08/11
 * Time: 15.50
 * To change this template use File | Settings | File Templates.
 */
public class RegisterDialog extends Dialog {
    protected TextField<String> userName;
    protected TextField<String> password;
    protected TextField<String> repassword;
    protected TextField<String> nome;
    protected TextField<String> cognome;
    protected TextField<String> ragioneSoc;
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

    protected Button reset;
    protected Button conferma;
    protected Status status;

    private FormBinding binding;
    private RegistrazioneModel registrazioneModel = new RegistrazioneModel();


    public RegisterDialog() {
        FormPanel formPanel = createForm();
        binding = new FormBinding(formPanel, true);
        binding.bind(registrazioneModel);

        FormLayout layout = new FormLayout();
        layout.setLabelWidth(150);
        layout.setDefaultWidth(155);
        setLayout(layout);
        setButtons("");
        setIcon(IconHelper.createStyle("user"));
        setHeading("Registrati");
        setModal(true);
        setBodyBorder(true);
        setBodyStyle("padding: 8px;background: none");
        setWidth(400);
        setResizable(false);
        add(formPanel);
    }


    private FormPanel createForm() {
        FormPanel formPanel = new FormPanel();
        formPanel.setHeaderVisible(false);

        formPanel.setButtonAlign(Style.HorizontalAlignment.LEFT);


        KeyListener keyListener = new KeyListener() {
            public void componentKeyUp(ComponentEvent event) {
                validate();
            }

        };

        userName = new TextField<String>();
        userName.setFieldLabel("Username");
        userName.setName("userName");
        userName.setMinLength(6);
        userName.getMessages().setMinLengthText("la username deve essere di almeno 6 caratteri");

        userName.addKeyListener(keyListener);
        formPanel.add(userName);

        password = new TextField<String>();
        password.setMinLength(6);
        password.getMessages().setMinLengthText("la password deve essere di almeno 6 caratteri");
        password.setPassword(true);
        password.setFieldLabel("Password");
        password.setName("password");
        password.addKeyListener(keyListener);
        formPanel.add(password);


        repassword = new TextField<String>();
        repassword.setMinLength(6);
        repassword.getMessages().setMinLengthText("Ripeti la password");
        repassword.setPassword(true);
        repassword.setFieldLabel("Ripeti password");
        repassword.setName("repassword");
        repassword.addKeyListener(keyListener);
        formPanel.add(repassword);


        nome = new TextField<String>();
        nome.setMinLength(2);
        nome.getMessages().setMinLengthText("Nome troppo breve");

        nome.setFieldLabel("Nome");
        nome.setName("nome");
        nome.addKeyListener(keyListener);
        formPanel.add(nome);

        cognome = new TextField<String>();
        cognome.setMinLength(2);
        cognome.getMessages().setMinLengthText("Cognome troppo breve");

        cognome.setFieldLabel("Cognome");
        cognome.setName("cognome");
        cognome.addKeyListener(keyListener);
        formPanel.add(cognome);

        ragioneSoc = new TextField<String>();
        ragioneSoc.setMinLength(2);
        ragioneSoc.getMessages().setMinLengthText("Ragione sociale troppo breve");

        ragioneSoc.setFieldLabel("Rag. sociale");
        ragioneSoc.setName("ragioneSoc");
        ragioneSoc.addKeyListener(keyListener);
        formPanel.add(ragioneSoc);

        indirizzo = new TextField<String>();
        indirizzo.setMinLength(2);
        indirizzo.getMessages().setMinLengthText("Indirizzo troppo breve");

        indirizzo.setFieldLabel("Indirizzo");
        indirizzo.setName("Indirizzo");
        indirizzo.addKeyListener(keyListener);
        formPanel.add(indirizzo);


        citta = new TextField<String>();
        citta.setMinLength(2);
        citta.getMessages().setMinLengthText("Nome città troppo breve");

        citta.setFieldLabel("Città");
        citta.setName("Città");
        citta.addKeyListener(keyListener);
        formPanel.add(citta);

        provincia = new TextField<String>();
        provincia.setMinLength(2);
        provincia.setMaxLength(2);
        provincia.getMessages().setMaxLengthText("Inserisci la sigla della provincia");

        provincia.setFieldLabel("Provincia");
        provincia.setName("Provincia");
        provincia.addKeyListener(keyListener);
        formPanel.add(provincia);

        cap = new TextField<String>();
        cap.setMinLength(5);
        cap.setMaxLength(5);
        cap.getMessages().setMinLengthText("Il CAP deve essere di 5 cifre");
        cap.setRegex("[0-9]+");
        cap.getMessages().setRegexText("Inserisci 5 cifre");
        cap.setFieldLabel("CAP");
        cap.setName("CAP");
        cap.addKeyListener(keyListener);
        formPanel.add(cap);

        partitaIvaCF = new TextField<String>();
        partitaIvaCF.setMinLength(11);
        partitaIvaCF.setMaxLength(17);
        partitaIvaCF.getMessages().setMinLengthText("La partita iva deve essere lunga 11 cifre e il codice fiscale 17 caratteri");

        partitaIvaCF.setFieldLabel("P.Iva/CF");
        partitaIvaCF.setName("P.Iva/CF");
        partitaIvaCF.addKeyListener(keyListener);
        formPanel.add(partitaIvaCF);

        telefono = new TextField<String>();
        telefono.setMinLength(2);
        telefono.getMessages().setMinLengthText("Numero troppo corto");
        telefono.setFieldLabel("Telefono");
        telefono.setName("telefono");
        telefono.addKeyListener(keyListener);
        telefono.setRegex("[0-9]+");
        telefono.getMessages().setRegexText("Solo valori numerici");
        formPanel.add(telefono);

        fax = new TextField<String>();
        fax.setMinLength(2);
        fax.getMessages().setMinLengthText("Numero fax troppo corto");
        fax.setFieldLabel("Fax");
        fax.setName("fax");
        fax.addKeyListener(keyListener);
        fax.setRegex("^[0-9]+");
        fax.getMessages().setRegexText("Solo valori numerici");
        formPanel.add(fax);

        cellulare = new TextField<String>();
        cellulare.setMinLength(2);
        cellulare.getMessages().setMinLengthText("Numero cellulare troppo breve");
        cellulare.setFieldLabel("Cellulare");
        cellulare.setName("cellulare");
        cellulare.addKeyListener(keyListener);
        cellulare.setRegex("^[0-9]+");
        cellulare.getMessages().setRegexText("Solo valori numerici");
        formPanel.add(cellulare);

        email = new TextField<String>();
        email.setMinLength(2);
        email.setFieldLabel("Email");
        email.setName("email");
        email.addKeyListener(keyListener);
        email.setRegex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
        email.getMessages().setRegexText("Email errata");
        formPanel.add(email);


        reemail = new TextField<String>();
        reemail.setMinLength(2);
        reemail.setFieldLabel("Ripeti email");
        reemail.setName("reemail");
        reemail.addKeyListener(keyListener);
        reemail.setRegex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
        reemail.getMessages().setRegexText("Ripeti emil");
        formPanel.add(reemail);


        setFocusWidget(userName);
        return formPanel;
    }

    @Override
    protected void createButtons() {
        super.createButtons();
        status = new Status();
        status.setBusy("attendere prego...");
        status.hide();
        status.setAutoWidth(true);
        getButtonBar().add(status);

        getButtonBar().add(new FillToolItem());

        reset = new Button("Reset");
        reset.addSelectionListener(new SelectionListener<ButtonEvent>() {
            public void componentSelected(ButtonEvent ce) {
                userName.reset();
                password.reset();
                repassword.reset();
                nome.reset();
                cognome.reset();
                ragioneSoc.reset();
                partitaIvaCF.reset();
                indirizzo.reset();
                citta.reset();
                provincia.reset();
                cap.reset();
                telefono.reset();
                fax.reset();
                cellulare.reset();
                email.reset();
                reemail.reset();
                validate();
                userName.focus();
            }

        });

        conferma = new Button("Conferma");
        conferma.disable();
        conferma.addSelectionListener(new SelectionListener<ButtonEvent>() {
            public void componentSelected(ButtonEvent ce) {
                status.show();
      //          getButtonBar().disable();
                Dispatcher.forwardEvent(RegisterEvents.DoRegistration, new AppEvent(RegisterEvents.DoRegistration, binding.getModel()));

            }
        });

        addButton(reset);
        addButton(conferma);
    }


    protected boolean hasValue(TextField<String> field) {
        return field.getValue() != null && field.getValue().length() > 0;
    }

    protected void validate() {
        conferma.setEnabled(
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
            && ((hasValue(nome) && hasValue(cognome))|| hasValue(ragioneSoc))
            && email.getValue().equals(reemail.getValue())
            && password.getValue().equals(repassword.getValue())
        );


    }

}
