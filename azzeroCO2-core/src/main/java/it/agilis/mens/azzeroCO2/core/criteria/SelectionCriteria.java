package it.agilis.mens.azzeroCO2.core.criteria;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

public interface SelectionCriteria {
    DetachedCriteria getDetachedCriteria();




    void reset();
}
