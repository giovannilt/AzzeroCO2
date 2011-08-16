package it.agilis.mens.azzeroCO2.shared;

import it.agilis.mens.azzeroCO2.core.entity.Ordine;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: serenadimaida
 * Date: 16/08/11
 * Time: 21:55
 * To change this template use File | Settings | File Templates.
 */
public class Utils {


    public static  List<it.agilis.mens.azzeroCO2.shared.model.amministrazione.Ordine> getOrdini(List<Ordine> ordini){
        List<it.agilis.mens.azzeroCO2.shared.model.amministrazione.Ordine> ordiniModel= new ArrayList<it.agilis.mens.azzeroCO2.shared.model.amministrazione.Ordine>();

                           // TODO FOR

        for(Ordine ordine: ordini){
           it.agilis.mens.azzeroCO2.shared.model.amministrazione.Ordine o= new it.agilis.mens.azzeroCO2.shared.model.amministrazione.Ordine();
            o.setCliente(ordine.getUtente().getUserName());

            ordiniModel.add(o);
        }


        return ordiniModel;

    }
}
