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

    public String getCategoria() {
        return get("categoria");
    }

    public void setCategoria(String categoria) {
        set("categoria", categoria);
    }

    public Double getAltezza() {
        return get("altezza");
    }

    public void setAltezza(Double altezza) {
        set("altezza", altezza);
    }

    public Double getLarghezza() {
        return get("larghezza");
    }

    public void setLarghezza(Double larghezza) {
        set("larghezza", larghezza);
    }

    public Integer getNumeroDiPagine() {
        return get("numeroDiPagine");
    }

    public void setNumeroDiPagine(Integer numeroDiPagine) {
        set("numeroDiPagine", numeroDiPagine);
    }

    public Double getTiratura() {
        return get("tiratura");
    }

    public void setTiratura(Double tiratura) {
        set("tiratura", tiratura);
    }

    public TipoDiCartaModel getTipoDiCarta() {
        return get("tipoDiCarta");
    }

    public void setTipoDiCarta(TipoDiCartaModel tipoDiCarta) {
        set("tipoDiCarta", tipoDiCarta);
    }

    public Double getGrammatura() {
        return get("grammatura");
    }

    public void setGrammatura(double grammatura) {
        set("grammatura", grammatura);
    }


}

