package it.agilis.mens.azzeroCO2.core.entity;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * User: serenadimaida
 * Date: 28/08/11
 * Time: 19:07
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class TrasportoPersone {

    @Id
    @GeneratedValue
    private Long id;

    private String categoria;
    private Integer bus50;
    private Integer bus100;
    private Integer bus250;
    private Integer bus500;
    private Integer auto50;
    private Integer auto100;
    private Integer auto250;
    private Integer auto500;
    private Integer treno50;
    private Integer treno100;
    private Integer treno250;
    private Integer treno500;

    private Integer aereo250;
    private Integer aereo500;

    @ManyToOne
    private Ordine ordine;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Integer getAuto50() {
        return auto50;
    }

    public void setAuto50(Integer auto50) {
        this.auto50 = auto50;
    }

    public Integer getAuto100() {
        return auto100;
    }

    public void setAuto100(Integer auto100) {
        this.auto100 = auto100;
    }

    public Integer getAuto250() {
        return auto250;
    }

    public void setAuto250(Integer auto250) {
        this.auto250 = auto250;
    }

    public Integer getAuto500() {
        return auto500;
    }

    public void setAuto500(Integer auto500) {
        this.auto500 = auto500;
    }





    public Integer getBus50() {
        return bus50;
    }

    public void setBus50(Integer bus50) {
        this.bus50 = bus50;
    }

    public Integer getBus100() {
        return bus100;
    }

    public void setBus100(Integer bus100) {
        this.bus100 = bus100;
    }

    public Integer getBus250() {
        return bus250;
    }

    public void setBus250(Integer bus250) {
        this.bus250 = bus250;
    }

    public Integer getBus500() {
        return bus500;
    }

    public void setBus500(Integer bus500) {
        this.bus500 = bus500;
    }



    public Integer getTreno50() {
        return treno50;
    }

    public void setTreno50(Integer treno50) {
        this.treno50 = treno50;
    }

    public Integer getTreno100() {
        return treno100;
    }

    public void setTreno10(Integer treno100) {
        this.treno100 = treno100;
    }

    public Integer getTreno250() {
        return treno250;
    }

    public void setTreno250(Integer treno250) {
        this.treno250 = treno250;
    }

    public Integer getTreno500() {
        return treno500;
    }

    public void setTreno500(Integer treno500) {
        this.treno500 = treno500;
    }



    public Integer getAereo250() {
        return aereo250;
    }

    public void setAereo250(Integer aereo250) {
        this.aereo250 = aereo250;
    }

    public Integer getAereo500() {
        return aereo500;
    }

    public void setAereo500(Integer aereo500) {
        this.aereo500 = aereo500;
    }


}
