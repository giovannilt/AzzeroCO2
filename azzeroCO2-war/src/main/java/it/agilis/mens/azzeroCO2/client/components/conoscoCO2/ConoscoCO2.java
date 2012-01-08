package it.agilis.mens.azzeroCO2.client.components.conoscoCO2;

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
import it.agilis.mens.azzeroCO2.client.forms.FormConoscoCO2;
import it.agilis.mens.azzeroCO2.client.forms.FormRiepilogo;
import it.agilis.mens.azzeroCO2.client.mvc.events.ConoscoCO2Events;
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
public class ConoscoCO2 extends LayoutContainer {
    private final TabPanel conoscoCO2Tab = new TabPanel();
    private final FormConoscoCO2 conoscoCO2Form = new FormConoscoCO2();

    private final FormRiepilogo formRiepilogo = new FormRiepilogo();
    private final FormAcquisto eventoFormAcquisto = new FormAcquisto();
    private final FormConferma formConferma = new FormConferma();
    private static int posizioniLabel = 0;
    private List<List<String>> posizioniText = new ArrayList<List<String>>();
    private UserInfoModel userInfoModel;
    private AppEvent event;

    @Override
    protected void onRender(Element target, int index) {
        super.onRender(target, index);

        setLayout(new RowLayout(Style.Orientation.HORIZONTAL));

        TabItem conoscoCO2 = new TabItem("conosco la CO2");
        conoscoCO2.setLayout(new BorderLayout());
        conoscoCO2.add(conoscoCO2Form, new BorderLayoutData(Style.LayoutRegion.CENTER));

        conoscoCO2Tab.add(conoscoCO2);


        TabItem riepilogo = new TabItem("riepilogo");
        riepilogo.add(formRiepilogo, new BorderLayoutData(Style.LayoutRegion.CENTER));
        riepilogo.setEnabled(false);
        conoscoCO2Tab.add(riepilogo);

        TabItem acquisto = new TabItem("scegli progetto di compensazione");
        acquisto.add(eventoFormAcquisto, new BorderLayoutData(Style.LayoutRegion.CENTER));
        acquisto.setEnabled(false);
        conoscoCO2Tab.add(acquisto);

        TabItem conferma = new TabItem("conferma");
        conferma.add(formConferma, new BorderLayoutData(Style.LayoutRegion.CENTER));
        conferma.setEnabled(false);
        conoscoCO2Tab.add(conferma);

        add(conoscoCO2Tab, new RowData(1, 1));

        posizioniText.add(Arrays.asList("", "Riepilogo"));                                   // DETTAGLIO
        posizioniText.add(Arrays.asList("Conosco la CO2", "Scegli progetto di compensazione"));       // RIEPILOGO
        posizioniText.add(Arrays.asList("Riepilogo", "Vai al pagamento"));                         // ACQUISTO
        posizioniText.add(Arrays.asList("", "torna alla home"));                                  // CONFERMA
    }


    public String previusTab() {
        for (int i = conoscoCO2Tab.getItems().size() - 1; i >= 0; i--) {
            TabItem item = conoscoCO2Tab.getItems().get(i);

            if (conoscoCO2Tab.getSelectedItem().getText().equalsIgnoreCase(item.getText())) {

                if (i > 0) {
                    item.setEnabled(false);
                    conoscoCO2Tab.getItems().get(i - 1).setEnabled(true);
                    conoscoCO2Tab.setSelection(conoscoCO2Tab.getItems().get(i - 1));
                    posizioniLabel--;
                    //  OrdineModel riepilogo = riepilogo();
                    Dispatcher.forwardEvent(ConoscoCO2Events.NextText, posizioniText.get(posizioniLabel).get(1));
                    Dispatcher.forwardEvent(ConoscoCO2Events.PreviousText, posizioniText.get(posizioniLabel).get(0));
                    return conoscoCO2Tab.getSelectedItem().getTitle();
                }
            }
        }
        return "";
    }

    public String nextTab() {
        int i = 0;
        for (TabItem item : conoscoCO2Tab.getItems()) {
            i++;
            if (conoscoCO2Tab.getSelectedItem().getText().equalsIgnoreCase(item.getText())) {
                if (i < conoscoCO2Tab.getItems().size()) {
                    if (conoscoCO2Tab.getItems().get(i).getText().equalsIgnoreCase("Conferma")) {
                        Dispatcher.forwardEvent(ConoscoCO2Events.Conferma);
                        return conoscoCO2Tab.getItems().get(i).getText();
                    }
                    if (conoscoCO2Tab.getItems().get(i).getText().equalsIgnoreCase("Scegli progetto di compensazione")) {
                        Dispatcher.forwardEvent(ConoscoCO2Events.Acquisto);
                    }
                    if (conoscoCO2Tab.getItems().get(i).getText().equalsIgnoreCase("Vai al pagamento")) {
                        if (userInfoModel.getProfilo() == Profile.Guest.ordinal()) {
                            return conoscoCO2Tab.getItems().get(i).getText();
                        }
                    }
                    if (conoscoCO2Tab.getItems().get(i).getText().equalsIgnoreCase("Riepilogo")) {
                        Dispatcher.forwardEvent(ConoscoCO2Events.Riepilogo);
                    }
                    item.setEnabled(false);
                    conoscoCO2Tab.getItems().get(i).setEnabled(true);
                    conoscoCO2Tab.setSelection(conoscoCO2Tab.getItems().get(i));

                    posizioniLabel++;

                    Dispatcher.forwardEvent(ConoscoCO2Events.NextText, posizioniText.get(posizioniLabel).get(1));
                    Dispatcher.forwardEvent(ConoscoCO2Events.PreviousText, posizioniText.get(posizioniLabel).get(0));
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
    }

    public OrdineModel riepilogo() {
        OrdineModel model = new OrdineModel();

        model.setNome("ConoscoCO2");

        model.setConoscoCO2Model(conoscoCO2Form.getConoscoCO2Model());

        model.setProgettoDiCompensazioneModel(eventoFormAcquisto.getProgettoDiCompensazioneModel());

        return model;
    }


    public void setProgettiDiCompensazione(List<ProgettoDiCompensazioneModel> progettiDiCompensazioneList) {
        eventoFormAcquisto.setInStore(progettiDiCompensazioneList);
    }

    public void setUserInfoModel(UserInfoModel userInfoModel) {
        this.userInfoModel = userInfoModel;
    }

    public void showConferma(DettaglioVTO result) {
        conoscoCO2Tab.getSelectedItem().disable();
        posizioniLabel++;
        conoscoCO2Tab.getItems().get(conoscoCO2Tab.getItems().size() - 1).setEnabled(true);
        conoscoCO2Tab.setSelection(conoscoCO2Tab.getItems().get(conoscoCO2Tab.getItems().size() - 1));
        Dispatcher.forwardEvent(ConoscoCO2Events.NextText, posizioniText.get(posizioniLabel).get(1));
        Dispatcher.forwardEvent(ConoscoCO2Events.PreviousText, posizioniText.get(posizioniLabel).get(0));
    }

    public void setConoscoCO2RiepilogoInStore(List<RiepilogoModel> riepilogoModels) {
        OrdineModel riepilogo = riepilogo();
        Esito esito = Esito.IN_PAGAMENTO;
        if (riepilogo.getPagamentoModel() != null &&
                riepilogo.getPagamentoModel().getEsito() != null) {
            esito = Esito.valueOf(riepilogo.getPagamentoModel().getEsito());
        }
        formRiepilogo.setRiepilogoInStore(riepilogoModels, esito);
        eventoFormAcquisto.setRiepilogo(riepilogoModels, riepilogo);
    }
}
