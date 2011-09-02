package it.agilis.mens.azzeroCO2.shared.model.amministrazione;

import com.extjs.gxt.ui.client.data.BaseModelData;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 8/31/11
 * Time: 7:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class CoefficienteModel extends BaseModelData {

    public Long getId() {
        return get("id");
    }

    public void setId(Long id) {
        set("id", id);
    }

    public String getNome() {
        return get("nome");
    }

    public void setNome(String nome) {
        set("nome", nome);
    }

    public String getTipologia() {
        return get("tipologia");
    }

    public void setTipologia(String tipologia) {
        set("tipologia", tipologia);
    }

    public String getUdm() {
        return get("udm");
    }

    public void setUdm(String udm) {
        set("udm", udm);
    }

    public Double getValore() {
        return get("valore");
    }

    public void setValore(Double valore) {
        set("valore", valore);
    }

    public String getNote() {
        return get("note");
    }

    public void setNote(String note) {
        set("note", note);
    }

    public String getCodice() {
        return get("codice");
    }

    public void setCodice(String codice) {
        set("codice", codice);
    }
}
