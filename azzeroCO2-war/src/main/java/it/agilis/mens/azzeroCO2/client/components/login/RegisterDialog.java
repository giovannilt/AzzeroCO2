package it.agilis.mens.azzeroCO2.client.components.login;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.KeyListener;
import com.extjs.gxt.ui.client.util.IconHelper;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 23/08/11
 * Time: 15.50
 * To change this template use File | Settings | File Templates.
 */
public class RegisterDialog extends Dialog {

     public RegisterDialog() {

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
        setWidth(400);
        setResizable(false);

        KeyListener keyListener = new KeyListener() {
            public void componentKeyUp(ComponentEvent event) {
              //  validate();
            }

        };
          /*
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

        setFocusWidget(userName);   */

    }

    @Override
    protected void createButtons() {

    }
}
