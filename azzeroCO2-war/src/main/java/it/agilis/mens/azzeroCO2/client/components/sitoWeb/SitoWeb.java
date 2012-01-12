package it.agilis.mens.azzeroCO2.client.components.sitoWeb;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.RowData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.google.gwt.user.client.Element;
import it.agilis.mens.azzeroCO2.client.forms.FormAcquisto;
import it.agilis.mens.azzeroCO2.client.forms.FormConferma;
import it.agilis.mens.azzeroCO2.client.forms.FormRiepilogo;
import it.agilis.mens.azzeroCO2.client.forms.FormSitoWeb;
import it.agilis.mens.azzeroCO2.client.mvc.events.SitoWebEvents;
import it.agilis.mens.azzeroCO2.shared.Profile;
import it.agilis.mens.azzeroCO2.shared.model.OrdineModel;
import it.agilis.mens.azzeroCO2.shared.model.RiepilogoModel;
import it.agilis.mens.azzeroCO2.shared.model.amministrazione.ProgettoDiCompensazioneModel;
import it.agilis.mens.azzeroCO2.shared.model.pagamento.Esito;
import it.agilis.mens.azzeroCO2.shared.model.registrazione.UserInfoModel;
import it.agilis.mens.azzeroCO2.shared.vto.OrdineVTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: serenadimaida
 * Date: 03/07/11
 * Time: 00.50
 * To change this template use File | Settings | File Templates.
 */
public class SitoWeb extends LayoutContainer {

    private OrdineModel ordineModel = new OrdineModel();

    private final TabPanel sitoWebTab = new TabPanel();
    private FormSitoWeb sitoWebForm = new FormSitoWeb();

    private final FormRiepilogo formRiepilogo = new FormRiepilogo();
    private final FormAcquisto formAcquisto = new FormAcquisto();
    private final FormConferma formConferma = new FormConferma();
    private static int posizioniLabel = 0;
    private List<List<String>> posizioniText = new ArrayList<List<String>>();
    private UserInfoModel userInfoModel;
    private AppEvent event;

    @Override
    protected void onRender(Element target, int index) {
        super.onRender(target, index);

        setLayout(new RowLayout(Style.Orientation.HORIZONTAL));

        TabItem sito = new TabItem("sito web");
        sito.setLayout(new BorderLayout());
        sito.add(sitoWebForm, new BorderLayoutData(Style.LayoutRegion.CENTER));

        sitoWebTab.add(sito);

        TabItem riepilogo = new TabItem("riepilogo");
        riepilogo.add(formRiepilogo, new BorderLayoutData(Style.LayoutRegion.CENTER));
        riepilogo.setEnabled(false);
        sitoWebTab.add(riepilogo);

        TabItem acquisto = new TabItem("Acquisto");
        acquisto.add(formAcquisto, new BorderLayoutData(Style.LayoutRegion.CENTER));
        acquisto.setEnabled(false);
        sitoWebTab.add(acquisto);

        TabItem conferma = new TabItem("conferma");
        conferma.setStyleAttribute("background-color", "#3F4757");
        conferma.add(formConferma, new BorderLayoutData(Style.LayoutRegion.CENTER));
        conferma.setEnabled(false);
        sitoWebTab.add(conferma);

        add(sitoWebTab, new RowData(1, 1));

        posizioniText.add(Arrays.asList("", "Riepilogo"));                                   // DETTAGLIO
        posizioniText.add(Arrays.asList("Sito web", "Acquisto"));       // RIEPILOGO
        posizioniText.add(Arrays.asList("Riepilogo", "Vai al pagamento"));                         // ACQUISTO
        posizioniText.add(Arrays.asList("", "torna alla home"));                                  // CONFERMA
    }


    public String previusTab() {
        for (int i = sitoWebTab.getItems().size() - 1; i >= 0; i--) {
            TabItem item = sitoWebTab.getItems().get(i);

            if (sitoWebTab.getSelectedItem().getText().equalsIgnoreCase(item.getText())) {

                if (i > 0) {
                    item.setEnabled(false);
                    sitoWebTab.getItems().get(i - 1).setEnabled(true);
                    sitoWebTab.setSelection(sitoWebTab.getItems().get(i - 1));
                    posizioniLabel--;
                    //  OrdineModel riepilogo = riepilogo();
                    Dispatcher.forwardEvent(SitoWebEvents.NextText, posizioniText.get(posizioniLabel).get(1));
                    Dispatcher.forwardEvent(SitoWebEvents.PreviousText, posizioniText.get(posizioniLabel).get(0));
                    return sitoWebTab.getSelectedItem().getTitle();
                }
            }
        }
        return "";
    }

    public String nextTab() {
        int i = 0;
        for (TabItem item : sitoWebTab.getItems()) {
            i++;
            if (sitoWebTab.getSelectedItem().getText().equalsIgnoreCase(item.getText())) {
                if (i < sitoWebTab.getItems().size()) {
                    if (sitoWebTab.getItems().get(i).getText().equalsIgnoreCase("Conferma")) {
                        Dispatcher.forwardEvent(SitoWebEvents.Conferma);
                        return sitoWebTab.getItems().get(i).getText();
                    }
                    if (sitoWebTab.getItems().get(i).getText().equalsIgnoreCase("Acquisto")) {
                        Dispatcher.forwardEvent(SitoWebEvents.Acquisto);
                    }
                    if (sitoWebTab.getItems().get(i).getText().equalsIgnoreCase("Vai al pagamento")) {
                        if (userInfoModel.getProfilo() == Profile.Guest.ordinal()) {
                            return sitoWebTab.getItems().get(i).getText();
                        }
                    }
                    if (sitoWebTab.getItems().get(i).getText().equalsIgnoreCase("Riepilogo")) {
                        Dispatcher.forwardEvent(SitoWebEvents.Riepilogo);
                    }
                    item.setEnabled(false);
                    sitoWebTab.getItems().get(i).setEnabled(true);
                    sitoWebTab.setSelection(sitoWebTab.getItems().get(i));

                    posizioniLabel++;

                    Dispatcher.forwardEvent(SitoWebEvents.NextText, posizioniText.get(posizioniLabel).get(1));
                    Dispatcher.forwardEvent(SitoWebEvents.PreviousText, posizioniText.get(posizioniLabel).get(0));
                    return sitoWebTab.getItems().get(i).getText();
                }
            }
        }
        return "";
    }

    public void clearPanel() {
        sitoWebForm.clear();
        sitoWebForm.setHeight(443);
        sitoWebForm.setWidth(691);

        formRiepilogo.clear();
        formAcquisto.clear();
        formConferma.clear();

        if (posizioniLabel == 1) {
            return;
        }
    }

    public OrdineModel riepilogo() {
        if (ordineModel == null) {
            ordineModel.setNome("Sito Web");
        }
        ordineModel.setSitoWebModel(sitoWebForm.getSitoWebModel());
        ordineModel.setProgettoDiCompensazioneModel(formAcquisto.getProgettoDiCompensazioneModel());
        return ordineModel;
    }

    public void restore(OrdineModel ordineModel) {
        formAcquisto.setProgettoDiCompensazione(ordineModel.getProgettoDiCompensazioneModel());
        this.ordineModel = ordineModel;

    }

    public void setProgettiDiCompensazione(List<ProgettoDiCompensazioneModel> progettiDiCompensazioneList) {
        formAcquisto.setInStore(progettiDiCompensazioneList);
    }

    public void setUserInfoModel(UserInfoModel userInfoModel) {
        this.userInfoModel = userInfoModel;
    }

    public void showConferma(OrdineVTO result) {
        sitoWebTab.getSelectedItem().disable();
        posizioniLabel++;
        sitoWebTab.getItems().get(sitoWebTab.getItems().size() - 1).setEnabled(true);
        sitoWebTab.setSelection(sitoWebTab.getItems().get(sitoWebTab.getItems().size() - 1));
        Dispatcher.forwardEvent(SitoWebEvents.NextText, posizioniText.get(posizioniLabel).get(1));
        Dispatcher.forwardEvent(SitoWebEvents.PreviousText, posizioniText.get(posizioniLabel).get(0));
    }

    public void setSitoWebRiepilogoInStore(List<RiepilogoModel> riepilogoModels) {
        OrdineModel riepilogo = riepilogo();
        Esito esito = Esito.IN_PAGAMENTO;
        if (riepilogo.getPagamentoModel() != null &&
                riepilogo.getPagamentoModel().getEsito() != null) {
            esito = Esito.valueOf(riepilogo.getPagamentoModel().getEsito());
        }
        formRiepilogo.setRiepilogoInStore(riepilogoModels, esito);
        formAcquisto.setRiepilogo(riepilogoModels, riepilogo);
    }

    public void goToBegin() {
        while (posizioniLabel > 1) {
            previusTab();
        }
        if (posizioniLabel > 0) {
            previusTab();
        }
    }

    public void showRiepilogo() {
        //To change body of created methods use File | Settings | File Templates.
    }
}
