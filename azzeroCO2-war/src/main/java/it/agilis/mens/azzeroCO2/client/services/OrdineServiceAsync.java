package it.agilis.mens.azzeroCO2.client.services;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import it.agilis.mens.azzeroCO2.shared.model.amministrazione.Ordine;

import java.util.List;

public interface OrdineServiceAsync {
    void createNewOrdine(AsyncCallback<Ordine> callback);

    void getOrdini(AsyncCallback<List<Ordine>> async);
}
