package it.agilis.mens.azzeroCO2.pages.main;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 3/12/11
 * Time: 7:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class CenterPanel extends LayoutContainer {



    public CenterPanel(){
       ContentPanel content= new ContentPanel();
       add(content);
    }



    public void setHeading(String puppa) {
    }
}
