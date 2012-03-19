package it.agilis.mens.azzeroCO2.client.mvc.views;

import it.agilis.mens.azzeroCO2.shared.model.RiepilogoModel;
import it.agilis.mens.azzeroCO2.shared.model.amministrazione.CoefficienteModel;

import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 3/19/12
 * Time: 8:05 PM
 * To change this template use File | Settings | File Templates.
 */
public interface  BaseView  {

    List<RiepilogoModel> riepilogo(Map<String, CoefficienteModel> coefficienti) ;

}
