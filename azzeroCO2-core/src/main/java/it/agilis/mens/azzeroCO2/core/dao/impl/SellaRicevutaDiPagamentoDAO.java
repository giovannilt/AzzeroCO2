package it.agilis.mens.azzeroCO2.core.dao.impl;

import it.agilis.mens.azzeroCO2.core.criteria.SellaRicevutaDiPagamentoCriteria;
import it.agilis.mens.azzeroCO2.core.dao.DAOSupport;
import it.agilis.mens.azzeroCO2.core.dao.ISellaRicevutaDiPagamentoDAO;
import it.agilis.mens.azzeroCO2.core.entity.SellaRicevutaDiPagamento;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Giovanni La Torre
 * Date: 22/09/11
 * Time: 16.43
 * To change this template use File | Settings | File Templates.
 */
public class SellaRicevutaDiPagamentoDAO extends DAOSupport implements ISellaRicevutaDiPagamentoDAO {
    @Override
    public SellaRicevutaDiPagamento getPagamento(SellaRicevutaDiPagamentoCriteria criteria) {
        List<SellaRicevutaDiPagamento> list = (List<SellaRicevutaDiPagamento>) getList(criteria.getDetachedCriteria(), true);
        if (list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    @Override
    public void salvaRicevuta(SellaRicevutaDiPagamento ricevuta) throws Exception {

        saveObject(ricevuta);
    }

}
