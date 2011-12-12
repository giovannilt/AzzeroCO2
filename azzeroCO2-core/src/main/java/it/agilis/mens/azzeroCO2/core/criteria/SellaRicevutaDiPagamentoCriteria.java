package it.agilis.mens.azzeroCO2.core.criteria;

import it.agilis.mens.azzeroCO2.core.entity.SellaRicevutaDiPagamento;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Giovanni La Torre
 * Date: 22/09/11
 * Time: 16.45
 * To change this template use File | Settings | File Templates.
 */
public class SellaRicevutaDiPagamentoCriteria implements Serializable, SelectionCriteria {
    private String orderId;

    @Override
    public DetachedCriteria getDetachedCriteria() {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SellaRicevutaDiPagamento.class, "SellaRicevutaDiPagamento");
        if (orderId != null && orderId.length() > 0) {
            detachedCriteria.add(Restrictions.eq("ORDER_ID", getOrderId()).ignoreCase());
        }
        return detachedCriteria;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public void reset() {

    }
}
