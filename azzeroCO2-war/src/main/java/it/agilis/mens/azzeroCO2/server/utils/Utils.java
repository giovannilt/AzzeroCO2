package it.agilis.mens.azzeroCO2.server.utils;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 8/16/11
 * Time: 10:49 PM
 * To change this template use File | Settings | File Templates.
 */

import it.agilis.mens.azzeroCO2.core.entity.Coupon;
import it.agilis.mens.azzeroCO2.core.entity.UserInfo;
import it.agilis.mens.azzeroCO2.shared.model.amministrazione.CouponModel;
import it.agilis.mens.azzeroCO2.shared.model.amministrazione.OrdineModel;
import it.agilis.mens.azzeroCO2.shared.model.registrazione.RegistrazioneModel;

import java.util.ArrayList;
import java.util.List;

public class Utils {


    public static List<OrdineModel> getOrdini(List<it.agilis.mens.azzeroCO2.core.entity.Ordine> ordini) {
        List<OrdineModel> ordiniModel = new ArrayList<OrdineModel>();

        // TODO FOR

        for (it.agilis.mens.azzeroCO2.core.entity.Ordine ordine : ordini) {
            OrdineModel o = new OrdineModel();
            o.setCliente(ordine.getUtente().getUserName());

            ordiniModel.add(o);
        }


        return ordiniModel;

    }

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

    public static UserInfo getUserInfo(RegistrazioneModel registrazioneModel) {
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
        userInfo.setPIvaCF(registrazioneModel.getPartitaIvaCF());
        userInfo.setTelefono(registrazioneModel.getTelefono());
        userInfo.setFax(registrazioneModel.getFax());
        userInfo.setCellulare(registrazioneModel.getCellulare());
        userInfo.setEmail(registrazioneModel.getEmail());
        return  userInfo;
    }
}
