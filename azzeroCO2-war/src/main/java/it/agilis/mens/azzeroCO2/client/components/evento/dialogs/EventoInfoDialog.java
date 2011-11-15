package it.agilis.mens.azzeroCO2.client.components.evento.dialogs;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.button.Button;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 11/15/11
 * Time: 8:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class EventoInfoDialog  extends Dialog {

    protected Button cancel;

    public EventoInfoDialog() {

        setHeading("Evento INFO");
       // setButtons(Dialog.YESNO);
        setBodyStyleName("pad-text");
        addText("BALBALALABLABALBAL?");
        //getItem(0).getFocusSupport().setIgnore(true);
        setModal(true);

    }

    @Override
    protected void createButtons() {



        cancel = new Button("close");
        cancel.addSelectionListener(new SelectionListener<ButtonEvent>() {
            public void componentSelected(ButtonEvent ce) {
                hide();
            }
        });

        addButton(cancel);
    }

}