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
import it.agilis.mens.azzeroCO2.client.components.conoscoCO2.ConoscoCO2;
import it.agilis.mens.azzeroCO2.client.components.conoscoCO2.ConoscoCO2North;
import it.agilis.mens.azzeroCO2.client.components.conoscoCO2.ConoscoCO2South;
import it.agilis.mens.azzeroCO2.client.components.conoscoCO2.ConoscoCO2West;
import it.agilis.mens.azzeroCO2.client.mvc.events.AzzeroCO2Events;
import it.agilis.mens.azzeroCO2.client.mvc.events.CentralEvents;
import it.agilis.mens.azzeroCO2.client.mvc.events.ConoscoCO2Events;
import it.agilis.mens.azzeroCO2.client.services.CalcoliHelper;
import it.agilis.mens.azzeroCO2.shared.Eventi;
import it.agilis.mens.azzeroCO2.shared.model.OrdineModel;
import it.agilis.mens.azzeroCO2.shared.model.RiepilogoModel;
import it.agilis.mens.azzeroCO2.shared.model.amministrazione.CoefficienteModel;
import it.agilis.mens.azzeroCO2.shared.model.amministrazione.ProgettoDiCompensazioneModel;
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
public class ConoscoCO2View extends View {
    private ContentPanel conoscoCO2panel = new ContentPanel();

    private ConoscoCO2 conoscoCO2 = new ConoscoCO2();
    private ContentPanel center = new ContentPanel();
    private ConoscoCO2South south = new ConoscoCO2South();
    private ConoscoCO2West west = new ConoscoCO2West();
    private ConoscoCO2North nord = new ConoscoCO2North();

    public ConoscoCO2View(Controller controller) {
        super(controller);
    }


    @Override
    protected void handleEvent(AppEvent event) {
        EventType eventType = event.getType();
        if (eventType.equals(AzzeroCO2Events.Init)) {
            onInit(event);
        } else if (eventType.equals(ConoscoCO2Events.Next)) {
            conoscoCO2.nextTab();
        } else if (eventType.equals(ConoscoCO2Events.Previous)) {
            conoscoCO2.previusTab();
        } else if (event.getType().equals(ConoscoCO2Events.PreviousText)) {
            OrdineModel riepilogo = conoscoCO2.riepilogo();
            south.setTextLeft(event.<String>getData(), getRiepilogo());
            setRiassunto(riepilogo,
                    event.<String>getData() != null && event.<String>getData().length() > 0 && event.<String>getData().equalsIgnoreCase("Manifesti pieghevoli e fogli"),
                    event.<String>getData() != null && event.<String>getData().length() > 0 && event.<String>getData().equalsIgnoreCase("Riepilogo"),
                    event.<String>getData() != null && event.<String>getData().length() == 0
            );
        } else if (event.getType().equals(ConoscoCO2Events.NextText)) {
            OrdineModel riepilogo = conoscoCO2.riepilogo();
            south.setTextRigth(event.<String>getData(), getRiepilogo());
            setRiassunto(riepilogo,
                    event.<String>getData() != null && event.<String>getData().length() > 0 && event.<String>getData().equalsIgnoreCase("Scegli progetto di compensazione"),
                    event.<String>getData() != null && event.<String>getData().length() > 0 && event.<String>getData().equalsIgnoreCase("Vai al pagamento"),
                    event.<String>getData() != null && event.<String>getData().length() > 0 && event.<String>getData().equalsIgnoreCase("torna alla home")
            );
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


    private void onInit(AppEvent event) {
        final BorderLayout layout = new BorderLayout();
        layout.setEnableState(false);
        conoscoCO2panel.setHeaderVisible(false);
        conoscoCO2panel.setLayout(layout);
        conoscoCO2panel.setStyleAttribute("padding", "1px");

        BorderLayoutData northData = new BorderLayoutData(Style.LayoutRegion.NORTH, 25);
        northData.setCollapsible(false);
        northData.setFloatable(false);
        northData.setHideCollapseTool(false);
        northData.setSplit(false);
        northData.setMargins(new Margins(0, 0, 0, 0));

        conoscoCO2panel.add(nord, northData);


        BorderLayoutData westData = new BorderLayoutData(Style.LayoutRegion.WEST, 250);
        westData.setCollapsible(false);
        westData.setFloatable(false);
        westData.setHideCollapseTool(false);
        westData.setSplit(false);
        westData.setMargins(new Margins(0, 0, 0, 0));


        conoscoCO2panel.add(west, westData);

        BorderLayoutData centerData = new BorderLayoutData(Style.LayoutRegion.CENTER);
        {
            final BorderLayout layout2 = new BorderLayout();
            center.setLayout(layout2);
            center.setStyleAttribute("padding", "1px");

            BorderLayoutData center2Data = new BorderLayoutData(Style.LayoutRegion.CENTER);
            center2Data.setMargins(new Margins(0, 0, 0, 0));
            center.add(conoscoCO2, center2Data);

            BorderLayoutData southData = new BorderLayoutData(Style.LayoutRegion.SOUTH, 33);
            southData.setMargins(new Margins(0, 0, 0, 0));
            center.add(south, southData);
        }
        center.setHeaderVisible(false);
        conoscoCO2panel.add(center, centerData);

        conoscoCO2panel.setTitle(Eventi.CONOSCI_CO2.name());
        Dispatcher.forwardEvent(new AppEvent(CentralEvents.ConosciCO2PanelReady,
                conoscoCO2panel));
    }

    public void setProgettiDiCompensazione(List<ProgettoDiCompensazioneModel> progettiDiCompensazioneList) {
        conoscoCO2.setProgettiDiCompensazione(progettiDiCompensazioneList);
    }

    public OrdineModel getRiepilogo() {
        return conoscoCO2.riepilogo();
    }


    public List<RiepilogoModel> riepilogo(Map<String, CoefficienteModel> coefficienti) {
        List<RiepilogoModel> list = CalcoliHelper.geListOfRiepilogoModel(conoscoCO2.riepilogo(), coefficienti, Eventi.CONOSCI_CO2);
        conoscoCO2.setConoscoCO2RiepilogoInStore(list);
        return list;
    }

    public void setUserInfo(UserInfoModel userInfoModel) {
        conoscoCO2.setUserInfoModel(userInfoModel);
    }


    public void setDettaglioModel(OrdineModel result) {
        conoscoCO2.restore(result);
    }

    public void showRiepilogo() {
        conoscoCO2.showRiepilogo();
    }

    public void showConferma(OrdineVTO result) {
        nord.hideButtons();
        conoscoCO2.showConferma(result);
    }

}
