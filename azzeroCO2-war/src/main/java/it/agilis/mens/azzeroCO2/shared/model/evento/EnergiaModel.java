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

     public long getId()  {
        return (Long) get("id");
    }
    public void setId(Long id){
        set("id",id);
    }

    public void setEnergiaElettrica(Double energiaElettrica) {
        set("energiaElettrica", energiaElettrica);
    }

    public double getEnergiaElettrica() {
        return get("energiaElettrica") == null ? 0 : new Double(get("energiaElettrica").toString());
    }

    public void setGasMetano(Double gasMetano) {
        set("gasMetano", gasMetano);
    }

    public double getGasMetano() {
        return get("gasMetano") == null ? 0 : new Double(get("gasMetano").toString());
    }

    public void setGasolio(Double gasolio) {
        set("gasolio", gasolio);
    }

    public double getGasolio() {
        return get("gasolio") == null ? 0 : new Double(get("gasolio").toString());
    }
}
