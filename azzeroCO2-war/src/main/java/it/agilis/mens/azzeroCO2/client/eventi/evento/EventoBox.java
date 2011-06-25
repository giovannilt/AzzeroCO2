package it.agilis.mens.azzeroCO2.client.eventi.evento;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.google.gwt.user.client.Element;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 6/19/11
 * Time: 12:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class EventoBox extends LayoutContainer {


    @Override
    protected void onRender(Element target, int index) {
         super.onRender(target, index);

        final BorderLayout layout = new BorderLayout();
        setLayout(layout);
        setStyleAttribute("padding", "1px");

        BorderLayoutData centerData = new BorderLayoutData(Style.LayoutRegion.CENTER);
        centerData.setMargins(new Margins(0,0,0,0));

         EventoDettaglio center = new EventoDettaglio();

        add(center, centerData);


        BorderLayoutData southData = new BorderLayoutData(Style.LayoutRegion.SOUTH,15);
        southData.setMargins(new Margins(0,0,0,0));

        EventoSouth south = new EventoSouth();

        add(south, southData);

    }
}
