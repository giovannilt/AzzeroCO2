package it.agilis.mens.azzeroCO2.client;

import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.google.gwt.core.client.EntryPoint;
import it.agilis.mens.azzeroCO2.client.mvc.controllers.LoginController;
import it.agilis.mens.azzeroCO2.client.mvc.controllers.AzzeroCO2Controller;
import it.agilis.mens.azzeroCO2.client.mvc.controllers.EventoController;
import it.agilis.mens.azzeroCO2.client.mvc.events.AzzeroCO2Events;


public class AzzeroCO2 implements EntryPoint {
	
    public void onModuleLoad() {
        Dispatcher dispatcher = Dispatcher.get();
		
        dispatcher.addController(new AzzeroCO2Controller());
		
        dispatcher.addController(new LoginController());
        dispatcher.addController(new EventoController());
		
        dispatcher.dispatch(AzzeroCO2Events.Init);
        dispatcher.dispatch(AzzeroCO2Events.UIReady);
		
    }
}
