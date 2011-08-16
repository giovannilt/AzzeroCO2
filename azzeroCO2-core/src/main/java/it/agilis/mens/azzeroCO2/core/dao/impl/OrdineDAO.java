package it.agilis.mens.azzeroCO2.core.dao.impl;

import it.agilis.mens.azzeroCO2.core.dao.DAOSupport;
import it.agilis.mens.azzeroCO2.core.entity.Ordine;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: serenadimaida
 * Date: 16/08/11
 * Time: 21:46
 * To change this template use File | Settings | File Templates.
 */
public class OrdineDAO extends DAOSupport {

    public List<Ordine> getListOrdini(){
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Ordine.class, "ordine");
        return (List<Ordine>) getList(detachedCriteria, true);

    }
}
