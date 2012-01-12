package it.agilis.mens.azzeroCO2.shared.vto;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 12/01/12
 * Time: 10.46
 * To change this template use File | Settings | File Templates.
 */
public class BigliettiDaVisitaModelVTO {

    private Long id;
    private Integer tiraturaBiglietti;
    private TipoDiCartaVTO tipoDiCartaBiglietti;
    private Double grammaturaBiglietti;

    private Integer tiraturaCartelline;
    private TipoDiCartaVTO tipoDiCartaCartelline;
    private Double grammaturaCartelline;


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

    public TipoDiCartaVTO getTipoDiCartaBiglietti() {
        return tipoDiCartaBiglietti;
    }

    public void setTipoDiCartaBiglietti(TipoDiCartaVTO tipoDiCartaBiglietti) {
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

    public TipoDiCartaVTO getTipoDiCartaCartelline() {
        return tipoDiCartaCartelline;
    }

    public void setTipoDiCartaCartelline(TipoDiCartaVTO tipoDiCartaCartelline) {
        this.tipoDiCartaCartelline = tipoDiCartaCartelline;
    }

    public Double getGrammaturaCartelline() {
        return grammaturaCartelline;
    }

    public void setGrammaturaCartelline(Double grammaturaCartelline) {
        this.grammaturaCartelline = grammaturaCartelline;
    }
}
