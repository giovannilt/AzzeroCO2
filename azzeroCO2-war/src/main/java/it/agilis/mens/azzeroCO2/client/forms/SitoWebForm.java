package it.agilis.mens.azzeroCO2.client.forms;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.util.Padding;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.layout.*;
import com.google.gwt.user.client.Element;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 7/2/11
 * Time: 12:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class SitoWebForm extends LayoutContainer {

    private FormPanel panel = new FormPanel();

    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);

        setLayout(new BorderLayout());
        BorderLayoutData centerData = new BorderLayoutData(Style.LayoutRegion.CENTER);
        centerData.setMargins(new Margins(0));
        add(panel, centerData);
            panel.setFrame(true);
        createColumnForm();

    }


    private void createColumnForm() {
        FormData formData = new FormData("100%");
        panel.setHeading("Sito Web");
        panel.setHeight(650);
        panel.setLabelAlign(FormPanel.LabelAlign.LEFT);


        HBoxLayoutData flex = new HBoxLayoutData(new Margins(0, 5, 0, 0));

        LayoutContainer visitatori = new LayoutContainer();
        HBoxLayout layoutVisitatori = new HBoxLayout();
        layoutVisitatori.setPadding(new Padding(10));
        layoutVisitatori.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.BOTTOM);
        visitatori.setLayout(layoutVisitatori);

        NumberField visitatoriField = new NumberField();
        visitatoriField.setWidth(60);


        visitatori.add(new LabelField("Numero di visitatori annui:"), flex);
        visitatori.add(visitatoriField, flex);

        panel.add(visitatori, new FormData("100%"));


    }

}
