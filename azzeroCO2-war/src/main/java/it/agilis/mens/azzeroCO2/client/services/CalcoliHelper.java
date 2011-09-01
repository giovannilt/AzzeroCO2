package it.agilis.mens.azzeroCO2.client.services;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.widget.Info;
import com.google.gwt.user.client.rpc.AsyncCallback;
import it.agilis.mens.azzeroCO2.shared.dto.CoefficientiDTO;
import it.agilis.mens.azzeroCO2.shared.model.RiepilogoModel;
import it.agilis.mens.azzeroCO2.shared.model.evento.DettaglioModel;
import it.agilis.mens.azzeroCO2.shared.model.evento.EnergiaModel;
import it.agilis.mens.azzeroCO2.shared.model.evento.NottiModel;
import it.agilis.mens.azzeroCO2.shared.model.evento.TrasportoPersoneModel;

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

        //Caloclo ENERGIA
        RiepilogoModel model = getEnergia(eventoModel.getEnergiaModel());
        if (model == null) {
            return null;
        } else {
            store.add(model);
        }

        model = getNotti(eventoModel.getNottiModel());
        if (model == null) {
            return null;
        } else {
            store.add(model);
        }
        store.addAll(getTrasportoPersone(eventoModel.getTrasportoPersoneModel()));

        return store;

    }

    private static RiepilogoModel getEnergia(EnergiaModel energiaModel) {
        RiepilogoModel energia = new RiepilogoModel();

        energia.setOggetto("Energia");

        String energiaDett = "Energia elettrica" + " " + energiaModel.getEnergiaElettrica() + " kw/h" +
                "Gas" + " " + energiaModel.getGasMetano() + " m3" +
                "Gasolio" + " " + energiaModel.getGasolio() + " lt";
        energia.setDettagli(energiaDett);

        CoefficientiDTO coefficientiEnergiaElettricaDTO = coefficienti.get("energiaElettrica");
        CoefficientiDTO coefficientiEnergiaGASDTO = coefficienti.get("energiaGas");
        CoefficientiDTO coefficientiEnergiaGasolioDTO = coefficienti.get("energiaGasolio");

        Double co2 = energiaModel.getEnergiaElettrica() * coefficientiEnergiaElettricaDTO.getValore();
        co2 += energiaModel.getGasMetano() * coefficientiEnergiaGASDTO.getValore();
        co2 += energiaModel.getGasolio() * coefficientiEnergiaGasolioDTO.getValore();
        energia.setKgCO2(co2);

        if (co2 == 0) {
            return null;
        } else {
            return energia;
        }
    }


    private static RiepilogoModel getNotti(NottiModel nottiModel) {
        RiepilogoModel notti = new RiepilogoModel();

        notti.setOggetto("Pernottamenti");
        String energiaDett = "Pernottamenti" + " " + nottiModel.getNotti() + " notti";
        notti.setDettagli(energiaDett);
        CoefficientiDTO coefficientiNottiDTO = coefficienti.get("notti");
        Double co2 = nottiModel.getNotti() * coefficientiNottiDTO.getValore();
        notti.setKgCO2(co2);

        if (co2 == 0) {
            return null;
        } else {
            return notti;
        }
    }

    private static List<RiepilogoModel> getTrasportoPersone(List<TrasportoPersoneModel> trasportoPersoneModels) {
        List<RiepilogoModel> _return = new ArrayList<RiepilogoModel>();
        for (TrasportoPersoneModel tpm : trasportoPersoneModels) {
            RiepilogoModel _rm = new RiepilogoModel();

             //TODO....
             _rm.setOggetto("Trasporto Persone / " +tpm.getCategoria());
             _rm.setDettagli("STI CAZZI");



            _return.add(_rm);
        }

        return _return;
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
