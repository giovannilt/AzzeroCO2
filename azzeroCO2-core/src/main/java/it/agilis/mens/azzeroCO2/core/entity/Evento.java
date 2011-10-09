package it.agilis.mens.azzeroCO2.core.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
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
    private String anno;
    private String dove;
    private Date inizio;
    private Date fine;
    private String note;

    private Integer pernottamenti;
    private Double energiaElettrica;
    private Double gasolio;
    private Double gas;

    @OneToOne
    private Ordine ordine;

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

    public String getAnno() {
        return anno;
    }

    public void setAnno(String anno) {
        this.anno = anno;
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

    public Integer getPernottamenti() {
        return pernottamenti;
    }

    public void setPernottamenti(Integer pernottamenti) {
        this.pernottamenti = pernottamenti;
    }

    public Double getEnergiaElettrica() {
        return energiaElettrica;
    }

    public void setEnergiaElettrica(Double energiaElettrica) {
        this.energiaElettrica = energiaElettrica;
    }

    public Double getGasolio() {
        return gasolio;
    }

    public void setGasolio(Double gasolio) {
        this.gasolio = gasolio;
    }

    public Double getGas() {
        return gas;
    }

    public void setGas(Double gas) {
        this.gas = gas;
    }

    public Ordine getOrdine() {
        return ordine;
    }

    public void setOrdine(Ordine ordine) {
        this.ordine = ordine;
    }
}
