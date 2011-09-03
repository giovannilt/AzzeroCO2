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
import it.agilis.mens.azzeroCO2.client.components.amministrazione.Amministrazione;
import it.agilis.mens.azzeroCO2.client.components.amministrazione.AmministrazioneNorth;
import it.agilis.mens.azzeroCO2.client.mvc.events.AzzeroCO2Events;
import it.agilis.mens.azzeroCO2.client.mvc.events.CentralEvents;
import it.agilis.mens.azzeroCO2.shared.Eventi;
import it.agilis.mens.azzeroCO2.shared.model.amministrazione.CoefficienteModel;
import it.agilis.mens.azzeroCO2.shared.model.amministrazione.CouponModel;
import it.agilis.mens.azzeroCO2.shared.model.amministrazione.OrdineModel;
import it.agilis.mens.azzeroCO2.shared.model.amministrazione.ProgettoDiCompensazioneModel;
import it.agilis.mens.azzeroCO2.shared.model.registrazione.UserInfoModel;

import java.util.List;
import java.util.Map;


/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 7/2/11
 * Time: 11:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class AmministrazioneView extends View {
    private ContentPanel amministrazionePanel = new ContentPanel();
    private Amministrazione amministrazione = new Amministrazione();

    public AmministrazioneView(Controller controller) {
        super(controller);
    }

    @Override
    protected void handleEvent(AppEvent event) {
        EventType eventType = event.getType();
        if (eventType.equals(AzzeroCO2Events.Init)) {
            onInit(event);
        }
    }


    private void onInit(AppEvent event) {
        final BorderLayout layout = new BorderLayout();
        layout.setEnableState(false);
        amministrazionePanel.setHeaderVisible(false);
        amministrazionePanel.setLayout(layout);

        BorderLayoutData northData = new BorderLayoutData(Style.LayoutRegion.NORTH, 25);
        northData.setCollapsible(false);
        northData.setFloatable(false);
        northData.setHideCollapseTool(false);
        northData.setSplit(false);
        northData.setMargins(new Margins(0, 0, 0, 0));
        amministrazionePanel.add(new AmministrazioneNorth(), northData);

        BorderLayoutData centerData = new BorderLayoutData(Style.LayoutRegion.CENTER);
        amministrazionePanel.add(amministrazione, centerData);

        amministrazionePanel.setTitle(Eventi.AMMINISTRAZIONE.name());
        Dispatcher.forwardEvent(new AppEvent(CentralEvents.AmministrazioneReady, amministrazionePanel));
    }

    public void setCoefficienti(Map<String, CoefficienteModel> coefficienti) {
       amministrazione.setCoefficienti(coefficienti);
    }
    public void setCoupon(List<CouponModel> coupon) {
       amministrazione.setCoupon(coupon);
    }

    public void setProgettiDiCompensazione(List<ProgettoDiCompensazioneModel> progettiDiCompensazione) {
       amministrazione.setProgettiDiCompensazione(progettiDiCompensazione);
    }

    public void setOrdini(List<OrdineModel> ordini) {
        amministrazione.setOrdini(ordini);
    }

    public void setUserInfo(UserInfoModel userInfoModel) {
        amministrazione.setUserInfo(userInfoModel);
    }
}
