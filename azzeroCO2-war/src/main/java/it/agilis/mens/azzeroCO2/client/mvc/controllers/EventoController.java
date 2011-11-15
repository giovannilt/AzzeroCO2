package it.agilis.mens.azzeroCO2.client.mvc.controllers;

import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.widget.Info;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import it.agilis.mens.azzeroCO2.client.mvc.events.AzzeroCO2Events;
import it.agilis.mens.azzeroCO2.client.mvc.events.EventoEvents;
import it.agilis.mens.azzeroCO2.client.mvc.events.LoginEvents;
import it.agilis.mens.azzeroCO2.client.mvc.events.PagamentoSellaEvents;
import it.agilis.mens.azzeroCO2.client.mvc.views.EventoView;
import it.agilis.mens.azzeroCO2.client.services.AzzerroCO2UtilsClientHelper;
import it.agilis.mens.azzeroCO2.shared.EMailVTO;
import it.agilis.mens.azzeroCO2.shared.Profile;
import it.agilis.mens.azzeroCO2.shared.model.RiepilogoModel;
import it.agilis.mens.azzeroCO2.shared.model.amministrazione.ProgettoDiCompensazioneModel;
import it.agilis.mens.azzeroCO2.shared.model.evento.DettaglioModel;
import it.agilis.mens.azzeroCO2.shared.model.evento.TipoDiCartaModel;
import it.agilis.mens.azzeroCO2.shared.model.pagamento.PagamentoModel;
import it.agilis.mens.azzeroCO2.shared.model.registrazione.UserInfoModel;
import it.agilis.mens.azzeroCO2.shared.vto.DettaglioVTO;

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

        registerEventTypes(EventoEvents.SentEmailConferma);
        registerEventTypes(EventoEvents.ShowConfermDialog);
        registerEventTypes(EventoEvents.ShowInfoDialog);
        registerEventTypes(EventoEvents.ShowRiepilogo);
    }

    @Override
    public void handleEvent(AppEvent event) {
        if (event.getType().equals(EventoEvents.ShowRiepilogo)) {
            eventoView.showRiepilogo();
        }else if (event.getType().equals(EventoEvents.LoadEvento)) {
            setCoefficienti();
            setProgettiDiCompensazione();
            eventoView.setProgettiDiCompensazione(getProgettiDiCompensazioneList());
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
            //save();
            DettaglioModel model = eventoView.getRiepilogo();
            model.setPagamentoModel(new PagamentoModel(getTotaleDaPagare(model)));

            save();

            Dispatcher.forwardEvent(PagamentoSellaEvents.ShowForm, model);

        } else if (event.getType().equals(EventoEvents.CaricaProgettiDiCompensazione)) {
            if (getProgettiDiCompensazioneList().size() == 0) {
                setProgettiDiCompensazione();
            }
            eventoView.setProgettiDiCompensazione(getProgettiDiCompensazioneList());

        } else if (event.getType().equals(EventoEvents.CaricaCoefficienti)) {
            setCoefficienti();
        } else if (event.getType().equals(EventoEvents.SentEmailConferma)) {
            sentMail((EMailVTO) event.getData());
        } else if (event.getType().equals(AzzeroCO2Events.LoggedIn)) {
            setUserInfoModel((UserInfoModel) event.getData());
            eventoView.setUserInfo(getUserInfoModel());
        } else if (event.getType().equals(EventoEvents.Save)) {
            save();
        } else {
            forwardToView(eventoView, event);
        }
    }

    private void save() {
        if (getUserInfoModel().getProfilo() == Profile.Guest.ordinal()) {
            Dispatcher.forwardEvent(LoginEvents.ShowForm);
        } else {
            final DettaglioVTO riepilogo = AzzerroCO2UtilsClientHelper.getDettaglioVTO(eventoView.getRiepilogo());
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
                getHustonService().saveOrdine(riepilogo, dettaglio);
            }
        }
    }

    private String getTotaleDaPagare(DettaglioModel model ) {
        List<RiepilogoModel> eventoRiepilogoModels = eventoView.riepilogo(getCoefficientiMAP());

        double totale = 0;
        for (RiepilogoModel r : eventoRiepilogoModels) {
            totale += r.getKgCO2();
        }
        ProgettoDiCompensazioneModel p= model.getProgettoDiCompensazioneModel();
        totale= totale * (p!=null ? p.getPrezzo() : 0);

        //TODO SUI CUPON
        // model.getCouponModel();

        return number.format(totale);
    }

}
