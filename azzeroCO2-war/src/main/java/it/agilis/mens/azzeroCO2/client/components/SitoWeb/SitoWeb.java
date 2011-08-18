package it.agilis.mens.azzeroCO2.client.components.SitoWeb;

import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.layout.HBoxLayout;
import com.google.gwt.user.client.Element;
import it.agilis.mens.azzeroCO2.client.forms.ConoscoCO2Form;
import it.agilis.mens.azzeroCO2.client.forms.SitoWebForm;
import it.agilis.mens.azzeroCO2.client.forms.evento.EventoFormAcquisto;
import it.agilis.mens.azzeroCO2.client.forms.evento.EventoFormConferma;
import it.agilis.mens.azzeroCO2.client.forms.evento.EventoFormRiepilogo;

/**
 * Created by IntelliJ IDEA.
 * User: serenadimaida
 * Date: 03/07/11
 * Time: 00.50
 * To change this template use File | Settings | File Templates.
 */
public class SitoWeb extends LayoutContainer {

    private final TabPanel sitoWebTab = new TabPanel();

    private final SitoWebForm sitoWebForm = new SitoWebForm();

    private final EventoFormRiepilogo eventoFormRiepilogo = new EventoFormRiepilogo();
    private final EventoFormConferma eventoFormConferma= new EventoFormConferma();
    private final EventoFormAcquisto eventoFormAcquisto = new EventoFormAcquisto();

    @Override
    protected void onRender(Element target, int index) {
        super.onRender(target, index);

        HBoxLayout layout = new HBoxLayout();
        layout.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.STRETCH);
        setLayout(layout);


        // TODO:.... CAPIRE COME CAZZO SI FA A STRETCH COME DIO COMANDA
        sitoWebTab.setSize(855, 637);

        TabItem sitoWeb = new TabItem("Sito Web");
        sitoWeb.add(sitoWebForm);
        sitoWebTab.add(sitoWeb);


        TabItem riepilogo = new TabItem("Riepilogo");
        riepilogo.setEnabled(false);
        riepilogo.add(eventoFormRiepilogo);
        sitoWebTab.add(riepilogo);

        TabItem acquisto = new TabItem("Acquisto");
        acquisto.setEnabled(false);
        acquisto.add(eventoFormAcquisto);
        sitoWebTab.add(acquisto);

        TabItem conferma = new TabItem("Conferma");
        conferma.setEnabled(false);
        conferma.add(eventoFormConferma);
        sitoWebTab.add(conferma);
        add(sitoWebTab);

    }


    public void previusTab(AppEvent event) {
        for (int i = sitoWebTab.getItems().size() - 1; i >= 0; i--) {
            TabItem item = sitoWebTab.getItems().get(i);
            if (sitoWebTab.getSelectedItem().getText().equalsIgnoreCase(item.getText())) {
                if (i > 0) {
                    item.setEnabled(false);
                    sitoWebTab.getItems().get(i - 1).setEnabled(true);
                    sitoWebTab.setSelection(sitoWebTab.getItems().get(i - 1));
                    return;
                }
            }
        }
    }

    public void nextTab(AppEvent event) {
        int i = 0;
        for (TabItem item : sitoWebTab.getItems()) {
            i++;
            if (sitoWebTab.getSelectedItem().getText().equalsIgnoreCase(item.getText())) {
                if (i < sitoWebTab.getItems().size()) {
                    item.setEnabled(false);
                    sitoWebTab.getItems().get(i).setEnabled(true);
                    sitoWebTab.setSelection(sitoWebTab.getItems().get(i));
                    return;
                }
            }

        }

    }


}
