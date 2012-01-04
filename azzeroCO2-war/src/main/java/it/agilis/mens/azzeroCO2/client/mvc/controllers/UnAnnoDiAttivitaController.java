package it.agilis.mens.azzeroCO2.client.mvc.controllers;

import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.widget.Info;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import it.agilis.mens.azzeroCO2.client.mvc.events.*;
import it.agilis.mens.azzeroCO2.client.mvc.views.UnAnnoDiAttivitaView;
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
        registerEventTypes(UnAnnoDiAttivitaEvents.LoadEvento);
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
    }

    @Override
    public void handleEvent(AppEvent event) {
        if (event.getType().equals(UnAnnoDiAttivitaEvents.InAttesaDiConfermaPagamento)) {
            final DettaglioVTO riepilogo = AzzerroCO2UtilsClientHelper.getDettaglioVTO(annoView.getRiepilogo());  //TODO da sistemare?

            final Timer timer = new Timer() {
                public void run() {
                    MyAsyncCallback asyncCallback = new MyAsyncCallback();
                    getHustonService().isPagato(riepilogo, getUserInfoModel(), asyncCallback);
                    asyncCallback.setTimer(this);
                }
            };
            timer.schedule(10000);
        } else if (event.getType().equals(UnAnnoDiAttivitaEvents.ShowRiepilogo)) {
            setCoefficentitoEventoView();
            annoView.showRiepilogo();
        } else if (event.getType().equals(UnAnnoDiAttivitaEvents.Riepilogo)) {
            setCoefficentitoEventoView();
        } else if (event.getType().equals(UnAnnoDiAttivitaEvents.LoadEvento)) {
            setCoefficienti();
            setProgettiDiCompensazione();
            annoView.setProgettiDiCompensazione(getProgettiDiCompensazioneList());
            annoView.setDettaglioModel((DettaglioModel) event.getData());
            annoView.setRiassunto((DettaglioModel) event.getData(), true, false, false);
        } else if (event.getType().equals(AzzeroCO2Events.Init)) {
            AsyncCallback<List<TipoDiCartaModel>> tipoDiCartaCallBack = new AsyncCallback<List<TipoDiCartaModel>>() {
                public void onFailure(Throwable caught) {
                    Info.display("Error", "Errore impossibile connettersi al server");
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
        } else if (event.getType().equals(EventoEvents.Acquisto)) {
            if (getUserInfoModel().getProfilo() == Profile.Guest.ordinal()) {
                Dispatcher.forwardEvent(LoginEvents.ShowForm);
            } else {
                if (getProgettiDiCompensazioneList().size() == 0) {
                    setProgettiDiCompensazione();
                }
                annoView.setProgettiDiCompensazione(getProgettiDiCompensazioneList());
            }
        } else if (event.getType().equals(EventoEvents.Conferma)) {
            DettaglioModel model = annoView.getRiepilogo();

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
            annoView.setProgettiDiCompensazione(getProgettiDiCompensazioneList());
        } else if (event.getType().equals(EventoEvents.CaricaCoefficienti)) {
            setCoefficienti();
        } else if (event.getType().equals(AzzeroCO2Events.LoggedIn)) {
            setUserInfoModel((UserInfoModel) event.getData());
            annoView.setUserInfo(getUserInfoModel());
        } else if (event.getType().equals(EventoEvents.Save)) {
            if (event.getData() instanceof DettaglioModel) {
                DettaglioModel model = (DettaglioModel) event.getData();
                save(model);
            } else {
                save(null);
            }
        } else {
            forwardToView(annoView, event);
        }
    }

    private void save(DettaglioModel model) {
        if (getUserInfoModel().getProfilo() == Profile.Guest.ordinal()) {
            Dispatcher.forwardEvent(LoginEvents.ShowForm);
        } else if (model == null) {
            saveVTO(AzzerroCO2UtilsClientHelper.getDettaglioVTO(annoView.getRiepilogo()));
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
                        annoView.setDettaglioModel(model);
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
            annoView.riepilogo(getCoefficientiMAP());
        }
    }

    private double getTotaleKgCO2(DettaglioModel model) {
        List<RiepilogoModel> eventoRiepilogoModels = annoView.riepilogo(getCoefficientiMAP());
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


                annoView.showConferma(result);
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
