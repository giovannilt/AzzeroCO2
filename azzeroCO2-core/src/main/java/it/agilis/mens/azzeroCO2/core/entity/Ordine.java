package it.agilis.mens.azzeroCO2.core.entity;

import javax.persistence.*;
import java.util.Date;


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


    private Date dataOrdine;
    private Boolean editable;
    private Long kgCo2;
    private Long valoreCompensazione;

    @OneToOne
    private Coupon coupon;

    @OneToOne
    private Sito sito;

    @OneToOne
    private Evento evento;

    @ManyToOne
    private UserInfo utente;

    @ManyToOne
    private Pubblicazioni pubblicazioni;


    @ManyToOne
    private ProgettoCompensazione progettoComp;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataOrdine() {
        return dataOrdine;
    }

    public void setDataOrdine(Date dataOrdine) {
        this.dataOrdine = dataOrdine;
    }

    public UserInfo getUtente() {
        return utente;
    }

    public void setUtente(UserInfo utente) {
        this.utente = utente;
    }

    public ProgettoCompensazione getProgettoComp() {
        return progettoComp;
    }

    public void setProgettoComp(ProgettoCompensazione progettoComp) {
        this.progettoComp = progettoComp;
    }

    public Coupon getCoupon() {
        return coupon;
    }

    public void setCoupon(Coupon copon) {
        this.coupon = coupon;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public Sito getSito() {
        return sito;
    }

    public void setSito(Sito sito) {
        this.sito = sito;
    }

    public Pubblicazioni getPubblicazioni() {
        return pubblicazioni;
    }

    public void setPubblicazioni(Pubblicazioni pubblicazioni) {
        this.pubblicazioni = pubblicazioni;
    }


}
