package it.agilis.mens.azzeroCO2.server.utils;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 8/16/11
 * Time: 10:49 PM
 * To change this template use File | Settings | File Templates.
 */

import it.agilis.mens.azzeroCO2.core.entity.*;
import it.agilis.mens.azzeroCO2.server.GitRepositoryState;
import it.agilis.mens.azzeroCO2.shared.git.GitRepositoryStateModel;
import it.agilis.mens.azzeroCO2.shared.model.amministrazione.CoefficienteModel;
import it.agilis.mens.azzeroCO2.shared.model.amministrazione.CouponModel;
import it.agilis.mens.azzeroCO2.shared.model.amministrazione.OrdineModel;
import it.agilis.mens.azzeroCO2.shared.model.amministrazione.ProgettoDiCompensazioneModel;
import it.agilis.mens.azzeroCO2.shared.model.evento.*;
import it.agilis.mens.azzeroCO2.shared.model.registrazione.UserInfoModel;

import java.util.*;

public class Utils {

    public static List<CouponModel> getListOfCoupon(List<Coupon> listOfCoupon) {
        List<CouponModel> coupons = new ArrayList<CouponModel>();
        for (Coupon c : listOfCoupon) {
            CouponModel cm = new CouponModel();
            cm.setId(c.getId());
            cm.setAttivo(c.getStato());
            cm.setCodice(c.getCodice());
            cm.setDataFine(c.getFineValidita());
            cm.setDataInizio(c.getInizioValidita());
            cm.setDescrizione(c.getDescrizione());
            cm.setTipo(c.getTipo());
            cm.setValore(c.getValore());
            coupons.add(cm);
        }
        return coupons;  //To change body of created methods use File | Settings | File Templates.
    }

    public static Coupon getCoupon(CouponModel couponModel) {
        Coupon coupon = new Coupon();
        coupon.setId(couponModel.getId());
        coupon.setCodice(couponModel.getCodice());
        coupon.setDescrizione(couponModel.getDescrizione());
        coupon.setFineValidita(couponModel.getDataFine());
        coupon.setInizioValidita(couponModel.getDataInizio());
        coupon.setStato(couponModel.getAttivo());
        coupon.setTipo(couponModel.getTipo());
        coupon.setValore(couponModel.getValore());
        return coupon;
    }

    public static UserInfo getUserInfo(UserInfoModel registrazioneModel) {
        UserInfo userInfo = new UserInfo();
        userInfo.setPassword(registrazioneModel.getPassword());
        userInfo.setUserName(registrazioneModel.getUserName());
        userInfo.setNome(registrazioneModel.getNome());
        userInfo.setCognome(registrazioneModel.getCognome());
        userInfo.setRagSociale(registrazioneModel.getRagioneSoc());
        userInfo.setIndirizzo(registrazioneModel.getIndirizzo());
        userInfo.setCitta(registrazioneModel.getCitta());
        userInfo.setProvincia(registrazioneModel.getProvincia());
        userInfo.setCap(registrazioneModel.getCap());
        userInfo.setpIvaCF(registrazioneModel.getPartitaIvaCF());
        userInfo.setTelefono(registrazioneModel.getTelefono());
        userInfo.setFax(registrazioneModel.getFax());
        userInfo.setCellulare(registrazioneModel.getCellulare());
        userInfo.setEmail(registrazioneModel.getEmail());
        return userInfo;
    }

    public static UserInfoModel getUserInfoModel(UserInfo userInfo) {
        UserInfoModel userInfoModel = new UserInfoModel();
        userInfoModel.setUserName(userInfo.getUserName());
        userInfoModel.setCap(userInfo.getCap());
        userInfoModel.setCellulare(userInfo.getCellulare());
        userInfoModel.setCitta(userInfo.getCitta());
        userInfoModel.setCognome(userInfo.getCognome());
        userInfoModel.setEmail(userInfo.getEmail());
        userInfoModel.setFax(userInfo.getFax());
        userInfoModel.setIndirizzo(userInfo.getIndirizzo());
        userInfoModel.setNome(userInfo.getNome());
        userInfoModel.setPartitaIvaCF(userInfo.getpIvaCF());
        userInfoModel.setPassword(userInfo.getPassword());
        userInfoModel.setTelefono(userInfo.getTelefono());
        userInfoModel.setRagioneSoc(userInfo.getRagSociale());
        userInfoModel.setId(userInfo.getId());

        return userInfoModel;  //To change body of created methods use File | Settings | File Templates.
    }

    public static List<TipoDiCartaModel> getTipoDiCarta(List<TipoDiCarta> tipoDiCarta) {

        List<TipoDiCartaModel> _return = new ArrayList<TipoDiCartaModel>();
        for (TipoDiCarta t : tipoDiCarta) {
            TipoDiCartaModel model = new TipoDiCartaModel();
            model.setId(t.getId());
            model.setParametro(t.getParametro());
            model.setNome(t.getNome());
            _return.add(model);
        }
        return _return;
    }

    public static Map<String, CoefficienteModel> getCoefficientiModel(List<Coefficiente> coefficienti) {
        Map<String, CoefficienteModel> _return = new HashMap<String, CoefficienteModel>();
        for (Coefficiente c : coefficienti) {
            CoefficienteModel cm = new CoefficienteModel();
            cm.setCodice(c.getCodice());
            cm.setId(c.getId());
            cm.setNome(c.getNome());
            cm.setNote(c.getNote());
            cm.setTipologia(c.getTipologia());
            cm.setUdm(c.getUdm());
            cm.setValore(c.getValore());
            _return.put(c.getCodice(), cm);
        }
        return _return;
    }

    public static List<Coefficiente> getCoefficienti(List<CoefficienteModel> coefficienti) {
        List<Coefficiente> _return = new ArrayList<Coefficiente>();
        for (CoefficienteModel cm : coefficienti) {
            Coefficiente c = new Coefficiente();
            c.setCodice(cm.getCodice());
            c.setId(cm.getId());
            c.setNome(cm.getNome());
            c.setNote(cm.getNote());
            c.setTipologia(cm.getTipologia());
            c.setUdm(cm.getUdm());
            c.setValore(cm.getValore());

            _return.add(c);
        }
        return _return;
    }

    public static List<ProgettoDiCompensazioneModel> getListOfProgettoDiCompensazione(List<ProgettoCompensazione> listOfProgettoDiCompensazione) {
        List<ProgettoDiCompensazioneModel> _return = new ArrayList<ProgettoDiCompensazioneModel>();
        for (ProgettoCompensazione pc : listOfProgettoDiCompensazione) {
            _return.add(getProgettoDiCompensazioneModel(pc));
        }
        return _return;

    }

    public static List<OrdineModel> getListOfOrdini(List<Ordine> listOfOrdini) {
        List<OrdineModel> _return = new ArrayList<OrdineModel>();
        for (Ordine o : listOfOrdini) {
            OrdineModel om = new OrdineModel();
            om.setId(o.getId());
            om.setData(o.getDataOrdine());
            //TODO om.setCliente(o.getUtente());
            om.setProgettoDiCompensazione(getProgettoDiCompensazioneModel(o.getProgettoComp()));
            om.setKgco2(o.getKgCo2());
            om.setImporto(o.getValoreCompensazione());

            _return.add(om);
        }
        return _return;

    }

    private static ProgettoDiCompensazioneModel getProgettoDiCompensazioneModel(ProgettoCompensazione pc) {
        ProgettoDiCompensazioneModel pcm = new ProgettoDiCompensazioneModel();
        pcm.setId(pc.getId());
        pcm.setAttivo(pc.getAttivo());
        //pcm.setKgCO2(pc.getKgCo2());
        pcm.setNome(pc.getNome());
        pcm.setPrezzo(pc.getPrezzo());
        return pcm;

    }

    public static List<ProgettoCompensazione> getProgettiDiCompensazione(List<ProgettoDiCompensazioneModel> progettiDiCompensaziones) {
        List<ProgettoCompensazione> _return = new ArrayList<ProgettoCompensazione>();
        for (ProgettoDiCompensazioneModel pdcm : progettiDiCompensaziones) {
            ProgettoCompensazione pdc = new ProgettoCompensazione();

            pdc.setAttivo(pdcm.getAttivo());
            pdc.setId(pdcm.getId());
            //pdc.setKgCo2(pdcm.getKgCO2());
            pdc.setNome(pdcm.getNome());
            pdc.setPrezzo(pdcm.getPrezzo());

            _return.add(pdc);
        }
        return _return;

    }

    public static GitRepositoryStateModel getGitState(GitRepositoryState gitRepoState) {
        GitRepositoryStateModel _return= new GitRepositoryStateModel();
        _return.setCommitId(gitRepoState.getCommitId());

         _return.setBranch(gitRepoState.getBranch());

         _return.setBuildUserName(gitRepoState.getBuildUserName());
         _return.setBuildUserEmail(gitRepoState.getBuildUserEmail());
         _return.setBuildTime(gitRepoState.getBuildTime());

         _return.setCommitUserName(gitRepoState.getCommitUserName());
         _return.setCommitUserEmail(gitRepoState.getCommitUserEmail());
         _return.setCommitTime(gitRepoState.getCommitTime());
        return _return;
    }

    public static Ordine getOrdine( DettaglioModel dettaglioModel)  {
        Ordine o= new Ordine();

        o.setId(dettaglioModel.getOrdineId());
        Evento e =new Evento();
        e.setId(dettaglioModel.getId());
        e.setNome(dettaglioModel.getNome());
        e.setDove(dettaglioModel.getDove());
        e.setInizio(dettaglioModel.getInizio());
        e.setFine(dettaglioModel.getFine());
        e.setNote(dettaglioModel.getNote());
        e.setEnergiaElettrica(dettaglioModel.getEnergiaModel().getEnergiaElettrica());
        e.setGas(dettaglioModel.getEnergiaModel().getGasMetano());
        e.setGasolio(dettaglioModel.getEnergiaModel().getGasolio());
        e.setPernottamenti(dettaglioModel.getNottiModel().getNotti());

        o.setEvento(e);



        List<TrasportoPersone> trasportoPersoneList= new ArrayList<TrasportoPersone>() ;

        for(TrasportoPersoneModel tpm: dettaglioModel.getTrasportoPersoneModel()){
            TrasportoPersone tp= new TrasportoPersone();
            tp.setId(tpm.getId());

            tp.setAereo1000(tpm.getAereoKm1000());
            tp.setAereo3000(tpm.getAereoKm3000());
            tp.setAereo9000(tpm.getAereoKm9000());
            tp.setAuto60(tpm.getAutoKm60());
            tp.setAuto300(tpm.getAutoKm300());
            tp.setAuto1000(tpm.getAutoKm1000());
            tp.setAuto3000(tpm.getAutoKm3000());
            tp.setAuto9000(tpm.getAutoKm9000());
            tp.setBus60(tpm.getBusKm60());
            tp.setBus300(tpm.getBusKm300());
            tp.setBus1000(tpm.getBusKm1000());
            tp.setBus3000(tpm.getBusKm3000());
            tp.setBus9000(tpm.getBusKm9000());
            tp.setMoto60(tpm.getMotoKm60());
            tp.setMoto300(tpm.getMotoKm300());
            tp.setTreno60(tpm.getTrenoKm60());
            tp.setTreno300(tpm.getTrenoKm300());
            tp.setTreno1000(tpm.getTrenoKm300());
            tp.setTreno1000(tpm.getTrenoKm1000());
            tp.setTreno3000(tpm.getTrenoKm3000());
            tp.setTreno9000(tpm.getTrenoKm9000());
            tp.setCategoria(tpm.getCategoria());


            trasportoPersoneList.add(tp);
        }

        o.setTrasportoPersone(trasportoPersoneList);






        List<Pubblicazione> pubblicazioniRilegateList= new ArrayList<Pubblicazione>() ;

        for(PubblicazioniRilegateModel prm: dettaglioModel.getPubblicazioniRilegateModel()){
            Pubblicazione pr= new Pubblicazione();
            pr.setId(prm.getId());
            pr.setAltezza(prm.getAltezza());
            pr.setLarghezza(prm.getLarghezza());
            pr.setTipoDiCarta(prm.getTipoDiCarta());
            pr.setGrammatura(prm.getGrammatura());
            pr.setPagine(new Long(prm.getNumeroDiPagine()));
            pr.setTiratura(prm.getTiratura());
            pr.setTipoDiCartaCopertina(prm.getTipoDiCartaCopertina());
            pr.setGrammaturaCopertina(prm.getGrammaturaCopertina());
            pr.setCategoria(prm.getCategoria());
            //TODO pr.setRilegato(true);


            pubblicazioniRilegateList.add(pr);
        }

        o.setPubblicazioni(pubblicazioniRilegateList);




        List<Pubblicazione> pubblicazioniList= new ArrayList<Pubblicazione>() ;

        for(ManifestiPieghevoliFogliModel pnrm: dettaglioModel.getManifestiPieghevoliFogliModel()){
            Pubblicazione pnr= new Pubblicazione();
            pnr.setId(pnrm.getId());
            pnr.setAltezza(pnrm.getAltezza());
            pnr.setLarghezza(pnrm.getLarghezza());
            pnr.setTipoDiCarta(pnrm.getTipoDiCarta());
            pnr.setGrammatura(pnrm.getGrammatura());
            pnr.setTiratura(pnrm.getTiratura());
            pnr.setCategoria(pnrm.getCategoria());
            //TODO pr.setRilegato(false);


            pubblicazioniRilegateList.add(pr);
        }

        o.setPubblicazioni(pubblicazioniRilegateList);




        return o;


    }

}
