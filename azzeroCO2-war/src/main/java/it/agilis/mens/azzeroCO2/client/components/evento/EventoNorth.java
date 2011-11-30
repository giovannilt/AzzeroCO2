package it.agilis.mens.azzeroCO2.client.components.evento;

import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.util.Padding;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.button.ToolButton;
import com.extjs.gxt.ui.client.widget.layout.HBoxLayout;
import com.extjs.gxt.ui.client.widget.layout.HBoxLayoutData;
import com.google.gwt.user.client.Element;
import it.agilis.mens.azzeroCO2.client.mvc.events.EventoEvents;

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

        LayoutContainer c = new LayoutContainer();
        HBoxLayout layout = new HBoxLayout();
        layout.setPadding(new Padding(1));
        layout.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.TOP);
        c.setLayout(layout);

        c.add(new Html("<p style=\"padding:1px;font-family:tahoma,arial,verdana,sans-serif;color:#000000;\">Compensa un Evento </p>"));

        HBoxLayoutData flex = new HBoxLayoutData(new Margins(0, 5, 0, 0));
        flex.setFlex(1);
        c.add(new Text(), flex);


        ToolButton save = new ToolButton("x-tool-save");
        save.addSelectionListener(new SelectionListener<IconButtonEvent>() {
            @Override
            public void componentSelected(IconButtonEvent ce) {
                Dispatcher.forwardEvent(EventoEvents.Save, "Save");
            }
        });
        c.add(save, new HBoxLayoutData(new Margins(5, 5, 0, 0)));

        ToolButton close = new ToolButton("x-tool-close");

        close.addSelectionListener(new SelectionListener<IconButtonEvent>() {
            @Override
            public void componentSelected(IconButtonEvent ce) {

                Dispatcher.forwardEvent(EventoEvents.ShowConfermDialog);

                //   Dispatcher.forwardEvent(CentralEvents.ShowPanel, Eventi.MAIN);
                //   Dispatcher.forwardEvent(EventoEvents.ClearPanel, Eventi.MAIN);
            }
        });
        c.add(close, new HBoxLayoutData(new Margins(5, 5, 0, 0)));
        c.setStyleAttribute("background-color", "#F89D00");
        add(c);
    }
}
