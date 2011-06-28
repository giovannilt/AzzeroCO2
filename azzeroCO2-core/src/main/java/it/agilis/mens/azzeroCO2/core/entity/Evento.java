package it.agilis.mens.azzeroCO2.core.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 6/25/11
 * Time: 11:47 AM
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Evento {

    @Id
    @GeneratedValue
    private Long id;

    private String nome;
    private String dove;
    private Date inizio;
    private Date fine;
    private String note;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
