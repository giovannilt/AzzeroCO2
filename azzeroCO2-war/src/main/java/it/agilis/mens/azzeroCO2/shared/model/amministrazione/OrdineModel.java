package it.agilis.mens.azzeroCO2.shared.model.amministrazione;

import com.extjs.gxt.ui.client.data.BaseModelData;
import it.agilis.mens.azzeroCO2.client.forms.amministrazione.UserInfo;
import it.agilis.mens.azzeroCO2.core.entity.ProgettoCompensazione;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 7/2/11
 * Time: 2:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class OrdineModel extends BaseModelData {

    public OrdineModel() {

    }

    public OrdineModel(Date data, UserInfo cliente, ProgettoCompensazione programma, Double kgco2, Double importo) {
        setData(data);
        setCliente(cliente);
        setProgramma(programma);
        setKgco2(kgco2);
        setImporto(importo);
    }




    public Date getData() {
        return get("data");
    }

    public Long getId() {
        return get("id");
    }
    public void setId(Long id){
        set("id", id) ;
    }




    public void setData(Date data) {
        set("data", data);
    }

    public UserInfo getCliente() {
        return get("cliente");
    }

    public void setCliente(UserInfo cliente) {
        set("cliente", cliente);
    }

    public ProgettoCompensazione getProgramma() {
        return get("programma");
    }


    public void setProgramma(ProgettoCompensazione programma) {
        set("programma", programma);
    }


    public Double getKgco2() {
        return get("kgco2");
    }

    public void setKgco2(Double kgco2) {
        set("kgco2", kgco2);
    }


    public Double getImporto() {
        return get("importo");
    }

    public void setImporto(Double importo) {
        set("importo", importo);
    }


}

