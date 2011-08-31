package it.agilis.mens.azzeroCO2.core.dao.impl;

import it.agilis.mens.azzeroCO2.core.dao.DAOSupport;
import it.agilis.mens.azzeroCO2.core.dao.ICoefficienteDAO;
import it.agilis.mens.azzeroCO2.core.entity.Coefficienti;
import it.agilis.mens.azzeroCO2.core.entity.Coupon;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 8/18/11
 * Time: 8:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class CoefficienteDAO extends DAOSupport implements ICoefficienteDAO {
    @Override
    public List<Coefficienti> getListOfCoefficienti() {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Coefficienti.class, "coefficienti");
        return  (List<Coefficienti>) getList(detachedCriteria, true);
    }

    @Override
    public void save(Coupon coupon) throws Exception {

        saveObject(coupon);
    }
}
