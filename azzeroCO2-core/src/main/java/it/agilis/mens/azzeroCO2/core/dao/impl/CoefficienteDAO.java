package it.agilis.mens.azzeroCO2.core.dao.impl;

import it.agilis.mens.azzeroCO2.core.dao.DAOSupport;
import it.agilis.mens.azzeroCO2.core.dao.ICoefficienteDAO;
import it.agilis.mens.azzeroCO2.core.entity.Coefficiente;
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
    public List<Coefficiente> getListOfCoefficienti() {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Coefficiente.class, "coefficienti");
        return  (List<Coefficiente>) getList(detachedCriteria, true);
    }

    @Override
    public void save(Coefficiente coefficiente) throws Exception {
        saveObject(coefficiente);
    }
}
