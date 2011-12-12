package it.agilis.mens.azzeroCO2.core.register;

import it.agilis.mens.azzeroCO2.core.criteria.ProgettoCompensazioneCriteria;
import it.agilis.mens.azzeroCO2.core.criteria.SellaRicevutaDiPagamentoCriteria;
import it.agilis.mens.azzeroCO2.core.entity.*;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 3/11/11
 * Time: 3:27 PM
 * To change this template use File | Settings | File Templates.
 */
public interface IAzzeroCO2Register {

    void saveCoupon(Coupon coupon) throws Exception;

    void saveCoupons(List<Coupon> coupons) throws Exception;


    void saveCoefficienti(List<Coefficiente> coefficienti) throws Exception;

    UserInfo saveUserInfo(UserInfo userInfo) throws Exception;


    void saveProgettiCompensazione(List<ProgettoCompensazione> progettiDiCompensazione) throws Exception;

    Ordine saveOrUpdateOrdine(Ordine o, UserInfo userInfo) throws Exception;


    List<Coupon> getListOfCoupon() throws Exception;

    UserInfo getUserInfo(String userInfo) throws Exception;


    List<TipoDiCarta> getTipoDiCarta() throws Exception;

    SellaRicevutaDiPagamento getSellaRicevutaDiPagamento(SellaRicevutaDiPagamentoCriteria criteria);

    List<Coefficiente> getCoefficienti();

    List<ProgettoCompensazione> getListOfProgettoDiCompensazione(ProgettoCompensazioneCriteria criteria);

    List<Ordine> getListOfOrdini(UserInfo userInfo);



}

