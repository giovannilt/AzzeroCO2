package it.agilis.mens.azzeroCO2.client.components.evento;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.widget.*;
import com.extjs.gxt.ui.client.widget.layout.*;
import com.google.gwt.user.client.Element;
import it.agilis.mens.azzeroCO2.client.forms.*;
import it.agilis.mens.azzeroCO2.client.forms.evento.EventoFormDettaglio;
import it.agilis.mens.azzeroCO2.client.mvc.events.EventoEvents;
import it.agilis.mens.azzeroCO2.shared.Eventi;
import it.agilis.mens.azzeroCO2.shared.Profile;
import it.agilis.mens.azzeroCO2.shared.model.OrdineModel;
import it.agilis.mens.azzeroCO2.shared.model.RiepilogoModel;
import it.agilis.mens.azzeroCO2.shared.model.amministrazione.CouponModel;
import it.agilis.mens.azzeroCO2.shared.model.amministrazione.ProgettoDiCompensazioneModel;
import it.agilis.mens.azzeroCO2.shared.model.evento.TipoDiCartaModel;
import it.agilis.mens.azzeroCO2.shared.model.evento.TrasportoMerciModel;
import it.agilis.mens.azzeroCO2.shared.model.pagamento.Esito;
import it.agilis.mens.azzeroCO2.shared.model.registrazione.UserInfoModel;
import it.agilis.mens.azzeroCO2.shared.vto.OrdineVTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 6/19/11
 * Time: 12:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class EventoDettaglio extends LayoutContainer {

    private final TabPanel eventoTab = new TabPanel();
    private EventoFormDettaglio formDettaglio = new EventoFormDettaglio();

    private final FormEnergia formEnergia = new FormEnergia();
    private final FormTrasportoPersone formTrasportoPersone = new FormTrasportoPersone();
    private final FormPernottamenti formPernottamenti = new FormPernottamenti();
    private final FormTrasportoMerci formTrasportoMerci = new FormTrasportoMerci();
    private final FormPubblicazioniRilegate formPubblicazioniRilegate = new FormPubblicazioniRilegate();
    private final FormManifestiPieghevoliFogli formManifestipieghevoliFogli = new FormManifestiPieghevoliFogli();

    public final FormRiepilogo formRiepilogo = new FormRiepilogo();
    private final FormAcquisto eventoFormAcquisto = new FormAcquisto();
    private final FormConferma formConferma = new FormConferma();
    private static int posizioniLabel = 1;
    private List<List<String>> posizioniText = new ArrayList<List<String>>();
    private UserInfoModel userInfoModel;

    @Override
    protected void onRender(Element target, int index) {
        super.onRender(target, index);

        setLayout(new RowLayout(Style.Orientation.HORIZONTAL));

        TabItem dettaglio = new TabItem("dettaglio");
        dettaglio.setLayout(new BorderLayout());
        dettaglio.add(formDettaglio, new BorderLayoutData(Style.LayoutRegion.CENTER));

        eventoTab.add(dettaglio);

        TabItem calcolo = new TabItem("calcolo");
        createCalcoloTabs();
        calcolo.add(createCalcoloTabs(), new BorderLayoutData(Style.LayoutRegion.CENTER));
        calcolo.setEnabled(false);
        eventoTab.add(calcolo);

        TabItem riepilogo = new TabItem("riepilogo");
        riepilogo.setStyleAttribute("background-color", "#555557");
        riepilogo.add(formRiepilogo, new BorderLayoutData(Style.LayoutRegion.CENTER));
        riepilogo.setEnabled(false);
        eventoTab.add(riepilogo);

        TabItem acquisto = new TabItem("acquisto");
        acquisto.setStyleAttribute("background-color", "#555557");
        acquisto.add(eventoFormAcquisto, new BorderLayoutData(Style.LayoutRegion.CENTER));
        acquisto.setEnabled(false);
        eventoTab.add(acquisto);

        TabItem conferma = new TabItem("conferma");
        conferma.setStyleAttribute("background-color", "#555557");
        conferma.add(formConferma, new BorderLayoutData(Style.LayoutRegion.CENTER));
        conferma.setEnabled(false);
        eventoTab.add(conferma);

        add(eventoTab, new RowData(1, 1));

        posizioniText.add(Arrays.asList(".", "Energia"));                                   // DETTAGLIO
        posizioniText.add(Arrays.asList("Dettaglio", "Trasporto persone"));                 // ENERGIA
        posizioniText.add(Arrays.asList("Energia", "Pernottamenti"));                      // TRASPORTO PERSONE
        posizioniText.add(Arrays.asList("Trasporto persone", "Trasporto Merci"));          // Pernottamenti
        posizioniText.add(Arrays.asList("Pernottamenti", "Pubblicazioni rilegate"));       // Trasporto Merci
        posizioniText.add(Arrays.asList("Trasporto merci", "Manifesti pieghevoli e fogli"));// Pubblicazioni rilegate
        posizioniText.add(Arrays.asList("Pubblicazioni rilegate", "Riepilogo"));           // Manifesti pieghevoli e Fogli
        posizioniText.add(Arrays.asList("Manifesti pieghevoli e fogli", "Acquisto"));       // RIEPILOGO
        posizioniText.add(Arrays.asList("Riepilogo", "Vai al pagamento"));                         // ACQUISTO
        posizioniText.add(Arrays.asList("", "torna alla home"));                                  // CONFERMA
    }


    public ContentPanel createCalcoloTabs() {
        ContentPanel calcoloCardPanel = new ContentPanel();
        calcoloCardPanel.setHeight(576);   //443        DIM
        final CardLayout layout = new CardLayout();
        calcoloCardPanel.setLayout(layout);
        calcoloCardPanel.setHeaderVisible(false);

        formEnergia.setTitle("Energia");
        calcoloCardPanel.add(formEnergia, new BorderLayoutData(Style.LayoutRegion.CENTER));
        formTrasportoPersone.setTitle("Trasporto Persone");
        calcoloCardPanel.add(formTrasportoPersone, new BorderLayoutData(Style.LayoutRegion.CENTER));
        formPernottamenti.setTitle("Pernottamenti");
        calcoloCardPanel.add(formPernottamenti, new BorderLayoutData(Style.LayoutRegion.CENTER));
        formTrasportoMerci.setTitle("Trasporto Merci");
        calcoloCardPanel.add(formTrasportoMerci, new BorderLayoutData(Style.LayoutRegion.CENTER));
        formPubblicazioniRilegate.setTitle("Pubblicazioni Rilegate");
        calcoloCardPanel.add(formPubblicazioniRilegate, new BorderLayoutData(Style.LayoutRegion.CENTER));
        formManifestipieghevoliFogli.setTitle("Manifesti Pieghevoli");
        calcoloCardPanel.add(formManifestipieghevoliFogli, new BorderLayoutData(Style.LayoutRegion.CENTER));

        return calcoloCardPanel;
    }

    public String previusTab() {
        for (int i = eventoTab.getItems().size() - 1; i >= 0; i--) {
            TabItem item = eventoTab.getItems().get(i);

            if (eventoTab.getSelectedItem().getText().equalsIgnoreCase(item.getText())) {
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
                                Dispatcher.forwardEvent(EventoEvents.NextText, posizioniText.get(posizioniLabel).get(1));
                                Dispatcher.forwardEvent(EventoEvents.PreviousText, posizioniText.get(posizioniLabel).get(0));
                                return layout.getActiveItem().getTitle();
                            }
                        }
                    }
                }
                if (i > 0) {
                    item.setEnabled(false);
                    eventoTab.getItems().get(i - 1).setEnabled(true);
                    eventoTab.setSelection(eventoTab.getItems().get(i - 1));
                    posizioniLabel--;
                    //  OrdineModel riepilogo = riepilogo();
                    Dispatcher.forwardEvent(EventoEvents.NextText, posizioniText.get(posizioniLabel).get(1));
                    Dispatcher.forwardEvent(EventoEvents.PreviousText, posizioniText.get(posizioniLabel).get(0));
                    return eventoTab.getSelectedItem().getTitle();
                }
            }
        }
        return "";
    }

    public String nextTab() {
        int i = 0;
        for (TabItem item : eventoTab.getItems()) {
            i++;
            if (eventoTab.getSelectedItem().getText().equalsIgnoreCase(item.getText())) {
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
                                Dispatcher.forwardEvent(EventoEvents.NextText, posizioniText.get(posizioniLabel).get(1));
                                Dispatcher.forwardEvent(EventoEvents.PreviousText, posizioniText.get(posizioniLabel).get(0));
                                return layout.getActiveItem().getTitle();
                            }
                        }
                    }
                }
                if (i < eventoTab.getItems().size()) {
                    if (eventoTab.getItems().get(i).getText().equalsIgnoreCase("Conferma")) {
                        Dispatcher.forwardEvent(EventoEvents.Conferma);
                        return eventoTab.getItems().get(i).getText();
                    }
                    if (eventoTab.getItems().get(i).getText().equalsIgnoreCase("Acquisto")) {
                        Dispatcher.forwardEvent(EventoEvents.Acquisto);
                    }
                    if (eventoTab.getItems().get(i).getText().equalsIgnoreCase("Vai al pagamento")) {
                        if (userInfoModel.getProfilo() == Profile.Guest.ordinal()) {
                            return eventoTab.getItems().get(i).getText();
                        }
                    }
                    if (eventoTab.getItems().get(i).getText().equalsIgnoreCase("Riepilogo")) {
                        Dispatcher.forwardEvent(EventoEvents.Riepilogo);
                    }
                    item.setEnabled(false);
                    eventoTab.getItems().get(i).setEnabled(true);
                    eventoTab.setSelection(eventoTab.getItems().get(i));

                    if (!eventoTab.getItems().get(i).getText().equalsIgnoreCase("Calcolo")) {
                        posizioniLabel++;
                    }
                    if (posizioniLabel == 0) {
                        posizioniLabel++;
                    }

                    Dispatcher.forwardEvent(EventoEvents.NextText, posizioniText.get(posizioniLabel).get(1));
                    Dispatcher.forwardEvent(EventoEvents.PreviousText, posizioniText.get(posizioniLabel).get(0));
                    return eventoTab.getItems().get(i).getText();
                }
            }
        }
        return "";
    }

    public void clearPanel() {
        formDettaglio.clear();
        formEnergia.clear();
        formTrasportoPersone.clear(true);
        formPernottamenti.clear();
        formTrasportoMerci.clear();
        formPubblicazioniRilegate.clear(true);
        formManifestipieghevoliFogli.clear(true);

        formRiepilogo.clear();
        eventoFormAcquisto.clear();

        if (posizioniLabel == 1) {
            return;
        }
        while (posizioniLabel > 0) {
            previusTab();
        }


        for (TabItem item : eventoTab.getItems()) {
            if (item.getText().equalsIgnoreCase("Calcolo")) {
                ContentPanel calcolo = (ContentPanel) item.getItem(0);
                FormEnergia formEnergia = (FormEnergia) calcolo.getItem(0);
                formEnergia.layout(true);
                formEnergia.setWidth("691");
                break;
            }
        }
    }

    public OrdineModel riepilogo() {
        OrdineModel eventoModel = formDettaglio.getOrdineModel();
        eventoModel.setEventiType(Eventi.EVENTO.name());
        eventoModel.setEnergiaModel(formEnergia.getEnergiaModel());
        eventoModel.setTrasportoPersoneModel(formTrasportoPersone.getTrasportoPersoneModel());
        eventoModel.setNottiModel(formPernottamenti.getNottiModel());
        eventoModel.setTrasportoMerciModel(formTrasportoMerci.getTrasportoMerciModel());

        eventoModel.setPubblicazioniRilegateModel(formPubblicazioniRilegate.getPubblicazioniRilegateModel());
        eventoModel.setManifestiPieghevoliFogliModel(formManifestipieghevoliFogli.getManifestiPieghevoliFogliModel());

        eventoModel.setProgettoDiCompensazioneModel(eventoFormAcquisto.getProgettoDiCompensazioneModel());
        eventoModel.setCouponModel(eventoFormAcquisto.getCouponModel());
        return eventoModel;
    }

    public void restore(OrdineModel eventoModel) {
        formDettaglio.setOrdineModel(eventoModel);
        formEnergia.setEnergiaModel(eventoModel.getEnergiaModel());
        formTrasportoPersone.setTrasportoPersoneModel(eventoModel.getTrasportoPersoneModel());
        formPernottamenti.setNottiModel(eventoModel.getNottiModel());
        formTrasportoMerci.setTrasportoMerciModel(eventoModel.getTrasportoMerciModel() == null ? new TrasportoMerciModel() : eventoModel.getTrasportoMerciModel());
        formPubblicazioniRilegate.setPubblicazioniRilegateModel(eventoModel.getPubblicazioniRilegateModel());
        formManifestipieghevoliFogli.setManifestiPieghevoliFogliModel(eventoModel.getManifestiPieghevoliFogliModel());

        eventoFormAcquisto.setProgettoDiCompensazione(eventoModel.getProgettoDiCompensazioneModel());
        eventoFormAcquisto.setCouponModel(eventoModel.getCouponModel());
    }

    public void setTipoDiCarta(List<TipoDiCartaModel> tipoDiCartaModels) {
        formPubblicazioniRilegate.setTipoDiCartaModel(tipoDiCartaModels);
        formManifestipieghevoliFogli.setTipoDiCartaModel(tipoDiCartaModels);
    }

    public void setEventoRiepilogoInStore(List<RiepilogoModel> eventoRiepilogoModels) {
        OrdineModel riepilogo = riepilogo();
        Esito esito = Esito.IN_PAGAMENTO;
        if (riepilogo.getPagamentoModel() != null &&
                riepilogo.getPagamentoModel().getEsito() != null) {
            esito = Esito.valueOf(riepilogo.getPagamentoModel().getEsito());
        }
        formRiepilogo.setRiepilogoInStore(eventoRiepilogoModels, esito);
        eventoFormAcquisto.setRiepilogo(eventoRiepilogoModels, riepilogo);
    }

    public void setProgettiDiCompensazione(List<ProgettoDiCompensazioneModel> progettiDiCompensazioneList) {
        eventoFormAcquisto.setInStore(progettiDiCompensazioneList);
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
        /*  for (TabItem item : eventoTab.getItems()) {
            if (item.getText().equalsIgnoreCase("Calcolo")) {
                ContentPanel calcolo = (ContentPanel) item.getItem(0);
                FormEnergia formEnergia = (FormEnergia) calcolo.getItem(0);
                formEnergia.layout(true);

                // TODO BETTER
                formEnergia.setWidth("691");
                break;
            }
        }*/
        while (posizioniLabel != 7) {
            nextTab();
        }
        Dispatcher.forwardEvent(EventoEvents.Riepilogo);
    }

    public void showStep(RiepilogoModel tabToShow) {
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
            if (s != null && !"".equalsIgnoreCase(s) && tabToShow.getOggetto().toLowerCase().startsWith(s.toLowerCase())) {
                return;
            }
        }
    }

    public void goToBegin() {
        while (posizioniLabel > 1) {
            previusTab();
        }
        previusTab();
    }

    public void clearStep(RiepilogoModel data) {
        if (data.getOggetto().equalsIgnoreCase("Energia")) {
            formEnergia.clear();
        } else if (data.getOggetto().startsWith("Trasporto Persone")) {
            formTrasportoPersone.clear(true);
        } else if (data.getOggetto().equalsIgnoreCase("Pernottamenti")) {
            formPernottamenti.clear();
        } else if (data.getOggetto().equalsIgnoreCase("Trasporto Merci")) {
            formTrasportoMerci.clear();
        } else if (data.getOggetto().toLowerCase().startsWith("Pubblicazioni rilegate".toLowerCase())) {
            formPubblicazioniRilegate.clear(true);
        } else if (data.getOggetto().toLowerCase().startsWith("Manifesti".toLowerCase())) {
            formManifestipieghevoliFogli.clear(true);
        }
    }

    public void showConferma(OrdineVTO result) {
        eventoTab.getSelectedItem().disable();
        posizioniLabel++;
        eventoTab.getItems().get(eventoTab.getItems().size() - 1).setEnabled(true);
        eventoTab.setSelection(eventoTab.getItems().get(eventoTab.getItems().size() - 1));
        Dispatcher.forwardEvent(EventoEvents.NextText, posizioniText.get(posizioniLabel).get(1));
        Dispatcher.forwardEvent(EventoEvents.PreviousText, posizioniText.get(posizioniLabel).get(0));
    }

    public void setCouponModel(CouponModel coupon) {
        eventoFormAcquisto.setCouponModel(coupon);
    }
}