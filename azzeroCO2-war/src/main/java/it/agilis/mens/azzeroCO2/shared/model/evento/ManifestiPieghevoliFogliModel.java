package it.agilis.mens.azzeroCO2.shared.model.evento;

import com.extjs.gxt.ui.client.data.BaseModel;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 7/2/11
 * Time: 1:04 AM
 * To change this template use File | Settings | File Templates.
 */
public class ManifestiPieghevoliFogliModel extends BaseModel {

    private double altezza;
    private double larghezza;
    private int tiratura;
    private int numeroDiPagine;
    private String tipoDiCarta;
    private double grammatura;


    public ManifestiPieghevoliFogliModel() {

    }

    public ManifestiPieghevoliFogliModel(String categoria) {
        setCategoria(categoria);
    }


    public String getCategoria() {
        return get("categoria");
    }

    public void setCategoria(String categoria) {
        set("categoria", categoria);
    }

    public double getAltezza() {
        return (Double) get("altezza");
    }

    public void setAltezza(double altezza) {
        set("altezza", altezza);
    }

    public double getLarghezza() {
        return (Double) get("larghezza");
    }

    public void setLarghezza(double larghezza) {
        set("larghezza", larghezza);
    }

    public int getNumeroDiPagine() {
        return (Integer) get("numeroDiPagine");
    }

    public void setNumeroDiPagine(int numeroDiPagine) {
        set("numeroDiPagine", numeroDiPagine);
    }

    public int getTiratura() {
        return (Integer) get("tiratura");
    }

    public void setTiratura(int tiratura) {
        set("tiratura", tiratura);
    }

    public String getTipoDiCarta() {
        return (String) get("tipoDiCarta");
    }

    public void setTipoDiCarta(String  tipoDiCarta) {
        set("tipoDiCarta", tipoDiCarta);
    }

    public double getGrammatura() {
        return (Double) get("grammatura");
    }

    public void setGrammatura(double grammatura) {
        set("grammatura", grammatura);
    }


}

