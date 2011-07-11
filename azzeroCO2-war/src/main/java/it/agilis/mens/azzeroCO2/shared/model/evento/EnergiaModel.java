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

    public void setEnergiaElettrica(int energiaElettrica) {
        set("energiaElettrica", energiaElettrica);
    }

    public int getEnergiaElettrica() {
        return (Integer) get("energiaElettrica");
    }

    public void setGasMetano(int gasMetano) {
        set("gasMetano", gasMetano);
    }

    public int getGasMetano() {
        return (Integer) get("gasMetano");
    }

    public void setGasolio(int gasolio) {
        set("gasolio", gasolio);
    }

    public int getGasolio() {
        return (Integer) get("gasolio");
    }
}
