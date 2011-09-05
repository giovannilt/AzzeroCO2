package it.agilis.mens.azzeroCO2.client.components.evento;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.util.Padding;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.HBoxLayout;
import com.extjs.gxt.ui.client.widget.layout.HBoxLayoutData;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import it.agilis.mens.azzeroCO2.client.AzzeroCO2Resources;
import it.agilis.mens.azzeroCO2.client.mvc.events.EventoEvents;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 6/19/11
 * Time: 4:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class EventoSouth extends LayoutContainer {
    private Text leftText = new Text("");
    private Text rigthText = new Text("Energia");

    private Button left = new Button();
    private Button right = new Button();

    @Override
    protected void onRender(Element target, int index) {
        super.onRender(target, index);

        LayoutContainer c = new LayoutContainer();
        HBoxLayout layout = new HBoxLayout();
        layout.setPadding(new Padding(1));
        layout.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.MIDDLE);
        c.setLayout(layout);
        left.setIcon(AbstractImagePrototype.create(AzzeroCO2Resources.INSTANCE.left()));
        left.setIconAlign(Style.IconAlign.LEFT);
        left.setSize(48, 45);
        left.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                Dispatcher.forwardEvent(new AppEvent(EventoEvents.Previous, ce));
            }
        });
        c.add(left, new HBoxLayoutData(new Margins(0, 0, 0, 0)));

        HBoxLayoutData flex = new HBoxLayoutData(new Margins(0, 5, 0, 0));
        flex.setFlex(1);
        c.add(leftText, flex);

        leftText.setSize(200, 15);
        rigthText.setSize(250, 15);
        rigthText.setStyleAttribute("text-align", "right");

        c.add(rigthText, new HBoxLayoutData(new Margins(0, 0, 0, 0)));

        right.setIconAlign(Style.IconAlign.RIGHT);
        right.setIcon(AbstractImagePrototype.create(AzzeroCO2Resources.INSTANCE.rigth()));
        right.setSize(48, 45);
        c.add(right, new HBoxLayoutData(new Margins(0, 0, 0, 0)));
        right.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                Dispatcher.forwardEvent(new AppEvent(EventoEvents.Next, ce));
            }
        });
        add(c);
    }


    public void setTextLeft(String left) {
        leftText.setText(left);
    }

    public void setTextRigth(String right) {
        rigthText.setText(right);
    }

}
