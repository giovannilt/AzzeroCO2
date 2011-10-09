package it.agilis.mens.azzeroCO2.core.dao;

import it.agilis.mens.azzeroCO2.core.criteria.OrdineCriteria;
import it.agilis.mens.azzeroCO2.core.entity.Ordine;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 8/18/11
 * Time: 8:41 PM
 * To change this template use File | Settings | File Templates.
 */
public interface IOrdineDAO {

    List<Ordine> getListOfOrdini(OrdineCriteria ordineCriteria);

    Ordine save(Ordine o) throws Exception;
}
