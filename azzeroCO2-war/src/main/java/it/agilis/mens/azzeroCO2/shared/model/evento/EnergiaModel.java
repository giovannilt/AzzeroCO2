package it.agilis.mens.azzeroCO2.shared.model.evento;

import com.extjs.gxt.ui.client.data.BaseModel;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 6/25/11
 * Time: 12:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class EnergiaModel extends BaseModel {

    private int energiaElettrica;
    private int gasMetano;
    private int gasolio;

    public void setEnergiaElettrica(Double energiaElettrica) {
        set("energiaElettrica", energiaElettrica);
    }

    public Double getEnergiaElettrica() {
        return (Double) get("energiaElettrica");
    }

    public void setGasMetano(Double gasMetano) {
        set("gasMetano", gasMetano);
    }

    public Double getGasMetano() {
        return (Double) get("gasMetano");
    }

    public void setGasolio(Double gasolio) {
        set("gasolio", gasolio);
    }

    public Double getGasolio() {
        return (Double) get("gasolio");
    }
}
