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

    private String nome;
    private Long  prezzo;
    private String img;
    private Boolean attivo;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeProgetto() {
        return nome;
    }

    public void setNomeProgetto(String nome) {
        this.nome = nome;
    }

    public Long getPrezzo() {
        return prezzo;
    }

    public void setprezzoProgetto(Long prezzo) {
        this.prezzo = prezzo;
    }

    public Boolean getAttivo() {
        return attivo;
    }

    public void setAttivo(Boolean attivo) {
        this.attivo = attivo;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
