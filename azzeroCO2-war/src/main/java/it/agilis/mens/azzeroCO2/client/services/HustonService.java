package it.agilis.mens.azzeroCO2.client.services;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import it.agilis.mens.azzeroCO2.shared.EMailVTO;
import it.agilis.mens.azzeroCO2.shared.git.GitRepositoryStateModel;
import it.agilis.mens.azzeroCO2.shared.model.OrdineModel;
import it.agilis.mens.azzeroCO2.shared.model.amministrazione.CoefficienteModel;
import it.agilis.mens.azzeroCO2.shared.model.amministrazione.CouponModel;
import it.agilis.mens.azzeroCO2.shared.model.amministrazione.ProgettoDiCompensazioneModel;
import it.agilis.mens.azzeroCO2.shared.model.evento.TipoDiCartaModel;
import it.agilis.mens.azzeroCO2.shared.model.pagamento.PagamentoModel;
import it.agilis.mens.azzeroCO2.shared.model.registrazione.UserInfoModel;
import it.agilis.mens.azzeroCO2.shared.vto.DettaglioVTO;

import java.util.List;
import java.util.Map;

@RemoteServiceRelativePath("huston")
public interface HustonService extends RemoteService {


    void saveCoupon(CouponModel coupon) throws IllegalArgumentException;

    Boolean saveCoupons(List<CouponModel> modifiedRecords) throws IllegalArgumentException;

    Boolean saveCoefficienti(List<CoefficienteModel> coefficienteModels) throws IllegalArgumentException;

    UserInfoModel createNewUser(UserInfoModel registrazioneModeli) throws IllegalArgumentException;

    UserInfoModel getUserInfo(String userInfo, String password) throws IllegalArgumentException;

    List<TipoDiCartaModel> getTipoDiCarta() throws IllegalArgumentException;


    // TODO mmm Migliorare la gestione dello USER
    void disconnectUser() throws IllegalArgumentException;

    Map<String, CoefficienteModel> getCoefficienti() throws IllegalArgumentException;

    List<CouponModel> getListOfCoupon() throws IllegalArgumentException;

    List<ProgettoDiCompensazioneModel> getListOfProgettoDiCompensazione(boolean all) throws IllegalArgumentException;

    List<OrdineModel> getListOfOrdini(UserInfoModel userInfoModel);

    Boolean saveProgettiDiCompensazione(List<ProgettoDiCompensazioneModel> progettiDiCompensaziones);

    GitRepositoryStateModel checkGitRevision();

    DettaglioVTO saveOrdine(DettaglioVTO riepilogo, UserInfoModel userInfoModel);

    void sentMail(EMailVTO email);

    void savePagamento(PagamentoModel pagamentoModel);

    boolean saveUserInfo(UserInfoModel data);

    boolean associaIDProgettoDiCompensazioneImmagine(Long idProgetto, String name);

    DettaglioVTO isPagato(DettaglioVTO riepilogo, UserInfoModel userInfoModel);

    boolean associaIDProgettoDiCompensazionePDF(Long idProgetto, String name);
}

