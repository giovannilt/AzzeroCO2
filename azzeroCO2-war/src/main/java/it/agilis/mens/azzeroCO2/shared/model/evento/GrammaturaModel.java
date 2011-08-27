package it.agilis.mens.azzeroCO2.shared.model.evento;

import com.extjs.gxt.ui.client.data.BaseModelData;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 7/2/11
 * Time: 12:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class GrammaturaModel extends BaseModelData {


    public Long getId() {
        return get("id");
    }

    public void setId(Long id) {
        set("id", id);
    }

    public Double getGrammatura() {
        return get("grammatura");
    }

    public void setGrammatura(Double grammatura) {
        set("grammatura", grammatura);
    }
}

