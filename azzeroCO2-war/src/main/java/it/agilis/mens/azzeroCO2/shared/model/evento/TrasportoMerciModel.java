package it.agilis.mens.azzeroCO2.shared.model.evento;

import com.extjs.gxt.ui.client.data.BaseModel;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 6/25/11
 * Time: 12:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class TrasportoMerciModel extends BaseModel {
     public Long getId()  {
        return get("id");
    }
    public void setId(Long id){
        set("id",id);
    }

    public double getFurgoneKm30() {
        return get("furgoneKm30") == null ? 0 : new Double(get("furgoneKm30").toString());
    }

    public double getFurgoneKm150() {
        return get("furgoneKm150") == null ? 0 : new Double(get("furgoneKm150").toString());
    }

    public double getFurgoneKm500() {
        return get("furgoneKm500") == null ? 0 : new Double(get("furgoneKm500").toString());
    }

    public double getFurgoneKm1500() {
        return get("furgoneKm1500") == null ? 0 : new Double(get("furgoneKm1500").toString());
    }

    public double getFurgoneKm9000() {
        return get("furgoneKm9000") == null ? 0 : new Double(get("furgoneKm9000").toString());
    }

    public double getTirKm30() {
        return get("tirKm30") == null ? 0 : new Double(get("tirKm30").toString());
    }

    public double getTirKm150() {
        return get("tirKm150") == null ? 0 : new Double(get("tirKm150").toString());
    }

    public double getTirKm500() {
        return get("tirKm500") == null ? 0 : new Double(get("tirKm500").toString());
    }

    public double getTirKm1500() {
        return get("tirKm1500") == null ? 0 : new Double(get("tirKm1500").toString());
    }

    public double getTirKm9000() {
        return get("tirKm9000") == null ? 0 : new Double(get("tirKm9000").toString());
    }

    public double getTrenoKm150() {
        return get("trenoKm150") == null ? 0 : new Double(get("trenoKm150").toString());
    }

    public double getTrenoKm500() {
        return get("trenoKm500") == null ? 0 : new Double(get("trenoKm500").toString());
    }

    public double getTrenoKm1500() {
        return get("trenoKm1500") == null ? 0 : new Double(get("trenoKm1500").toString());
    }

    public double getTrenoKm9000() {
        return get("trenoKm9000") == null ? 0 : new Double(get("trenoKm9000").toString());
    }

    public double getNaveKm500() {
        return get("naveKm500") == null ? 0 : new Double(get("naveKm500").toString());
    }

    public double getNaveKm1500() {
        return get("naveKm1500") == null ? 0 : new Double(get("naveKm1500").toString());
    }

    public double getNaveKm9000() {
        return get("naveKm9000") == null ? 0 : new Double(get("naveKm9000").toString());
    }

    public double getAereoKm1500() {
        return get("aereoKm1500") == null ? 0 : new Double(get("aereoKm1500").toString());
    }

    public double getAereoKm9000() {
        return get("aereoKm9000") == null ? 0 : new Double(get("aereoKm9000").toString());
    }
    
      public void getFurgoneKm30(double val) {
        set("furgoneKm30", val);
    }

    public void getFurgoneKm150(double val) {
        set("furgoneKm150", val);
    }

    public void getFurgoneKm500(double val) {
        set("furgoneKm500", val);
    }

    public void getFurgoneKm1500(double val) {
        set("furgoneKm1500", val);
    }

    public void getFurgoneKm9000(double val) {
        set("furgoneKm9000", val);
    }

    public void getTirKm30(double val) {
        set("tirKm30", val);
    }

    public void getTirKm150(double val) {
        set("tirKm150", val);
    }

    public void getTirKm500(double val) {
        set("tirKm500", val);
    }

    public void getTirKm1500(double val) {
        set("tirKm1500", val);
    }

    public void getTirKm9000(double val) {
        set("tirKm9000", val);
    }

    public void getTrenoKm150(double val) {
        set("trenoKm150", val);
    }

    public void getTrenoKm500(double val) {
        set("trenoKm500", val);
    }

    public void getTrenoKm1500(double val) {
        set("trenoKm1500", val);
    }

    public void getTrenoKm9000(double val) {
        set("trenoKm9000", val);
    }

    public void getNaveKm500(double val) {
        set("naveKm500", val);
    }

    public void getNaveKm1500(double val) {
        set("naveKm1500", val);
    }

    public void getNaveKm9000(double val) {
        set("naveKm9000", val);
    }

    public void getAereoKm1500(double val) {
        set("aereoKm1500", val);
    }

    public void getAereoKm9000(double val) {
         set("aereoKm9000", val);
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