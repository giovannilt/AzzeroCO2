package it.agilis.mens.azzeroCO2.client.components;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.button.Button;

/**
 * Created by IntelliJ IDEA.
 * User: serenadimaida
 * Date: 14/12/11
 * Time: 18:15
 * To change this template use File | Settings | File Templates.
 */
public class TrasportoMerciDialog extends Dialog{
    protected Button cancel;

            public TrasportoMerciDialog() {

                setHeading("Trasporto merci");
               // setButtons(Dialog.YESNO);
                setBodyStyleName("pad-text");
                addText("Si fa riferimento alle emissioni prodotte dal mezzo in uno specifico chilometraggio.");
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
