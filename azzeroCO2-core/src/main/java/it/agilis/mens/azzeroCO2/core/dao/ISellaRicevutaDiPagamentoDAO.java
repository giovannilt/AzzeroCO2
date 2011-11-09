package it.agilis.mens.azzeroCO2.core.dao;

import it.agilis.mens.azzeroCO2.core.criteria.SellaRicevutaDiPagamentoCriteria;
import it.agilis.mens.azzeroCO2.core.entity.SellaRicevutaDiPagamento;

/**
 * Created by IntelliJ IDEA.
 * User: Giovanni La Torre
 * Date: 22/09/11
 * Time: 16.41
 * To change this template use File | Settings | File Templates.
 */
public interface ISellaRicevutaDiPagamentoDAO {

    SellaRicevutaDiPagamento getPagamento(SellaRicevutaDiPagamentoCriteria criteria);

    void salvaRicevuta(SellaRicevutaDiPagamento ricevuta) throws Exception;
}
