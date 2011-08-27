package it.agilis.mens.azzeroCO2.client.services;

import com.extjs.gxt.ui.client.store.ListStore;
import it.agilis.mens.azzeroCO2.shared.model.RiepilogoModel;
import it.agilis.mens.azzeroCO2.shared.model.evento.DettaglioModel;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 8/27/11
 * Time: 7:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class CalcoliHelper {

     public static ListStore<RiepilogoModel> geListOfRiepilogoModel(DettaglioModel eventoModel) {
         ListStore<RiepilogoModel> store= new ListStore<RiepilogoModel>();

         eventoModel.getEnergiaModel();

         return store;

    }
}
