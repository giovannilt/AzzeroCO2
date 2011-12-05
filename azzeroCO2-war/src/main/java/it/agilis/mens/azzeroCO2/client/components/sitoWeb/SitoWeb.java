package it.agilis.mens.azzeroCO2.client.components.sitoWeb;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.widget.*;
import com.extjs.gxt.ui.client.widget.layout.*;
import com.google.gwt.user.client.Element;
import it.agilis.mens.azzeroCO2.client.forms.SitoWebForm;
import it.agilis.mens.azzeroCO2.client.forms.evento.*;
import it.agilis.mens.azzeroCO2.client.mvc.events.EventoEvents;
import it.agilis.mens.azzeroCO2.shared.Profile;
import it.agilis.mens.azzeroCO2.shared.model.RiepilogoModel;
import it.agilis.mens.azzeroCO2.shared.model.amministrazione.ProgettoDiCompensazioneModel;
import it.agilis.mens.azzeroCO2.shared.model.evento.DettaglioModel;
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
    private SitoWebForm sitoWebForm = new SitoWebForm();



    private final EventoFormRiepilogo eventoFormRiepilogo = new EventoFormRiepilogo();
    private final EventoFormAcquisto eventoFormAcquisto = new EventoFormAcquisto();
    private final EventoFormConferma eventoFormConferma = new EventoFormConferma();
    private static int posizioniLabel = 1;
    private List<List<String>> posizioniText = new ArrayList<List<String>>();
    private UserInfoModel userInfoModel;
    private AppEvent event;

    @Override
    protected void onRender(Element target, int index) {
        super.onRender(target, index);

        setLayout(new RowLayout(Style.Orientation.HORIZONTAL));

        TabItem sito = new TabItem("Sito web");
        sito.setLayout(new BorderLayout());
        sito.add(sitoWebForm, new BorderLayoutData(Style.LayoutRegion.CENTER));

        sitoWebTab.add(sito);


        TabItem riepilogo = new TabItem("Riepilogo");
        riepilogo.add(eventoFormRiepilogo, new BorderLayoutData(Style.LayoutRegion.CENTER));
        riepilogo.setEnabled(false);
        sitoWebTab.add(riepilogo);

        TabItem acquisto = new TabItem("Scegli progetto di compensazione");
        acquisto.add(eventoFormAcquisto, new BorderLayoutData(Style.LayoutRegion.CENTER));
        acquisto.setEnabled(false);
        sitoWebTab.add(acquisto);

        TabItem conferma = new TabItem("Conferma");
        conferma.add(eventoFormConferma, new BorderLayoutData(Style.LayoutRegion.CENTER));
        conferma.setEnabled(false);
        sitoWebTab.add(conferma);

        add(sitoWebTab, new RowData(1, 1));

        posizioniText.add(Arrays.asList("", "Riepilogo"));                                   // DETTAGLIO
        posizioniText.add(Arrays.asList("Sito web", "Scegli progetto di compensazione"));       // RIEPILOGO
        posizioniText.add(Arrays.asList("Riepilogo", "Conferma"));                         // ACQUISTO
        posizioniText.add(Arrays.asList("Scegli progetto di compensazione", ""));                                  // CONFERMA
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
                    //  DettaglioModel riepilogo = riepilogo();
                    Dispatcher.forwardEvent(EventoEvents.NextText, posizioniText.get(posizioniLabel).get(1));
                    Dispatcher.forwardEvent(EventoEvents.PreviousText, posizioniText.get(posizioniLabel).get(0));
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
                        Dispatcher.forwardEvent(EventoEvents.Conferma);
                        // Dispatcher.forwardEvent(EventoEvents.SentEmailConferma);
                        return sitoWebTab.getItems().get(i).getText();
                    }
                    if (sitoWebTab.getItems().get(i).getText().equalsIgnoreCase("Scegli progetto di compensazione")) {
                        Dispatcher.forwardEvent(EventoEvents.Acquisto);
                        if (userInfoModel.getProfilo() == Profile.Guest.ordinal()) {
                            return sitoWebTab.getItems().get(i).getText();
                        }
                    }
                    if (sitoWebTab.getItems().get(i).getText().equalsIgnoreCase("Riepilogo")) {
                        Dispatcher.forwardEvent(EventoEvents.Riepilogo);
                    }

                    item.setEnabled(false);

                    sitoWebTab.getItems().get(i).setEnabled(true);
                    sitoWebTab.setSelection(sitoWebTab.getItems().get(i));

                    if (!sitoWebTab.getItems().get(i).getText().equalsIgnoreCase("Calcolo")) {
                        posizioniLabel++;
                    }
                    if (posizioniLabel == 0) {
                        posizioniLabel++;
                    }

                    Dispatcher.forwardEvent(EventoEvents.NextText, posizioniText.get(posizioniLabel).get(1));
                    Dispatcher.forwardEvent(EventoEvents.PreviousText, posizioniText.get(posizioniLabel).get(0));
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

        eventoFormRiepilogo.clear();
        eventoFormAcquisto.clear();
        eventoFormConferma.clear();


        if (posizioniLabel == 1) {
            return;
        }



        for (TabItem item : sitoWebTab.getItems()) {

        }
    }

    public DettaglioModel riepilogo() {
        //DettaglioModel eventoModel = sitoWebForm.getDettaglioModel();


        //eventoModel.setProgettoDiCompensazioneModel(eventoFormAcquisto.getProgettoDiCompensazioneModel());

        //return eventoModel;
    return null;
    }

    public void restore(DettaglioModel eventoModel) {
        //sitoWebForm.setDettaglioModel(eventoModel);

        eventoFormAcquisto.setProgettoDiCompensazione(eventoModel.getProgettoDiCompensazioneModel());
    }



    public void setEventoRiepilogoInStore(List<RiepilogoModel> eventoRiepilogoModels) {
        DettaglioModel riepilogo = riepilogo();
        Esito esito = Esito.IN_PAGAMENTO;
        if (riepilogo.getPagamentoModel() != null &&
                riepilogo.getPagamentoModel().getEsito() != null) {
            esito = Esito.valueOf(riepilogo.getPagamentoModel().getEsito());
        }
        eventoFormRiepilogo.setEventoRiepilogoInStore(eventoRiepilogoModels, esito);
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
            
        }
        for (TabItem item : sitoWebTab.getItems()) {

        }
        
        Dispatcher.forwardEvent(EventoEvents.Riepilogo);
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
        Dispatcher.forwardEvent(EventoEvents.NextText, posizioniText.get(posizioniLabel).get(1));
        Dispatcher.forwardEvent(EventoEvents.PreviousText, posizioniText.get(posizioniLabel).get(0));}


}
