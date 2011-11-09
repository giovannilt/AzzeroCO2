package it.agilis.mens.azzeroCO2.client.mvc.views;

import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.extjs.gxt.ui.client.mvc.View;
import it.agilis.mens.azzeroCO2.client.components.PagamentoSella;
import it.agilis.mens.azzeroCO2.client.mvc.events.PagamentoSellaEvents;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 3/17/11
 * Time: 11:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class PagamentoSellaView extends View {
    // la finestra di login è una  dialog modale
    private PagamentoSella pagamentroSellaDialog;

    public PagamentoSellaView(Controller controller) {
        super(controller);
    }

    @Override
    protected void initialize() {
        super.initialize();
        pagamentroSellaDialog = new PagamentoSella();
    }

    @Override
    protected void handleEvent(AppEvent event) {
        if (event.getType() == PagamentoSellaEvents.CloseForm) {
            pagamentroSellaDialog.hide();
        } else if (event.getType() == PagamentoSellaEvents.ShowForm) {
            pagamentroSellaDialog.show();
        }
    }


}