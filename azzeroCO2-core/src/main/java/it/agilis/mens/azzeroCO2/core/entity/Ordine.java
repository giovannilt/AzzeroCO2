package it.agilis.mens.azzeroCO2.core.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * Created by IntelliJ IDEA.
 * User: serenadimaida
 * Date: 16/08/11
 * Time: 21:41
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Ordine {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private Coupon coupon;

    @OneToOne
    private Sito sito;

    @OneToOne
    private Evento evento;

    @ManyToOne
    private UserInfo utente;

    @OneToMany
    private List<ProgettoCompensazione> progettoCompensaziones;

    @OneToMany
    private List<Pubblicazione> pubblicazioni;

    @OneToMany
    private List<TrasportoPersone> trasportoPersone;

    @ManyToOne
    private ProgettoCompensazione progettoComp;

    private Date dataOrdine;
    private Boolean editable;
    private Long kgCo2;
    private Long valoreCompensazione;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Coupon getCoupon() {
        return coupon;
    }

    public void setCoupon(Coupon coupon) {
        this.coupon = coupon;
    }

    public Sito getSito() {
        return sito;
    }

    public void setSito(Sito sito) {
        this.sito = sito;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public UserInfo getUtente() {
        return utente;
    }

    public void setUtente(UserInfo utente) {
        this.utente = utente;
    }

    public List<ProgettoCompensazione> getProgettoCompensaziones() {
        return progettoCompensaziones;
    }

    public void setProgettoCompensaziones(List<ProgettoCompensazione> progettoCompensaziones) {
        this.progettoCompensaziones = progettoCompensaziones;
    }

    public List<Pubblicazione> getPubblicazioni() {
        return pubblicazioni;
    }

    public void setPubblicazioni(List<Pubblicazione> pubblicazioni) {
        this.pubblicazioni = pubblicazioni;
    }

    public List<TrasportoPersone> getTrasportoPersone() {
        return trasportoPersone;
    }

    public void setTrasportoPersone(List<TrasportoPersone> trasportoPersone) {
        this.trasportoPersone = trasportoPersone;
    }

    public ProgettoCompensazione getProgettoComp() {
        return progettoComp;
    }

    public void setProgettoComp(ProgettoCompensazione progettoComp) {
        this.progettoComp = progettoComp;
    }

    public Date getDataOrdine() {
        return dataOrdine;
    }

    public void setDataOrdine(Date dataOrdine) {
        this.dataOrdine = dataOrdine;
    }

    public Boolean getEditable() {
        return editable;
    }

    public void setEditable(Boolean editable) {
        this.editable = editable;
    }

    public Long getKgCo2() {
        return kgCo2;
    }

    public void setKgCo2(Long kgCo2) {
        this.kgCo2 = kgCo2;
    }

    public Long getValoreCompensazione() {
        return valoreCompensazione;
    }

    public void setValoreCompensazione(Long valoreCompensazione) {
        this.valoreCompensazione = valoreCompensazione;
    }
}



