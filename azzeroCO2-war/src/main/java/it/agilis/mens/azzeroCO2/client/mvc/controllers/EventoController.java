package it.agilis.mens.azzeroCO2.client.mvc.controllers;

import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.widget.Info;
import com.google.gwt.user.client.rpc.AsyncCallback;
import it.agilis.mens.azzeroCO2.client.mvc.events.AzzeroCO2Events;
import it.agilis.mens.azzeroCO2.client.mvc.events.EventoEvents;
import it.agilis.mens.azzeroCO2.client.mvc.views.EventoView;
import it.agilis.mens.azzeroCO2.shared.model.evento.DettaglioModel;
import it.agilis.mens.azzeroCO2.shared.model.evento.TipoDiCartaModel;
import it.agilis.mens.azzeroCO2.shared.model.registrazione.UserInfoModel;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 3/23/11
 * Time: 10:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class EventoController extends BaseController {

    private final EventoView eventoView = new EventoView(this);

    public EventoController() {
        registerEventTypes(AzzeroCO2Events.Init);
        registerEventTypes(AzzeroCO2Events.Error);
        registerEventTypes(EventoEvents.Next);
        registerEventTypes(EventoEvents.Previous);
        registerEventTypes(EventoEvents.ClearPanel);
        registerEventTypes(EventoEvents.Save);
        registerEventTypes(AzzeroCO2Events.LoggedIn);
        registerEventTypes(EventoEvents.Riepilogo);

        registerEventTypes(EventoEvents.LoadEvento);

        registerEventTypes(EventoEvents.CaricaCoefficienti);
        registerEventTypes(EventoEvents.CaricaProgettiDiCompensazione);

        registerEventTypes(EventoEvents.PreviousText);
        registerEventTypes(EventoEvents.NextText);
    }

    @Override
    public void handleEvent(AppEvent event) {
        if (event.getType().equals(EventoEvents.LoadEvento)) {
            eventoView.setDettaglioModel((DettaglioModel) event.getData());
            eventoView.setRiassunto((DettaglioModel) event.getData());
        } else if (event.getType().equals(AzzeroCO2Events.Init)) {
            AsyncCallback<List<TipoDiCartaModel>> tipoDiCartaCallBack = new AsyncCallback<List<TipoDiCartaModel>>() {
                public void onFailure(Throwable caught) {
                    Info.display("Error", "Errore impossibile connettersi al server");
                }

                @Override
                public void onSuccess(List<TipoDiCartaModel> result) {
                    if (result != null) {
                        eventoView.setTipoDiCarta(result);
                    }
                }
            };
            getHustonService().getTipoDiCarta(tipoDiCartaCallBack);
            forwardToView(eventoView, event);
        } else if (event.getType().equals(EventoEvents.Riepilogo)) {
            if (getCoefficientiMAP() == null || getCoefficientiMAP().values().size() == 0) {
                Info.display("Error", "Errore impossibile recuperare i coefficenti dal server");
            } else {
                eventoView.riepilogo(getCoefficientiMAP());
            }
        } else if (event.getType().equals(EventoEvents.CaricaProgettiDiCompensazione)) {
            if (getProgettiDiCompensazioneList().size() == 0) {
                setProgettiDiCompensazione();
            }
            eventoView.setProgettiDiCompensazione(getProgettiDiCompensazioneList());
        } else if (event.getType().equals(EventoEvents.CaricaCoefficienti)) {
            setCoefficienti();
        } else if (event.getType().equals(AzzeroCO2Events.LoggedIn)) {
            setUserInfoModel((UserInfoModel) event.getData());
        } else if (event.getType().equals(EventoEvents.Save)) {
            if (eventoView.getRiepilogo().getNome() == null || eventoView.getRiepilogo().getNome().length() == 0) {
                Info.display("Warning", "Nome Evento Mancante");
            } else {
                final DettaglioModel evento=eventoView.getRiepilogo();
                AsyncCallback<DettaglioModel> dettaglioModel = new AsyncCallback<DettaglioModel>() {
                    public void onFailure(Throwable caught) {
                        Info.display("Error", "Errore impossibile connettersi al server");
                    }
                    @Override
                    public void onSuccess(DettaglioModel result) {
                        if (result != null) {
                            eventoView.setDettaglioModel(result);
                            Info.display("Info", "Evento "+ evento.getNome()+ " salvato con successo.");
                        }
                    }
                };

                getHustonService().saveOrdine(evento, dettaglioModel);
            }
        } else {
            forwardToView(eventoView, event);
        }
    }
}
