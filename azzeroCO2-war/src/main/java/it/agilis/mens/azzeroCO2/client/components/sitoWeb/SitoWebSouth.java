package it.agilis.mens.azzeroCO2.client.components.sitoWeb;

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
import it.agilis.mens.azzeroCO2.client.mvc.events.CentralEvents;
import it.agilis.mens.azzeroCO2.client.mvc.events.SitoWebEvents;
import it.agilis.mens.azzeroCO2.shared.Eventi;
import it.agilis.mens.azzeroCO2.shared.model.OrdineModel;
import it.agilis.mens.azzeroCO2.shared.model.pagamento.Esito;


/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 6/19/11
 * Time: 4:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class SitoWebSouth extends LayoutContainer {

    private Text leftText = new Text("");
    private Text rigthText = new Text("Riepilogo");

    private Button left = new Button();
    private Button right = new Button();

    private LayoutContainer c = new LayoutContainer();
    private HBoxLayout layout = new HBoxLayout();

    private LayoutContainer leftContainer = new LayoutContainer();
    private HBoxLayout leftLayout = new HBoxLayout();

    @Override
    protected void onRender(Element target, int index) {
        super.onRender(target, index);

        layout.setPadding(new Padding(2, 17, 1, 10)); //DIM
        layout.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.MIDDLE);
        c.setLayout(layout);
        c.setBorders(false);

        leftLayout.setPadding(new Padding(0));
        leftLayout.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.MIDDLE);
        leftLayout.setAdjustForFlexRemainder(true);
        leftContainer.setBorders(false);
        leftContainer.setLayout(leftLayout);

        left.setIcon(AbstractImagePrototype.create(AzzeroCO2Resources.INSTANCE.left()));
        left.setIconAlign(Style.IconAlign.LEFT);
        left.setSize(32, 32);
        left.setText("");
        left.setToolTip("");
        left.setTitle("");
        left.setVisible(false);

        left.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                Dispatcher.forwardEvent(new AppEvent(SitoWebEvents.Previous, ce));
            }
        });
        leftContainer.add(left, new HBoxLayoutData(new Margins(0, 0, 0, 0)));
        leftContainer.add(leftText, new HBoxLayoutData(new Margins(0, 0, 0, 0)));

        HBoxLayoutData flex = new HBoxLayoutData(new Margins(-14, 0, 0, 0));
        flex.setFlex(1);
        c.add(leftContainer, flex);

        leftText.setSize(200, 15);
        rigthText.setSize(250, 15);
        rigthText.setStyleAttribute("text-align", "right");
        rigthText.setStyleAttribute("font-family", "arial");
        leftText.setStyleAttribute("font-family", "arial");
        leftText.setStyleAttribute("font-size", "14px");
        rigthText.setStyleAttribute("font-size", "14px");

        c.add(rigthText);

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
                if (rigthText.getText().equalsIgnoreCase("torna alla home")) {
                    Dispatcher.forwardEvent(CentralEvents.ShowPanel, Eventi.MAIN);
                    Dispatcher.forwardEvent(SitoWebEvents.ClearPanel, Eventi.MAIN);
                } else {
                    Dispatcher.forwardEvent(new AppEvent(SitoWebEvents.Next, ce));
                }
            }
        });
        add(c);
    }


    public void setTextLeft(String left_t, OrdineModel riepilogo) {
        if (riepilogo.getPagamentoModel() != null
                && riepilogo.getPagamentoModel().getEsito() != null
                && riepilogo.getPagamentoModel().getEsito().equalsIgnoreCase(Esito.PAGATO.toString())) {
            leftContainer.setVisible(false);
            left.setVisible(false);
        } else {
            if (left_t.length() == 0 || left_t.equalsIgnoreCase(".")) {
                leftContainer.setVisible(false);
                left.setVisible(false);
            } else {
                leftContainer.setVisible(true);
                left.setVisible(true);
            }
            leftText.setText(left_t);
            leftContainer.layout(true);
        }
    }

    public void setTextRigth(String right_t, OrdineModel riepilogo) {
        if (riepilogo.getPagamentoModel() != null
                && riepilogo.getPagamentoModel().getEsito() != null
                && riepilogo.getPagamentoModel().getEsito().equalsIgnoreCase(Esito.PAGATO.toString())) {
            right.setVisible(false);
            rigthText.setVisible(false);
        } else {
            if (right_t.length() == 0) {
                right.setVisible(false);
                rigthText.setVisible(false);
            } else {
                right.setVisible(true);
                rigthText.setVisible(true);
            }
        }
        rigthText.setText(right_t);
    }
}
