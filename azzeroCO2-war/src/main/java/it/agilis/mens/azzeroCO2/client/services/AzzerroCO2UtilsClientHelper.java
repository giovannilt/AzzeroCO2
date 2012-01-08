package it.agilis.mens.azzeroCO2.client.services;

import it.agilis.mens.azzeroCO2.shared.model.OrdineModel;
import it.agilis.mens.azzeroCO2.shared.model.evento.ManifestiPieghevoliFogliModel;
import it.agilis.mens.azzeroCO2.shared.model.evento.PubblicazioniRilegateModel;
import it.agilis.mens.azzeroCO2.shared.model.evento.TipoDiCartaModel;
import it.agilis.mens.azzeroCO2.shared.model.pagamento.PagamentoModel;
import it.agilis.mens.azzeroCO2.shared.vto.DettaglioVTO;
import it.agilis.mens.azzeroCO2.shared.vto.ManifestiPieghevoliFogliVTO;
import it.agilis.mens.azzeroCO2.shared.vto.PubblicazioniRilegateVTO;
import it.agilis.mens.azzeroCO2.shared.vto.TipoDiCartaVTO;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

    public static DettaglioVTO getDettaglioVTO(OrdineModel ordineModel) {
        DettaglioVTO dettaglioVTO = new DettaglioVTO();

        dettaglioVTO.setEventiType(ordineModel.getEventiType());

        dettaglioVTO.setNottiModel(ordineModel.getNottiModel());
        dettaglioVTO.setEnergiaModel(ordineModel.getEnergiaModel());
        dettaglioVTO.setTrasportoMerciModel(ordineModel.getTrasportoMerciModel());
        dettaglioVTO.setTrasportoPersoneModel(ordineModel.getTrasportoPersoneModel());
        dettaglioVTO.setProgettoDiCompensazioneModel(ordineModel.getProgettoDiCompensazioneModel());

        dettaglioVTO.setConoscoCO2(ordineModel.getConoscoCO2Model());

        dettaglioVTO.setSellaRicevutaDiPagamento(ordineModel.getPagamentoModel());

        dettaglioVTO.setDove(ordineModel.getDove());
        dettaglioVTO.setFine(ordineModel.getFine());
        dettaglioVTO.setId(ordineModel.getId());
        dettaglioVTO.setInizio(ordineModel.getInizio());
        dettaglioVTO.setNome(ordineModel.getNome());
        dettaglioVTO.setNote(ordineModel.getNote());
        dettaglioVTO.setOrdineId(ordineModel.getOrdineId());


        dettaglioVTO.setManifestiPieghevoliFogliVTO(getManifestiPiegjevoliFogltioVTOList(ordineModel.getManifestiPieghevoliFogliModel()));
        dettaglioVTO.setPubblicazioniRilegateVTO(getPubblicazioniRilegateVTOList(ordineModel.getPubblicazioniRilegateModel()));

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

    public static OrdineModel getDettaglioModel(DettaglioVTO dettaglioVTO) {
        OrdineModel ordineModel = new OrdineModel();

        ordineModel.setEventiType(dettaglioVTO.getEventiType());


        ordineModel.setEnergiaModel(dettaglioVTO.getEnergiaModel());
        ordineModel.setTrasportoMerciModel(dettaglioVTO.getTrasportoMerciModel());
        ordineModel.setTrasportoPersoneModel(dettaglioVTO.getTrasportoPersoneModel());
        ordineModel.setNottiModel(dettaglioVTO.getNottiModel());
        ordineModel.setConoscoCO2Model(dettaglioVTO.getConoscoCO2Model());

        ordineModel.setDove(dettaglioVTO.getDove());
        ordineModel.setFine(dettaglioVTO.getFine());
        ordineModel.setId(dettaglioVTO.getId());
        ordineModel.setInizio(dettaglioVTO.getInizio());
        ordineModel.setNome(dettaglioVTO.getNome());
        ordineModel.setNote(dettaglioVTO.getNote());
        ordineModel.setOrdineId(dettaglioVTO.getOrdineId());

        ordineModel.setPagamentoModel(dettaglioVTO.getPagamentoModel());

        ordineModel.setManifestiPieghevoliFogliModel(getManifestiPiegjevoliFogltioModelList(dettaglioVTO.getManifestiPieghevoliFogliVTO()));
        ordineModel.setPubblicazioniRilegateModel(getPubblicazioniRilegateModelList(dettaglioVTO.getPubblicazioniRilegateVTO()));

        ordineModel.setProgettoDiCompensazioneModel(dettaglioVTO.getProgettoDiCompensazioneModel());

        return ordineModel;
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
        if (tipoDiCartaVTO == null) {
            return null;
        }
        TipoDiCartaModel _return = new TipoDiCartaModel();
        _return.setId(tipoDiCartaVTO.getId());
        _return.setNome(tipoDiCartaVTO.getNome());
        _return.setParametro(tipoDiCartaVTO.getParametro());
        return _return;
    }


    public static String getMAC_MD5(PagamentoModel model) {
        String s = model.getMERCHANT_ID() + model.getORDER_ID() + model.getIMPORTO() + model.getDIVISA() + model.getABI() + model.getITEMS() + model.key;
        s = s.toUpperCase();
        return encodeMD5(s);
    }

    public static String getMAC_MD5(String toMd5) {

        return encodeMD5(toMd5);
    }

    private static String encodeMD5(String stringToEncode) {
        byte[] uniqueKey = stringToEncode.getBytes();
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(uniqueKey);
        } catch (NoSuchAlgorithmException e) {
            throw new Error("no MD5 support in this VM");
        }
        StringBuffer hashString = new StringBuffer();
        for (int i = 0; i < hash.length; ++i) {
            String hex = Integer.toHexString(hash[i]);
            if (hex.length() == 1) {
                hashString.append('0');
                hashString.append(hex.charAt(hex.length() - 1));
            } else {
                hashString.append(hex.substring(hex.length() - 2));
            }
        }
        return hashString.toString();
    }
}



