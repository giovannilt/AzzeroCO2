package it.agilis.mens.azzeroCO2.client.components.login;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.KeyListener;
import com.extjs.gxt.ui.client.util.IconHelper;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;

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
    protected TextField<String> partitaIvaCF;

    protected Button reset;


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
                //validate();
            }

        };

                userName = new TextField<String>();
                userName.setMinLength(4);
                userName.setFieldLabel("Username");
                userName.setName("userName");
                userName.addKeyListener(keyListener);
                add(userName);

                password = new TextField<String>();
                password.setMinLength(4);
                password.setPassword(true);
                password.setFieldLabel("Password");
                password.setName("password");
                password.addKeyListener(keyListener);
                add(password);

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

                telefono = new TextField<String>();
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
                add(email);


                setFocusWidget(userName);

    }

    @Override
    protected void createButtons() {

    }
}
