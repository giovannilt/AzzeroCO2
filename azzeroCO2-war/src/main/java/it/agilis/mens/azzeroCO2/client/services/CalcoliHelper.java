package it.agilis.mens.azzeroCO2.client.services;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.widget.Info;
import com.google.gwt.user.client.rpc.AsyncCallback;
import it.agilis.mens.azzeroCO2.shared.model.CoefficienteModel;
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
    private static Map<String, CoefficienteModel> coefficienti = null;

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
        String energia1;
        String energia2;
        String energia3;
        if(energiaModel.getEnergiaElettrica()>0) { energia1="Energia elettrica" + " " + energiaModel.getEnergiaElettrica() + " kw/h"+"\n";} else { energia1="";}
        if(energiaModel.getEnergiaElettrica()>0) { energia2="Energia elettrica" + " " + energiaModel.getEnergiaElettrica() + " kw/h"+"\n";} else { energia2="";}
        if(energiaModel.getEnergiaElettrica()>0) { energia3="Energia elettrica" + " " + energiaModel.getEnergiaElettrica() + " kw/h"+"\n";} else { energia3="";}


        String energiaDett = energia1+energia2+energia3;
        //        +"Gas" + " " + energiaModel.getGasMetano() + " m3" +
        //        "Gasolio" + " " + energiaModel.getGasolio() + " lt";
        //energia.setDettagli(energiaDett);

        CoefficienteModel coefficienteModelEnergia = coefficienti.get("energiaElettrica");
        CoefficienteModel coefficientiEnergiaGAS= coefficienti.get("energiaGas");
        CoefficienteModel coefficienteModelGasolio = coefficienti.get("energiaGasolio");

        Double co2 = energiaModel.getEnergiaElettrica() * coefficienteModelEnergia.getValore();
        co2 += energiaModel.getGasMetano() * coefficientiEnergiaGAS.getValore();
        co2 += energiaModel.getGasolio() * coefficienteModelGasolio.getValore();
        energia.setKgCO2(co2);

        if (co2 > 0) {
            return energia;
        } else {
            return null;
        }
    }


    private static RiepilogoModel getNotti(NottiModel nottiModel) {
        RiepilogoModel notti = new RiepilogoModel();

        notti.setOggetto("Pernottamenti");
        String energiaDett = "Pernottamenti" + " " + nottiModel.getNotti() + " notti";
        notti.setDettagli(energiaDett);
        CoefficienteModel coefficienteModel = coefficienti.get("notti");
        Double co2 = nottiModel.getNotti() * coefficienteModel.getValore();
        notti.setKgCO2(co2);

        if (co2 > 0) {
            return notti;
        } else {
            return null;
        }
    }




    private static List<RiepilogoModel> getTrasportoPersone(List<TrasportoPersoneModel> trasportoPersoneModels) {
        List<RiepilogoModel> _return = new ArrayList<RiepilogoModel>();


        for (TrasportoPersoneModel tpm : trasportoPersoneModels) {
            RiepilogoModel _rm = new RiepilogoModel();


            _rm.setOggetto("Trasporto Persone / " +tpm.getCategoria());
            String tp50Bus;
            String tp50Auto;
            String tp50Treno;
            String tp50;

            String tp100Bus;
            String tp100Auto;
            String tp100Treno;
            String tp100;

            String tp250Bus;
            String tp250Auto;
            String tp250Treno;
            String tp250Aereo;
            String tp250;

            String tp500Bus;
            String tp500Auto;
            String tp500Treno;
            String tp500Aereo;
            String tp500;

            if(tpm.getBusPiu50()>0) { tp50Bus="Bus " +  tpm.getBusPiu50() + " tratte"+"\n";} else { tp50Bus="";}
            if(tpm.getAutoPiu50()>0) { tp50Auto="Auto " +  tpm.getAutoPiu50() + " tratte"+"\n";} else { tp50Auto="";}
            if(tpm.getTrenoPiu50()>0) { tp50Treno="Treno " +  tpm.getTrenoPiu50() + " tratte"+"\n";} else { tp50Treno="";}
            if(tpm.getBusPiu50()+tpm.getAutoPiu50()+tpm.getTrenoPiu50()>0) {tp50="Distanza 50 km:"+"\n"+tp50Bus+tp50Auto+tp50Treno;;} else{tp50="";}

            if(tpm.getBusPiu100()>0) { tp100Bus="Bus " +  tpm.getBusPiu100() + " tratte"+"\n";} else { tp100Bus="";}
            if(tpm.getAutoPiu100()>0) { tp100Auto="Auto " +  tpm.getAutoPiu100() + " tratte"+"\n";} else { tp100Auto="";}
            if(tpm.getTrenoPiu100()>0) { tp100Treno="Treno " +  tpm.getTrenoPiu100() + " tratte"+"\n";} else { tp100Treno="";}
            if(tpm.getBusPiu100()+tpm.getAutoPiu100()+tpm.getTrenoPiu100()>0) {tp100="Distanza 100 km:"+"\n"+tp100Bus+tp100Auto+tp100Treno;;} else{tp100="";}

            if(tpm.getBusPiu250()>0) { tp250Bus="Bus " +  tpm.getBusPiu250() + " tratte"+"\n";} else { tp250Bus="";}
            if(tpm.getAutoPiu250()>0) { tp250Auto="Auto " +  tpm.getAutoPiu250() + " tratte"+"\n";} else { tp250Auto="";}
            if(tpm.getTrenoPiu250()>0) { tp250Treno="Treno " +  tpm.getTrenoPiu250() + " tratte"+"\n";} else { tp250Treno="";}
            if(tpm.getAereoPiu250()>0) { tp250Aereo="Aereo " +  tpm.getAereoPiu250() + " tratte"+"\n";} else { tp250Aereo="";}
            if(tpm.getBusPiu250()+tpm.getAutoPiu250()+tpm.getTrenoPiu250()+tpm.getAereoPiu250()>0) {tp250="Distanza 250 km:"+"\n"+tp250Bus+tp250Auto+tp250Treno+tp250Aereo;;} else{tp250="";}

            if(tpm.getBusPiu500()>0) { tp500Bus="Bus " +  tpm.getBusPiu500() + " tratte"+"\n";} else { tp500Bus="";}
            if(tpm.getAutoPiu500()>0) { tp500Auto="Auto " +  tpm.getAutoPiu500() + " tratte"+"\n";} else { tp500Auto="";}
            if(tpm.getTrenoPiu500()>0) { tp500Treno="Treno " +  tpm.getTrenoPiu500() + " tratte"+"\n";} else { tp500Treno="";}
            if(tpm.getAereoPiu500()>0) { tp500Aereo="Aereo " +  tpm.getAereoPiu500() + " tratte"+"\n";} else { tp500Aereo="";}
            if(tpm.getBusPiu500()+tpm.getAutoPiu500()+tpm.getTrenoPiu500()+tpm.getAereoPiu500()>0) {tp500="Distanza 500 km:"+"\n"+tp500Bus+tp500Auto+tp500Treno+tp500Aereo;;} else{tp500="";}


            _rm.setDettagli(tp50+tp100+tp250+tp500);




            CoefficienteModel coefficienteModelTPBUS = coefficienti.get("tpbus");
            CoefficienteModel coefficienteModelTPAUTO= coefficienti.get("tpauto");
            CoefficienteModel coefficienteModelTPTRENO = coefficienti.get("tptreno");
            CoefficienteModel coefficienteModelTPAEREO = coefficienti.get("tpaereo");

            Double co2 = tpm.getBusPiu50() * coefficienteModelTPBUS.getValore()*50;
            co2 += tpm.getBusPiu100() * coefficienteModelTPBUS.getValore()*100;
            co2 += tpm.getBusPiu250() * coefficienteModelTPBUS.getValore()*250;
            co2 += tpm.getBusPiu500() * coefficienteModelTPBUS.getValore()*500;

            co2 += tpm.getAutoPiu50() * coefficienteModelTPAUTO.getValore()*50;
            co2 += tpm.getAutoPiu100() * coefficienteModelTPAUTO.getValore()*100;
            co2 += tpm.getAutoPiu250() * coefficienteModelTPAUTO.getValore()*250;
            co2 += tpm.getAutoPiu500() * coefficienteModelTPAUTO.getValore()*500;

            co2 += tpm.getTrenoPiu50() * coefficienteModelTPTRENO.getValore()*50;
            co2 += tpm.getTrenoPiu100() * coefficienteModelTPTRENO.getValore()*100;
            co2 += tpm.getTrenoPiu250() * coefficienteModelTPTRENO.getValore()*250;
            co2 += tpm.getTrenoPiu500() * coefficienteModelTPTRENO.getValore()*500;

            co2 += tpm.getAereoPiu250() * coefficienteModelTPAEREO.getValore()*250;
            co2 += tpm.getAereoPiu500() * coefficienteModelTPAEREO.getValore()*500;


            _rm.setKgCO2(co2);

            if (co2>0) {_return.add(_rm);} else{return  null;}
        }

        return _return;
    }

    private static void getCoefficienti() {
        AsyncCallback<Map<String, CoefficienteModel>> aCallback = new AsyncCallback<Map<String, CoefficienteModel>>() {
            public void onFailure(Throwable caught) {
                Info.display("Error", "Errore impossibile connettersi al server");
            }

            @Override
            public void onSuccess(Map<String, CoefficienteModel> result) {
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
