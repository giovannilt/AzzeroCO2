package it.agilis.mens.azzeroCO2.shared.model.evento;

import com.extjs.gxt.ui.client.data.BaseModel;

/**
 * Created by IntelliJ IDEA.
 * User: mapsadmin
 * Date: 11/07/11
 * Time: 10.22
 * To change this template use File | Settings | File Templates.
 */
public class NottiModel extends BaseModel {

    public Long getId() {
        return get("id");
    }

    public void setId(Long id) {
        set("id", id);
    }

    public void setNotti(Integer notti) {
        set("notti", notti);
    }

    public Integer getNotti() {
        return get("notti");
    }
}
