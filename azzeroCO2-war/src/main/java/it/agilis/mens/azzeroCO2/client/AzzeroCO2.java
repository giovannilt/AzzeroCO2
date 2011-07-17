package it.agilis.mens.azzeroCO2.client;

import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.google.gwt.core.client.EntryPoint;
import it.agilis.mens.azzeroCO2.client.mvc.controllers.*;
import it.agilis.mens.azzeroCO2.client.mvc.events.AzzeroCO2Events;


public class AzzeroCO2 implements EntryPoint {

    public void onModuleLoad() {
        Dispatcher dispatcher = Dispatcher.get();

        dispatcher.addController(new AzzeroCO2Controller());

        dispatcher.addController(new LoginController());
        dispatcher.addController(new CentralController());
        dispatcher.addController(new AmministrazioneController());
        dispatcher.addController(new EventoController());
        dispatcher.addController(new UnAnnoDiAttivitaController());
        dispatcher.addController(new ConosciCO2Controller());
        dispatcher.addController(new NorthController());

        dispatcher.dispatch(AzzeroCO2Events.Init);
        dispatcher.dispatch(AzzeroCO2Events.UIReady);

    }
}
