package it.agilis.mens.azzeroCO2.pages.main;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.RowData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.google.gwt.user.client.Element;

public class ListEventMenu extends LayoutContainer {

    final protected LayoutContainer center;


    public ListEventMenu(LayoutContainer center) {
        this.center = center;
    }

    protected void onRender(Element target, int index) {
        super.onRender(target, index);

        setLayout(new RowLayout(Style.Orientation.VERTICAL));

        Button evento = new Button();
        evento.setText("EVENTO");
        evento.setHeight("24%");
         evento.addSelectionListener(new SelectionListener<ButtonEvent>() {
            public void componentSelected(ButtonEvent ce) {
              //  center.setHeading("PUPPA");
            }

        });

        Button annotDiAttivia = new Button();
        annotDiAttivia.setText("annotDiAttivia");
        annotDiAttivia.setHeight("24%");

        Button consumoCarta = new Button();
        consumoCarta.setHeight("24%");
        consumoCarta.setText("consumoCarta");

        Button conosciCO2 = new Button();
        conosciCO2.setText("conosciCO2");
        conosciCO2.setHeight("24%");

        add(evento, new RowData(1, -1, new Margins(5)));
        add(annotDiAttivia, new RowData(1, -1, new Margins(5)));
        add(consumoCarta, new RowData(1, -1, new Margins(5)));
        add(conosciCO2, new RowData(1, -1, new Margins(5)));


    }
}
