package it.agilis.mens.azzeroCO2.client.forms;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.binding.FormBinding;
import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.ToolButton;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.layout.RowData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.google.gwt.user.client.Element;
import it.agilis.mens.azzeroCO2.client.mvc.events.AzzeroCO2Events;
import it.agilis.mens.azzeroCO2.shared.model.evento.EnergiaModel;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 6/19/11
 * Time: 5:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class FormEnergia extends LayoutContainer {

    private EnergiaModel energiaModel = new EnergiaModel();
    private FormBinding binding = null;
    private FormPanel formPanel;

    public FormEnergia() {
        formPanel = createForm();
        binding = new FormBinding(formPanel, true);
        binding.bind(energiaModel);
    }

    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);

        setLayout(new RowLayout(Style.Orientation.HORIZONTAL));
        add(formPanel, new RowData(.99, 0.96));

    }


    private FormPanel createForm() {
        FormPanel panel = new FormPanel();

        FormLayout layout = new FormLayout();
        layout.setLabelWidth(110);
        layout.setDefaultWidth(500);
        panel.setLayout(layout);

        panel.setFrame(true);

        panel.setHeading("Energia");
        panel.setLabelAlign(LabelAlign.LEFT);

        ToolButton tool1 = new ToolButton("x-tool-help");
        panel.getHeader().addTool(tool1);
        tool1.addSelectionListener(new SelectionListener<IconButtonEvent>() {
            @Override
            public void componentSelected(IconButtonEvent ce) {
                Dispatcher.forwardEvent(AzzeroCO2Events.ShowInfoDialog, "Energia elettrica:  Il dato tiene conto di tutti i combustibili usati nelle centrali elettriche italiane.<br><br>Gas/gasolio: il dato tiene conto dell’uso del combustibile nei generatori domestici più utilizzati, sia unifamiliari che centralizzati.");
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


        LabelField label = new LabelField("Inserisci nei relativi campi la quantità di energia utilizzata per realizzare l'evento.<br>");
        label.setStyleAttribute("font-weight", "bolder");
        panel.add(label);

        NumberField energiaElettrica = new NumberField();
        energiaElettrica.setFieldLabel("Energia Elettrica ");
        energiaElettrica.setLabelStyle(energiaElettrica.getLabelStyle()+";font-size:11px");
        energiaElettrica.setLabelSeparator("");
        energiaElettrica.setWidth(60);
        energiaElettrica.setName("energiaElettrica");
         panel.add(energiaElettrica, new FormData("30%"));

        LabelField note = new LabelField("Non riempire in caso di energia da fonti rinnovabili.<br><br><br>");
        note.setStyleAttribute("font-style", "italic");
        panel.add(note);

        NumberField gasMetano = new NumberField();
        gasMetano.setFieldLabel("Gas metano m<sup>3</sup>");
        gasMetano.setLabelStyle(energiaElettrica.getLabelStyle()+";font-size:11px");
        gasMetano.setLabelSeparator("");
        gasMetano.setName("gasMetano");
        gasMetano.setWidth(60);
        panel.add(gasMetano, new FormData("30%"));

        NumberField gasolio = new NumberField();
        gasolio.setFieldLabel("Gasolio litri");
        gasolio.setLabelStyle(energiaElettrica.getLabelStyle()+";font-size:11px");
        gasolio.setLabelSeparator("");
        gasolio.setName("gasolio");
        gasolio.setWidth(60);
        panel.add(gasolio, new FormData("30%"));

        return panel;
    }

    public void clear() {
        binding.clear();
        energiaModel = new EnergiaModel();
        binding.bind(energiaModel);
    }

    public EnergiaModel getEnergiaModel() {
        return energiaModel;
    }

    public void setEnergiaModel(EnergiaModel energiaModel) {
        if (energiaModel != null) {
            this.energiaModel = energiaModel;
            binding.bind(energiaModel);
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
    }
}







