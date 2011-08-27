package it.agilis.mens.azzeroCO2.core.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 8/27/11
 * Time: 10:55 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Pubblicazione {

    @Id
    @GeneratedValue
    private Long id;

    private Double altezza;

    @OneToMany
    private List<TipoDiCarta> tipoDiCarta;

    @OneToMany
    private List<Grammatura> grammatura;

    private Long tiratura;

    @OneToMany
    private List<TipoDiCarta> tipoDiCartaCopertina;

    @OneToMany
    private List<Grammatura> grammaturaCopertina;

    private String tipo;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAltezza() {
        return altezza;
    }

    public void setAltezza(Double altezza) {
        this.altezza = altezza;
    }

    public List<TipoDiCarta> getTipoDiCarta() {
        return tipoDiCarta;
    }

    public void setTipoDiCarta(List<TipoDiCarta> tipoDiCarta) {
        this.tipoDiCarta = tipoDiCarta;
    }

    public List<Grammatura> getGrammatura() {
        return grammatura;
    }

    public void setGrammatura(List<Grammatura> grammatura) {
        this.grammatura = grammatura;
    }

    public Long getTiratura() {
        return tiratura;
    }

    public void setTiratura(Long tiratura) {
        this.tiratura = tiratura;
    }

    public List<TipoDiCarta> getTipoDiCartaCopertina() {
        return tipoDiCartaCopertina;
    }

    public void setTipoDiCartaCopertina(List<TipoDiCarta> tipoDiCartaCopertina) {
        this.tipoDiCartaCopertina = tipoDiCartaCopertina;
    }

    public List<Grammatura> getGrammaturaCopertina() {
        return grammaturaCopertina;
    }

    public void setGrammaturaCopertina(List<Grammatura> grammaturaCopertina) {
        this.grammaturaCopertina = grammaturaCopertina;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
