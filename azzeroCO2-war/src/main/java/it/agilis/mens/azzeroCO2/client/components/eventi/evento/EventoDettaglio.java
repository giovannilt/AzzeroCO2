package it.agilis.mens.azzeroCO2.client.components.eventi.evento;

import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.layout.HBoxLayout;
import com.google.gwt.user.client.Element;
import it.agilis.mens.azzeroCO2.client.components.eventi.evento.tabs.EventoItemCalcolo;
import it.agilis.mens.azzeroCO2.client.forms.EventoFormDettaglio;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 6/19/11
 * Time: 12:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class EventoDettaglio extends LayoutContainer {

    private final TabPanel eventoTab = new TabPanel();
    private final TabItem calcolo = new EventoItemCalcolo();
    private final TabItem dettaglio = new TabItem();

    private final EventoFormDettaglio formDettaglio= new EventoFormDettaglio();

    @Override
    protected void onRender(Element target, int index) {
        super.onRender(target, index);

        HBoxLayout layout = new HBoxLayout();
        layout.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.STRETCH);
        setLayout(layout);



        // TODO:.... CAPIRE COME CAZZO SI FA A STRETCH COME DIO COMANDA
        eventoTab.setSize(855, 637);

        TabItem riepilogo = new TabItem();
        riepilogo.setText("Riepilogo");
        TabItem acquisto = new TabItem();
        acquisto.setText("Acquisto");
        TabItem conferma = new TabItem();
        conferma.setText("Conferma");


        dettaglio.setText("Dettaglio");
        dettaglio.add(formDettaglio);
        eventoTab.add(dettaglio);

        calcolo.setText("Calcolo");
        eventoTab.add(calcolo);

        eventoTab.add(riepilogo);
        eventoTab.add(acquisto);
        eventoTab.add(conferma);

       add(eventoTab);


    }


    public void next() {
        eventoTab.setSelection(calcolo);
    }
}