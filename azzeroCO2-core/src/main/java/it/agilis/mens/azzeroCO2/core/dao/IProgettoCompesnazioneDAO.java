package it.agilis.mens.azzeroCO2.core.dao;

import it.agilis.mens.azzeroCO2.core.criteria.ProgettoCompensazioneCriteria;
import it.agilis.mens.azzeroCO2.core.entity.ProgettoCompensazione;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 9/2/11
 * Time: 11:19 PM
 * To change this template use File | Settings | File Templates.
 */
public interface IProgettoCompesnazioneDAO {
    List<ProgettoCompensazione> getListOfProgettoDiCompensazione(ProgettoCompensazioneCriteria criteria);

    void save(ProgettoCompensazione progettoCompensazione) throws Exception;
}
