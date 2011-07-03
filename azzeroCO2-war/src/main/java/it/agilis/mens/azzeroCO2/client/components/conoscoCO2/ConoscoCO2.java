package it.agilis.mens.azzeroCO2.client.components.conoscoCO2;

import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.widget.*;
import com.extjs.gxt.ui.client.widget.layout.CardLayout;
import com.extjs.gxt.ui.client.widget.layout.HBoxLayout;
import com.google.gwt.user.client.Element;
import it.agilis.mens.azzeroCO2.client.forms.*;

/**
 * Created by IntelliJ IDEA.
 * User: serenadimaida
 * Date: 03/07/11
 * Time: 00.50
 * To change this template use File | Settings | File Templates.
 */
public class ConoscoCO2 extends LayoutContainer{

    private final TabPanel conoscoCO2Tab = new TabPanel();

    private final ConoscoCO2Form conoscoCO2Form = new ConoscoCO2Form();


    //private final ContentPanel conoscoCO2Panel = new ContentPanel();

    private final EventoFormRiepilogo eventoFormRiepilogo = new EventoFormRiepilogo();
    private final EventoFormAcquisto  eventoFormAcquisto  = new EventoFormAcquisto();

    @Override
    protected void onRender(Element target, int index) {
        super.onRender(target, index);

        HBoxLayout layout = new HBoxLayout();
        layout.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.STRETCH);
        setLayout(layout);


        // TODO:.... CAPIRE COME CAZZO SI FA A STRETCH COME DIO COMANDA
        conoscoCO2Tab.setSize(855, 637);

        TabItem conoscoCO2 = new TabItem("Conosco la CO2");
        conoscoCO2.add(conoscoCO2Form);
        conoscoCO2Tab.add(conoscoCO2);


        TabItem riepilogo = new TabItem("Riepilogo");
        riepilogo.add(eventoFormRiepilogo);
        conoscoCO2Tab.add(riepilogo);

        TabItem acquisto = new TabItem("Acquisto");
        acquisto.add(eventoFormAcquisto);
        conoscoCO2Tab.add(acquisto);

        TabItem conferma = new TabItem("Conferma");
        conoscoCO2Tab.add(conferma);


        add(conoscoCO2Tab);

    }


    public void previusTab(AppEvent event) {
        for(int i= conoscoCO2Tab.getItems().size()-1; i>=0; i--){
            TabItem item= conoscoCO2Tab.getItems().get(i);

            if (conoscoCO2Tab.getSelectedItem().getText().equalsIgnoreCase(item.getText())) {
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
                                conoscoCO2Tab.getItems().get(i-1).setEnabled(true);
                                conoscoCO2Tab.setSelection(conoscoCO2Tab.getItems().get(i-1));
                                return;
                            }
                        }
                    }
                } else {
                    if (i > 0 ) {
                        item.setEnabled(false);
                        conoscoCO2Tab.getItems().get(i-1).setEnabled(true);
                        conoscoCO2Tab.setSelection(conoscoCO2Tab.getItems().get(i-1));
                        return;
                    }
                }
            }

        }
    }

    public void nextTab(AppEvent event) {
        int i = 0;
        for (TabItem item : conoscoCO2Tab.getItems()) {
            i++;
            if (conoscoCO2Tab.getSelectedItem().getText().equalsIgnoreCase(item.getText())) {
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
                                conoscoCO2Tab.getItems().get(i).setEnabled(true);
                                conoscoCO2Tab.setSelection(conoscoCO2Tab.getItems().get(i));
                                return;
                            }
                        }
                    }
                } else {
                    if (i < conoscoCO2Tab.getItems().size()) {
                        item.setEnabled(false);
                        conoscoCO2Tab.getItems().get(i).setEnabled(true);
                        conoscoCO2Tab.setSelection(conoscoCO2Tab.getItems().get(i));
                        return;
                    }
                }
            }

        }

    }







}
