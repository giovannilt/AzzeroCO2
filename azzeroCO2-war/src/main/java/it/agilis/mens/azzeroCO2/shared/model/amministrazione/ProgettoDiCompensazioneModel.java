package it.agilis.mens.azzeroCO2.shared.model.amministrazione;

import com.extjs.gxt.ui.client.data.BaseModelData;


/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 7/2/11
 * Time: 6:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class ProgettoDiCompensazioneModel extends BaseModelData {

    public Double getPrezzo() {
        return get("prezzo");
    }

    public void setPrezzo(Double prezzo) {
        set("prezzo", prezzo);
    }


    public String getNome() {
        return get("nome");
    }

    public void setNome(String nome) {
        set("nome", nome);
    }

    public String getDescrizione() {
        return get("descrizione");
    }

    public void setDescrizione(String descrizione) {
        set("descrizione", descrizione);
    }

    public Boolean getAttivo() {
        return get("attivo");
    }

    public void setAttivo(Boolean attivo) {
        set("attivo", attivo);
    }

    public Long getId() {
        return get("id");
    }

    public void setId(Long id) {
        set("id", id);
    }

    public void setImageUrl(String imageUrl) {
        set("imageUrl", imageUrl);
    }

    public String getImageUrl() {
        return get("imageUrl");
    }

    public void setPdfUrl(String pdfUrl) {
        set("pdfUrl", pdfUrl);
    }

    public String getPdfUrl() {
        return get("pdfUrl");
    }
}
