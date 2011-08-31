package it.agilis.mens.azzeroCO2.core.dao;

import it.agilis.mens.azzeroCO2.core.entity.Coefficienti;
import it.agilis.mens.azzeroCO2.core.entity.Coupon;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 8/31/11
 * Time: 7:37 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ICoefficienteDAO {
    List<Coefficienti> getListOfCoefficienti();

    void save(Coupon coupon) throws Exception;
}
