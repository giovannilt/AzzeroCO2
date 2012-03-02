package it.agilis.mens.azzeroCO2.client.forms;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.RowData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Image;
import it.agilis.mens.azzeroCO2.client.AzzeroCO2Resources;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 6/19/11
 * Time: 5:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class FormConferma extends LayoutContainer {


    private ContentPanel centre = new ContentPanel() {
        @Override
        protected void onLoad() {
            super.onLoad();
            centre.getBody().setStyleAttribute("border-bottom", "3px solid #f8b333");
            centre.getBody().setStyleAttribute("border-style", "solid");
            centre.getBody().setStyleAttribute("border-top", "3px solid #f8b333");
            centre.getBody().setStyleAttribute("margin-bottom", "0px");
            centre.getHeader().setStyleAttribute("border-right-width", "15px");
        }
    };
    private String haiCompensatoText = "Hai Compensato ";
    private Text haiCompensato = new Text(haiCompensatoText);

    private final Image immagine = new Image();

    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);

        BorderLayout layout = new BorderLayout();
        setLayout(layout);
        layout.setEnableState(false);
        setStyleAttribute("padding", "0px");

        createCentre();
        centre.setHeading("Conferma");
        centre.setHeight(557);
        BorderLayoutData centerData = new BorderLayoutData(Style.LayoutRegion.CENTER);
        add(centre, centerData);

    }

    private void createCentre() {
        centre.setLayout(new RowLayout(Style.Orientation.VERTICAL));


        ContentPanel row = new ContentPanel();
        row.setHeaderVisible(false);
        row.setHeight(75);
        row.setLayout(new RowLayout(Style.Orientation.HORIZONTAL));

        haiCompensato.setBorders(true);

        row.add(new Image(AzzeroCO2Resources.INSTANCE.check()), new RowData(-1, 1, new Margins(4)));
        row.add(haiCompensato, new RowData(1, 1, new Margins(4, 0, 4, 0)));

        centre.add(row, new RowData(1, -1, new Margins(4)));

        row = new ContentPanel();
        row.setHeaderVisible(false);
        row.setLayout(new RowLayout(Style.Orientation.HORIZONTAL));
        row.add(immagine, new RowData(1, 1));

        centre.add(row, new RowData(1, 1, new Margins(4, 0, 4, 0)));
    }

}

