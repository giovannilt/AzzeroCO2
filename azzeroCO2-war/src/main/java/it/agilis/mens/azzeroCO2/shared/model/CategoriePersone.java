package it.agilis.mens.azzeroCO2.shared.model;

import com.extjs.gxt.ui.client.data.BaseModelData;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 7/2/11
 * Time: 1:04 AM
 * To change this template use File | Settings | File Templates.
 */
public class CategoriePersone extends BaseModelData {

    public CategoriePersone() {

    }

    public CategoriePersone(String name) {
        setName(name);
    }


    public String getName() {
        return get("name");
    }

    public void setName(String name) {
        set("name", name);
    }
}

