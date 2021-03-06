package it.agilis.mens.azzeroCO2.client.forms.evento;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.binding.FormBinding;
import com.extjs.gxt.ui.client.event.*;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.ToolButton;
import com.extjs.gxt.ui.client.widget.form.*;
import com.extjs.gxt.ui.client.widget.layout.*;
import com.google.gwt.user.client.Element;
import it.agilis.mens.azzeroCO2.client.MyInfo;
import it.agilis.mens.azzeroCO2.client.mvc.events.AzzeroCO2Events;
import it.agilis.mens.azzeroCO2.shared.model.OrdineModel;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 6/19/11
 * Time: 5:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class EventoFormDettaglio extends LayoutContainer {

    private OrdineModel ordineModel = new OrdineModel();
    private FormBinding binding = null;
    private FormPanel formPanel;

    public EventoFormDettaglio() {
        formPanel = createForm();
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
        cp.add(formPanel, new RowData(0.99, .991));

        BorderLayoutData centerData = new BorderLayoutData(Style.LayoutRegion.CENTER);
        add(cp, centerData);
    }


    private FormPanel createForm() {
        FormPanel panel = new FormPanel();

        panel.setFrame(true);
        panel.setHeading("Dettagli evento");

        panel.setButtonAlign(Style.HorizontalAlignment.CENTER);
        panel.setLayout(new ColumnLayout());

        ToolButton tool1 = new ToolButton("x-tool-help");
        panel.getHeader().addTool(tool1);
        tool1.addSelectionListener(new SelectionListener<IconButtonEvent>() {
            @Override
            public void componentSelected(IconButtonEvent ce) {
                Dispatcher.forwardEvent(AzzeroCO2Events.ShowInfoDialog, "Indica il nome, il luogo e la data dell'evento.<br><br>Il nome dell'evento è obbligatorio.");
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

        LayoutContainer left = new LayoutContainer();
        left.setStyleAttribute("padding-right", "10px");
        FormLayout layout = new FormLayout();
        layout.setLabelAlign(FormPanel.LabelAlign.LEFT);
        left.setLayout(layout);

        TextField<String> nomeEvento = new TextField<String>();
        nomeEvento.setFieldLabel("Nome (*)");
        nomeEvento.setName("nome");
        nomeEvento.setStyleAttribute("padding-bottom", "20px");

        left.add(nomeEvento);

        final DateField dataInizio = new DateField();
        final DateField dataFine = new DateField();

        dataInizio.setFieldLabel("Data inizio");
        dataInizio.addListener(Events.Change, new Listener<FieldEvent>() {
            public void handleEvent(FieldEvent p_event) {
                if (dataFine.getValue() != null && p_event.getValue() != null &&
                        dataFine.getValue().before((Date) p_event.getValue())) {
                    MyInfo.show("La data di fine non puà precedere la data di inizio dell'evento..");
                }

                // Window.alert("change: " + p_event.value);
            }
        });

        dataInizio.setPropertyEditor(new DateTimePropertyEditor("dd.MM.yyyy"));
        dataInizio.setName("inizio");
        dataInizio.setStyleAttribute("padding-bottom", "20px");
        left.add(dataInizio);
        //   dataInizio.set

        TextArea note = new TextArea();
        note.setPreventScrollbars(true);
        note.setFieldLabel("Note");
        note.setName("note");
        left.add(note);

        LabelField ps = new LabelField("(*) campo obbligatorio");

        left.add(ps);


        LayoutContainer right = new LayoutContainer();
        right.setStyleAttribute("padding-left", "10px");
        layout = new FormLayout();
        layout.setLabelAlign(FormPanel.LabelAlign.LEFT);
        right.setLayout(layout);

        TextField<String> dove = new TextField<String>();
        dove.setFieldLabel("Dove");
        dove.setName("dove");
        dove.setStyleAttribute("padding-bottom", "20px");
        right.add(dove);


        dataFine.setFieldLabel("Data fine");
        dataFine.setPropertyEditor(new DateTimePropertyEditor("dd.MM.yyyy"));
        dataFine.addListener(Events.Change, new Listener<FieldEvent>() {
            public void handleEvent(FieldEvent p_event) {
                if (dataInizio.getValue() != null && p_event.getValue() != null &&
                        dataInizio.getValue().after((Date) p_event.getValue())) {
                    MyInfo.show("La data di fine non puà precedere la data di inizio dell'evento.");
                }
            }
        });
        dataFine.setName("fine");

        right.add(dataFine);

        left.setStyleAttribute("padding-top", "20px");
        right.setStyleAttribute("padding-top", "20px");

        panel.add(left, new ColumnData(.5));
        panel.add(right, new ColumnData(.5));


        return panel;
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
        formPanel.getBody().setStyleAttribute("margin-bottom", "0px");
    }

}
