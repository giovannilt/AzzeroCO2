package it.agilis.mens.azzeroCO2.client.components.login;

import com.extjs.gxt.ui.client.Style;
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
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.toolbar.FillToolItem;
import com.sun.tools.jdi.StringReferenceImpl;
import it.agilis.mens.azzeroCO2.client.mvc.events.RegisterEvents;

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
    protected NumberField telefono;
    protected TextField<String> fax;
    protected TextField<String> cellulare;
    protected TextField<String> email;
    protected TextField<String> reemail;
    protected TextField<String> partitaIvaCF;


    protected Button reset;
    protected Button conferma;

    protected Status status;



     public RegisterDialog() {

        FormLayout layout = new FormLayout();
        layout.setLabelWidth(90);
        layout.setDefaultWidth(155);
        setLayout(layout);

        setButtonAlign(Style.HorizontalAlignment.LEFT);
        setButtons("");
        setIcon(IconHelper.createStyle("user"));
        setHeading("Registrati");
        setModal(true);
        setBodyBorder(true);
        setBodyStyle("padding: 8px;background: none");
        setWidth(400);
        setResizable(false);

        KeyListener keyListener = new KeyListener() {
            public void componentKeyUp(ComponentEvent event) {
                validate();
            }

        };




         userName = new TextField<String>();
         userName.setMinLength(4);
         userName.setFieldLabel("Username");
         userName.setName("userName");
         userName.setMinLength(6);

         userName.addKeyListener(keyListener);
         add(userName);

         password = new TextField<String>();
         password.setMinLength(4);
         password.setPassword(true);
         password.setFieldLabel("Password");
         password.setName("password");
         password.addKeyListener(keyListener);
         add(password);


        repassword = new TextField<String>();
         repassword.setMinLength(4);
         repassword.setPassword(true);
         repassword.setFieldLabel("Ripeti password");
         repassword.setName("repassword");
         repassword.addKeyListener(keyListener);
         add(repassword);



         nome = new TextField<String>();
         nome.setMinLength(2);
         nome.setFieldLabel("Nome");
         nome.setName("Nome");
         nome.addKeyListener(keyListener);
         add(nome);

         cognome = new TextField<String>();
         cognome.setMinLength(2);
         cognome.setFieldLabel("Cognome");
         cognome.setName("Cognome");
         cognome.addKeyListener(keyListener);
         add(cognome);

         ragioneSoc = new TextField<String>();
         ragioneSoc.setMinLength(2);
         ragioneSoc.setFieldLabel("Rag. sociale");
         ragioneSoc.setName("Rag. sociale");
         ragioneSoc.addKeyListener(keyListener);
         add(ragioneSoc);

         indirizzo = new TextField<String>();
         indirizzo.setMinLength(2);
         indirizzo.setFieldLabel("Indirizzo");
         indirizzo.setName("Indirizzo");
         indirizzo.addKeyListener(keyListener);
         add(indirizzo);


         citta = new TextField<String>();
         citta.setMinLength(2);
         citta.setFieldLabel("Città");
         citta.setName("Città");
         citta.addKeyListener(keyListener);
         add(citta);

         provincia = new TextField<String>();
         provincia.setMinLength(2);
         provincia.setFieldLabel("Provincia");
         provincia.setName("Provincia");
         provincia.addKeyListener(keyListener);
         add(provincia);

         cap = new TextField<String>();
         cap.setMinLength(2);
         cap.setFieldLabel("CAP");
         cap.setName("CAP");
         cap.addKeyListener(keyListener);
         add(cap);

         partitaIvaCF = new TextField<String>();
         partitaIvaCF.setMinLength(2);
         partitaIvaCF.setFieldLabel("P.Iva/CF");
         partitaIvaCF.setName("P.Iva/CF");
         partitaIvaCF.addKeyListener(keyListener);
         add(partitaIvaCF);

         NumberField telefono = new NumberField();
         telefono.setMinLength(2);
         telefono.setFieldLabel("Telefono");
         telefono.setName("Telefono");
         telefono.addKeyListener(keyListener);
         add(telefono);

         fax = new TextField<String>();
         fax.setMinLength(2);
         fax.setFieldLabel("Fax");
         fax.setName("Fax");
         fax.addKeyListener(keyListener);
         add(fax);

         cellulare = new TextField<String>();
         cellulare.setMinLength(2);
         cellulare.setFieldLabel("Cellulare");
         cellulare.setName("Cellulare");
         cellulare.addKeyListener(keyListener);
         add(cellulare);

         email = new TextField<String>();
         email.setMinLength(2);
         email.setFieldLabel("Email");
         email.setName("Email");
         email.addKeyListener(keyListener);
         email.setRegex("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\\\.[A-Z]{2,4}$");
         //email.getMessages().setRegexText("Email errata");
         add(email);


         reemail = new TextField<String>();
         reemail.setMinLength(2);
         reemail.setFieldLabel("Ripeti email");
         reemail.setName("Email");
         reemail.addKeyListener(keyListener);
         reemail.setRegex("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\\\.[A-Z]{2,4}$");
         //email.getMessages().setRegexText("Email errata");
         add(reemail);


         setFocusWidget(userName);
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
                //register.setVisible(false);
                AppEvent event= new AppEvent(RegisterEvents.DoRegistration, new String());
                event.setData("userName", userName.getValue());
                event.setData("password", password.getValue());
                event.setData("email",email.getValue());
                event.setData("telefono",telefono.getValue());
                event.setData("partitaIvaCF",partitaIvaCF.getValue());
                event.setData("citta",citta.getValue());
                event.setData("provincia",provincia.getValue());
                event.setData("citta", citta.getValue());
                event.setData("cap",cap.getValue());
                event.setData("fax",fax.getValue());
                event.setData("cellulare",cellulare.getValue());
                event.setData("indirizzo", indirizzo.getValue());

                status.show();
                getButtonBar().disable();
                //LoginDialog.this.hide();

                Dispatcher.forwardEvent(RegisterEvents.DoRegistration, event);
                //onSubmit();
            }
        });

        /*register= new Button("Registra");
        register.addSelectionListener(new SelectionListener<ButtonEvent>() {
            public void componentSelected(ButtonEvent ce) {
                Dispatcher.forwardEvent(RegisterEvents.HideForm, ce);
                Dispatcher.forwardEvent(RegisterEvents.ShowForm, ce);
            }
        }*/
        //);


        addButton(reset);
        addButton(conferma);
        //addButton(register);

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
                        //TODO && email.getValue()==reemail.getValue()
                        //&& (hasValue(telefono.toString())|| hasValue(cellulare))

                        && hasValue(indirizzo)
                        && hasValue(provincia)
                        && ((hasValue(nome) && hasValue(cognome) ) || hasValue(ragioneSoc))
                        && password.getValue().length() > 5
                        && email.getValue().length()>5
                        && partitaIvaCF.getValue().length()>10
                        && citta.getValue().length()>2
                        && provincia.getValue().length()>1
                        && email.getValue()==reemail.getValue()
                        //TODO && password.getValue()==repassword.getValue()
                        //&& telefono.getValue().length()>3
                        //&& ((nome.getValue().length()>0 && cognome.getValue().length()>0)|| ragioneSoc.getValue().length()>0)
        );


    }

}
