package it.agilis.mens.azzeroCO2.shared.model.amministrazione;

import com.extjs.gxt.ui.client.data.BaseModelData;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 7/2/11
 * Time: 2:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class Ordine extends BaseModelData {

    public Ordine() {

    }

    public Ordine(Date data, String cliente, String programma, Double kgco2, Double importo) {

        setData(data);
        setCliente(cliente);
        setProgramma(programma);
        setKgco2(kgco2);
        setImporto(importo);
    }


    public String getData() {
        return get("data");
    }

    public void setData(Date data) {
        set("data", data);
    }

    public String getCliente() {
        return get("cliente");
    }

    public void setCliente(String cliente) {
        set("cliente", cliente);
    }

    public String getProgramma() {
        return get("programma");
    }

    public void setProgramma(String programma) {
        set("programma", programma);
    }


    public Double getKgco2() {
        return get("kgco2");
    }

    public void setKgco2(Double kgco2) {
        set("kgco2", kgco2);
    }


    public Double getImporto() {
        return get("importo");
    }

    public void setImporto(Double importo) {
        set("importo", importo);
    }





}

