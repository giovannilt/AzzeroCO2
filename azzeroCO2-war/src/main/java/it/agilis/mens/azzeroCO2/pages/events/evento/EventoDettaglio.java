package it.agilis.mens.azzeroCO2.pages.events.evento;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.layout.CenterLayout;
import com.google.gwt.user.client.Element;

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
        setLayout(new CenterLayout());


        ContentPanel panel = new ContentPanel();
        panel.setHeaderVisible(false);

        TabPanel eventoTab = new TabPanel();


        TabItem dettagli = new TabItem();
        dettagli.setText("dettagli");
        TabItem calcolo = new TabItem();
        TabItem riepilogo = new TabItem();
        TabItem acquisto = new TabItem();
        TabItem conferma = new TabItem();


        eventoTab.add(dettagli);
        eventoTab.add(calcolo);
        eventoTab.add(riepilogo);
        eventoTab.add(acquisto);
        eventoTab.add(conferma);

        panel.add(eventoTab);
        add(panel);

    }
}
