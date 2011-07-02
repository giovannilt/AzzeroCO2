package it.agilis.mens.azzeroCO2.client.mvc.views;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.mvc.View;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.util.Padding;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.layout.*;
import it.agilis.mens.azzeroCO2.client.components.conoscoCO2.ConoscoCO2;
import it.agilis.mens.azzeroCO2.client.components.eventi.evento.EventoDettaglio;
import it.agilis.mens.azzeroCO2.client.components.eventi.evento.EventoNorth;
import it.agilis.mens.azzeroCO2.client.components.eventi.evento.EventoSouth;
import it.agilis.mens.azzeroCO2.client.components.eventi.evento.EventoWest;
import it.agilis.mens.azzeroCO2.client.mvc.events.AzzeroCO2Events;
import it.agilis.mens.azzeroCO2.client.mvc.events.CentralEvents;
import it.agilis.mens.azzeroCO2.client.mvc.events.EventoEvents;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 7/2/11
 * Time: 11:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class ConosciCO2View extends View {
    private ContentPanel evento = new ContentPanel();

        private ConoscoCO2 conoscoCO2 = new ConoscoCO2();
        private ContentPanel center = new ContentPanel();
        private EventoSouth south = new EventoSouth();


        public ConosciCO2View(Controller controller) {
            super(controller);
        }


        @Override
        protected void handleEvent(AppEvent event) {
            EventType eventType = event.getType();
            if (eventType.equals(AzzeroCO2Events.Init)) {
                onInit(event);
            } else if (eventType.equals(EventoEvents.Next)) {

            } else if (eventType.equals(EventoEvents.Previous)) {

            }
        }


        private void onInit(AppEvent event) {
            final BorderLayout layout = new BorderLayout();
            layout.setEnableState(false);
            evento.setHeaderVisible(false);
            evento.setLayout(layout);
            evento.setStyleAttribute("padding", "1px");

            BorderLayoutData northData = new BorderLayoutData(Style.LayoutRegion.NORTH, 25);
            northData.setCollapsible(false);
            northData.setFloatable(false);
            northData.setHideCollapseTool(false);
            northData.setSplit(false);
            northData.setMargins(new Margins(0, 0, 0, 0));
            evento.add(new EventoNorth(), northData);


            BorderLayoutData westData = new BorderLayoutData(Style.LayoutRegion.WEST, 150);
            westData.setCollapsible(false);
            westData.setFloatable(false);
            westData.setHideCollapseTool(false);
            westData.setSplit(false);
            westData.setMargins(new Margins(0, 0, 0, 0));

            evento.add(new EventoWest(), westData);

            BorderLayoutData centerData = new BorderLayoutData(Style.LayoutRegion.CENTER);
            {
                final BorderLayout layout2 = new BorderLayout();
                center.setLayout(layout2);
                center.setStyleAttribute("padding", "1px");

                BorderLayoutData center2Data = new BorderLayoutData(Style.LayoutRegion.CENTER);
                center2Data.setMargins(new Margins(0, 0, 0, 0));
                center.add(conoscoCO2, center2Data);

                BorderLayoutData southData = new BorderLayoutData(Style.LayoutRegion.SOUTH, 15);
                southData.setMargins(new Margins(0, 0, 0, 0));
                center.add(south, southData);
            }
            center.setHeaderVisible(false);
            evento.add(center, centerData);

            Dispatcher.forwardEvent(new AppEvent(CentralEvents.EventoPanelReady,
                    evento));
        }
    }
