package it.agilis.mens.azzeroCO2.core.dao.impl;

import it.agilis.mens.azzeroCO2.core.dao.DAOSupport;
import it.agilis.mens.azzeroCO2.core.dao.ITipoDiCartaDAO;
import it.agilis.mens.azzeroCO2.core.entity.TipoDiCarta;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 8/27/11
 * Time: 11:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class TipoDiCartaDAO  extends DAOSupport implements ITipoDiCartaDAO {
    @Override
    public List<TipoDiCarta> getTipoDiCarta() {
       return (List<TipoDiCarta>) getList( DetachedCriteria.forClass(TipoDiCarta.class, "TipoDiCarta"), true);
    }
}
