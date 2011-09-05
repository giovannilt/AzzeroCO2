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
