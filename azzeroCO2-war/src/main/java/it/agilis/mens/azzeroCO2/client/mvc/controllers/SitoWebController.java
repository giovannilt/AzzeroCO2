package it.agilis.mens.azzeroCO2.client.mvc.controllers;

import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.widget.Info;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import it.agilis.mens.azzeroCO2.client.mvc.events.AzzeroCO2Events;
import it.agilis.mens.azzeroCO2.client.mvc.events.LoginEvents;
import it.agilis.mens.azzeroCO2.client.mvc.events.PagamentoSellaEvents;
import it.agilis.mens.azzeroCO2.client.mvc.events.SitoWebEvents;
import it.agilis.mens.azzeroCO2.client.mvc.views.SitoWebView;
import it.agilis.mens.azzeroCO2.client.services.AzzerroCO2UtilsClientHelper;
import it.agilis.mens.azzeroCO2.shared.Eventi;
import it.agilis.mens.azzeroCO2.shared.Profile;
import it.agilis.mens.azzeroCO2.shared.model.OrdineModel;
import it.agilis.mens.azzeroCO2.shared.model.RiepilogoModel;
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
        } else if (event.getType().equals(SitoWebEvents.Acquisto)) {
            if (getProgettiDiCompensazioneList().size() == 0) {
                setProgettiDiCompensazione();
            }
            sitoWebView.setProgettiDiCompensazione(getProgettiDiCompensazioneList());
        } else if (event.getType().equals(SitoWebEvents.Conferma)) {
            if (getUserInfoModel().getProfilo() == Profile.Guest.ordinal()) {
                Dispatcher.forwardEvent(LoginEvents.ShowForm);
            } else {
                OrdineModel model = sitoWebView.getRiepilogo();
                model.setEventiType(Eventi.WEB.name());
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
                Info.display("Error", "Errore impossibile connettersi al server " + caught);
            }

            @Override
            public void onSuccess(OrdineVTO result) {
                if (result != null) {
                    OrdineModel model = AzzerroCO2UtilsClientHelper.getDettaglioModel(result);
                    model.setNome("Compensazione SitoWeb");
                    sitoWebView.setDettaglioModel(model);
                    Info.display("Info", "Evento" + riepilogo.getNome() + " salvato con successo.");
                }
            }
        };
        getHustonService().saveOrdine(riepilogo, getUserInfoModel(), dettaglio);

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

                    sitoWebView.showConferma(result);

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
                sitoWebView.setDettaglioModel(model);
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

    private void setCoefficientiSitoWebView() {
        if (getCoefficientiMAP() == null || getCoefficientiMAP().values().size() == 0) {
            setCoefficienti();
            if (getCoefficientiMAP() == null || getCoefficientiMAP().values().size() == 0) {
                Info.display("Error", "Errore impossibile recuperare i coefficenti dal server 001");
            }
        }
        if (getCoefficientiMAP() == null || getCoefficientiMAP().values().size() == 0) {
            Info.display("Error", "Errore impossibile recuperare i coefficenti dal server 002");
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
