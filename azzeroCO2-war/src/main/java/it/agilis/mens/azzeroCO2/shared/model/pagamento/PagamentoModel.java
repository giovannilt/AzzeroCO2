package it.agilis.mens.azzeroCO2.shared.model.pagamento;

import com.extjs.gxt.ui.client.data.BaseModel;
import com.google.gwt.core.client.GWT;
import it.agilis.mens.azzeroCO2.client.services.AzzerroCO2UtilsClientHelper;


import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Giovanni La Torre
 * Date: 18/09/11
 * Time: 17.25
 * To change this template use File | Settings | File Templates.
 */
public class PagamentoModel extends BaseModel {
    public static final String key = "8C4796853CD664B2F6AC00C13B268108";
    private String conoscoCO2;

    public PagamentoModel() {
    }

    public PagamentoModel(String importo) {


        setTIPO_PAGAMENTO("CC");
        setMERCHANT_ID("396870600001");
        setORDER_ID("AZZEROCO2." + new Date().getTime());

        setIMPORTO(importo);
        setDIVISA("EUR");
        setABI("03599");
        setITEMS("pagamentoCalcolatore^Pagamento online della propria impronta di CO2^1^" + importo + "^EUR;".toUpperCase());  // DA COMPLETARE CON IMPORTO ED ^EUR
        setURLOK(GWT.getHostPageBaseURL() +  "azzeroCO2/rispostaBancaOK");
        setURLKO(GWT.getHostPageBaseURL() +  "azzeroCO2/rispostaBancaKO");
        setURLACK(GWT.getHostPageBaseURL() + "azzeroCO2/rispostaBancaOK");

        setMAC(AzzerroCO2UtilsClientHelper.getMAC_MD5(this).toUpperCase());

        setEsito(Esito.IN_PAGAMENTO.toString());

    }

    public void setLastUpdate(Date lastUpdate) {
        set("lastUpdate", lastUpdate);
    }

    public Date getLastUpdate() {
        return get("lastUpdate");
    }

    public void setUpdateFromBanca(Date updateFromBanca) {
        set("updateFromBanca", updateFromBanca);
    }

    public Date getUpdateFromBanca() {
        return get("updateFromBanca");
    }

    public String getTIPO_PAGAMENTO() {
        return get("TIPO_PAGAMENTO");
    }

    public void setTIPO_PAGAMENTO(String TIPO_PAGAMENTO) {
        set("TIPO_PAGAMENTO", TIPO_PAGAMENTO);
    }

    public String getMERCHANT_ID() {
        return get("MERCHANT_ID");
    }

    public void setMERCHANT_ID(String MERCHANT_ID) {
        set("MERCHANT_ID", MERCHANT_ID);
    }

    public String getORDER_ID() {
        return get("ORDER_ID");
    }

    public void setORDER_ID(String ORDER_ID) {
        set("ORDER_ID", ORDER_ID);
    }

    public String getIMPORTO() {
        return get("IMPORTO");
    }

    public void setIMPORTO(String IMPORTO) {
        set("IMPORTO", IMPORTO);
    }

    public String getDIVISA() {
        return get("DIVISA");
    }

    public void setDIVISA(String DIVISA) {
        set("DIVISA", DIVISA);
    }

    public String getABI() {
        return get("ABI");
    }

    public void setABI(String ABI) {
        set("ABI", ABI);
    }

    public String getITEMS() {
        return get("ITEMS");
    }

    public void setITEMS(String ITEMS) {
        ITEMS= ITEMS.toUpperCase();
        set("ITEMS", ITEMS);
    }

    public String getURLOK() {
        return get("URLOK");
    }

    public void setURLOK(String URLOK) {
        set("URLOK", URLOK);
    }

    public String getURLKO() {
        return get("URLOK");
    }

    public void setURLKO(String URLKO) {
        set("URLKO", URLKO);
    }

    public String getURLACK() {
        return get("URLACK");
    }

    public void setURLACK(String URLACK) {
        set("URLACK", URLACK);
    }

    public String getMAC() {
        return get("MAC");
    }

    public void setMAC(String MAC) {
        set("MAC", MAC);
    }

    public Double getKgCO2() {
        return get("kgCO2");
    }

    public void setKgCO2(Double kgCO2) {
        set("kgCO2", kgCO2);
    }

    public Long getId() {
        return get("id");
    }

    public void setId(Long id) {
        set("id", id);
    }

    public String getEsito() {
        return get("esito");
    }

    public void setEsito(String esito) {
        set("esito", esito);
    }

    @Override
    public String toString() {
        String _return = new String();

        _return += "TIPO_PAGAMENTO=" + getTIPO_PAGAMENTO() + "&";
        _return += "MAC=" + getMAC() + "&";
        _return += "URLACK=" + getURLACK() + "&";
        _return += "URLKO=" + getURLKO() + "&";
        _return += "URLOK=" + getURLOK() + "&";
        _return += "ITEMS=" + getITEMS() + "&";
        _return += "ABI=" + getABI() + "&";
        _return += "DIVISA=" + getDIVISA() + "&";
        _return += "IMPORTO=" + getIMPORTO() + "&";
        _return += "ORDER_ID=" + getORDER_ID() + "&";
        _return += "MERCHANT_ID=" + getMERCHANT_ID();

        _return = "https://www.payment.fccrt.it/CheckOutEGIPSy.asp?" + _return;
        return _return;

    }

    public String getConoscoCO2() {
        return conoscoCO2;
    }
}

