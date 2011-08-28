package it.agilis.mens.azzeroCO2.core.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


/**
 * Created by IntelliJ IDEA.
 * User: serenadimaida
 * Date: 28/08/11
 * Time: 11:50
 * To change this template use File | Settings | File Templates.
 */

@Entity
public class ProgettoCompensazione {

    @Id
    @GeneratedValue
    private Long id;

    private String nomeProgetto;
    private Long  prezzoProgetto;
    private String img;
    private Boolean attivoProgetto;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeProgetto() {
        return nomeProgetto;
    }

    public void setNomeProgetto(String nomeProgetto) {
        this.nomeProgetto = nomeProgetto;
    }

    public Long getPrezzoProgetto() {
        return prezzoProgetto;
    }

    public void setprezzoProgetto(Long prezzoProgetto) {
        this.prezzoProgetto = prezzoProgetto;
    }

    public Boolean getAttivoProgetto() {
        return attivoProgetto;
    }

    public void setAttivoProgetto(Boolean attivoProgetto) {
        this.attivoProgetto = attivoProgetto;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
