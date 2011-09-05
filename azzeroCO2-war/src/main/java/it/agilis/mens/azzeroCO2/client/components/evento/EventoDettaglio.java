package it.agilis.mens.azzeroCO2.client.components.evento;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.widget.*;
import com.extjs.gxt.ui.client.widget.layout.*;
import com.google.gwt.user.client.Element;
import it.agilis.mens.azzeroCO2.client.forms.evento.*;
import it.agilis.mens.azzeroCO2.client.mvc.events.EventoEvents;
import it.agilis.mens.azzeroCO2.shared.model.RiepilogoModel;
import it.agilis.mens.azzeroCO2.shared.model.amministrazione.ProgettoDiCompensazioneModel;
import it.agilis.mens.azzeroCO2.shared.model.evento.DettaglioModel;
import it.agilis.mens.azzeroCO2.shared.model.evento.TipoDiCartaModel;

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

    private final EventoFormEnergia formEnergia = new EventoFormEnergia();
    private final EventoFormTrasportoPersone formTrasportoPersone = new EventoFormTrasportoPersone();
    private final EventoFormPernottamenti formPernottamenti = new EventoFormPernottamenti();
    private final EventoFormTrasportoMerci formTrasportoMerci = new EventoFormTrasportoMerci();
    private final EventoFormPubblicazioniRilegate formPubblicazioniRilegate = new EventoFormPubblicazioniRilegate();
    private final EventoFormManifestiPieghevoliFogli formManifestiPiegevoliFogli = new EventoFormManifestiPieghevoliFogli();

    private final EventoFormRiepilogo eventoFormRiepilogo = new EventoFormRiepilogo();
    private final EventoFormAcquisto eventoFormAcquisto = new EventoFormAcquisto();
    private final EventoFormConferma eventoFormConferma = new EventoFormConferma();

    @Override
    protected void onRender(Element target, int index) {
        super.onRender(target, index);

        setLayout(new RowLayout(Style.Orientation.HORIZONTAL));

        TabItem dettaglio = new TabItem("Dettaglio");
        dettaglio.setLayout(new BorderLayout());
        dettaglio.add(formDettaglio, new BorderLayoutData(Style.LayoutRegion.CENTER));

        eventoTab.add(dettaglio);

        TabItem calcolo = new TabItem("Calcolo");
        createCalcoloTabs();
        calcolo.add(createCalcoloTabs(), new BorderLayoutData(Style.LayoutRegion.CENTER));
        calcolo.setEnabled(false);
        eventoTab.add(calcolo);

        TabItem riepilogo = new TabItem("Riepilogo");
        riepilogo.add(eventoFormRiepilogo, new BorderLayoutData(Style.LayoutRegion.CENTER));
        riepilogo.setEnabled(false);
        eventoTab.add(riepilogo);

        TabItem acquisto = new TabItem("Acquisto");
        acquisto.add(eventoFormAcquisto, new BorderLayoutData(Style.LayoutRegion.CENTER));
        acquisto.setEnabled(false);
        eventoTab.add(acquisto);

        TabItem conferma = new TabItem("Conferma");
        conferma.add(eventoFormConferma, new BorderLayoutData(Style.LayoutRegion.CENTER));
        conferma.setEnabled(false);
        eventoTab.add(conferma);

        add(eventoTab, new RowData(1, 1));
    }


    public ContentPanel createCalcoloTabs() {
        ContentPanel calcoloCardPanel = new ContentPanel();
        calcoloCardPanel.setHeight(515);
        final CardLayout layout = new CardLayout();
        calcoloCardPanel.setLayout(layout);
        calcoloCardPanel.setHeaderVisible(false);

        formEnergia.setTitle("Energia");
        calcoloCardPanel.add(formEnergia, new BorderLayoutData(Style.LayoutRegion.CENTER));
        formTrasportoPersone.setTitle("TrasportoPersone");
        calcoloCardPanel.add(formTrasportoPersone, new BorderLayoutData(Style.LayoutRegion.CENTER));
        formPernottamenti.setTitle("Pernottamenti");
        calcoloCardPanel.add(formPernottamenti, new BorderLayoutData(Style.LayoutRegion.CENTER));
        formTrasportoMerci.setTitle("TrasportoMerci");
        calcoloCardPanel.add(formTrasportoMerci, new BorderLayoutData(Style.LayoutRegion.CENTER));
        formPubblicazioniRilegate.setTitle("PubblicazioniRilegate");
        calcoloCardPanel.add(formPubblicazioniRilegate, new BorderLayoutData(Style.LayoutRegion.CENTER));
        formManifestiPiegevoliFogli.setTitle("ManifestiPiegevoliFogli");
        calcoloCardPanel.add(formManifestiPiegevoliFogli, new BorderLayoutData(Style.LayoutRegion.CENTER));

        return calcoloCardPanel;
    }

    public void previusTab(AppEvent event) {
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
                                Dispatcher.forwardEvent(EventoEvents.PreviousText, calcolo.getItem(j - 1).getTitle());
                                Dispatcher.forwardEvent(EventoEvents.NextText, calcolo.getItem(j).getTitle());
                                return;
                            } else {
                                item.setEnabled(false);
                                eventoTab.getItems().get(i - 1).setEnabled(true);
                                eventoTab.setSelection(eventoTab.getItems().get(i - 1));
                                Dispatcher.forwardEvent(EventoEvents.PreviousText, eventoTab.getItems().get(i - 1).getText());
                                Dispatcher.forwardEvent(EventoEvents.NextText, eventoTab.getItems().get(i).getTitle());
                                return;
                            }
                        }
                    }
                } else {
                    if (i > 0) {
                        item.setEnabled(false);
                        eventoTab.getItems().get(i - 1).setEnabled(true);
                        eventoTab.setSelection(eventoTab.getItems().get(i - 1));
                        Dispatcher.forwardEvent(EventoEvents.PreviousText, eventoTab.getItems().get(i - 1).getText());
                        Dispatcher.forwardEvent(EventoEvents.NextText, eventoTab.getItems().get(i).getText());
                        return;
                    }
                }
            }

        }
    }

    public void nextTab(AppEvent event) {
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
                                Dispatcher.forwardEvent(EventoEvents.NextText, calcolo.getItem(j + 1).getTitle());
                                Dispatcher.forwardEvent(EventoEvents.PreviousText, calcolo.getItem(j - 1).getTitle());
                                return;
                            } else {
                                item.setEnabled(false);
                                eventoTab.getItems().get(i).setEnabled(true);
                                if (eventoTab.getItems().get(i).getText().equalsIgnoreCase("Riepilogo")) {
                                    Dispatcher.forwardEvent(EventoEvents.Riepilogo);
                                }
                                eventoTab.setSelection(eventoTab.getItems().get(i));
                                if (item.getText().equalsIgnoreCase("Calcolo")) {
                                    Dispatcher.forwardEvent(EventoEvents.NextText, "Trasporto Persone");
                                } else {
                                    Dispatcher.forwardEvent(EventoEvents.NextText, eventoTab.getItems().get(i).getText());
                                }
                                Dispatcher.forwardEvent(EventoEvents.PreviousText, eventoTab.getItems().get(i - 1).getText());
                                return;
                            }
                        }
                    }
                } else {
                    if (i < eventoTab.getItems().size()) {
                        item.setEnabled(false);
                        eventoTab.getItems().get(i).setEnabled(true);
                        eventoTab.setSelection(eventoTab.getItems().get(i));
                        if (eventoTab.getItems().get(i).getText().equalsIgnoreCase("Acquisto")) {
                            Dispatcher.forwardEvent(EventoEvents.CaricaProgettiDiCompensazione);
                        }
                        if (eventoTab.getItems().get(i).getText().equalsIgnoreCase("Calcolo")) {
                            Dispatcher.forwardEvent(EventoEvents.NextText, "Trasporto Persone");
                        } else {
                            Dispatcher.forwardEvent(EventoEvents.NextText, eventoTab.getItems().get(i).getText());
                        }
                        Dispatcher.forwardEvent(EventoEvents.PreviousText, eventoTab.getItems().get(i - 1).getText());
                        return;
                    }
                }
            }
        }
    }

    public void clearPanel() {
        formDettaglio.clear();
        formEnergia.clear();
        formTrasportoPersone.clear();
        formPernottamenti.clear();
        formTrasportoMerci.clear();
        formPubblicazioniRilegate.clear();
        formManifestiPiegevoliFogli.clear();

        eventoFormRiepilogo.clear();
        eventoFormAcquisto.clear();
        eventoFormConferma.clear();

    }

    public DettaglioModel riepilogo() {
        DettaglioModel eventoModel = formDettaglio.getDettaglioModel();
        eventoModel.setEnergiaModel(formEnergia.getEnergiaModel());
        eventoModel.setTrasportoPersoneModel(formTrasportoPersone.getTrasportoPersoneModel());
        eventoModel.setNottiModel(formPernottamenti.getNottiModel());
        eventoModel.setTrasportoMerciModel(formTrasportoMerci.getTrasportoMerciModel());
        eventoModel.setPubblicazioniRilegateModel(formPubblicazioniRilegate.getPubblicazioniRilegateModel());
        eventoModel.setManifestiPieghevoliFogliModel(formManifestiPiegevoliFogli.getManifestiPieghevoliFogliModel());
        return eventoModel;
    }

    public void restore(DettaglioModel eventoModel) {
        formDettaglio.setDettaglioModel(eventoModel);
        formEnergia.setEnergiaModel(eventoModel.getEnergiaModel());
        formTrasportoPersone.setTrasportoPersoneModel(eventoModel.getTrasportoPersoneModel());
        formPernottamenti.setNottiModel(eventoModel.getNottiModel());
        formTrasportoMerci.setTrasportoMerciModel(eventoModel.getTrasportoMerciModel());
        formPubblicazioniRilegate.setPubblicazioniRilegateModel(eventoModel.getPubblicazioniRilegateModel());
        formManifestiPiegevoliFogli.setManifestiPieghevoliFogliModel(eventoModel.getManifestiPieghevoliFogliModel());
    }

    public void setTipoDiCarta(List<TipoDiCartaModel> tipoDiCartaModels) {
        formPubblicazioniRilegate.setTipoDiCartaModel(tipoDiCartaModels);
        formManifestiPiegevoliFogli.setTipoDiCartaModel(tipoDiCartaModels);
    }

    public void setEventoRiepilogoInStore(List<RiepilogoModel> eventoRiepilogoModels) {
        if (eventoFormRiepilogo.getStore() != null) {
            for (RiepilogoModel rm : eventoFormRiepilogo.getStore().getModels()) {
                eventoFormRiepilogo.getStore().remove(rm);
            }
        }
        eventoFormRiepilogo.getStore().add(eventoRiepilogoModels);
        eventoFormAcquisto.setRiepilogo(eventoRiepilogoModels, riepilogo());
    }

    public void setProgettiDiCompensazione(List<ProgettoDiCompensazioneModel> progettiDiCompensazioneList) {
        eventoFormAcquisto.setInStore(progettiDiCompensazioneList);
    }
}