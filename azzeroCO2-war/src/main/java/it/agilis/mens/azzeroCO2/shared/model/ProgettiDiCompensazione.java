package it.agilis.mens.azzeroCO2.shared.model;

import com.extjs.gxt.ui.client.data.BaseModelData;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 7/2/11
 * Time: 6:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class ProgettiDiCompensazione extends BaseModelData {


    public ProgettiDiCompensazione(String name, String type, String kgCO2) {
        setName(name);
        setKgCO2(kgCO2);
        setType(type);
    }
    public String getType() {
        return get("type");
    }

    public void setType(String type) {
        set("type", type);
    }

    public String getKgCO2() {
        return get("kgCO2");
    }

    public void setKgCO2(String kgCO2) {
        set("kgCO2", kgCO2);
    }

    public String getName() {
        return get("name");
    }

    public void setName(String name) {
        set("name", name);
    }

}
