package it.agilis.mens.azzeroCO2.client.components.eventi.evento;

import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.widget.*;
import com.extjs.gxt.ui.client.widget.layout.CardLayout;
import com.extjs.gxt.ui.client.widget.layout.HBoxLayout;
import com.google.gwt.user.client.Element;
import it.agilis.mens.azzeroCO2.client.forms.*;


/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 6/19/11
 * Time: 12:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class EventoDettaglio extends LayoutContainer {

    private final TabPanel eventoTab = new TabPanel();

    private final EventoFormDettaglio formDettaglio = new EventoFormDettaglio();

    private final ContentPanel calcoloCardPanel = new ContentPanel();
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

        HBoxLayout layout = new HBoxLayout();
        layout.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.STRETCH);
        setLayout(layout);

        calcoloCardPanel.setSize(855, 632);
        // TODO:.... CAPIRE COME CAZZO SI FA A STRETCH COME DIO COMANDA
        eventoTab.setSize(855, 637);

        TabItem dettaglio = new TabItem("Dettaglio");
        dettaglio.add(formDettaglio);
        eventoTab.add(dettaglio);

        TabItem calcolo = new TabItem("Calcolo");
        createCalcoloTabs();
        calcolo.add(calcoloCardPanel);
        calcolo.setEnabled(false);
        eventoTab.add(calcolo);

        TabItem riepilogo = new TabItem("Riepilogo");
        riepilogo.add(eventoFormRiepilogo);
        riepilogo.setEnabled(false);
        eventoTab.add(riepilogo);

        TabItem acquisto = new TabItem("Acquisto");
        acquisto.add(eventoFormAcquisto);
        acquisto.setEnabled(false);
        eventoTab.add(acquisto);

        TabItem conferma = new TabItem("Conferma");
        conferma.add(eventoFormConferma);
        conferma.setEnabled(false);
        eventoTab.add(conferma);

        add(eventoTab);
    }


    public void createCalcoloTabs() {
        formEnergia.setTitle("Energia");

        final CardLayout layout = new CardLayout();
        calcoloCardPanel.setLayout(layout);
        calcoloCardPanel.setHeaderVisible(false);

        formEnergia.setTitle("formEnergia");
        calcoloCardPanel.add(formEnergia);

        formTrasportoPersone.setTitle("formTrasportoPersone");
        calcoloCardPanel.add(formTrasportoPersone);
        formPernottamenti.setTitle("formPernottamenti");
        calcoloCardPanel.add(formPernottamenti);
        formTrasportoMerci.setTitle("formTrasportoMerci");
        calcoloCardPanel.add(formTrasportoMerci);
        formPubblicazioniRilegate.setTitle("formPubblicazioniRilegate");
        calcoloCardPanel.add(formPubblicazioniRilegate);
        formManifestiPiegevoliFogli.setTitle("formManifestiPiegevoliFogli");
        calcoloCardPanel.add(formManifestiPiegevoliFogli);

    }

    public void previusTab(AppEvent event) {
        for(int i= eventoTab.getItems().size()-1; i>=0; i--){
            TabItem item= eventoTab.getItems().get(i);

            if (eventoTab.getSelectedItem().getText().equalsIgnoreCase(item.getText())) {
                if (item.getText().equalsIgnoreCase("Calcolo")) {
                    ContentPanel calcolo =(ContentPanel)item.getItem(0);
                    CardLayout layout =(CardLayout)calcolo.getLayout();

                    for (int j= calcolo.getItems().size()-1; j>=0; j--) {
                        Component subItem= calcolo.getItems().get(j);
                        if(layout.getActiveItem().getTitle().equalsIgnoreCase(subItem.getTitle())) {
                            if(j > 0 ){
                                layout.setActiveItem(calcolo.getItem(j-1));
                                return;
                            }else{
                                item.setEnabled(false);
                                eventoTab.getItems().get(i-1).setEnabled(true);
                                eventoTab.setSelection(eventoTab.getItems().get(i-1));
                                return;
                            }
                        }
                    }
                } else {
                    if (i > 0 ) {
                        item.setEnabled(false);
                        eventoTab.getItems().get(i-1).setEnabled(true);
                        eventoTab.setSelection(eventoTab.getItems().get(i-1));
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
                    ContentPanel calcolo =(ContentPanel)item.getItem(0);
                    CardLayout layout =(CardLayout)calcolo.getLayout();

                    int j=0;
                    for (Component subItem : calcolo.getItems()) {
                        j++;
                        if(layout.getActiveItem().getTitle().equalsIgnoreCase(subItem.getTitle())) {
                            if(j< calcolo.getItems().size()){
                                layout.setActiveItem(calcolo.getItem(j));
                                return;
                            }else{
                                item.setEnabled(false);
                                eventoTab.getItems().get(i).setEnabled(true);
                                eventoTab.setSelection(eventoTab.getItems().get(i));
                                return;
                            }
                        }
                    }
                } else {
                    if (i < eventoTab.getItems().size()) {
                        item.setEnabled(false);
                        eventoTab.getItems().get(i).setEnabled(true);
                        eventoTab.setSelection(eventoTab.getItems().get(i));
                        return;
                    }
                }
            }

        }

    }
}