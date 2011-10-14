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
import it.agilis.mens.azzeroCO2.shared.model.amministrazione.ProgettoDiCompensazioneModel;
import it.agilis.mens.azzeroCO2.shared.model.evento.*;
import it.agilis.mens.azzeroCO2.shared.model.registrazione.UserInfoModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        if (registrazioneModel != null)
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

    public static List<DettaglioModel> getListOfOrdini(List<Ordine> listOfOrdini) {
        List<DettaglioModel> _return = new ArrayList<DettaglioModel>();
        for (Ordine o : listOfOrdini) {
            _return.add(getDettaglioModel(o));
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
        GitRepositoryStateModel _return = new GitRepositoryStateModel();
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

    public static Ordine getOrdine(DettaglioModel dettaglioModel) {
        Ordine o = new Ordine();
        o.setId(dettaglioModel.getOrdineId());


        Evento e = new Evento();
        e.setId(dettaglioModel.getId());
        e.setNome(dettaglioModel.getNome());
        e.setDove(dettaglioModel.getDove());
        e.setInizio(dettaglioModel.getInizio());
        e.setFine(dettaglioModel.getFine());
        e.setNote(dettaglioModel.getNote());

        if (dettaglioModel.getEnergiaModel() != null) {
            e.setEnergiaElettrica(dettaglioModel.getEnergiaModel().getEnergiaElettrica());
            e.setGas(dettaglioModel.getEnergiaModel().getGasMetano());
            e.setGasolio(dettaglioModel.getEnergiaModel().getGasolio());
            e.setPernottamenti(dettaglioModel.getNottiModel().getNotti());
        }
        o.setEvento(e);

        if (dettaglioModel.getTrasportoMerciModel() != null) {
            TrasportoMerci tm = new TrasportoMerci();
            tm.setId(dettaglioModel.getTrasportoMerciModel().getId());
            tm.setFurgone30(dettaglioModel.getTrasportoMerciModel().getFurgoneKm30());
            tm.setFurgone150(dettaglioModel.getTrasportoMerciModel().getFurgoneKm150());
            tm.setFurgone1500(dettaglioModel.getTrasportoMerciModel().getFurgoneKm1500());
            tm.setFurgone500(dettaglioModel.getTrasportoMerciModel().getFurgoneKm500());
            tm.setFurgone9000(dettaglioModel.getTrasportoMerciModel().getFurgoneKm9000());
            tm.setTir30(dettaglioModel.getTrasportoMerciModel().getTirKm30());
            tm.setTir150(dettaglioModel.getTrasportoMerciModel().getTirKm150());
            tm.setTir1500(dettaglioModel.getTrasportoMerciModel().getTirKm1500());
            tm.setTir500(dettaglioModel.getTrasportoMerciModel().getTirKm500());
            tm.setTir9000(dettaglioModel.getTrasportoMerciModel().getTirKm9000());
            tm.setTreno150(dettaglioModel.getTrasportoMerciModel().getTrenoKm150());
            tm.setTreno1500(dettaglioModel.getTrasportoMerciModel().getTrenoKm1500());
            tm.setTreno500(dettaglioModel.getTrasportoMerciModel().getTrenoKm500());
            tm.setTreno9000(dettaglioModel.getTrasportoMerciModel().getTrenoKm9000());
            tm.setNave1500(dettaglioModel.getTrasportoMerciModel().getNaveKm1500());
            tm.setNave500(dettaglioModel.getTrasportoMerciModel().getNaveKm500());
            tm.setNave9000(dettaglioModel.getTrasportoMerciModel().getNaveKm9000());
            tm.setAereo1500(dettaglioModel.getTrasportoMerciModel().getAereoKm1500());
            tm.setAereo9000(dettaglioModel.getTrasportoMerciModel().getAereoKm9000());
            o.setTrasportoMerci(tm);
        }
        if (dettaglioModel.getTrasportoPersoneModel() != null && dettaglioModel.getTrasportoPersoneModel().size() > 0) {
            List<TrasportoPersone> trasportoPersoneList = new ArrayList<TrasportoPersone>();
            for (TrasportoPersoneModel tpm : dettaglioModel.getTrasportoPersoneModel()) {
                TrasportoPersone tp = new TrasportoPersone();
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
        }
        if (dettaglioModel.getPubblicazioniRilegateModel() != null && dettaglioModel.getPubblicazioniRilegateModel().size() > 0) {
            List<Pubblicazione> pubblicazioniRilegateList = new ArrayList<Pubblicazione>();
            for (PubblicazioniRilegateModel prm : dettaglioModel.getPubblicazioniRilegateModel()) {
                Pubblicazione pr = new Pubblicazione();
                pr.setId(prm.getId());
                pr.setAltezza(prm.getAltezza());
                pr.setLarghezza(prm.getLarghezza());

                if (prm.getTipoDiCarta() != null) {
                    TipoDiCarta tp = new TipoDiCarta();
                    tp.setId(prm.getTipoDiCarta().getId());
                    tp.setNome(prm.getTipoDiCarta().getNome());
                    tp.setParametro(prm.getTipoDiCarta().getParametro());
                    pr.setTipoDiCarta(tp);
                }

                pr.setGrammatura(prm.getGrammatura());
                pr.setPagine(new Long(prm.getNumeroDiPagine()));
                pr.setTiratura(new Long(prm.getTiratura()));

                if (prm.getTipoDiCartaCopertina() != null) {
                    TipoDiCarta tp = new TipoDiCarta();
                    tp.setId(prm.getTipoDiCartaCopertina().getId());
                    tp.setNome(prm.getTipoDiCartaCopertina().getNome());
                    tp.setParametro(prm.getTipoDiCartaCopertina().getParametro());
                    pr.setTipoDiCartaCopertina(tp);
                }

                pr.setGrammaturaCopertina(prm.getGrammaturaCopertina());
                pr.setCategoria(prm.getCategoria());
                pr.setRilegato(true);
                pubblicazioniRilegateList.add(pr);
            }
            o.setPubblicazioni(pubblicazioniRilegateList);
        }
        if (dettaglioModel.getManifestiPieghevoliFogliModel() != null && dettaglioModel.getManifestiPieghevoliFogliModel().size() > 0) {
            List<Pubblicazione> pubblicazioniList = new ArrayList<Pubblicazione>();
            for (ManifestiPieghevoliFogliModel pnrm : dettaglioModel.getManifestiPieghevoliFogliModel()) {
                Pubblicazione pnr = new Pubblicazione();
                pnr.setId(pnrm.getId());
                pnr.setAltezza(pnrm.getAltezza());
                pnr.setLarghezza(pnrm.getLarghezza());
                TipoDiCarta tp = new TipoDiCarta();
                tp.setId(pnrm.getTipoDiCarta().getId());
                tp.setNome(pnrm.getTipoDiCarta().getNome());
                tp.setParametro(pnrm.getTipoDiCarta().getParametro());
                pnr.setTipoDiCarta(tp);
                pnr.setGrammatura(pnrm.getGrammatura());
                pnr.setTiratura(new Long(pnrm.getTiratura()));
                pnr.setCategoria(pnrm.getCategoria());
                pnr.setRilegato(false);
                pubblicazioniList.add(pnr);
            }
            o.setPubblicazioni(pubblicazioniList);
        }
        return o;
    }

    public static DettaglioModel getDettaglioModel(Ordine o) {
        DettaglioModel dm = new DettaglioModel();

        dm.setOrdineId(o.getId());
        dm.setId(o.getEvento().getId());
        dm.setNome(o.getEvento().getNome());
        dm.setDove(o.getEvento().getDove());
        dm.setInizio(o.getEvento().getInizio());
        dm.setFine(o.getEvento().getFine());
        dm.setNote(o.getEvento().getNote());

        EnergiaModel em = new EnergiaModel();
        // CHECK id of EMM
        em.setEnergiaElettrica(o.getEvento().getEnergiaElettrica());
        em.setGasMetano(o.getEvento().getGas());
        em.setGasolio(o.getEvento().getGasolio());

        dm.setEnergiaModel(em);

        NottiModel notti = new NottiModel();
        notti.setNotti(o.getEvento().getPernottamenti());
        dm.setNottiModel(notti);


        if (o.getTrasportoMerci() != null) {
            TrasportoMerciModel tm = new TrasportoMerciModel();
            tm.setId(o.getTrasportoMerci().getId());
            tm.setFurgoneKm30(o.getTrasportoMerci().getFurgone30());
            tm.setFurgoneKm150(o.getTrasportoMerci().getFurgone150());
            tm.setFurgoneKm1500(o.getTrasportoMerci().getFurgone1500());
            tm.setFurgoneKm500(o.getTrasportoMerci().getFurgone500());
            tm.setFurgoneKm9000(o.getTrasportoMerci().getFurgone9000());
            tm.setTirKm30(o.getTrasportoMerci().getTir30());
            tm.setTirKm150(o.getTrasportoMerci().getTir150());
            tm.setTirKm1500(o.getTrasportoMerci().getTir1500());
            tm.setTirKm500(o.getTrasportoMerci().getTir500());
            tm.setTirKm9000(o.getTrasportoMerci().getTir9000());
            tm.setTrenoKm150(o.getTrasportoMerci().getTreno150());
            tm.setTrenoKm1500(o.getTrasportoMerci().getTreno1500());
            tm.setTrenoKm500(o.getTrasportoMerci().getTreno500());
            tm.setTrenoKm9000(o.getTrasportoMerci().getTreno9000());
            tm.setNaveKm1500(o.getTrasportoMerci().getNave1500());
            tm.setNaveKm500(o.getTrasportoMerci().getNave500());
            tm.setNaveKm9000(o.getTrasportoMerci().getNave9000());
            tm.setAereoKm1500(o.getTrasportoMerci().getAereo1500());
            tm.setAereoKm9000(o.getTrasportoMerci().getAereo9000());

            dm.setTrasportoMerciModel(tm);
        }

        if (o.getPubblicazioni() != null) {
            List<TrasportoPersoneModel> tpmList = new ArrayList<TrasportoPersoneModel>();
            for (TrasportoPersone tpm : o.getTrasportoPersone()) {
                TrasportoPersoneModel tp = new TrasportoPersoneModel();
                tp.setId(tpm.getId());
                tp.setAereoKm1000(tpm.getAereo1000());
                tp.setAereoKm3000(tpm.getAereo3000());
                tp.setAereoKm9000(tpm.getAereo9000());
                tp.setAutoKm60(tpm.getAuto60());
                tp.setAutoKm300(tpm.getAuto300());
                tp.setAutoKm1000(tpm.getAuto1000());
                tp.setAutoKm3000(tpm.getAuto3000());
                tp.setAutoKm9000(tpm.getAuto9000());
                tp.setBusKm60(tpm.getBus60());
                tp.setBusKm300(tpm.getBus300());
                tp.setBusKm1000(tpm.getBus1000());
                tp.setBusKm3000(tpm.getBus3000());
                tp.setBusKm9000(tpm.getBus9000());
                tp.setMotoKm60(tpm.getMoto60());
                tp.setMotoKm300(tpm.getMoto300());
                tp.setTrenoKm60(tpm.getTreno60());
                tp.setTrenoKm300(tpm.getTreno300());
                tp.setTrenoKm1000(tpm.getTreno300());
                tp.setTrenoKm1000(tpm.getTreno1000());
                tp.setTrenoKm3000(tpm.getTreno3000());
                tp.setTrenoKm9000(tpm.getTreno9000());
                tp.setCategoria(tpm.getCategoria());
                tpmList.add(tp);
            }

            dm.setTrasportoPersoneModel(tpmList);
        }

        List<PubblicazioniRilegateModel> prmList = new ArrayList<PubblicazioniRilegateModel>();
        List<ManifestiPieghevoliFogliModel> mpfmList = new ArrayList<ManifestiPieghevoliFogliModel>();

        if (o.getPubblicazioni() != null) {
            for (Pubblicazione prm : o.getPubblicazioni()) {
                if (prm.getRilegato()) {
                    PubblicazioniRilegateModel pr = new PubblicazioniRilegateModel();
                    pr.setId(prm.getId());
                    pr.setAltezza(prm.getAltezza());
                    pr.setLarghezza(prm.getLarghezza());

                    if (prm.getTipoDiCarta() != null) {
                        TipoDiCartaModel tp = new TipoDiCartaModel();
                        tp.setId(prm.getTipoDiCarta().getId());
                        tp.setNome(prm.getTipoDiCarta().getNome());
                        tp.setParametro(prm.getTipoDiCarta().getParametro());
                        pr.setTipoDiCarta(tp);
                    }

                    pr.setGrammatura(prm.getGrammatura());
                    pr.setNumeroDiPagine(prm.getPagine().intValue());
                    pr.setTiratura(prm.getTiratura().intValue());

                    if (prm.getTipoDiCartaCopertina() != null) {
                        TipoDiCartaModel tp = new TipoDiCartaModel();
                        tp.setId(prm.getTipoDiCartaCopertina().getId());
                        tp.setNome(prm.getTipoDiCartaCopertina().getNome());
                        tp.setParametro(prm.getTipoDiCartaCopertina().getParametro());
                        pr.setTipoDiCartaCopertina(tp);
                    }

                    pr.setGrammaturaCopertina(prm.getGrammaturaCopertina());
                    pr.setCategoria(prm.getCategoria());

                    prmList.add(pr);
                } else {
                    ManifestiPieghevoliFogliModel mpfm = new ManifestiPieghevoliFogliModel();
                    mpfm.setId(prm.getId());
                    mpfm.setAltezza(prm.getAltezza());
                    mpfm.setLarghezza(prm.getLarghezza());

                    if (prm.getTipoDiCarta() != null) {
                        TipoDiCartaModel tp = new TipoDiCartaModel();
                        tp.setId(prm.getTipoDiCarta().getId());
                        tp.setNome(prm.getTipoDiCarta().getNome());
                        tp.setParametro(prm.getTipoDiCarta().getParametro());
                        mpfm.setTipoDiCarta(tp);
                    }
                    mpfm.setGrammatura(prm.getGrammatura());
                    mpfm.setTiratura(prm.getTiratura().intValue());
                    mpfm.setCategoria(prm.getCategoria());

                    mpfmList.add(mpfm);
                }
            }
            dm.setPubblicazioniRilegateModel(prmList);
            dm.setManifestiPieghevoliFogliModel(mpfmList);
        }
        return dm;
    }


}
