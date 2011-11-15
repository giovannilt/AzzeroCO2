package it.agilis.mens.azzeroCO2.client.forms.evento;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.binding.FormBinding;
import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
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
    private FormPanel panel = createForm();

    public EventoFormTrasportoMerci() {
        binding = new FormBinding(panel, true);
        binding.bind(trasportoMerciModel);
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


        panel.setHeading("Trasporto merci");
        panel.getHeader().addTool(new ToolButton("x-tool-help"));
        ToolButton tool = new ToolButton("x-tool-refresh");
        panel.getHeader().addTool(tool);
        tool.addSelectionListener(new SelectionListener<IconButtonEvent>() {
            @Override
            public void componentSelected(IconButtonEvent ce) {
                clear();
            }
        });


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

        LabelField istruzione=new LabelField("Inserisci il numero di tonnellate di merce per chilometraggio a mezzo di trasporto. </br>");
        LabelField note =new LabelField("Il trasporto viene calcolato su medie di chilometraggio");
        istruzione.setStyleAttribute("font-weight","bold");
        note.setStyleAttribute("font-style","italic");

        c2.add(istruzione, flex);
        panel.add(c2);

        LayoutContainer c3 = new LayoutContainer();
        HBoxLayout layout3 = new HBoxLayout();
        layout3.setPadding(new Padding(2));
        layout3.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.MIDDLE);
        c3.setLayout(layout3);
        c3.add(note,flex);
        panel.add(c3);





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
        furgoneKm30.setPropertyEditorType(Double.class);

        Image tir = new Image(AzzeroCO2Resources.INSTANCE.tir());
        furgone.setAltText("Tir");
        NumberField tirKm30 = new NumberField();
        tirKm30.setName("tirKm30");
        tirKm30.setWidth(60);
        tirKm30.setPropertyEditorType(Double.class);

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
        furgoneKm150.setPropertyEditorType(Double.class);

        Image tir150 = new Image(AzzeroCO2Resources.INSTANCE.tir());
        tir150.setAltText("Tir");
        NumberField tirKm150 = new NumberField();
        tirKm150.setName("tirKm150");
        tirKm150.setWidth(60);
        tirKm150.setPropertyEditorType(Double.class);

        Image treno150 = new Image(AzzeroCO2Resources.INSTANCE.treno());
        treno150.setAltText("Treno");
        NumberField trenoKm150 = new NumberField();
        trenoKm150.setName("trenoKm150");
        trenoKm150.setWidth(60);
        trenoKm150.setPropertyEditorType(Double.class);

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
        furgoneKm500.setPropertyEditorType(Double.class);

        Image tir500 = new Image(AzzeroCO2Resources.INSTANCE.tir());
        tir500.setAltText("Auto");
        NumberField tirKm500 = new NumberField();
        tirKm500.setName("tirKm500");
        tirKm500.setWidth(60);
        tirKm500.setPropertyEditorType(Double.class);

        Image treno500 = new Image(AzzeroCO2Resources.INSTANCE.treno());
        treno500.setAltText("Treno");
        NumberField trenoKm500 = new NumberField();
        trenoKm500.setName("trenoKm500");
        trenoKm500.setWidth(60);
        trenoKm500.setPropertyEditorType(Double.class);

        Image nave500 = new Image(AzzeroCO2Resources.INSTANCE.nave());
        nave500.setAltText("Nave");
        NumberField naveKm500 = new NumberField();
        naveKm500.setName("naveKm500");
        naveKm500.setWidth(60);
        naveKm500.setPropertyEditorType(Double.class);

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
        HBoxLayout layoutRigaKm1500 = new HBoxLayout();
        layoutRigaKm1500.setPadding(new Padding(2));
        layoutRigaKm1500.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.MIDDLE);
        km1500.setLayout(layoutRigaKm1500);

        km1500.add(new LabelField("Distanza percorsa: europea"), flex);
        panel.add(km1500);
        LayoutContainer km1500input = new LayoutContainer();
        HBoxLayout layoutKm1500input = new HBoxLayout();
        layoutKm1500input.setPadding(new Padding(2));
        layoutKm1500input.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.MIDDLE);
        km1500input.setLayout(layoutKm1500input);

        Image furgone1500 = new Image(AzzeroCO2Resources.INSTANCE.furgone());
        furgone1500.setAltText("Furgone");
        NumberField furgoneKm1500 = new NumberField();
        furgoneKm1500.setWidth(60);
        furgoneKm1500.setName(("furgoneKm1500"));
        furgoneKm1500.setPropertyEditorType(Double.class);

        Image tir1500 = new Image(AzzeroCO2Resources.INSTANCE.tir());
        tir1500.setAltText("Tir");
        NumberField tirKm1500 = new NumberField();
        tirKm1500.setWidth(60);
        tirKm1500.setName("tirKm1500");
        tirKm1500.setPropertyEditorType(Double.class);

        Image treno1500 = new Image(AzzeroCO2Resources.INSTANCE.treno());
        treno1500.setAltText("Treno");
        NumberField trenoKm1500 = new NumberField();
        trenoKm1500.setWidth(60);
        trenoKm1500.setName("trenoKm1500");
        trenoKm1500.setPropertyEditorType(Double.class);

        Image nave1500 = new Image(AzzeroCO2Resources.INSTANCE.nave());
        nave1500.setAltText("Nave");
        NumberField naveKm1500 = new NumberField();
        naveKm1500.setWidth(60);
        naveKm1500.setName("naveKm1500");
        naveKm1500.setPropertyEditorType(Double.class);

        Image aereo1500 = new Image(AzzeroCO2Resources.INSTANCE.aereo());
        aereo1500.setAltText("Aereo");
        NumberField aereoKm1500 = new NumberField();
        aereoKm1500.setWidth(60);
        aereoKm1500.setName("aereoKm1500");
        aereoKm1500.setPropertyEditorType(Double.class);

        km1500input.add(furgone1500);
        km1500input.add(furgoneKm1500, flex);
        km1500input.add(tir1500);
        km1500input.add(tirKm1500, flex);
        km1500input.add(treno1500);
        km1500input.add(trenoKm1500, flex);
        km1500input.add(nave1500);
        km1500input.add(naveKm1500, flex);

        km1500input.add(aereo1500);
        km1500input.add(aereoKm1500, flex);
        panel.add(km1500input, new FormData("100%"));

        LayoutContainer km9000 = new LayoutContainer();
        HBoxLayout layoutRigaKm9000 = new HBoxLayout();
        layoutRigaKm9000.setPadding(new Padding(2));
        layoutRigaKm9000.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.MIDDLE);
        km9000.setLayout(layoutRigaKm9000);

        km9000.add(new LabelField("Distanza percorsa: Extraeuropea"), flex);
        panel.add(km9000);
        LayoutContainer km9000input = new LayoutContainer();
        HBoxLayout layoutKm9000input = new HBoxLayout();
        layoutKm9000input.setPadding(new Padding(2));
        layoutKm9000input.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.MIDDLE);
        km9000input.setLayout(layoutKm9000input);

        /*Image furgone9000 = new Image(AzzeroCO2Resources.INSTANCE.furgone());
        furgone9000.setAltText("Furgone");
        NumberField furgoneKm9000 = new NumberField();
        furgoneKm9000.setWidth(60);
        furgoneKm9000.setName("furgoneKm9000");
        furgoneKm9000.setPropertyEditorType(Double.class);
        */

        Image tir9000 = new Image(AzzeroCO2Resources.INSTANCE.tir());
        tir9000.setAltText("Tir");
        NumberField tirKm9000 = new NumberField();
        tirKm9000.setWidth(60);
        tirKm9000.setName("tirKm9000");
        tirKm9000.setPropertyEditorType(Double.class);

        Image treno9000 = new Image(AzzeroCO2Resources.INSTANCE.treno());
        treno9000.setAltText("Treno");
        NumberField trenoKm9000 = new NumberField();
        trenoKm9000.setWidth(60);
        trenoKm9000.setName("trenoKm9000");
        trenoKm9000.setPropertyEditorType(Double.class);

        Image nave9000 = new Image(AzzeroCO2Resources.INSTANCE.nave());
        nave9000.setAltText("Nave");
        NumberField naveKm9000 = new NumberField();
        naveKm9000.setWidth(60);
        naveKm9000.setName("naveKm9000");
        naveKm9000.setPropertyEditorType(Double.class);

        Image aereo9000 = new Image(AzzeroCO2Resources.INSTANCE.aereo());
        aereo9000.setAltText("Aereo");
        NumberField aereoKm9000 = new NumberField();
        aereoKm9000.setWidth(60);
        aereoKm9000.setName("aereoKm9000");
        aereoKm9000.setPropertyEditorType(Double.class);

        //km9000input.add(furgone9000);
        //km9000input.add(furgoneKm9000, flex);
        km9000input.add(tir9000);
        km9000input.add(tirKm9000, flex);
        km9000input.add(treno9000);
        km9000input.add(trenoKm9000, flex);
        km9000input.add(nave9000);
        km9000input.add(naveKm9000, flex);

        km9000input.add(aereo9000);
        km9000input.add(aereoKm9000, flex);
        panel.add(km9000input, new FormData("100%"));

        return panel;
    }

    public void clear() {
        binding.clear();
        trasportoMerciModel= new TrasportoMerciModel();
        binding.bind(trasportoMerciModel);

    }

    public TrasportoMerciModel getTrasportoMerciModel() {
        return trasportoMerciModel;
    }

    public void setTrasportoMerciModel(TrasportoMerciModel trasportoMerciModel) {
        this.trasportoMerciModel = trasportoMerciModel;
        binding.bind(trasportoMerciModel);
    }

}

