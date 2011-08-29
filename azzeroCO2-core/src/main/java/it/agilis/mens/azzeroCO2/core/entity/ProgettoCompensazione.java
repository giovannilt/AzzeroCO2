package it.agilis.mens.azzeroCO2.core.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;


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

    @OneToMany
    private List<Ordine> ordine;

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

    public List<Ordine> getOrdine() {
        return ordine;
    }

    public void setOrdine(List<Ordine> ordine) {
        this.ordine = ordine;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(Long prezzo) {
        this.prezzo = prezzo;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Boolean getAttivo() {
        return attivo;
    }

    public void setAttivo(Boolean attivo) {
        this.attivo = attivo;
    }
}
