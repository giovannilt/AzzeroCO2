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
import it.agilis.mens.azzeroCO2.client.components.sitoWeb.SitoWeb;
import it.agilis.mens.azzeroCO2.client.components.sitoWeb.SitoWebNorth;
import it.agilis.mens.azzeroCO2.client.components.sitoWeb.SitoWebSouth;
import it.agilis.mens.azzeroCO2.client.components.sitoWeb.SitoWebWest;
import it.agilis.mens.azzeroCO2.client.mvc.events.AzzeroCO2Events;
import it.agilis.mens.azzeroCO2.client.mvc.events.CentralEvents;
import it.agilis.mens.azzeroCO2.client.mvc.events.SitoWebEvents;
import it.agilis.mens.azzeroCO2.client.services.CalcoliHelper;
import it.agilis.mens.azzeroCO2.shared.Eventi;
import it.agilis.mens.azzeroCO2.shared.model.OrdineModel;
import it.agilis.mens.azzeroCO2.shared.model.RiepilogoModel;
import it.agilis.mens.azzeroCO2.shared.model.amministrazione.CoefficienteModel;
import it.agilis.mens.azzeroCO2.shared.model.amministrazione.CouponModel;
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
public class SitoWebView extends View {
    private ContentPanel sitoWebpanel = new ContentPanel();

    private SitoWeb sitoWeb = new SitoWeb();
    private ContentPanel center = new ContentPanel();
    private SitoWebSouth south = new SitoWebSouth();
    private SitoWebNorth nord = new SitoWebNorth();
    private SitoWebWest west = new SitoWebWest();

    public SitoWebView(Controller controller) {
        super(controller);
    }

    @Override
    protected void handleEvent(AppEvent event) {
        EventType eventType = event.getType();
        if (eventType.equals(AzzeroCO2Events.Init)) {
            onInit(event);
        } else if (eventType.equals(SitoWebEvents.GoToBegin)) {
            sitoWeb.goToBegin();
        } else if (eventType.equals(SitoWebEvents.Next)) {
            sitoWeb.nextTab();
        } else if (eventType.equals(SitoWebEvents.Previous)) {
            sitoWeb.previusTab();
        } else if (event.getType().equals(SitoWebEvents.PreviousText)) {
            OrdineModel riepilogo = sitoWeb.riepilogo();
            south.setTextLeft(event.<String>getData(), getRiepilogo());
            setRiassunto(riepilogo,
                    event.<String>getData() != null && event.<String>getData().length() > 0 && event.<String>getData().equalsIgnoreCase("Manifesti pieghevoli e fogli"),
                    event.<String>getData() != null && event.<String>getData().length() > 0 && event.<String>getData().equalsIgnoreCase("Riepilogo"),
                    event.<String>getData() != null && event.<String>getData().length() == 0
            );
        } else if (event.getType().equals(SitoWebEvents.NextText)) {
            OrdineModel riepilogo = sitoWeb.riepilogo();
            south.setTextRigth(event.<String>getData(), getRiepilogo());
            setRiassunto(riepilogo,
                    event.<String>getData() != null && event.<String>getData().length() > 0 && event.<String>getData().equalsIgnoreCase("Acquisto"),
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
        sitoWebpanel.setHeaderVisible(false);
        sitoWebpanel.setLayout(layout);
        sitoWebpanel.setStyleAttribute("padding", "1px");

        BorderLayoutData northData = new BorderLayoutData(Style.LayoutRegion.NORTH, 25);
        northData.setCollapsible(false);
        northData.setFloatable(false);
        northData.setHideCollapseTool(false);
        northData.setSplit(false);
        northData.setMargins(new Margins(0, 0, 0, 0));
        sitoWebpanel.add(nord, northData);


        BorderLayoutData westData = new BorderLayoutData(Style.LayoutRegion.WEST, 250);
        westData.setCollapsible(false);
        westData.setFloatable(false);
        westData.setHideCollapseTool(false);
        westData.setSplit(false);
        westData.setMargins(new Margins(0, 0, 0, 0));

        sitoWebpanel.add(west, westData);

        BorderLayoutData centerData = new BorderLayoutData(Style.LayoutRegion.CENTER);
        {
            final BorderLayout layout2 = new BorderLayout();
            center.setLayout(layout2);
            center.setStyleAttribute("padding", "1px");

            BorderLayoutData center2Data = new BorderLayoutData(Style.LayoutRegion.CENTER);
            center2Data.setMargins(new Margins(0, 0, 0, 0));
            center.add(sitoWeb, center2Data);

            BorderLayoutData southData = new BorderLayoutData(Style.LayoutRegion.SOUTH, 33);
            southData.setMargins(new Margins(0, 0, 0, 0));
            center.add(south, southData);
        }
        center.setHeaderVisible(false);
        sitoWebpanel.add(center, centerData);

        sitoWebpanel.setTitle(Eventi.WEB.name());
        Dispatcher.forwardEvent(new AppEvent(CentralEvents.SitoWebPanelReady,
                sitoWebpanel));
    }

    public void setProgettiDiCompensazione(List<ProgettoDiCompensazioneModel> progettiDiCompensazioneList) {
        sitoWeb.setProgettiDiCompensazione(progettiDiCompensazioneList);
    }

    public OrdineModel getRiepilogo() {
        return sitoWeb.riepilogo();
    }


    public List<RiepilogoModel> riepilogo(Map<String, CoefficienteModel> coefficienti) {
        List<RiepilogoModel> list = CalcoliHelper.geListOfRiepilogoModel(sitoWeb.riepilogo(), coefficienti, Eventi.CONOSCI_CO2);
        sitoWeb.setSitoWebRiepilogoInStore(list);
        return list;
    }

    public void setUserInfo(UserInfoModel userInfoModel) {
        sitoWeb.setUserInfoModel(userInfoModel);
    }


    public void setDettaglioModel(OrdineModel result) {
        sitoWeb.restore(result);
    }

    public void showRiepilogo() {
        sitoWeb.showRiepilogo();
    }

    public void showConferma(OrdineVTO result) {
        nord.hideButtons();
        sitoWeb.showConferma(result);
    }

    public void setCoupon(CouponModel result) {
        sitoWeb.setCouponModel(result);
    }
}
