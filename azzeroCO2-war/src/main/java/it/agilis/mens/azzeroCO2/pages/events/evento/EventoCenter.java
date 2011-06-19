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
 * Date: 6/19/11
 * Time: 12:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class EventoCenter extends LayoutContainer {


    @Override
    protected void onRender(Element target, int index) {
        super.onRender(target, index);
        final BorderLayout layout = new BorderLayout();
        layout.setEnableState(false);
        setLayout(layout);
        setStyleAttribute("padding", "5px");

        //addText("Evento Center");

        BorderLayoutData westData = new BorderLayoutData(Style.LayoutRegion.WEST, 100);
        westData.setCollapsible(false);
        westData.setFloatable(false);
        westData.setHideCollapseTool(false);
        westData.setSplit(false);
        westData.setMargins(new Margins(0, 0, 0, 0));

        add(new EventoWest(), westData);

        BorderLayoutData centerData = new BorderLayoutData(Style.LayoutRegion.CENTER);
        centerData.setMargins(new Margins(0));

        EventoBox center = new EventoBox();

        add(center, centerData);


    }
}
