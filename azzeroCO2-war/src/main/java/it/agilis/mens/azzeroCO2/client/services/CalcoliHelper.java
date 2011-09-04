package it.agilis.mens.azzeroCO2.client.services;

import com.extjs.gxt.ui.client.Registry;
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

    private static HustonServiceAsync hustonService = Registry.get(AzzeroCO2Constants.HUSTON_SERVICE);
    private static Map<String, CoefficienteModel> coefficienti = null;

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


        return store;
    }

    private static RiepilogoModel getEnergia(EnergiaModel energiaModel) {
        RiepilogoModel energia = new RiepilogoModel();

        energia.setOggetto("Energia");
        String energia1 = "";
        String energia2 = "";
        String energia3 = "";

        CoefficienteModel coefficienteModelEnergia = coefficienti.get("ENEELE");
        CoefficienteModel coefficientiEnergiaGAS = coefficienti.get("ENEGAS");
        CoefficienteModel coefficienteModelGasolio = coefficienti.get("ENEGSL");

        double co2 = 0;
        if (energiaModel.getEnergiaElettrica() != null && energiaModel.getEnergiaElettrica() > 0) {
            energia1 = "Energia elettrica" + " " + energiaModel.getEnergiaElettrica() + " kw/h </br>";
            co2 = energiaModel.getEnergiaElettrica() * coefficienteModelEnergia.getValore();
        }
        if (energiaModel.getGasMetano() != null && energiaModel.getGasMetano() > 0) {
            energia2 = "Gas" + " " + energiaModel.getEnergiaElettrica() + " metri cubi  </br>";
            co2 += energiaModel.getGasMetano() * coefficientiEnergiaGAS.getValore();
        }
        if (energiaModel.getGasolio() != null && energiaModel.getGasolio() > 0) {
            energia3 = "Gasolio" + " " + energiaModel.getEnergiaElettrica() + " litri </br>";
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
        if (nottiModel.getNotti() != null && nottiModel.getNotti() > 0) {
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
            String tp50Bus = "";
            String tp50Auto = "";
            String tp50Treno = "";
            String tp50 = "";

            String tp100Bus = "";
            String tp100Auto = "";
            String tp100Treno = "";
            String tp100 = "";

            String tp250Bus = "";
            String tp250Auto = "";
            String tp250Treno = "";
            String tp250Aereo = "";
            String tp250 = "";

            String tp500Bus = "";
            String tp500Auto = "";
            String tp500Treno = "";
            String tp500Aereo = "";
            String tp500 = "";

            if (tpm.getBusPiu50() > 0) {
                tp50Bus = "Bus " + tpm.getBusPiu50() + " tratte </br>";
            }
            if (tpm.getAutoPiu50() > 0) {
                tp50Auto = "Auto " + tpm.getAutoPiu50() + " tratte </br>";
            }
            if (tpm.getTrenoPiu50() > 0) {
                tp50Treno = "Treno " + tpm.getTrenoPiu50() + " tratte </br>";
            }
            if (tpm.getBusPiu50() + tpm.getAutoPiu50() + tpm.getTrenoPiu50() > 0) {
                tp50 = "Distanza 50 km:</br>" + tp50Bus + tp50Auto + tp50Treno;
            }

            if (tpm.getBusPiu100() > 0) {
                tp100Bus = "Bus " + tpm.getBusPiu100() + " tratte </br>";
            }
            if (tpm.getAutoPiu100() > 0) {
                tp100Auto = "Auto " + tpm.getAutoPiu100() + " tratte </br>";
            }
            if (tpm.getTrenoPiu100() > 0) {
                tp100Treno = "Treno " + tpm.getTrenoPiu100() + " tratte </br>";
            }
            if (tpm.getBusPiu100() + tpm.getAutoPiu100() + tpm.getTrenoPiu100() > 0) {
                tp100 = "Distanza 100 km:</br>" + tp100Bus + tp100Auto + tp100Treno;
            }

            if (tpm.getBusPiu250() > 0) {
                tp250Bus = "Bus " + tpm.getBusPiu250() + " tratte </br>";
            }
            if (tpm.getAutoPiu250() > 0) {
                tp250Auto = "Auto " + tpm.getAutoPiu250() + " tratte </br>";
            }
            if (tpm.getTrenoPiu250() > 0) {
                tp250Treno = "Treno " + tpm.getTrenoPiu250() + " tratte </br>";
            }
            if (tpm.getAereoPiu250() > 0) {
                tp250Aereo = "Aereo " + tpm.getAereoPiu250() + " tratte </br>";
            }
            if (tpm.getBusPiu250() + tpm.getAutoPiu250() + tpm.getTrenoPiu250() + tpm.getAereoPiu250() > 0) {
                tp250 = "Distanza 250 km:</br>" + tp250Bus + tp250Auto + tp250Treno + tp250Aereo;
            }

            if (tpm.getBusPiu500() > 0) {
                tp500Bus = "Bus " + tpm.getBusPiu500() + " tratte </br>";
            }
            if (tpm.getAutoPiu500() > 0) {
                tp500Auto = "Auto " + tpm.getAutoPiu500() + " tratte </br>";
            }
            if (tpm.getTrenoPiu500() > 0) {
                tp500Treno = "Treno " + tpm.getTrenoPiu500() + " tratte </br>";
            }
            if (tpm.getAereoPiu500() > 0) {
                tp500Aereo = "Aereo " + tpm.getAereoPiu500() + " tratte </br>";
            }
            if (tpm.getBusPiu500() + tpm.getAutoPiu500() + tpm.getTrenoPiu500() + tpm.getAereoPiu500() > 0) {
                tp500 = "Distanza 500 km:</br>" + tp500Bus + tp500Auto + tp500Treno + tp500Aereo;
            }


            CoefficienteModel coefficienteModelTPBUS = coefficienti.get("TRPBUS");
            CoefficienteModel coefficienteModelTPAUTO = coefficienti.get("TRPAUT");
            CoefficienteModel coefficienteModelTPTRENO = coefficienti.get("TRPTRE");
            CoefficienteModel coefficienteModelTPAEREO = coefficienti.get("TRPAER");

            Double co2 = tpm.getBusPiu50() * coefficienteModelTPBUS.getValore() * 50;
            co2 += tpm.getBusPiu100() * coefficienteModelTPBUS.getValore() * 100;
            co2 += tpm.getBusPiu250() * coefficienteModelTPBUS.getValore() * 250;
            co2 += tpm.getBusPiu500() * coefficienteModelTPBUS.getValore() * 500;

            co2 += tpm.getAutoPiu50() * coefficienteModelTPAUTO.getValore() * 50;
            co2 += tpm.getAutoPiu100() * coefficienteModelTPAUTO.getValore() * 100;
            co2 += tpm.getAutoPiu250() * coefficienteModelTPAUTO.getValore() * 250;
            co2 += tpm.getAutoPiu500() * coefficienteModelTPAUTO.getValore() * 500;

            co2 += tpm.getTrenoPiu50() * coefficienteModelTPTRENO.getValore() * 50;
            co2 += tpm.getTrenoPiu100() * coefficienteModelTPTRENO.getValore() * 100;
            co2 += tpm.getTrenoPiu250() * coefficienteModelTPTRENO.getValore() * 250;
            co2 += tpm.getTrenoPiu500() * coefficienteModelTPTRENO.getValore() * 500;

            co2 += tpm.getAereoPiu250() * coefficienteModelTPAEREO.getValore() * 250;
            co2 += tpm.getAereoPiu500() * coefficienteModelTPAEREO.getValore() * 500;

            if (co2 > 0) {
                 _rm.setDettagli(tp50 +"</br>"+ tp100 +"</br>"+ tp250 +"</br>"+ tp500);
                _rm.setKgCO2(co2);
                _return.add(_rm);
            }
        }
        return _return;
    }


    private static List<RiepilogoModel> getPubblRil(List<PubblicazioniRilegateModel> pubblRilModel) {
        List<RiepilogoModel> _return = new ArrayList<RiepilogoModel>();

        for (PubblicazioniRilegateModel prm : pubblRilModel) {
            RiepilogoModel _rm = new RiepilogoModel();

            double co2 = 0;
            double co2Copertina = 0;

            String formato = "";
            if (prm.getLarghezza() != null && prm.getAltezza() != null) {
                formato = "Dimensioni " + prm.getLarghezza() + "x" + prm.getAltezza() + "</br>";
                co2 = prm.getAltezza() / 100 * prm.getLarghezza() / 100;
                co2Copertina = (prm.getAltezza() / 100 * prm.getLarghezza() / 100) * 2;
            }
            String pagine = "";
            if (prm.getNumeroDiPagine() != null) {
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
            if (prm.getGrammatura() != null) {
                materiale += " " + prm.getGrammatura() + " gr</br>";
                co2 *= prm.getGrammatura();
            }
            String tiratura = "";
            if (prm.getTiratura() != null) {
                tiratura = "Tiratura " + prm.getTiratura();
                co2 *= prm.getTiratura();
                co2Copertina *= prm.getTiratura();
            }
            String copertina;  // TODO.?.
            if (prm.getTipoDiCartaCopertina() != null) {
                if (coefficienti.get(prm.getTipoDiCartaCopertina().getParametro()) != null) {
                    co2Copertina *= coefficienti.get(prm.getTipoDiCartaCopertina().getParametro()).getValore();
                }
            }
            if (prm.getGrammaturaCopertina() != null) {
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
            if (prm.getLarghezza() != null && prm.getAltezza() != null) {
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
            if (prm.getGrammatura() != null) {
                materiale += " " + prm.getGrammatura() + " gr</br>";
                co2 *= prm.getGrammatura();
            }
            String pagine = "";
            if (prm.getNumeroDiPagine() != null) {
                pagine = "Numero di pagine: " + prm.getNumeroDiPagine() + "</br>";
                co2 *= prm.getNumeroDiPagine();
            }
            String tiratura = "";
            if (prm.getTiratura() != null) {
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
