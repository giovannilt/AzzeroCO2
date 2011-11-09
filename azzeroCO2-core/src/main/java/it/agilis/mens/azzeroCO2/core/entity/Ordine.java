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

    @OneToOne(cascade ={ CascadeType.ALL}, fetch = FetchType.LAZY)
    private Evento evento;

    @ManyToOne (fetch = FetchType.EAGER)
    private UserInfo utente;

    @OneToOne
    private ProgettoCompensazione progettoCompensazione;

    @OneToMany (cascade ={CascadeType.ALL}, fetch = FetchType.LAZY)
    private List<Pubblicazione> pubblicazioni;

    @OneToOne (cascade ={CascadeType.ALL}, fetch = FetchType.LAZY)
    private TrasportoMerci trasportoMerci;

    @OneToMany (cascade ={CascadeType.ALL}, fetch = FetchType.LAZY)
    private List<TrasportoPersone> trasportoPersone;

    private Date dataOrdine;
    private Boolean editable;
    private Double kgCo2;
    private Double valoreCompensazione;

    @OneToOne (cascade ={CascadeType.ALL}, fetch = FetchType.EAGER)
    private SellaRicevutaDiPagamento ricevutaDiPagamento;

    public SellaRicevutaDiPagamento getRicevutaDiPagamento() {
        return ricevutaDiPagamento;
    }

    public void setRicevutaDiPagamento(SellaRicevutaDiPagamento ricevutaDiPagamento) {
        this.ricevutaDiPagamento = ricevutaDiPagamento;
    }

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

    public ProgettoCompensazione getProgettoCompensazione() {
        return progettoCompensazione;
    }

    public void setProgettoCompensazione(ProgettoCompensazione progettoCompensazione) {
        this.progettoCompensazione = progettoCompensazione;
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

    public Double getKgCo2() {
        return kgCo2;
    }

    public void setKgCo2(Double kgCo2) {
        this.kgCo2 = kgCo2;
    }

    public Double getValoreCompensazione() {
        return valoreCompensazione;
    }

    public void setValoreCompensazione(Double valoreCompensazione) {
        this.valoreCompensazione = valoreCompensazione;
    }

    public TrasportoMerci getTrasportoMerci() {
        return trasportoMerci;
    }

    public void setTrasportoMerci(TrasportoMerci trasportoMerci) {
        this.trasportoMerci = trasportoMerci;
    }
}



