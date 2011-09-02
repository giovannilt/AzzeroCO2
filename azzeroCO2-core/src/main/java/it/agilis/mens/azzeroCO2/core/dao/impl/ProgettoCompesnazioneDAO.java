package it.agilis.mens.azzeroCO2.core.dao.impl;

import it.agilis.mens.azzeroCO2.core.dao.DAOSupport;
import it.agilis.mens.azzeroCO2.core.dao.IProgettoCompesnazioneDAO;
import it.agilis.mens.azzeroCO2.core.entity.Coupon;
import it.agilis.mens.azzeroCO2.core.entity.ProgettoCompensazione;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 9/2/11
 * Time: 11:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class ProgettoCompesnazioneDAO extends DAOSupport implements IProgettoCompesnazioneDAO {
    @Override
    public List<ProgettoCompensazione> getListOfProgettoDiCompensazione() {

        return (List<ProgettoCompensazione>) getList(DetachedCriteria.forClass(ProgettoCompensazione.class, "ProgettoCompensazione"), true);
    }

      @Override
    public void save(ProgettoCompensazione progettoCompensazione) throws Exception {

        saveObject(progettoCompensazione);
    }
}
