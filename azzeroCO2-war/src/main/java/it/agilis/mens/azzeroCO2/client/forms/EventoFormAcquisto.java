package it.agilis.mens.azzeroCO2.client.forms;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.button.ToolButton;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.google.gwt.user.client.Element;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 6/19/11
 * Time: 5:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class EventoFormAcquisto extends TabItem {

    ContentPanel east = new ContentPanel();
    ContentPanel centre = new ContentPanel();


    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);

        BorderLayout layout = new BorderLayout();
        setLayout(layout);
        layout.setEnableState(false);
        setStyleAttribute("padding", "0px");

        createEast();
        east.setHeading("Acquisto");
        BorderLayoutData westData = new BorderLayoutData(Style.LayoutRegion.EAST, 300);
        east.getHeader().addTool(new ToolButton("x-tool-help"));
        east.getHeader().addTool(new ToolButton("x-tool-refresh"));
        westData.setMargins(new Margins(0));
        east.setAutoHeight(true);
        add(east, westData);

        createCentre();
        centre.setHeading("Progetti Di Compensazione");

        BorderLayoutData centerData = new BorderLayoutData(Style.LayoutRegion.CENTER);
        centerData.setMargins(new Margins(0));
        add(centre, centerData);

    }

    private void createEast() {
        ContentPanel c = new ContentPanel();
        c.setHeaderVisible(false);


        east.add(c);
    }

    private void createCentre() {

    }
}

