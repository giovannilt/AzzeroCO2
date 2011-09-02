package it.agilis.mens.azzeroCO2.core.register;

import it.agilis.mens.azzeroCO2.core.criteria.OrdineCriteria;
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

    void saveCoefficiente(Coefficiente coefficiente) throws Exception;

    void saveCoefficienti(List<Coefficiente> coefficienti) throws Exception;

    void saveUserInfo(UserInfo userInfo) throws Exception;

    List<Ordine> getOrdini(OrdineCriteria ordineCriteria) throws Exception;

    List<Coupon> getListOfCoupon() throws Exception;

    UserInfo getUserInfo(String userInfo) throws Exception;

    //List<Grammatura> getGrammatura( ) throws Exception;

    List<TipoDiCarta> getTipoDiCarta() throws Exception;


}

