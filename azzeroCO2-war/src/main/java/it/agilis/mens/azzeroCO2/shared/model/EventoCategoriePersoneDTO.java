package it.agilis.mens.azzeroCO2.shared.model;

import com.extjs.gxt.ui.client.data.BeanModelTag;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 7/2/11
 * Time: 1:04 AM
 * To change this template use File | Settings | File Templates.
 */
public class EventoCategoriePersoneDTO implements Serializable, BeanModelTag {

    private String name;

    private Boolean isCustom=false;

    private int busPiu50;
    private int autoPiu50;
    private int trenoPiu50;

    private int busPiu100;
    private int autoPiu100;
    private int trenoPiu100;

    private int busPiu250;
    private int autoPiu250;
    private int trenoPiu250;
    private int aereoPiu250;

    private int busPiu500;
    private int autoPiu500;
    private int trenoPiu500;
    private int aereoPiu500;

    public EventoCategoriePersoneDTO(String name) {
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBusPiu50() {
        return busPiu50;
    }

    public void setBusPiu50(int busPiu50) {
        this.busPiu50 = busPiu50;
    }

    public int getAutoPiu50() {
        return autoPiu50;
    }

    public void setAutoPiu50(int autoPiu50) {
        this.autoPiu50 = autoPiu50;
    }

    public int getTrenoPiu50() {
        return trenoPiu50;
    }

    public void setTrenoPiu50(int trenoPiu50) {
        this.trenoPiu50 = trenoPiu50;
    }

    public int getBusPiu100() {
        return busPiu100;
    }

    public void setBusPiu100(int busPiu100) {
        this.busPiu100 = busPiu100;
    }

    public int getAutoPiu100() {
        return autoPiu100;
    }

    public void setAutoPiu100(int autoPiu100) {
        this.autoPiu100 = autoPiu100;
    }

    public int getTrenoPiu100() {
        return trenoPiu100;
    }

    public void setTrenoPiu100(int trenoPiu100) {
        this.trenoPiu100 = trenoPiu100;
    }

    public int getBusPiu250() {
        return busPiu250;
    }

    public void setBusPiu250(int busPiu250) {
        this.busPiu250 = busPiu250;
    }

    public int getAutoPiu250() {
        return autoPiu250;
    }

    public void setAutoPiu250(int autoPiu250) {
        this.autoPiu250 = autoPiu250;
    }

    public int getTrenoPiu250() {
        return trenoPiu250;
    }

    public void setTrenoPiu250(int trenoPiu250) {
        this.trenoPiu250 = trenoPiu250;
    }

    public int getAereoPiu250() {
        return aereoPiu250;
    }

    public void setAereoPiu250(int aereoPiu250) {
        this.aereoPiu250 = aereoPiu250;
    }

    public Boolean getCustom() {
        return isCustom;
    }

    public void setCustom(Boolean custom) {
        isCustom = custom;
    }

    public int getBusPiu500() {
        return busPiu500;
    }

    public void setBusPiu500(int busPiu500) {
        this.busPiu500 = busPiu500;
    }

    public int getAutoPiu500() {
        return autoPiu500;
    }

    public void setAutoPiu500(int autoPiu500) {
        this.autoPiu500 = autoPiu500;
    }

    public int getTrenoPiu500() {
        return trenoPiu500;
    }

    public void setTrenoPiu500(int trenoPiu500) {
        this.trenoPiu500 = trenoPiu500;
    }

    public int getAereoPiu500() {
        return aereoPiu500;
    }

    public void setAereoPiu500(int aereoPiu500) {
        this.aereoPiu500 = aereoPiu500;
    }
}

