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
        layout2.setPadding(new Padding(2));
        layout2.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.MIDDLE);
        c2.setLayout(layout2);

        c2.add(new LabelField("Inserisci il numero di tonnellate di merce per chilometraggio a mezzo di trasporto. Il trasporto viene calcolato su medie di chilometraggio"), flex);

        panel.add(c2);
        LayoutContainer km30 = new LayoutContainer();
        HBoxLayout layoutRigaKm30 = new HBoxLayout();
        layoutRigaKm30.setPadding(new Padding(2));
        km30.setLayout(layoutRigaKm30);
        km30.add(new LabelField("Distanza percorsa: provinciale"), flex);
        panel.add(km30);
        LayoutContainer km30input = new LayoutContainer();
        HBoxLayout layoutKm30input = new HBoxLayout();
        layoutKm30input.setPadding(new Padding(2));
        layoutKm30input.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.MIDDLE);
        km30input.setLayout(layoutKm30input);

        Image furgone = new Image(AzzeroCO2Resources.INSTANCE.furgone());
        furgone.setAltText("Furgone");
        NumberField furgoneKm30 = new NumberField();
        furgoneKm30.setName("furgoneKm30");
        furgoneKm30.setWidth(60);

        Image tir = new Image(AzzeroCO2Resources.INSTANCE.tir());
        furgone.setAltText("Tir");
        NumberField tirKm30 = new NumberField();
        tirKm30.setName("tirKm30");
        tirKm30.setWidth(60);

        km30input.add(furgone);
        km30input.add(furgoneKm30, flex);
        km30input.add(tir);
        km30input.add(tirKm30, flex);

        layoutRigaKm30.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.MIDDLE);
        panel.add(km30input, new FormData("100%"));

        LayoutContainer km150 = new LayoutContainer();
        HBoxLayout layoutRigaKm150 = new HBoxLayout();
        layoutRigaKm150.setPadding(new Padding(2));
        layoutRigaKm150.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.MIDDLE);
        km150.setLayout(layoutRigaKm150);

        km150.add(new LabelField("Distanza percorsa: regionale"), flex);
        panel.add(km150);

        LayoutContainer km150input = new LayoutContainer();
        HBoxLayout layoutKm150input = new HBoxLayout();
        layoutKm150input.setPadding(new Padding(2));
        layoutKm150input.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.MIDDLE);
        km150input.setLayout(layoutKm150input);


        Image furgone150 = new Image(AzzeroCO2Resources.INSTANCE.furgone());
        furgone150.setAltText("Bus");
        NumberField furgoneKm150 = new NumberField();
        furgoneKm150.setName("furgoneKm150");
        furgoneKm150.setWidth(60);

        Image tir150 = new Image(AzzeroCO2Resources.INSTANCE.tir());
        tir150.setAltText("Tir");
        NumberField tirKm150 = new NumberField();
        tirKm150.setName("tirKm150");
        tirKm150.setWidth(60);

        Image treno150 = new Image(AzzeroCO2Resources.INSTANCE.treno());
        treno150.setAltText("Treno");
        NumberField trenoKm150 = new NumberField();
        trenoKm150.setName("trenoKm150");
        trenoKm150.setWidth(60);

        km150input.add(furgone150);
        km150input.add(furgoneKm150, flex);
        km150input.add(tir150);
        km150input.add(tirKm150, flex);
        km150input.add(treno150);
        km150input.add(trenoKm150, flex);
        panel.add(km150input, new FormData("100%"));

        LayoutContainer km500 = new LayoutContainer();
        HBoxLayout layoutRigaKm500 = new HBoxLayout();
        layoutRigaKm500.setPadding(new Padding(2));
        layoutRigaKm500.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.MIDDLE);
        km500.setLayout(layoutRigaKm500);
        km500.add(new LabelField("Distanza percorsa: nazionale"), flex);
        panel.add(km500);

        LayoutContainer km500input = new LayoutContainer();
        HBoxLayout layoutKm500input = new HBoxLayout();
        layoutKm500input.setPadding(new Padding(2));
        layoutKm500input.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.MIDDLE);
        km500input.setLayout(layoutKm500input);

        Image furgone500 = new Image(AzzeroCO2Resources.INSTANCE.furgone());
        furgone500.setAltText("Bus");
        NumberField furgoneKm500 = new NumberField();
        furgoneKm500.setName("furgoneKm500");
        furgoneKm500.setWidth(60);

        Image tir500 = new Image(AzzeroCO2Resources.INSTANCE.tir());
        tir500.setAltText("Auto");
        NumberField tirKm500 = new NumberField();
        tirKm500.setName("tirKm500");
        tirKm500.setWidth(60);

        Image treno500 = new Image(AzzeroCO2Resources.INSTANCE.treno());
        treno500.setAltText("Treno");
        NumberField trenoKm500 = new NumberField();
        trenoKm500.setName("trenoKm500");
        trenoKm500.setWidth(60);

        Image nave500 = new Image(AzzeroCO2Resources.INSTANCE.nave());
        nave500.setAltText("Nave");
        NumberField naveKm500 = new NumberField();
        naveKm500.setName("naveKm500");
        naveKm500.setWidth(60);

        km500input.add(furgone500);
        km500input.add(furgoneKm500, flex);
        km500input.add(tir500);
        km500input.add(tirKm500, flex);
        km500input.add(treno500);
        km500input.add(trenoKm500, flex);
        km500input.add(nave500);
        km500input.add(naveKm500, flex);
        panel.add(km500input, new FormData("100%"));

        LayoutContainer km1500 = new LayoutContainer();
        HBoxLayout layoutRigaKm15001500 = new HBoxLayout();
        layoutRigaKm15001500.setPadding(new Padding(2));
        layoutRigaKm15001500.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.MIDDLE);
        km1500.setLayout(layoutRigaKm15001500);

        km1500.add(new LabelField("Distanza percorsa: europea"), flex);
        panel.add(km1500);
        LayoutContainer km1500input = new LayoutContainer();
        HBoxLayout layoutKm15001500input = new HBoxLayout();
        layoutKm15001500input.setPadding(new Padding(2));
        layoutKm15001500input.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.MIDDLE);
        km1500input.setLayout(layoutKm15001500input);

        Image furgone1500 = new Image(AzzeroCO2Resources.INSTANCE.furgone());
        furgone1500.setAltText("Furgone");
        NumberField furgoneKm1500 = new NumberField();
        furgoneKm1500.setWidth(60);

        Image tir1500 = new Image(AzzeroCO2Resources.INSTANCE.tir());
        tir1500.setAltText("Tir");
        NumberField tirKm1500 = new NumberField();
        tirKm1500.setWidth(60);

        Image treno1500 = new Image(AzzeroCO2Resources.INSTANCE.treno());
        treno1500.setAltText("Treno");
        NumberField trenoKm15001500 = new NumberField();
        trenoKm15001500.setWidth(60);

        Image nave1500 = new Image(AzzeroCO2Resources.INSTANCE.nave());
        nave1500.setAltText("Nave");
        NumberField naveKm15001500 = new NumberField();
        naveKm15001500.setWidth(60);

        Image aereo1500 = new Image(AzzeroCO2Resources.INSTANCE.aereo());
        aereo1500.setAltText("Aereo");
        NumberField aereoKm15001500 = new NumberField();
        aereoKm15001500.setWidth(60);

        km1500input.add(furgone1500);
        km1500input.add(furgoneKm1500, flex);
        km1500input.add(tir1500);
        km1500input.add(tirKm1500, flex);
        km1500input.add(treno1500);
        km1500input.add(trenoKm15001500, flex);
        km1500input.add(nave1500);
        km1500input.add(naveKm15001500, flex);

        km1500input.add(aereo1500);
        km1500input.add(aereoKm15001500, flex);
        panel.add(km1500input, new FormData("100%"));
        
        LayoutContainer km9000 = new LayoutContainer();
        HBoxLayout layoutRigaKm90009000 = new HBoxLayout();
        layoutRigaKm90009000.setPadding(new Padding(2));
        layoutRigaKm90009000.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.MIDDLE);
        km9000.setLayout(layoutRigaKm90009000);

        km9000.add(new LabelField("Distanza percorsa: Extraeuropea"), flex);
        panel.add(km9000);
        LayoutContainer km9000input = new LayoutContainer();
        HBoxLayout layoutKm90009000input = new HBoxLayout();
        layoutKm90009000input.setPadding(new Padding(2));
        layoutKm90009000input.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.MIDDLE);
        km9000input.setLayout(layoutKm90009000input);

        Image furgone9000 = new Image(AzzeroCO2Resources.INSTANCE.furgone());
        furgone9000.setAltText("Furgone");
        NumberField furgoneKm9000 = new NumberField();
        furgoneKm9000.setWidth(60);

        Image tir9000 = new Image(AzzeroCO2Resources.INSTANCE.tir());
        tir9000.setAltText("Tir");
        NumberField tirKm9000 = new NumberField();
        tirKm9000.setWidth(60);

        Image treno9000 = new Image(AzzeroCO2Resources.INSTANCE.treno());
        treno9000.setAltText("Treno");
        NumberField trenoKm90009000 = new NumberField();
        trenoKm90009000.setWidth(60);

        Image nave9000 = new Image(AzzeroCO2Resources.INSTANCE.nave());
        nave9000.setAltText("Nave");
        NumberField naveKm90009000 = new NumberField();
        naveKm90009000.setWidth(60);

        Image aereo9000 = new Image(AzzeroCO2Resources.INSTANCE.aereo());
        aereo9000.setAltText("Aereo");
        NumberField aereoKm90009000 = new NumberField();
        aereoKm90009000.setWidth(60);

        km9000input.add(furgone9000);
        km9000input.add(furgoneKm9000, flex);
        km9000input.add(tir9000);
        km9000input.add(tirKm9000, flex);
        km9000input.add(treno9000);
        km9000input.add(trenoKm90009000, flex);
        km9000input.add(nave9000);
        km9000input.add(naveKm90009000, flex);

        km9000input.add(aereo9000);
        km9000input.add(aereoKm90009000, flex);
        panel.add(km9000input, new FormData("100%"));

        return panel;
    }

    public void clear() {
        binding.clear();
    }

    public TrasportoMerciModel getTrasportoMerciModel() {
        return trasportoMerciModel;
    }

    public void setTrasportoMerciModel(TrasportoMerciModel trasportoMerciModel) {
        this.trasportoMerciModel = trasportoMerciModel;
    }

}

