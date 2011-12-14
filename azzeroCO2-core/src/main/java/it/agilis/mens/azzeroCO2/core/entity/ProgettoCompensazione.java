package it.agilis.mens.azzeroCO2.core.entity;

import javax.persistence.*;


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

    @OneToOne
    private Ordine ordine;

    private String nome;
    @Column(length=500)
    private String descrizione;
    private Double prezzo;
    private String img;
    private Boolean attivo;
    private double kgCo2;
    private String pdf;

    public Long getId() {
        return id;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Ordine getOrdine() {
        return ordine;
    }

    public void setOrdine(Ordine ordine) {
        this.ordine = ordine;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(Double prezzo) {
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

    public double getKgCo2() {
        return kgCo2;
    }

    public void setKgCo2(double kgCo2) {
        this.kgCo2 = kgCo2;
    }

    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }
}
