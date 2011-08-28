package it.agilis.mens.azzeroCO2.core.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by IntelliJ IDEA.
 * User: serenadimaida
 * Date: 28/08/11
 * Time: 14:57
 * To change this template use File | Settings | File Templates.
 */
public class Pubblicazioni {

    @Id
    @GeneratedValue
    private Long id;

    private String categoria;
    private String tipoCarta;
    private Long  grammatura;
    private Integer pagine;
    private Integer tiratura;
    private Double altezza;
    private Double larghezza;
    private Boolean copertina;



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

    public String getTipoCarta() {
        return tipoCarta;
    }

    public void setTipoCarta(String tipoCarta) {
        this.tipoCarta = tipoCarta;
    }

    public Long getGrammatura() {
        return grammatura;
    }

    public void setGrammatura(Long grammatura) {
        this.grammatura = grammatura;
    }


    public Integer getPagine() {
        return pagine;
    }

    public void setPagine(Integer pagine) {
        this.pagine = pagine;
    }

    public Integer getTiratura() {
        return tiratura;
    }

    public void setTiratura(Integer tiratura) {
        this.tiratura = tiratura;
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

    public Boolean getCopertina() {
        return copertina;
    }

    public void setCopertina(Boolean copertina) {
        this.copertina = copertina;
    }



}
