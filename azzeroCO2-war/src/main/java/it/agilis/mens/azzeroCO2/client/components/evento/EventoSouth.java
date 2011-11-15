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

    private LayoutContainer c = new LayoutContainer();

    @Override
    protected void onRender(Element target, int index) {
        super.onRender(target, index);


        HBoxLayout layout = new HBoxLayout();
        layout.setPadding(new Padding(1));
        layout.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.MIDDLE);
        c.setLayout(layout);
        left.setIcon(AbstractImagePrototype.create(AzzeroCO2Resources.INSTANCE.left()));
        left.setIconAlign(Style.IconAlign.LEFT);
        left.setSize(32, 32);
        left.setText("");
        left.setToolTip("");
        left.setTitle("");
        left.setEnabled(false);
        left.setVisible(false);

        left.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                Dispatcher.forwardEvent(new AppEvent(EventoEvents.Previous, ce));
            }
        });
        c.add(left, new HBoxLayoutData(new Margins(0, 0, 0, 0)));

        HBoxLayoutData flex = new HBoxLayoutData(new Margins(0, 0, 0, 5));
        flex.setFlex(1);
        c.add(leftText, flex);

        leftText.setSize(200, 15);
        rigthText.setSize(250, 15);
        rigthText.setStyleAttribute("text-align", "right");
        rigthText.setStyleAttribute("font-family", "tahoma,arial,verdana,sans-serif");
        leftText.setStyleAttribute("font-family", "tahoma,arial,verdana,sans-serif");
        leftText.setStyleAttribute("font-size", "14px");
        rigthText.setStyleAttribute("font-size", "14px");

        c.add(rigthText, new HBoxLayoutData(new Margins(0, 5, 0, 0)));

        right.setIconAlign(Style.IconAlign.RIGHT);
        //  right.setText("Energia");
        right.setIcon(AbstractImagePrototype.create(AzzeroCO2Resources.INSTANCE.rigth()));
        right.setSize(32, 32);
        right.setTitle("");
        right.setText("");
        right.setToolTip("");

        c.add(right, new HBoxLayoutData(new Margins(0, 0, 0, 0)));
        right.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                Dispatcher.forwardEvent(new AppEvent(EventoEvents.Next, ce));
            }
        });
        add(c);
    }


    public void setTextLeft(String left_t) {
        if (left_t.length() == 0) {
            left.setEnabled(false);
            left.setVisible(false);
        } else {
            left.setEnabled(true);
            left.setVisible(true);
        }
        leftText.setText(left_t);

        c.layout(true);
        //    left.setToolTip(left_t);
        //    left.setTitle("");
        //   left.setText("");
    }

    public void setTextRigth(String right_t) {
        if (right_t.length() == 0) {
            right.setEnabled(false);
            right.setVisible(false);
        } else {
            right.setEnabled(true);
            right.setVisible(true);
        }
        rigthText.setText(right_t);
        c.layout(true);
        //   right.setToolTip(right_t);
        //   right.setText("");
        //   right.setTitle("");
    }

}
