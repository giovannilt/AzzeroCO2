package it.agilis.mens.azzeroCO2.server.services;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import it.agilis.mens.azzeroCO2.client.services.OrdineService;
import it.agilis.mens.azzeroCO2.core.register.impl.AzzeroCO2Register;
import it.agilis.mens.azzeroCO2.server.utils.Utils;
import it.agilis.mens.azzeroCO2.shared.model.amministrazione.OrdineModel;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

/**
 * Created by IntelliJ IDEA.
 * User: serenadimaida
 * Date: 16/08/11
 * Time: 17:57
 * To change this template use File | Settings | File Templates.
 */
public class OrdineServiceImpl extends RemoteServiceServlet implements OrdineService{

    @Autowired
    private AzzeroCO2Register azzeroCO2Register;

    public AzzeroCO2Register getAzzeroCO2Register() {
        return azzeroCO2Register;
    }

    public void setAzzeroCO2Register(AzzeroCO2Register azzeroCO2Register) {
        this.azzeroCO2Register = azzeroCO2Register;
    }

    @Override
    public List<OrdineModel> getOrdini(){
       return Utils.getOrdini(azzeroCO2Register.getOrdini());
    }

    @Override
    public OrdineModel createNewOrdine(){
        UUID uuid =UUID.randomUUID();
        // TODO    return new OrdineModel(uuid.toString());

        return new OrdineModel(); // da sistemare

    }
}
