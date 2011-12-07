package it.agilis.mens.azzeroCO2.client.forms.annoAttivita;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.binding.FormBinding;
import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.ToolButton;
import com.extjs.gxt.ui.client.widget.form.*;
import com.extjs.gxt.ui.client.widget.layout.*;
import com.google.gwt.user.client.Element;
import it.agilis.mens.azzeroCO2.client.mvc.events.UnAnnoDiAttivitaEvents;
import it.agilis.mens.azzeroCO2.shared.model.evento.DettaglioModel;

/**
 * Created by IntelliJ IDEA.
 * User: serenadimaida
 * Date: 07/12/11
 * Time: 10:18
 * To change this template use File | Settings | File Templates.
 */
public class AnnoFormDettaglio extends LayoutContainer {

    private DettaglioModel dettaglioModel = new DettaglioModel();
    private FormBinding binding = null;
    private FormPanel formPanel;

    public AnnoFormDettaglio() {
        createForm();
        binding = new FormBinding(formPanel, true);
        binding.bind(dettaglioModel);
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
        left.setStyleAttribute("paddingRight", "10px");
        FormLayout layout = new FormLayout();
        layout.setLabelAlign(FormPanel.LabelAlign.LEFT);
        left.setLayout(layout);

        TextField<String> nomeEvento = new TextField<String>();
        nomeEvento.setFieldLabel("Nome dell'attività");
        nomeEvento.setName("nome");
        left.add(nomeEvento);

        DateField dataInizio = new DateField();
        dataInizio.setFieldLabel("Data inizio");

        dataInizio.setPropertyEditor(new DateTimePropertyEditor("dd.MM.yyyy"));
        dataInizio.setName("inizio");
        left.add(dataInizio);
        //   dataInizio.set

        TextArea note = new TextArea();
        note.setPreventScrollbars(true);
        note.setFieldLabel("Note");
        note.setName("note");
        left.add(note);

        LayoutContainer right = new LayoutContainer();
        right.setStyleAttribute("paddingLeft", "10px");
        layout = new FormLayout();
        layout.setLabelAlign(FormPanel.LabelAlign.LEFT);
        right.setLayout(layout);

        TextField<String> dove = new TextField<String>();
        dove.setFieldLabel("Dove");
        dove.setName("dove");
        right.add(dove);

        DateField dataFine = new DateField();
        dataFine.setFieldLabel("Data fine");
        dataFine.setPropertyEditor(new DateTimePropertyEditor("dd.MM.yyyy"));
        dataFine.setName("fine");
        right.add(dataFine);

        formPanel.add(left, new ColumnData(.5));
        formPanel.add(right, new ColumnData(.5));

       // return formPanel;
    }


    public void clear() {
        try {
            //  binding.unbind();
            binding.clear();
            formPanel.clear();
            dettaglioModel = new DettaglioModel();
            binding.bind(dettaglioModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public DettaglioModel getDettaglioModel() {
        return dettaglioModel;
    }

    public void setDettaglioModel(DettaglioModel dettaglioModel) {
        this.dettaglioModel = dettaglioModel;
        binding.bind(dettaglioModel);
    }


    @Override
    protected void onLoad() {
        super.onLoad();
        formPanel.getBody().setStyleAttribute("border-bottom", "3px solid orange");
        formPanel.getBody().setStyleAttribute("border-style", "solid");
        formPanel.getBody().setStyleAttribute("border-top", "3px solid orange");
        formPanel.getBody().setStyleAttribute("border-width", "3px 0");
        formPanel.getBody().setStyleAttribute("margin-bottom", "0");

        //To change body of overridden methods use File | Settings | File Templates.
    }


}
