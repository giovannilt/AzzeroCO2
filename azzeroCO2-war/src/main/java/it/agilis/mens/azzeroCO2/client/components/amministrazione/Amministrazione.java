package it.agilis.mens.azzeroCO2.client.components.amministrazione;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.layout.RowData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import it.agilis.mens.azzeroCO2.client.forms.amministrazione.Coefficienti;
import it.agilis.mens.azzeroCO2.client.forms.amministrazione.Coupon;
import it.agilis.mens.azzeroCO2.client.forms.amministrazione.Ordini;
import it.agilis.mens.azzeroCO2.client.forms.amministrazione.ProgrammiDiCompensazione;
import it.agilis.mens.azzeroCO2.client.services.AzzeroCO2Constants;
import it.agilis.mens.azzeroCO2.client.services.HustonServiceAsync;
import it.agilis.mens.azzeroCO2.shared.model.amministrazione.CouponModel;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: serenadimaida
 * Date: 03/07/11
 * Time: 00.50
 * To change this template use File | Settings | File Templates.
 */
public class Amministrazione extends LayoutContainer {

    private final TabPanel amministrazioneTab = new TabPanel();
    private final Coefficienti coefficienti = new Coefficienti();
    private final ProgrammiDiCompensazione programmi = new ProgrammiDiCompensazione();
    private HustonServiceAsync hustonService;

    public Amministrazione() {
        hustonService = Registry.get(AzzeroCO2Constants.HUSTON_SERVICE);
    }

    @Override
    protected void onRender(Element target, int index) {
        super.onRender(target, index);
        HustonServiceAsync hustonService = Registry.get(AzzeroCO2Constants.HUSTON_SERVICE);
        final ListStore<CouponModel> store = new ListStore<CouponModel>();
        AsyncCallback<List<CouponModel>> aCallback = new AsyncCallback<List<CouponModel>>() {
            public void onFailure(Throwable caught) {
                Info.display("Error", "Problemi Di Connessione al SERVER di AzzeroCO2");
            }
            @Override
            public void onSuccess(List<CouponModel> result) {
                store.add(result);
            }

        };
        hustonService.getListOfCoupon(aCallback);
        setLayout(new RowLayout(Style.Orientation.HORIZONTAL));

        TabItem ordiniTab = new TabItem("Ordini");
        ordiniTab.add(new Ordini());
        amministrazioneTab.add(ordiniTab);

        TabItem couponTab = new TabItem("Coupon");
        couponTab.add(new Coupon(store));
        amministrazioneTab.add(couponTab);

        TabItem coefficientiTab = new TabItem("Coefficienti di calcolo");
        coefficientiTab.add(coefficienti);
        amministrazioneTab.add(coefficientiTab);

        TabItem programmiTab = new TabItem("Programmi compensazione");
        programmiTab.add(programmi);
        amministrazioneTab.add(programmiTab);

        add(amministrazioneTab, new RowData(1, 1));
    }
}
