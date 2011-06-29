package it.agilis.mens.azzeroCO2.client.mvc.views;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.mvc.View;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.util.Padding;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.*;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;
import it.agilis.mens.azzeroCO2.client.components.eventi.Eventi;
import it.agilis.mens.azzeroCO2.client.mvc.events.AzzeroCO2Events;
import it.agilis.mens.azzeroCO2.client.mvc.events.CentralEvents;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 3/23/11
 * Time: 10:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class CentralView extends View {
    private final ContentPanel centralPanel = new ContentPanel();
    private CardLayout layout = new CardLayout();

    public CentralView(Controller controller) {
        super(controller);
    }

    @Override
    protected void handleEvent(AppEvent event) {
        EventType eventType = event.getType();
        if (eventType.equals(AzzeroCO2Events.Init)) {
            onInit(event);
        } else if (eventType.equals(CentralEvents.EventoPanelReady)) {
            onContentReady(event);
        } else if (eventType.equals(CentralEvents.ConosciCO2PanelReady)) {
            onContentReady(event);
        } else if (eventType.equals(CentralEvents.ConsumoCartaPanelReady)) {
            onContentReady(event);
        } else if (eventType.equals(CentralEvents.WebPanelReady)) {
            onContentReady(event);
        } else if(eventType.equals(CentralEvents.ShowPanel))   {
            setActiveItem(event.<Eventi>getData());
        }
    }

    private void onContentReady(AppEvent event) {
        centralPanel.add(event.<Widget>getData());
    }

    private void setActiveItem(Eventi evento) {
        layout.setActiveItem(centralPanel.getItem(evento.ordinal()));
    }

    private ContentPanel getStartContent() {
        final ContentPanel _return = new ContentPanel();
        _return.setHeaderVisible(false);
        final BorderLayout layoutBorder = new BorderLayout();
        _return.setLayout(layoutBorder);
        _return.setStyleAttribute("padding", "1px");

        ContentPanel center = new ContentPanel();
        center.setHeading("Compensa le emissioni delle tue attivita'!");
        center.setScrollMode(Style.Scroll.AUTOX);
        BorderLayoutData centerData = new BorderLayoutData(Style.LayoutRegion.CENTER);
        centerData.setMargins(new Margins(0));
        _return.add(center, centerData);

        {  // Primo Rigo "EVENTI"
            LayoutContainer c = new LayoutContainer();
            HBoxLayout layout = new HBoxLayout();
            layout.setPadding(new Padding(5));
            layout.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.MIDDLE);
            layout.setPack(BoxLayout.BoxLayoutPack.START);
            c.setLayout(layout);
            HBoxLayoutData layoutData = new HBoxLayoutData(new Margins(0, 5, 0, 0));
            Button unEvento = new Button("Un Evento");
            unEvento.addSelectionListener(new SelectionListener<ButtonEvent>() {
                public void componentSelected(ButtonEvent ce) {
                    setActiveItem(Eventi.EVENTO);
                }
            });
            unEvento.setSize(370, 400);
            c.add(unEvento, layoutData);
            Button unAnno = new Button("Un Anno Di attivita'");
            unAnno.addSelectionListener(new SelectionListener<ButtonEvent>() {
                public void componentSelected(ButtonEvent ce) {
                    setActiveItem(Eventi.ANNO_DI_ATTIVITA);
                }
            });
            unAnno.setSize(370, 400);
            c.add(unAnno, layoutData);
            center.add(c, new FlowData(1));
        }

        {  // Secondo Rigo "EVENTI"
            LayoutContainer c = new LayoutContainer();
            HBoxLayout layout = new HBoxLayout();
            layout.setPadding(new Padding(5));
            layout.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.MIDDLE);
            layout.setPack(BoxLayout.BoxLayoutPack.START);
            c.setLayout(layout);
            HBoxLayoutData layoutData = new HBoxLayoutData(new Margins(0, 5, 0, 0));
            Button pubblicazione = new Button("Una publicazione");
            pubblicazione.setSize(245, 217);
            c.add(pubblicazione, layoutData);
            Button web = new Button("Un sito web");
            web.addSelectionListener(new SelectionListener<ButtonEvent>() {
                public void componentSelected(ButtonEvent ce) {
                    setActiveItem(Eventi.WEB);
                }
            });
            web.setSize(245, 217);
            c.add(web, layoutData);
            Button co2 = new Button("Conosco la CO2");
            co2.addSelectionListener(new SelectionListener<ButtonEvent>() {
                public void componentSelected(ButtonEvent ce) {
                    setActiveItem(Eventi.CONOSCI_CO2);
                }
            });
            co2.setSize(245, 217);
            c.add(co2, layoutData);
            center.add(c, new FlowData(1));
        }

        ContentPanel east = new ContentPanel();
        east.setHeaderVisible(false);
        BorderLayoutData eastData = new BorderLayoutData(Style.LayoutRegion.EAST, 246);
        eastData.setSplit(false);
        eastData.setMargins(new Margins(0));

        _return.add(east, eastData);

        {
            ContentPanel c = new ContentPanel();
            c.setHeight("672px");

            c.setHeaderVisible(false);
            VBoxLayout layout = new VBoxLayout();
            layout.setPadding(new Padding(0));
            layout.setVBoxLayoutAlign(VBoxLayout.VBoxLayoutAlign.STRETCHMAX);


            c.setLayout(layout);

            VBoxLayoutData flex = new VBoxLayoutData(new Margins(0, 0, 0, 0));
            flex.setFlex(1);
            {   // Cos'e' la compensazione
                ContentPanel compensazione = new ContentPanel();
                compensazione.setWidth("242px");
                compensazione.setHeading("Che cosa e' la Compensazione?");
                compensazione.addText("La compensazione e' .. .. .. .. ");
                Image img = new Image("/imgs/header.png");
                img.setHeight("200");
                img.setWidth("200");
                compensazione.add(img);
                c.add(compensazione, flex);
            }
            {   // NEWS
                ContentPanel news = new ContentPanel();
                news.setHeading("NEWS");
                news.addText("NEWS .. .. .. .. ");
                Image img = new Image("/imgs/header.png");
                img.setHeight("50");
                img.setWidth("50");
                news.add(img);
                c.add(news, flex);
            }

            east.add(c, new FlowData(0));
        }

        return _return;
    }


    private void onInit(AppEvent event) {
        centralPanel.setHeaderVisible(false);
        centralPanel.setButtonAlign(Style.HorizontalAlignment.CENTER);
        centralPanel.setLayout(layout);
        layout.setActiveItem(centralPanel.getItem(Eventi.MAIN.ordinal()));
        centralPanel.add(getStartContent());
        Dispatcher.forwardEvent(new AppEvent(AzzeroCO2Events.CentralPanelReady,
                centralPanel));
    }
}
