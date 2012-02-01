package it.agilis.mens.azzeroCO2.client.mvc.controllers;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import it.agilis.mens.azzeroCO2.client.MyInfo;
import it.agilis.mens.azzeroCO2.client.services.AzzeroCO2Constants;
import it.agilis.mens.azzeroCO2.client.services.HustonServiceAsync;
import it.agilis.mens.azzeroCO2.shared.EMailVTO;
import it.agilis.mens.azzeroCO2.shared.git.GitRepositoryStateModel;
import it.agilis.mens.azzeroCO2.shared.model.amministrazione.CoefficienteModel;
import it.agilis.mens.azzeroCO2.shared.model.amministrazione.ProgettoDiCompensazioneModel;
import it.agilis.mens.azzeroCO2.shared.model.registrazione.UserInfoModel;
import it.agilis.mens.azzeroCO2.shared.vto.OrdineVTO;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 8/27/11
 * Time: 3:40 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class BaseController extends Controller {

    private HustonServiceAsync hustonService = Registry.get(AzzeroCO2Constants.HUSTON_SERVICE);
    private UserInfoModel userInfoModel;
    private Map<String, CoefficienteModel> coefficientiMAP = new HashMap<String, CoefficienteModel>();
    private List<ProgettoDiCompensazioneModel> progettiDiCompensazioneList = new ArrayList<ProgettoDiCompensazioneModel>();
    private String info = "";

    protected final NumberFormat number = NumberFormat.getFormat("0.00");

    public UserInfoModel getUserInfoModel() {
        return userInfoModel;
    }

    public void setUserInfoModel(UserInfoModel userInfoModel) {
        this.userInfoModel = userInfoModel;
    }

    public HustonServiceAsync getHustonService() {
        return hustonService;
    }

    public void setHustonService(HustonServiceAsync hustonService) {
        this.hustonService = hustonService;
    }

    public void setCoefficienti() {
        AsyncCallback<Map<String, CoefficienteModel>> aCallback = new AsyncCallback<Map<String, CoefficienteModel>>() {
            public void onFailure(Throwable caught) {
                MyInfo.show("Error", "Errore impossibile connettersi al server", 7000);
            }

            @Override
            public void onSuccess(Map<String, CoefficienteModel> result) {
                coefficientiMAP.clear();
                coefficientiMAP.putAll(result);
            }
        };
        hustonService.getCoefficienti(aCallback);
    }

    public void setProgettiDiCompensazione() {
        AsyncCallback<List<ProgettoDiCompensazioneModel>> aCallback = new AsyncCallback<List<ProgettoDiCompensazioneModel>>() {
            public void onFailure(Throwable caught) {
                MyInfo.show("Error", "Errore impossibile connettersi al server", 7000);
            }

            @Override
            public void onSuccess(List<ProgettoDiCompensazioneModel> result) {
                progettiDiCompensazioneList.clear();
                progettiDiCompensazioneList.addAll(result);
            }
        };
        hustonService.getListOfProgettoDiCompensazione(false, aCallback);
    }

    public void setInfo() {
        AsyncCallback<GitRepositoryStateModel> aCallback = new AsyncCallback<GitRepositoryStateModel>() {
            public void onFailure(Throwable caught) {
                MyInfo.show("Error", "Errore impossibile connettersi al server", 7000);
            }

            @Override
            public void onSuccess(GitRepositoryStateModel result) {
                info = result.toString();
            }
        };
        hustonService.checkGitRevision(aCallback);
    }

    public Map<String, CoefficienteModel> getCoefficientiMAP() {
        return coefficientiMAP;
    }

    public void setCoefficientiMAP(Map<String, CoefficienteModel> coefficientiMAP) {
        this.coefficientiMAP = coefficientiMAP;
    }

    public List<ProgettoDiCompensazioneModel> getProgettiDiCompensazioneList() {
        return progettiDiCompensazioneList;
    }

    public void setProgettiDiCompensazioneList(List<ProgettoDiCompensazioneModel> progettiDiCompensazioneList) {
        this.progettiDiCompensazioneList = progettiDiCompensazioneList;
    }

    public String getInfo() {
        return info;
    }

    public void sentMail(OrdineVTO result) {
        EMailVTO data = new EMailVTO();
        /*data.setBody(result.getNome() + " /n " +
                "" + GWT.getModuleBaseURL() + "downloadCertificato?certificato=" + result.getPagamentoModel().getCertificatoPDF() +
                "");   */
        data.setBody("Ciao " + userInfoModel.getNome() + ",\n" +
                "\n" +
                "l'operazione di compensazione che hai richiesto è andata a buon fine.\n" +
                "Di seguito il riepilogo dei dati: \n" +
                "\n" +
                "Nome: " + userInfoModel.getNome() + "\n" +
                "Cognome: " + userInfoModel.getCognome() + "\n" +
                "Società: " + userInfoModel.getRagioneSociale() + "\n" +
                "Mail: " + userInfoModel.getEmail() + "\n" +
                "Indirizzo: " + userInfoModel.getIndirizzo() + "\n" +
                "CAP: " + userInfoModel.getCap() + "\n" +
                "Città: " + userInfoModel.getCitta() + "\n" +
                "Codice Fiscale: " + userInfoModel.getPivaCF() + "\n" +
                "Partita IVA: " + userInfoModel.getPivaCF() + "\n" +
                "\n" +
                "Progetto: " + result.getProgettoDiCompensazioneModel().getNome() + "\n" +
                "\n" +
                "Totale kgCO2*: " + result.getPagamentoModel().getKgCO2() + "\n" +
                "Crediti di emissione: " + result.getPagamentoModel().getKgCO2() / 1000 + "\n" +
                "Euro: " + result.getPagamentoModel().getIMPORTO() + "\n" +
                "\n" +
                "Scarica tuo certificato di compensazione: \n" +
                GWT.getModuleBaseURL() + "downloadCertificato?certificato=" + result.getPagamentoModel().getCertificatoPDF() + "\n" +
                "\n" +
                "Scarica la scheda informativa del progetto di compensazione che hai scelto: \n" +
                GWT.getModuleBaseURL() + "ImmaginiProgetti/" + result.getProgettoDiCompensazioneModel().getPdfUrl() +
                "\n\n" +
                "*La CO2 che viene presa in cosiderazione è CO2 equivalente (CO2eq), ovvero l'indice che rappresenta l'impatto in atmosfera di tutti i gas serra: quindi non solo il biossido di carbonio, ma anche il metano, i perfluorocarburi e l'ossido di diazoto.\n" +
                "\n" +
                "\n" +
                "Grazie e arrivederci!\n" +
                "-- \n" +
                "\n" +
                "AzzeroCO2 è una società di consulenza energetico-ambientale che offre ai suoi clienti la possibilità di ridurre le emissioni ottimizzando i costi di gestione e le risorse a disposizione. Per saperne di più visita il nostro sito: www.azzeroco2.it");

        data.setFromUser("no-reply@azzeroco2.it");
        data.setSubject("Conferma ordine compensazione: " + userInfoModel.getRagioneSociale());
        data.setToUser(Arrays.asList(userInfoModel.getEmail(), "serena@mensagilis.it", "giovanni@mensagilis.it"));

        /**
         *  • marketing@azzeroco2.it
         *  • info@azzeroco2.it
         * */

        AsyncCallback<Void> aCallback = new AsyncCallback<Void>() {
            public void onFailure(Throwable caught) {
                MyInfo.show("Error", "Errore impossibile connettersi al server.", 7000);
            }

            @Override
            public void onSuccess(Void aVoid) {
                MyInfo.show("Email Inviata Con successo.");
            }
        };
        hustonService.sentMail(data, aCallback);
    }


}