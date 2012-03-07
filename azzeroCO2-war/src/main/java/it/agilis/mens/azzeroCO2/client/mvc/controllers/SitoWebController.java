package it.agilis.mens.azzeroCO2.client.mvc.controllers;

import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import it.agilis.mens.azzeroCO2.client.MyInfo;
import it.agilis.mens.azzeroCO2.client.mvc.events.AzzeroCO2Events;
import it.agilis.mens.azzeroCO2.client.mvc.events.LoginEvents;
import it.agilis.mens.azzeroCO2.client.mvc.events.PagamentoSellaEvents;
import it.agilis.mens.azzeroCO2.client.mvc.events.SitoWebEvents;
import it.agilis.mens.azzeroCO2.client.mvc.views.SitoWebView;
import it.agilis.mens.azzeroCO2.client.services.AzzerroCO2UtilsClientHelper;
import it.agilis.mens.azzeroCO2.shared.Eventi;
import it.agilis.mens.azzeroCO2.shared.Profile;
import it.agilis.mens.azzeroCO2.shared.model.CouponType;
import it.agilis.mens.azzeroCO2.shared.model.OrdineModel;
import it.agilis.mens.azzeroCO2.shared.model.RiepilogoModel;
import it.agilis.mens.azzeroCO2.shared.model.amministrazione.CouponModel;
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
public class SitoWebController extends BaseController {

    private SitoWebView sitoWebView = new SitoWebView(this);

    public SitoWebController() {
        registerEventTypes(AzzeroCO2Events.Init);
        registerEventTypes(AzzeroCO2Events.Error);
        registerEventTypes(SitoWebEvents.Next);
        registerEventTypes(SitoWebEvents.NextText);
        registerEventTypes(SitoWebEvents.Previous);
        registerEventTypes(SitoWebEvents.PreviousText);
        registerEventTypes(AzzeroCO2Events.LoggedIn);
        registerEventTypes(SitoWebEvents.ClearPanel);
        registerEventTypes(SitoWebEvents.Save);
        registerEventTypes(SitoWebEvents.Riepilogo);
        registerEventTypes(SitoWebEvents.Acquisto);
        registerEventTypes(SitoWebEvents.CaricaCoefficienti);
        registerEventTypes(SitoWebEvents.CaricaProgettiDiCompensazione);
        registerEventTypes(SitoWebEvents.PreviousText);
        registerEventTypes(SitoWebEvents.NextText);
        registerEventTypes(SitoWebEvents.Conferma);
        registerEventTypes(SitoWebEvents.ShowConfermDialog);
        registerEventTypes(SitoWebEvents.GoToBegin);
        registerEventTypes(SitoWebEvents.ClearStep);
        registerEventTypes(SitoWebEvents.InAttesaDiConfermaPagamento);
        registerEventTypes(SitoWebEvents.NorthPanelShowButtons);
        registerEventTypes(SitoWebEvents.RemoveModel);
        registerEventTypes(SitoWebEvents.UseCoupon);
    }

    @Override
    public void handleEvent(AppEvent event) {
        if (event.getType().equals(AzzeroCO2Events.LoggedIn)) {
            setUserInfoModel((UserInfoModel) event.getData());
        } else if (event.getType().equals(SitoWebEvents.InAttesaDiConfermaPagamento)) {
            final MyAsyncCallback asyncCallback = new MyAsyncCallback();
            final Timer timer = new Timer() {
                public void run() {
                    OrdineModel riepilogo = sitoWebView.getRiepilogo();
                    riepilogo.setEventiType(Eventi.CONOSCI_CO2.name());
                    getHustonService().isPagato(AzzerroCO2UtilsClientHelper.getDettaglioVTO(riepilogo), getUserInfoModel(), asyncCallback);
                    asyncCallback.setTimer(this);

                }
            };
            timer.schedule(10000);

        } else if (event.getType().equals(SitoWebEvents.Riepilogo)) {
            setCoefficientiSitoWebView();
        } else if (event.getType().equals(AzzeroCO2Events.Init)) {
            setCoefficienti();
            forwardToView(sitoWebView, event);
            if (getProgettiDiCompensazioneList().size() == 0) {
                setProgettiDiCompensazione();
            }
        } else if (event.getType().equals(SitoWebEvents.UseCoupon)) {
            String couponCode = event.getData();
            if (couponCode != null && !"".equalsIgnoreCase(couponCode)) {
                AsyncCallback<CouponModel> asyncCallback = new AsyncCallback<CouponModel>() {
                    public void onFailure(Throwable caught) {
                        MyInfo.show("Error", "Errore impossibile connettersi al server", 7000);
                    }

                    @Override
                    public void onSuccess(CouponModel result) {
                        if (result != null) {
                            sitoWebView.setCoupon(result);
                        } else {
                            MyInfo.show("Warn", "Coupon non Utilizzabile", 7000);
                        }
                    }
                };
                getHustonService().getValidCouponByCode(couponCode, asyncCallback);
            }

        } else if (event.getType().equals(SitoWebEvents.Acquisto)) {
            if (getProgettiDiCompensazioneList().size() == 0) {
                setProgettiDiCompensazione();
            }
            sitoWebView.setProgettiDiCompensazione(getProgettiDiCompensazioneList());
            sitoWebView.riepilogo(getCoefficientiMAP());
        } else if (event.getType().equals(SitoWebEvents.Conferma)) {
            if (getUserInfoModel().getProfilo() == Profile.Guest.ordinal()) {
                Dispatcher.forwardEvent(LoginEvents.ShowForm);
            } else {
                OrdineModel model = sitoWebView.getRiepilogo();
                model.setEventiType(Eventi.WEB.name());
                double kgCO2 = getTotaleKgCO2(model);
                Double importo = new Double(0.0);

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
                        sitoWebView.showConferma(AzzerroCO2UtilsClientHelper.getDettaglioVTO(model));
                    }
                } else {
                    MyInfo.show("Seleziona il Progetto di compensazione");
                }
            }
        } else if (event.getType().equals(SitoWebEvents.CaricaProgettiDiCompensazione)) {
            if (getProgettiDiCompensazioneList().size() == 0) {
                setProgettiDiCompensazione();
            }
            sitoWebView.setProgettiDiCompensazione(getProgettiDiCompensazioneList());
        } else if (event.getType().equals(SitoWebEvents.CaricaCoefficienti)) {
            setCoefficienti();
        } else if (event.getType().equals(AzzeroCO2Events.LoggedIn)) {
            setUserInfoModel((UserInfoModel) event.getData());
            sitoWebView.setUserInfo(getUserInfoModel());
        } else if (event.getType().equals(SitoWebEvents.Save)) {
            if (event.getData() instanceof OrdineModel) {
                OrdineModel model = (OrdineModel) event.getData();
                model.setEventiType(Eventi.WEB.name());
                save(model);
            } else {
                save(null);
            }
        } else {
            forwardToView(sitoWebView, event);
        }
    }

    private void save(OrdineModel model) {
        if (getUserInfoModel().getProfilo() == Profile.Guest.ordinal()) {
            Dispatcher.forwardEvent(LoginEvents.ShowForm);
        } else if (model == null) {
            OrdineModel riepilogo = sitoWebView.getRiepilogo();
            riepilogo.setEventiType(Eventi.WEB.name());
            saveVTO(AzzerroCO2UtilsClientHelper.getDettaglioVTO(riepilogo));
        } else if (model != null) {

            model.setEventiType(Eventi.WEB.name());
            saveVTO(AzzerroCO2UtilsClientHelper.getDettaglioVTO(model));
        }
    }

    private void saveVTO(final OrdineVTO riepilogo) {
        AsyncCallback<OrdineVTO> dettaglio = new AsyncCallback<OrdineVTO>() {
            public void onFailure(Throwable caught) {
                MyInfo.show("Error", "Errore impossibile connettersi al server " + caught, 7000);
            }

            @Override
            public void onSuccess(OrdineVTO result) {
                if (result != null) {
                    OrdineModel model = AzzerroCO2UtilsClientHelper.getDettaglioModel(result);
                    model.setNome("Compensazione SitoWeb");
                    sitoWebView.setDettaglioModel(model);
                    openConfermaToAzzeroCO2_IT(model);
                    MyInfo.show("Evento" + riepilogo.getNome() + " salvato con successo.");
                }
            }
        };
        getHustonService().saveOrdine(riepilogo, getUserInfoModel(), dettaglio);

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

                    sitoWebView.showConferma(result);

                    sentMail(result);
                }
                if (result.getPagamentoModel().getEsito().equalsIgnoreCase(Esito.ANNULLATO.toString())) {
                    MyInfo.show("La Banca ha rifiutato la transazione, il pagamento si ritiene annullato.");
                    Dispatcher.forwardEvent(PagamentoSellaEvents.CloseForm);
                } else {
                    if (numeroDiVolte > 0) {
                        MyInfo.show("Non Ancora pagato");
                        getTimer().schedule(10000);
                        numeroDiVolte--;
                    } else {
                        MyInfo.show("Evento non pagato, atteso pagamento per piu' di 2 minuti, si consiglia di ricaricare ");
                        Dispatcher.forwardEvent(PagamentoSellaEvents.EnableButton);
                    }
                }
                OrdineModel model = AzzerroCO2UtilsClientHelper.getDettaglioModel(result);
                sitoWebView.setDettaglioModel(model);
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

    private void setCoefficientiSitoWebView() {
        if (getCoefficientiMAP() == null || getCoefficientiMAP().values().size() == 0) {
            setCoefficienti();
            if (getCoefficientiMAP() == null || getCoefficientiMAP().values().size() == 0) {
                MyInfo.show("Error", "Errore impossibile recuperare i coefficenti dal server 001", 7000);
            }
        }
        if (getCoefficientiMAP() == null || getCoefficientiMAP().values().size() == 0) {
            MyInfo.show("Error", "Errore impossibile recuperare i coefficenti dal server 002", 7000);
        } else {
            sitoWebView.riepilogo(getCoefficientiMAP());
        }
    }

    private double getTotaleKgCO2(OrdineModel model) {
        List<RiepilogoModel> eventoRiepilogoModels = sitoWebView.riepilogo(getCoefficientiMAP());
        double totale = 0;
        for (RiepilogoModel r : eventoRiepilogoModels) {
            totale += r.getKgCO2();
        }
        return totale;
    }
}
