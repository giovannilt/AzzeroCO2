package it.agilis.mens.azzeroCO2.core.dao.impl;

import it.agilis.mens.azzeroCO2.core.dao.DAOSupport;
import it.agilis.mens.azzeroCO2.core.dao.IGrammaturaDAO;
import it.agilis.mens.azzeroCO2.core.entity.Grammatura;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 8/27/11
 * Time: 11:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class GrammaturaDAO extends DAOSupport implements IGrammaturaDAO {
    @Override
    public List<Grammatura> getGrammatura() {
        return (List<Grammatura>) getList( DetachedCriteria.forClass(Grammatura.class, "grammatura"), true);
    }
}
