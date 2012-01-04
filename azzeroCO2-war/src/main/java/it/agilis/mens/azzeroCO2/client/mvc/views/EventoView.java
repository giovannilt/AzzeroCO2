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
import it.agilis.mens.azzeroCO2.client.components.evento.EventoNorth;
import it.agilis.mens.azzeroCO2.client.components.evento.EventoSouth;
import it.agilis.mens.azzeroCO2.client.components.evento.EventoWest;
import it.agilis.mens.azzeroCO2.client.components.evento.dialogs.EventoConfermDialog;
import it.agilis.mens.azzeroCO2.client.mvc.events.AzzeroCO2Events;
import it.agilis.mens.azzeroCO2.client.mvc.events.CentralEvents;
import it.agilis.mens.azzeroCO2.client.mvc.events.EventoEvents;
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
 * User: giovannilt
 * Date: 3/23/11
 * Time: 10:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class EventoView extends View {
    private ContentPanel evento = new ContentPanel();
    private EventoConfermDialog eventoConfermDialog = new EventoConfermDialog();

    private EventoDettaglio eventoDettaglio = new EventoDettaglio();
    private ContentPanel center = new ContentPanel();
    private EventoSouth south = new EventoSouth();
    private EventoWest west = new EventoWest();
    private EventoNorth north = new EventoNorth();


    public EventoView(Controller controller) {
        super(controller);
    }

    @Override
    protected void handleEvent(AppEvent event) {
        EventType eventType = event.getType();
        if (eventType.equals(AzzeroCO2Events.Init)) {
            onInit(event);
        } else if (eventType.equals(EventoEvents.RemoveModel)) {
            eventoDettaglio.formRiepilogo.removeModel((RiepilogoModel) event.getData());
        } else if (eventType.equals(EventoEvents.GoToBegin)) {
            eventoDettaglio.goToBegin();
        } else if (eventType.equals(EventoEvents.Next)) {
            onNext(event);
        } else if (eventType.equals(EventoEvents.NorthPanelShowButtons)) {
            north.showButtons();
        } else if (eventType.equals(EventoEvents.ClearStep)) {
            eventoDettaglio.clearStep((RiepilogoModel) event.getData());
            DettaglioModel riepilogo = eventoDettaglio.riepilogo();
            setRiassunto(riepilogo, false, false, false);
        } else if (eventType.equals(EventoEvents.ClearPanel)) {
            eventoDettaglio.clearPanel();
            west.clean();
            south.setTextRigth("Energia", null);
        } else if (eventType.equals(EventoEvents.Previous)) {
            onPrevius(event);
        } else if (event.getType().equals(EventoEvents.PreviousText)) {
            DettaglioModel riepilogo = eventoDettaglio.riepilogo();
            south.setTextLeft(event.<String>getData(), getRiepilogo());
            setRiassunto(riepilogo,
                    event.<String>getData() != null && event.<String>getData().length() > 0 && event.<String>getData().equalsIgnoreCase("Manifesti pieghevoli e fogli"),
                    event.<String>getData() != null && event.<String>getData().length() > 0 && event.<String>getData().equalsIgnoreCase("Riepilogo"),
                    event.<String>getData() != null && event.<String>getData().length() == 0
            );
        } else if (event.getType().equals(EventoEvents.NextText)) {
            DettaglioModel riepilogo = eventoDettaglio.riepilogo();
            south.setTextRigth(event.<String>getData(), getRiepilogo());
            setRiassunto(riepilogo,
                    event.<String>getData() != null && event.<String>getData().length() > 0 && event.<String>getData().equalsIgnoreCase("Scegli progetto di compensazione"),
                    event.<String>getData() != null && event.<String>getData().length() > 0 && event.<String>getData().equalsIgnoreCase("Vai al pagamento"),
                    event.<String>getData() != null && event.<String>getData().length() > 0 && event.<String>getData().equalsIgnoreCase("torna alla home")
            );
        } else if (event.getType().equals(EventoEvents.ShowStep)) {
            eventoDettaglio.showStep(event.<RiepilogoModel>getData());
        } else if (event.getType().equals(EventoEvents.ShowConfermDialog)) {
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

    public void goToBegin() {
        eventoDettaglio.goToBegin();
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
        evento.setHeaderVisible(false);
        evento.setLayout(layout);

        BorderLayoutData northData = new BorderLayoutData(Style.LayoutRegion.NORTH, 25);
        northData.setCollapsible(false);
        northData.setFloatable(false);
        northData.setHideCollapseTool(false);
        northData.setSplit(false);
        northData.setMargins(new Margins(0, 0, 0, 0));

        evento.add(north, northData);

        BorderLayoutData westData = new BorderLayoutData(Style.LayoutRegion.WEST, 250);
        westData.setCollapsible(false);
        westData.setFloatable(false);
        westData.setHideCollapseTool(false);
        westData.setSplit(false);
        westData.setMargins(new Margins(0, 0, 0, 0));

        evento.add(west, westData);

        BorderLayoutData centerData = new BorderLayoutData(Style.LayoutRegion.CENTER);
        {
            final BorderLayout layout2 = new BorderLayout();
            center.setLayout(layout2);

            BorderLayoutData center2Data = new BorderLayoutData(Style.LayoutRegion.CENTER);
            center2Data.setMargins(new Margins(0, 0, 0, 0));
            center.add(eventoDettaglio, center2Data);

            BorderLayoutData southData = new BorderLayoutData(Style.LayoutRegion.SOUTH, 36);
            southData.setMargins(new Margins(0, 0, 0, 0));
            center.setStyleAttribute("background-color", "#313646");
            center.add(south, southData);
        }
        center.setHeaderVisible(false);
        evento.add(center, centerData);

        evento.setTitle(Eventi.EVENTO.name());
        Dispatcher.forwardEvent(new AppEvent(CentralEvents.EventoPanelReady, evento));
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
        north.hideButtons();
        eventoDettaglio.showConferma(result);
    }

}
