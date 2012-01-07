package it.agilis.mens.azzeroCO2.shared.model;

import com.extjs.gxt.ui.client.data.BaseModelData;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 7/2/11
 * Time: 2:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class RiepilogoModel extends BaseModelData {

    public int getIndex() {
        return (Integer) get("index");
    }

    public void setIndex(int index) {
        set("index", index);
    }

    public String getOggetto() {
        return get("oggetto");
    }

    public void setOggetto(String oggetto) {
        set("oggetto", oggetto);
    }

    public String getDettagli() {
        return get("dettagli");
    }

    public void setDettagli(String dettagli) {
        set("dettagli", dettagli);
    }

    public Double getKgCO2() {
        return get("kgCO2");
    }

    public void setKgCO2(Double kgCO2) {
        set("kgCO2", kgCO2);
    }

    public void setEventi(String eventi) {
        set("eventi", eventi);
    }

    public String getEventi() {
        return get("eventi");
    }
}

