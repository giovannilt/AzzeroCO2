package it.agilis.mens.azzeroCO2.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 7/3/11
 * Time: 6:44 PM
 * To change this template use File | Settings | File Templates.
 */
public interface AzzeroCO2Resources extends ClientBundle {
    public static final AzzeroCO2Resources INSTANCE =  GWT.create(AzzeroCO2Resources.class);

    @Source("it/agilis/mens/azzeroCO2/client/icons/UnEvento.png")
    ImageResource evento();

    @Source("it/agilis/mens/azzeroCO2/client/icons/UnAnnoDiAttivita.png")
    ImageResource unAnnoDiAttivita();

    @Source("it/agilis/mens/azzeroCO2/client/icons/ConoscoLaCO2.png")
    ImageResource conoscoLaCO2();

    @Source("it/agilis/mens/azzeroCO2/client/icons/UnaPubblicazione.png")
    ImageResource unaPubblicazione();

    @Source("it/agilis/mens/azzeroCO2/client/icons/UnSitoWeb.png")
    ImageResource unSitoWeb();
}
