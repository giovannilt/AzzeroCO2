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
import it.agilis.mens.azzeroCO2.shared.vto.DettaglioVTO;

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


    private final TabPanel sitoWebTab = new TabPanel();
    private FormSitoWeb sitoWebForm = new FormSitoWeb();


    private final FormRiepilogo formRiepilogo = new FormRiepilogo();
    private final FormAcquisto formAcquisto = new FormAcquisto();
    private final FormConferma formConferma = new FormConferma();
    private static int posizioniLabel = 1;
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

        TabItem acquisto = new TabItem("scegli progetto di compensazione");
        acquisto.add(formAcquisto, new BorderLayoutData(Style.LayoutRegion.CENTER));
        acquisto.setEnabled(false);
        sitoWebTab.add(acquisto);

        TabItem conferma = new TabItem("conferma");
        conferma.add(formConferma, new BorderLayoutData(Style.LayoutRegion.CENTER));
        conferma.setEnabled(false);
        sitoWebTab.add(conferma);

        add(sitoWebTab, new RowData(1, 1));

        posizioniText.add(Arrays.asList("", "Riepilogo"));                                   // DETTAGLIO
        posizioniText.add(Arrays.asList("Sito web", "Scegli progetto di compensazione"));       // RIEPILOGO
        posizioniText.add(Arrays.asList("Riepilogo", "Vai al pagamento"));                         // ACQUISTO
        posizioniText.add(Arrays.asList("", "torna alla home"));                                  // CONFERMA
        // ACQUISTO
    }


    public String previusTab(AppEvent event) {
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

    public String nextTab(AppEvent event) {
        int i = 0;
        for (TabItem item : sitoWebTab.getItems()) {
            i++;
            if (sitoWebTab.getSelectedItem().getText().equalsIgnoreCase(item.getText())) {

                if (i < sitoWebTab.getItems().size()) {
                    if (sitoWebTab.getItems().get(i).getText().equalsIgnoreCase("Conferma")) {
                        Dispatcher.forwardEvent(SitoWebEvents.Conferma);
                        return sitoWebTab.getItems().get(i).getText();
                    }
                    if (sitoWebTab.getItems().get(i).getText().equalsIgnoreCase("Scegli progetto di compensazione")) {
                        Dispatcher.forwardEvent(SitoWebEvents.Acquisto);
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

                    if (posizioniLabel == 0) {
                        posizioniLabel++;
                    }

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


        for (TabItem item : sitoWebTab.getItems()) {

        }
    }

    public OrdineModel riepilogo() {
        //TODO ... . . . . .
        OrdineModel eventoModel = null;//sitoWebForm.getOrdineModel();
        eventoModel.setProgettoDiCompensazioneModel(formAcquisto.getProgettoDiCompensazioneModel());
        return eventoModel;
    }

    public void restore(OrdineModel eventoModel) {
        //sitoWebForm.setOrdineModel(eventoModel);

        formAcquisto.setProgettoDiCompensazione(eventoModel.getProgettoDiCompensazioneModel());
    }


    public void setEventoRiepilogoInStore(List<RiepilogoModel> eventoRiepilogoModels) {
        OrdineModel riepilogo = riepilogo();
        Esito esito = Esito.IN_PAGAMENTO;
        if (riepilogo.getPagamentoModel() != null &&
                riepilogo.getPagamentoModel().getEsito() != null) {
            esito = Esito.valueOf(riepilogo.getPagamentoModel().getEsito());
        }
        formRiepilogo.setRiepilogoInStore(eventoRiepilogoModels, esito);
        formAcquisto.setRiepilogo(eventoRiepilogoModels, riepilogo);
    }

    public void setProgettiDiCompensazione(List<ProgettoDiCompensazioneModel> progettiDiCompensazioneList) {
        formAcquisto.setInStore(progettiDiCompensazioneList);
    }

    public void setUserInfoModel(UserInfoModel userInfoModel) {
        this.userInfoModel = userInfoModel;
    }

    public void showRiepilogo() {
        if (posizioniLabel != 1) {

        }
        for (TabItem item : sitoWebTab.getItems()) {

        }

        Dispatcher.forwardEvent(SitoWebEvents.Riepilogo);
    }

    public void showStep(RiepilogoModel tabToShow) {
        while (posizioniLabel > 0) {
            String s = previusTab(event);
            if (s != null && !"".equalsIgnoreCase(s) && tabToShow.getOggetto().toLowerCase().startsWith(s.toLowerCase())) {
                return;
            }
        }

        String s = "";
        while (posizioniText.size() - 4 >= posizioniLabel) {
            s = nextTab(event);
            if (s.equalsIgnoreCase("Conferma")) {
                return;
            }
            if (s != null && !"".equalsIgnoreCase(s) && tabToShow.getOggetto().toLowerCase().startsWith(s.toLowerCase())) {
                return;
            }
        }
    }


    public void showConferma(DettaglioVTO result) {
        sitoWebTab.getSelectedItem().disable();
        posizioniLabel++;
        sitoWebTab.getItems().get(sitoWebTab.getItems().size() - 1).setEnabled(true);
        sitoWebTab.setSelection(sitoWebTab.getItems().get(sitoWebTab.getItems().size() - 1));
        Dispatcher.forwardEvent(SitoWebEvents.NextText, posizioniText.get(posizioniLabel).get(1));
        Dispatcher.forwardEvent(SitoWebEvents.PreviousText, posizioniText.get(posizioniLabel).get(0));
    }


}
