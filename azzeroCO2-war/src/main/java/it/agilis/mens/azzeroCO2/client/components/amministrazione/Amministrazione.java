package it.agilis.mens.azzeroCO2.client.components.amministrazione;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.layout.RowData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.google.gwt.user.client.Element;
import it.agilis.mens.azzeroCO2.client.forms.amministrazione.*;
import it.agilis.mens.azzeroCO2.shared.model.amministrazione.CoefficienteModel;
import it.agilis.mens.azzeroCO2.shared.model.amministrazione.CouponModel;
import it.agilis.mens.azzeroCO2.shared.model.amministrazione.OrdineModel;
import it.agilis.mens.azzeroCO2.shared.model.amministrazione.ProgettoDiCompensazioneModel;
import it.agilis.mens.azzeroCO2.shared.model.registrazione.UserInfoModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: serenadimaida
 * Date: 03/07/11
 * Time: 00.50
 * To change this template use File | Settings | File Templates.
 */
public class Amministrazione extends LayoutContainer {

    private final TabPanel amministrazioneTab = new TabPanel();

    private final Coefficienti coefficentiForm = new Coefficienti();
    private final ProgettiDiCompensazione progettiDiCompensazioneForm = new ProgettiDiCompensazione();
    private final Coupon couponForm = new Coupon();
    private final Ordini ordiniForm = new Ordini();
    private final UserInfo userInfoForm= new UserInfo();

    @Override
    protected void onRender(Element target, int index) {
        super.onRender(target, index);

        setLayout(new RowLayout(Style.Orientation.HORIZONTAL));

        TabItem ordiniTab = new TabItem("Ordini");
        ordiniTab.add(ordiniForm);
        amministrazioneTab.add(ordiniTab);

        TabItem couponTab = new TabItem("Coupon");
        couponTab.add(couponForm);
        amministrazioneTab.add(couponTab);

        TabItem coefficientiTab = new TabItem("Coefficienti di calcolo");
        coefficientiTab.add(coefficentiForm);
        amministrazioneTab.add(coefficientiTab);

        TabItem programmiTab = new TabItem("Programmi compensazione");
        programmiTab.add(progettiDiCompensazioneForm);
        amministrazioneTab.add(programmiTab);


        TabItem userTab = new TabItem("Profilo utente");
        userTab.add(userInfoForm);
        amministrazioneTab.add(userTab);


        add(amministrazioneTab, new RowData(1, 1));
    }

    public void setCoefficienti(Map<String, CoefficienteModel> coefficienti) {
        List coefficientiList= new ArrayList<CoefficienteModel>();
        coefficientiList.addAll(coefficienti.values());
        coefficentiForm.setCoefficentiInStore(coefficientiList);
    }
    public void setCoupon(List<CouponModel> coupon) {
        couponForm.setCouponInStore(coupon);
    }

    public void setProgettiDiCompensazione(List<ProgettoDiCompensazioneModel> progettiDiCompensazioneModels) {
        progettiDiCompensazioneForm.setProgettiDicompensazioneInStore(progettiDiCompensazioneModels);
    }

    public void setOrdini(List<OrdineModel> ordini) {
        ordiniForm.setOrdiniInStore(ordini);
    }

    public void setUserInfo(UserInfoModel userInfoModel) {
        //To change body of created methods use File | Settings | File Templates.
    }
}
