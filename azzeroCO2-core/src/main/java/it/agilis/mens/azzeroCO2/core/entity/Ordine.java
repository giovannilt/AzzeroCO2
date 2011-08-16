package it.agilis.mens.azzeroCO2.core.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;


/**
 * Created by IntelliJ IDEA.
 * User: serenadimaida
 * Date: 16/08/11
 * Time: 21:41
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Ordine {

    @Id
    @GeneratedValue
    private Long id;


    private Date dataOrdine;

    @ManyToOne
    private UserInfo utente;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataOrdine() {
        return dataOrdine;
    }

    public void setDataOrdine(Date dataOrdine) {
        this.dataOrdine = dataOrdine;
    }

    public UserInfo getUtente() {
        return utente;
    }

    public void setUtente(UserInfo utente) {
        this.utente = utente;
    }
}
