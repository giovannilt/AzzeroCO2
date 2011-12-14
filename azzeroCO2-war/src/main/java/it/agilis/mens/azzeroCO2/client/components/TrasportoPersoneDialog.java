package it.agilis.mens.azzeroCO2.client.components;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.button.Button;

/**
 * Created by IntelliJ IDEA.
 * User: serenadimaida
 * Date: 14/12/11
 * Time: 18:07
 * To change this template use File | Settings | File Templates.
 */
public class TrasportoPersoneDialog extends Dialog   {
protected Button cancel;

        public TrasportoPersoneDialog() {

            setHeading("Trasporto persone");
           // setButtons(Dialog.YESNO);
            setBodyStyleName("pad-text");
            addText("Treno: si fa riferimento alle emissioni, ripartite per passeggero, dell’intero ciclo di vita del mezzo, delle rotaie e di tutti i componenti del sistema ferroviario. Le emissioni prodotte variano per tipo di treno e in base all’energia utilizzata per la sua costruzione e per la trazione.</br></br>Aereo: si fa riferimento alle emissioni, ripartite per passeggero, dell’intero ciclo di vita del mezzo e di tutti i componenti del sistema di trasporto aereo. Le emissioni prodotte variano per tipo di aereo e in base all’energia utilizzata per la sua costruzione e trazione.</br></br><<Auto, moto, autobus: si fa riferimento alle emissioni, ripartite per passeggero, di tutto il ciclo di vita del mezzo, delle strade e di tutte le componenti del trasporto su strada.");
            //getItem(0).getFocusSupport().setIgnore(true);
            setModal(true);

        }



        @Override
        protected void createButtons() {



            cancel = new Button("chiudi");
            cancel.addSelectionListener(new SelectionListener<ButtonEvent>() {
                public void componentSelected(ButtonEvent ce) {
                    hide();
                }
            });

            addButton(cancel);
        }

}
