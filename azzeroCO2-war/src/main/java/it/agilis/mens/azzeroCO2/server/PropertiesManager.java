package it.agilis.mens.azzeroCO2.server;

/**
 * Created by IntelliJ IDEA.
 * User: Giovanni La Torre
 * Date: 25/11/11
 * Time: 10.28
 * To change this template use File | Settings | File Templates.
 */
public class PropertiesManager {

    private String imageSource;
    private String externalUrlImage;

    public void setImageSource(String imageSource) {
        this.imageSource = imageSource;
    }

    public String getImageSource() {
        return imageSource;
    }

    public String getExternalUrlImage() {
        return externalUrlImage;
    }

    public void setExternalUrlImage(String externalUrlImage) {
        this.externalUrlImage = externalUrlImage;
    }

}
