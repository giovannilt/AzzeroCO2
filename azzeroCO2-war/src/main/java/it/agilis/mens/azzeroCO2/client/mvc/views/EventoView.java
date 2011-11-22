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
import com.google.gwt.i18n.client.DateTimeFormat;
import it.agilis.mens.azzeroCO2.client.components.evento.EventoDettaglio;
import it.agilis.mens.azzeroCO2.client.components.evento.EventoNorth;
import it.agilis.mens.azzeroCO2.client.components.evento.EventoSouth;
import it.agilis.mens.azzeroCO2.client.components.evento.EventoWest;
import it.agilis.mens.azzeroCO2.client.components.evento.dialogs.EventoConfermDialog;
import it.agilis.mens.azzeroCO2.client.components.evento.dialogs.EventoInfoDialog;
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
import it.agilis.mens.azzeroCO2.shared.model.registrazione.UserInfoModel;

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
    private EventoInfoDialog eventoInfoDialog = new EventoInfoDialog();

    private EventoDettaglio eventoDettaglio = new EventoDettaglio();
    private ContentPanel center = new ContentPanel();
    private EventoSouth south = new EventoSouth();
    private EventoWest west = new EventoWest();
    private UserInfoModel userInfoModel;

    private DateTimeFormat dateFormat = DateTimeFormat.getFormat("dd.MM.y");

    public EventoView(Controller controller) {
        super(controller);
    }

    @Override
    protected void handleEvent(AppEvent event) {
        EventType eventType = event.getType();
        if (eventType.equals(AzzeroCO2Events.Init)) {
            onInit(event);
        } else if (eventType.equals(EventoEvents.Next)) {
            onNext(event);
        } else if (eventType.equals(EventoEvents.ClearStep)) {
            eventoDettaglio.clearStep((RiepilogoModel) event.getData());
        } else if (eventType.equals(EventoEvents.ClearPanel)) {
            eventoDettaglio.clearPanel();
            west.clean();
        } else if (eventType.equals(EventoEvents.Previous)) {
            onPrevius(event);
        } else if (eventType.equals(EventoEvents.Riepilogo)) {
            DettaglioModel riepilogo = eventoDettaglio.riepilogo();
            setRiassunto(riepilogo);
        } else if (event.getType().equals(EventoEvents.PreviousText)) {
            DettaglioModel riepilogo = eventoDettaglio.riepilogo();
            south.setTextLeft(event.<String>getData());
            setRiassunto(riepilogo);
        } else if (event.getType().equals(EventoEvents.NextText)) {
            DettaglioModel riepilogo = eventoDettaglio.riepilogo();
            south.setTextRigth(event.<String>getData());
            setRiassunto(riepilogo);
        } else if (event.getType().equals(EventoEvents.ShowStep)) {
            eventoDettaglio.showStep(event.<RiepilogoModel>getData());
        } else if (event.getType().equals(EventoEvents.ShowInfoDialog)) {
            eventoInfoDialog.show();
        } else if (event.getType().equals(EventoEvents.ShowConfermDialog)) {
            eventoConfermDialog.show();
        }
    }

    public void setRiassunto(DettaglioModel riepilogo) {
        west.setInStore(CalcoliHelper.getListOfRiepilogoModelLazy(riepilogo));
        if (riepilogo.getInizio() != null
                && riepilogo.getFine() != null) {
            String dal = " Dal " + dateFormat.format(riepilogo.getInizio());
            String a = " al " + dateFormat.format(riepilogo.getFine());
            west.setTitle(riepilogo.getNome() + dal + a);
        } else {
            west.setTitle(riepilogo.getNome());
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
        evento.setHeaderVisible(false);
        evento.setLayout(layout);
        evento.setStyleAttribute("padding", "1px");

        BorderLayoutData northData = new BorderLayoutData(Style.LayoutRegion.NORTH, 25);
        northData.setCollapsible(false);
        northData.setFloatable(false);
        northData.setHideCollapseTool(false);
        northData.setSplit(false);
        northData.setMargins(new Margins(0, 0, 0, 0));
        evento.add(new EventoNorth(), northData);


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
            center.setStyleAttribute("padding", "1px");

            BorderLayoutData center2Data = new BorderLayoutData(Style.LayoutRegion.CENTER);
            center2Data.setMargins(new Margins(0, 0, 0, 0));
            center.add(eventoDettaglio, center2Data);

            BorderLayoutData southData = new BorderLayoutData(Style.LayoutRegion.SOUTH, 33);
            southData.setMargins(new Margins(0, 0, 0, 0));
            center.setStyleAttribute("background-color", "#313646");
            center.add(south, southData);
        }
        center.setHeaderVisible(false);
        evento.add(center, centerData);

        evento.setTitle(Eventi.EVENTO.name());
        Dispatcher.forwardEvent(new AppEvent(CentralEvents.EventoPanelReady,
                evento));

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
        this.userInfoModel = userInfoModel;
        eventoDettaglio.setUserInfoModel(userInfoModel);
    }

    public void showRiepilogo() {
        eventoDettaglio.showRiepilogo();
    }
}
