package it.agilis.mens.azzeroCO2.core.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by IntelliJ IDEA.
 * User: serenadimaida
 * Date: 28/08/11
 * Time: 12:30
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Sito {

    @Id
    @GeneratedValue
    private Long id;

    private Integer utenti;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUtenti() {
        return utenti;
    }

    public void setUtenti(Integer utenti) {
        this.utenti = utenti;
    }


}
