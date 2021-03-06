package it.agilis.mens.azzeroCO2.core.register.impl;

import it.agilis.mens.azzeroCO2.core.criteria.OrdineCriteria;
import it.agilis.mens.azzeroCO2.core.criteria.ProgettoCompensazioneCriteria;
import it.agilis.mens.azzeroCO2.core.criteria.SellaRicevutaDiPagamentoCriteria;
import it.agilis.mens.azzeroCO2.core.dao.*;
import it.agilis.mens.azzeroCO2.core.entity.*;
import it.agilis.mens.azzeroCO2.core.register.IAzzeroCO2Register;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
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
    @Autowired
    private ISellaRicevutaDiPagamentoDAO pagamentoDAO;

    public ISellaRicevutaDiPagamentoDAO getPagamentoDAO() {
        return pagamentoDAO;
    }

    public void setPagamentoDAO(ISellaRicevutaDiPagamentoDAO pagamentoDAO) {
        this.pagamentoDAO = pagamentoDAO;
    }

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
    public List<Coupon> getListOfCoupon() throws Exception {
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
    public void saveCoefficienti(List<Coefficiente> coefficienti) throws Exception {
        for (Coefficiente c : coefficienti) {
            coefficienteDAO.save(c);
        }
    }

    @Override
    public UserInfo saveUserInfo(UserInfo userInfo) throws Exception {
        return userInfoDAO.save(userInfo);
    }

    public UserInfo getUserInfo(String userName) {
        UserInfo userInfo = userInfoDAO.findUserInfo(userName);
        if (userInfo == null) {
            return null;
        } else {
            OrdineCriteria ordineCriteria = new OrdineCriteria();
            ordineCriteria.setUserInfo(userInfo);
            userInfo.setOrdini(ordineDAO.getListOfOrdini(ordineCriteria));
            return userInfo;
        }
    }

    @Override
    public List<TipoDiCarta> getTipoDiCarta() throws Exception {
        return tipoDiCartaDAO.getTipoDiCarta();
    }

    @Override
    public List<Coefficiente> getCoefficienti() {
        return coefficienteDAO.getListOfCoefficienti();
    }

    @Override
    public List<ProgettoCompensazione> getListOfProgettoDiCompensazione(ProgettoCompensazioneCriteria criteria) {
        return progettoCompensazioneDAO.getListOfProgettoDiCompensazione(criteria);
    }

    @Override
    public List<Ordine> getListOfOrdini(UserInfo userInfo) {
        OrdineCriteria ordineCriteria = new OrdineCriteria();
        ordineCriteria.setUserInfo(userInfo);
        return ordineDAO.getListOfOrdini(ordineCriteria);
    }

    @Override
    public void saveProgettiCompensazione(List<ProgettoCompensazione> progettiDiCompensazione) throws Exception {
        for (ProgettoCompensazione p : progettiDiCompensazione) {
            progettoCompensazioneDAO.save(p);
        }
    }

    @Override
    public SellaRicevutaDiPagamento getSellaRicevutaDiPagamento(SellaRicevutaDiPagamentoCriteria criteria) {
        return pagamentoDAO.getPagamento(criteria);
    }

    @Override
    public Ordine saveOrUpdateOrdine(Ordine o, UserInfo userInfo) throws Exception {
        o.setLastUpdate(new Date());
        o.setUtente(userInfo);
        return ordineDAO.save(o);
    }

    public void saveRicevuta(SellaRicevutaDiPagamento ricevuta) throws Exception {
        pagamentoDAO.salvaRicevuta(ricevuta);
    }

    public void associaIDProgettoDiCompensazioneImmagine(Long idProgetto, String nomeImmagine) throws Exception {
        progettoCompensazioneDAO.associaIDProgettoDiCompensazioneImmagine(idProgetto, nomeImmagine);
    }

    public Ordine getOrdine(OrdineCriteria ordineCriteria) throws NotFoundException {

        List<Ordine> listOfOrdini = ordineDAO.getListOfOrdini(ordineCriteria);
        if (listOfOrdini.size() > 0) {
            return listOfOrdini.get(0);
        } else {
            throw new NotFoundException("Ordine non esistente:" + ordineCriteria.toString());
        }
    }

    public Ordine getOrdineById(Long idOrdine) throws NotFoundException {
        return ordineDAO.getOrdineEager(idOrdine);
    }
}
