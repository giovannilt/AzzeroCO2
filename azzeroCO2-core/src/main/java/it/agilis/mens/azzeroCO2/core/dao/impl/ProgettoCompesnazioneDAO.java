package it.agilis.mens.azzeroCO2.core.dao.impl;

import it.agilis.mens.azzeroCO2.core.criteria.ProgettoCompensazioneCriteria;
import it.agilis.mens.azzeroCO2.core.dao.DAOSupport;
import it.agilis.mens.azzeroCO2.core.dao.IProgettoCompesnazioneDAO;
import it.agilis.mens.azzeroCO2.core.entity.ProgettoCompensazione;

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
    public List<ProgettoCompensazione> getListOfProgettoDiCompensazione(ProgettoCompensazioneCriteria criteria) {
        return (List<ProgettoCompensazione>) getList(criteria.getDetachedCriteria(), true);
    }

    @Override
    public void save(ProgettoCompensazione progettoCompensazione) throws Exception {
        saveObject(progettoCompensazione);
    }

    @Override
    public void associaIDProgettoDiCompensazioneImmagine(Long idProgetto, String nomeImmagine) throws Exception {
        ProgettoCompensazione p = (ProgettoCompensazione) getObjectById(ProgettoCompensazione.class, idProgetto);
        p.setImg(nomeImmagine);
        saveObject(p);
    }

    @Override
    public void associaIDProgettoDiCompensazionePDF(Long idProgetto, String nomePDF) throws Exception {
         ProgettoCompensazione p = (ProgettoCompensazione) getObjectById(ProgettoCompensazione.class, idProgetto);
        p.setPdf(nomePDF);
        saveObject(p);
    }
}
