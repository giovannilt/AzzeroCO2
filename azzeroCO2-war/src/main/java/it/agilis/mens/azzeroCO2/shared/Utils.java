package it.agilis.mens.azzeroCO2.shared;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 8/16/11
 * Time: 10:49 PM
 * To change this template use File | Settings | File Templates.
 */

import it.agilis.mens.azzeroCO2.shared.model.amministrazione.Ordine;

import java.util.ArrayList;
import java.util.List;

public class Utils {


    public static List<Ordine> getOrdini(List<it.agilis.mens.azzeroCO2.core.entity.Ordine> ordini){
        List<it.agilis.mens.azzeroCO2.shared.model.amministrazione.Ordine> ordiniModel= new ArrayList<Ordine>();

                           // TODO FOR

        for(it.agilis.mens.azzeroCO2.core.entity.Ordine ordine: ordini){
           it.agilis.mens.azzeroCO2.shared.model.amministrazione.Ordine o= new it.agilis.mens.azzeroCO2.shared.model.amministrazione.Ordine();
            o.setCliente(ordine.getUtente().getUserName());

            ordiniModel.add(o);
        }


        return ordiniModel;

    }
}
