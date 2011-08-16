package it.agilis.mens.azzeroCO2.shared.model.amministrazione;

import com.extjs.gxt.ui.client.data.BaseModelData;
import com.google.gwt.user.client.ui.CheckBox;
import com.sun.tools.javac.comp.Check;

import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 7/2/11
 * Time: 6:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class ProgettiDiCompensazione extends BaseModelData {

    public ProgettiDiCompensazione(){

    }

    public ProgettiDiCompensazione(String type, String name, Double kgCO2, Boolean attivo) {
        setName(name);
        setType(type);
        setKgCO2(kgCO2);
        setAttivo(attivo);
    }




    public String getType() {
        return get("type");
    }


    public void setType(String type){
        set("type", type);
    }





    public String getName() {
        return get("name");
    }

    public void setName(String name) {
        set("name", name);
    }





    public Double getKgCO2() {
        return get("kgCO2");
    }

    public void setKgCO2(Double kgCO2) {
        set("kgCO2",kgCO2);
    }


    public Boolean getAttivo() {
        return get("attivo");
    }



    public void setAttivo(Boolean attivo){
        set("attivo",attivo);
    }



}