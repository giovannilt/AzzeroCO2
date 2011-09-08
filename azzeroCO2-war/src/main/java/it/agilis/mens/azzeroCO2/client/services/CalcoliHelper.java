package it.agilis.mens.azzeroCO2.client.services;

import it.agilis.mens.azzeroCO2.shared.model.RiepilogoModel;
import it.agilis.mens.azzeroCO2.shared.model.amministrazione.CoefficienteModel;
import it.agilis.mens.azzeroCO2.shared.model.evento.*;

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
     private static Map<String, CoefficienteModel> coefficienti = null;

    public static List<RiepilogoModel> getListOfRiepilogoModelLazy(DettaglioModel eventoModel) {
        List<RiepilogoModel> store = new ArrayList<RiepilogoModel>();
        RiepilogoModel model = null;
        if (eventoModel != null && eventoModel.getEnergiaModel() != null) {
            if ( eventoModel.getEnergiaModel().getEnergiaElettrica() != 0
                    ||  eventoModel.getEnergiaModel().getGasMetano() != 0
                    ||  eventoModel.getEnergiaModel().getGasolio() != 0) {
                model = new RiepilogoModel();
                model.setDettagli("Energia");
                model.setOggetto("Energia");
                store.add(model);
            }
        }
        if (eventoModel != null && eventoModel.getTrasportoPersoneModel() != null && eventoModel.getTrasportoPersoneModel().size() > 0) {
            model = new RiepilogoModel();
            String string = "Trasporto Persone <br>";
            for (TrasportoPersoneModel mf : eventoModel.getTrasportoPersoneModel()) {
                string += "  / " + mf.getCategoria() + "<br>";
            }
            model.setDettagli("Trasporto Persone");
            model.setOggetto(string);
            store.add(model);
        }
        if (eventoModel != null && eventoModel.getNottiModel() != null) {
            if (eventoModel.getNottiModel().getNotti() > 0) {
                model = new RiepilogoModel();
                model.setDettagli("Notti");
                model.setOggetto("Notti");
                store.add(model);
            }
        }

        if (eventoModel != null && eventoModel.getTrasportoMerciModel() != null) {
            if (!eventoModel.getTrasportoMerciModel().isVoid()) {
                model = new RiepilogoModel();
                model.setDettagli("Trasporto Merci");
                model.setOggetto("Trasporto Merci");
                store.add(model);
            }
        }
        if (eventoModel != null && eventoModel.getPubblicazioniRilegateModel() != null && eventoModel.getPubblicazioniRilegateModel().size() > 0) {
            model = new RiepilogoModel();
            String string = "Pubblicazioni Rilegate <br>";
            for (PubblicazioniRilegateModel mf : eventoModel.getPubblicazioniRilegateModel()) {
                if (!mf.isVoid()) {
                    string += "  / " + mf.getCategoria() + "<br>";
                }
            }
            if (!string.equalsIgnoreCase("Pubblicazioni Rilegate <br>")) {
                model.setDettagli("Pubblicazioni Rilegate");
                model.setOggetto(string);
                store.add(model);
            }
        }

        if (eventoModel != null && eventoModel.getManifestiPieghevoliFogliModel() != null && eventoModel.getManifestiPieghevoliFogliModel().size() > 0) {
            model = new RiepilogoModel();
            String manifesti = "Manifesti Pieghevoli <br>";
            for (ManifestiPieghevoliFogliModel mf : eventoModel.getManifestiPieghevoliFogliModel()) {
                if (!mf.isVoid()) {
                    manifesti += "  / " + mf.getCategoria() + "<br>";
                }
            }
            if (!manifesti.equalsIgnoreCase("Manifesti Pieghevoli <br>")) {
                model.setDettagli("Manifesti Pieghevoli");
                model.setOggetto(manifesti);
                store.add(model);
            }
        }
        return store;
    }

    public static List<RiepilogoModel> geListOfRiepilogoModel(DettaglioModel eventoModel, Map<String, CoefficienteModel> c) {
        coefficienti = c;
        List<RiepilogoModel> store = new ArrayList<RiepilogoModel>();

        //Caloclo ENERGIA
        RiepilogoModel model = null;
        if (eventoModel != null && eventoModel.getEnergiaModel() != null) {
            model = getEnergia(eventoModel.getEnergiaModel());
            if (model != null) {
                store.add(model);
            }
        }
        if (eventoModel != null && eventoModel.getNottiModel() != null) {
            model = getNotti(eventoModel.getNottiModel());
            if (model != null) {
                store.add(model);
            }
        }
        store.addAll(getPubblNonRil(eventoModel.getManifestiPieghevoliFogliModel()));
        store.addAll(getPubblRil(eventoModel.getPubblicazioniRilegateModel()));

        store.addAll(getTrasportoPersone(eventoModel.getTrasportoPersoneModel()));

        model= getTrasportoMerci(eventoModel.getTrasportoMerciModel());
        if(model!=null){
            store.add(model);
        }

        return store;
    }

    private static RiepilogoModel getEnergia(EnergiaModel energiaModel) {
        RiepilogoModel energia = new RiepilogoModel();

        energia.setOggetto("Energia");
        String energia1 = "";
        String energia2 = "";
        String energia3 = "";

        CoefficienteModel coefficienteModelEnergia = coefficienti.get("ENEELE");
        CoefficienteModel coefficientiEnergiaGAS =   coefficienti.get("ENEGAS");
        CoefficienteModel coefficienteModelGasolio = coefficienti.get("ENEGSL");

        double co2 = 0;
        if ( energiaModel.getEnergiaElettrica() > 0) {
            energia1 = "Energia elettrica" + " " + energiaModel.getEnergiaElettrica() + " kw/h </br>";
            co2 = energiaModel.getEnergiaElettrica() * coefficienteModelEnergia.getValore();
        }
        if ( energiaModel.getGasMetano() > 0) {
            energia2 = "Gas" + " " + energiaModel.getGasMetano() + " metri cubi  </br>";
            co2 += energiaModel.getGasMetano() * coefficientiEnergiaGAS.getValore();
        }
        if (energiaModel.getGasolio() > 0) {
            energia3 = "Gasolio" + " " + energiaModel.getGasolio() + " litri </br>";
            co2 += energiaModel.getGasolio() * coefficienteModelGasolio.getValore();
        }
        energia.setDettagli(energia1 + energia2 + energia3);

        if (co2 > 0) {
            energia.setKgCO2(co2);
            return energia;
        }
        return null;
    }

    private static RiepilogoModel getNotti(NottiModel nottiModel) {
        RiepilogoModel notti = new RiepilogoModel();
        notti.setOggetto("Pernottamenti");
        Double co2 = -1.0;
        if (nottiModel.getNotti() > 0) {
            String energiaDett = nottiModel.getNotti() + " notti";
            notti.setDettagli(energiaDett);
            CoefficienteModel coefficienteModel = coefficienti.get("PERNOT");
            co2 = nottiModel.getNotti() * coefficienteModel.getValore();
            notti.setKgCO2(co2);
        }

        if (co2 > 0) {
            return notti;
        }
        return null;
    }

    private static List<RiepilogoModel> getTrasportoPersone(List<TrasportoPersoneModel> trasportoPersoneModels) {
        List<RiepilogoModel> _return = new ArrayList<RiepilogoModel>();
        for (TrasportoPersoneModel tpm : trasportoPersoneModels) {
            RiepilogoModel _rm = new RiepilogoModel();
            _rm.setOggetto("Trasporto Persone / " + tpm.getCategoria());
            String tp60Bus = "";
            String tp60Auto = "";
            String tp60Treno = "";
            String tp60Moto = "";
            String tp60 = "";

            String tp300Bus = "";
            String tp300Auto = "";
            String tp300Treno = "";
            String tp300Moto = "";
            String tp300 = "";

            String tp1000Bus = "";
            String tp1000Auto = "";
            String tp1000Treno = "";
            String tp1000Aereo = "";
            String tp1000 = "";

            String tp3000Bus = "";
            String tp3000Auto = "";
            String tp3000Treno = "";
            String tp3000Aereo = "";
            String tp3000 = "";

            String tp9000Bus = "";
            String tp9000Auto = "";
            String tp9000Treno = "";
            String tp9000Aereo = "";
            String tp9000 = "";

            if (tpm.getBusKm60() > 0) {
                tp60Bus = "Bus " + tpm.getBusKm60() + " tratte </br>";
            }
            if (tpm.getAutoKm60() > 0) {
                tp60Auto = "Auto " + tpm.getAutoKm60() + " tratte </br>";
            }
            if (tpm.getTrenoKm60() > 0) {
                tp60Treno = "Treno " + tpm.getTrenoKm60() + " tratte </br>";
            }
            if (tpm.getMotoKm60() > 0) {
                tp60Moto = "Moto " + tpm.getMotoKm60() + " tratte </br>";
            }
            if (tpm.getBusKm60() + tpm.getAutoKm60() + tpm.getTrenoKm60() > 0) {
                tp60 = "Distanza Provinciale:</br>" + tp60Bus + tp60Auto + tp60Treno + tp60Moto;
            }

            if (tpm.getBusKm300() > 0) {
                tp300Bus = "Bus " + tpm.getBusKm300() + " tratte </br>";
            }
            if (tpm.getAutoKm300() > 0) {
                tp300Auto = "Auto " + tpm.getAutoKm300() + " tratte </br>";
            }
            if (tpm.getTrenoKm300() > 0) {
                tp300Treno = "Treno " + tpm.getTrenoKm300() + " tratte </br>";
            }
            if (tpm.getMotoKm300() > 0) {
                tp300Moto = "Moto " + tpm.getMotoKm300() + " tratte </br>";
            }
            if (tpm.getBusKm300() + tpm.getAutoKm300() + tpm.getTrenoKm300() > 0) {
                tp300 = "Distanza Regionale:</br>" + tp300Bus + tp300Auto + tp300Treno + tp300Moto;
            }

            if (tpm.getBusKm1000() > 0) {
                tp1000Bus = "Bus " + tpm.getBusKm1000() + " tratte </br>";
            }
            if (tpm.getAutoKm1000() > 0) {
                tp1000Auto = "Auto " + tpm.getAutoKm1000() + " tratte </br>";
            }
            if (tpm.getTrenoKm1000() > 0) {
                tp1000Treno = "Treno " + tpm.getTrenoKm1000() + " tratte </br>";
            }
            if (tpm.getAereoKm1000() > 0) {
                tp1000Aereo = "Aereo " + tpm.getAereoKm1000() + " tratte </br>";
            }
            if (tpm.getBusKm1000() + tpm.getAutoKm1000() + tpm.getTrenoKm1000() + tpm.getAereoKm1000() > 0) {
                tp1000 = "Distanza Nazionale:</br>" + tp1000Bus + tp1000Auto + tp1000Treno + tp1000Aereo;
            }

            if (tpm.getBusKm3000() > 0) {
                tp3000Bus = "Bus " + tpm.getBusKm3000() + " tratte </br>";
            }
            if (tpm.getAutoKm300() > 0) {
                tp3000Auto = "Auto " + tpm.getAutoKm3000() + " tratte </br>";
            }
            if (tpm.getTrenoKm300() > 0) {
                tp3000Treno = "Treno " + tpm.getTrenoKm3000() + " tratte </br>";
            }
            if (tpm.getMotoKm300() > 0) {
                tp3000Aereo = "Moto " + tpm.getAereoKm3000() + " tratte </br>";
            }
            if (tpm.getBusKm3000() + tpm.getAutoKm3000() + tpm.getTrenoKm3000() > 0) {
                tp3000 = "Distanza Regionale:</br>" + tp3000Bus + tp3000Auto + tp3000Treno + tp3000Aereo;
            }

            if (tpm.getBusKm9000() > 0) {
                tp9000Bus = "Bus " + tpm.getBusKm9000() + " tratte </br>";
            }
            if (tpm.getAutoKm9000() > 0) {
                tp9000Auto = "Auto " + tpm.getAutoKm9000() + " tratte </br>";
            }
            if (tpm.getTrenoKm9000() > 0) {
                tp9000Treno = "Treno " + tpm.getTrenoKm9000() + " tratte </br>";
            }
            if (tpm.getAereoKm9000() > 0) {
                tp9000Aereo = "Aereo " + tpm.getAereoKm9000() + " tratte </br>";
            }
            if (tpm.getBusKm9000() + tpm.getAutoKm9000() + tpm.getTrenoKm9000() + tpm.getAereoKm9000() > 0) {
                tp9000 = "Distanza europea:</br>" + tp9000Bus + tp9000Auto + tp9000Treno + tp9000Aereo;
            }


            CoefficienteModel coefficienteModelTPBUS = coefficienti.get("TRPBUS");
            CoefficienteModel coefficienteModelTPAUTO = coefficienti.get("TRPAUT");
            CoefficienteModel coefficienteModelTPTRENO = coefficienti.get("TRPTRE");
            CoefficienteModel coefficienteModelTPAEREO = coefficienti.get("TRPAER");
            CoefficienteModel coefficienteModelTPAEREE = coefficienti.get("TRPAER");
            CoefficienteModel coefficienteModelTPAERMOT = coefficienti.get("TRPMOT");

            Double co2 = tpm.getBusKm60() * coefficienteModelTPBUS.getValore() * 60;
            co2 += tpm.getBusKm300() * coefficienteModelTPBUS.getValore() * 300;
            co2 += tpm.getBusKm1000() * coefficienteModelTPBUS.getValore() * 1000;
            co2 += tpm.getBusKm3000() * coefficienteModelTPBUS.getValore() * 3000;
            co2 += tpm.getBusKm9000() * coefficienteModelTPBUS.getValore() * 9000;

            co2 += tpm.getAutoKm60() * coefficienteModelTPAUTO.getValore() * 60;
            co2 += tpm.getAutoKm300() * coefficienteModelTPAUTO.getValore() * 300;
            co2 += tpm.getAutoKm1000() * coefficienteModelTPAUTO.getValore() * 1000;
            co2 += tpm.getAutoKm3000() * coefficienteModelTPAUTO.getValore() * 3000;
            co2 += tpm.getAutoKm9000() * coefficienteModelTPAUTO.getValore() * 9000;

            co2 += tpm.getTrenoKm60() * coefficienteModelTPTRENO.getValore() * 60;
            co2 += tpm.getTrenoKm300() * coefficienteModelTPTRENO.getValore() * 300;
            co2 += tpm.getTrenoKm1000() * coefficienteModelTPTRENO.getValore() * 1000;
            co2 += tpm.getTrenoKm3000() * coefficienteModelTPTRENO.getValore() * 3000;
            co2 += tpm.getTrenoKm9000() * coefficienteModelTPTRENO.getValore() * 9000;


            co2 += tpm.getMotoKm60() * coefficienteModelTPAERMOT.getValore() * 60;
            co2 += tpm.getMotoKm300() * coefficienteModelTPAERMOT.getValore() * 300;

            co2 += tpm.getAereoKm1000() * coefficienteModelTPAEREO.getValore() * 1000;
            co2 += tpm.getAereoKm3000() * coefficienteModelTPAEREO.getValore() * 3000;
            co2 += tpm.getAereoKm9000() * coefficienteModelTPAEREE.getValore() * 9000;

            if (co2 > 0) {
                _rm.setDettagli(tp60 + "</br>" + tp300 + "</br>" + tp1000 + "</br>" + tp3000 + "</br>" + tp9000);
                _rm.setKgCO2(co2);
                _return.add(_rm);
            }
        }
        return _return;
    }





    private static RiepilogoModel getTrasportoMerci(TrasportoMerciModel trasportoMerciModel) {
        RiepilogoModel traspMerci = new RiepilogoModel();

        traspMerci.setOggetto("Trasporto Merci");
        String furgone30 = "";
        String tir30 = "";
        String furgone150 = "";
        String tir150 ="";
        String treno150 ="";
        String furgone1500 = "";
        String furgone500 ="";
        String tir500 ="";
        String treno500 ="";
        String nave500 ="";

        String tir1500 ="";
        String treno1500 ="";
        String nave1500 ="";
        String aereo1500="";
        String furgone9000 = "";
        String tir9000 ="";
        String treno9000 ="";
        String nave9000 = "";
        String aereo9000 ="";


        CoefficienteModel coefficienteModelFurgone = coefficienti.get("TRMFUR");
        CoefficienteModel coefficienteModelTir =   coefficienti.get("TRMTIR");
        CoefficienteModel coefficienteModelTreno = coefficienti.get("TRMTRE");
        CoefficienteModel coefficienteModelNave = coefficienti.get("TRMNAV");
        CoefficienteModel coefficienteModelAereoEU= coefficienti.get("TRMAEU");
        CoefficienteModel coefficienteModelAereoEE = coefficienti.get("TRMAEE");


        double co2 = 0;
        if ( trasportoMerciModel.getFurgoneKm30() > 0) {
            furgone30 = "Furgone:" + " " + trasportoMerciModel.getFurgoneKm30() + " ton </br>";
            co2 = trasportoMerciModel.getFurgoneKm30() * coefficienteModelFurgone.getValore();
        }

        if ( trasportoMerciModel.getTirKm30() > 0) {
            tir30 = "Tir:" + " " + trasportoMerciModel.getTirKm30() + " ton </br>";
            co2 += trasportoMerciModel.getTirKm30() * coefficienteModelTir.getValore();
        }


        if ( trasportoMerciModel.getFurgoneKm150() > 0) {
            furgone150 = "Furgone:" + " " + trasportoMerciModel.getFurgoneKm150() + " ton </br>";
            co2 += trasportoMerciModel.getFurgoneKm150() * coefficienteModelFurgone.getValore();
        }

        if ( trasportoMerciModel.getTirKm150() > 0) {
            tir150 = "Tir:" + " " + trasportoMerciModel.getTirKm150() + " ton </br>";
            co2 += trasportoMerciModel.getTirKm150() * coefficienteModelTir.getValore();
        }
        if ( trasportoMerciModel.getTrenoKm150() > 0) {
            treno150 = "Treno:" + " " + trasportoMerciModel.getTrenoKm150() + " ton </br>";
            co2 += trasportoMerciModel.getTrenoKm150() * coefficienteModelTreno.getValore();
        }

        if ( trasportoMerciModel.getFurgoneKm500() > 0) {
            furgone500 = "Furgone:" + " " + trasportoMerciModel.getFurgoneKm500() + " ton </br>";
            co2 += trasportoMerciModel.getFurgoneKm500() * coefficienteModelFurgone.getValore();
        }

        if ( trasportoMerciModel.getTirKm500() > 0) {
            tir500 = "Tir:" + " " + trasportoMerciModel.getTirKm500() + " ton </br>";
            co2 += trasportoMerciModel.getTirKm500() * coefficienteModelTir.getValore();
        }
        if ( trasportoMerciModel.getTrenoKm500() > 0) {
            treno500 = "Tir:" + " " + trasportoMerciModel.getTrenoKm500() + " ton </br>";
            co2 += trasportoMerciModel.getTrenoKm500() * coefficienteModelTreno.getValore();
        }

        if ( trasportoMerciModel.getNaveKm500() > 0) {
            nave500 = "Tir:" + " " + trasportoMerciModel.getTirKm500() + " ton </br>";
            co2 += trasportoMerciModel.getNaveKm500() * coefficienteModelTreno.getValore();
        }



        if ( trasportoMerciModel.getFurgoneKm1500() > 0) {
            furgone1500 = "Furgone:" + " " + trasportoMerciModel.getFurgoneKm1500() + " ton </br>";
            co2 += trasportoMerciModel.getFurgoneKm1500() * coefficienteModelFurgone.getValore();
        }

        if (trasportoMerciModel.getTirKm1500() > 0) {
            tir1500 = "Tir:" + " " + trasportoMerciModel.getTirKm1500() + " ton </br>";
            co2 += trasportoMerciModel.getTirKm1500() * coefficienteModelTir.getValore();
        }
        if (trasportoMerciModel.getTrenoKm1500() > 0) {
            treno1500 = "Treno:" + " " + trasportoMerciModel.getTirKm1500() + " ton </br>";
            co2 += trasportoMerciModel.getTrenoKm1500() * coefficienteModelTreno.getValore();
        }

        if ( trasportoMerciModel.getNaveKm1500() > 0) {
            nave1500 = "Nave:" + " " + trasportoMerciModel.getTirKm1500() + " ton </br>";
            co2 += trasportoMerciModel.getNaveKm1500() * coefficienteModelNave.getValore();
        }

        if ( trasportoMerciModel.getAereoKm1500() > 0) {
            aereo1500 = "Aereo:" + " " + trasportoMerciModel.getAereoKm1500() + " ton </br>";
            co2 += trasportoMerciModel.getAereoKm1500() * coefficienteModelAereoEU.getValore();
        }


        if (trasportoMerciModel.getFurgoneKm9000() > 0) {
            furgone9000 = "Furgone:" + " " + trasportoMerciModel.getFurgoneKm9000() + " ton </br>";
            co2 += trasportoMerciModel.getFurgoneKm9000() * coefficienteModelFurgone.getValore();
        }

        if ( trasportoMerciModel.getTirKm9000() > 0) {
            tir9000 = "Tir:" + " " + trasportoMerciModel.getTirKm9000() + " ton </br>";
            co2 += trasportoMerciModel.getTirKm9000() * coefficienteModelTir.getValore();
        }
        if ( trasportoMerciModel.getTrenoKm9000() > 0) {
            treno9000 = "Treno:" + " " + trasportoMerciModel.getTirKm9000() + " ton </br>";
            co2 += trasportoMerciModel.getTrenoKm9000() * coefficienteModelTreno.getValore();
        }

        if (trasportoMerciModel.getNaveKm9000() > 0) {
            nave9000 = "Nave:" + " " + trasportoMerciModel.getTirKm9000() + " ton </br>";
            co2 += trasportoMerciModel.getNaveKm9000() * coefficienteModelNave.getValore();
        }

        if (trasportoMerciModel.getAereoKm9000() > 0) {
            aereo9000 = "Aereo:" + " " + trasportoMerciModel.getAereoKm9000() + " ton </br>";
            co2 += trasportoMerciModel.getAereoKm9000() * coefficienteModelAereoEE.getValore();
        }


        String provinciale="";
        String regionale="";
        String nazionale="";
        String europeo="";
        String extraeuropeo="";
        if(trasportoMerciModel.getFurgoneKm30()+trasportoMerciModel.getTirKm30()>0){
            provinciale="Distanza provinciale:</br>";
        }

        if(trasportoMerciModel.getFurgoneKm150()+trasportoMerciModel.getTirKm150()+trasportoMerciModel.getTirKm150()>0){
            regionale="Distanza regionale:</br>";
        }
        if(trasportoMerciModel.getFurgoneKm500()+trasportoMerciModel.getTirKm500()+trasportoMerciModel.getTrenoKm500()+trasportoMerciModel.getNaveKm500()>0){
            nazionale="Distanza nazionale:</br>";
        }
        if(trasportoMerciModel.getFurgoneKm1500()+trasportoMerciModel.getTirKm1500()+trasportoMerciModel.getTrenoKm1500()+trasportoMerciModel.getNaveKm1500()+trasportoMerciModel.getAereoKm1500()>0){
            europeo="Distanza europea:</br>";
        }
        if(trasportoMerciModel.getFurgoneKm9000()+trasportoMerciModel.getTirKm9000()+trasportoMerciModel.getTrenoKm9000()+trasportoMerciModel.getNaveKm9000()+trasportoMerciModel.getAereoKm9000()>0){
            extraeuropeo="Distanza extra europea:</br>";
        }

        traspMerci.setDettagli(provinciale+furgone30+tir30+regionale+furgone150+tir150+treno150+nazionale+furgone500+tir500+treno500+nave500+europeo+furgone1500+tir1500+treno1500+nave1500+aereo1500+extraeuropeo+furgone9000+tir9000+treno9000+nave9000+aereo9000);

        if (co2 > 0) {
            traspMerci.setKgCO2(co2);
            return traspMerci;
        }
        return null;
    }

    private static List<RiepilogoModel> getPubblRil(List<PubblicazioniRilegateModel> pubblRilModel) {
        List<RiepilogoModel> _return = new ArrayList<RiepilogoModel>();

        for (PubblicazioniRilegateModel prm : pubblRilModel) {
            RiepilogoModel _rm = new RiepilogoModel();

            double co2 = 0;
            double co2Copertina = 0;

            String formato = "";
            if (prm.getLarghezza() >0  && prm.getAltezza()> 0) {
                formato = "Dimensioni " + prm.getLarghezza() + "x" + prm.getAltezza() + "</br>";
                co2 = prm.getAltezza() / 100 * prm.getLarghezza() / 100;
                co2Copertina = (prm.getAltezza() / 100 * prm.getLarghezza() / 100) * 2;
            }
            String pagine = "";
            if (prm.getNumeroDiPagine()>0) {
                pagine = "Numero di pagine: " + prm.getNumeroDiPagine() + "</br>";
                co2 *= prm.getNumeroDiPagine();
            }
            String materiale = "";
            if (prm.getTipoDiCarta() != null) {
                if (coefficienti.get(prm.getTipoDiCarta().getParametro()) != null) {
                    co2 *= coefficienti.get(prm.getTipoDiCarta().getParametro()).getValore();
                }
                materiale = prm.getTipoDiCarta().getNome();
            }
            if (prm.getGrammatura() >0) {
                materiale += " " + prm.getGrammatura() + " gr</br>";
                co2 *= prm.getGrammatura();
            }
            String tiratura = "";
            if (prm.getTiratura() >0) {
                tiratura = "Tiratura " + prm.getTiratura();
                co2 *= prm.getTiratura();
                co2Copertina *= prm.getTiratura();
            }
            if (prm.getTipoDiCartaCopertina() != null) {
                if (coefficienti.get(prm.getTipoDiCartaCopertina().getParametro()) != null) {
                    co2Copertina *= coefficienti.get(prm.getTipoDiCartaCopertina().getParametro()).getValore();
                }
            }
            if (prm.getGrammaturaCopertina() >0) {
                co2Copertina *= prm.getGrammaturaCopertina();
            }

            if (co2 + co2Copertina > 0) {
                _rm.setKgCO2(co2 + co2Copertina);
                _rm.setOggetto("Pubblicazioni rilegate / " + prm.getCategoria());
                _rm.setDettagli(formato + "</br>" + materiale + "</br>" + pagine + "</br>" + tiratura);
                _return.add(_rm);
            }
        }
        return _return;
    }

    private static List<RiepilogoModel> getPubblNonRil(List<ManifestiPieghevoliFogliModel> pubblNonRilModel) {
        List<RiepilogoModel> _return = new ArrayList<RiepilogoModel>();
        for (ManifestiPieghevoliFogliModel prm : pubblNonRilModel) {
            RiepilogoModel _rm = new RiepilogoModel();

            double co2 = 0;

            _rm.setOggetto("Manifesti, pieghevoli, fogli / " + prm.getCategoria());
            String formato = "";
            if (prm.getLarghezza() >0 && prm.getAltezza() >0) {
                formato = "Dimensioni " + prm.getLarghezza() + "x" + prm.getAltezza() + "</br>";
                co2 = prm.getAltezza() / 100 * prm.getLarghezza() / 100;
            }
            String materiale = "";
            if (prm.getTipoDiCarta() != null) {
                if (coefficienti.containsKey(prm.getTipoDiCarta().getParametro())) {
                    co2 *= coefficienti.get(prm.getTipoDiCarta().getParametro()).getValore();
                }
                materiale = prm.getTipoDiCarta().getNome();
            }
            if (prm.getGrammatura() >0) {
                materiale += " " + prm.getGrammatura() + " gr</br>";
                co2 *= prm.getGrammatura();
            }
            String pagine = "";
            if (prm.getNumeroDiPagine() >0) {
                pagine = "Numero di pagine: " + prm.getNumeroDiPagine() + "</br>";
                co2 *= prm.getNumeroDiPagine();
            }
            String tiratura = "";
            if (prm.getTiratura() >0) {
                tiratura = "Tiratura " + prm.getTiratura();
                co2 *= prm.getTiratura();
            }

            if (co2 > 0) {
                _rm.setDettagli(formato + "</br>" + materiale + "</br>" + pagine + "</br>" + tiratura);
                _rm.setKgCO2(co2);
                _return.add(_rm);
            }
        }
        return _return;
    }
}
