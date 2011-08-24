package it.agilis.mens.azzeroCO2.shared.model.registrazione;

import com.extjs.gxt.ui.client.data.BaseModel;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 8/24/11
 * Time: 9:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class RegistrazioneModel extends BaseModel {


    public String getUserName() {
         return (String) get("userName");
    }

    public void setUserName(String userName) {
        set("userName", userName);
    }

    public String getPassword() {
        return (String) get("password");
    }

    public void setPassword(String password) {
       set("password", password);
    }

    public String getRepassword() {
        return (String) get("repassword");
    }

    public void setRepassword(String repassword) {
       set("repassword", repassword);
    }

    public String getNome() {
         return (String) get("nome");
    }

    public void setNome(String nome) {
        set("nome", nome);
    }

    public String getCognome() {
         return (String) get("cognome");
    }

    public void setCognome(String cognome) {
        set("cognome", cognome);
    }

    public String getRagioneSoc() {
         return (String) get("ragioneSoc");
    }

    public void setRagioneSoc(String ragioneSoc) {
        set("ragioneSoc", ragioneSoc);
    }

    public String getIndirizzo() {
         return (String) get("indirizzo");
    }

    public void setIndirizzo(String indirizzo) {
        set("indirizzo", indirizzo);
    }

    public String getCitta() {
         return (String) get("citta");
    }

    public void setCitta(String citta) {
        set("citta", citta);
    }

    public String getProvincia() {
         return (String) get("provincia");
    }

    public void setProvincia(String provincia) {
        set("provincia", provincia);
    }

    public String getCap() {
         return (String) get("cap");
    }

    public void setCap(String cap) {
        set("cap", cap);
    }

    public String getTelefono() {
         return (String) get("telefono");
    }

    public void setTelefono(String telefono) {
        set("telefono", telefono);
    }

    public String getFax() {
         return (String) get("fax");
    }

    public void setFax(String fax) {
        set("fax", fax);
    }

    public String getCellulare() {
         return (String) get("cellulare");
    }

    public void setCellulare(String cellulare) {
        set("cellulare", cellulare);
    }

    public String getEmail() {
         return (String) get("email");
    }

    public void setEmail(String email) {
        set("email", email);
    }

    public String getReemail() {
         return (String) get("reemail");
    }

    public void setReemail(String reemail) {
        set("reemail", reemail);
    }

    public String getPartitaIvaCF() {
         return (String) get("partitaIvaCF");
    }

    public void setPartitaIvaCF(String partitaIvaCF) {
        set("partitaIvaCF", partitaIvaCF);
    }

    
}
