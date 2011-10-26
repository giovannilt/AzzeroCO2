package it.agilis.mens.azzeroCO2.shared.vto;


public class ManifestiPieghevoliFogliVTO {

    private Long id;
    private String categoria;
    private Double Altezza;
    private Double Larghezza;
    private Integer numeroDiPagine;
    private Integer tiratura;
    private TipoDiCartaVTO tipoDiCartaVTO;
    private Double Grammatura;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Double getAltezza() {
        return Altezza;
    }

    public void setAltezza(Double altezza) {
        Altezza = altezza;
    }

    public Double getLarghezza() {
        return Larghezza;
    }

    public void setLarghezza(Double larghezza) {
        Larghezza = larghezza;
    }

    public Integer getNumeroDiPagine() {
        return numeroDiPagine;
    }

    public void setNumeroDiPagine(Integer numeroDiPagine) {
        this.numeroDiPagine = numeroDiPagine;
    }

    public Integer getTiratura() {
        return tiratura;
    }

    public void setTiratura(Integer tiratura) {
        this.tiratura = tiratura;
    }

    public TipoDiCartaVTO getTipoDiCartaVTO() {
        return tipoDiCartaVTO;
    }

    public void setTipoDiCartaVTO(TipoDiCartaVTO tipoDiCartaVTO) {
        this.tipoDiCartaVTO = tipoDiCartaVTO;
    }

    public Double getGrammatura() {
        return Grammatura;
    }

    public void setGrammatura(Double grammatura) {
        Grammatura = grammatura;
    }
}
