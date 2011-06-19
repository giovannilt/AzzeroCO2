package it.agilis.mens.azzeroCO2.pages.main;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.CardLayout;
import com.google.gwt.user.client.Element;
import it.agilis.mens.azzeroCO2.pages.events.*;
import it.agilis.mens.azzeroCO2.pages.events.evento.Evento;
import it.agilis.mens.azzeroCO2.pages.events.startContent.StartContent;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 3/12/11
 * Time: 7:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class CenterPanel extends LayoutContainer {
    private ContentPanel content;
    private CardLayout layout = new CardLayout();

    public CenterPanel() {
        content = new ContentPanel();
        layout = new CardLayout();
    }

    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);

        add(content);
        content.setHeaderVisible(false);
        content.setButtonAlign(Style.HorizontalAlignment.CENTER);
        content.setLayout(layout);
        layout.setActiveItem(content.getItem(Eventi.MAIN.ordinal()));

        Eventi[] eventi = Eventi.values();
        for (int i = 0; i < eventi.length; i++) {
            LayoutContainer c = create(eventi[i]);
            //c.addText(eventi[i].toString());
            c.setSize("100%", "100%");
            content.add(c);
        }

    }

    public void setHeading(String text) {
        content.setHeading(text);
    }

    public void setActiveItem(Eventi activeItem) {
        layout.setActiveItem(content.getItem(activeItem.ordinal()));
    }

    private LayoutContainer create(Eventi evento) {
        switch(evento) {
            case EVENTO: {
                return new Evento();
            }
            case ANNO_DI_ATTIVITA: {
                return new AnnoDiAttivita();
            }
            case CONSUMO_CARTA: {
                return new ConsumoCarta();
            }
            case CONOSCI_CO2: {
                return new ConosciCO2();
            }
            default:{
                return new StartContent(this);
            }

        }

    }
}
