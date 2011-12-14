package it.agilis.mens.azzeroCO2.client.components;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.button.Button;

/**
 * Created by IntelliJ IDEA.
 * User: serenadimaida
 * Date: 14/12/11
 * Time: 18:12
 * To change this template use File | Settings | File Templates.
 */
public class PernottamentiDialog extends Dialog{
    protected Button cancel;

            public PernottamentiDialog() {

                setHeading("Pernottamenti");
               // setButtons(Dialog.YESNO);
                setBodyStyleName("pad-text");
                addText("È stata presa in considerazione una media dei consumi di energia e di acqua per stanza per notte.");
                //getItem(0).getFocusSupport().setIgnore(true);
                setModal(true);

            }



            @Override
            protected void createButtons() {



                cancel = new Button("chiudi");
                cancel.addSelectionListener(new SelectionListener<ButtonEvent>() {
                    public void componentSelected(ButtonEvent ce) {
                        hide();
                    }
                });

                addButton(cancel);
            }


}
