package it.agilis.mens.azzeroCO2.client.components.conoscoCO2;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.google.gwt.user.client.Element;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 6/19/11
 * Time: 12:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class ConoscoCO2West extends LayoutContainer {


    @Override
    protected void onRender(Element target, int index) {
         super.onRender(target, index);
        ContentPanel panel = new ContentPanel();
        panel.setHeaderVisible(false);
        panel.setBodyStyle("background-color:#000000");
        add(panel);

    }
}
