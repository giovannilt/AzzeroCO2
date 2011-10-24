package it.agilis.mens.azzeroCO2.core.criteria;

import it.agilis.mens.azzeroCO2.core.entity.ProgettoCompensazione;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 10/24/11
 * Time: 10:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class ProgettoCompensazioneCriteria implements Serializable, SelectionCriteria {
    private Boolean attivo=true;

    @Override
    public DetachedCriteria getDetachedCriteria() {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ProgettoCompensazione.class, "ProgettoCompensazione");
        if (attivo) {
            detachedCriteria.add(Restrictions.eq("attivo", isAttivo()));
        }
        return detachedCriteria;
    }

    @Override
    public void reset() {
        attivo = true;
    }

    public boolean isAttivo() {
        return attivo;
    }

    public void setAttivo(boolean attivo) {
        this.attivo = attivo;
    }
}
