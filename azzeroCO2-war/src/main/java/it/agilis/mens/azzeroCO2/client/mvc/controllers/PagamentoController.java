package it.agilis.mens.azzeroCO2.client.mvc.controllers;

import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import it.agilis.mens.azzeroCO2.client.mvc.events.PagamentoSellaEvents;
import it.agilis.mens.azzeroCO2.client.mvc.views.PagamentoSellaView;

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
    }

    public void handleEvent(AppEvent event) {
        EventType type = event.getType();
        forwardToView(pagamentroView, event);

    }

}