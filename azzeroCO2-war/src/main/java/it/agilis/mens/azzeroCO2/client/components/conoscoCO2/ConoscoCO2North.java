package it.agilis.mens.azzeroCO2.client.components.conoscoCO2;

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
import it.agilis.mens.azzeroCO2.client.mvc.events.ConoscoCO2Events;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 6/19/11
 * Time: 12:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class ConoscoCO2North extends LayoutContainer {
    private ToolButton close = new ToolButton("x-tool-close");
    private ToolButton save = new ToolButton("x-tool-save");

    @Override
    protected void onRender(Element target, int index) {
        super.onRender(target, index);

        LayoutContainer c = new LayoutContainer();
        HBoxLayout layout = new HBoxLayout();
        layout.setPadding(new Padding(1));
        layout.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.TOP);
        c.setLayout(layout);

        c.add(new Html("<p style=\"padding:1px;font-family:arial;color:#000000;\">Conosco la CO2</p>"));

        HBoxLayoutData flex = new HBoxLayoutData(new Margins(0, 5, 0, 0));
        flex.setFlex(1);
        c.add(new Text(), flex);
        c.setStyleAttribute("padding-left","30px");


        // ToolButton save = new ToolButton("x-tool-save");
        save.addSelectionListener(new SelectionListener<IconButtonEvent>() {
            @Override
            public void componentSelected(IconButtonEvent ce) {
                Dispatcher.forwardEvent(ConoscoCO2Events.Save, "Save");
            }
        });
        c.add(save, new HBoxLayoutData(new Margins(5, 5, 0, 0)));
         save.setVisible(false);
        //  ToolButton close = new ToolButton("x-tool-close");

        close.addSelectionListener(new SelectionListener<IconButtonEvent>() {
            @Override
            public void componentSelected(IconButtonEvent ce) {

                Dispatcher.forwardEvent(ConoscoCO2Events.ShowConfermDialog);

            }
        });
        c.add(close, new HBoxLayoutData(new Margins(5, 5, 0, 0)));
        close.setVisible(false);
        c.setStyleAttribute("background-color", "#F89D00");
        add(c);
    }

    public void showButtons() {
        close.setVisible(true);
        save.setVisible(true);
    }

    public void hideButtons() {
        close.setVisible(false);
        save.setVisible(false);
    }
}
