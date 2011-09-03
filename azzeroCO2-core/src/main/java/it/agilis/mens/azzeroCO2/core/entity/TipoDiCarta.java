package it.agilis.mens.azzeroCO2.core.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 8/27/11
 * Time: 10:52 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class TipoDiCarta {
    @Id
    @GeneratedValue
    private Long id;
    private String nome;
    private Double parametro;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getParametro() {
        return parametro;
    }

    public void setParametro(Double parametro) {
        this.parametro = parametro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
