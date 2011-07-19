package it.agilis.mens.azzeroCO2.shared.model.amministrazione;

import com.extjs.gxt.ui.client.data.BaseModelData;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 7/2/11
 * Time: 6:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class ProgettiDiCompensazione extends BaseModelData {


    public ProgettiDiCompensazione(String name, String type, Double kgCO2, String attivo) {
        setName(name);
        setKgCO2(kgCO2);
        setType(type);
        setType(attivo);
    }
    public String getType() {
        return get("type");
    }

    public void setType(String type) {
        set("type", type);
    }

    public Double getKgCO2() {
        return get("kgCO2");
    }

    public void setKgCO2(Double kgCO2) {
        set("kgCO2", kgCO2);
    }

    public String getName() {
        return get("name");
    }

    public void setName(String name) {
        set("name", name);
    }

    public String getAttivo() {
        return get("attivo");
    }

    public void setAttivo(String attivo) {
        set("attivo", attivo);
    }


}
