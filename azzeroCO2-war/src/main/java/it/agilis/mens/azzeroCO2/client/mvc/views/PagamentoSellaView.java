package it.agilis.mens.azzeroCO2.client.mvc.views;

import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.extjs.gxt.ui.client.mvc.View;
import it.agilis.mens.azzeroCO2.client.components.PagamentoSella;
import it.agilis.mens.azzeroCO2.client.mvc.events.PagamentoSellaEvents;
import it.agilis.mens.azzeroCO2.shared.model.evento.DettaglioModel;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 3/17/11
 * Time: 11:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class PagamentoSellaView extends View {
    // la finestra di login è una  dialog modale
    private PagamentoSella pagamentoSellaDialog;

    public PagamentoSellaView(Controller controller) {
        super(controller);

    }

    @Override
    protected void initialize() {
        super.initialize();
        pagamentoSellaDialog = new PagamentoSella();
    }

    @Override
    protected void handleEvent(AppEvent event) {
        if (event.getType() == PagamentoSellaEvents.CloseForm) {
            pagamentoSellaDialog.hide();
        } else if (event.getType() == PagamentoSellaEvents.ShowForm) {
            pagamentoSellaDialog.show();
        }
    }


    public void setPagamentoModel(DettaglioModel model) {
        pagamentoSellaDialog.setModel(model);
    }
}