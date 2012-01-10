package it.agilis.mens.azzeroCO2.shared.model.sitoWeb;

import com.extjs.gxt.ui.client.data.BaseModel;

/**
 * Created by IntelliJ IDEA.
 * User: mapsadmin
 * Date: 11/07/11
 * Time: 10.22
 * To change this template use File | Settings | File Templates.
 */
public class SitoWebModel extends BaseModel {

    public Long getId() {
        return get("id");
    }

    public void setId(Long id) {
        set("id", id);
    }

    public void setVisitatori(Integer visitatori) {
        set("visitatori", visitatori);
    }

    public Integer getVisitatori() {
        return get("visitatori");
    }
}
