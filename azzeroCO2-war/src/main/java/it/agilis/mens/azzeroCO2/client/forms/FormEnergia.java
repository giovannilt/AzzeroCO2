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
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.layout.*;
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

        BorderLayout layout = new BorderLayout();
        setLayout(layout);

        ContentPanel cp = new ContentPanel();
        cp.setFrame(true);
        cp.setHeaderVisible(false);
        cp.setLayout(new RowLayout(Style.Orientation.HORIZONTAL));
        cp.add(formPanel, new RowData(1, 1));


        //  cp.setStyleAttribute("padding0 10px;

        BorderLayoutData centerData = new BorderLayoutData(Style.LayoutRegion.CENTER);
        add(cp, centerData);
    }


    private FormPanel createForm() {
        FormPanel panel = new FormPanel();
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

        //     HBoxLayoutData flex = new HBoxLayoutData(new Margins(0, 5, 0, 0));
        LayoutContainer c2 = new LayoutContainer();
        HBoxLayout layout2 = new HBoxLayout();
        layout2.setPadding(new Padding(10));
        layout2.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.BOTTOM);
        c2.setLayout(layout2);

        LabelField widget = new LabelField("Inserisci nei relativi campi la quantità di energia utilizzata per realizzare l'evento.");
        widget.setStyleAttribute("font-weight", "bolder");

        c2.add(widget);

        panel.add(c2);

        LayoutContainer c = new LayoutContainer();
        HBoxLayout layout = new HBoxLayout();
        layout.setPadding(new Padding(10));
        layout.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.BOTTOM);

        c.setLayout(layout);

        NumberField energiaElettrica = new NumberField();
        energiaElettrica.setWidth(60);
        energiaElettrica.setName("energiaElettrica");
        //energiaElettrica.setPropertyEditorType(Integer.class);

        LabelField label = new LabelField("Energia Elettrica ");
        label.setWidth(100);
        c.add(label);
        c.add(energiaElettrica);
        LabelField kwh = new LabelField("kWh");
        kwh.setStyleAttribute("font-size", "11px");
        kwh.setStyleAttribute("padding-left", "3px");
        c.add(kwh, new HBoxLayoutData(new Margins(0, 5, 0, 0)));
        panel.add(c, new FormData("100%"));

        LayoutContainer c3 = new LayoutContainer();
        HBoxLayout layout3 = new HBoxLayout();
        layout3.setPadding(new Padding(10));
        layout3.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.BOTTOM);
        c3.setLayout(layout3);

        LabelField note = new LabelField("Non riempire in caso di energia da fonti rinnovabili.<br><br><br>");
        note.setStyleAttribute("font-style", "italic");
        c3.add(note);

        panel.add(c3);

        LayoutContainer cGas = new LayoutContainer();
        HBoxLayout layoutGas = new HBoxLayout();
        layoutGas.setPadding(new Padding(10));
        layoutGas.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.BOTTOM);
        cGas.setLayout(layoutGas);

        NumberField gasMetano = new NumberField();
        gasMetano.setName("gasMetano");
        gasMetano.setWidth(60);
        //  gasMetano.setPropertyEditorType(Integer.class);

        label = new LabelField("Gas metano ");
        label.setWidth(100);
        cGas.add(label);
        cGas.add(gasMetano);
        LabelField widget1 = new LabelField("m<sup>3</sup>");
        widget1.setStyleAttribute("font-size", "11px");
        widget1.setStyleAttribute("padding-left", "3px");
        cGas.add(widget1, new HBoxLayoutData(new Margins(0, 5, 0, 0)));

        panel.add(cGas, new FormData("100%"));

        LayoutContainer cGasoline = new LayoutContainer();
        HBoxLayout layoutGasoline = new HBoxLayout();
        layoutGasoline.setPadding(new Padding(10));
        layoutGasoline.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.BOTTOM);
        cGasoline.setLayout(layoutGasoline);

        NumberField gasolio = new NumberField();
        gasolio.setName("gasolio");
        gasolio.setWidth(60);
        //  gasolio.setPropertyEditorType(Integer.class);

        label = new LabelField("Gasolio ");
        label.setWidth(100);
        cGasoline.add(label);
        cGasoline.add(gasolio);
        LabelField litri = new LabelField("litri");
        litri.setStyleAttribute("font-size", "11px");
        litri.setStyleAttribute("padding-left", "3px");
        cGasoline.add(litri, new HBoxLayoutData(new Margins(0, 5, 0, 0)));

        panel.add(cGasoline, new FormData("100%"));

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
    protected void onAfterLayout() {
        super.onAfterLayout();
        formPanel.getBody().setStyleAttribute("border-bottom", "3px solid #f8b333");
        formPanel.getBody().setStyleAttribute("border-style", "solid");
        formPanel.getBody().setStyleAttribute("border-top", "3px solid #f8b333");
        formPanel.getBody().setStyleAttribute("border-width", "3px 0");
        formPanel.getBody().setStyleAttribute("margin-bottom", "0");
    }
}

