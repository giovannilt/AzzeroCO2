package it.agilis.mens.azzeroCO2.client.components.eventi.evento;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.layout.HBoxLayout;
import com.google.gwt.user.client.Element;
import it.agilis.mens.azzeroCO2.client.components.eventi.evento.tabs.EventoItemCalcolo;
import it.agilis.mens.azzeroCO2.client.components.eventi.evento.tabs.EventoItemDettaglio;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 6/19/11
 * Time: 12:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class EventoDettaglio extends LayoutContainer {

    TabPanel eventoTab = new TabPanel();
    TabItem calcolo = new EventoItemCalcolo();
    TabItem dettaglio = new EventoItemDettaglio();

    @Override
    protected void onRender(Element target, int index) {
        super.onRender(target, index);

        HBoxLayout layout = new HBoxLayout();
        layout.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.STRETCH);
        setLayout(layout);

        ContentPanel panel = new ContentPanel();
        panel.setHeaderVisible(false);


        // TODO:.... CAPIRE COME CAZZO SI FA A STRETCH COME DIO COMANDA
        eventoTab.setSize(855, 637);

        TabItem riepilogo = new TabItem();
        riepilogo.setText("Riepilogo");
        TabItem acquisto = new TabItem();
        acquisto.setText("Acquisto");
        TabItem conferma = new TabItem();
        conferma.setText("Conferma");


        dettaglio.setText("Dettaglio");
        eventoTab.add(dettaglio);

        calcolo.setText("Calcolo");
        eventoTab.add(calcolo);

        eventoTab.add(riepilogo);
        eventoTab.add(acquisto);
        eventoTab.add(conferma);

        panel.add(eventoTab);
        add(panel);


    }


    public void next() {

        eventoTab.setSelection(calcolo);
        /*TabItem activeItem = eventoTab.getSelectedItem();
        List<TabItem> list= eventoTab.getItems();
        for(TabItem item: list ){
            if(item.getText().equalsIgnoreCase(activeItem.getText())){

            }
        }
        TabItem next =  calcolo;
        if (next != null) {
            eventoTab.setSelection(next);
        } else if (eventoTab.getItemCount() > 0) {
            eventoTab.setSelection(eventoTab.getItem(0));
        } */

    }

    public void previus() {
    }
}