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

    public Long getId() {
        return get("id");
    }

    public void setId(Long id) {
        set("id", id);
    }

    public String getCategoria() {
        return get("categoria");
    }

    public void setCategoria(String categoria) {
        set("categoria", categoria);
    }

    public double getAltezza() {
        return get("altezza") == null ? 0 : new Double(get("altezza").toString());
    }

    public void setAltezza(Double altezza) {
        set("altezza", altezza);
    }

    public double getLarghezza() {
        return get("larghezza") == null ? 0 : new Double(get("larghezza").toString());
    }

    public void setLarghezza(Double larghezza) {
        set("larghezza", larghezza);
    }

    public int getNumeroDiPagine() {
        return get("numeroDiPagine") == null ? 0 : new Integer(get("numeroDiPagina").toString());
    }

    public void setNumeroDiPagine(Integer numeroDiPagine) {
        set("numeroDiPagine", numeroDiPagine);
    }

    public int getTiratura() {
        return get("tiratura") == null ? 0 : new Integer(get("tiratura").toString());
    }

    public void setTiratura(Integer tiratura) {
        set("tiratura", tiratura);
    }

    public TipoDiCartaModel getTipoDiCarta() {
        return get("tipoDiCarta");
    }

    public void setTipoDiCarta(TipoDiCartaModel tipoDiCarta) {
        set("tipoDiCarta", tipoDiCarta);
    }

    public double getGrammatura() {
        return get("grammatura") == null ? 0 : new Double(get("grammatura").toString());
    }

    public void setGrammatura(double grammatura) {
        set("grammatura", grammatura);
    }

    public boolean isVoid() {

        for (Object value : getProperties().values()) {
            if (value instanceof Double) {
                Double d = (Double) value;
                if (d != 0) {
                    return false;
                }
            }
        }
        return true;
    }

}

