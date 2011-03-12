package it.agilis.mens.azzeroCO2.pages.main;

import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.CardLayout;
import com.google.gwt.user.client.Element;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 3/12/11
 * Time: 7:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class CenterPanel extends LayoutContainer {



    public CenterPanel(){

    }

    protected void onRender(Element target, int index) {
       final CardLayout layout = new CardLayout();
       setLayout(layout);
    }




}
