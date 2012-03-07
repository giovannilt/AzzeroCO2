package it.agilis.mens.azzeroCO2.client.mvc.controllers;

import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import it.agilis.mens.azzeroCO2.client.MyInfo;
import it.agilis.mens.azzeroCO2.client.mvc.events.AzzeroCO2Events;
import it.agilis.mens.azzeroCO2.client.mvc.events.ConoscoCO2Events;
import it.agilis.mens.azzeroCO2.client.mvc.events.LoginEvents;
import it.agilis.mens.azzeroCO2.client.mvc.events.PagamentoSellaEvents;
import it.agilis.mens.azzeroCO2.client.mvc.views.ConoscoCO2View;
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
public class ConoscoCO2Controller extends BaseController {

    private ConoscoCO2View conoscoCO2View = new ConoscoCO2View(this);

    public ConoscoCO2Controller() {
        registerEventTypes(AzzeroCO2Events.Init);
        registerEventTypes(AzzeroCO2Events.Error);
        registerEventTypes(ConoscoCO2Events.Next);
        registerEventTypes(ConoscoCO2Events.NextText);
        registerEventTypes(ConoscoCO2Events.Previous);
        registerEventTypes(ConoscoCO2Events.PreviousText);
        registerEventTypes(AzzeroCO2Events.LoggedIn);


        registerEventTypes(ConoscoCO2Events.ClearPanel);
        registerEventTypes(ConoscoCO2Events.Save);

        registerEventTypes(ConoscoCO2Events.Riepilogo);
        registerEventTypes(ConoscoCO2Events.Acquisto);

        registerEventTypes(ConoscoCO2Events.CaricaCoefficienti);
        registerEventTypes(ConoscoCO2Events.CaricaProgettiDiCompensazione);
        registerEventTypes(ConoscoCO2Events.PreviousText);
        registerEventTypes(ConoscoCO2Events.NextText);
        registerEventTypes(ConoscoCO2Events.Conferma);
        registerEventTypes(ConoscoCO2Events.ShowConfermDialog);

        registerEventTypes(ConoscoCO2Events.GoToBegin);
        registerEventTypes(ConoscoCO2Events.ClearStep);
        registerEventTypes(ConoscoCO2Events.InAttesaDiConfermaPagamento);
        registerEventTypes(ConoscoCO2Events.NorthPanelShowButtons);
        registerEventTypes(ConoscoCO2Events.RemoveModel);
        registerEventTypes(ConoscoCO2Events.UseCoupon);

    }

    @Override
    public void handleEvent(AppEvent event) {
        if (event.getType().equals(AzzeroCO2Events.LoggedIn)) {
            setUserInfoModel((UserInfoModel) event.getData());
        } else if (event.getType().equals(ConoscoCO2Events.InAttesaDiConfermaPagamento)) {
            final MyAsyncCallback asyncCallback = new MyAsyncCallback();
            final Timer timer = new Timer() {
                public void run() {
                    OrdineModel riepilogo = conoscoCO2View.getRiepilogo();
                    riepilogo.setEventiType(Eventi.CONOSCI_CO2.name());
                    getHustonService().isPagato(AzzerroCO2UtilsClientHelper.getDettaglioVTO(riepilogo), getUserInfoModel(), asyncCallback);
                    asyncCallback.setTimer(this);

                }
            };
            timer.schedule(10000);

        } else if (event.getType().equals(ConoscoCO2Events.Riepilogo)) {
            setCoefficientiConoscoCO2View();
        } else if (event.getType().equals(AzzeroCO2Events.Init)) {
            setCoefficienti();
            forwardToView(conoscoCO2View, event);
            if (getProgettiDiCompensazioneList().size() == 0) {
                setProgettiDiCompensazione();
            }
        } else if (event.getType().equals(ConoscoCO2Events.Acquisto)) {
            if (getProgettiDiCompensazioneList().size() == 0) {
                setProgettiDiCompensazione();
            }
            conoscoCO2View.setProgettiDiCompensazione(getProgettiDiCompensazioneList());
            conoscoCO2View.riepilogo(getCoefficientiMAP());

        } else if (event.getType().equals(ConoscoCO2Events.UseCoupon)) {
            String couponCode = event.getData();
            if (couponCode != null && !"".equalsIgnoreCase(couponCode)) {
                AsyncCallback<CouponModel> asyncCallback = new AsyncCallback<CouponModel>() {
                    public void onFailure(Throwable caught) {
                        MyInfo.show("Error", "Errore impossibile connettersi al server", 7000);
                    }

                    @Override
                    public void onSuccess(CouponModel result) {
                        if (result != null) {
                            conoscoCO2View.setCoupon(result);
                        } else {
                            MyInfo.show("Warn", "Coupon non Utilizzabile", 7000);
                        }
                    }
                };
                getHustonService().getValidCouponByCode(couponCode, asyncCallback);
            }

        } else if (event.getType().equals(ConoscoCO2Events.Conferma)) {
            if (getUserInfoModel().getProfilo() == Profile.Guest.ordinal()) {
                Dispatcher.forwardEvent(LoginEvents.ShowForm);
            } else {
                OrdineModel model = conoscoCO2View.getRiepilogo();
                model.setEventiType(Eventi.CONOSCI_CO2.name());
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
                        if (importo > 0.01) {// mettere 10 euro dopo la fase di test
                            Dispatcher.forwardEvent(PagamentoSellaEvents.ShowForm, model);
                        } else {
                            MyInfo.show("Non e' possibile comperare ordini inferiori ai 10 euro");
                        }
                    } else {
                        PagamentoModel p = model.getPagamentoModel();
                        p.setEsito(Esito.OMAGGIO.name());
                        model.setPagamentoModel(p);
                        save(model);
                        conoscoCO2View.showConferma(AzzerroCO2UtilsClientHelper.getDettaglioVTO(model));
                    }

                } else {
                    MyInfo.show("Seleziona il Progetto di compensazione");
                }
            }
        } else if (event.getType().equals(ConoscoCO2Events.CaricaProgettiDiCompensazione)) {
            if (getProgettiDiCompensazioneList().size() == 0) {
                setProgettiDiCompensazione();
            }
            conoscoCO2View.setProgettiDiCompensazione(getProgettiDiCompensazioneList());
        } else if (event.getType().equals(ConoscoCO2Events.CaricaCoefficienti)) {
            setCoefficienti();
        } else if (event.getType().equals(AzzeroCO2Events.LoggedIn)) {
            setUserInfoModel((UserInfoModel) event.getData());
            conoscoCO2View.setUserInfo(getUserInfoModel());
        } else if (event.getType().equals(ConoscoCO2Events.Save)) {
            if (event.getData() instanceof OrdineModel) {
                OrdineModel model = (OrdineModel) event.getData();
                model.setEventiType(Eventi.CONOSCI_CO2.name());
                save(model);
            } else {
                save(null);
            }
        } else {
            forwardToView(conoscoCO2View, event);
        }
    }

    private void save(OrdineModel model) {
        if (getUserInfoModel().getProfilo() == Profile.Guest.ordinal()) {
            Dispatcher.forwardEvent(LoginEvents.ShowForm);
        } else if (model == null) {
            OrdineModel riepilogo = conoscoCO2View.getRiepilogo();
            riepilogo.setEventiType(Eventi.CONOSCI_CO2.name());
            saveVTO(AzzerroCO2UtilsClientHelper.getDettaglioVTO(riepilogo));
        } else if (model != null) {

            model.setEventiType(Eventi.CONOSCI_CO2.name());
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
                    conoscoCO2View.setDettaglioModel(model);
                    openConfermaToAzzeroCO2_IT(model);
                    MyInfo.show("Evento " + riepilogo.getNome() + " salvato con successo.");
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

                    conoscoCO2View.showConferma(result);

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
                conoscoCO2View.setDettaglioModel(model);
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

    private void setCoefficientiConoscoCO2View() {
        if (getCoefficientiMAP() == null || getCoefficientiMAP().values().size() == 0) {
            setCoefficienti();
            if (getCoefficientiMAP() == null || getCoefficientiMAP().values().size() == 0) {
                MyInfo.show("Error", "Errore impossibile recuperare i coefficenti dal server 001", 7000);
            }
        }
        if (getCoefficientiMAP() == null || getCoefficientiMAP().values().size() == 0) {
            MyInfo.show("Error", "Errore impossibile recuperare i coefficenti dal server 002", 7000);
        } else {
            conoscoCO2View.riepilogo(getCoefficientiMAP());
        }
    }

    private double getTotaleKgCO2(OrdineModel model) {
        List<RiepilogoModel> eventoRiepilogoModels = conoscoCO2View.riepilogo(getCoefficientiMAP());
        double totale = 0;
        for (RiepilogoModel r : eventoRiepilogoModels) {
            totale += r.getKgCO2();
        }
        return totale;
    }


}
