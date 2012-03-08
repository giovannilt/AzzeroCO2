package it.agilis.mens.azzeroCO2.client.mvc.controllers;

import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import it.agilis.mens.azzeroCO2.client.MyInfo;
import it.agilis.mens.azzeroCO2.client.mvc.events.AzzeroCO2Events;
import it.agilis.mens.azzeroCO2.client.mvc.events.LoginEvents;
import it.agilis.mens.azzeroCO2.client.mvc.events.PagamentoSellaEvents;
import it.agilis.mens.azzeroCO2.client.mvc.events.UnAnnoDiAttivitaEvents;
import it.agilis.mens.azzeroCO2.client.mvc.views.UnAnnoDiAttivitaView;
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
 * User: giovannilt
 * Date: 7/2/11
 * Time: 11:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class UnAnnoDiAttivitaController extends BaseController {

    private final UnAnnoDiAttivitaView annoView = new UnAnnoDiAttivitaView(this);
    private final NumberFormat number = NumberFormat.getFormat("0.00");


    public UnAnnoDiAttivitaController() {
        registerEventTypes(AzzeroCO2Events.Init);
        registerEventTypes(AzzeroCO2Events.Error);
        registerEventTypes(AzzeroCO2Events.LoggedIn);
        registerEventTypes(UnAnnoDiAttivitaEvents.Next);
        registerEventTypes(UnAnnoDiAttivitaEvents.Previous);
        registerEventTypes(UnAnnoDiAttivitaEvents.ClearPanel);
        registerEventTypes(UnAnnoDiAttivitaEvents.Save);
        registerEventTypes(UnAnnoDiAttivitaEvents.Riepilogo);
        registerEventTypes(UnAnnoDiAttivitaEvents.Acquisto);
        registerEventTypes(UnAnnoDiAttivitaEvents.LoadUnAnnoDiAttivita);
        registerEventTypes(UnAnnoDiAttivitaEvents.CaricaCoefficienti);
        registerEventTypes(UnAnnoDiAttivitaEvents.CaricaProgettiDiCompensazione);
        registerEventTypes(UnAnnoDiAttivitaEvents.PreviousText);
        registerEventTypes(UnAnnoDiAttivitaEvents.NextText);
        registerEventTypes(UnAnnoDiAttivitaEvents.Conferma);
        registerEventTypes(UnAnnoDiAttivitaEvents.ShowConfermDialog);
        registerEventTypes(UnAnnoDiAttivitaEvents.ShowInfoDialog);
        registerEventTypes(UnAnnoDiAttivitaEvents.ShowRiepilogo);
        registerEventTypes(UnAnnoDiAttivitaEvents.GoToBegin);
        registerEventTypes(UnAnnoDiAttivitaEvents.ShowStep);
        registerEventTypes(UnAnnoDiAttivitaEvents.ClearStep);
        registerEventTypes(UnAnnoDiAttivitaEvents.InAttesaDiConfermaPagamento);
        registerEventTypes(UnAnnoDiAttivitaEvents.NorthPanelShowButtons);
        registerEventTypes(UnAnnoDiAttivitaEvents.RemoveModel);
        registerEventTypes(UnAnnoDiAttivitaEvents.UseCoupon);
    }

    @Override
    public void handleEvent(AppEvent event) {
        if (event.getType().equals(UnAnnoDiAttivitaEvents.InAttesaDiConfermaPagamento)) {
            final MyAsyncCallback asyncCallback = new MyAsyncCallback();
            final Timer timer = new Timer() {
                public void run() {
                    OrdineModel riepilogo = annoView.getRiepilogo();
                    riepilogo.setEventiType(Eventi.ANNO_DI_ATTIVITA.name());
                    getHustonService().isPagato(AzzerroCO2UtilsClientHelper.getDettaglioVTO(riepilogo), getUserInfoModel(), asyncCallback);
                    asyncCallback.setTimer(this);
                }
            };
            timer.schedule(10000);
        } else if (event.getType().equals(UnAnnoDiAttivitaEvents.ShowRiepilogo)) {
            setCoefficentitoEventoView();
            annoView.showRiepilogo();
        } else if (event.getType().equals(UnAnnoDiAttivitaEvents.Riepilogo)) {
            setCoefficentitoEventoView();
        } else if (event.getType().equals(UnAnnoDiAttivitaEvents.LoadUnAnnoDiAttivita)) {
            setCoefficienti();
            setProgettiDiCompensazione();
            annoView.setProgettiDiCompensazione(getProgettiDiCompensazioneList());
            annoView.setDettaglioModel((OrdineModel) event.getData());
            annoView.setRiassunto((OrdineModel) event.getData(), true, false, false);
        } else if (event.getType().equals(AzzeroCO2Events.Init)) {
            AsyncCallback<List<TipoDiCartaModel>> tipoDiCartaCallBack = new AsyncCallback<List<TipoDiCartaModel>>() {
                public void onFailure(Throwable caught) {
                    MyInfo.show("Error", "Errore impossibile connettersi al server", 7000);
                }

                @Override
                public void onSuccess(List<TipoDiCartaModel> result) {
                    if (result != null) {
                        annoView.setTipoDiCarta(result);
                    }
                }
            };
            setCoefficienti();
            getHustonService().getTipoDiCarta(tipoDiCartaCallBack);
            forwardToView(annoView, event);
        } else if (event.getType().equals(UnAnnoDiAttivitaEvents.Acquisto)) {
            if (getProgettiDiCompensazioneList().size() == 0) {
                setProgettiDiCompensazione();
            }
            annoView.setProgettiDiCompensazione(getProgettiDiCompensazioneList());
        } else if (event.getType().equals(UnAnnoDiAttivitaEvents.Conferma)) {
            if (getUserInfoModel().getProfilo() == Profile.Guest.ordinal()) {
                Dispatcher.forwardEvent(LoginEvents.ShowForm);
            } else {
                OrdineModel model = annoView.getRiepilogo();
                model.setEventiType(Eventi.ANNO_DI_ATTIVITA.name());
                double kgCO2 = getTotaleKgCO2(model);
                Double importo = new Double(0.0);

                // TODO Calcolare il totale togliendo lo sconto COUPON
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
                                importo = val;
                            } else if (couponModel.getTipo().equalsIgnoreCase(CouponType.PERCENTO.toString())) {
                                importo = (kgCO2 * totale) * couponModel.getValore() / 100;
                                pagamentoModel = new PagamentoModel(number.format(importo));
                            } else if (couponModel.getTipo().equalsIgnoreCase(CouponType.OMAGGIO.toString())) {
                                pagamentoModel = new PagamentoModel(number.format(0.0));
                            }

                        } catch (Exception e) {
                            MyInfo.show("ERROR", e.getMessage(), 7000);
                        }
                    } else {
                        importo = kgCO2 * model.getProgettoDiCompensazioneModel().getPrezzo();
                        pagamentoModel = new PagamentoModel(number.format(importo));
                    }
                    pagamentoModel.setLastUpdate(new Date());
                    pagamentoModel.setKgCO2(kgCO2);
                    model.setPagamentoModel(pagamentoModel);
                    model.setCouponModel(couponModel);

                    if (couponModel == null || (couponModel != null &&
                            !couponModel.getTipo().equalsIgnoreCase(CouponType.OMAGGIO.toString()))) {
                        if (importo > 0.01) { //rimettere 10 euro
                            Dispatcher.forwardEvent(PagamentoSellaEvents.ShowForm, model);
                        } else {
                            MyInfo.show("Non e' possibile comperare ordini inferiori ai 10 euro");
                        }
                    } else {
                        PagamentoModel p = model.getPagamentoModel();
                        p.setEsito(Esito.OMAGGIO.name());
                        model.setPagamentoModel(p);
                        save(model);

                        annoView.showConferma(AzzerroCO2UtilsClientHelper.getDettaglioVTO(model));
                    }

                } else {
                    MyInfo.show("Seleziona il Progetto di compensazione");
                }
            }
        } else if (event.getType().equals(UnAnnoDiAttivitaEvents.CaricaProgettiDiCompensazione)) {
            if (getProgettiDiCompensazioneList().size() == 0) {
                setProgettiDiCompensazione();
            }
            annoView.setProgettiDiCompensazione(getProgettiDiCompensazioneList());
        } else if (event.getType().equals(UnAnnoDiAttivitaEvents.CaricaCoefficienti)) {
            setCoefficienti();
        } else if (event.getType().equals(AzzeroCO2Events.LoggedIn)) {
            setUserInfoModel((UserInfoModel) event.getData());
            annoView.setUserInfo(getUserInfoModel());
        } else if (event.getType().equals(UnAnnoDiAttivitaEvents.Save)) {
            if (event.getData() instanceof OrdineModel) {
                OrdineModel model = (OrdineModel) event.getData();
                model.setEventiType(Eventi.ANNO_DI_ATTIVITA.name());
                save(model);
            } else {
                save(null);
            }
        } else {
            forwardToView(annoView, event);
        }
    }

    private void save(OrdineModel model) {
        if (getUserInfoModel().getProfilo() == Profile.Guest.ordinal()) {
            Dispatcher.forwardEvent(LoginEvents.ShowForm);
        } else if (model == null) {
            OrdineModel riepilogo = annoView.getRiepilogo();
            riepilogo.setEventiType(Eventi.ANNO_DI_ATTIVITA.name());
            saveVTO(AzzerroCO2UtilsClientHelper.getDettaglioVTO(riepilogo));
        } else if (model != null) {
            model.setEventiType(Eventi.ANNO_DI_ATTIVITA.name());
            saveVTO(AzzerroCO2UtilsClientHelper.getDettaglioVTO(model));
        }
    }

    private void saveVTO(final OrdineVTO riepilogo) {
        if (riepilogo.getNome() == null || riepilogo.getNome().length() == 0) {
            MyInfo.show("Warning", "Nome Anno di attivà mancante", 7000);
        } else {
            AsyncCallback<OrdineVTO> dettaglio = new AsyncCallback<OrdineVTO>() {
                public void onFailure(Throwable caught) {
                    MyInfo.show("Error", "Errore impossibile connettersi al server " + caught, 7000);
                }

                @Override
                public void onSuccess(OrdineVTO result) {
                    if (result != null) {
                        OrdineModel model = AzzerroCO2UtilsClientHelper.getDettaglioModel(result);
                        annoView.setDettaglioModel(model);
                        MyInfo.show("Anno di attivià " + riepilogo.getNome() + " salvato con successo.");
                        if (model.getPagamentoModel().getEsito().equalsIgnoreCase(Esito.OMAGGIO.name())) {
                            openConfermaToAzzeroCO2_IT(model);
                        }
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
                MyInfo.show("Error", "Errore impossibile recuperare i coefficenti dal server 001", 7000);
            }
        }
        if (getCoefficientiMAP() == null || getCoefficientiMAP().values().size() == 0) {
            MyInfo.show("Error", "Errore impossibile recuperare i coefficenti dal server 002", 7000);
        } else {
            annoView.riepilogo(getCoefficientiMAP());
        }
    }

    private double getTotaleKgCO2(OrdineModel model) {
        List<RiepilogoModel> riepilogo = annoView.riepilogo(getCoefficientiMAP());
        double totale = 0;
        for (RiepilogoModel r : riepilogo) {
            totale += r.getKgCO2();
        }
        return totale;
    }

    class MyAsyncCallback implements AsyncCallback<OrdineVTO> {
        private Timer timer;
        private int numeroDiVolte = 60;

        public void onFailure(Throwable caught) {
            MyInfo.show("Error", "Errore impossibile connettersi al server " + caught, 7000);
        }

        @Override
        public void onSuccess(OrdineVTO result) {
            if (result != null) {
                if (result.getPagamentoModel().getEsito().equalsIgnoreCase(Esito.PAGATO.toString())) {
                    MyInfo.show("Pagamento Avvenuto con sucesso");
                    Dispatcher.forwardEvent(PagamentoSellaEvents.CloseForm);
                    annoView.showConferma(result);
                    sentMail(result);
                } else {
                    if (numeroDiVolte > 0) {
                        MyInfo.show("Non Ancora pagato");
                        getTimer().schedule(10000);
                        numeroDiVolte--;
                    }
                    if (result.getPagamentoModel().getEsito().equalsIgnoreCase(Esito.PAGAMENTO_NON_AVVENUTO.toString())) {
                        MyInfo.show("La Banca ha rifiutato la transazione, il pagamento si ritiene annullato.");
                        Dispatcher.forwardEvent(PagamentoSellaEvents.CloseForm);
                    } else {
                        MyInfo.show("Evento non pagato, atteso pagamento per piu' di 10 minuti, si consiglia di ricaricare ");
                        Dispatcher.forwardEvent(PagamentoSellaEvents.EnableButton);
                    }
                }
                OrdineModel model = AzzerroCO2UtilsClientHelper.getDettaglioModel(result);
                annoView.setDettaglioModel(model);
            } else {
                MyInfo.show("Error", "Errore impossibile connettersi al server ERRORE DI SISTEMA", 7000);
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
