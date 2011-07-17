package it.agilis.mens.azzeroCO2.client.components.amministrazione;

import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.layout.HBoxLayout;
import com.google.gwt.user.client.Element;
import it.agilis.mens.azzeroCO2.client.pagAmministrazione.AmministrazioneCoeffPage;
import it.agilis.mens.azzeroCO2.client.pagAmministrazione.AmministrazioneCouponPage;
import it.agilis.mens.azzeroCO2.client.pagAmministrazione.AmministrazioneOrdiniPage;
import it.agilis.mens.azzeroCO2.client.pagAmministrazione.AmministrazioneProgrammiPage;
import it.agilis.mens.azzeroCO2.client.pagAmministrazione.AmministrazioneCoeffPage;
import it.agilis.mens.azzeroCO2.client.pagAmministrazione.AmministrazioneCouponPage;
import it.agilis.mens.azzeroCO2.client.pagAmministrazione.AmministrazioneOrdiniPage;

/**
 * Created by IntelliJ IDEA.
 * User: serenadimaida
 * Date: 03/07/11
 * Time: 00.50
 * To change this template use File | Settings | File Templates.
 */
public class Amministrazione extends LayoutContainer {

    private final TabPanel amministrazioneTab = new TabPanel();


    private final AmministrazioneOrdiniPage amministrazioneOrdini = new AmministrazioneOrdiniPage();
    private final AmministrazioneCouponPage amministrazioneCoupon= new AmministrazioneCouponPage();
    private final AmministrazioneCoeffPage amministrazioneCoeff = new AmministrazioneCoeffPage();
    private final AmministrazioneProgrammiPage amministrazioneProgrammi = new AmministrazioneProgrammiPage();

    @Override
    protected void onRender(Element target, int index) {
        super.onRender(target, index);

        HBoxLayout layout = new HBoxLayout();
        layout.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.STRETCH);
        setLayout(layout);


        // TODO:.... CAPIRE COME CAZZO SI FA A STRETCH COME DIO COMANDA
        amministrazioneTab.setSize(855, 637);

        TabItem ordini = new TabItem("Ordini");
        ordini.add(amministrazioneOrdini);
        amministrazioneTab.add(ordini);


        TabItem coupon = new TabItem("Coupon");
        coupon.setEnabled(false);
        coupon.add(amministrazioneCoupon);
        amministrazioneTab.add(coupon);

        TabItem coeff = new TabItem("Coefficienti calcolo");
        coeff.setEnabled(false);
        coeff.add(amministrazioneCoeff);
        amministrazioneTab.add(coeff);

        TabItem programmi = new TabItem("Programmi compensazione");
        programmi.setEnabled(false);
        programmi.add(amministrazioneProgrammi);
        amministrazioneTab.add(programmi);
        add(amministrazioneTab);



    }


}
