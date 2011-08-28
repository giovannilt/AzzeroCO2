package it.agilis.mens.azzeroCO2.core.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by IntelliJ IDEA.
 * User: serenadimaida
 * Date: 28/08/11
 * Time: 12:39
 * To change this template use File | Settings | File Templates.
 */
public class Coefficienti {

    @Id
    @GeneratedValue
    private Long id;

    private String codiceCoeff;

    private String nomeCoeff;

    private String tipoCoeff;

    private double valoreCoeff;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodiceCoeff() {
        return codiceCoeff;
    }

    public void setCodiceCoeff(String codiceCoeff) {
        this.codiceCoeff = codiceCoeff;
    }

    public String getNomeCoeff() {
        return nomeCoeff;
    }

    public void setNomeCoeff(String nomeCoeff) {
        this.nomeCoeff = nomeCoeff;
    }

    public String getTipoCoeff() {
        return tipoCoeff;
    }

    public void setTipoCoeff(String tipoCoeff) {
        this.tipoCoeff = tipoCoeff;
    }

    public Double getValoreCoeff() {
        return valoreCoeff;
    }

    public void setValoreCoeff(Double valoreCoeff) {
        this.valoreCoeff = valoreCoeff;
    }

}
