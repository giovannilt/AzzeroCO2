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

    public Double getFurgoneKm30() {
        return get("furgoneKm30");
    }

    public Double getFurgoneKm150() {
        return get("furgoneKm150");
    }

    public Double getFurgoneKm500() {
        return get("furgoneKm500");
    }

    public Double getFurgoneKm1500() {
        return get("furgoneKm1500");
    }

    public Double getFurgoneKm9000() {
        return get("furgoneKm9000");
    }

    public Double getTirKm30() {
        return get("tirKm30");
    }

    public Double getTirKm150() {
        return get("tirKm150");
    }

    public Double getTirKm500() {
        return get("tirKm500");
    }

    public Double getTirKm1500() {
        return get("tirKm1500");
    }

    public Double getTirKm9000() {
        return get("tirKm9000");
    }

    public Double getTrenoKm150() {
        return get("trenoKm150");
    }

    public Double getTrenoKm500() {
        return get("trenoKm500");
    }

    public Double getTrenoKm1500() {
        return get("trenoKm1500");
    }

    public Double getTrenoKm9000() {
        return get("trenoKm9000");
    }


    public Double getNaveKm500() {
        return get("naveKm500");
    }

    public Double getNaveKm1500() {
        return get("naveKm1500");
    }

    public Double getNaveKm9000() {
        return get("NaveKm9000");
    }

    public Double getAereoKm1500() {
        return get("aereoKm1500");
    }

    public Double getAereoKm9000() {
        return get("aereiKm9000");
    }
    /// SET

    public void setFurgoneKm30(Double furgoneKm30) {
        set("furgoneKm30", furgoneKm30);
    }

    public void setFurgoneKm150(Double furgoneKm150) {
        set("furgoneKm150", furgoneKm150);
    }

    public void setFurgoneKm500(Double furgoneKm500) {
        set("furgoneKm500", furgoneKm500);
    }

    public void setFurgoneKm1500(Double furgoneKm1500) {
        set("furgoneKm1500", furgoneKm1500);
    }

    public void setFurgoneKm9000(Double furgoneKm9000) {
        set("furgoneKm9000", furgoneKm9000);
    }

    public void setTirKm30(Double tirKm30) {
        set("tirKm30", tirKm30);
    }

    public void setTirKm150(Double tirKm150) {
        set("tirKm150", tirKm150);
    }

    public void setTirKm500(Double tirKm500) {
        set("tirKm500", tirKm500);
    }

    public void setTirKm1500(Double tirKm1500) {
        set("tirKm1500", tirKm1500);
    }

    public void setTirKm9000(Double tirKm9000) {
        set("tirKm9000", tirKm9000);
    }

    public void setTrenoKm150(Double trenoKm150) {
        set("trenoKm150", trenoKm150);
    }

    public void setTrenoKm500(Double trenoKm500) {
        set("trenoKm500", trenoKm500);
    }

    public void setTrenoKm1500(Double trenoKm1500) {
        set("trenoKm1500", trenoKm1500);
    }

    public void setTrenoKm9000(Double trenoKm9000) {
        set("trenoKm9000", trenoKm9000);
    }


    public void setNaveKm500(Double naveKm500) {
        set("naveKm500", naveKm500);
    }

    public void setNaveKm1500(Double naveKm1500) {
        set("naveKm1500", naveKm1500);
    }

    public void setNaveKm9000(Double naveKm9000) {
        set("NaveKm9000", naveKm9000);
    }

    public void setAereoKm1500(Double aereoKm1500) {
        set("aereoKm1500", aereoKm1500);
    }

    public void setAereoKm9000(Double aereiKm9000) {
        set("aereiKm9000", aereiKm9000);
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