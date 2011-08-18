package it.agilis.mens.azzeroCO2.server.utils;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 8/16/11
 * Time: 10:49 PM
 * To change this template use File | Settings | File Templates.
 */

import it.agilis.mens.azzeroCO2.shared.model.amministrazione.OrdineModel;

import java.util.ArrayList;
import java.util.List;

public class Utils {


    public static List<OrdineModel> getOrdini(List<it.agilis.mens.azzeroCO2.core.entity.Ordine> ordini){
        List<OrdineModel> ordiniModel= new ArrayList<OrdineModel>();

                           // TODO FOR

        for(it.agilis.mens.azzeroCO2.core.entity.Ordine ordine: ordini){
           OrdineModel o= new OrdineModel();
            o.setCliente(ordine.getUtente().getUserName());

            ordiniModel.add(o);
        }


        return ordiniModel;

    }
}
