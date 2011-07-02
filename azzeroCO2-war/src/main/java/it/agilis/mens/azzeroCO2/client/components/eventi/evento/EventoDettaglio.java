package it.agilis.mens.azzeroCO2.client.components.eventi.evento;
//package com.extjs.gxt.samples.client.examples.forms;


import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
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

    private final TabPanel calcoloTabs = new TabPanel();
    private final EventoFormEnergia formEnergia = new EventoFormEnergia();
    private final EventoFormTrasportoPersone formTrasportoPersone = new EventoFormTrasportoPersone();
    private final EventoFormPernottamenti formPernottamenti = new EventoFormPernottamenti();
    private final EventoFormPubblicazioniRilegate formTrasportoMerci = new EventoFormPubblicazioniRilegate();
    private final EventoFormManifestiPieghevoliFogli formManifestiPiegevoliFogli = new EventoFormManifestiPieghevoliFogli();

    @Override
    protected void onRender(Element target, int index) {
        super.onRender(target, index);

        HBoxLayout layout = new HBoxLayout();
        layout.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.STRETCH);
        setLayout(layout);

        calcoloTabs.setSize(850, 630);
        // TODO:.... CAPIRE COME CAZZO SI FA A STRETCH COME DIO COMANDA
        eventoTab.setSize(855, 637);

        TabItem dettaglio = new TabItem("Dettaglio");
        dettaglio.add(formDettaglio);
        eventoTab.add(dettaglio);

        TabItem calcolo = new TabItem("Calcolo");
        createCalcoloTabs();
        calcolo.add(calcoloTabs);
        eventoTab.add(calcolo);

        TabItem riepilogo = new TabItem("Riepilogo");
        eventoTab.add(riepilogo);

        TabItem acquisto = new TabItem("Acquisto");
        eventoTab.add(acquisto);

        TabItem conferma = new TabItem("Conferma");
        eventoTab.add(conferma);
        add(eventoTab);

    }


    public void createCalcoloTabs() {
        formEnergia.setTitle("Energia");
        calcoloTabs.add(formEnergia);
        calcoloTabs.add(formTrasportoPersone);
        calcoloTabs.add(formPernottamenti);
        calcoloTabs.add(formTrasportoMerci);
        calcoloTabs.add(formManifestiPiegevoliFogli);

    }
}