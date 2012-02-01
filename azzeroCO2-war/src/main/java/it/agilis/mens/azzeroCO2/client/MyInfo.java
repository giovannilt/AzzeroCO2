package it.agilis.mens.azzeroCO2.client;


import com.extjs.gxt.ui.client.util.Point;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.InfoConfig;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 2/1/12
 * Time: 8:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class MyInfo extends Info {

    @Override
    protected void onShowInfo() {
        super.onShowInfo();
    }


    public static void show(String message) {
        show("Info", message, 5000);
    }


    public static void show(String title, String message, int miliseconds) {

        MyInfo info = new MyInfo();

        // Configuração
        InfoConfig config = new InfoConfig(title, message);
        config.display = miliseconds;

        // Formatação
        info.setBodyStyle("background-color: #F8B333; text-align: center; border: 0x solid black; padding: 3px; font-size: 11px; font-weight: bold;");
        info.setFrame(false);
        info.setAutoHeight(true);
        info.setAnimCollapse(true);

        // Exibição
        info.show(config);

        // Posicionamento
        info.setAutoWidth(false);
        info.setWidth(info.getWidth() + 30);
        Point p = info.position();
        p.x = ((p.x + info.getWidth()) / 2) - (info.getWidth() / 2);
        info.setPosition(p.x, 0);

    }
}
