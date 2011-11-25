package it.agilis.mens.azzeroCO2.shared.model.registrazione;

import com.extjs.gxt.ui.client.data.BaseModel;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 8/24/11
 * Time: 9:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserInfoModel extends BaseModel {


    public String getUserName() {
        return get("userName");
    }

    public void setUserName(String userName) {
        set("userName", userName);
    }

    public String getPassword() {
        return get("password");
    }

    public void setPassword(String password) {
        set("password", password);
    }

    public String getRepassword() {
        return get("repassword");
    }

    public void setRepassword(String repassword) {
        set("repassword", repassword);
    }

    public String getNome() {
        return get("nome");
    }

    public void setNome(String nome) {
        set("nome", nome);
    }

    public String getCognome() {
        return get("cognome");
    }

    public void setCognome(String cognome) {
        set("cognome", cognome);
    }

    public String getRagioneSoc() {
        return get("ragioneSociale");
    }

    public void setRagioneSoc(String ragioneSociale) {
        set("ragioneSociale", ragioneSociale);
    }

    public String getIndirizzo() {
        return get("indirizzo");
    }

    public void setIndirizzo(String indirizzo) {
        set("indirizzo", indirizzo);
    }

    public String getCitta() {
        return get("citta");
    }

    public void setCitta(String citta) {
        set("citta", citta);
    }

    public String getProvincia() {
        return get("provincia");
    }

    public void setProvincia(String provincia) {
        set("provincia", provincia);
    }

    public String getCap() {
        return get("cap");
    }

    public void setCap(String cap) {
        set("cap", cap);
    }

    public String getTelefono() {
        return get("telefono");
    }

    public void setTelefono(String telefono) {
        set("telefono", telefono);
    }

    public String getFax() {
        return get("fax");
    }

    public void setFax(String fax) {
        set("fax", fax);
    }

    public String getCellulare() {
        return get("cellulare");
    }

    public void setCellulare(String cellulare) {
        set("cellulare", cellulare);
    }

    public String getEmail() {
        return get("email");
    }

    public void setEmail(String email) {
        set("email", email);
    }

    public String getReemail() {
        return get("reemail");
    }

    public void setReemail(String reemail) {
        set("reemail", reemail);
    }

    public String getPivaCF() {
        return get("pivaCF");
    }

    public void setPivaCF(String pivaCF) {
        set("pivaCF", pivaCF);
    }

    public Integer getProfilo() {
        return get("profile");
    }

    public void setProfilo(Integer profile) {
        set("profile", profile);
    }

    public void setId(Long id) {
        set("id", id);
    }

    public Long getId() {
        return get("id");
    }

    public void setKCO2Compensati(Double KCO2Compensati) {
        set("KCO2Compensati", KCO2Compensati);
    }

    public Double getKCO2Compensati() {
        return get("KCO2Compensati");
    }
}
