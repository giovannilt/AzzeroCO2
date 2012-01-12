package it.agilis.mens.azzeroCO2.shared.model.unaPubblicazione;

import com.extjs.gxt.ui.client.data.BaseModel;
import it.agilis.mens.azzeroCO2.shared.model.evento.TipoDiCartaModel;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 12/01/12
 * Time: 10.46
 * To change this template use File | Settings | File Templates.
 */
public class BigliettiDaVisitaModel extends BaseModel {

    public Long getId() {
        return get("id");
    }

    public void setId(Long id) {
        set("id", id);
    }

    public Integer getTiraturaBiglietti() {
        return get("tiraturaBiglietti") == null ? 0 : new Integer(get("tiraturaBiglietti").toString());
    }

    public void setTiraturaBiglietti(Integer tiraturaBiglietti) {
        set("tiraturaBiglietti", tiraturaBiglietti);
    }

    public Double getGrammaturaBiglietti() {
        return (Double) get("grammaturaBiglietti") == null ? 0 : new Double(get("grammaturaBiglietti").toString());
    }

    public void setGrammaturaBiglietti(Double grammaturaBiglietti) {
        set("grammaturaBiglietti", grammaturaBiglietti);
    }

    public TipoDiCartaModel getTipoDiCartaBiglietti() {
        return get("tipoDiCartaBiglietti");
    }

    public void setTipoDiCartaBiglietti(TipoDiCartaModel tipoDiCartaBiglietti) {
        set("tipoDiCartaBiglietti", tipoDiCartaBiglietti);
    }

    public Integer getTiraturaCartelline() {
        return get("tiraturaCartelline") == null ? 0 : new Integer(get("tiraturaCartelline").toString());
    }

    public void setTiraturaCartelline(Integer tiraturaCartelline) {
        set("tiraturaCartelline", tiraturaCartelline);
    }

    public Double getGrammaturaCartelline() {
        return (Double) get("grammaturaCartelline") == null ? 0 : new Double(get("grammaturaCartelline").toString());
    }

    public void setGrammaturaCartelline(Double grammaturaCartelline) {
        set("grammaturaCartelline", grammaturaCartelline);
    }

    public TipoDiCartaModel getTipoDiCartaCartelline() {
        return get("tipoDiCartaCartelline");
    }

    public void setTipoDiCartaCartelline(TipoDiCartaModel tipoDiCartaCartelline) {
        set("tipoDiCartaCartelline", tipoDiCartaCartelline);
    }

    public boolean isVoid() {
        for (Object value : getProperties().values()) {
            if (value instanceof Double) {
                Double d = (Double) value;
                if (d != 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
