package it.agilis.mens.azzeroCO2.shared.vto;

import it.agilis.mens.azzeroCO2.shared.model.amministrazione.ProgettoDiCompensazioneModel;
import it.agilis.mens.azzeroCO2.shared.model.conoscoCO2.ConoscoCO2Model;
import it.agilis.mens.azzeroCO2.shared.model.evento.EnergiaModel;
import it.agilis.mens.azzeroCO2.shared.model.evento.NottiModel;
import it.agilis.mens.azzeroCO2.shared.model.evento.TrasportoMerciModel;
import it.agilis.mens.azzeroCO2.shared.model.evento.TrasportoPersoneModel;
import it.agilis.mens.azzeroCO2.shared.model.pagamento.PagamentoModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 10/26/11
 * Time: 7:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class DettaglioVTO implements Serializable {

    private EnergiaModel energiaModel;
    private ArrayList<TrasportoPersoneModel> trasportoPersoneModel;
    private NottiModel nottiModel;
    private TrasportoMerciModel trasportoMerciModel;

    private ArrayList<PubblicazioniRilegateVTO> pubblicazioniRilegateVTO;
    private ArrayList<ManifestiPieghevoliFogliVTO> manifestiPieghevoliFogliVTO;
    private String dove;
    private Date inizio;
    private Long id;
    private Date fine;
    private String nome;
    private String note;
    private Long ordineId;
    private ProgettoDiCompensazioneModel progettoDiCompensazioneModel;
    private PagamentoModel pagamentoModel;
    private ConoscoCO2Model conoscoCO2Model;
    private String eventiType;

    public ProgettoDiCompensazioneModel getProgettoDiCompensazioneModel() {
        return progettoDiCompensazioneModel;
    }

    public void setOrdineId(Long ordineId) {
        this.ordineId = ordineId;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setDove(String dove) {
        this.dove = dove;
    }

    public void setInizio(Date inizio) {
        this.inizio = inizio;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFine(Date fine) {
        this.fine = fine;
    }

    public EnergiaModel getEnergiaModel() {
        return energiaModel;
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

    public ArrayList<PubblicazioniRilegateVTO> getPubblicazioniRilegateVTO() {
        return pubblicazioniRilegateVTO;
    }

    public void setPubblicazioniRilegateVTO(ArrayList<PubblicazioniRilegateVTO> pubblicazioniRilegateVTO) {
        this.pubblicazioniRilegateVTO = pubblicazioniRilegateVTO;
    }

    public ArrayList<ManifestiPieghevoliFogliVTO> getManifestiPieghevoliFogliVTO() {
        return manifestiPieghevoliFogliVTO;
    }

    public void setManifestiPieghevoliFogliVTO(ArrayList<ManifestiPieghevoliFogliVTO> manifestiPieghevoliFogliVTO) {
        this.manifestiPieghevoliFogliVTO = manifestiPieghevoliFogliVTO;
    }

    public String getDove() {
        return dove;
    }

    public Date getInizio() {
        return inizio;
    }

    public Long getId() {
        return id;
    }

    public Date getFine() {
        return fine;
    }

    public String getNome() {
        return nome;
    }

    public String getNote() {
        return note;
    }

    public Long getOrdineId() {
        return ordineId;
    }

    public void setProgettoDiCompensazioneModel(ProgettoDiCompensazioneModel progettoDiCompensazioneModel) {
        this.progettoDiCompensazioneModel = progettoDiCompensazioneModel;
    }

    public void setSellaRicevutaDiPagamento(PagamentoModel pagamentoModel) {
        this.pagamentoModel = pagamentoModel;
    }

    public PagamentoModel getPagamentoModel() {
        return pagamentoModel;
    }

    public void setConoscoCO2(ConoscoCO2Model conoscoCO2Model) {
        this.conoscoCO2Model = conoscoCO2Model;
    }

    public ConoscoCO2Model getConoscoCO2Model() {
        return conoscoCO2Model;
    }

    public String getEventiType() {
        return eventiType;
    }

    public void setEventiType(String eventiType) {
        this.eventiType = eventiType;

    }
}
