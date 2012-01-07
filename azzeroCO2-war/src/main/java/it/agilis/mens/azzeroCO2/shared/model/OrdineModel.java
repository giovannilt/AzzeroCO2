package it.agilis.mens.azzeroCO2.shared.model;

import com.extjs.gxt.ui.client.data.BaseModel;
import it.agilis.mens.azzeroCO2.shared.model.amministrazione.ProgettoDiCompensazioneModel;
import it.agilis.mens.azzeroCO2.shared.model.conoscoCO2.ConoscoCO2Model;
import it.agilis.mens.azzeroCO2.shared.model.evento.*;
import it.agilis.mens.azzeroCO2.shared.model.pagamento.PagamentoModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 6/25/11
 * Time: 12:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class OrdineModel extends BaseModel {

    private EnergiaModel energiaModel;
    private ArrayList<TrasportoPersoneModel> trasportoPersoneModel;
    private NottiModel nottiModel;
    private TrasportoMerciModel trasportoMerciModel;

    private List<PubblicazioniRilegateModel> pubblicazioniRilegateModel;
    private List<ManifestiPieghevoliFogliModel> manifestiPieghevoliFogliModel;
    private ProgettoDiCompensazioneModel progettoDiCompensazioneModel;

    private PagamentoModel pagamentoModel;

    private ConoscoCO2Model conoscoCO2Model;

    public ProgettoDiCompensazioneModel getProgettoDiCompensazioneModel() {
        return progettoDiCompensazioneModel;
    }

    public Long getOrdineId() {
        return get("ordineId");
    }

    public void setOrdineId(Long ordineId) {
        set("ordineId", ordineId);
    }

    public Long getId() {
        return get("id");
    }

    public void setId(Long id) {
        set("id", id);
    }

    public String getNome() {
        return (String) get("nome");
    }

    public void setNome(String nome) {
        set("nome", nome);
    }

    public String getDove() {
        return (String) get("dove");
    }

    public void setDove(String dove) {
        set("dove", dove);
    }

    public Date getLastUpdate() {
        return (Date) get("lastUpdate");
    }

    public void setLastUpdate(Date lastUpdate) {
        set("lastUpdate", lastUpdate);
    }

    public String getNote() {
        return (String) get("note");
    }

    public void setNote(String note) {
        set("note", note);
    }

    public Date getInizio() {
        return (Date) get("inizio");
    }

    public void setInizio(Date inizio) {
        set("inizio", inizio);
    }

    public Date getFine() {
        return (Date) get("fine");
    }

    public void setFine(Date fine) {
        set("fine", fine);
    }

    public void setEnergiaModel(EnergiaModel energiaModel) {
        this.energiaModel = energiaModel;
    }

    public ArrayList<TrasportoPersoneModel> getTrasportoPersoneModel() {
        return trasportoPersoneModel;
    }

    public void setTrasportoPersoneModel(ArrayList<TrasportoPersoneModel> trasportoPersoneModel) {
        this.trasportoPersoneModel = trasportoPersoneModel;
    }

    public NottiModel getNottiModel() {
        return nottiModel;
    }

    public void setNottiModel(NottiModel nottiModel) {
        this.nottiModel = nottiModel;
    }

    public TrasportoMerciModel getTrasportoMerciModel() {
        return trasportoMerciModel;
    }

    public void setTrasportoMerciModel(TrasportoMerciModel trasportoMerciModel) {
        this.trasportoMerciModel = trasportoMerciModel;
    }

    public List<PubblicazioniRilegateModel> getPubblicazioniRilegateModel() {
        return pubblicazioniRilegateModel;
    }

    public void setPubblicazioniRilegateModel(List<PubblicazioniRilegateModel> pubblicazioniRilegateModel) {
        this.pubblicazioniRilegateModel = pubblicazioniRilegateModel;
    }

    public List<ManifestiPieghevoliFogliModel> getManifestiPieghevoliFogliModel() {
        return manifestiPieghevoliFogliModel;
    }

    public void setManifestiPieghevoliFogliModel(List<ManifestiPieghevoliFogliModel> manifestiPieghevoliFogliModel) {
        this.manifestiPieghevoliFogliModel = manifestiPieghevoliFogliModel;
    }

    public EnergiaModel getEnergiaModel() {
        return energiaModel;
    }

    public void setProgettoDiCompensazioneModel(ProgettoDiCompensazioneModel progettoDiCompensazioneModel) {
        this.progettoDiCompensazioneModel = progettoDiCompensazioneModel;
    }

    public PagamentoModel getPagamentoModel() {
        return pagamentoModel;
    }

    public void setPagamentoModel(PagamentoModel pagamentoModel) {
        this.pagamentoModel = pagamentoModel;
    }

    public void setConoscoCO2Model(ConoscoCO2Model conoscoCO2Model) {
        this.conoscoCO2Model = conoscoCO2Model;
    }

    public ConoscoCO2Model getConoscoCO2Model() {
        return conoscoCO2Model;
    }
}
