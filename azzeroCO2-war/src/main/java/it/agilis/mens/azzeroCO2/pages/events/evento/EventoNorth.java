package it.agilis.mens.azzeroCO2.pages.events.evento;

import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.util.Padding;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.BoxLayout;
import com.extjs.gxt.ui.client.widget.layout.HBoxLayout;
import com.extjs.gxt.ui.client.widget.layout.HBoxLayoutData;
import com.google.gwt.user.client.Element;

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

        ContentPanel panel = new ContentPanel();
        panel.setHeaderVisible(false);
        panel.setAutoWidth(true);
        panel.setHeight(35);

        LayoutContainer c = new LayoutContainer();
        c.addText("Compensa Un Evento ");
        HBoxLayout layout = new HBoxLayout();
        layout.setPadding(new Padding(5));
        layout.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.MIDDLE);
        layout.setPack(BoxLayout.BoxLayoutPack.END);
        c.setLayout(layout);

         HBoxLayoutData layoutData = new HBoxLayoutData(new Margins(0, 5, 0, 0));
        c.add(new Button("Salva"), layoutData);
        c.add(new Button("Chiudi"), layoutData);

        panel.add(c);
        add(panel);
    }

}
