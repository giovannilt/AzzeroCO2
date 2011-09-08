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
        return get("altezza")== null ? 0 : new Double(get("altezza").toString());
    }

    public void setAltezza(Double altezza) {
        set("altezza", altezza);
    }

    public Double getLarghezza() {
        return  get("larghezza")== null ? 0 : new Double(get("larghezza").toString());
    }

    public void setLarghezza(Double larghezza) {
        set("larghezza", larghezza);
    }

    public Integer getNumeroDiPagine() {
        return get("numeroDiPagine")== null ? 0 : new Integer(get("numeroDiPagine").toString());
    }

    public void setNumeroDiPagine(Double numeroDiPagine) {
        set("numeroDiPagine", numeroDiPagine);
    }

    public Integer getTiratura() {
        return get("tiratura")== null ? 0 : new Integer(get("tiratura").toString());
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
        return (Double) get("grammatura")== null ? 0 : new Double(get("grammatura").toString());
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
        return (Double) get("grammaturaCopertina")== null ? 0 : new Double(get("grammaturaCopertina").toString());
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

