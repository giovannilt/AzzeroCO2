package it.agilis.mens.azzeroCO2.core.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by IntelliJ IDEA.
 * User: serenadimaida
 * Date: 28/08/11
 * Time: 19:07
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class TrasportoMerci {

    @Id
    @GeneratedValue
    private Long id;


    private Double furgone30;
    private Double furgone150;
    private Double furgone500;
    private Double furgone1500;
    private Double furgone9000;

    private Double tir30;
    private Double tir150;
    private Double tir500;
    private Double tir1500;
    private Double tir9000;

    private Double treno150;
    private Double treno500;
    private Double treno1500;
    private Double treno9000;


    private Double nave500;
    private Double nave1500;
    private Double nave9000;

    private Double aereo1500;
    private Double aereo9000;

    @ManyToOne
    private Ordine ordine;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getFurgone30() {
        return furgone30;
    }

    public void setFurgone30(Double furgone30) {
        this.furgone30 = furgone30;
    }

    public Double getFurgone150() {
        return furgone150;
    }

    public void setFurgone150(Double furgone150) {
        this.furgone150 = furgone150;
    }

    public Double getFurgone500() {
        return furgone500;
    }

    public void setFurgone500(Double furgone500) {
        this.furgone500 = furgone500;
    }

    public Double getFurgone1500() {
        return furgone1500;
    }

    public void setFurgone1500(Double furgone1500) {
        this.furgone1500 = furgone1500;
    }

    public Double getFurgone9000() {
        return furgone9000;
    }

    public void setFurgone9000(Double furgone9000) {
        this.furgone9000 = furgone9000;
    }

    public Double getTir30() {
        return tir30;
    }

    public void setTir30(Double tir30) {
        this.tir30 = tir30;
    }

    public Double getTir150() {
        return tir150;
    }

    public void setTir150(Double tir150) {
        this.tir150 = tir150;
    }

    public Double getTir500() {
        return tir500;
    }

    public void setTir500(Double tir500) {
        this.tir500 = tir500;
    }

    public Double getTir1500() {
        return tir1500;
    }

    public void setTir1500(Double tir1500) {
        this.tir1500 = tir1500;
    }

    public Double getTir9000() {
        return tir9000;
    }

    public void setTir9000(Double tir9000) {
        this.tir9000 = tir9000;
    }

    public Double getTreno150() {
        return treno150;
    }

    public void setTreno150(Double treno150) {
        this.treno150 = treno150;
    }

    public Double getTreno500() {
        return treno500;
    }

    public void setTreno500(Double treno500) {
        this.treno500 = treno500;
    }

    public Double getTreno1500() {
        return treno1500;
    }

    public void setTreno1500(Double treno1500) {
        this.treno1500 = treno1500;
    }

    public Double getTreno9000() {
        return treno9000;
    }

    public void setTreno9000(Double treno9000) {
        this.treno9000 = treno9000;
    }

    public Double getNave500() {
        return nave500;
    }

    public void setNave500(Double nave500) {
        this.nave500 = nave500;
    }

    public Double getNave1500() {
        return nave1500;
    }

    public void setNave1500(Double nave1500) {
        this.nave1500 = nave1500;
    }

    public Double getNave9000() {
        return nave9000;
    }

    public void setNave9000(Double nave9000) {
        this.nave9000 = nave9000;
    }

    public Double getAereo1500() {
        return aereo1500;
    }

    public void setAereo1500(Double aereo1500) {
        this.aereo1500 = aereo1500;
    }

    public Double getAereo9000() {
        return aereo9000;
    }

    public void setAereo9000(Double aereo9000) {
        this.aereo9000 = aereo9000;
    }

    public Ordine getOrdine() {
        return ordine;
    }

    public void setOrdine(Ordine ordine) {
        this.ordine = ordine;
    }
}
