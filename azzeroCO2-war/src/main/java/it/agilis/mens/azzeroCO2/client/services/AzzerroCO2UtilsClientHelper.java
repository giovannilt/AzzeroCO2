package it.agilis.mens.azzeroCO2.client.services;

import it.agilis.mens.azzeroCO2.shared.model.evento.DettaglioModel;
import it.agilis.mens.azzeroCO2.shared.model.evento.ManifestiPieghevoliFogliModel;
import it.agilis.mens.azzeroCO2.shared.model.evento.PubblicazioniRilegateModel;
import it.agilis.mens.azzeroCO2.shared.model.evento.TipoDiCartaModel;
import it.agilis.mens.azzeroCO2.shared.vto.DettaglioVTO;
import it.agilis.mens.azzeroCO2.shared.vto.ManifestiPieghevoliFogliVTO;
import it.agilis.mens.azzeroCO2.shared.vto.PubblicazioniRilegateVTO;
import it.agilis.mens.azzeroCO2.shared.vto.TipoDiCartaVTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 10/26/11
 * Time: 7:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class AzzerroCO2UtilsClientHelper {

    public static DettaglioVTO getDettaglioVTO(DettaglioModel dettaglioModel) {
        DettaglioVTO dettaglioVTO = new DettaglioVTO();

        dettaglioVTO.setNottiModel(dettaglioModel.getNottiModel());
        dettaglioVTO.setEnergiaModel(dettaglioModel.getEnergiaModel());
        dettaglioVTO.setTrasportoMerciModel(dettaglioModel.getTrasportoMerciModel());
        dettaglioVTO.setTrasportoPersoneModel(dettaglioModel.getTrasportoPersoneModel());
        dettaglioVTO.setProgettoDiCompensazioneModel(dettaglioModel.getProgettoDiCompensazioneModel());

        dettaglioVTO.setDove(dettaglioModel.getDove());
        dettaglioVTO.setFine(dettaglioModel.getFine());
        dettaglioVTO.setId(dettaglioModel.getId());
        dettaglioVTO.setInizio(dettaglioModel.getInizio());
        dettaglioVTO.setNome(dettaglioModel.getNome());
        dettaglioVTO.setNote(dettaglioModel.getNote());
        dettaglioVTO.setOrdineId(dettaglioModel.getOrdineId());


        dettaglioVTO.setManifestiPieghevoliFogliVTO(getManifestiPiegjevoliFogltioVTOList(dettaglioModel.getManifestiPieghevoliFogliModel()));
        dettaglioVTO.setPubblicazioniRilegateVTO(getPubblicazioniRilegateVTOList(dettaglioModel.getPubblicazioniRilegateModel()));

        return dettaglioVTO;

    }

    private static ArrayList<PubblicazioniRilegateVTO> getPubblicazioniRilegateVTOList(List<PubblicazioniRilegateModel> pubblicazioniRilegateModel) {
        if (pubblicazioniRilegateModel == null)
            return null;
        ArrayList<PubblicazioniRilegateVTO> _return = new ArrayList<PubblicazioniRilegateVTO>();
        for (PubblicazioniRilegateModel p : pubblicazioniRilegateModel) {
            _return.add(getPubblicazioniRilegateVTO(p));
        }
        return _return;
    }

    private static PubblicazioniRilegateVTO getPubblicazioniRilegateVTO(PubblicazioniRilegateModel p) {
        PubblicazioniRilegateVTO _return = new PubblicazioniRilegateVTO();
        _return.setAltezza(p.getAltezza());
        _return.setCategoria(p.getCategoria());
        _return.setGrammatura(p.getGrammatura());
        _return.setTiratura(p.getTiratura());

        _return.setGrammaturaCopertina(p.getGrammaturaCopertina());
        _return.setId(p.getId());
        _return.setLarghezza(p.getLarghezza());

        _return.setNumeroDiPagine(p.getNumeroDiPagine());
        _return.setTipoDiCarta(getTipoDiCartaVTO(p.getTipoDiCarta()));
        _return.setTipoDiCartaCopertina(getTipoDiCartaVTO(p.getTipoDiCartaCopertina()));

        _return.setId(p.getId());


        return _return;
    }

    private static ArrayList<ManifestiPieghevoliFogliVTO> getManifestiPiegjevoliFogltioVTOList(List<ManifestiPieghevoliFogliModel> manifestiPieghevoliFogliModel) {
        if (manifestiPieghevoliFogliModel == null)
            return null;
        ArrayList<ManifestiPieghevoliFogliVTO> _return = new ArrayList<ManifestiPieghevoliFogliVTO>();

        for (ManifestiPieghevoliFogliModel m : manifestiPieghevoliFogliModel) {
            _return.add(getManifestiPiegjevoliFogltioVTO(m));
        }
        return _return;
    }

    private static ManifestiPieghevoliFogliVTO getManifestiPiegjevoliFogltioVTO(ManifestiPieghevoliFogliModel m) {
        ManifestiPieghevoliFogliVTO _return = new ManifestiPieghevoliFogliVTO();
        _return.setAltezza(m.getAltezza());
        _return.setCategoria(m.getCategoria());
        _return.setGrammatura(m.getGrammatura());
        _return.setId(m.getId());
        _return.setLarghezza(m.getAltezza());
        _return.setNumeroDiPagine(m.getNumeroDiPagine());
        _return.setTiratura(m.getTiratura());
        _return.setTipoDiCartaVTO(getTipoDiCartaVTO(m.getTipoDiCarta()));
        return _return;
    }

    private static TipoDiCartaVTO getTipoDiCartaVTO(TipoDiCartaModel tipoDiCarta) {
        if (tipoDiCarta == null)
            return null;
        TipoDiCartaVTO _return = new TipoDiCartaVTO();
        _return.setId(tipoDiCarta.getId());
        _return.setNome(tipoDiCarta.getNome());
        _return.setParametro(tipoDiCarta.getParametro());
        return _return;
    }

    public static DettaglioModel getDettaglioModel(DettaglioVTO dettaglioVTO) {
        DettaglioModel dettaglioModel = new DettaglioModel();

        dettaglioModel.setEnergiaModel(dettaglioVTO.getEnergiaModel());
        dettaglioModel.setTrasportoMerciModel(dettaglioVTO.getTrasportoMerciModel());
        dettaglioModel.setTrasportoPersoneModel(dettaglioVTO.getTrasportoPersoneModel());
        dettaglioModel.setNottiModel(dettaglioVTO.getNottiModel());

        dettaglioModel.setDove(dettaglioVTO.getDove());
        dettaglioModel.setFine(dettaglioVTO.getFine());
        dettaglioModel.setId(dettaglioVTO.getId());
        dettaglioModel.setInizio(dettaglioVTO.getInizio());
        dettaglioModel.setNome(dettaglioVTO.getNome());
        dettaglioModel.setNote(dettaglioVTO.getNote());
        dettaglioModel.setOrdineId(dettaglioVTO.getOrdineId());

        dettaglioModel.setManifestiPieghevoliFogliModel(getManifestiPiegjevoliFogltioModelList(dettaglioVTO.getManifestiPieghevoliFogliVTO()));
        dettaglioModel.setPubblicazioniRilegateModel(getPubblicazioniRilegateModelList(dettaglioVTO.getPubblicazioniRilegateVTO()));

        dettaglioModel.setProgettoDiCompensazioneModel(dettaglioVTO.getProgettoDiCompensazioneModel());

        return dettaglioModel;
    }

    private static ArrayList<PubblicazioniRilegateModel> getPubblicazioniRilegateModelList(List<PubblicazioniRilegateVTO> pubblicazioniRilegateVTO) {
        if (pubblicazioniRilegateVTO == null)
            return null;
        ArrayList<PubblicazioniRilegateModel> _return = new ArrayList<PubblicazioniRilegateModel>();
        for (PubblicazioniRilegateVTO p : pubblicazioniRilegateVTO) {
            _return.add(getPubblicazioniRilegateModel(p));
        }
        return _return;
    }

    private static PubblicazioniRilegateModel getPubblicazioniRilegateModel(PubblicazioniRilegateVTO p) {
        PubblicazioniRilegateModel _return = new PubblicazioniRilegateModel();

        _return.setAltezza(p.getAltezza());
        _return.setCategoria(p.getCategoria());
        _return.setGrammatura(p.getGrammatura());
        _return.setGrammaturaCopertina(p.getGrammaturaCopertina());
        _return.setId(p.getId());
        _return.setLarghezza(p.getLarghezza());
        _return.setNumeroDiPagine(p.getNumeroDiPagine());
        _return.setTipoDiCarta(getTipoDiCartaModel(p.getTipoDiCarta()));
        _return.setTipoDiCartaCopertina(getTipoDiCartaModel(p.getTipoDiCartaCopertina()));
        _return.setTiratura(p.getTiratura());

        return _return;
    }

    private static List<ManifestiPieghevoliFogliModel> getManifestiPiegjevoliFogltioModelList(List<ManifestiPieghevoliFogliVTO> manifestiPieghevoliFogliVTO) {
        if (manifestiPieghevoliFogliVTO == null)
            return null;
        ArrayList<ManifestiPieghevoliFogliModel> _return = new ArrayList<ManifestiPieghevoliFogliModel>();
        for (ManifestiPieghevoliFogliVTO m : manifestiPieghevoliFogliVTO) {
            _return.add(getManifestiPiegjevoliFogltioModel(m));
        }
        return _return;
    }

    private static ManifestiPieghevoliFogliModel getManifestiPiegjevoliFogltioModel(ManifestiPieghevoliFogliVTO m) {
        ManifestiPieghevoliFogliModel _return = new ManifestiPieghevoliFogliModel();

        _return.setAltezza(m.getAltezza());
        _return.setCategoria(m.getCategoria());
        _return.setGrammatura(m.getGrammatura());
        _return.setId(m.getId());
        _return.setLarghezza(m.getLarghezza());
        _return.setTiratura(m.getTiratura());
        _return.setNumeroDiPagine(m.getNumeroDiPagine());
        _return.setTipoDiCarta(getTipoDiCartaModel(m.getTipoDiCartaVTO()));

        return _return;
    }

    private static TipoDiCartaModel getTipoDiCartaModel(TipoDiCartaVTO tipoDiCartaVTO) {
        if(tipoDiCartaVTO==null){
            return null;
        }
        TipoDiCartaModel _return = new TipoDiCartaModel();
        _return.setId(tipoDiCartaVTO.getId());
        _return.setNome(tipoDiCartaVTO.getNome());
        _return.setParametro(tipoDiCartaVTO.getParametro());
        return _return;
    }

}
