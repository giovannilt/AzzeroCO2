package it.agilis.mens.azzeroCO2.client.mvc.views;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.extjs.gxt.ui.client.mvc.View;
import com.extjs.gxt.ui.client.widget.Viewport;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.google.gwt.user.client.ui.RootPanel;
import it.agilis.mens.azzeroCO2.client.main.MainPage;
import it.agilis.mens.azzeroCO2.client.mvc.events.AzzeroCO2Events;


/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 6/25/11
 * Time: 8:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class AzzeroCO2View extends View {
    private final MainPage main = new MainPage();
    private final Viewport viewport = new Viewport();

    public AzzeroCO2View(Controller controller) {
        super(controller);
    }

    @Override
    protected void handleEvent(AppEvent event) {
        EventType eventType = event.getType();
        if (eventType.equals(AzzeroCO2Events.Init)) {
            onInit(event);
        } else if(eventType.equals(AzzeroCO2Events.Error)){
            onError(event);
        } else if (eventType.equals(AzzeroCO2Events.UIReady)){
            onUIReady(event);
        }

    }

    private void onInit(AppEvent event) {
        BorderLayoutData centerData = new BorderLayoutData(Style.LayoutRegion.CENTER);
        /*centerData.setCollapsible(false);
        RowLayout rowLayout = new RowLayout(Style.Orientation.VERTICAL);
        main.setLayout(rowLayout);
        */
        viewport.add(main, centerData);

    }

    private void onUIReady(AppEvent event) {
        RootPanel.get().add(viewport);
    }

    private void onError(AppEvent event) {
	}
}
