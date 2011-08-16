package it.agilis.mens.azzeroCO2.client.services;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import it.agilis.mens.azzeroCO2.shared.model.amministrazione.Ordine;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: serenadimaida
 * Date: 16/08/11
 * Time: 17:50
 * To change this template use File | Settings | File Templates.
 */
@RemoteServiceRelativePath("ordine-service")
public interface OrdineService extends RemoteService{
    Ordine createNewOrdine();

    List<Ordine> getOrdini();

}
