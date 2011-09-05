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
    private Integer moto60;
    private Integer moto300;
    private Integer bus60;
    private Integer bus300;
    private Integer bus1000;
    private Integer bus3000;
    private Integer bus9000;
    private Integer auto60;
    private Integer auto300;
    private Integer auto1000;
    private Integer auto3000;
    private Integer auto9000;
    private Integer treno60;
    private Integer treno300;
    private Integer treno1000;
    private Integer treno3000;
    private Integer treno9000;

    private Integer aereo1000;
    private Integer aereo3000;
    private Integer aereo9000;

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

    public Integer getAuto60() {
        return auto60;
    }

    public void setAuto60(Integer auto60) {
        this.auto60 = auto60;
    }

    public Integer getMoto60() {
        return moto60;
    }

    public void setMoto60(Integer moto60) {
        this.moto60 = moto60;
    }

    public Integer getMoto300() {
        return moto300;
    }

    public void setMoto300(Integer moto300) {
        this.moto300 = moto300;
    }



    public Integer getAuto300() {
        return auto300;
    }

    public void setAuto300(Integer auto300) {
        this.auto300 = auto300;
    }

    public Integer getAuto1000() {
        return auto1000;
    }

    public void setAuto1000(Integer auto1000) {
        this.auto1000 = auto1000;
    }

    public Integer getAuto3000() {
        return auto3000;
    }

    public void setAuto3000(Integer auto3000) {
        this.auto3000 = auto3000;
    }





    public Integer getBus60() {
        return bus60;
    }

    public void setBus60(Integer bus60) {
        this.bus60 = bus60;
    }

    public Integer getBus300() {
        return bus300;
    }

    public void setBus300(Integer bus300) {
        this.bus300 = bus300;
    }

    public Integer getBus1000() {
        return bus1000;
    }

    public void setBus1000(Integer bus1000) {
        this.bus1000 = bus1000;
    }

    public Integer getBus3000() {
        return bus3000;
    }

    public void setBus3000(Integer bus3000) {
        this.bus3000 = bus3000;
    }



    public Integer getTreno60() {
        return treno60;
    }

    public void setTreno60(Integer treno60) {
        this.treno60 = treno60;
    }

    public Integer getTreno300() {
        return treno300;
    }

    public void setTreno10(Integer treno300) {
        this.treno300 = treno300;
    }

    public Integer getTreno1000() {
        return treno1000;
    }

    public void setTreno1000(Integer treno1000) {
        this.treno1000 = treno1000;
    }

    public Integer getTreno3000() {
        return treno3000;
    }

    public void setTreno3000(Integer treno3000) {
        this.treno3000 = treno3000;
    }



    public Integer getAereo1000() {
        return aereo1000;
    }

    public void setAereo1000(Integer aereo1000) {
        this.aereo1000 = aereo1000;
    }

    public Integer getAereo3000() {
        return aereo3000;
    }

    public void setAereo3000(Integer aereo3000) {
        this.aereo3000 = aereo3000;
    }


    public Integer getBus9000() {
        return bus9000;
    }

    public void setBus9000(Integer bus9000) {
        this.bus9000 = bus9000;
    }

    public Integer getAuto9000() {
        return auto9000;
    }

    public void setAuto9000(Integer auto9000) {
        this.auto9000 = auto9000;
    }

    public Integer getTreno9000() {
        return treno9000;
    }

    public void setTreno9000(Integer treno9000) {
        this.treno9000 = treno9000;
    }
    public Integer getAereo9000() {
        return aereo9000;
    }

    public void setAereo9000(Integer aereo9000) {
        this.aereo9000 = aereo9000;
    }


}
