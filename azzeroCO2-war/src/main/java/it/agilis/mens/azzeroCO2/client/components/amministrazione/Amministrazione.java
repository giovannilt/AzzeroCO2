package it.agilis.mens.azzeroCO2.client.components.amministrazione;

import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.google.gwt.user.client.Element;
import it.agilis.mens.azzeroCO2.client.forms.amministrazione.Coefficienti;
import it.agilis.mens.azzeroCO2.client.forms.amministrazione.Coupon;
import it.agilis.mens.azzeroCO2.client.forms.amministrazione.Ordini;
import it.agilis.mens.azzeroCO2.client.forms.amministrazione.ProgrammiDiCompensazione;

/**
 * Created by IntelliJ IDEA.
 * User: serenadimaida
 * Date: 03/07/11
 * Time: 00.50
 * To change this template use File | Settings | File Templates.
 */
public class Amministrazione extends LayoutContainer {

    private final TabPanel amministrazioneTab = new TabPanel();
    private final Ordini ordini = new Ordini();
    private final Coupon coupon = new Coupon();
    private final Coefficienti coefficienti = new Coefficienti();
    private final ProgrammiDiCompensazione programmi = new ProgrammiDiCompensazione();

    @Override
    protected void onRender(Element target, int index) {
        super.onRender(target, index);

        TabItem ordiniTab = new TabItem("Ordini");
        ordiniTab.add(ordini);
        amministrazioneTab.add(ordiniTab);

        TabItem couponTab = new TabItem("Coupon");
        couponTab.add(coupon);
        amministrazioneTab.add(couponTab);

        TabItem coefficientiTab = new TabItem("Coefficienti calcolo");
        coefficientiTab.add(coefficienti);
        amministrazioneTab.add(coefficientiTab);

        TabItem programmiTab = new TabItem("Programmi compensazione");
        programmiTab.add(programmi);
        amministrazioneTab.add(programmiTab);

        add(amministrazioneTab);
    }
}
