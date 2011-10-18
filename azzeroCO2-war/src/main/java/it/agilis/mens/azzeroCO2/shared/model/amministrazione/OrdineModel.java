package it.agilis.mens.azzeroCO2.shared.model.amministrazione;

import com.extjs.gxt.ui.client.data.BaseModelData;
import it.agilis.mens.azzeroCO2.shared.model.amministrazione.CouponModel;
import it.agilis.mens.azzeroCO2.shared.model.amministrazione.ProgettoDiCompensazioneModel;
import it.agilis.mens.azzeroCO2.shared.model.registrazione.UserInfoModel;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 7/2/11
 * Time: 2:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class OrdineModel extends BaseModelData {

    public int getIndex() {
        return (Integer) get("index");
    }

    public void setIndex(int index) {
        set("index", index);
    }


    public Date getData() {
        return get("data");
    }

    public void setData(Date data) {
        set("data", data);
    }

    public Long getId() {
        return get("id");
    }

    public void setId(Long id) {
        set("id", id);
    }

    public UserInfoModel getUserInfoModel() {
        return get("userInfo");
    }

    public void setUserInfo(UserInfoModel userInfo) {
        set("userInfo", userInfo);
    }

    public ProgettoDiCompensazioneModel getProgettoDiCompensazione() {
        return get("progettoDiCompensazione");
    }

    public void setProgettoDiCompensazione(ProgettoDiCompensazioneModel progettoDiCompensazione) {
        set("progettoDiCompensazione", progettoDiCompensazione);
    }

    public void setCoupon(CouponModel coupon) {
        set("coupon", coupon);
    }

    public CouponModel getCouponModel() {
        return get("coupon");
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
