package it.agilis.mens.azzeroCO2.shared.model;

import com.extjs.gxt.ui.client.data.BeanModelTag;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 6/25/11
 * Time: 12:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class EventoDettaglioDTO implements BeanModelTag, Serializable {

    private String nome;
    private String dove;
    private Date inizio;
    private Date fine;
    private String note;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDove() {
        return dove;
    }

    public void setDove(String dove) {
        this.dove = dove;
    }

    public Date getInizio() {
        return inizio;
    }

    public void setInizio(Date inizio) {
        this.inizio = inizio;
    }

    public Date getFine() {
        return fine;
    }

    public void setFine(Date fine) {
        this.fine = fine;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
