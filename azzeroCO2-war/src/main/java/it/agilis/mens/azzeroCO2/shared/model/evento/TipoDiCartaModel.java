package it.agilis.mens.azzeroCO2.shared.model.evento;

import com.extjs.gxt.ui.client.data.BaseModel;
import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 7/2/11
 * Time: 12:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class TipoDiCartaModel extends BaseModel implements IsSerializable {

    public Long getId() {
        return get("id");
    }

    public void setId(Long id) {
        set("id", id);
    }

    public String getParametro() {
        return get("parametro");
    }

    public void setParametro(String parametro) {
        set("parametro", parametro);
    }


    public String getNome() {
        return get("nome");
    }

    public void setNome(String nome) {
        set("nome", nome);
    }
}

