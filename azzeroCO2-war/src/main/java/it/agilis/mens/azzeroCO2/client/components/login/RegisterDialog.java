package it.agilis.mens.azzeroCO2.client.components.login;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.binding.FormBinding;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.KeyListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.util.IconHelper;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.util.Padding;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Status;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.BoxLayout;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.layout.HBoxLayout;
import com.extjs.gxt.ui.client.widget.layout.HBoxLayoutData;
import com.extjs.gxt.ui.client.widget.toolbar.FillToolItem;
import it.agilis.mens.azzeroCO2.client.mvc.events.RegisterEvents;
import it.agilis.mens.azzeroCO2.shared.model.registrazione.UserInfoModel;

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
    private UserInfoModel registrazioneModel = new UserInfoModel();


    public RegisterDialog() {
        FormPanel formPanel = createForm();
        binding = new FormBinding(formPanel, true);
        binding.bind(registrazioneModel);

        FormLayout layout = new FormLayout();
        layout.setLabelWidth(200);
        layout.setDefaultWidth(155);
        setLayout(layout);
        setButtons("");
        setIcon(IconHelper.createStyle("user"));
        setHeading("Registrati");
        setModal(true);
        setBodyBorder(true);
        setBodyStyle("padding: 8px;background: none");
        setWidth(600);
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
        userName.setFieldLabel("Utente*");
        userName.setName("userName");
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
        password.setFieldLabel("Password*");
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
        repassword.setFieldLabel("Ripeti password*");
        repassword.setName("repassword");
        repassword.addKeyListener(keyListener);
        repassword.setLabelStyle("white-space:nowrap");
        repassword.setStyleAttribute("padding-left", "80px");
        formPanel.add(repassword);


        nome = new TextField<String>();
        nome.setMinLength(2);
        nome.getMessages().setMinLengthText("Nome troppo breve");

        nome.setFieldLabel("Nome*");
        nome.setName("nome");
        nome.addKeyListener(keyListener);
        nome.setLabelStyle("white-space:nowrap");
        nome.setStyleAttribute("padding-left", "80px");
        //nome.setLabelStyle("width:150");
        formPanel.add(nome);

        cognome = new TextField<String>();
        cognome.setMinLength(2);
        cognome.getMessages().setMinLengthText("Cognome troppo breve");

        cognome.setFieldLabel("Cognome*");
        cognome.setName("cognome");
        cognome.addKeyListener(keyListener);
        cognome.setLabelStyle("white-space:nowrap");
        cognome.setStyleAttribute("padding-left", "80px");
        //cognome.setLabelStyle("width:150");
        formPanel.add(cognome);

        ragioneSoc = new TextField<String>();
        ragioneSoc.setMinLength(2);
        ragioneSoc.getMessages().setMinLengthText("Ragione sociale troppo breve");

        ragioneSoc.setFieldLabel("Rag. sociale");
        ragioneSoc.setName("ragioneSociale");
        ragioneSoc.addKeyListener(keyListener);
        ragioneSoc.setLabelStyle("white-space:nowrap");
        ragioneSoc.setStyleAttribute("padding-left", "80px");
        //ragioneSoc.setLabelStyle("width:150");

        formPanel.add(ragioneSoc);

        indirizzo = new TextField<String>();
        indirizzo.setMinLength(2);
        indirizzo.getMessages().setMinLengthText("Indirizzo troppo breve");

        indirizzo.setFieldLabel("Indirizzo*");
        indirizzo.setName("indirizzo");
        indirizzo.addKeyListener(keyListener);
        indirizzo.setLabelStyle("white-space:nowrap");
        indirizzo.setStyleAttribute("padding-left", "80px");
        //indirizzo.setLabelStyle("width:150");
        formPanel.add(indirizzo);


        citta = new TextField<String>();
        citta.setMinLength(2);
        citta.getMessages().setMinLengthText("Nome città troppo breve");

        citta.setFieldLabel("Città*");
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

        provincia.setFieldLabel("Provincia*");
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
        cap.setFieldLabel("CAP*");
        cap.setName("cap");
        cap.addKeyListener(keyListener);
        cap.setLabelStyle("white-space:nowrap");
        cap.setStyleAttribute("padding-left", "80px");
        //cap.setLabelStyle("width:150");
        formPanel.add(cap);

        partitaIvaCF = new TextField<String>();
        partitaIvaCF.setMinLength(11);
        partitaIvaCF.setMaxLength(17);
        partitaIvaCF.setName("pivaCF*");
        partitaIvaCF.getMessages().setMinLengthText("La partita iva deve essere lunga 11 cifre e il codice fiscale 17 caratteri");
        partitaIvaCF.setFieldLabel("P.Iva/CF*");
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
        email.setFieldLabel("Email*");
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
        reemail.setFieldLabel("Ripeti email*");
        reemail.setName("reemail");
        reemail.addKeyListener(keyListener);
        reemail.setRegex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
        reemail.getMessages().setRegexText("Ripeti emil");
        reemail.setLabelStyle("white-space:nowrap");
        reemail.setStyleAttribute("padding-left", "80px");
        //reemail.setLabelStyle("width:150");
        formPanel.add(reemail);

        CheckBox privacy = new CheckBox();
        privacy.setValue(true);
        Text autorizzo = new Text("<table><tbody><tr><td style='font-size:9px;'>Autorizzo il trattamento dei dati personali ai sensi del D. lgs. 196/03</td></tr></tbody></table>");

        HBoxLayout layout = new HBoxLayout();
        layout.setPadding(new Padding(1));
        layout.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.MIDDLE);
        layout.setPack(BoxLayout.BoxLayoutPack.END);

        LayoutContainer cp = new LayoutContainer();
        cp.setLayout(layout);

        HBoxLayoutData layoutData = new HBoxLayoutData(new Margins(0, 0, 0, 0));

        cp.add(privacy, layoutData);
        cp.add(autorizzo, layoutData);
        formPanel.add(cp);

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
                getButtonBar().disable();

                if (binding != null) {
                    Dispatcher.forwardEvent(RegisterEvents.DoRegistration, binding.getModel());
                }

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
                        && ((hasValue(nome) && hasValue(cognome)) || hasValue(ragioneSoc))
                        && email.getValue().equals(reemail.getValue())
                        && password.getValue().equals(repassword.getValue())
        );
    }

    public void hideStatus() {
        status.hide();
        getButtonBar().enable();
    }
}
