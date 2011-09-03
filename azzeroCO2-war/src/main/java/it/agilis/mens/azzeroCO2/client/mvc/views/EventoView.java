package it.agilis.mens.azzeroCO2.client.mvc.views;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.mvc.View;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import it.agilis.mens.azzeroCO2.client.components.evento.EventoDettaglio;
import it.agilis.mens.azzeroCO2.client.components.evento.EventoNorth;
import it.agilis.mens.azzeroCO2.client.components.evento.EventoSouth;
import it.agilis.mens.azzeroCO2.client.components.evento.EventoWest;
import it.agilis.mens.azzeroCO2.client.mvc.events.AzzeroCO2Events;
import it.agilis.mens.azzeroCO2.client.mvc.events.CentralEvents;
import it.agilis.mens.azzeroCO2.client.mvc.events.EventoEvents;
import it.agilis.mens.azzeroCO2.shared.Eventi;
import it.agilis.mens.azzeroCO2.shared.model.evento.TipoDiCartaModel;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 3/23/11
 * Time: 10:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class EventoView extends View {
    private ContentPanel evento = new ContentPanel();

    private EventoDettaglio eventoDettaglio = new EventoDettaglio();
    private ContentPanel center = new ContentPanel();
    private EventoSouth south = new EventoSouth();

    public EventoView(Controller controller) {
        super(controller);
    }


    @Override
    protected void handleEvent(AppEvent event) {
        EventType eventType = event.getType();
        if (eventType.equals(AzzeroCO2Events.Init)) {
            onInit(event);
        } else if (eventType.equals(EventoEvents.Next)) {
            onNext(event);
        } else if (eventType.equals(EventoEvents.ClearPanel)) {
            eventoDettaglio.clearPanel();
        } else if (eventType.equals(EventoEvents.Previous)) {
            onPrevius(event);
        } else if (eventType.equals(EventoEvents.Riepilogo)) {
            eventoDettaglio.riepilogo();
        }
    }

    private void onPrevius(AppEvent event) {
        eventoDettaglio.previusTab(event);
    }

    private void onNext(AppEvent event) {
        eventoDettaglio.nextTab(event);
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
            center.add(eventoDettaglio, center2Data);

            BorderLayoutData southData = new BorderLayoutData(Style.LayoutRegion.SOUTH, 15);
            southData.setMargins(new Margins(0, 0, 0, 0));
            center.add(south, southData);
        }
        center.setHeaderVisible(false);
        evento.add(center, centerData);

        evento.setTitle(Eventi.EVENTO.name());
        Dispatcher.forwardEvent(new AppEvent(CentralEvents.EventoPanelReady,
                evento));

    }

    public void setTipoDiCarta(List<TipoDiCartaModel> tipoDiCartaModels) {
        eventoDettaglio.setTipoDiCarta(tipoDiCartaModels);
    }

    public void riepilogo() {
        eventoDettaglio.riepilogo();
    }
}
