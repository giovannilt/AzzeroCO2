package it.agilis.mens.azzeroCO2.shared.model.evento;

import com.extjs.gxt.ui.client.data.BaseModel;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 6/25/11
 * Time: 12:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class DettaglioModel extends BaseModel {

    private String nome;
    private String dove;
    private Date inizio;
    private Date fine;
    private String note;

    public String getNome() {
        return (String) get("nome");
    }

    public void setNome(String nome) {
        set("nome", nome);
    }

    public String getDove() {
        return (String) get("dove");
    }

    public void setDove(String dove) {
        set("dove", dove);
    }

    public String getNote() {
        return (String) get("note");
    }

    public void setNote(String note) {
        set("note", note);
    }

    public Date getInizio() {
        return (Date) get("inizio");
    }

    public void setInizio(Date inizio) {
        set("inizio", inizio);
    }

    public Date getFine() {
        return (Date) get("fine");
    }

    public void setFine(Date fine) {
        set("fine", fine);
    }

}
