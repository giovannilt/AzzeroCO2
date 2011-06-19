package it.agilis.mens.azzeroCO2.pages.events.evento;

import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.util.Padding;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.button.ToolButton;
import com.extjs.gxt.ui.client.widget.layout.HBoxLayout;
import com.extjs.gxt.ui.client.widget.layout.HBoxLayoutData;
import com.google.gwt.user.client.Element;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 6/19/11
 * Time: 4:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class EventoSouth extends LayoutContainer {

    @Override
    protected void onRender(Element target, int index) {
        super.onRender(target, index);


        LayoutContainer c = new LayoutContainer();
        HBoxLayout layout = new HBoxLayout();
        layout.setPadding(new Padding(1));
        layout.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.TOP);
        c.setLayout(layout);

        c.add(new ToolButton("x-tool-left"), new HBoxLayoutData(new Margins(0, 0, 0, 0)));
        HBoxLayoutData flex = new HBoxLayoutData(new Margins(0, 5, 0, 0));
        flex.setFlex(1);
        c.add(new Text(), flex);
        c.add(new ToolButton("x-tool-right"), new HBoxLayoutData(new Margins(0, 0, 0, 0)));

        add(c);

    }
}
