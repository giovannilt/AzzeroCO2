package it.agilis.mens.azzeroCO2.shared.model.amministrazione;

import com.extjs.gxt.ui.client.data.BaseModelData;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 7/2/11
 * Time: 2:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class Coefficiente extends BaseModelData {

    private String coefficiente;

    public Coefficiente() {

    }

    public Coefficiente(String coefficiente, String tipologia, Double valore) {
        setCoefficiente(coefficiente);
        setTipologia(tipologia);
        setKgCO2(valore);
    }


    public String getCoefficiente() {
        return get("coefficiente");
    }

    public void setCoefficiente(String coefficiente) {
        set("coeffiiente", coefficiente);
    }

    public String getTipologia() {
        return get("tipologia");
    }

    public void setTipologia(String tipologia) {
        set("tipologia", tipologia);
    }

    public Double getValore() {
        return get("valore");
    }

    public void setKgCO2(Double valore) {
        set("kgCO2", valore);
    }


}

