package it.agilis.mens.azzeroCO2.shared.model.evento;

import com.extjs.gxt.ui.client.data.BaseModelData;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 7/2/11
 * Time: 1:04 AM
 * To change this template use File | Settings | File Templates.
 */
public class PubblicazioniRilegateModel extends BaseModelData {

    public PubblicazioniRilegateModel() {

    }

    public PubblicazioniRilegateModel(String categoria) {
        setCategoria(categoria);
    }


    public String getCategoria() {
        return get("categoria");
    }

    public void setCategoria(String categoria) {
        set("categoria", categoria);
    }
}

