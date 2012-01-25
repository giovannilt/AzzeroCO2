package it.agilis.mens.azzeroCO2.client.forms;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.binding.FormBinding;
import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.util.Padding;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.ToolButton;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.layout.*;
import com.google.gwt.user.client.Element;
import it.agilis.mens.azzeroCO2.client.mvc.events.AzzeroCO2Events;
import it.agilis.mens.azzeroCO2.shared.model.conoscoCO2.ConoscoCO2Model;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 7/2/11
 * Time: 12:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class FormConoscoCO2 extends LayoutContainer {

    private ConoscoCO2Model conoscoCO2Model = new ConoscoCO2Model();
    private FormBinding binding = null;
    private FormPanel formPanel;

    public FormConoscoCO2() {
        formPanel = createForm();
        binding = new FormBinding(formPanel, true);
        binding.bind(conoscoCO2Model);

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

        panel.setHeading("Compensa le tue emissioni");
        panel.setLabelAlign(FormPanel.LabelAlign.LEFT);

        ToolButton tool1 = new ToolButton("x-tool-help");
        panel.getHeader().addTool(tool1);
        tool1.addSelectionListener(new SelectionListener<IconButtonEvent>() {
            @Override
            public void componentSelected(IconButtonEvent ce) {
                Dispatcher.forwardEvent(AzzeroCO2Events.ShowInfoDialog);
            }
        });
        ToolButton tool = new ToolButton("x-tool-refresh");
        panel.getHeader().addTool(tool);
        tool.addSelectionListener(new SelectionListener<IconButtonEvent>() {
            @Override
            public void componentSelected(IconButtonEvent ce) {
                clear();
            }
        });

        HBoxLayoutData flex = new HBoxLayoutData(new Margins(0, 5, 0, 0));


        LayoutContainer c1 = new LayoutContainer();
        HBoxLayout layout1 = new HBoxLayout();
        layout1.setPadding(new Padding(5));
        layout1.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.MIDDLE);
        c1.setLayout(layout1);

        LabelField istruzioni = new LabelField("Insirisci i Kg di CO2 che hai emesso.");
        istruzioni.setStyleAttribute("font-weight", "bolder");


        LayoutContainer visitatori = new LayoutContainer();
        HBoxLayout layoutVisitatori = new HBoxLayout();
        layoutVisitatori.setPadding(new Padding(10));
        layoutVisitatori.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.BOTTOM);
        visitatori.setLayout(layoutVisitatori);

        NumberField visitatoriField = new NumberField();
        // visitatoriField.setRegex("[0-9]+");
        visitatoriField.getMessages().setRegexText("Conosco la CO2");
        visitatoriField.setName("conoscoCO2");
        visitatoriField.setWidth(80);
        visitatoriField.setPropertyEditorType(Double.class);

        visitatori.add(new LabelField("Kg CO2"), flex);
        visitatori.add(visitatoriField, flex);

        c1.add(istruzioni, flex);
        panel.add(c1);


        panel.add(visitatori, new FormData("100%"));
        return panel;
    }

    public void clear() {
        binding.clear();
        conoscoCO2Model = new ConoscoCO2Model();
        binding.bind(conoscoCO2Model);

    }

    public ConoscoCO2Model getConoscoCO2Model() {
        return conoscoCO2Model;
    }

    @Override
    protected void onLoad() {
        super.onLoad();
        formPanel.getBody().setStyleAttribute("border-bottom", "3px solid #f8b333");
        formPanel.getBody().setStyleAttribute("border-style", "solid");
        formPanel.getBody().setStyleAttribute("border-top", "3px solid #f8b333");
        formPanel.getBody().setStyleAttribute("border-width", "3px 0");
        formPanel.getBody().setStyleAttribute("margin-bottom", "0");
    }


}
