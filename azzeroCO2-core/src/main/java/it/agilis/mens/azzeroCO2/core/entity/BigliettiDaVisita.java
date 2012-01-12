package it.agilis.mens.azzeroCO2.core.entity;

import javax.persistence.ManyToOne;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 12/01/12
 * Time: 10.46
 * To change this template use File | Settings | File Templates.
 */
public class BigliettiDaVisita {

    private Long id;
    private Integer tiraturaBiglietti;
    @ManyToOne
    private TipoDiCarta tipoDiCartaBiglietti;
    private Double grammaturaBiglietti;

    private Integer tiraturaCartelline;
    @ManyToOne
    private TipoDiCarta tipoDiCartaCartelline;
    private Double grammaturaCartelline;

    @ManyToOne
    private Ordine ordine;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTiraturaBiglietti() {
        return tiraturaBiglietti;
    }

    public void setTiraturaBiglietti(Integer tiraturaBiglietti) {
        this.tiraturaBiglietti = tiraturaBiglietti;
    }

    public TipoDiCarta getTipoDiCartaBiglietti() {
        return tipoDiCartaBiglietti;
    }

    public void setTipoDiCartaBiglietti(TipoDiCarta tipoDiCartaBiglietti) {
        this.tipoDiCartaBiglietti = tipoDiCartaBiglietti;
    }

    public Double getGrammaturaBiglietti() {
        return grammaturaBiglietti;
    }

    public void setGrammaturaBiglietti(Double grammaturaBiglietti) {
        this.grammaturaBiglietti = grammaturaBiglietti;
    }

    public Integer getTiraturaCartelline() {
        return tiraturaCartelline;
    }

    public void setTiraturaCartelline(Integer tiraturaCartelline) {
        this.tiraturaCartelline = tiraturaCartelline;
    }

    public TipoDiCarta getTipoDiCartaCartelline() {
        return tipoDiCartaCartelline;
    }

    public void setTipoDiCartaCartelline(TipoDiCarta tipoDiCartaCartelline) {
        this.tipoDiCartaCartelline = tipoDiCartaCartelline;
    }

    public Double getGrammaturaCartelline() {
        return grammaturaCartelline;
    }

    public void setGrammaturaCartelline(Double grammaturaCartelline) {
        this.grammaturaCartelline = grammaturaCartelline;
    }

    public Ordine getOrdine() {
        return ordine;
    }

    public void setOrdine(Ordine ordine) {
        this.ordine = ordine;
    }
}
