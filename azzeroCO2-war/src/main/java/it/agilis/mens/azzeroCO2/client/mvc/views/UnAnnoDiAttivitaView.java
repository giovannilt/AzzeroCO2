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
import it.agilis.mens.azzeroCO2.client.components.annoAttivita.AnnoDettaglio;
import it.agilis.mens.azzeroCO2.client.components.annoAttivita.AnnoNorth;
import it.agilis.mens.azzeroCO2.client.components.annoAttivita.AnnoSouth;
import it.agilis.mens.azzeroCO2.client.components.annoAttivita.AnnoWest;
import it.agilis.mens.azzeroCO2.client.components.evento.dialogs.EventoConfermDialog;
import it.agilis.mens.azzeroCO2.client.mvc.events.AzzeroCO2Events;
import it.agilis.mens.azzeroCO2.client.mvc.events.CentralEvents;
import it.agilis.mens.azzeroCO2.client.mvc.events.UnAnnoDiAttivitaEvents;
import it.agilis.mens.azzeroCO2.client.services.CalcoliHelper;
import it.agilis.mens.azzeroCO2.shared.Eventi;
import it.agilis.mens.azzeroCO2.shared.model.OrdineModel;
import it.agilis.mens.azzeroCO2.shared.model.RiepilogoModel;
import it.agilis.mens.azzeroCO2.shared.model.amministrazione.CoefficienteModel;
import it.agilis.mens.azzeroCO2.shared.model.amministrazione.ProgettoDiCompensazioneModel;
import it.agilis.mens.azzeroCO2.shared.model.evento.TipoDiCartaModel;
import it.agilis.mens.azzeroCO2.shared.model.pagamento.Esito;
import it.agilis.mens.azzeroCO2.shared.model.registrazione.UserInfoModel;
import it.agilis.mens.azzeroCO2.shared.vto.OrdineVTO;

import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 7/2/11
 * Time: 11:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class UnAnnoDiAttivitaView extends View {
    private ContentPanel unAnnoDiAttivita = new ContentPanel();
    private EventoConfermDialog eventoConfermDialog = new EventoConfermDialog();

    private AnnoDettaglio annoDettaglio = new AnnoDettaglio();
    private ContentPanel center = new ContentPanel();
    private AnnoSouth south = new AnnoSouth();
    private AnnoWest west = new AnnoWest();
    private AnnoNorth north = new AnnoNorth();


    public UnAnnoDiAttivitaView(Controller controller) {
        super(controller);
    }

    @Override
    protected void handleEvent(AppEvent event) {
        EventType eventType = event.getType();
        if (eventType.equals(AzzeroCO2Events.Init)) {
            onInit();
        } else if (eventType.equals(UnAnnoDiAttivitaEvents.RemoveModel)) {
            annoDettaglio.formRiepilogo.removeModel((RiepilogoModel) event.getData());
        } else if (eventType.equals(UnAnnoDiAttivitaEvents.GoToBegin)) {
            annoDettaglio.goToBegin();
        } else if (eventType.equals(UnAnnoDiAttivitaEvents.Next)) {
            onNext();
        } else if (eventType.equals(UnAnnoDiAttivitaEvents.NorthPanelShowButtons)) {
            north.showButtons();
        } else if (eventType.equals(UnAnnoDiAttivitaEvents.ClearStep)) {
            annoDettaglio.clearStep((RiepilogoModel) event.getData());
            OrdineModel riepilogo = annoDettaglio.riepilogo();
            setRiassunto(riepilogo, false, false, false);
        } else if (eventType.equals(UnAnnoDiAttivitaEvents.ClearPanel)) {
            annoDettaglio.clearPanel();
            west.clean();
            south.setTextRigth("Energia", null);
        } else if (eventType.equals(UnAnnoDiAttivitaEvents.Previous)) {
            onPrevius();
        } else if (event.getType().equals(UnAnnoDiAttivitaEvents.PreviousText)) {
            OrdineModel riepilogo = annoDettaglio.riepilogo();
            south.setTextLeft(event.<String>getData(), getRiepilogo());
            setRiassunto(riepilogo,
                    event.<String>getData() != null && event.<String>getData().length() > 0 && event.<String>getData().equalsIgnoreCase("Sito"),
                    event.<String>getData() != null && event.<String>getData().length() > 0 && event.<String>getData().equalsIgnoreCase("Riepilogo"),
                    event.<String>getData() != null && event.<String>getData().length() == 0
            );
        } else if (event.getType().equals(UnAnnoDiAttivitaEvents.NextText)) {
            OrdineModel riepilogo = annoDettaglio.riepilogo();
            south.setTextRigth(event.<String>getData(), getRiepilogo());
            setRiassunto(riepilogo,
                    event.<String>getData() != null && event.<String>getData().length() > 0 && event.<String>getData().equalsIgnoreCase("Acquisto"),
                    event.<String>getData() != null && event.<String>getData().length() > 0 && event.<String>getData().equalsIgnoreCase("Vai al pagamento"),
                    event.<String>getData() != null && event.<String>getData().length() > 0 && event.<String>getData().equalsIgnoreCase("torna alla home")
            );
        } else if (event.getType().equals(UnAnnoDiAttivitaEvents.ShowStep)) {
            annoDettaglio.showStep(event.<RiepilogoModel>getData());
        } else if (event.getType().equals(UnAnnoDiAttivitaEvents.ShowConfermDialog)) {
            eventoConfermDialog.show();
        }
    }

    public void setRiassunto(OrdineModel riepilogo, boolean isRiepilogo, boolean isScegliProgettoCompensazione, boolean isConferma) {
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

    private void onPrevius() {
        annoDettaglio.previusTab();
    }

    private void onNext() {
        annoDettaglio.nextTab();
    }

    private void onInit() {
        final BorderLayout layout = new BorderLayout();
        layout.setEnableState(false);
        unAnnoDiAttivita.setHeaderVisible(false);
        unAnnoDiAttivita.setLayout(layout);

        BorderLayoutData northData = new BorderLayoutData(Style.LayoutRegion.NORTH, 25);
        northData.setCollapsible(false);
        northData.setFloatable(false);
        northData.setHideCollapseTool(false);
        northData.setSplit(false);
        northData.setMargins(new Margins(0, 0, 0, 0));

        unAnnoDiAttivita.add(north, northData);

        BorderLayoutData westData = new BorderLayoutData(Style.LayoutRegion.WEST, 250);
        westData.setCollapsible(false);
        westData.setFloatable(false);
        westData.setHideCollapseTool(false);
        westData.setSplit(false);
        westData.setMargins(new Margins(0, 0, 0, 0));

        unAnnoDiAttivita.add(west, westData);

        BorderLayoutData centerData = new BorderLayoutData(Style.LayoutRegion.CENTER);
        {
            final BorderLayout layout2 = new BorderLayout();
            center.setLayout(layout2);

            BorderLayoutData center2Data = new BorderLayoutData(Style.LayoutRegion.CENTER);
            center2Data.setMargins(new Margins(0, 0, 0, 0));
            center.add(annoDettaglio, center2Data);

            BorderLayoutData southData = new BorderLayoutData(Style.LayoutRegion.SOUTH, 33);
            southData.setMargins(new Margins(0, 0, 0, 0));
            center.setStyleAttribute("background-color", "#313646");
            center.add(south, southData);
        }
        center.setHeaderVisible(false);
        unAnnoDiAttivita.add(center, centerData);

        unAnnoDiAttivita.setTitle(Eventi.ANNO_DI_ATTIVITA.name());
        Dispatcher.forwardEvent(new AppEvent(CentralEvents.UnAnnoDiAttivitaPanelReady, unAnnoDiAttivita));
    }

    public void setTipoDiCarta(List<TipoDiCartaModel> tipoDiCartaModels) {
        annoDettaglio.setTipoDiCarta(tipoDiCartaModels);
    }

    public List<RiepilogoModel> riepilogo(Map<String, CoefficienteModel> coefficienti) {
        List<RiepilogoModel> list = CalcoliHelper.geListOfRiepilogoModel(annoDettaglio.riepilogo(), coefficienti, Eventi.ANNO_DI_ATTIVITA);
        annoDettaglio.setUnaAnnoDiAttivitaRiepilogoInStore(list);
        return list;
    }

    public void setProgettiDiCompensazione(List<ProgettoDiCompensazioneModel> progettiDiCompensazioneList) {
        annoDettaglio.setProgettiDiCompensazione(progettiDiCompensazioneList);
    }

    public OrdineModel getRiepilogo() {
        return annoDettaglio.riepilogo();
    }

    public void setDettaglioModel(OrdineModel result) {
        annoDettaglio.restore(result);
    }

    public void setUserInfo(UserInfoModel userInfoModel) {
        annoDettaglio.setUserInfoModel(userInfoModel);
    }

    public void showRiepilogo() {
        annoDettaglio.showRiepilogo();
    }

    public void showConferma(OrdineVTO result) {
        north.hideButtons();
        annoDettaglio.showConferma(result);
    }

}
