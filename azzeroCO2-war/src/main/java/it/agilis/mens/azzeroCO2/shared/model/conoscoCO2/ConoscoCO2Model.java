package it.agilis.mens.azzeroCO2.shared.model.conoscoCO2;

import com.extjs.gxt.ui.client.data.BaseModel;

/**
 * Created by IntelliJ IDEA.
 * User: mapsadmin
 * Date: 11/07/11
 * Time: 10.22
 * To change this template use File | Settings | File Templates.
 */
public class ConoscoCO2Model extends BaseModel {

    public Long getId() {
        return get("id");
    }

    public void setId(Long id) {
        set("id", id);
    }

    public void setConoscoCO2(Double conoscoCO2) {
        set("conoscoCO2", conoscoCO2);
    }

    public Double getConoscoCO2() {
        return get("conoscoCO2");
    }
}
