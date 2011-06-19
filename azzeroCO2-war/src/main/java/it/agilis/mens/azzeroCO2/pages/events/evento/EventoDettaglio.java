package it.agilis.mens.azzeroCO2.pages.events.evento;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.layout.HBoxLayout;
import com.google.gwt.user.client.Element;
<<<<<<< HEAD
import it.agilis.mens.azzeroCO2.pages.events.evento.tabs.EventoItemCalcolo;
=======
>>>>>>> origin/master
import it.agilis.mens.azzeroCO2.pages.events.evento.tabs.EventoItemDettaglio;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 6/19/11
 * Time: 12:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class EventoDettaglio extends LayoutContainer {

    @Override
    protected void onRender(Element target, int index) {
        super.onRender(target, index);

        HBoxLayout layout = new HBoxLayout();
        layout.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.STRETCH);
        setLayout(layout);

        ContentPanel panel = new ContentPanel();
        panel.setHeaderVisible(false);

        TabPanel eventoTab = new TabPanel();
        eventoTab.setResizeTabs(true);
        eventoTab.setAnimScroll(true);
        eventoTab.setTabScroll(true);
        eventoTab.setDeferredRender(true);
        eventoTab.setAutoWidth(true);

        // TODO:.... CAPIRE COME CAZZO SI FA A STRETCH COME DIO COMANDA
        eventoTab.setSize(855, 637);

<<<<<<< HEAD
=======
        TabItem dettagli = new TabItem();
        dettagli.setText("Dettagli");
        TabItem calcolo = new TabItem();
        calcolo.setText("Calcolo");

>>>>>>> origin/master
        TabItem riepilogo = new TabItem();
        riepilogo.setText("Riepilogo");
        TabItem acquisto = new TabItem();
        acquisto.setText("Acquisto");
        TabItem conferma = new TabItem();
        conferma.setText("Conferma");

<<<<<<< HEAD
        TabItem dettaglio= new EventoItemDettaglio();
        dettaglio.setText("Dettaglio");
        eventoTab.add(dettaglio);
        TabItem calcolo = new EventoItemCalcolo();
        calcolo.setText("Calcolo");
=======
        eventoTab.add(new EventoItemDettaglio());
>>>>>>> origin/master
        eventoTab.add(calcolo);


        eventoTab.add(riepilogo);
        eventoTab.add(acquisto);
        eventoTab.add(conferma);

        panel.add(eventoTab);
        add(panel);

    }
}
