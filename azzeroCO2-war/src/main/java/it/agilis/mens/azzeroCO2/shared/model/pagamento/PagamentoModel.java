package it.agilis.mens.azzeroCO2.shared.model.pagamento;

import com.extjs.gxt.ui.client.data.BaseModelData;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 9/6/11
 * Time: 3:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class PagamentoModel extends BaseModelData {


    public Long getId() {
        return get("id");
    }

    public void setId(Long id) {
        set("id", id);
    }
}
