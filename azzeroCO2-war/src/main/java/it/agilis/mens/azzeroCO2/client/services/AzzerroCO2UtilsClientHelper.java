package it.agilis.mens.azzeroCO2.client.services;

import it.agilis.mens.azzeroCO2.shared.Eventi;
import it.agilis.mens.azzeroCO2.shared.model.OrdineModel;
import it.agilis.mens.azzeroCO2.shared.model.evento.ManifestiPieghevoliFogliModel;
import it.agilis.mens.azzeroCO2.shared.model.evento.PubblicazioniRilegateModel;
import it.agilis.mens.azzeroCO2.shared.model.evento.TipoDiCartaModel;
import it.agilis.mens.azzeroCO2.shared.model.pagamento.PagamentoModel;
import it.agilis.mens.azzeroCO2.shared.model.unaPubblicazione.BigliettiDaVisitaModel;
import it.agilis.mens.azzeroCO2.shared.vto.*;

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

    public static OrdineVTO getDettaglioVTO(OrdineModel ordineModel) {
        OrdineVTO ordineVTO = new OrdineVTO();

        ordineVTO.setEventiType(ordineModel.getEventiType());
        ordineVTO.setNome(ordineModel.getNome());
        ordineVTO.setId(ordineModel.getId());
        ordineVTO.setOrdineId(ordineModel.getOrdineId());
        ordineVTO.setProgettoDiCompensazioneModel(ordineModel.getProgettoDiCompensazioneModel());
        ordineVTO.setSellaRicevutaDiPagamento(ordineModel.getPagamentoModel());

        if (Eventi.valueOf(ordineModel.getEventiType()) == Eventi.EVENTO) {
            ordineVTO.setNottiModel(ordineModel.getNottiModel());
            ordineVTO.setEnergiaModel(ordineModel.getEnergiaModel());
            ordineVTO.setTrasportoMerciModel(ordineModel.getTrasportoMerciModel());
            ordineVTO.setTrasportoPersoneModel(ordineModel.getTrasportoPersoneModel());
            ordineVTO.setDove(ordineModel.getDove());
            ordineVTO.setFine(ordineModel.getFine());
            ordineVTO.setInizio(ordineModel.getInizio());
            ordineVTO.setNote(ordineModel.getNote());
            ordineVTO.setManifestiPieghevoliFogliVTO(getManifestiPiegjevoliFogltioVTOList(ordineModel.getManifestiPieghevoliFogliModel()));
            ordineVTO.setPubblicazioniRilegateVTO(getPubblicazioniRilegateVTOList(ordineModel.getPubblicazioniRilegateModel()));
        } else if (Eventi.valueOf(ordineModel.getEventiType()) == Eventi.ANNO_DI_ATTIVITA) {
            ordineVTO.setNottiModel(ordineModel.getNottiModel());
            ordineVTO.setEnergiaModel(ordineModel.getEnergiaModel());
            ordineVTO.setTrasportoMerciModel(ordineModel.getTrasportoMerciModel());
            ordineVTO.setTrasportoPersoneModel(ordineModel.getTrasportoPersoneModel());

            ordineVTO.setDove(ordineModel.getDove());
            ordineVTO.setAnno(ordineModel.getAnno());
            ordineVTO.setDove(ordineModel.getDove());
            ordineVTO.setNote(ordineModel.getNote());

            ordineVTO.setManifestiPieghevoliFogliVTO(getManifestiPiegjevoliFogltioVTOList(ordineModel.getManifestiPieghevoliFogliModel()));
            ordineVTO.setPubblicazioniRilegateVTO(getPubblicazioniRilegateVTOList(ordineModel.getPubblicazioniRilegateModel()));
            ordineVTO.setSitoWebModel(ordineModel.getSitoWebModel());
            ordineVTO.setBigliettiDaVisitaVTO(getBigliettiDaVisitaModelVTO(ordineModel.getBigliettiDaVisitaModel()));

        } else if (Eventi.valueOf(ordineModel.getEventiType()) == Eventi.WEB) {
            ordineVTO.setSitoWebModel(ordineModel.getSitoWebModel());
        } else if (Eventi.valueOf(ordineModel.getEventiType()) == Eventi.UNA_PUBBLICAZIONE) {
            ordineVTO.setManifestiPieghevoliFogliVTO(getManifestiPiegjevoliFogltioVTOList(ordineModel.getManifestiPieghevoliFogliModel()));
            ordineVTO.setPubblicazioniRilegateVTO(getPubblicazioniRilegateVTOList(ordineModel.getPubblicazioniRilegateModel()));
            ordineVTO.setBigliettiDaVisitaVTO(getBigliettiDaVisitaModelVTO(ordineModel.getBigliettiDaVisitaModel()));
        } else if (Eventi.valueOf(ordineModel.getEventiType()) == Eventi.CONOSCI_CO2) {
            ordineVTO.setConoscoCO2(ordineModel.getConoscoCO2Model());
        }

        if (ordineModel.getCouponModel() != null) {
            ordineVTO.setCouponModel(ordineModel.getCouponModel());
        }

        return ordineVTO;
    }

    private static BigliettiDaVisitaModelVTO getBigliettiDaVisitaModelVTO(BigliettiDaVisitaModel bigliettiDaVisita) {
        if (bigliettiDaVisita == null)
            return null;
        BigliettiDaVisitaModelVTO _return = new BigliettiDaVisitaModelVTO();

        _return.setId(bigliettiDaVisita.getId());
        _return.setGrammaturaBiglietti(bigliettiDaVisita.getGrammaturaBiglietti());
        _return.setGrammaturaCartelline(bigliettiDaVisita.getGrammaturaCartelline());
        _return.setTipoDiCartaBiglietti(getTipoDiCartaVTO(bigliettiDaVisita.getTipoDiCartaBiglietti()));
        _return.setTipoDiCartaCartelline(getTipoDiCartaVTO(bigliettiDaVisita.getTipoDiCartaCartelline()));
        _return.setTiraturaBiglietti(bigliettiDaVisita.getTiraturaBiglietti());
        _return.setTiraturaCartelline(bigliettiDaVisita.getTiraturaCartelline());

        return _return;
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

    public static OrdineModel getDettaglioModel(OrdineVTO ordineVTO) {
        OrdineModel ordineModel = new OrdineModel();

        ordineModel.setEventiType(ordineVTO.getEventiType());
        ordineModel.setOrdineId(ordineVTO.getOrdineId());
        ordineModel.setId(ordineVTO.getId());
        ordineModel.setPagamentoModel(ordineVTO.getPagamentoModel());
        ordineModel.setProgettoDiCompensazioneModel(ordineVTO.getProgettoDiCompensazioneModel());
        ordineModel.setNome(ordineVTO.getNome());

        if (Eventi.valueOf(ordineModel.getEventiType()) == Eventi.EVENTO) {
            ordineModel.setEnergiaModel(ordineVTO.getEnergiaModel());
            ordineModel.setTrasportoMerciModel(ordineVTO.getTrasportoMerciModel());
            ordineModel.setTrasportoPersoneModel(ordineVTO.getTrasportoPersoneModel());
            ordineModel.setNottiModel(ordineVTO.getNottiModel());
            ordineModel.setDove(ordineVTO.getDove());
            ordineModel.setFine(ordineVTO.getFine());
            ordineModel.setInizio(ordineVTO.getInizio());
            ordineModel.setNote(ordineVTO.getNote());
            ordineModel.setManifestiPieghevoliFogliModel(getManifestiPiegjevoliFogltioModelList(ordineVTO.getManifestiPieghevoliFogliVTO()));
            ordineModel.setPubblicazioniRilegateModel(getPubblicazioniRilegateModelList(ordineVTO.getPubblicazioniRilegateVTO()));

        } else if (Eventi.valueOf(ordineModel.getEventiType()) == Eventi.ANNO_DI_ATTIVITA) {
            ordineModel.setEnergiaModel(ordineVTO.getEnergiaModel());
            ordineModel.setTrasportoMerciModel(ordineVTO.getTrasportoMerciModel());
            ordineModel.setTrasportoPersoneModel(ordineVTO.getTrasportoPersoneModel());
            ordineModel.setNottiModel(ordineVTO.getNottiModel());
            ordineModel.setDove(ordineVTO.getDove());
            ordineModel.setAnno(ordineVTO.getAnno());
            ordineModel.setNote(ordineVTO.getNote());
            ordineModel.setManifestiPieghevoliFogliModel(getManifestiPiegjevoliFogltioModelList(ordineVTO.getManifestiPieghevoliFogliVTO()));
            ordineModel.setPubblicazioniRilegateModel(getPubblicazioniRilegateModelList(ordineVTO.getPubblicazioniRilegateVTO()));
            ordineModel.setSitoWebModel(ordineVTO.getSitoWebModel());
            ordineModel.setBigliettiDaVisitaModel(getBigliettiDaVisitaModel(ordineVTO.getBigliettiDaVisitaVTO()));

        } else if (Eventi.valueOf(ordineModel.getEventiType()) == Eventi.WEB) {
            ordineModel.setSitoWebModel(ordineVTO.getSitoWebModel());
        } else if (Eventi.valueOf(ordineModel.getEventiType()) == Eventi.CONOSCI_CO2) {
            ordineModel.setConoscoCO2Model(ordineVTO.getConoscoCO2Model());
        } else if (Eventi.valueOf(ordineModel.getEventiType()) == Eventi.UNA_PUBBLICAZIONE) {
            ordineModel.setManifestiPieghevoliFogliModel(getManifestiPiegjevoliFogltioModelList(ordineVTO.getManifestiPieghevoliFogliVTO()));
            ordineModel.setPubblicazioniRilegateModel(getPubblicazioniRilegateModelList(ordineVTO.getPubblicazioniRilegateVTO()));
            ordineModel.setBigliettiDaVisitaModel(getBigliettiDaVisitaModel(ordineVTO.getBigliettiDaVisitaVTO()));
        }

        if (ordineVTO.getCouponModel() != null) {
            ordineModel.setCouponModel(ordineVTO.getCouponModel());
        }

        return ordineModel;
    }

    private static BigliettiDaVisitaModel getBigliettiDaVisitaModel(BigliettiDaVisitaModelVTO bigliettiDaVisitaVTO) {
        if (bigliettiDaVisitaVTO == null)
            return null;
        BigliettiDaVisitaModel _return = new BigliettiDaVisitaModel();
        _return.setId(bigliettiDaVisitaVTO.getId());
        _return.setGrammaturaBiglietti(bigliettiDaVisitaVTO.getGrammaturaBiglietti());
        _return.setGrammaturaCartelline(bigliettiDaVisitaVTO.getGrammaturaCartelline());
        _return.setTipoDiCartaBiglietti(getTipoDiCartaModel(bigliettiDaVisitaVTO.getTipoDiCartaBiglietti()));
        _return.setTipoDiCartaCartelline(getTipoDiCartaModel(bigliettiDaVisitaVTO.getTipoDiCartaCartelline()));
        _return.setTiraturaBiglietti(bigliettiDaVisitaVTO.getTiraturaBiglietti());
        _return.setTiraturaCartelline(bigliettiDaVisitaVTO.getTiraturaCartelline());
        return _return;
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



