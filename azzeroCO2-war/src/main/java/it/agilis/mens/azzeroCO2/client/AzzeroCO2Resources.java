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
    public static final AzzeroCO2Resources INSTANCE = GWT.create(AzzeroCO2Resources.class);

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

    @Source("it/agilis/mens/azzeroCO2/client/icons/Check.png")
    @ImageResource.ImageOptions(flipRtl = true)
    ImageResource check();

    @Source("it/agilis/mens/azzeroCO2/client/icons/AzzeroCO2Stemp.png")
    @ImageResource.ImageOptions(flipRtl = true, width = 100, height = 100)
    ImageResource azzeroCO2Stemp();

    @Source("it/agilis/mens/azzeroCO2/client/icons/foto-alberi-1.png")
    @ImageResource.ImageOptions(flipRtl = true, width = 450, height = 450)
    ImageResource fotoAlbero();

    @Source("it/agilis/mens/azzeroCO2/client/imgs/Aereo.png")
    @ImageResource.ImageOptions(flipRtl = true)
    ImageResource aereo();

    @Source("it/agilis/mens/azzeroCO2/client/imgs/Automobile.png")
    @ImageResource.ImageOptions(flipRtl = true)
    ImageResource automobile();

    @Source("it/agilis/mens/azzeroCO2/client/imgs/Bus.png")
    @ImageResource.ImageOptions(flipRtl = true)
    ImageResource bus();

    @Source("it/agilis/mens/azzeroCO2/client/imgs/Tir.png")
    @ImageResource.ImageOptions(flipRtl = true)
    ImageResource tir();

    @Source("it/agilis/mens/azzeroCO2/client/imgs/Furgone.png")
    @ImageResource.ImageOptions(flipRtl = true)
    ImageResource furgone();

    @Source("it/agilis/mens/azzeroCO2/client/imgs/Moto.png")
    @ImageResource.ImageOptions(flipRtl = true)
    ImageResource moto();

    @Source("it/agilis/mens/azzeroCO2/client/imgs/Nave.png")
    @ImageResource.ImageOptions(flipRtl = true)
    ImageResource nave();

    @Source("it/agilis/mens/azzeroCO2/client/imgs/treno.png")
    @ImageResource.ImageOptions(flipRtl = true)
    ImageResource treno();

    @Source("it/agilis/mens/azzeroCO2/client/imgs/header.png")
    @ImageResource.ImageOptions(flipRtl = true)
    ImageResource header();

    @Source("it/agilis/mens/azzeroCO2/client/icons/Left.png")
    @ImageResource.ImageOptions(flipRtl = true, width = 45, height = 45)
    ImageResource left();

    @Source("it/agilis/mens/azzeroCO2/client/icons/Rigth.png")
    @ImageResource.ImageOptions(flipRtl = true, width = 45, height = 45)
    ImageResource rigth();

    @Source("it/agilis/mens/azzeroCO2/client/icons/Check-icon.png")
    @ImageResource.ImageOptions(flipRtl = true, width = 14, height = 14)
    ImageResource checkIcon();
}
