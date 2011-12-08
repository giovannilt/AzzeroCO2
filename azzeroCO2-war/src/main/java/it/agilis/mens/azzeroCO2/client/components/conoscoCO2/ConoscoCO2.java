package it.agilis.mens.azzeroCO2.client.components.conoscoCO2;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.layout.*;
import com.google.gwt.user.client.Element;
import it.agilis.mens.azzeroCO2.client.forms.FormConoscoCO2;

import it.agilis.mens.azzeroCO2.client.forms.FormAcquisto;
import it.agilis.mens.azzeroCO2.client.forms.FormConferma;
import it.agilis.mens.azzeroCO2.client.forms.FormRiepilogo;
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
public class ConoscoCO2 extends LayoutContainer {


    private final TabPanel conoscoCO2Tab = new TabPanel();
    private final FormConoscoCO2 conoscoCO2Form = new FormConoscoCO2();


    private final FormRiepilogo formRiepilogo = new FormRiepilogo();
    private final FormAcquisto eventoFormAcquisto = new FormAcquisto();
    private final FormConferma formConferma = new FormConferma();
    private static int posizioniLabel = 1;
    private List<List<String>> posizioniText = new ArrayList<List<String>>();
    private UserInfoModel userInfoModel;
    private AppEvent event;

    @Override
    protected void onRender(Element target, int index) {
        super.onRender(target, index);

        setLayout(new RowLayout(Style.Orientation.HORIZONTAL));

        TabItem conoscoCO2 = new TabItem("Conosco la CO2");
        conoscoCO2.setLayout(new BorderLayout());
        conoscoCO2.add(conoscoCO2Form, new BorderLayoutData(Style.LayoutRegion.CENTER));

        conoscoCO2Tab.add(conoscoCO2);


        TabItem riepilogo = new TabItem("Riepilogo");
        riepilogo.add(formRiepilogo, new BorderLayoutData(Style.LayoutRegion.CENTER));
       // riepilogo.setEnabled(false);
        conoscoCO2Tab.add(riepilogo);

        TabItem acquisto = new TabItem("Scegli progetto di compensazione");
        acquisto.add(eventoFormAcquisto, new BorderLayoutData(Style.LayoutRegion.CENTER));
      //  acquisto.setEnabled(false);
        conoscoCO2Tab.add(acquisto);

        TabItem conferma = new TabItem("Conferma");
        conferma.add(formConferma, new BorderLayoutData(Style.LayoutRegion.CENTER));
      //  conferma.setEnabled(false);
        conoscoCO2Tab.add(conferma);

        add(conoscoCO2Tab, new RowData(1, 1));

        posizioniText.add(Arrays.asList("", "Riepilogo"));                                   // DETTAGLIO
        posizioniText.add(Arrays.asList("Conosco la CO2", "Scegli progetto di compensazione"));       // RIEPILOGO
        posizioniText.add(Arrays.asList("Riepilogo", "Vai al pagamento"));                         // ACQUISTO
                posizioniText.add(Arrays.asList("", "torna alla home"));                                  // CONFERMA
         }


    public String previusTab(AppEvent event) {
        for (int i = conoscoCO2Tab.getItems().size() - 1; i >= 0; i--) {
            TabItem item = conoscoCO2Tab.getItems().get(i);

            if (conoscoCO2Tab.getSelectedItem().getText().equalsIgnoreCase(item.getText())) {

                if (i > 0) {
                    item.setEnabled(false);
                    conoscoCO2Tab.getItems().get(i - 1).setEnabled(true);
                    conoscoCO2Tab.setSelection(conoscoCO2Tab.getItems().get(i - 1));
                    posizioniLabel--;
                    //  DettaglioModel riepilogo = riepilogo();
                    Dispatcher.forwardEvent(EventoEvents.NextText, posizioniText.get(posizioniLabel).get(1));
                    Dispatcher.forwardEvent(EventoEvents.PreviousText, posizioniText.get(posizioniLabel).get(0));
                    return conoscoCO2Tab.getSelectedItem().getTitle();
                }
            }
        }
        return "";
    }

    public String nextTab(AppEvent event) {
        int i = 0;
        for (TabItem item : conoscoCO2Tab.getItems()) {
            i++;
            if (conoscoCO2Tab.getSelectedItem().getText().equalsIgnoreCase(item.getText())) {

                if (i < conoscoCO2Tab.getItems().size()) {
                    if (conoscoCO2Tab.getItems().get(i).getText().equalsIgnoreCase("Conferma")) {
                        Dispatcher.forwardEvent(EventoEvents.Conferma);
                        // Dispatcher.forwardEvent(EventoEvents.SentEmailConferma);
                        return conoscoCO2Tab.getItems().get(i).getText();
                    }
                    if (conoscoCO2Tab.getItems().get(i).getText().equalsIgnoreCase("Scegli progetto di compensazione")) {
                        Dispatcher.forwardEvent(EventoEvents.Acquisto);
                        if (userInfoModel.getProfilo() == Profile.Guest.ordinal()) {
                            return conoscoCO2Tab.getItems().get(i).getText();
                        }
                    }
                    if (conoscoCO2Tab.getItems().get(i).getText().equalsIgnoreCase("Riepilogo")) {
                        Dispatcher.forwardEvent(EventoEvents.Riepilogo);
                    }

                    item.setEnabled(false);

                    conoscoCO2Tab.getItems().get(i).setEnabled(true);
                    conoscoCO2Tab.setSelection(conoscoCO2Tab.getItems().get(i));

                    posizioniLabel++;

                    if (posizioniLabel == 0) {
                        posizioniLabel++;
                    }

                    Dispatcher.forwardEvent(EventoEvents.NextText, posizioniText.get(posizioniLabel).get(1));
                    Dispatcher.forwardEvent(EventoEvents.PreviousText, posizioniText.get(posizioniLabel).get(0));
                    return conoscoCO2Tab.getItems().get(i).getText();
                }
            }
        }
        return "";
    }

    public void clearPanel() {
        conoscoCO2Form.clear();
        conoscoCO2Form.setHeight(443);
        conoscoCO2Form.setWidth(691);

        formRiepilogo.clear();
        eventoFormAcquisto.clear();
        formConferma.clear();


        if (posizioniLabel == 1) {
            return;
        }


        for (TabItem item : conoscoCO2Tab.getItems()) {

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
        formRiepilogo.setEventoRiepilogoInStore(eventoRiepilogoModels, esito);
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
        for (TabItem item : conoscoCO2Tab.getItems()) {

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
        conoscoCO2Tab.getSelectedItem().disable();
        posizioniLabel++;
        conoscoCO2Tab.getItems().get(conoscoCO2Tab.getItems().size() - 1).setEnabled(true);
        conoscoCO2Tab.setSelection(conoscoCO2Tab.getItems().get(conoscoCO2Tab.getItems().size() - 1));
        Dispatcher.forwardEvent(EventoEvents.NextText, posizioniText.get(posizioniLabel).get(1));
        Dispatcher.forwardEvent(EventoEvents.PreviousText, posizioniText.get(posizioniLabel).get(0));
    }
}
