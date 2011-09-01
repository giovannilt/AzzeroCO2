package it.agilis.mens.azzeroCO2.client.services;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.widget.Info;
import com.google.gwt.user.client.rpc.AsyncCallback;
import it.agilis.mens.azzeroCO2.shared.dto.CoefficientiDTO;
import it.agilis.mens.azzeroCO2.shared.model.RiepilogoModel;
import it.agilis.mens.azzeroCO2.shared.model.evento.DettaglioModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 8/27/11
 * Time: 7:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class CalcoliHelper {

    private static HustonServiceAsync hustonService = Registry.get(AzzeroCO2Constants.HUSTON_SERVICE);
    private static Map<String, CoefficientiDTO> coefficienti = null;

    public static List<RiepilogoModel> geListOfRiepilogoModel(DettaglioModel eventoModel) {
        if (coefficienti == null) {
            getCoefficienti();
        }


        List<RiepilogoModel> store = new ArrayList<RiepilogoModel>();

        store.add(getEnergia());

        return store;

    }

    private static RiepilogoModel getEnergia() {
        RiepilogoModel energia = new RiepilogoModel();

        // eventoModel.getEnergiaModel().
        CoefficientiDTO coefficientiEnergiaDTO = coefficienti.get("energia");
        // TODO Fare il puro CALCOLO

        coefficientiEnergiaDTO.getCodice();


        return energia;
    }

    private static void getCoefficienti() {
        AsyncCallback<Map<String, CoefficientiDTO>> aCallback = new AsyncCallback<Map<String, CoefficientiDTO>>() {
            public void onFailure(Throwable caught) {
                Info.display("Error", "Errore impossibile connettersi al server");
            }

            @Override
            public void onSuccess(Map<String, CoefficientiDTO> result) {
                if (result != null) {
                    coefficienti = result;
                } else {
                    Info.display("Error", "Errore impossibile connettersi al server");
                }
            }
        };
        hustonService.getCoefficienti(aCallback);

    }
}
