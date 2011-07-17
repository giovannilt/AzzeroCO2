package it.agilis.mens.azzeroCO2.client.mvc.events;

import com.extjs.gxt.ui.client.event.EventType;
import com.google.gwt.dev.util.log.speedtracer.SpeedTracerLogger;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 6/29/11
 * Time: 9:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class CentralEvents {

    public static final EventType EventoPanelReady= new EventType();
    public static final EventType ConosciCO2PanelReady= new EventType();
    public static final EventType UnaPubblicazioneReady= new EventType();
    public static final EventType UnAnnoDiAttivitaPanelReady= new EventType();
    public static final EventType AmministrazioneReady=new EventType();
    public static final EventType WebPanelReady= new EventType();

    public static final EventType ShowPanel= new EventType();

}
