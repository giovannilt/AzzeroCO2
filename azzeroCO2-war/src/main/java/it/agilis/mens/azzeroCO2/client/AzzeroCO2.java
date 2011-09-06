package it.agilis.mens.azzeroCO2.client;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import it.agilis.mens.azzeroCO2.client.mvc.controllers.*;
import it.agilis.mens.azzeroCO2.client.mvc.events.AzzeroCO2Events;
import it.agilis.mens.azzeroCO2.client.services.AzzeroCO2Constants;
import it.agilis.mens.azzeroCO2.client.services.HustonService;


public class AzzeroCO2 implements EntryPoint {

    public void onModuleLoad() {
        Registry.register(AzzeroCO2Constants.HUSTON_SERVICE, GWT.<Object>create(HustonService.class));

        Dispatcher dispatcher = Dispatcher.get();

        dispatcher.addController(new AzzeroCO2Controller());

        dispatcher.addController(new LoginController());
        dispatcher.addController(new PagamentoController());
        dispatcher.addController(new RegisterController());
        dispatcher.addController(new CentralController());
        dispatcher.addController(new AmministrazioneController());
        dispatcher.addController(new EventoController());
        dispatcher.addController(new UnAnnoDiAttivitaController());
        dispatcher.addController(new ConosciCO2Controller());
        dispatcher.addController(new SitoWebController());
        dispatcher.addController(new NorthController());

        dispatcher.dispatch(AzzeroCO2Events.Init);
        dispatcher.dispatch(AzzeroCO2Events.UIReady);

    }
}
