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
import it.agilis.mens.azzeroCO2.shared.model.evento.TipoDiCartaModel;
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
        pcm.setKgCO2(pc.getKgCo2());
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
            pdc.setKgCo2(pdcm.getKgCO2());
            pdc.setNome(pdcm.getNome());
            pdc.setPrezzo(pdcm.getPrezzo());

            _return.add(pdc);
        }
        return _return;

    }

    public static GitRepositoryStateModel getGitState(GitRepositoryState gitRepoState) {
        GitRepositoryStateModel _return= new GitRepositoryStateModel();
        _return.setCommitId(gitRepoState.getCommitId());
        return _return;  //To change body of created methods use File | Settings | File Templates.
    }

}
