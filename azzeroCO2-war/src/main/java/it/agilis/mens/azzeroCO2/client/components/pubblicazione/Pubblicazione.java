package it.agilis.mens.azzeroCO2.client.components.pubblicazione;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.widget.*;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.CardLayout;
import com.extjs.gxt.ui.client.widget.layout.RowData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.google.gwt.user.client.Element;
import it.agilis.mens.azzeroCO2.client.forms.*;
import it.agilis.mens.azzeroCO2.client.forms.publicazioni.FormBigliettiDaVisita;
import it.agilis.mens.azzeroCO2.client.mvc.events.PubblicazioniEvents;
import it.agilis.mens.azzeroCO2.shared.Eventi;
import it.agilis.mens.azzeroCO2.shared.Profile;
import it.agilis.mens.azzeroCO2.shared.model.OrdineModel;
import it.agilis.mens.azzeroCO2.shared.model.RiepilogoModel;
import it.agilis.mens.azzeroCO2.shared.model.amministrazione.ProgettoDiCompensazioneModel;
import it.agilis.mens.azzeroCO2.shared.model.evento.TipoDiCartaModel;
import it.agilis.mens.azzeroCO2.shared.model.pagamento.Esito;
import it.agilis.mens.azzeroCO2.shared.model.registrazione.UserInfoModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: serenadimaida
 * Date: 07/12/11
 * Time: 18:41
 * To change this template use File | Settings | File Templates.
 */
public class Pubblicazione extends LayoutContainer {

    private final TabPanel pubblicazioneoTab = new TabPanel();

    private final FormPubblicazioniRilegate formPubblicazioniRilegate = new FormPubblicazioniRilegate();
    private final FormManifestiPieghevoliFogli formManifestipieghevoliFogli = new FormManifestiPieghevoliFogli();
    private final FormBigliettiDaVisita formBigliettiDaVisita = new FormBigliettiDaVisita();

    private final FormRiepilogo formRiepilogo = new FormRiepilogo();
    private final FormAcquisto formAcquisto = new FormAcquisto();
    private final FormConferma formConferma = new FormConferma();
    private static int posizioniLabel = 0;
    private List<List<String>> posizioniText = new ArrayList<List<String>>();
    private UserInfoModel userInfoModel;
    private OrdineModel ordineModel = new OrdineModel();

    @Override
    protected void onRender(Element target, int index) {
        super.onRender(target, index);

        setLayout(new RowLayout(Style.Orientation.HORIZONTAL));

        TabItem calcolo = new TabItem("calcolo");
        createCalcoloTabs();
        calcolo.add(createCalcoloTabs(), new BorderLayoutData(Style.LayoutRegion.CENTER));
        calcolo.setEnabled(true);
        pubblicazioneoTab.add(calcolo);

        TabItem riepilogo = new TabItem("riepilogo");
        riepilogo.add(formRiepilogo, new BorderLayoutData(Style.LayoutRegion.CENTER));
        riepilogo.setEnabled(false);
        pubblicazioneoTab.add(riepilogo);

        TabItem acquisto = new TabItem("acquisto");
        acquisto.setStyleAttribute("background-color", "#555557");
        acquisto.add(formAcquisto, new BorderLayoutData(Style.LayoutRegion.CENTER));
        acquisto.setEnabled(false);
        pubblicazioneoTab.add(acquisto);

        TabItem conferma = new TabItem("conferma");
        conferma.setStyleAttribute("background-color", "#555557");
        conferma.add(formConferma, new BorderLayoutData(Style.LayoutRegion.CENTER));
        conferma.setEnabled(false);
        pubblicazioneoTab.add(conferma);

        add(pubblicazioneoTab, new RowData(1, 1));

        posizioniText.add(Arrays.asList(".", "Manifesti pieghevoli e fogli"));// Pubblicazioni rilegate
        posizioniText.add(Arrays.asList("Pubblicazioni rilegate", "Biglietti da visita e cartelline"));
        posizioniText.add(Arrays.asList("Manifesti pieghevoli e fogli", "Riepilogo"));           // Manifesti pieghevoli e Fogli
        posizioniText.add(Arrays.asList("Biglietti da visita e cartelline", "Acquisto"));       // RIEPILOGO
        posizioniText.add(Arrays.asList("Riepilogo", "Vai al pagamento"));                         // ACQUISTO
        posizioniText.add(Arrays.asList("", "torna alla home"));                                  // CONFERMA
    }


    public ContentPanel createCalcoloTabs() {
        ContentPanel calcoloCardPanel = new ContentPanel();
        calcoloCardPanel.setHeight(523); //DIM
        final CardLayout layout = new CardLayout();
        calcoloCardPanel.setLayout(layout);
        calcoloCardPanel.setHeaderVisible(false);

        formPubblicazioniRilegate.setTitle("Pubblicazioni Rilegate");
        calcoloCardPanel.add(formPubblicazioniRilegate, new BorderLayoutData(Style.LayoutRegion.CENTER));

        formManifestipieghevoliFogli.setTitle("Manifesti Pieghevoli e Fogli");
        calcoloCardPanel.add(formManifestipieghevoliFogli, new BorderLayoutData(Style.LayoutRegion.CENTER));

        formBigliettiDaVisita.setTitle("Biglietti da visita e cartelline");
        calcoloCardPanel.add(formBigliettiDaVisita, new BorderLayoutData(Style.LayoutRegion.CENTER));

        return calcoloCardPanel;
    }

    public String previusTab() {
        for (int i = pubblicazioneoTab.getItems().size() - 1; i >= 0; i--) {
            TabItem item = pubblicazioneoTab.getItems().get(i);

            if (pubblicazioneoTab.getSelectedItem().getText().equalsIgnoreCase(item.getText())) {
                if (item.getText().equalsIgnoreCase("Calcolo")) {
                    ContentPanel calcolo = (ContentPanel) item.getItem(0);
                    CardLayout layout = (CardLayout) calcolo.getLayout();

                    for (int j = calcolo.getItems().size() - 1; j >= 0; j--) {
                        Component subItem = calcolo.getItems().get(j);
                        if (layout.getActiveItem().getTitle().equalsIgnoreCase(subItem.getTitle())) {
                            if (j > 0) {
                                layout.setActiveItem(calcolo.getItem(j - 1));
                                posizioniLabel--;
                                //  OrdineModel riepilogo = riepilogo();
                                Dispatcher.forwardEvent(PubblicazioniEvents.NextText, posizioniText.get(posizioniLabel).get(1));
                                Dispatcher.forwardEvent(PubblicazioniEvents.PreviousText, posizioniText.get(posizioniLabel).get(0));
                                return layout.getActiveItem().getTitle();
                            }
                        }
                    }
                }
                if (i > 0) {
                    item.setEnabled(false);
                    pubblicazioneoTab.getItems().get(i - 1).setEnabled(true);
                    pubblicazioneoTab.setSelection(pubblicazioneoTab.getItems().get(i - 1));
                    posizioniLabel--;
                    //  OrdineModel riepilogo = riepilogo();
                    Dispatcher.forwardEvent(PubblicazioniEvents.NextText, posizioniText.get(posizioniLabel).get(1));
                    Dispatcher.forwardEvent(PubblicazioniEvents.PreviousText, posizioniText.get(posizioniLabel).get(0));
                    return pubblicazioneoTab.getSelectedItem().getTitle();
                }
            }
        }
        return "";
    }

    public String nextTab() {
        int i = 0;
        for (TabItem item : pubblicazioneoTab.getItems()) {
            i++;
            if (pubblicazioneoTab.getSelectedItem().getText().equalsIgnoreCase(item.getText())) {
                if (item.getText().equalsIgnoreCase("Calcolo")) {
                    ContentPanel calcolo = (ContentPanel) item.getItem(0);
                    CardLayout layout = (CardLayout) calcolo.getLayout();

                    int j = 0;
                    for (Component subItem : calcolo.getItems()) {
                        j++;
                        if (layout.getActiveItem().getTitle().equalsIgnoreCase(subItem.getTitle())) {
                            if (j < calcolo.getItems().size()) {
                                layout.setActiveItem(calcolo.getItem(j));
                                posizioniLabel++;
                                Dispatcher.forwardEvent(PubblicazioniEvents.NextText, posizioniText.get(posizioniLabel).get(1));
                                Dispatcher.forwardEvent(PubblicazioniEvents.PreviousText, posizioniText.get(posizioniLabel).get(0));
                                return layout.getActiveItem().getTitle();
                            }
                        }
                    }
                }
                if (i < pubblicazioneoTab.getItems().size()) {
                    if (pubblicazioneoTab.getItems().get(i).getText().equalsIgnoreCase("Conferma")) {
                        Dispatcher.forwardEvent(PubblicazioniEvents.Conferma);
                        return pubblicazioneoTab.getItems().get(i).getText();
                    }
                    if (pubblicazioneoTab.getItems().get(i).getText().equalsIgnoreCase("Acquisto")) {
                        Dispatcher.forwardEvent(PubblicazioniEvents.Acquisto);
                    }
                    if (pubblicazioneoTab.getItems().get(i).getText().equalsIgnoreCase("Vai al pagamento")) {
                        if (userInfoModel.getProfilo() == Profile.Guest.ordinal()) {
                            return pubblicazioneoTab.getItems().get(i).getText();
                        }
                    }
                    if (pubblicazioneoTab.getItems().get(i).getText().equalsIgnoreCase("Riepilogo")) {
                        Dispatcher.forwardEvent(PubblicazioniEvents.Riepilogo);
                    }
                    item.setEnabled(false);
                    pubblicazioneoTab.getItems().get(i).setEnabled(true);
                    pubblicazioneoTab.setSelection(pubblicazioneoTab.getItems().get(i));

                    if (!pubblicazioneoTab.getItems().get(i).getText().equalsIgnoreCase("Calcolo")) {
                        posizioniLabel++;
                    }
                    if (posizioniLabel == 0) {
                        posizioniLabel++;
                    }

                    Dispatcher.forwardEvent(PubblicazioniEvents.NextText, posizioniText.get(posizioniLabel).get(1));
                    Dispatcher.forwardEvent(PubblicazioniEvents.PreviousText, posizioniText.get(posizioniLabel).get(0));
                    return pubblicazioneoTab.getItems().get(i).getText();
                }
            }
        }
        return "";
    }

    public void clearPanel() {
        formPubblicazioniRilegate.clear(true);
        formManifestipieghevoliFogli.clear(true);
        formBigliettiDaVisita.clear();

        formRiepilogo.clear();
        formAcquisto.clear();
        formConferma.clear();
    }

    public OrdineModel riepilogo() {
        ordineModel.setNome("Pubblicazioni");

        ordineModel.setEventiType(Eventi.UNA_PUBBLICAZIONE.name());

        ordineModel.setPubblicazioniRilegateModel(formPubblicazioniRilegate.getPubblicazioniRilegateModel());
        ordineModel.setManifestiPieghevoliFogliModel(formManifestipieghevoliFogli.getManifestiPieghevoliFogliModel());
        ordineModel.setBigliettiDaVisitaModel(formBigliettiDaVisita.getBigliettiDaVisitaModel());

        ordineModel.setProgettoDiCompensazioneModel(formAcquisto.getProgettoDiCompensazioneModel());

        return ordineModel;
    }

    public void restore(OrdineModel ordineModel) {
        this.ordineModel = ordineModel;
        formPubblicazioniRilegate.setPubblicazioniRilegateModel(ordineModel.getPubblicazioniRilegateModel());
        formManifestipieghevoliFogli.setManifestiPieghevoliFogliModel(ordineModel.getManifestiPieghevoliFogliModel());
        formAcquisto.setProgettoDiCompensazione(ordineModel.getProgettoDiCompensazioneModel());
        formBigliettiDaVisita.setBigliettiDaVisitaModel(ordineModel.getBigliettiDaVisitaModel());
    }

    public void setTipoDiCarta(List<TipoDiCartaModel> tipoDiCartaModels) {
        formPubblicazioniRilegate.setTipoDiCartaModel(tipoDiCartaModels);
        formManifestipieghevoliFogli.setTipoDiCartaModel(tipoDiCartaModels);
        formBigliettiDaVisita.setTipoDiCartaModel(tipoDiCartaModels);
    }

    public void setPubblicazioneRiepilogoInStore(List<RiepilogoModel> eventoRiepilogoModels) {
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
            while (posizioniLabel != 0) {
                previusTab();
            }
        }
        for (TabItem item : pubblicazioneoTab.getItems()) {
            if (item.getText().equalsIgnoreCase("Calcolo")) {
                ContentPanel calcolo = (ContentPanel) item.getItem(0);
                FormEnergia formEnergia = (FormEnergia) calcolo.getItem(0);
                formEnergia.layout(true);

                // TODO BETTER
                formEnergia.setWidth("691");
                break;
            }
        }
        while (posizioniLabel != 7) {
            nextTab();
        }
        Dispatcher.forwardEvent(PubblicazioniEvents.Riepilogo);
    }

    public void showStep(RiepilogoModel tabToShow) {
        if (posizioniLabel == 0) {
            if (tabToShow.getOggetto().toLowerCase().startsWith("pubblicazioni rilegate")) {
                return;
            }
        }

        while (posizioniLabel > 0) {
            String s = previusTab();
            if (s != null && !"".equalsIgnoreCase(s) && tabToShow.getOggetto().toLowerCase().startsWith(s.toLowerCase())) {
                return;
            }
        }

        String s = "";
        while (posizioniText.size() - 4 >= posizioniLabel) {
            s = nextTab();
            if (s.equalsIgnoreCase("Vai al pagamento")) {
                return;
            }
            if (s != null &&
                    !"".equalsIgnoreCase(s) &&
                    tabToShow.getOggetto().toLowerCase().startsWith(s.toLowerCase())) {
                return;
            }
        }
    }

    public void showConferma() {
        pubblicazioneoTab.getSelectedItem().disable();
        posizioniLabel++;
        pubblicazioneoTab.getItems().get(pubblicazioneoTab.getItems().size() - 1).setEnabled(true);
        pubblicazioneoTab.setSelection(pubblicazioneoTab.getItems().get(pubblicazioneoTab.getItems().size() - 1));
        Dispatcher.forwardEvent(PubblicazioniEvents.NextText, posizioniText.get(posizioniLabel).get(1));
        Dispatcher.forwardEvent(PubblicazioniEvents.PreviousText, posizioniText.get(posizioniLabel).get(0));
    }

    public void goToBegin() {
        while (posizioniLabel > 1) {
            previusTab();
        }
        previusTab();
    }

}
