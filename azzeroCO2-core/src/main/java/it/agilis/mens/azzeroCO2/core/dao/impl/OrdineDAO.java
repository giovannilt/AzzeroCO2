package it.agilis.mens.azzeroCO2.core.dao.impl;

import it.agilis.mens.azzeroCO2.core.criteria.OrdineCriteria;
import it.agilis.mens.azzeroCO2.core.dao.DAOSupport;
import it.agilis.mens.azzeroCO2.core.dao.IOrdineDAO;
import it.agilis.mens.azzeroCO2.core.entity.Ordine;
import org.hibernate.Hibernate;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: serenadimaida
 * Date: 16/08/11
 * Time: 21:46
 * To change this template use File | Settings | File Templates.
 */
@Transactional
public class OrdineDAO extends DAOSupport implements IOrdineDAO  {

     @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<Ordine> getListOfOrdini(OrdineCriteria ordineCriteria){
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Ordine.class, "ordine");

        List<Ordine> list = (List<Ordine>) getList(detachedCriteria, true);
        for(Ordine o:list){
            Hibernate.initialize(o.getProgettoCompensazione());
            Hibernate.initialize(o.getTrasportoPersone());
            Hibernate.initialize(o.getTrasportoMerci());
            Hibernate.initialize(o.getPubblicazioni());
            Hibernate.initialize(o.getCoupon());
            Hibernate.initialize(o.getEvento());

        }
        return list;

    }

    @Override
    public Ordine save(Ordine o) throws Exception {
        return (Ordine) saveObject(o);
    }

    @Override
    public Ordine getOrdine(Long ordineId) {
        return (Ordine) getObjectById(Ordine.class, ordineId);
    }
}
