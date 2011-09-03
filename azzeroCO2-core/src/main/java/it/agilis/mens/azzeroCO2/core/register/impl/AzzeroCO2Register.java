package it.agilis.mens.azzeroCO2.core.register.impl;

import it.agilis.mens.azzeroCO2.core.criteria.OrdineCriteria;
import it.agilis.mens.azzeroCO2.core.dao.*;
import it.agilis.mens.azzeroCO2.core.entity.*;
import it.agilis.mens.azzeroCO2.core.register.IAzzeroCO2Register;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 3/10/11
 * Time: 10:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class AzzeroCO2Register implements IAzzeroCO2Register {

    @Autowired
    private IUserInfoDAO userInfoDAO;
    @Autowired
    private IOrdineDAO ordineDAO;
    @Autowired
    private ICouponDAO couponDAO;
    @Autowired
    private ITipoDiCartaDAO tipoDiCartaDAO;
    @Autowired
    private ICoefficienteDAO coefficienteDAO;
    @Autowired
    private IProgettoCompesnazioneDAO progettoCompensazioneDAO;

    public IProgettoCompesnazioneDAO getProgettoCompensazioneDAO() {
        return progettoCompensazioneDAO;
    }

    public void setProgettoCompensazioneDAO(IProgettoCompesnazioneDAO progettoCompensazioneDAO) {
        this.progettoCompensazioneDAO = progettoCompensazioneDAO;
    }

    public ICoefficienteDAO getCoefficienteDAO() {
        return coefficienteDAO;
    }

    public void setCoefficienteDAO(ICoefficienteDAO coefficienteDAO) {
        this.coefficienteDAO = coefficienteDAO;
    }

    public ITipoDiCartaDAO getTipoDiCartaDAO() {
        return tipoDiCartaDAO;
    }

    public void setTipoDiCartaDAO(ITipoDiCartaDAO tipoDiCartaDAO) {
        this.tipoDiCartaDAO = tipoDiCartaDAO;
    }

    public void setUserInfoDAO(IUserInfoDAO userInfoDAO) {
        this.userInfoDAO = userInfoDAO;
    }


    public void setOrdineDAO(IOrdineDAO ordineDAO) {
        this.ordineDAO = ordineDAO;
    }

    public void setCouponDAO(ICouponDAO couponDAO) {
        this.couponDAO = couponDAO;
    }

    public IUserInfoDAO getUserInfoDAO() {
        return userInfoDAO;
    }

    public IOrdineDAO getOrdineDAO() {
        return ordineDAO;
    }

    public ICouponDAO getCouponDAO() {
        return couponDAO;
    }



    @Override
    public List<Ordine> getOrdini(OrdineCriteria ordineCriteria)throws Exception {
        return ordineDAO.getListOfOrdini(ordineCriteria);
    }
    @Override
    public List<Coupon> getListOfCoupon() throws Exception{
        return couponDAO.getListOfCoupon();
    }


    @Override
    public void saveCoupon(Coupon coupon) throws Exception {
        couponDAO.save(coupon);
    }

    @Override
    public void saveCoupons(List<Coupon> coupons) throws Exception {
        for (Coupon c : coupons) {
            couponDAO.save(c);
        }
    }

    @Override
    public void saveCoefficiente(Coefficiente coefficiente) throws Exception {
        coefficienteDAO.save(coefficiente);
    }

    @Override
    public void saveCoefficienti(List<Coefficiente> coefficienti) throws Exception {
        for(Coefficiente c: coefficienti){
            coefficienteDAO.save(c);
        }
    }

    @Override
    public void saveUserInfo(UserInfo userInfo) throws Exception {
        userInfoDAO.save(userInfo);
    }

    public UserInfo getUserInfo(String userName) {
        return userInfoDAO.findUserInfo(userName);
    }

    /*@Override
    public List<Grammatura> getGrammatura() throws Exception {
        return grammaturaDAO.getGrammatura();
    }
    */
    @Override
    public List<TipoDiCarta> getTipoDiCarta() throws Exception {
        return tipoDiCartaDAO.getTipoDiCarta();
    }

    public List<Coefficiente> getCoefficienti() {
        return coefficienteDAO.getListOfCoefficienti();
    }

    public List<ProgettoCompensazione> getListOfProgettoDiCompensazione() {
        return progettoCompensazioneDAO.getListOfProgettoDiCompensazione();
    }

    public List<Ordine> getListOfOrdini(UserInfo userInfo) {
        OrdineCriteria ordineCriteria= new OrdineCriteria();
        ordineCriteria.setUserInfo(userInfo);
        return ordineDAO.getListOfOrdini(ordineCriteria);
    }
}
