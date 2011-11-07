package it.agilis.mens.azzeroCO2.shared.model.pagamento;

import com.extjs.gxt.ui.client.data.BaseModelData;
import com.google.gwt.core.client.GWT;
import it.agilis.mens.azzeroCO2.client.services.AzzerroCO2UtilsClientHelper;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Giovanni La Torre
 * Date: 18/09/11
 * Time: 17.25
 * To change this template use File | Settings | File Templates.
 */
public class PagamentoModel extends BaseModelData {
    public static final String key = "8C4796853CD664B2F6AC00C13B268108";

    public PagamentoModel() {
    }

    public PagamentoModel(String importo) {

        setTIPO_PAGAMENTO("CC");
        setMERCHANT_ID("396870600001");
        setORDER_ID(generateID());

        setIMPORTO(importo);
        setDIVISA("EUR");
        setABI("03599");
        setITEMS("pagamentoCalcolatore^Pagamento online della propria impronta di CO2^1^" + importo + "^EUR");  // DA COMPLETARE CON IMPORTO ED ^EUR
        setURLOK(GWT.getHostPageBaseURL()+"/azzeroCO2/rispostaBancaOK");
        setURLKO(GWT.getHostPageBaseURL()+"/azzeroCO2/rispostaBancaKO");
        setURLACK(GWT.getHostPageBaseURL()+"/azzeroCO2/rispostaBanca");
        try {
            setMAC(new String(AzzerroCO2UtilsClientHelper.getMAC_MD5(this), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();

        }

    }

    private String generateID() {
        String _return = "AZZEROCO2." + new Date().getTime();
        return _return;
    }

  /*  private byte[] getMAC_MD5() {
        MessageDigest algorithm = null;
        byte messageDigest[] = new byte[]{};
        try {
            algorithm = MessageDigest.getInstance("MD5");
            algorithm.reset();

            algorithm.update((getMERCHANT_ID() + getORDER_ID() + getIMPORTO() + getDIVISA() + getABI() + getITEMS() + key).toLowerCase().getBytes());
            messageDigest = algorithm.digest();
            return messageDigest;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return messageDigest;
        }
    }*/

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
}
