package it.agilis.mens.azzeroCO2.client.components.dialogs;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.button.Button;
import it.agilis.mens.azzeroCO2.client.mvc.events.CentralEvents;
import it.agilis.mens.azzeroCO2.client.mvc.events.EventoEvents;
import it.agilis.mens.azzeroCO2.shared.Eventi;


/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 3/12/11
 * Time: 1:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class EventoConfermDialog extends Dialog {

    protected Button ok;
    protected Button cancel;

    public EventoConfermDialog() {

        setHeading("Annulla Evento");
       // setButtons(Dialog.YESNO);
        setBodyStyleName("pad-text");
        addText("Sei sicuro di voler annullare il calolo?");
        //getItem(0).getFocusSupport().setIgnore(true);
        setModal(true);

    }

    @Override
    protected void createButtons() {

        ok = new Button("Si");
        ok.addSelectionListener(new SelectionListener<ButtonEvent>() {
            public void componentSelected(ButtonEvent ce) {
                Dispatcher.forwardEvent(CentralEvents.ShowPanel, Eventi.MAIN);
                Dispatcher.forwardEvent(EventoEvents.ClearPanel, Eventi.MAIN);
                hide();
            }
        });

        cancel = new Button("No");
        cancel.addSelectionListener(new SelectionListener<ButtonEvent>() {
            public void componentSelected(ButtonEvent ce) {
                hide();
            }
        });
        addButton(ok);
        addButton(cancel);
    }

}
