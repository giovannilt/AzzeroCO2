package it.agilis.mens.azzeroCO2.core.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 8/27/11
 * Time: 10:53 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Grammatura {

    @Id
    @GeneratedValue
    private Long id;

    private Double grammatura;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getGrammatura() {
        return grammatura;
    }

    public void setGrammatura(Double grammatura) {
        this.grammatura = grammatura;
    }
}
