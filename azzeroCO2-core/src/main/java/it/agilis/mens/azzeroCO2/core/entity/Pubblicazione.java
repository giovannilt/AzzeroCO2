package it.agilis.mens.azzeroCO2.core.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 8/27/11
 * Time: 10:55 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Pubblicazione {

    @Id
    @GeneratedValue
    private Long id;

    private Double altezza;
    private String categoria;
    private Long pagine;
    private Double larghezza;
    private Boolean copertina;
    private Double grammatura;
    private Double grammaturaCopertina;
    private Long tiratura;

    @OneToMany
    private List<TipoDiCarta> tipoDiCarta;

    @OneToMany
    private List<TipoDiCarta> tipoDiCartaCopertina;

    @ManyToOne
    private Ordine ordine;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAltezza() {
        return altezza;
    }

    public void setAltezza(Double altezza) {
        this.altezza = altezza;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Long getPagine() {
        return pagine;
    }

    public void setPagine(Long pagine) {
        this.pagine = pagine;
    }

    public Double getLarghezza() {
        return larghezza;
    }

    public void setLarghezza(Double larghezza) {
        this.larghezza = larghezza;
    }

    public Boolean getCopertina() {
        return copertina;
    }

    public void setCopertina(Boolean copertina) {
        this.copertina = copertina;
    }

    public Double getGrammatura() {
        return grammatura;
    }

    public void setGrammatura(Double grammatura) {
        this.grammatura = grammatura;
    }

    public Double getGrammaturaCopertina() {
        return grammaturaCopertina;
    }

    public void setGrammaturaCopertina(Double grammaturaCopertina) {
        this.grammaturaCopertina = grammaturaCopertina;
    }

    public Long getTiratura() {
        return tiratura;
    }

    public void setTiratura(Long tiratura) {
        this.tiratura = tiratura;
    }

    public List<TipoDiCarta> getTipoDiCarta() {
        return tipoDiCarta;
    }

    public void setTipoDiCarta(List<TipoDiCarta> tipoDiCarta) {
        this.tipoDiCarta = tipoDiCarta;
    }

    public List<TipoDiCarta> getTipoDiCartaCopertina() {
        return tipoDiCartaCopertina;
    }

    public void setTipoDiCartaCopertina(List<TipoDiCarta> tipoDiCartaCopertina) {
        this.tipoDiCartaCopertina = tipoDiCartaCopertina;
    }

    public Ordine getOrdine() {
        return ordine;
    }

    public void setOrdine(Ordine ordine) {
        this.ordine = ordine;
    }
}
