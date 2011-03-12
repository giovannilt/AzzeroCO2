package it.agilis.mens.azzeroCO2.core.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 3/11/11
 * Time: 3:12 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Calcolo {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Preventivo preventivo;

    @ManyToOne
    private TipoDiCalcolo tipoDiCalcolo;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Preventivo getPreventivo() {
        return preventivo;
    }

    public void setPreventivo(Preventivo preventivo) {
        this.preventivo = preventivo;
    }

    public TipoDiCalcolo getTipoDiCalcolo() {
        return tipoDiCalcolo;
    }

    public void setTipoDiCalcolo(TipoDiCalcolo tipoDiCalcolo) {
        this.tipoDiCalcolo = tipoDiCalcolo;
    }
}
