package it.agilis.mens.azzeroCO2.server.utils;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 8/16/11
 * Time: 10:49 PM
 * To change this template use File | Settings | File Templates.
 */

import it.agilis.mens.azzeroCO2.core.entity.*;
import it.agilis.mens.azzeroCO2.core.register.impl.Email;
import it.agilis.mens.azzeroCO2.server.GitRepositoryState;
import it.agilis.mens.azzeroCO2.shared.EMailVTO;
import it.agilis.mens.azzeroCO2.shared.git.GitRepositoryStateModel;
import it.agilis.mens.azzeroCO2.shared.model.OrdineModel;
import it.agilis.mens.azzeroCO2.shared.model.amministrazione.CoefficienteModel;
import it.agilis.mens.azzeroCO2.shared.model.amministrazione.CouponModel;
import it.agilis.mens.azzeroCO2.shared.model.amministrazione.ProgettoDiCompensazioneModel;
import it.agilis.mens.azzeroCO2.shared.model.conoscoCO2.ConoscoCO2Model;
import it.agilis.mens.azzeroCO2.shared.model.evento.*;
import it.agilis.mens.azzeroCO2.shared.model.pagamento.PagamentoModel;
import it.agilis.mens.azzeroCO2.shared.model.registrazione.UserInfoModel;
import it.agilis.mens.azzeroCO2.shared.model.sitoWeb.SitoWebModel;
import it.agilis.mens.azzeroCO2.shared.model.unaPubblicazione.BigliettiDaVisitaModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Utils {

    public static CouponModel getCouponModel(Coupon c) {
        CouponModel cm = new CouponModel();
        cm.setId(c.getId());
        cm.setAttivo(c.getStato());
        cm.setCodice(c.getCodice());
        cm.setDataFine(c.getFineValidita());
        cm.setDataInizio(c.getInizioValidita());
        cm.setDescrizione(c.getDescrizione());
        cm.setTipo(c.getTipo());
        cm.setValore(c.getValore());
        return cm;
    }

    public static List<CouponModel> getListOfCoupon(List<Coupon> listOfCoupon) {
        List<CouponModel> coupons = new ArrayList<CouponModel>();
        for (Coupon c : listOfCoupon) {
            coupons.add(getCouponModel(c));
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
        if (registrazioneModel != null) {
            userInfo.setPassword(registrazioneModel.getPassword());
        }
        userInfo.setUserName(registrazioneModel.getUserName());
        userInfo.setNome(registrazioneModel.getNome());
        userInfo.setCognome(registrazioneModel.getCognome());
        userInfo.setRagSociale(registrazioneModel.getRagioneSociale());
        userInfo.setIndirizzo(registrazioneModel.getIndirizzo());
        userInfo.setCitta(registrazioneModel.getCitta());
        userInfo.setProvincia(registrazioneModel.getProvincia());
        userInfo.setCap(registrazioneModel.getCap());
        userInfo.setpIvaCF(registrazioneModel.getPivaCF());
        userInfo.setTelefono(registrazioneModel.getTelefono());
        userInfo.setFax(registrazioneModel.getFax());
        userInfo.setCellulare(registrazioneModel.getCellulare());
        userInfo.setEmail(registrazioneModel.getEmail());

        userInfo.setId(registrazioneModel.getId());

        userInfo.setProfile(Profile.User);
        return userInfo;
    }

    public static UserInfoModel getUserInfoModel(UserInfo userInfo) {
        if (userInfo == null) {
            return null;
        }
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
        userInfoModel.setPivaCF(userInfo.getpIvaCF());
        userInfoModel.setPassword(userInfo.getPassword());
        userInfoModel.setTelefono(userInfo.getTelefono());
        userInfoModel.setRagioneSociale(userInfo.getRagSociale());
        userInfoModel.setId(userInfo.getId());
        userInfoModel.setProvincia(userInfo.getProvincia());

        double kco2Compensati = 0.0;
        if (userInfo.getOrdini() != null) {
            for (Ordine o : userInfo.getOrdini()) {
                if (o.getRicevutaDiPagamento() != null && o.getRicevutaDiPagamento().getESITO().equals(Esito.PAGATO)) {
                    kco2Compensati += o.getRicevutaDiPagamento().getKgCO2();
                }
            }
        }
        userInfoModel.setKCO2Compensati(kco2Compensati);
        userInfoModel.setProfilo(userInfo.getProfile().ordinal());
        userInfoModel.setId(userInfo.getId());

        return userInfoModel;
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
            _return.add(getOrdineModel(o));
        }
        return _return;

    }

    private static ProgettoDiCompensazioneModel getProgettoDiCompensazioneModel(ProgettoCompensazione pc) {
        if (pc == null) {
            return null;
        }
        ProgettoDiCompensazioneModel pcm = new ProgettoDiCompensazioneModel();
        pcm.setId(pc.getId());
        pcm.setAttivo(pc.getAttivo());
        pcm.setNome(pc.getNome());
        pcm.setDescrizione(pc.getDescrizione());
        pcm.setPrezzo(pc.getPrezzo());
        pcm.setImageUrl(pc.getImg());
        return pcm;

    }

    public static List<ProgettoCompensazione> getProgettiDiCompensazioneList(List<ProgettoDiCompensazioneModel> progettiDiCompensaziones) {
        List<ProgettoCompensazione> _return = new ArrayList<ProgettoCompensazione>();
        for (ProgettoDiCompensazioneModel pdcm : progettiDiCompensaziones) {
            _return.add(getProgettoDiCompensazione(pdcm));
        }
        return _return;

    }

    public static ProgettoCompensazione getProgettoDiCompensazione(ProgettoDiCompensazioneModel pdcm) {
        if (pdcm == null) {
            return null;
        }
        ProgettoCompensazione pdc = new ProgettoCompensazione();

        pdc.setAttivo(pdcm.getAttivo());
        pdc.setId(pdcm.getId());
        pdc.setDescrizione(pdcm.getDescrizione());
        pdc.setNome(pdcm.getNome());
        pdc.setPrezzo(pdcm.getPrezzo());
        pdc.setImg(pdcm.getImageUrl());
        return pdc;

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

    public static Ordine getOrdine(OrdineModel ordineModel) {
        Ordine o = new Ordine();
        o.setId(ordineModel.getOrdineId());
        o.setEventiType(Eventi.valueOf(ordineModel.getEventiType()));
        o.setProgettoCompensazione(getProgettoDiCompensazione(ordineModel.getProgettoDiCompensazioneModel()));
        o.setRicevutaDiPagamento(getRicevuta(ordineModel.getPagamentoModel()));

        if (ordineModel.getCouponModel() != null) {
            o.setCoupon(getCoupon(ordineModel.getCouponModel()));
        }

        if (Eventi.valueOf(ordineModel.getEventiType()) == Eventi.UNA_PUBBLICAZIONE) {
            if (ordineModel.getPubblicazioniRilegateModel() != null && ordineModel.getPubblicazioniRilegateModel().size() > 0) {
                List<Pubblicazione> pubblicazioniRilegateList = getListOfPubblicazione(ordineModel.getPubblicazioniRilegateModel());
                if (o.getPubblicazioni() == null) {
                    o.setPubblicazioni(pubblicazioniRilegateList);
                } else {
                    o.getPubblicazioni().addAll(pubblicazioniRilegateList);
                }
            }
            if (ordineModel.getManifestiPieghevoliFogliModel() != null && ordineModel.getManifestiPieghevoliFogliModel().size() > 0) {
                List<Pubblicazione> pubblicazioniList = getListOfManifesti(ordineModel.getManifestiPieghevoliFogliModel());
                if (o.getPubblicazioni() == null) {
                    o.setPubblicazioni(pubblicazioniList);
                } else {
                    o.getPubblicazioni().addAll(pubblicazioniList);
                }
            }
            if (ordineModel.getBigliettiDaVisitaModel() != null) {
                o.setBigliettiDaVisita(getBigliettiDaVisita(ordineModel.getBigliettiDaVisitaModel()));
            }
        } else if (ordineModel.getSitoWebModel() != null && Eventi.valueOf(ordineModel.getEventiType()) == Eventi.WEB) {
            Sito s = new Sito();
            s.setUtenti(ordineModel.getSitoWebModel().getVisitatori());
            o.setSito(s);
        } else if (ordineModel.getConoscoCO2Model() != null && Eventi.valueOf(ordineModel.getEventiType()) == Eventi.CONOSCI_CO2) {
            o.setConoscoCO2(ordineModel.getConoscoCO2Model().getConoscoCO2());

        } else if (Eventi.valueOf(ordineModel.getEventiType().toUpperCase()) == Eventi.EVENTO
                || Eventi.valueOf(ordineModel.getEventiType().toUpperCase()) == Eventi.ANNO_DI_ATTIVITA) {

            Evento e = new Evento();
            e.setId(ordineModel.getId());
            e.setNome(ordineModel.getNome());
            e.setDove(ordineModel.getDove());
            e.setNote(ordineModel.getNote());

            if (Eventi.valueOf(ordineModel.getEventiType().toUpperCase()) == Eventi.ANNO_DI_ATTIVITA) {
                e.setAnno(ordineModel.getAnno());

                if (ordineModel.getSitoWebModel() != null) {
                    Sito s = new Sito();
                    s.setUtenti(ordineModel.getSitoWebModel().getVisitatori());
                    o.setSito(s);
                }
                if (ordineModel.getBigliettiDaVisitaModel() != null) {
                    o.setBigliettiDaVisita(getBigliettiDaVisita(ordineModel.getBigliettiDaVisitaModel()));
                }

            } else {
                e.setInizio(ordineModel.getInizio());
                e.setFine(ordineModel.getFine());
            }


            if (ordineModel.getEnergiaModel() != null) {
                e.setEnergiaElettrica(ordineModel.getEnergiaModel().getEnergiaElettrica());
                e.setGas(ordineModel.getEnergiaModel().getGasMetano());
                e.setGasolio(ordineModel.getEnergiaModel().getGasolio());
                e.setPernottamenti(ordineModel.getNottiModel().getNotti());
            }
            o.setEvento(e);

            if (ordineModel.getTrasportoMerciModel() != null) {
                TrasportoMerci tm = new TrasportoMerci();
                tm.setId(ordineModel.getTrasportoMerciModel().getId());
                tm.setFurgone30(ordineModel.getTrasportoMerciModel().getFurgoneKm30());
                tm.setFurgone150(ordineModel.getTrasportoMerciModel().getFurgoneKm150());
                tm.setFurgone1500(ordineModel.getTrasportoMerciModel().getFurgoneKm1500());
                tm.setFurgone500(ordineModel.getTrasportoMerciModel().getFurgoneKm500());
                tm.setFurgone9000(ordineModel.getTrasportoMerciModel().getFurgoneKm9000());
                tm.setTir30(ordineModel.getTrasportoMerciModel().getTirKm30());
                tm.setTir150(ordineModel.getTrasportoMerciModel().getTirKm150());
                tm.setTir1500(ordineModel.getTrasportoMerciModel().getTirKm1500());
                tm.setTir500(ordineModel.getTrasportoMerciModel().getTirKm500());
                tm.setTir9000(ordineModel.getTrasportoMerciModel().getTirKm9000());
                tm.setTreno150(ordineModel.getTrasportoMerciModel().getTrenoKm150());
                tm.setTreno1500(ordineModel.getTrasportoMerciModel().getTrenoKm1500());
                tm.setTreno500(ordineModel.getTrasportoMerciModel().getTrenoKm500());
                tm.setTreno9000(ordineModel.getTrasportoMerciModel().getTrenoKm9000());
                tm.setNave1500(ordineModel.getTrasportoMerciModel().getNaveKm1500());
                tm.setNave500(ordineModel.getTrasportoMerciModel().getNaveKm500());
                tm.setNave9000(ordineModel.getTrasportoMerciModel().getNaveKm9000());
                tm.setAereo1500(ordineModel.getTrasportoMerciModel().getAereoKm1500());
                tm.setAereo9000(ordineModel.getTrasportoMerciModel().getAereoKm9000());
                o.setTrasportoMerci(tm);
            }
            if (ordineModel.getTrasportoPersoneModel() != null && ordineModel.getTrasportoPersoneModel().size() > 0) {
                List<TrasportoPersone> trasportoPersoneList = new ArrayList<TrasportoPersone>();
                for (TrasportoPersoneModel tpm : ordineModel.getTrasportoPersoneModel()) {
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
            if (ordineModel.getPubblicazioniRilegateModel() != null && ordineModel.getPubblicazioniRilegateModel().size() > 0) {
                List<Pubblicazione> pubblicazioniRilegateList = getListOfPubblicazione(ordineModel.getPubblicazioniRilegateModel());
                if (o.getPubblicazioni() == null) {
                    o.setPubblicazioni(pubblicazioniRilegateList);
                } else {
                    o.getPubblicazioni().addAll(pubblicazioniRilegateList);
                }
            }
            if (ordineModel.getManifestiPieghevoliFogliModel() != null && ordineModel.getManifestiPieghevoliFogliModel().size() > 0) {
                List<Pubblicazione> pubblicazioniList = getListOfManifesti(ordineModel.getManifestiPieghevoliFogliModel());
                if (o.getPubblicazioni() == null) {
                    o.setPubblicazioni(pubblicazioniList);
                } else {
                    o.getPubblicazioni().addAll(pubblicazioniList);
                }
            }
        }
        return o;
    }

    private static BigliettiDaVisita getBigliettiDaVisita(BigliettiDaVisitaModel model) {
        BigliettiDaVisita bigliettiDaVisita = new BigliettiDaVisita();
        bigliettiDaVisita.setId(model.getId());
        if (model.getTipoDiCartaBiglietti() != null
                && model.getGrammaturaBiglietti() != null
                && model.getTiraturaBiglietti() != null) {
            bigliettiDaVisita.setGrammaturaBiglietti(model.getGrammaturaBiglietti());
            if (model.getTipoDiCartaBiglietti() != null) {
                TipoDiCarta tp = new TipoDiCarta();
                tp.setId(model.getTipoDiCartaBiglietti().getId());
                tp.setNome(model.getTipoDiCartaBiglietti().getNome());
                tp.setParametro(model.getTipoDiCartaBiglietti().getParametro());
                bigliettiDaVisita.setTipoDiCartaBiglietti(tp);
            }
            bigliettiDaVisita.setTiraturaBiglietti(model.getTiraturaBiglietti());
        }

        if (model.getTipoDiCartaCartelline() != null
                && model.getGrammaturaCartelline() != null
                && model.getTiraturaCartelline() != null) {
            bigliettiDaVisita.setGrammaturaCartelline(model.getGrammaturaCartelline());
            if (model.getTipoDiCartaCartelline() != null) {
                TipoDiCarta tp = new TipoDiCarta();
                tp.setId(model.getTipoDiCartaCartelline().getId());
                tp.setNome(model.getTipoDiCartaCartelline().getNome());
                tp.setParametro(model.getTipoDiCartaCartelline().getParametro());
                bigliettiDaVisita.setTipoDiCartaCartelline(tp);
            }
            bigliettiDaVisita.setTiraturaBiglietti(model.getTiraturaBiglietti());
        }

        return bigliettiDaVisita;
    }

    private static List<Pubblicazione> getListOfManifesti(List<ManifestiPieghevoliFogliModel> prmlist) {
        List<Pubblicazione> pubblicazioniList = new ArrayList<Pubblicazione>();
        for (ManifestiPieghevoliFogliModel pnrm : prmlist) {
            Pubblicazione pnr = new Pubblicazione();
            pnr.setId(pnrm.getId());
            pnr.setAltezza(pnrm.getAltezza());
            pnr.setLarghezza(pnrm.getLarghezza());
            if (pnrm.getTipoDiCarta() != null) {
                TipoDiCarta tp = new TipoDiCarta();
                tp.setId(pnrm.getTipoDiCarta().getId());
                tp.setNome(pnrm.getTipoDiCarta().getNome());
                tp.setParametro(pnrm.getTipoDiCarta().getParametro());
                pnr.setTipoDiCarta(tp);
            }
            pnr.setGrammatura(pnrm.getGrammatura());
            pnr.setTiratura(new Long(pnrm.getTiratura()));
            pnr.setCategoria(pnrm.getCategoria());
            pnr.setRilegato(false);
            pubblicazioniList.add(pnr);
        }
        return pubblicazioniList;

    }

    private static List<Pubblicazione> getListOfPubblicazione(List<PubblicazioniRilegateModel> prmlist) {
        List<Pubblicazione> pubblicazioniRilegateList = new ArrayList<Pubblicazione>();
        for (PubblicazioniRilegateModel prm : prmlist) {
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
        return pubblicazioniRilegateList;
    }

    public static OrdineModel getOrdineModel(Ordine o) {
        OrdineModel dm = new OrdineModel();

        dm.setLastUpdate(o.getLastUpdate());
        dm.setProgettoDiCompensazioneModel(getProgettoDiCompensazioneModel(o.getProgettoCompensazione()));
        dm.setPagamentoModel(getPagamentoModel(o.getRicevutaDiPagamento()));
        dm.setOrdineId(o.getId());
        dm.setEventiType(o.getEventiType().name());
        if (o.getCoupon() != null) {
            dm.setCouponModel(getCouponModel(o.getCoupon()));
        }

        if (o.getEventiType() == Eventi.UNA_PUBBLICAZIONE) {
            dm = setPubblicazioni(o, dm);
            if (o.getBigliettiDaVisita() != null) {
                dm.setBigliettiDaVisitaModel(getBigliettiDaVisitaModel(o.getBigliettiDaVisita()));
            }
            dm.setNome("Compensazione di una Pubblicazione");
        } else if (o.getEventiType() == Eventi.WEB) {
            if (o.getSito() != null) {
                SitoWebModel sito = new SitoWebModel();
                sito.setId(o.getSito().getId());
                sito.setVisitatori(o.getSito().getUtenti());
                dm.setSitoWebModel(sito);
            }
            dm.setNome("Compensazione di un Sito Web");
        } else if (o.getEventiType() == Eventi.CONOSCI_CO2) {
            ConoscoCO2Model co2m = new ConoscoCO2Model();
            co2m.setConoscoCO2(o.getConoscoCO2());
            dm.setConoscoCO2Model(co2m);
            dm.setNome("Compensazione di CO2 calcolato");
        } else if (o.getEventiType() == Eventi.EVENTO || o.getEventiType() == Eventi.ANNO_DI_ATTIVITA) {
            if (o.getEvento() != null) {
                if (o.getEventiType() == Eventi.EVENTO) {
                    dm.setInizio(o.getEvento().getInizio());
                    dm.setFine(o.getEvento().getFine());
                } else {
                    dm.setAnno(o.getEvento().getAnno());
                }
                dm.setId(o.getEvento().getId());
                dm.setNome(o.getEvento().getNome());
                dm.setDove(o.getEvento().getDove());

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
            }

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

            if (o.getTrasportoPersone() != null) {
                ArrayList<TrasportoPersoneModel> tpmList = new ArrayList<TrasportoPersoneModel>();
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

            dm = setPubblicazioni(o, dm);
        }
        return dm;
    }

    private static BigliettiDaVisitaModel getBigliettiDaVisitaModel(BigliettiDaVisita bigliettiDaVisita) {
        BigliettiDaVisitaModel _return = new BigliettiDaVisitaModel();

        _return.setId(bigliettiDaVisita.getId());
        _return.setGrammaturaBiglietti(bigliettiDaVisita.getGrammaturaBiglietti());
        _return.setGrammaturaCartelline(bigliettiDaVisita.getGrammaturaBiglietti());
        _return.setTiraturaBiglietti(bigliettiDaVisita.getTiraturaBiglietti());
        _return.setTiraturaCartelline(bigliettiDaVisita.getTiraturaCartelline());
        if (bigliettiDaVisita.getTipoDiCartaBiglietti() != null) {
            TipoDiCartaModel tp = new TipoDiCartaModel();
            tp.setId(bigliettiDaVisita.getTipoDiCartaBiglietti().getId());
            tp.setNome(bigliettiDaVisita.getTipoDiCartaBiglietti().getNome());
            tp.setParametro(bigliettiDaVisita.getTipoDiCartaBiglietti().getParametro());
            _return.setTipoDiCartaBiglietti(tp);
        }
        if (bigliettiDaVisita.getTipoDiCartaCartelline() != null) {
            TipoDiCartaModel tp = new TipoDiCartaModel();
            tp.setId(bigliettiDaVisita.getTipoDiCartaCartelline().getId());
            tp.setNome(bigliettiDaVisita.getTipoDiCartaCartelline().getNome());
            tp.setParametro(bigliettiDaVisita.getTipoDiCartaCartelline().getParametro());
            _return.setTipoDiCartaCartelline(tp);
        }

        return _return;
    }

    private static OrdineModel setPubblicazioni(Ordine o, OrdineModel dm) {
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

    private static PagamentoModel getPagamentoModel(SellaRicevutaDiPagamento ricevutaDiPagamento) {
        if (ricevutaDiPagamento == null) {
            return null;
        }
        PagamentoModel ricevuta = new PagamentoModel();

        ricevuta.setId(ricevutaDiPagamento.getId());
        ricevuta.setEsito(it.agilis.mens.azzeroCO2.shared.model.pagamento.Esito.values()[ricevutaDiPagamento.getESITO().ordinal()].toString());

        ricevuta.setMERCHANT_ID(ricevutaDiPagamento.getMERCHANT_ID());
        ricevuta.setDIVISA(ricevutaDiPagamento.getDIVISA());

        ricevuta.setIMPORTO(ricevutaDiPagamento.getIMPORTO());
        ricevuta.setMAC(ricevutaDiPagamento.getMAC());
        ricevuta.setORDER_ID(ricevutaDiPagamento.getORDER_ID());

        ricevuta.setTIPO_PAGAMENTO(ricevutaDiPagamento.getTIPO_PAGAMENTO());

        ricevuta.setABI(ricevutaDiPagamento.getABI());
        ricevuta.setITEMS(ricevutaDiPagamento.getITEMS());

        ricevuta.setLastUpdate(ricevutaDiPagamento.getLastUpdate());
        ricevuta.setUpdateFromBanca(ricevutaDiPagamento.getUpdateFromBanca());

        ricevuta.setKgCO2(ricevutaDiPagamento.getKgCO2());

        return ricevuta;
    }

    public static Email getEmail(EMailVTO e) {
        Email _return = new Email();

        _return.setBody(e.getBody());
        _return.setFromUser(e.getFromUser());
        _return.setSubject(e.getSubject());
        _return.setToUser(e.getToUser().toArray(new String[]{}));

        return _return;
    }

    public static SellaRicevutaDiPagamento getRicevuta(PagamentoModel pagamentoModel) {
        if (pagamentoModel == null) {
            return null;
        }
        SellaRicevutaDiPagamento ricevuta = new SellaRicevutaDiPagamento();

        ricevuta.setId(pagamentoModel.getId());
        
        if(pagamentoModel.getEsito().equalsIgnoreCase("In pagamento")){
            ricevuta.setEsito(Esito.IN_PAGAMENTO);
        }else{
            ricevuta.setEsito(Esito.valueOf(pagamentoModel.getEsito()));

        }

        ricevuta.setMERCHANT_ID(pagamentoModel.getMERCHANT_ID());
        ricevuta.setDIVISA(pagamentoModel.getDIVISA());

        ricevuta.setIMPORTO(pagamentoModel.getIMPORTO());
        ricevuta.setMAC(pagamentoModel.getMAC());

        ricevuta.setORDER_ID(pagamentoModel.getORDER_ID());
        ricevuta.setTIPO_PAGAMENTO(pagamentoModel.getTIPO_PAGAMENTO());

        ricevuta.setABI(pagamentoModel.getABI());
        ricevuta.setITEMS(pagamentoModel.getITEMS());

        ricevuta.setLastUpdate(pagamentoModel.getLastUpdate());
        ricevuta.setUpdateFromBanca(pagamentoModel.getUpdateFromBanca());

        ricevuta.setKgCO2(pagamentoModel.getKgCO2());

        return ricevuta;

    }


}
