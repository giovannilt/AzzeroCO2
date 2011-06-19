package it.agilis.mens.azzeroCO2.pages.events.evento;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.google.gwt.user.client.Element;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 3/15/11
 * Time: 9:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class Evento extends LayoutContainer {

    public Evento() {

    }

    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);
        final BorderLayout layout = new BorderLayout();
        layout.setEnableState(false);
        setLayout(layout);
        setStyleAttribute("padding", "1px");

        BorderLayoutData northData = new BorderLayoutData(Style.LayoutRegion.NORTH,25);
        northData.setCollapsible(false);
        northData.setFloatable(false);
        northData.setHideCollapseTool(false);
        northData.setSplit(false);
        northData.setMargins(new Margins(0, 0, 0, 0));
        add(new EventoNorth(), northData);

        BorderLayoutData centerData = new BorderLayoutData(Style.LayoutRegion.CENTER);
        centerData.setMargins(new Margins(0,0,0,0));

        EventoCenter center = new EventoCenter();

        add(center, centerData);

    }


}
