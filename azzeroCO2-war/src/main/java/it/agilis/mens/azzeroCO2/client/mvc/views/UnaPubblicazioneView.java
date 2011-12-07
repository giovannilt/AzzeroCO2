package it.agilis.mens.azzeroCO2.client.mvc.views;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.mvc.View;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import it.agilis.mens.azzeroCO2.client.components.evento.EventoDettaglio;
import it.agilis.mens.azzeroCO2.client.components.evento.dialogs.EventoConfermDialog;
import it.agilis.mens.azzeroCO2.client.components.evento.dialogs.EventoInfoDialog;
import it.agilis.mens.azzeroCO2.client.components.pubblicazione.PubblicazioneNorth;
import it.agilis.mens.azzeroCO2.client.components.pubblicazione.PubblicazioneSouth;
import it.agilis.mens.azzeroCO2.client.components.pubblicazione.PubblicazioneWest;
import it.agilis.mens.azzeroCO2.client.mvc.events.AzzeroCO2Events;
import it.agilis.mens.azzeroCO2.client.mvc.events.CentralEvents;
import it.agilis.mens.azzeroCO2.client.mvc.events.PubblicazioniEvents;
import it.agilis.mens.azzeroCO2.client.services.CalcoliHelper;
import it.agilis.mens.azzeroCO2.shared.Eventi;
import it.agilis.mens.azzeroCO2.shared.model.RiepilogoModel;
import it.agilis.mens.azzeroCO2.shared.model.amministrazione.CoefficienteModel;
import it.agilis.mens.azzeroCO2.shared.model.amministrazione.ProgettoDiCompensazioneModel;
import it.agilis.mens.azzeroCO2.shared.model.evento.DettaglioModel;
import it.agilis.mens.azzeroCO2.shared.model.evento.TipoDiCartaModel;
import it.agilis.mens.azzeroCO2.shared.model.pagamento.Esito;
import it.agilis.mens.azzeroCO2.shared.model.registrazione.UserInfoModel;
import it.agilis.mens.azzeroCO2.shared.vto.DettaglioVTO;

import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: serenadimaida
 * Date: 07/12/11
 * Time: 22:30
 * To change this template use File | Settings | File Templates.
 */
public class UnaPubblicazioneView extends View {

    private ContentPanel pubblicazione = new ContentPanel();
    private EventoConfermDialog eventoConfermDialog = new EventoConfermDialog();
    private EventoInfoDialog eventoInfoDialog = new EventoInfoDialog();

    private EventoDettaglio eventoDettaglio = new EventoDettaglio();
    private ContentPanel center = new ContentPanel();
    private PubblicazioneSouth south = new PubblicazioneSouth();
    private PubblicazioneWest west = new PubblicazioneWest();
    private PubblicazioneNorth north = new PubblicazioneNorth();


    public UnaPubblicazioneView(Controller controller) {
        super(controller);
    }

    @Override
    protected void handleEvent(AppEvent event) {
        EventType eventType = event.getType();
        if (eventType.equals(AzzeroCO2Events.Init)) {
            onInit(event);
        } else if (eventType.equals(PubblicazioniEvents.Next)) {
            onNext(event);
        } else if (eventType.equals(PubblicazioniEvents.ClearStep)) {
            eventoDettaglio.clearStep((RiepilogoModel) event.getData());
            DettaglioModel riepilogo = eventoDettaglio.riepilogo();
            setRiassunto(riepilogo, false, false, false);
        } else if (eventType.equals(PubblicazioniEvents.ClearPanel)) {
            eventoDettaglio.clearPanel();
            west.clean();
        } else if (eventType.equals(PubblicazioniEvents.Previous)) {
            onPrevius(event);
        } else if (event.getType().equals(PubblicazioniEvents.PreviousText)) {
            DettaglioModel riepilogo = eventoDettaglio.riepilogo();
            south.setTextLeft(event.<String>getData(), getRiepilogo());
            setRiassunto(riepilogo,
                    event.<String>getData() != null && event.<String>getData().length() > 0 && event.<String>getData().equalsIgnoreCase("Manifesti piegevoli e fogli"),
                    event.<String>getData() != null && event.<String>getData().length() > 0 && event.<String>getData().equalsIgnoreCase("Riepilogo"),
                    false
            );
        } else if (event.getType().equals(PubblicazioniEvents.NextText)) {
            DettaglioModel riepilogo = eventoDettaglio.riepilogo();
            south.setTextRigth(event.<String>getData(), getRiepilogo());
            setRiassunto(riepilogo,
                    event.<String>getData() != null && event.<String>getData().length() > 0 && event.<String>getData().equalsIgnoreCase("Scegli progetto di compensazione"),
                    event.<String>getData() != null && event.<String>getData().length() > 0 && event.<String>getData().equalsIgnoreCase("Vai al pagamento"),
                    event.<String>getData() != null && event.<String>getData().length() > 0 && event.<String>getData().equalsIgnoreCase("torna alla home")
            );
        } else if (event.getType().equals(PubblicazioniEvents.ShowStep)) {
            eventoDettaglio.showStep(event.<RiepilogoModel>getData());
        } else if (event.getType().equals(PubblicazioniEvents.ShowInfoDialog)) {
            eventoInfoDialog.show();
        } else if (event.getType().equals(PubblicazioniEvents.ShowConfermDialog)) {
            eventoConfermDialog.show();
        }
    }

    public void setRiassunto(DettaglioModel riepilogo, boolean isRiepilogo, boolean isScegliProgettoCompensazione, boolean isConferma) {
        if (isRiepilogo) {
            west.isInRiepilogo(riepilogo);
        } else if (isScegliProgettoCompensazione) {
            west.isScegliProgettoCompensazione(riepilogo);
        } else if (isConferma) {
            west.isInConferma(riepilogo);
        } else {
            if (riepilogo.getPagamentoModel() != null &&
                    riepilogo.getPagamentoModel().getEsito() != null) {
                west.setInStore(riepilogo, Esito.valueOf(riepilogo.getPagamentoModel().getEsito()));
            } else {
                west.setInStore(riepilogo, Esito.IN_PAGAMENTO);
            }
        }

    }


    private void onPrevius(AppEvent event) {
        eventoDettaglio.previusTab();
    }

    private void onNext(AppEvent event) {
        eventoDettaglio.nextTab();
    }

    private void onInit(AppEvent event) {
        final BorderLayout layout = new BorderLayout();
        layout.setEnableState(false);
        pubblicazione.setHeaderVisible(false);
        pubblicazione.setLayout(layout);

        BorderLayoutData northData = new BorderLayoutData(Style.LayoutRegion.NORTH, 25);
        northData.setCollapsible(false);
        northData.setFloatable(false);
        northData.setHideCollapseTool(false);
        northData.setSplit(false);
        northData.setMargins(new Margins(0, 0, 0, 0));

        pubblicazione.add(north, northData);

        BorderLayoutData westData = new BorderLayoutData(Style.LayoutRegion.WEST, 250);
        westData.setCollapsible(false);
        westData.setFloatable(false);
        westData.setHideCollapseTool(false);
        westData.setSplit(false);
        westData.setMargins(new Margins(0, 0, 0, 0));

        pubblicazione.add(west, westData);

        BorderLayoutData centerData = new BorderLayoutData(Style.LayoutRegion.CENTER);
        {
            final BorderLayout layout2 = new BorderLayout();
            center.setLayout(layout2);

            BorderLayoutData center2Data = new BorderLayoutData(Style.LayoutRegion.CENTER);
            center2Data.setMargins(new Margins(0, 0, 0, 0));
            center.add(eventoDettaglio, center2Data);

            BorderLayoutData southData = new BorderLayoutData(Style.LayoutRegion.SOUTH, 33);
            southData.setMargins(new Margins(0, 0, 0, 0));
            center.setStyleAttribute("background-color", "#313646");
            center.add(south, southData);
        }
        center.setHeaderVisible(false);
        pubblicazione.add(center, centerData);

        pubblicazione.setTitle(Eventi.UNA_PUBBLICAZIONE.name());
        Dispatcher.forwardEvent(new AppEvent(CentralEvents.EventoPanelReady, pubblicazione));
    }

    public void setTipoDiCarta(List<TipoDiCartaModel> tipoDiCartaModels) {
        eventoDettaglio.setTipoDiCarta(tipoDiCartaModels);
    }

    public List<RiepilogoModel> riepilogo(Map<String, CoefficienteModel> coefficienti) {
        List<RiepilogoModel> list = CalcoliHelper.geListOfRiepilogoModel(eventoDettaglio.riepilogo(), coefficienti);
        eventoDettaglio.setEventoRiepilogoInStore(list);
        return list;
    }

    public void setProgettiDiCompensazione(List<ProgettoDiCompensazioneModel> progettiDiCompensazioneList) {
        eventoDettaglio.setProgettiDiCompensazione(progettiDiCompensazioneList);
    }

    public DettaglioModel getRiepilogo() {
        return eventoDettaglio.riepilogo();
    }

    public void setDettaglioModel(DettaglioModel result) {
        eventoDettaglio.restore(result);
    }

    public void setUserInfo(UserInfoModel userInfoModel) {
        eventoDettaglio.setUserInfoModel(userInfoModel);
    }

    public void showRiepilogo() {
        eventoDettaglio.showRiepilogo();
    }

    public void showConferma(DettaglioVTO result) {
        eventoDettaglio.showConferma(result);
    }

}