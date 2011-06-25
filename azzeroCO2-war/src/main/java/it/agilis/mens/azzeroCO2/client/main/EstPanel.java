package it.agilis.mens.azzeroCO2.client.main;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.RowData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.google.gwt.user.client.Element;
import it.agilis.mens.azzeroCO2.client.eventi.Eventi;

public class EstPanel extends LayoutContainer {

    final protected CenterPanel center;


    public EstPanel(CenterPanel center) {
        this.center = center;
    }

    protected void onRender(Element target, int index) {
        super.onRender(target, index);
        setLayout(new RowLayout(Style.Orientation.VERTICAL));

        final Eventi[] eventi = Eventi.values();
        for (int i = 0; i < eventi.length; i++) {
            final int indice=i;
            Button b = new Button(eventi[i].toString());
            b.setHeight("19%");
            b.addSelectionListener(new SelectionListener<ButtonEvent>() {
                public void componentSelected(ButtonEvent ce) {
                    center.setHeading(eventi[indice].toString());
                    center.setActiveItem(eventi[indice]);
                }
            });
            add(b, new RowData(1, -1, new Margins(6)));
        }

    }
}
