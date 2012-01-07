package it.agilis.mens.azzeroCO2.client.mvc.controllers;

import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.widget.Info;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import it.agilis.mens.azzeroCO2.client.mvc.events.AzzeroCO2Events;
import it.agilis.mens.azzeroCO2.client.mvc.events.LoginEvents;
import it.agilis.mens.azzeroCO2.client.mvc.events.PagamentoSellaEvents;
import it.agilis.mens.azzeroCO2.client.mvc.events.PubblicazioniEvents;
import it.agilis.mens.azzeroCO2.client.mvc.views.UnaPubblicazioneView;
import it.agilis.mens.azzeroCO2.client.services.AzzerroCO2UtilsClientHelper;
import it.agilis.mens.azzeroCO2.shared.Profile;
import it.agilis.mens.azzeroCO2.shared.model.OrdineModel;
import it.agilis.mens.azzeroCO2.shared.model.RiepilogoModel;
import it.agilis.mens.azzeroCO2.shared.model.evento.TipoDiCartaModel;
import it.agilis.mens.azzeroCO2.shared.model.pagamento.PagamentoModel;
import it.agilis.mens.azzeroCO2.shared.model.registrazione.UserInfoModel;
import it.agilis.mens.azzeroCO2.shared.vto.DettaglioVTO;

import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: serenadimaida
 * Date: 07/12/11
 * Time: 22:37
 * To change this template use File | Settings | File Templates.
 */
public class PubblicazioniController extends BaseController {
    private final UnaPubblicazioneView pubblicazioneView = new UnaPubblicazioneView(this);
    private final NumberFormat number = NumberFormat.getFormat("0.00");


    public PubblicazioniController() {
        registerEventTypes(AzzeroCO2Events.Init);
        registerEventTypes(AzzeroCO2Events.Error);
        registerEventTypes(PubblicazioniEvents.Next);
        registerEventTypes(PubblicazioniEvents.Previous);
        registerEventTypes(PubblicazioniEvents.ClearPanel);
        registerEventTypes(PubblicazioniEvents.Save);
        registerEventTypes(AzzeroCO2Events.LoggedIn);
        registerEventTypes(PubblicazioniEvents.Riepilogo);
        registerEventTypes(PubblicazioniEvents.Acquisto);
        registerEventTypes(PubblicazioniEvents.LoadEvento);
        registerEventTypes(PubblicazioniEvents.CaricaCoefficienti);
        registerEventTypes(PubblicazioniEvents.CaricaProgettiDiCompensazione);
        registerEventTypes(PubblicazioniEvents.PreviousText);
        registerEventTypes(PubblicazioniEvents.NextText);
        registerEventTypes(PubblicazioniEvents.Conferma);
        registerEventTypes(PubblicazioniEvents.ShowConfermDialog);
        registerEventTypes(PubblicazioniEvents.ShowInfoDialog);
        registerEventTypes(PubblicazioniEvents.ShowRiepilogo);
        registerEventTypes(PubblicazioniEvents.ShowStep);
        registerEventTypes(PubblicazioniEvents.ClearStep);
        registerEventTypes(PubblicazioniEvents.InAttesaDiConfermaPagamento);
    }

    @Override
    public void handleEvent(AppEvent event) {
        if (event.getType().equals(PubblicazioniEvents.InAttesaDiConfermaPagamento)) {
            final DettaglioVTO riepilogo = AzzerroCO2UtilsClientHelper.getDettaglioVTO(pubblicazioneView.getRiepilogo());

            final Timer timer = new Timer() {
                public void run() {
                    MyAsyncCallback asyncCallback = new MyAsyncCallback();
                    getHustonService().isPagato(riepilogo, getUserInfoModel(), asyncCallback);
                    asyncCallback.setTimer(this);
                }
            };
            timer.schedule(10000);
        } else if (event.getType().equals(PubblicazioniEvents.ShowRiepilogo)) {
            setCoefficentitoEventoView();
            pubblicazioneView.showRiepilogo();
        } else if (event.getType().equals(PubblicazioniEvents.Riepilogo)) {
            setCoefficentitoEventoView();
        } else if (event.getType().equals(PubblicazioniEvents.LoadEvento)) {
            setCoefficienti();
            setProgettiDiCompensazione();
            pubblicazioneView.setProgettiDiCompensazione(getProgettiDiCompensazioneList());
            pubblicazioneView.setDettaglioModel((OrdineModel) event.getData());
            pubblicazioneView.setRiassunto((OrdineModel) event.getData(), true, false, false);
        } else if (event.getType().equals(AzzeroCO2Events.Init)) {
            AsyncCallback<List<TipoDiCartaModel>> tipoDiCartaCallBack = new AsyncCallback<List<TipoDiCartaModel>>() {
                public void onFailure(Throwable caught) {
                    Info.display("Error", "Errore impossibile connettersi al server");
                }

                @Override
                public void onSuccess(List<TipoDiCartaModel> result) {
                    if (result != null) {
                        pubblicazioneView.setTipoDiCarta(result);
                    }
                }
            };
            setCoefficienti();
            getHustonService().getTipoDiCarta(tipoDiCartaCallBack);
            forwardToView(pubblicazioneView, event);
        } else if (event.getType().equals(PubblicazioniEvents.Acquisto)) {
            if (getUserInfoModel().getProfilo() == Profile.Guest.ordinal()) {
                Dispatcher.forwardEvent(LoginEvents.ShowForm);
            } else {
                if (getProgettiDiCompensazioneList().size() == 0) {
                    setProgettiDiCompensazione();
                }
                pubblicazioneView.setProgettiDiCompensazione(getProgettiDiCompensazioneList());
            }
        } else if (event.getType().equals(PubblicazioniEvents.Conferma)) {
            OrdineModel model = pubblicazioneView.getRiepilogo();

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
        } else if (event.getType().equals(PubblicazioniEvents.CaricaProgettiDiCompensazione)) {
            if (getProgettiDiCompensazioneList().size() == 0) {
                setProgettiDiCompensazione();
            }
            pubblicazioneView.setProgettiDiCompensazione(getProgettiDiCompensazioneList());
        } else if (event.getType().equals(PubblicazioniEvents.CaricaCoefficienti)) {
            setCoefficienti();
        } else if (event.getType().equals(AzzeroCO2Events.LoggedIn)) {
            setUserInfoModel((UserInfoModel) event.getData());
            pubblicazioneView.setUserInfo(getUserInfoModel());
        } else if (event.getType().equals(PubblicazioniEvents.Save)) {
            if (event.getData() instanceof OrdineModel) {
                OrdineModel model = (OrdineModel) event.getData();
                save(model);
            } else {
                save(null);
            }
        } else {
            forwardToView(pubblicazioneView, event);
        }
    }

    private void save(OrdineModel model) {
        if (getUserInfoModel().getProfilo() == Profile.Guest.ordinal()) {
            Dispatcher.forwardEvent(LoginEvents.ShowForm);
        } else if (model == null) {
            saveVTO(AzzerroCO2UtilsClientHelper.getDettaglioVTO(pubblicazioneView.getRiepilogo()));
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
                        OrdineModel model = AzzerroCO2UtilsClientHelper.getDettaglioModel(result);
                        pubblicazioneView.setDettaglioModel(model);
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
            pubblicazioneView.riepilogo(getCoefficientiMAP());
        }
    }

    private double getTotaleKgCO2(OrdineModel model) {
        List<RiepilogoModel> eventoRiepilogoModels = pubblicazioneView.riepilogo(getCoefficientiMAP());
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


                pubblicazioneView.showConferma(result);
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
