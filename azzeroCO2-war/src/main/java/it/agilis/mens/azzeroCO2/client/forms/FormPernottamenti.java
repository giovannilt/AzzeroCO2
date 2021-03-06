package it.agilis.mens.azzeroCO2.client.forms;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.binding.FormBinding;
import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.ToolButton;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.layout.RowData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.google.gwt.user.client.Element;
import it.agilis.mens.azzeroCO2.client.mvc.events.AzzeroCO2Events;
import it.agilis.mens.azzeroCO2.shared.model.evento.NottiModel;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 7/2/11
 * Time: 12:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class FormPernottamenti extends LayoutContainer {

    private NottiModel nottiModel = new NottiModel();
    private FormBinding binding = null;
    private FormPanel formPanel;

    public FormPernottamenti() {
        formPanel = createForm();
        binding = new FormBinding(formPanel, true);
        binding.bind(nottiModel);

    }

    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);

        setLayout(new RowLayout(Style.Orientation.HORIZONTAL));
        add(formPanel, new RowData(.98, .95));      //DIM
    }


    private FormPanel createForm() {
        FormPanel panel = new FormPanel();
        panel.setFrame(true);

        FormLayout layout = new FormLayout();
        layout.setLabelWidth(50);
        layout.setDefaultWidth(500);
        panel.setLayout(layout);

        panel.setHeading("Pernottamenti");
        panel.setLabelAlign(FormPanel.LabelAlign.LEFT);

        ToolButton tool1 = new ToolButton("x-tool-help");
        panel.getHeader().addTool(tool1);
        tool1.addSelectionListener(new SelectionListener<IconButtonEvent>() {
            @Override
            public void componentSelected(IconButtonEvent ce) {
                Dispatcher.forwardEvent(AzzeroCO2Events.ShowInfoDialog, "È stata presa in considerazione una media dei consumi di energia e di acqua per stanza per notte.");
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
        LabelField istruzioni = new LabelField("Inserisci il numero di notti in hotel che l'evento ha reso necessarie<br> ");
        istruzioni.setStyleAttribute("font-weight", "bolder");


        NumberField numeroNotti = new NumberField();
        numeroNotti.setFieldLabel("Notti");
        numeroNotti.setLabelStyle(numeroNotti.getLabelStyle() + ";font-size:11px");
        numeroNotti.setRegex("[0-9]+");
        numeroNotti.getMessages().setRegexText("Inserisci un numero intero");
        numeroNotti.setName("notti");
        numeroNotti.setWidth(60);
        numeroNotti.setPropertyEditorType(Integer.class);


        panel.add(istruzioni, new FormData("100%"));
        panel.add(numeroNotti, new FormData("20%"));
        return panel;
    }

    public void clear() {
        binding.clear();
        nottiModel = new NottiModel();
        binding.bind(nottiModel);

    }

    public NottiModel getNottiModel() {
        return nottiModel;
    }

    public void setNottiModel(NottiModel nottiModel) {
        if (nottiModel != null) {
            this.nottiModel = nottiModel;
            binding.bind(nottiModel);
        }
    }

    @Override
    protected void onLoad() {
        super.onLoad();
        formPanel.getBody().setStyleAttribute("border-bottom", "3px solid #f8b333");
        formPanel.getBody().setStyleAttribute("border-style", "solid");
        formPanel.getBody().setStyleAttribute("border-top", "3px solid #f8b333");
        formPanel.getBody().setStyleAttribute("border-width", "3px 0");
        formPanel.getBody().setStyleAttribute("margin-bottom", "0px");

        //To change body of overridden methods use File | Settings | File Templates.
    }
}
