package it.agilis.mens.azzeroCO2.shared.vto;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 10/26/11
 * Time: 7:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class PubblicazioniRilegateVTO implements Serializable {

    private Long id;
    private String categoria;
    private Double altezza;

    private Double larghezza;
    private Integer numeroDiPagine;
    private Integer tiratura;

    private Double grammatura;
    private TipoDiCartaVTO tipoDiCartaCopertina;
    private TipoDiCartaVTO tipoDiCarta;

    private Double grammaturaCopertina;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Double getAltezza() {
        return altezza;
    }

    public void setAltezza(Double altezza) {
        this.altezza = altezza;
    }

    public Double getLarghezza() {
        return larghezza;
    }

    public void setLarghezza(Double larghezza) {
        this.larghezza = larghezza;
    }

    public Integer getNumeroDiPagine() {
        return numeroDiPagine;
    }

    public void setNumeroDiPagine(Integer numeroDiPagine) {
        this.numeroDiPagine = numeroDiPagine;
    }

    public Integer getTiratura() {
        return tiratura;
    }

    public void setTiratura(Integer tiratura) {
        this.tiratura = tiratura;
    }

    public Double getGrammatura() {
        return grammatura;
    }

    public void setGrammatura(Double grammatura) {
        this.grammatura = grammatura;
    }

    public TipoDiCartaVTO getTipoDiCartaCopertina() {
        return tipoDiCartaCopertina;
    }

    public void setTipoDiCartaCopertina(TipoDiCartaVTO tipoDiCartaCopertina) {
        this.tipoDiCartaCopertina = tipoDiCartaCopertina;
    }

    public TipoDiCartaVTO getTipoDiCarta() {
        return tipoDiCarta;
    }

    public void setTipoDiCarta(TipoDiCartaVTO tipoDiCarta) {
        this.tipoDiCarta = tipoDiCarta;
    }

    public Double getGrammaturaCopertina() {
        return grammaturaCopertina;
    }

    public void setGrammaturaCopertina(Double grammaturaCopertina) {
        this.grammaturaCopertina = grammaturaCopertina;
    }
}
