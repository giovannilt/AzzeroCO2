package it.agilis.mens.azzeroCO2.core.criteria;

import org.hibernate.criterion.DetachedCriteria;

public interface SelectionCriteria {
    DetachedCriteria getDetachedCriteria();
    void reset();
}
