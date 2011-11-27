package it.agilis.mens.azzeroCO2.core.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 9/6/11
 * Time: 3:07 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class SellaRicevutaDiPagamento {
    @Id
    @GeneratedValue
    private Long id;

    private String TRANSACTION_ID;
    private String MERCHANT_ID;
    private String ORDER_ID;
    private String IMPORTO;
    private String DIVISA;
    private String MAC;
    private Esito ESITO;
    private String TIPO_PAGAMENTO;
    private String ABI;
    private String ITEMS;
    private String COD_AUT;

    public String getTIPO_PAGAMENTO() {
        return TIPO_PAGAMENTO;
    }

    public void setTIPO_PAGAMENTO(String TIPO_PAGAMENTO) {
        this.TIPO_PAGAMENTO = TIPO_PAGAMENTO;
    }

    public String getABI() {
        return ABI;
    }

    public void setABI(String ABI) {
        this.ABI = ABI;
    }

    public String getITEMS() {
        return ITEMS;
    }

    public void setITEMS(String ITEMS) {
        this.ITEMS = ITEMS;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTRANSACTION_ID() {
        return TRANSACTION_ID;
    }

    public void setTRANSACTION_ID(String TRANSACTION_ID) {
        this.TRANSACTION_ID = TRANSACTION_ID;
    }

    public String getMERCHANT_ID() {
        return MERCHANT_ID;
    }

    public void setMERCHANT_ID(String MERCHANT_ID) {
        this.MERCHANT_ID = MERCHANT_ID;
    }

    public String getORDER_ID() {
        return ORDER_ID;
    }

    public void setORDER_ID(String ORDER_ID) {
        this.ORDER_ID = ORDER_ID;
    }

    public String getCOD_AUT() {
        return COD_AUT;
    }

    public void setCOD_AUT(String COD_AUT) {
        this.COD_AUT = COD_AUT;
    }

    public String getIMPORTO() {
        return IMPORTO;
    }

    public void setIMPORTO(String IMPORTO) {
        this.IMPORTO = IMPORTO;
    }

    public String getDIVISA() {
        return DIVISA;
    }

    public void setDIVISA(String DIVISA) {
        this.DIVISA = DIVISA;
    }

    public String getMAC() {
        return MAC;
    }

    public void setMAC(String MAC) {
        this.MAC = MAC;
    }

    public void setEsito(Esito esito) {
        this.ESITO = esito;
    }

    public Esito getESITO() {
        return ESITO;
    }


}
