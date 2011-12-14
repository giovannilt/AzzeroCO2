package it.agilis.mens.azzeroCO2.client.components;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.button.Button;

/**
 * Created by IntelliJ IDEA.
 * User: serenadimaida
 * Date: 14/12/11
 * Time: 15:18
 * To change this template use File | Settings | File Templates.
 */
public class EnergiaInfoDialog extends Dialog{
    protected Button cancel;

        public EnergiaInfoDialog() {

            setHeading("Energia");
           // setButtons(Dialog.YESNO);
            setBodyStyleName("pad-text");
            addText("Energia elettrica:  Il dato tiene conto di tutti i combustibili usati nelle centrali elettriche italiane.</br>Gas/gasolio: il dato tiene conto dell’uso del combustibile nei generatori domestici più utilizzati, sia unifamiliari che centralizzati.");
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
