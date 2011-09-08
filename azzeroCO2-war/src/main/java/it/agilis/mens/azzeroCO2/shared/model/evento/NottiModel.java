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
    private Integer notti;

    public void setNotti(Integer notti) {
        set("notti", notti);
    }

    public int getNotti() {
        return get("notti") == null ? 0 : new Integer(get("notti").toString());

    }
}
