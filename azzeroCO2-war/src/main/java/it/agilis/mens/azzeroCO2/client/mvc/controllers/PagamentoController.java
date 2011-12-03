package it.agilis.mens.azzeroCO2.client.mvc.controllers;

import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.widget.Info;
import com.google.gwt.user.client.rpc.AsyncCallback;
import it.agilis.mens.azzeroCO2.client.mvc.events.PagamentoSellaEvents;
import it.agilis.mens.azzeroCO2.client.mvc.views.PagamentoSellaView;
import it.agilis.mens.azzeroCO2.shared.model.evento.DettaglioModel;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 3/17/11
 * Time: 11:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class PagamentoController extends BaseController {
    private PagamentoSellaView pagamentroView = new PagamentoSellaView(this);

    public PagamentoController() {
        registerEventTypes(PagamentoSellaEvents.ShowForm);
        registerEventTypes(PagamentoSellaEvents.SubmitForm);
        registerEventTypes(PagamentoSellaEvents.CloseForm);
        registerEventTypes(PagamentoSellaEvents.CreateCertificato);
    }

    public void handleEvent(AppEvent event) {
        EventType type = event.getType();
        if (type.equals(PagamentoSellaEvents.CreateCertificato)) {
           AsyncCallback<String> aCallback = new AsyncCallback<String>() {
            public void onFailure(Throwable caught) {
                Info.display("Error", "Errore impossibile connettersi al server");
            }

            @Override
            public void onSuccess(String result) {

            }
        };
        getHustonService().creaCertificatoInPDF(aCallback);

        } else if (type.equals(PagamentoSellaEvents.ShowForm)) {
            forwardToView(pagamentroView, event);
            DettaglioModel model = (DettaglioModel) event.getData();
            pagamentroView.setPagamentoModel(model);
        } else {
            forwardToView(pagamentroView, event);
        }
    }

}