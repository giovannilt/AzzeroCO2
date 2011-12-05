package it.agilis.mens.azzeroCO2.client.mvc.controllers;

import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.widget.Info;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import it.agilis.mens.azzeroCO2.client.mvc.events.AzzeroCO2Events;
import it.agilis.mens.azzeroCO2.client.mvc.events.EventoEvents;
import it.agilis.mens.azzeroCO2.client.mvc.events.LoginEvents;
import it.agilis.mens.azzeroCO2.client.mvc.events.PagamentoSellaEvents;
import it.agilis.mens.azzeroCO2.client.mvc.views.EventoView;
import it.agilis.mens.azzeroCO2.client.services.AzzerroCO2UtilsClientHelper;
import it.agilis.mens.azzeroCO2.shared.Profile;
import it.agilis.mens.azzeroCO2.shared.model.RiepilogoModel;
import it.agilis.mens.azzeroCO2.shared.model.evento.DettaglioModel;
import it.agilis.mens.azzeroCO2.shared.model.evento.TipoDiCartaModel;
import it.agilis.mens.azzeroCO2.shared.model.pagamento.PagamentoModel;
import it.agilis.mens.azzeroCO2.shared.model.registrazione.UserInfoModel;
import it.agilis.mens.azzeroCO2.shared.vto.DettaglioVTO;

import java.util.Date;
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
    private final NumberFormat number = NumberFormat.getFormat("0.00");


    public EventoController() {
        registerEventTypes(AzzeroCO2Events.Init);
        registerEventTypes(AzzeroCO2Events.Error);
        registerEventTypes(EventoEvents.Next);
        registerEventTypes(EventoEvents.Previous);
        registerEventTypes(EventoEvents.ClearPanel);
        registerEventTypes(EventoEvents.Save);
        registerEventTypes(AzzeroCO2Events.LoggedIn);
        registerEventTypes(EventoEvents.Riepilogo);
        registerEventTypes(EventoEvents.Acquisto);
        registerEventTypes(EventoEvents.LoadEvento);
        registerEventTypes(EventoEvents.CaricaCoefficienti);
        registerEventTypes(EventoEvents.CaricaProgettiDiCompensazione);
        registerEventTypes(EventoEvents.PreviousText);
        registerEventTypes(EventoEvents.NextText);
        registerEventTypes(EventoEvents.Conferma);
        registerEventTypes(EventoEvents.ShowConfermDialog);
        registerEventTypes(EventoEvents.ShowInfoDialog);
        registerEventTypes(EventoEvents.ShowRiepilogo);
        registerEventTypes(EventoEvents.ShowStep);
        registerEventTypes(EventoEvents.ClearStep);
        registerEventTypes(EventoEvents.InAttesaDiConfermaPagamento);
    }

    @Override
    public void handleEvent(AppEvent event) {
        if (event.getType().equals(EventoEvents.InAttesaDiConfermaPagamento)) {
            final DettaglioVTO riepilogo = AzzerroCO2UtilsClientHelper.getDettaglioVTO(eventoView.getRiepilogo());

            final Timer timer = new Timer() {
                public void run() {
                    MyAsyncCallback asyncCallback = new MyAsyncCallback();
                    getHustonService().isPagato(riepilogo, getUserInfoModel(), asyncCallback);
                    asyncCallback.setTimer(this);
                }
            };
            timer.schedule(10000);
        } else if (event.getType().equals(EventoEvents.ShowRiepilogo)) {
            setCoefficentitoEventoView();
            eventoView.showRiepilogo();
        } else if (event.getType().equals(EventoEvents.Riepilogo)) {
            setCoefficentitoEventoView();
        } else if (event.getType().equals(EventoEvents.LoadEvento)) {
            setCoefficienti();
            setProgettiDiCompensazione();
            eventoView.setProgettiDiCompensazione(getProgettiDiCompensazioneList());
            eventoView.setDettaglioModel((DettaglioModel) event.getData());
            eventoView.setRiassunto((DettaglioModel) event.getData(), true);
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
            setCoefficienti();
            getHustonService().getTipoDiCarta(tipoDiCartaCallBack);
            forwardToView(eventoView, event);
        } else if (event.getType().equals(EventoEvents.Acquisto)) {
            if (getUserInfoModel().getProfilo() == Profile.Guest.ordinal()) {
                Dispatcher.forwardEvent(LoginEvents.ShowForm);
            } else {
                if (getProgettiDiCompensazioneList().size() == 0) {
                    setProgettiDiCompensazione();
                }
                eventoView.setProgettiDiCompensazione(getProgettiDiCompensazioneList());
            }
        } else if (event.getType().equals(EventoEvents.Conferma)) {
            DettaglioModel model = eventoView.getRiepilogo();

            double kgCO2 = getTotaleKgCO2(model);

            // TODO Calcolare il totale togliendo lo sconto COUPON
            if (model.getProgettoDiCompensazioneModel() != null) {
                PagamentoModel pagamentoModel = new PagamentoModel(number.format(kgCO2 * model.getProgettoDiCompensazioneModel().getPrezzo()));
                pagamentoModel.setLastUpdate(new Date());
                pagamentoModel.setKgCO2(kgCO2);
                model.setPagamentoModel(pagamentoModel);
                Dispatcher.forwardEvent(PagamentoSellaEvents.ShowForm, model);
            } else {
                Info.display("Info", "Seleziona il Progetto di compensazione");
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
            eventoView.setUserInfo(getUserInfoModel());
        } else if (event.getType().equals(EventoEvents.Save)) {
            if (event.getData() instanceof DettaglioModel) {
                DettaglioModel model = (DettaglioModel) event.getData();
                save(model);
            } else {
                save(null);
            }
        } else {
            forwardToView(eventoView, event);
        }
    }

    private void save(DettaglioModel model) {
        if (getUserInfoModel().getProfilo() == Profile.Guest.ordinal()) {
            Dispatcher.forwardEvent(LoginEvents.ShowForm);
        } else if (model == null) {
            saveVTO(AzzerroCO2UtilsClientHelper.getDettaglioVTO(eventoView.getRiepilogo()));
        } else if (model != null) {
            saveVTO(AzzerroCO2UtilsClientHelper.getDettaglioVTO(model));
        }
    }

    private void saveVTO(final DettaglioVTO riepilogo) {
        if (riepilogo.getNome() == null || riepilogo.getNome().length() == 0) {
            Info.display("Warning", "Nome Evento Mancante");
        } else {
            AsyncCallback<DettaglioVTO> dettaglio = new AsyncCallback<DettaglioVTO>() {
                public void onFailure(Throwable caught) {
                    Info.display("Error", "Errore impossibile connettersi al server " + caught);
                }

                @Override
                public void onSuccess(DettaglioVTO result) {
                    if (result != null) {
                        DettaglioModel model = AzzerroCO2UtilsClientHelper.getDettaglioModel(result);
                        eventoView.setDettaglioModel(model);
                        Info.display("Info", "Evento " + riepilogo.getNome() + " salvato con successo.");
                    }
                }
            };
            getHustonService().saveOrdine(riepilogo, getUserInfoModel(), dettaglio);
        }
    }

    private void setCoefficentitoEventoView() {
        if (getCoefficientiMAP() == null || getCoefficientiMAP().values().size() == 0) {
            setCoefficienti();
            if (getCoefficientiMAP() == null || getCoefficientiMAP().values().size() == 0) {
                Info.display("Error", "Errore impossibile recuperare i coefficenti dal server 001");
            }
        }
        if (getCoefficientiMAP() == null || getCoefficientiMAP().values().size() == 0) {
            Info.display("Error", "Errore impossibile recuperare i coefficenti dal server 002");
        } else {
            eventoView.riepilogo(getCoefficientiMAP());
        }
    }

    private double getTotaleKgCO2(DettaglioModel model) {
        List<RiepilogoModel> eventoRiepilogoModels = eventoView.riepilogo(getCoefficientiMAP());
        double totale = 0;
        for (RiepilogoModel r : eventoRiepilogoModels) {
            totale += r.getKgCO2();
        }
        return totale;
    }

    class MyAsyncCallback implements AsyncCallback<DettaglioVTO> {
        private Timer timer;

        public void onFailure(Throwable caught) {
            Info.display("Error", "Errore impossibile connettersi al server " + caught);
        }

        @Override
        public void onSuccess(DettaglioVTO result) {
            if (result != null) {
                Info.display("Info", "Pagamento Avvenuto con sucesso");
                Dispatcher.forwardEvent(PagamentoSellaEvents.CloseForm);


                eventoView.showConferma(result);
                sentMail(result);
            } else {
                Info.display("Info", "NON PAGATO");
                getTimer().schedule(10000);
            }
        }

        public void setTimer(Timer timer) {
            this.timer = timer;
        }

        public Timer getTimer() {
            return timer;
        }
    }


}
