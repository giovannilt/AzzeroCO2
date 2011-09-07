package it.agilis.mens.azzeroCO2.client.components.evento;

import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.util.Padding;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.ToolButton;
import com.extjs.gxt.ui.client.widget.layout.HBoxLayout;
import com.google.gwt.user.client.Element;
import it.agilis.mens.azzeroCO2.client.mvc.events.CentralEvents;
import it.agilis.mens.azzeroCO2.client.mvc.events.EventoEvents;
import it.agilis.mens.azzeroCO2.shared.Eventi;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 6/19/11
 * Time: 12:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class EventoNorth extends LayoutContainer {

    @Override
    protected void onRender(Element target, int index) {
        super.onRender(target, index);

        ContentPanel c = new ContentPanel();
        HBoxLayout layout = new HBoxLayout();
        layout.setPadding(new Padding(1));
        layout.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.TOP);
        c.setLayout(layout);
        c.setHeading("Compensa un Evento");


        ToolButton save = new ToolButton("x-tool-save");
        save.addSelectionListener(new SelectionListener<IconButtonEvent>() {
            @Override
            public void componentSelected(IconButtonEvent ce) {
                Dispatcher.forwardEvent(EventoEvents.Save, "Save");
                Info.display("Info", "Evento salvato");
            }
        });
        c.getHeader().addTool(save);

        ToolButton close = new ToolButton("x-tool-close");

        close.addSelectionListener(new SelectionListener<IconButtonEvent>() {
            @Override
            public void componentSelected(IconButtonEvent ce) {
                Dispatcher.forwardEvent(CentralEvents.ShowPanel, Eventi.MAIN);
                Dispatcher.forwardEvent(EventoEvents.ClearPanel, Eventi.MAIN);
            }
        });


        c.getHeader().addTool(close);
       // c.getHeader().setStyleAttribute().setBodyStyle("backgroundColor: white;");
     // TODO... SETTARE ARANCIONE   c.getHeader().setStyleAttribute("background-color", "#F89D00;");
        add(c);
    }

}
