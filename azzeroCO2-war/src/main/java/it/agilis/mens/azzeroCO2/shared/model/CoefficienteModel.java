package it.agilis.mens.azzeroCO2.shared.model;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 8/31/11
 * Time: 7:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class CoefficienteModel {
    //TODO


    private Long id;

    private String nome;
    private String tipologia;
    private String udm;
    private Double valore;
    private String note;

    private String codice;

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

    public String getTipologia() {
        return tipologia;
    }

    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
    }

    public String getUdm() {
        return udm;
    }

    public void setUdm(String udm) {
        this.udm = udm;
    }

    public Double getValore() {
        return valore;
    }

    public void setValore(Double valore) {
        this.valore = valore;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }
}
