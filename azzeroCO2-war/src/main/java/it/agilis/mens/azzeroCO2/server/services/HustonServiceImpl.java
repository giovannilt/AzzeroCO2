package it.agilis.mens.azzeroCO2.server.services;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import it.agilis.mens.azzeroCO2.client.services.AzzerroCO2UtilsClientHelper;
import it.agilis.mens.azzeroCO2.client.services.HustonService;
import it.agilis.mens.azzeroCO2.core.criteria.ProgettoCompensazioneCriteria;
import it.agilis.mens.azzeroCO2.core.entity.Coupon;
import it.agilis.mens.azzeroCO2.core.entity.Esito;
import it.agilis.mens.azzeroCO2.core.entity.Ordine;
import it.agilis.mens.azzeroCO2.core.entity.UserInfo;
import it.agilis.mens.azzeroCO2.core.register.impl.AzzeroCO2Register;
import it.agilis.mens.azzeroCO2.core.register.impl.EmailSender;
import it.agilis.mens.azzeroCO2.server.GitRepositoryState;
import it.agilis.mens.azzeroCO2.server.PropertiesManager;
import it.agilis.mens.azzeroCO2.server.utils.Utils;
import it.agilis.mens.azzeroCO2.shared.EMailVTO;
import it.agilis.mens.azzeroCO2.shared.git.GitRepositoryStateModel;
import it.agilis.mens.azzeroCO2.shared.model.OrdineModel;
import it.agilis.mens.azzeroCO2.shared.model.amministrazione.CoefficienteModel;
import it.agilis.mens.azzeroCO2.shared.model.amministrazione.CouponModel;
import it.agilis.mens.azzeroCO2.shared.model.amministrazione.ProgettoDiCompensazioneModel;
import it.agilis.mens.azzeroCO2.shared.model.evento.TipoDiCartaModel;
import it.agilis.mens.azzeroCO2.shared.model.pagamento.PagamentoModel;
import it.agilis.mens.azzeroCO2.shared.model.registrazione.UserInfoModel;
import it.agilis.mens.azzeroCO2.shared.vto.OrdineVTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.mail.MessagingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 3/15/11
 * Time: 9:57 PM
 * To change this template use File | Settings | File Templates.
 */

public class HustonServiceImpl extends RemoteServiceServlet implements
        HustonService {
    @Autowired
    GitRepositoryState gitRepoState;

    public GitRepositoryState getGitRepoState() {
        return gitRepoState;
    }

    public void setGitRepoState(GitRepositoryState gitRepoState) {
        this.gitRepoState = gitRepoState;
    }

    @Autowired
    @Qualifier("propertiesManager")
    private PropertiesManager propertiesManager;

    public PropertiesManager getPropertiesManager() {
        return propertiesManager;
    }

    public void setPropertiesManager(PropertiesManager propertiesManager) {
        this.propertiesManager = propertiesManager;
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        WebApplicationContext ctx = WebApplicationContextUtils
                .getRequiredWebApplicationContext(config.getServletContext());
        AutowireCapableBeanFactory beanFactory = ctx
                .getAutowireCapableBeanFactory();
        beanFactory.autowireBean(this);
    }

    @Autowired
    @Qualifier("azzeroCO2Register")
    private AzzeroCO2Register azzeroCO2Register;

    public AzzeroCO2Register getAzzeroCO2Register() {
        return azzeroCO2Register;
    }

    public void setAzzeroCO2Register(AzzeroCO2Register azzeroCO2Register) {
        this.azzeroCO2Register = azzeroCO2Register;
    }

    @Autowired
    @Qualifier("emailSender")
    private EmailSender emailSender;


    public EmailSender getEmailSender() {
        return emailSender;
    }

    public void setEmailSender(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Override
    public List<CouponModel> getListOfCoupon() throws IllegalArgumentException {
        try {
            return Utils.getListOfCoupon(azzeroCO2Register.getListOfCoupon());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<ProgettoDiCompensazioneModel> getListOfProgettoDiCompensazione(boolean all) throws IllegalArgumentException {
        try {
            ProgettoCompensazioneCriteria progettoCompensazioneCriteria = new ProgettoCompensazioneCriteria();
            progettoCompensazioneCriteria.setAttivo(!all);
            return Utils.getListOfProgettoDiCompensazione(azzeroCO2Register.getListOfProgettoDiCompensazione(progettoCompensazioneCriteria));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<OrdineModel> getListOfOrdini(UserInfoModel userInfoModel) {
        try {
            return Utils.getListOfOrdini(azzeroCO2Register.getListOfOrdini(Utils.getUserInfo(userInfoModel)));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Boolean saveCoupons(List<CouponModel> modifiedRecords) throws IllegalArgumentException {
        try {
            List<Coupon> coupons = new ArrayList<Coupon>();
            for (CouponModel r : modifiedRecords) {
                coupons.add(Utils.getCoupon(r));
            }
            azzeroCO2Register.saveCoupons(coupons);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Boolean saveCoefficienti(List<CoefficienteModel> coefficienteModels) {
        try {
            azzeroCO2Register.saveCoefficienti(Utils.getCoefficienti(coefficienteModels));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Boolean saveProgettiDiCompensazione(List<ProgettoDiCompensazioneModel> progettiDiCompensaziones) {
        try {
            azzeroCO2Register.saveProgettiCompensazione(Utils.getProgettiDiCompensazioneList(progettiDiCompensaziones));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public UserInfoModel createNewUser(UserInfoModel userInfoModel) throws IllegalArgumentException {
        try {
            UserInfo ui = azzeroCO2Register.getUserInfo(userInfoModel.getUserName());
            if (ui != null) {
                return null;
            }
            return Utils.getUserInfoModel(azzeroCO2Register.saveUserInfo(Utils.getUserInfo(userInfoModel)));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void saveCoupon(CouponModel couponModel) throws IllegalArgumentException {
        try {
            azzeroCO2Register.saveCoupon(Utils.getCoupon(couponModel));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public UserInfoModel getUserInfo(String userName, String password) throws IllegalArgumentException {
        try {
            UserInfoModel userInfoModel = Utils.getUserInfoModel(azzeroCO2Register.getUserInfo(userName));
            if (userInfoModel != null &&
                    userInfoModel.getPassword().contentEquals(password)) {
                return userInfoModel;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public List<TipoDiCartaModel> getTipoDiCarta() throws IllegalArgumentException {
        try {
            return Utils.getTipoDiCarta(azzeroCO2Register.getTipoDiCarta());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void disconnectUser() throws IllegalArgumentException {
        HttpServletRequest req = this.getThreadLocalRequest();
        if (req != null && req.isRequestedSessionIdValid()) {
            req.getSession(false).invalidate();
            try {
                this.doPost(req, this.getThreadLocalResponse());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Map<String, CoefficienteModel> getCoefficienti() {
        try {
            return Utils.getCoefficientiModel(azzeroCO2Register.getCoefficienti());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public GitRepositoryStateModel checkGitRevision() {
        return Utils.getGitState(gitRepoState);
    }

    @Override
    public OrdineVTO saveOrdine(OrdineVTO evento, UserInfoModel user) {
        try {
            OrdineModel ordineModel = AzzerroCO2UtilsClientHelper.getDettaglioModel(evento);
            Ordine ordine = Utils.getOrdine(ordineModel);

            Ordine o = azzeroCO2Register.saveOrUpdateOrdine(ordine, Utils.getUserInfo(user));

            OrdineModel ordineModel1 = Utils.getDettaglioModel(o);
            OrdineVTO ordineVTO = AzzerroCO2UtilsClientHelper.getDettaglioVTO(ordineModel1);

            return ordineVTO;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public void sentMail(EMailVTO email) {
        try {
            emailSender.sendMail(Utils.getEmail(email));
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void savePagamento(PagamentoModel pagamentoModel) {
        try {
            azzeroCO2Register.saveRicevuta(Utils.getRicevuta(pagamentoModel));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean saveUserInfo(UserInfoModel userInfoModel) {
        try {
            azzeroCO2Register.saveUserInfo(Utils.getUserInfo(userInfoModel));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean associaIDProgettoDiCompensazioneImmagine(Long idProgetto, String nomeImmagine) {
        try {
            azzeroCO2Register.associaIDProgettoDiCompensazioneImmagine(idProgetto, nomeImmagine);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean associaIDProgettoDiCompensazionePDF(Long idProgetto, String nomePDF) {
        try {
            azzeroCO2Register.associaIDProgettoDiCompensazionePDF(idProgetto, nomePDF);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public OrdineVTO isPagato(OrdineVTO riepilogo, UserInfoModel userInfoModel) {
        try {
            Ordine o = null;
            if (riepilogo.getOrdineId() == null) {
                OrdineModel ordineModel = AzzerroCO2UtilsClientHelper.getDettaglioModel(riepilogo);
                o = azzeroCO2Register.saveOrUpdateOrdine(Utils.getOrdine(ordineModel), Utils.getUserInfo(userInfoModel));
            } else {
                o = azzeroCO2Register.getOrdineDAO().getOrdineEager(riepilogo.getOrdineId());
            }

            if (o.getRicevutaDiPagamento().getESITO().equals(Esito.PAGATO)) {
                o.getRicevutaDiPagamento().setCertificatoPDF(creaCertificatoInPDF(o));
                azzeroCO2Register.saveOrUpdateOrdine(o, Utils.getUserInfo(userInfoModel));
            }

            OrdineModel ordineModel1 = Utils.getDettaglioModel(o);
            return AzzerroCO2UtilsClientHelper.getDettaglioVTO(ordineModel1);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String creaCertificatoInPDF(Ordine o) {
        return azzeroCO2Register.creaCertificatoPDF(o);
    }
}
