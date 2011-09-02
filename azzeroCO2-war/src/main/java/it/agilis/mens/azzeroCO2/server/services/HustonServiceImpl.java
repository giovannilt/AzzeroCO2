package it.agilis.mens.azzeroCO2.server.services;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import it.agilis.mens.azzeroCO2.client.services.HustonService;
import it.agilis.mens.azzeroCO2.core.entity.Coupon;
import it.agilis.mens.azzeroCO2.core.entity.UserInfo;
import it.agilis.mens.azzeroCO2.core.register.impl.AzzeroCO2Register;
import it.agilis.mens.azzeroCO2.server.utils.Utils;
import it.agilis.mens.azzeroCO2.shared.model.amministrazione.CoefficienteModel;
import it.agilis.mens.azzeroCO2.shared.model.amministrazione.CouponModel;
import it.agilis.mens.azzeroCO2.shared.model.amministrazione.ProgettoDiCompensazioneModel;
import it.agilis.mens.azzeroCO2.shared.model.evento.TipoDiCartaModel;
import it.agilis.mens.azzeroCO2.shared.model.registrazione.UserInfoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//import it.agilis.mens.azzeroCO2.shared.model.evento.GrammaturaModel;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 3/15/11
 * Time: 9:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class HustonServiceImpl extends RemoteServiceServlet implements
        HustonService {


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
    public List<ProgettoDiCompensazioneModel> getListOfProgettoDiCompensazione() throws IllegalArgumentException {
         try {
            return Utils.getListOfProgettoDiCompensazione(azzeroCO2Register.getListOfProgettoDiCompensazione());
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
    public Boolean createNewUser(UserInfoModel userInfoModel) throws IllegalArgumentException {
        try {
            UserInfo ui = azzeroCO2Register.getUserInfo(userInfoModel.getUserName());
            if (ui != null) {
                return false;
            }
            azzeroCO2Register.saveUserInfo(Utils.getUserInfo(userInfoModel));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
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

        UserInfoModel userInfoModel = Utils.getUserInfoModel(azzeroCO2Register.getUserInfo(userName));

        if (userInfoModel != null &&
                userInfoModel.getPassword().contentEquals(password)) {

            return userInfoModel;

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


}
