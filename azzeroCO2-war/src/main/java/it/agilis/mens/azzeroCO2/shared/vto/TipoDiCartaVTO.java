package it.agilis.mens.azzeroCO2.shared.vto;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 7/2/11
 * Time: 12:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class TipoDiCartaVTO implements Serializable {

    private Long id;
    private String nome;
    private String parametro;

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

    public String getParametro() {
        return parametro;
    }

    public void setParametro(String parametro) {
        this.parametro = parametro;
    }
}

