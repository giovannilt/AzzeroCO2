package it.agilis.mens.azzeroCO2.core.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 3/10/11
 * Time: 10:10 PM
 * To change this template use File | Settings | File Templates.
 */

@Entity
public class UserInfo implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String userName;
    private String password;
    private String nome;
    private String cognome;
    private String ragSociale;
    private String indirizzo;
    private String citta;
    private String provincia;
    private String cap;
    private String pIvaCF;
    private String telefono;
    private String fax;
    private String cellulare;
    private String email;

    @OneToMany (fetch = FetchType.LAZY)
    private List<Preventivo> preventivi;

    private Profile profile= Profile.Guest;

    @OneToMany (fetch= FetchType.LAZY)
    private List<Ordine> ordini;

    public List<Ordine> getOrdini() {
        return ordini;
    }

    public void setOrdini(List<Ordine> ordini) {
        this.ordini = ordini;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Preventivo> getPreventivi() {
        return preventivi;
    }

    public void setPreventivi(List<Preventivo> preventivi) {
        this.preventivi = preventivi;
    }

    public void setProfile(Profile profile) {
        this.profile=profile;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public void setRagSociale(String ragSociale) {
        this.ragSociale = ragSociale;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public void setCellulare(String cellulare) {
        this.cellulare = cellulare;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getRagSociale() {
        return ragSociale;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public String getCitta() {
        return citta;
    }

    public String getProvincia() {
        return provincia;
    }

    public String getCap() {
        return cap;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getFax() {
        return fax;
    }

    public String getCellulare() {
        return cellulare;
    }

    public String getEmail() {
        return email;
    }

    public String getPIvaCF() {
        return pIvaCF;
    }

    public void setPIvaCF(String pIvaCF) {
        this.pIvaCF = pIvaCF;
    }
}
