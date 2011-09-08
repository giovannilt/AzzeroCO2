package it.agilis.mens.azzeroCO2.shared.model.evento;

import com.extjs.gxt.ui.client.data.BaseModel;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 7/2/11
 * Time: 1:04 AM
 * To change this template use File | Settings | File Templates.
 */
public class PubblicazioniRilegateModel extends BaseModel {

    public String getCategoria() {
        return get("categoria");
    }

    public void setCategoria(String categoria) {
        set("categoria", categoria);
    }

    public Double getAltezza() {
        return (Double) get("altezza");
    }

    public void setAltezza(Double altezza) {
        set("altezza", altezza);
    }

    public Double getLarghezza() {
        return (Double) get("larghezza");
    }

    public void setLarghezza(Double larghezza) {
        set("larghezza", larghezza);
    }

    public Double getNumeroDiPagine() {
        return get("numeroDiPagine");
    }

    public void setNumeroDiPagine(Double numeroDiPagine) {
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
        return (Double) get("grammatura");
    }

    public void setGrammatura(Double grammatura) {
        set("grammatura", grammatura);
    }

    public TipoDiCartaModel getTipoDiCartaCopertina() {
        return (TipoDiCartaModel) get("tipoDiCartaCopertina");
    }

    public void setTipoDiCartaCopertina(TipoDiCartaModel tipoDiCartaCopertina) {
        set("tipoDiCartaCoprtina", tipoDiCartaCopertina);
    }

    public Double getGrammaturaCopertina() {
        return (Double) get("grammaturaCopertina");
    }

    public void setGrammaturaCopertina(Double grammaturaCopertina) {
        set("grammaturaCopertina", grammaturaCopertina);
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

