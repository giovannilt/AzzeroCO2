package it.agilis.mens.azzeroCO2.shared.model.amministrazione;

import com.extjs.gxt.ui.client.data.BaseModelData;


import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 7/2/11
 * Time: 2:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class Coupon extends BaseModelData {

    public Coupon() {

    }

    public Coupon(String codice, String descrizione,String tipo, Double valore, Date dataInizio, Date dataFine, Boolean attivo) {
        setCodice(codice);
        setDescrizione(descrizione);
        setTipo(tipo);
        setValore(valore);
        setDataInizio(dataInizio);
        setDataFine(dataFine);
        setAttivo(attivo);
    }


    public String getCodice() {
        return get("codice");
    }

    public void setCodice(String codice) {
        set("codice", codice);
    }

    public String getDescrizione() {
        return get("descrizione");
    }

    public void setDescrizione(String descrizione) {
        set("descrizione", descrizione);
    }

    public String getTipo() {
        return get("tipo");
    }

    public void setTipo(String tipo) {
        set("tipo", tipo);
    }


    public Double getValore() {
        return get("valore");
    }

    public void setValore(Double valore) {
        set("valore", valore);
    }


    public Date getDataInizio() {
        return get("dataInizio");
    }

    public void setDataInizio(Date dataInizio) {
        set("dataInizio", dataInizio);
    }


    public Date getDataFine() {
        return get("dataFine");
    }

    public void setDataFine(Date dataFine) {
        set("dataFine", dataFine);
    }


    public Boolean getAttivo() {
        return get("attivo");
    }

    public void setAttivo(Boolean attivo) {
        set("attivo", attivo);
    }


}

