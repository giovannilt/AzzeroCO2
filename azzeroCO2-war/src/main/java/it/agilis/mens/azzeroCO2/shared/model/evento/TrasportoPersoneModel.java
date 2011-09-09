package it.agilis.mens.azzeroCO2.shared.model.evento;

import com.extjs.gxt.ui.client.data.BaseModel;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 6/25/11
 * Time: 12:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class TrasportoPersoneModel extends BaseModel {

    public int getAereoKm9000() {
        return get("aereoKm9000") == null ? 0 : new Integer(get("aereoKm9000").toString());
    }

    public void setAereoKm9000(int aereoKm9000) {
        set("aereoKm9000", aereoKm9000);
    }

    public int getAereoKm3000() {
        return get("aereoKm3000") == null ? 0 : new Integer(get("naveKm9000").toString());
    }

    public void setAereoKm1500(int aereoKm3000) {
        set("aereoKm3000", aereoKm3000);
    }

    public int getAereoKm1000() {
        return get("aereoKm1000") == null ? 0 : new Integer(get("aereoKm1000").toString());

    }

    public void setAereoKm1000(int aereoKm1000) {
        set("aereoKm1000", aereoKm1000);
    }

    public int getAutoKm9000() {
        return get("autoKm9000") == null ? 0 : new Integer(get("autoKm9000").toString());
    }

    public void setAutoKm9000(int autoKm9000) {
        set("autoKm9000", autoKm9000);
    }

    public int getAutoKm3000() {
        return get("autoKm3000") == null ? 0 : new Integer(get("autoKm3000").toString());
    }

    public void setAutoKm1500(int autoKm3000) {
        set("autoKm3000", autoKm3000);
    }

    public int getAutoKm1000() {
        return get("autoKm1000") == null ? 0 : new Integer(get("autoKm1000").toString());
    }

    public void setAutoKm1000(int autoKm1000) {
        set("autoKm1000", autoKm1000);
    }

    public int getAutoKm300() {
        return get("autoKm300") == null ? 0 : new Integer(get("autoKm300").toString());
    }

    public void setAutoKm300(int autoKm300) {
        set("autoKm300", autoKm300);
    }

    public int getAutoKm60() {
        return get("autoKm60") == null ? 0 : new Integer(get("autoKm60").toString());
    }

    public void setAutoKm60(int autoKm60) {
        set("autoKm60", autoKm60);
    }

    public int getBusKm9000() {
        return get("busKm9000") == null ? 0 : new Integer(get("busKm9000").toString());

    }

    public void setBusKm9000(int busKm9000) {
        set("busKm9000", busKm9000);
    }

    public int getBusKm3000() {
        return get("busKm3000") == null ? 0 : new Integer(get("busKm3000").toString());

    }

    public void setBusKm3000(int busKm3000) {
        set("busKm3000", busKm3000);
    }

    public int getBusKm1000() {
        return get("busKm1000") == null ? 0 : new Integer(get("busKm1000").toString());

    }

    public void setBusKm1000(int busKm1000) {
        set("busKm1000", busKm1000);
    }

    public int getBusKm300() {
        return get("busKm300") == null ? 0 : new Integer(get("busKm300").toString());
    }

    public void setBusKm300(int busKm300) {
        set("busKm300", busKm300);
    }

    public int getBusKm60() {
        return get("busKm60") == null ? 0 : new Integer(get("busKm60").toString());

    }

    public void setBusKm60(int busKm60) {
        set("busKm60", busKm60);
    }

    public int getTrenoKm9000() {
        return get("trenoKm9000") == null ? 0 : new Integer(get("trenoKm9000").toString());
    }

    public void setTrenoKm9000(int trenoKm9000) {
        set("trenoKm9000", trenoKm9000);
    }

    public int getTrenoKm3000() {
        return get("trenoKm3000") == null ? 0 : new Integer(get("trenoKm3000").toString());

    }

    public void setTrenoKm1500(int trenoKm3000) {
        set("trenoKm3000", trenoKm3000);
    }

    public int getTrenoKm1000() {
        return get("trenoKm1000") == null ? 0 : new Integer(get("trenoKm1000").toString());

    }

    public void setTrenoKm1000(int trenoKm1000) {
        set("trenoKm1000", trenoKm1000);
    }

    public int getTrenoKm300() {
        return get("trenoKm300") == null ? 0 : new Integer(get("trenoKm300").toString());

    }

    public void setTrenoKm300(int trenoKm300) {
        set("trenoKm300", trenoKm300);
    }

    public int getTrenoKm60() {
        return get("trenoKm60") == null ? 0 : new Integer(get("trenoKm60").toString());
    }

    public void setTrenoKm60(int trenoKm60) {
        set("trenoKm60", trenoKm60);
    }

    public void setCategoria(String categoria) {
        set("categoria", categoria);
    }

    public String getCategoria() {
        return (String) get("categoria");
    }

    public String toString() {
        return (String) get("categoria");
    }

    public int getMotoKm300() {
        return get("motoKm300") == null ? 0 : new Integer(get("motoKm300").toString());

    }

    public void setMotoKm300(int motoKm300) {
        set("AutoKm300", motoKm300);
    }

    public int getMotoKm60() {
        return get("motoKm60") == null ? 0 : new Integer(get("motoKm60").toString());
    }

    public void setMotoKm60(int motoKm60) {
        set("motoKm60", motoKm60);
    }

     public boolean isVoid() {
    for (Object value : getProperties().values()) {
            if (value instanceof Integer) {
                Integer d = (Integer) value;
                if (d != 0) {
                    return false;
                }
            }
        }
        return true;
    }
}