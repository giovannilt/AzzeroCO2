package it.agilis.mens.azzeroCO2.client.components.amministrazione;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.layout.RowData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.google.gwt.user.client.Element;
import it.agilis.mens.azzeroCO2.client.forms.amministrazione.*;
import it.agilis.mens.azzeroCO2.shared.Profile;
import it.agilis.mens.azzeroCO2.shared.model.amministrazione.CoefficienteModel;
import it.agilis.mens.azzeroCO2.shared.model.amministrazione.CouponModel;
import it.agilis.mens.azzeroCO2.shared.model.amministrazione.ProgettoDiCompensazioneModel;
import it.agilis.mens.azzeroCO2.shared.model.evento.DettaglioModel;
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
    private final UserInfo userInfoForm = new UserInfo();
    private UserInfoModel userInfoModel;
    private TabItem couponTab = new TabItem("Coupon");
    private TabItem coefficientiTab = new TabItem("Coefficienti di calcolo");
    private TabItem programmiTab = new TabItem("Progetti di compensazione");
    private TabItem ordiniTab = new TabItem("I tuoi calcoli");
    private TabItem userTab = new TabItem("Utente");

    @Override
    protected void onRender(Element target, int index) {
        super.onRender(target, index);
        setLayout(new RowLayout(Style.Orientation.HORIZONTAL));

        ordiniTab.add(ordiniForm);
        couponTab.add(couponForm);
        coefficientiTab.add(coefficentiForm);
        programmiTab.add(progettiDiCompensazioneForm);
        userTab.add(userInfoForm);
        add(amministrazioneTab, new RowData(1, 1));
    }

    public void setCoefficienti(Map<String, CoefficienteModel> coefficienti) {
        List coefficientiList = new ArrayList<CoefficienteModel>();
        coefficientiList.addAll(coefficienti.values());
        coefficentiForm.setCoefficentiInStore(coefficientiList);
    }

    public void setCoupon(List<CouponModel> coupon) {
        couponForm.setCouponInStore(coupon);
    }

    public void setProgettiDiCompensazione(List<ProgettoDiCompensazioneModel> progettiDiCompensazioneModels) {
        progettiDiCompensazioneForm.setProgettiDicompensazioneInStore(progettiDiCompensazioneModels);
    }

    public void setOrdini(List<DettaglioModel> ordini) {
        ordiniForm.setOrdiniInStore(ordini);
    }


    public void setUserInfo(UserInfoModel userInfoModel) {
        this.userInfoModel = userInfoModel;
        userInfoForm.setUserInStore(userInfoModel);
        amministrazioneTab.add(ordiniTab);
        amministrazioneTab.add(userTab);

        if (userInfoModel != null &&
                userInfoModel.getProfilo() != null &&
                (userInfoModel.getProfilo().intValue() == Profile.Administrator.ordinal()
                        || userInfoModel.getProfilo().intValue() == Profile.SuperAdministrator.ordinal()
                )) {

            amministrazioneTab.add(couponTab);
            amministrazioneTab.add(coefficientiTab);
            amministrazioneTab.add(programmiTab);
            //ALL VISIBLE
        }
        amministrazioneTab.setSelection(ordiniTab);
    }
}



