package it.agilis.mens.azzeroCO2.client.forms.annoAttivita;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.binding.FormBinding;
import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.ToolButton;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.*;
import com.google.gwt.user.client.Element;
import it.agilis.mens.azzeroCO2.client.mvc.events.UnAnnoDiAttivitaEvents;
import it.agilis.mens.azzeroCO2.shared.model.OrdineModel;

/**
 * Created by IntelliJ IDEA.
 * User: serenadimaida
 * Date: 07/12/11
 * Time: 10:18
 * To change this template use File | Settings | File Templates.
 */
public class FormAnnoDettaglio extends LayoutContainer {

    private OrdineModel ordineModel = new OrdineModel();
    private FormBinding binding = null;
    private FormPanel formPanel;

    public FormAnnoDettaglio() {
        createForm();
        binding = new FormBinding(formPanel, true);
        binding.bind(ordineModel);
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
        cp.add(formPanel, new RowData(.99, 0.963));  //DIM

        BorderLayoutData centerData = new BorderLayoutData(Style.LayoutRegion.CENTER);
        add(cp, centerData);
    }


    private void createForm() {
        formPanel = new FormPanel();

        formPanel.setFrame(true);
        formPanel.setHeading("Dettagli attività");

        formPanel.setButtonAlign(Style.HorizontalAlignment.CENTER);
        formPanel.setLayout(new ColumnLayout());

        ToolButton tool1 = new ToolButton("x-tool-help");
        formPanel.getHeader().addTool(tool1);
        tool1.addSelectionListener(new SelectionListener<IconButtonEvent>() {
            @Override
            public void componentSelected(IconButtonEvent ce) {
                Dispatcher.forwardEvent(UnAnnoDiAttivitaEvents.ShowInfoDialog);
            }
        });
        ToolButton tool = new ToolButton("x-tool-refresh");
        formPanel.getHeader().addTool(tool);
        tool.addSelectionListener(new SelectionListener<IconButtonEvent>() {
            @Override
            public void componentSelected(IconButtonEvent ce) {
                clear();
            }
        });

        LayoutContainer left = new LayoutContainer();
        left.setStyleAttribute("padding-right", "10px");
        left.setStyleAttribute("padding-Top", "20px");

        FormLayout layout = new FormLayout();
        layout.setLabelAlign(FormPanel.LabelAlign.LEFT);
        left.setLayout(layout);

        LabelField widget = new LabelField("Inserisci i dettagli dell'attività");
        widget.setStyleAttribute("font-weight", "bolder");
        widget.setStyleAttribute("padding-top","20px");
        widget.setStyleAttribute("padding-bottom","20px");
        widget.setStyleAttribute("padding-left","15px");
        formPanel.add(widget);

        TextField<String> nomeEvento = new TextField<String>();
        nomeEvento.setFieldLabel("Nome dell'attività");
        nomeEvento.setLabelStyle("width:150px");

        nomeEvento.setStyleAttribute("padding-bottom", "20px");
        nomeEvento.setName("nome");

        left.add(nomeEvento);

        TextField<String> anno = new TextField<String>();
        anno.setFieldLabel("Anno");
        anno.setLabelStyle("width:150px");

        anno.setStyleAttribute("padding-bottom", "20px");
        anno.setName("anno");
        left.add(anno);

        TextField<String> dove = new TextField<String>();
        dove.setFieldLabel("Dove");
        dove.setLabelStyle("width:150px");

        dove.setStyleAttribute("padding-bottom", "20px");
        dove.setName("dove");
        left.add(dove);

        TextArea note = new TextArea();
        note.setPreventScrollbars(true);
        note.setLabelStyle("width:150px");

        note.setStyleAttribute("padding-bottom", "20px");
        note.setFieldLabel("Note");
        note.setName("note");
        left.add(note);

        left.setWidth(600);
        left.setStyleAttribute("padding-left","15px");
        formPanel.add(left);

    }


    public void clear() {
        try {
            //  binding.unbind();
            binding.clear();
            formPanel.clear();
            ordineModel = new OrdineModel();
            binding.bind(ordineModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public OrdineModel getOrdineModel() {
        return ordineModel;
    }

    public void setOrdineModel(OrdineModel ordineModel) {
        this.ordineModel = ordineModel;
        binding.bind(ordineModel);
    }


    @Override
    protected void onLoad() {
        super.onLoad();
        formPanel.getBody().setStyleAttribute("border-bottom", "3px solid #f8b333");
        formPanel.getBody().setStyleAttribute("border-style", "solid");
        formPanel.getBody().setStyleAttribute("border-top", "3px solid #f8b333");
        formPanel.getBody().setStyleAttribute("border-width", "3px 0");
        formPanel.getBody().setStyleAttribute("margin-bottom", "0");

        //To change body of overridden methods use File | Settings | File Templates.
    }


}
