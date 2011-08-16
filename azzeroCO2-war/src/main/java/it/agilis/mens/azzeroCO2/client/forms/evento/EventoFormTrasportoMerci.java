package it.agilis.mens.azzeroCO2.client.forms.evento;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.binding.FormBinding;
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
import com.google.gwt.user.client.ui.Image;
import it.agilis.mens.azzeroCO2.client.AzzeroCO2Resources;
import it.agilis.mens.azzeroCO2.shared.model.evento.TrasportoMerciModel;


/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 6/19/11
 * Time: 5:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class EventoFormTrasportoMerci extends LayoutContainer {

    private TrasportoMerciModel trasportoMerciModel = new TrasportoMerciModel();
    private FormBinding binding = null;

    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);

        BorderLayout layout = new BorderLayout();
        setLayout(layout);

        ContentPanel cp = new ContentPanel();
        cp.setFrame(true);
        cp.setHeaderVisible(false);
        cp.setLayout(new RowLayout(Style.Orientation.HORIZONTAL));

        FormPanel panel = createForm();
        panel.setHeading("Trasporto merci");
        panel.getHeader().addTool(new ToolButton("x-tool-help"));
        panel.getHeader().addTool(new ToolButton("x-tool-close"));

        binding = new FormBinding(panel, true);
        binding.bind(trasportoMerciModel);

        cp.add(panel, new RowData(1, 1));

        BorderLayoutData centerData = new BorderLayoutData(Style.LayoutRegion.CENTER);
        add(cp, centerData);
    }

    private FormPanel createForm() {
        FormPanel panel = new FormPanel();
        panel.setFrame(true);
        panel.setLabelAlign(FormPanel.LabelAlign.LEFT);

        HBoxLayoutData flex = new HBoxLayoutData(new Margins(0, 5, 0, 0));
        LayoutContainer c2 = new LayoutContainer();
        HBoxLayout layout2 = new HBoxLayout();
        layout2.setPadding(new Padding(10));
        layout2.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.MIDDLE);
        c2.setLayout(layout2);

        c2.add(new LabelField("Inserisci il numero di tonnellate di merce per chilometraggio a mezzo di trasporto. Il trasporto viene calcolato su medie di chilometraggio"), flex);

        panel.add(c2);
        LayoutContainer piu50 = new LayoutContainer();
        HBoxLayout layoutRigaPiu50 = new HBoxLayout();
        layoutRigaPiu50.setPadding(new Padding(10));
        piu50.setLayout(layoutRigaPiu50);
        piu50.add(new LabelField("Distanza percorsa >50 km"), flex);
        panel.add(piu50);
        LayoutContainer piu50input = new LayoutContainer();
        HBoxLayout layoutPiu50input = new HBoxLayout();
        layoutPiu50input.setPadding(new Padding(10));
        layoutPiu50input.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.MIDDLE);
        piu50input.setLayout(layoutPiu50input);

        Image bus = new Image(AzzeroCO2Resources.INSTANCE.bus());
        bus.setAltText("Bus");
        NumberField busPiu50 = new NumberField();
        busPiu50.setName("busPiu50");
        busPiu50.setWidth(60);

        Image automobile = new Image(AzzeroCO2Resources.INSTANCE.automobile());
        bus.setAltText("Auto");
        NumberField autoPiu50 = new NumberField();
        autoPiu50.setName("autoPiu50");
        autoPiu50.setWidth(60);

        Image treno = new Image(AzzeroCO2Resources.INSTANCE.treno());
        bus.setAltText("Treno");
        NumberField trenoPiu50 = new NumberField();
        trenoPiu50.setName("trenoPiu50");
        trenoPiu50.setWidth(60);

        piu50input.add(bus);
        piu50input.add(busPiu50, flex);
        piu50input.add(automobile);
        piu50input.add(autoPiu50, flex);
        piu50input.add(treno);
        piu50input.add(trenoPiu50, flex);

        layoutRigaPiu50.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.MIDDLE);
        panel.add(piu50input, new FormData("100%"));

        LayoutContainer piu100 = new LayoutContainer();
        HBoxLayout layoutRigaPiu100 = new HBoxLayout();
        layoutRigaPiu100.setPadding(new Padding(10));
        layoutRigaPiu100.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.MIDDLE);
        piu100.setLayout(layoutRigaPiu100);

        piu100.add(new LabelField("Distanza percorsa >100 km"), flex);
        panel.add(piu100);

        LayoutContainer piu100input = new LayoutContainer();
        HBoxLayout layoutPiu100input = new HBoxLayout();
        layoutPiu100input.setPadding(new Padding(10));
        layoutPiu100input.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.MIDDLE);
        piu100input.setLayout(layoutPiu100input);


        Image bus100 = new Image(AzzeroCO2Resources.INSTANCE.bus());
        bus100.setAltText("Bus");
        NumberField busPiu100 = new NumberField();
        busPiu100.setName("busPiu100");
        busPiu100.setWidth(60);

        Image automobile100 = new Image(AzzeroCO2Resources.INSTANCE.automobile());
        automobile100.setAltText("Auto");
        NumberField autoPiu100 = new NumberField();
        autoPiu100.setName("autoPiu100");
        autoPiu100.setWidth(60);

        Image treno100 = new Image(AzzeroCO2Resources.INSTANCE.treno());
        treno100.setAltText("Treno");
        NumberField trenoPiu100 = new NumberField();
        trenoPiu100.setName("trenoPiu100");
        trenoPiu100.setWidth(60);

        piu100input.add(bus100);
        piu100input.add(busPiu100, flex);
        piu100input.add(automobile100);
        piu100input.add(autoPiu100, flex);
        piu100input.add(treno100);
        piu100input.add(trenoPiu100, flex);
        panel.add(piu100input, new FormData("100%"));

        LayoutContainer piu250 = new LayoutContainer();
        HBoxLayout layoutRigaPiu250 = new HBoxLayout();
        layoutRigaPiu250.setPadding(new Padding(10));
        layoutRigaPiu250.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.MIDDLE);
        piu250.setLayout(layoutRigaPiu250);
        piu250.add(new LabelField("Distanza percorsa >250 km"), flex);
        panel.add(piu250);

        LayoutContainer piu250input = new LayoutContainer();
        HBoxLayout layoutPiu250input = new HBoxLayout();
        layoutPiu250input.setPadding(new Padding(10));
        layoutPiu250input.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.MIDDLE);
        piu250input.setLayout(layoutPiu250input);

        Image bus250 = new Image(AzzeroCO2Resources.INSTANCE.bus());
        bus250.setAltText("Bus");
        NumberField busPiu250 = new NumberField();
        busPiu250.setName("busPiu250");
        busPiu250.setWidth(60);


        Image automobile250 = new Image(AzzeroCO2Resources.INSTANCE.automobile());
        automobile250.setAltText("Auto");
        NumberField autoPiu250 = new NumberField();
        autoPiu250.setName("autoPiu250");
        autoPiu250.setWidth(60);

        Image treno250 = new Image(AzzeroCO2Resources.INSTANCE.treno());
        treno250.setAltText("Treno");
        NumberField trenoPiu250 = new NumberField();
        trenoPiu250.setName("trenoPiu250");
        trenoPiu250.setWidth(60);

        Image aereo250 = new Image(AzzeroCO2Resources.INSTANCE.aereo());
        aereo250.setAltText("Aereo");
        NumberField aereoPiu250 = new NumberField();
        aereoPiu250.setName("aereoPiu250");
        aereoPiu250.setWidth(60);

        piu250input.add(bus250);
        piu250input.add(busPiu250, flex);
        piu250input.add(automobile250);
        piu250input.add(autoPiu250, flex);
        piu250input.add(treno250);
        piu250input.add(trenoPiu250, flex);
        piu250input.add(aereo250);
        piu250input.add(aereoPiu250, flex);
        panel.add(piu250input, new FormData("100%"));

        LayoutContainer piu500 = new LayoutContainer();
        HBoxLayout layoutRigaPiu500 = new HBoxLayout();
        layoutRigaPiu500.setPadding(new Padding(10));
        layoutRigaPiu500.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.MIDDLE);
        piu500.setLayout(layoutRigaPiu500);

        piu500.add(new LabelField("Distanza percorsa >500 km"), flex);
        panel.add(piu500);
        LayoutContainer piu500input = new LayoutContainer();
        HBoxLayout layoutPiu500input = new HBoxLayout();
        layoutPiu500input.setPadding(new Padding(10));
        layoutPiu500input.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.MIDDLE);
        piu500input.setLayout(layoutPiu500input);

        Image bus500 = new Image(AzzeroCO2Resources.INSTANCE.bus());
        bus500.setAltText("Bus");
        NumberField busPiu500 = new NumberField();
        busPiu500.setWidth(60);
        Image automobile500 = new Image(AzzeroCO2Resources.INSTANCE.automobile());
        automobile500.setAltText("Auto");
        NumberField autoPiu500 = new NumberField();
        autoPiu500.setWidth(60);

        Image treno500 = new Image(AzzeroCO2Resources.INSTANCE.treno());
        treno500.setAltText("Treno");
        NumberField trenoPiu500 = new NumberField();
        trenoPiu500.setWidth(60);

        Image aereo500 = new Image(AzzeroCO2Resources.INSTANCE.aereo());
        aereo500.setAltText("Aereo");

        NumberField aereoPiu500 = new NumberField();
        aereoPiu500.setWidth(60);
        piu500input.add(bus500);
        piu500input.add(busPiu500, flex);
        piu500input.add(automobile500);
        piu500input.add(autoPiu500, flex);
        piu500input.add(treno500);
        piu500input.add(trenoPiu500, flex);
        piu500input.add(aereo500);
        piu500input.add(aereoPiu500, flex);
        panel.add(piu500input, new FormData("100%"));

        return panel;
    }

    public void clear() {
        binding.clear();
    }

    public void setModelObject(TrasportoMerciModel trasportoMerciModel) {
        this.trasportoMerciModel = trasportoMerciModel;
    }
}
