package it.agilis.mens.azzeroCO2.shared.model;

import com.extjs.gxt.ui.client.data.BeanModelTag;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 6/25/11
 * Time: 12:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class EventoCalcoloDTO implements BeanModelTag, Serializable {

    private Double energiaElettrica;
    private Double gasMetano;
    private Double gasolio;

    private Integer notti;

    private Double busPiu50;
    private Double autoPiu50;
    private Double trenoPiu50;
    private Double busPiu100;
    private Double autoPiu100;
    private Double trenoPiu100;
    private Double busPiu250;
    private Double autoPiu250;
    private Double trenoPiu250;
    private Double aereoPiu250;

    public Double getEnergiaElettrica() {
        return energiaElettrica;
    }

    public void setEnergiaElettrica(Double energiaElettrica) {
        this.energiaElettrica = energiaElettrica;
    }

    public Double getGasMetano() {
        return gasMetano;
    }

    public void setGasMetano(Double gasMetano) {
        this.gasMetano = gasMetano;
    }

    public Double getGasolio() {
        return gasolio;
    }

    public void setGasolio(Double gasolio) {
        this.gasolio = gasolio;
    }

    public Integer getNotti() {
        return notti;
    }

    public void setNotti(Integer notti) {
        this.notti = notti;
    }

    public Double getBusPiu50() {
        return busPiu50;
    }

    public void setBusPiu50(Double busPiu50) {
        this.busPiu50 = busPiu50;
    }

    public Double getAutoPiu50() {
        return autoPiu50;
    }

    public void setAutoPiu50(Double autoPiu50) {
        this.autoPiu50 = autoPiu50;
    }

    public Double getTrenoPiu50() {
        return trenoPiu50;
    }

    public void setTrenoPiu50(Double trenoPiu50) {
        this.trenoPiu50 = trenoPiu50;
    }

    public Double getBusPiu100() {
        return busPiu100;
    }

    public void setBusPiu100(Double busPiu100) {
        this.busPiu100 = busPiu100;
    }

    public Double getAutoPiu100() {
        return autoPiu100;
    }

    public void setAutoPiu100(Double autoPiu100) {
        this.autoPiu100 = autoPiu100;
    }

    public Double getTrenoPiu100() {
        return trenoPiu100;
    }

    public void setTrenoPiu100(Double trenoPiu100) {
        this.trenoPiu100 = trenoPiu100;
    }

    public Double getBusPiu250() {
        return busPiu250;
    }

    public void setBusPiu250(Double busPiu250) {
        this.busPiu250 = busPiu250;
    }

    public Double getAutoPiu250() {
        return autoPiu250;
    }

    public void setAutoPiu250(Double autoPiu250) {
        this.autoPiu250 = autoPiu250;
    }

    public Double getTrenoPiu250() {
        return trenoPiu250;
    }

    public void setTrenoPiu250(Double trenoPiu250) {
        this.trenoPiu250 = trenoPiu250;
    }

    public Double getAereoPiu250() {
        return aereoPiu250;
    }

    public void setAereoPiu250(Double aereoPiu250) {
        this.aereoPiu250 = aereoPiu250;
    }
}