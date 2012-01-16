package it.agilis.mens.azzeroCO2.client.components.evento.dialogs;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.button.Button;
import it.agilis.mens.azzeroCO2.client.mvc.events.EventoEvents;
import it.agilis.mens.azzeroCO2.client.mvc.events.UnAnnoDiAttivitaEvents;
import it.agilis.mens.azzeroCO2.shared.Eventi;
import it.agilis.mens.azzeroCO2.shared.model.RiepilogoModel;


/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 3/12/11
 * Time: 1:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class RiepilogoConfermDialog extends Dialog {

    protected Button ok;
    protected Button cancel;

    private RiepilogoModel model;

    public RiepilogoConfermDialog() {

        setHeading("Eliminara");
        // setButtons(Dialog.YESNO);
        setBodyStyleName("pad-text");
        addText("Sei sicuro di voler eliminare questo elemento?");
        //getItem(0).getFocusSupport().setIgnore(true);
        setModal(true);

    }

    @Override
    protected void createButtons() {

        ok = new Button("Si");
        ok.addSelectionListener(new SelectionListener<ButtonEvent>() {
            public void componentSelected(ButtonEvent ce) {
                if (Eventi.EVENTO == Eventi.valueOf(model.getEventi())) {
                    Dispatcher.forwardEvent(EventoEvents.RemoveModel, model);
                } else if (Eventi.CONOSCI_CO2 == Eventi.valueOf(model.getEventi())) {
                } else if (Eventi.ANNO_DI_ATTIVITA == Eventi.valueOf(model.getEventi())) {
                    Dispatcher.forwardEvent(UnAnnoDiAttivitaEvents.RemoveModel, model);
                } else if (Eventi.UNA_PUBBLICAZIONE == Eventi.valueOf(model.getEventi())) {
                } else if (Eventi.WEB == Eventi.valueOf(model.getEventi())) {
                }

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

    public void setModel(RiepilogoModel model) {
        this.model = model;
    }
}
