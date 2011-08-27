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
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.toolbar.FillToolItem;
import it.agilis.mens.azzeroCO2.client.mvc.events.LoginEvents;


/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 3/12/11
 * Time: 1:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class LoginDialog extends Dialog {

    protected TextField<String> userName;
    protected TextField<String> password;
    protected Button reset;
    protected Button login;
    protected Status status;

    public LoginDialog() {

        FormLayout layout = new FormLayout();
        layout.setLabelWidth(90);
        layout.setDefaultWidth(155);
        setLayout(layout);

        setButtonAlign(Style.HorizontalAlignment.LEFT);
        setButtons("");
        setIcon(IconHelper.createStyle("user"));
        setHeading("Login");
        setModal(true);
        setBodyBorder(true);
        setBodyStyle("padding: 8px;background: none");
        setWidth(300);
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
        userName.addKeyListener(keyListener);
        add(userName);

        password = new TextField<String>();
        password.setMinLength(4);
        password.setPassword(true);
        password.setFieldLabel("Password");
        password.setName("password");
        password.addKeyListener(keyListener);
        add(password);

        setFocusWidget(userName);

    }

    @Override
    protected void createButtons() {
        super.createButtons();
        status = new Status();
        status.setBusy("please wait...");
        status.hide();
        status.setAutoWidth(true);
        getButtonBar().add(status);

        getButtonBar().add(new FillToolItem());

        reset = new Button("Reset");
        reset.addSelectionListener(new SelectionListener<ButtonEvent>() {
            public void componentSelected(ButtonEvent ce) {
                userName.reset();
                password.reset();
                validate();
                userName.focus();
            }

        });

        login = new Button("Login");
        login.disable();
        login.addSelectionListener(new SelectionListener<ButtonEvent>() {
            public void componentSelected(ButtonEvent ce) {
             //   register.setVisible(false);
                AppEvent event= new AppEvent(LoginEvents.DoLogin);
                event.setData("userName", userName.getValue());
                event.setData("password", password.getValue());
                status.show();
              //  getButtonBar().disable();

                Dispatcher.forwardEvent(event);

            }
        });

        addButton(reset);
        addButton(login);


    }






    protected boolean hasValue(TextField<String> field) {
        return field.getValue() != null && field.getValue().length() > 0;
    }

    protected void validate() {
        login.setEnabled(hasValue(userName) && hasValue(password)
                && password.getValue().length() > 3);
    }

}
