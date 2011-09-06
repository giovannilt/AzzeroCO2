package it.agilis.mens.azzeroCO2.core.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 9/6/11
 * Time: 3:07 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class SellaInvioPagamento {
    @Id
    @GeneratedValue
    private Long id;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
