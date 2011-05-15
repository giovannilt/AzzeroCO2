package it.agilis.mens.azzeroCO2.pages.events;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.util.Padding;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.*;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Image;
import it.agilis.mens.azzeroCO2.pages.main.CenterPanel;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 3/15/11
 * Time: 9:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class StartContent extends LayoutContainer {


    private CenterPanel centerPanel;

    public StartContent(CenterPanel centerPanel) {
        this.centerPanel=centerPanel;
    }

    protected void onRender(Element target, int index) {
        super.onRender(target, index);

        final BorderLayout layoutBorder = new BorderLayout();
        setLayout(layoutBorder);
        setStyleAttribute("padding", "1px");

        ContentPanel center = new ContentPanel();
        center.setHeading("Compensa le emissioni delle tue attivita'!");
        center.setScrollMode(Style.Scroll.AUTOX);
        BorderLayoutData centerData = new BorderLayoutData(Style.LayoutRegion.CENTER);
        centerData.setMargins(new Margins(0));
        add(center, centerData);

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
                    centerPanel.setHeading(Eventi.EVENTO.toString());
                    centerPanel.setActiveItem(Eventi.EVENTO);
                }
            });
            unEvento.setSize(370, 400);
            c.add(unEvento, layoutData);
            Button unAnno = new Button("Un Anno Di attivita'");
            unAnno.addSelectionListener(new SelectionListener<ButtonEvent>() {
                public void componentSelected(ButtonEvent ce) {
                    centerPanel.setHeading(Eventi.ANNO_DI_ATTIVITA.toString());
                    centerPanel.setActiveItem(Eventi.ANNO_DI_ATTIVITA);
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
            Button pubblicazione = new Button("Una pubbllicazione");
            pubblicazione.setSize(245, 217);
            c.add(pubblicazione, layoutData);
            Button web = new Button("Un sito web");
            web.addSelectionListener(new SelectionListener<ButtonEvent>() {
                public void componentSelected(ButtonEvent ce) {
                    centerPanel.setHeading(Eventi.WEB.toString());
                    centerPanel.setActiveItem(Eventi.WEB);
                }
            });
            web.setSize(245, 217);
            c.add(web, layoutData);
            Button co2 = new Button("Conosco la CO2");
            co2.addSelectionListener(new SelectionListener<ButtonEvent>() {
                public void componentSelected(ButtonEvent ce) {
                    centerPanel.setHeading(Eventi.CONOSCI_CO2.toString());
                    centerPanel.setActiveItem(Eventi.CONOSCI_CO2);
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

        add(east, eastData);

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
                img.setHeight("50");
                img.setWidth("50");
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


    }
}
