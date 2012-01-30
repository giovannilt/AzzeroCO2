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
import it.agilis.mens.azzeroCO2.client.mvc.views.PubblicazioniView;
import it.agilis.mens.azzeroCO2.client.services.AzzerroCO2UtilsClientHelper;
import it.agilis.mens.azzeroCO2.shared.Eventi;
import it.agilis.mens.azzeroCO2.shared.Profile;
import it.agilis.mens.azzeroCO2.shared.model.CouponType;
import it.agilis.mens.azzeroCO2.shared.model.OrdineModel;
import it.agilis.mens.azzeroCO2.shared.model.RiepilogoModel;
import it.agilis.mens.azzeroCO2.shared.model.amministrazione.CouponModel;
import it.agilis.mens.azzeroCO2.shared.model.evento.TipoDiCartaModel;
import it.agilis.mens.azzeroCO2.shared.model.pagamento.Esito;
import it.agilis.mens.azzeroCO2.shared.model.pagamento.PagamentoModel;
import it.agilis.mens.azzeroCO2.shared.model.registrazione.UserInfoModel;
import it.agilis.mens.azzeroCO2.shared.vto.OrdineVTO;

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
    private final PubblicazioniView pubblicazioneView = new PubblicazioniView(this);
    private final NumberFormat number = NumberFormat.getFormat("0.00");


    public PubblicazioniController() {
        registerEventTypes(AzzeroCO2Events.Init);
        registerEventTypes(AzzeroCO2Events.Error);
        registerEventTypes(AzzeroCO2Events.LoggedIn);
        registerEventTypes(PubblicazioniEvents.Save);
        registerEventTypes(PubblicazioniEvents.Next);
        registerEventTypes(PubblicazioniEvents.Previous);
        registerEventTypes(PubblicazioniEvents.ClearPanel);
        registerEventTypes(PubblicazioniEvents.Riepilogo);
        registerEventTypes(PubblicazioniEvents.Acquisto);
        registerEventTypes(PubblicazioniEvents.LoadPubblicazione);
        registerEventTypes(PubblicazioniEvents.CaricaCoefficienti);
        registerEventTypes(PubblicazioniEvents.CaricaProgettiDiCompensazione);
        registerEventTypes(PubblicazioniEvents.PreviousText);
        registerEventTypes(PubblicazioniEvents.NextText);
        registerEventTypes(PubblicazioniEvents.Conferma);
        registerEventTypes(PubblicazioniEvents.ShowConfermDialog);
        registerEventTypes(PubblicazioniEvents.ShowInfoDialog);
        registerEventTypes(PubblicazioniEvents.ShowRiepilogo);
        registerEventTypes(PubblicazioniEvents.ShowStep);
        registerEventTypes(PubblicazioniEvents.GoToBegin);
        registerEventTypes(PubblicazioniEvents.ClearStep);
        registerEventTypes(PubblicazioniEvents.InAttesaDiConfermaPagamento);
        registerEventTypes(PubblicazioniEvents.UseCoupon);
    }

    @Override
    public void handleEvent(AppEvent event) {
        if (event.getType().equals(PubblicazioniEvents.InAttesaDiConfermaPagamento)) {
            final MyAsyncCallback asyncCallback = new MyAsyncCallback();
            final Timer timer = new Timer() {
                public void run() {
                    OrdineModel ordineModel = pubblicazioneView.getRiepilogo();
                    ordineModel.setEventiType(Eventi.UNA_PUBBLICAZIONE.name());
                    getHustonService().isPagato(AzzerroCO2UtilsClientHelper.getDettaglioVTO(ordineModel), getUserInfoModel(), asyncCallback);
                    asyncCallback.setTimer(this);
                }
            };
            timer.schedule(10000);
        } else if (event.getType().equals(PubblicazioniEvents.ShowRiepilogo)) {
            setCoefficentitoEventoView();
            pubblicazioneView.showRiepilogo();
        } else if (event.getType().equals(PubblicazioniEvents.Riepilogo)) {
            setCoefficentitoEventoView();
        } else if (event.getType().equals(PubblicazioniEvents.LoadPubblicazione)) {
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
            if (getProgettiDiCompensazioneList().size() == 0) {
                setProgettiDiCompensazione();
            }
            pubblicazioneView.setProgettiDiCompensazione(getProgettiDiCompensazioneList());
        } else if (event.getType().equals(PubblicazioniEvents.Conferma)) {
            if (getUserInfoModel().getProfilo() == Profile.Guest.ordinal()) {
                Dispatcher.forwardEvent(LoginEvents.ShowForm);
            } else {
                OrdineModel model = pubblicazioneView.getRiepilogo();
                model.setEventiType(Eventi.UNA_PUBBLICAZIONE.name());
                double kgCO2 = getTotaleKgCO2(model);
                Double importo= new Double(0.0);
                PagamentoModel pagamentoModel = null;
                if (model.getProgettoDiCompensazioneModel() != null) {

                    CouponModel couponModel = model.getCouponModel();
                    if (couponModel != null && !"".equalsIgnoreCase(couponModel.getTipo())) {
                        couponModel.setAttivo(false);
                        try {
                            Double totale = model.getProgettoDiCompensazioneModel().getPrezzo();

                            if (couponModel.getTipo().equalsIgnoreCase(CouponType.EURO.toString())) {
                                Double val = (kgCO2 * totale) - couponModel.getValore();
                                if (val < 0) {
                                    val = 0.0;
                                }
                                pagamentoModel = new PagamentoModel(number.format(val));
                                importo=val;
                            } else if (couponModel.getTipo().equalsIgnoreCase(CouponType.PERCENTO.toString())) {
                                importo =(kgCO2 * totale) * couponModel.getValore() / 100;
                                pagamentoModel = new PagamentoModel(number.format(importo));
                            } else if (couponModel.getTipo().equalsIgnoreCase(CouponType.OMAGGIO.toString())) {
                                pagamentoModel = new PagamentoModel(number.format(0.0));
                            }

                        } catch (Exception e) {
                            Info.display("ERROR", e.getMessage());
                        }
                    } else {
                        pagamentoModel = new PagamentoModel(number.format(kgCO2 * model.getProgettoDiCompensazioneModel().getPrezzo()));
                    }

                    pagamentoModel.setLastUpdate(new Date());
                    pagamentoModel.setKgCO2(kgCO2);
                    model.setPagamentoModel(pagamentoModel);
                    model.setCouponModel(couponModel);

                    if( couponModel!= null &&
                            !couponModel.getTipo().equalsIgnoreCase(CouponType.OMAGGIO.toString())){
                        if(importo>10.0) {
                            Dispatcher.forwardEvent(PagamentoSellaEvents.ShowForm, model);
                        }else{
                            Info.display("Info", "Non e' possibile comperare ordini inferiori ai 10 euro");
                        }
                    } else{
                        save(model);
                        pubblicazioneView.showConferma(AzzerroCO2UtilsClientHelper.getDettaglioVTO(model));
                    }
                } else {
                    Info.display("Info", "Seleziona il Progetto di compensazione");
                }
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
                model.setEventiType(Eventi.UNA_PUBBLICAZIONE.name());
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
            OrdineModel ordineModel = pubblicazioneView.getRiepilogo();
            ordineModel.setEventiType(Eventi.UNA_PUBBLICAZIONE.name());
            saveVTO(AzzerroCO2UtilsClientHelper.getDettaglioVTO(ordineModel));
        } else if (model != null) {
            model.setEventiType(Eventi.UNA_PUBBLICAZIONE.name());
            saveVTO(AzzerroCO2UtilsClientHelper.getDettaglioVTO(model));
        }
    }

    private void saveVTO(final OrdineVTO riepilogo) {
        if (riepilogo.getNome() == null || riepilogo.getNome().length() == 0) {
            Info.display("Warning", "Nome Pubblicazione Mancante");
        } else {
            AsyncCallback<OrdineVTO> dettaglio = new AsyncCallback<OrdineVTO>() {
                public void onFailure(Throwable caught) {
                    Info.display("Error", "Errore impossibile connettersi al server " + caught);
                }

                @Override
                public void onSuccess(OrdineVTO result) {
                    if (result != null) {
                        OrdineModel model = AzzerroCO2UtilsClientHelper.getDettaglioModel(result);
                        pubblicazioneView.setDettaglioModel(model);
                        Info.display("Info", "Pubblicazione " + riepilogo.getNome() + " salvato con successo.");
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

    class MyAsyncCallback implements AsyncCallback<OrdineVTO> {
        private Timer timer;
        private int numeroDiVolte = 12;

        public void onFailure(Throwable caught) {
            Info.display("Error", "Errore impossibile connettersi al server " + caught);
        }

        @Override
        public void onSuccess(OrdineVTO result) {
            if (result != null) {
                if (result.getPagamentoModel().getEsito().equalsIgnoreCase(Esito.PAGATO.toString())) {
                    Info.display("Info", "Pagamento Avvenuto con sucesso");
                    Dispatcher.forwardEvent(PagamentoSellaEvents.CloseForm);

                    pubblicazioneView.showConferma(result);

                    sentMail(result);
                }
                if (result.getPagamentoModel().getEsito().equalsIgnoreCase(Esito.ANNULLATO.toString())) {
                    Info.display("Info", "La Banca ha rifiutato la transazione, il pagamento si ritiene annullato.");
                    Dispatcher.forwardEvent(PagamentoSellaEvents.CloseForm);
                } else {
                    if (numeroDiVolte > 0) {
                        Info.display("Info", "Non Ancora pagato");
                        getTimer().schedule(10000);
                        numeroDiVolte--;
                    } else {
                        Info.display("Info", "Evento non pagato, atteso pagamento per piu' di 2 minuti, si consiglia di ricaricare ");
                        Dispatcher.forwardEvent(PagamentoSellaEvents.EnableButton);
                    }
                }
                OrdineModel model = AzzerroCO2UtilsClientHelper.getDettaglioModel(result);
                pubblicazioneView.setDettaglioModel(model);
            } else {
                Info.display("Error", "Errore impossibile connettersi al server ERRORE DI SISTEMA");
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
