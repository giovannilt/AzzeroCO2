package it.agilis.mens.azzeroCO2.client.forms.evento;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.binding.FormBinding;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.util.Padding;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.layout.*;
import com.google.gwt.user.client.Element;
import it.agilis.mens.azzeroCO2.shared.model.evento.NottiModel;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 7/2/11
 * Time: 12:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class EventoFormPernottamenti extends LayoutContainer {

    private NottiModel nottiModel = new NottiModel();
    private FormBinding binding = null;
    private FormPanel formPanel;

    public EventoFormPernottamenti() {
        formPanel = createForm();
        binding = new FormBinding(formPanel, true);
        binding.bind(nottiModel);

    }

    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);

        BorderLayout layout = new BorderLayout();
        setLayout(layout);

        ContentPanel cp = new ContentPanel();
        cp.setFrame(true);
        cp.setHeaderVisible(false);
        cp.setLayout(new RowLayout(Style.Orientation.HORIZONTAL));

        cp.add(formPanel, new RowData(1, 1));


        BorderLayoutData centerData = new BorderLayoutData(Style.LayoutRegion.CENTER);
        add(cp, centerData);
    }


    private FormPanel createForm() {
        FormPanel panel = new FormPanel();
        panel.setFrame(true);

        panel.setHeading("Pernottamenti");
        panel.setLabelAlign(FormPanel.LabelAlign.LEFT);

        HBoxLayoutData flex = new HBoxLayoutData(new Margins(0, 5, 0, 0));
        LayoutContainer notti = new LayoutContainer();
        HBoxLayout layoutNotti = new HBoxLayout();
        layoutNotti.setPadding(new Padding(10));
        layoutNotti.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.BOTTOM);
        notti.setLayout(layoutNotti);

        NumberField numeroNotti = new NumberField();
        numeroNotti.setRegex("[0-9]+");
        numeroNotti.getMessages().setRegexText("Inserisci un numero intero");
        numeroNotti.setName("notti");
        numeroNotti.setWidth(60);
        numeroNotti.setPropertyEditorType(Integer.class);

        notti.add(new LabelField("notti"), flex);
        notti.add(numeroNotti, flex);

        panel.add(notti, new FormData("100%"));
        return panel;
    }

    public void clear() {
        binding.clear();
        nottiModel= new NottiModel();
        binding.bind(nottiModel);

    }

    public NottiModel getNottiModel() {
        return nottiModel;
    }

    public void setNottiModel(NottiModel nottiModel) {
        this.nottiModel = nottiModel;
        binding.bind(nottiModel);
    }
}
