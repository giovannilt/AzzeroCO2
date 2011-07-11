package it.agilis.mens.azzeroCO2.client.forms;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.binding.FormBinding;
import com.extjs.gxt.ui.client.data.BeanModel;
import com.extjs.gxt.ui.client.data.BeanModelLookup;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.util.Padding;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.layout.*;
import com.google.gwt.user.client.Element;
import it.agilis.mens.azzeroCO2.shared.model.evento.TrasportoPersoneModel;

//import com.extjs.gxt.samples.resources.client.Resources;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 6/19/11
 * Time: 5:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class EventoFormEnergia extends TabItem {

    private FormPanel panel = new FormPanel();
    private TrasportoPersoneModel trasportoPersoneModel = new TrasportoPersoneModel();
    private FormBinding binding = null;
    private BeanModel model = null;

    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);

        BorderLayout layout = new BorderLayout();
        setLayout(layout);
        layout.setEnableState(false);
        setStyleAttribute("padding", "0px");


        createColumnForm();

        BorderLayoutData centerData = new BorderLayoutData(Style.LayoutRegion.CENTER);
        centerData.setMargins(new Margins(0));
        add(panel, centerData);

    }


    private void createColumnForm() {
        panel.setFrame(true);

        panel.setHeading("Energia");
        panel.setHeight(650);
        panel.setLabelAlign(LabelAlign.LEFT);

        HBoxLayoutData flex = new HBoxLayoutData(new Margins(0, 5, 0, 0));
        LayoutContainer c2 = new LayoutContainer();
        HBoxLayout layout2 = new HBoxLayout();
        layout2.setPadding(new Padding(10));
        layout2.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.BOTTOM);
        c2.setLayout(layout2);

        c2.add(new LabelField("Inserisci nei relativi campi la qualita' di energia utilizzata per realizzare l'evento."), flex);

        panel.add(c2);

        LayoutContainer c = new LayoutContainer();
        HBoxLayout layout = new HBoxLayout();
        layout.setPadding(new Padding(10));
        layout.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.BOTTOM);
        c.setLayout(layout);

        NumberField energiaElettrica = new NumberField();
        energiaElettrica.setWidth(60);
        energiaElettrica.setName("energiaElettrica");

        LabelField label = new LabelField("Energia Elettrica ");
        label.setWidth(100);
        c.add(label);
        c.add(energiaElettrica, flex);
        c.add(new LabelField("Kw/h"), flex);
        panel.add(c, new FormData("100%"));

        LayoutContainer c3 = new LayoutContainer();
        HBoxLayout layout3 = new HBoxLayout();
        layout3.setPadding(new Padding(10));
        layout3.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.BOTTOM);
        c3.setLayout(layout3);

        c3.add(new LabelField("Non riempire in caso di energia da fonti rinnovabili."), flex);

        panel.add(c3);


        LayoutContainer cGas = new LayoutContainer();
        HBoxLayout layoutGas = new HBoxLayout();
        layoutGas.setPadding(new Padding(10));
        layoutGas.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.BOTTOM);
        cGas.setLayout(layoutGas);

        NumberField gasMetano = new NumberField();
        gasMetano.setName("gasMetano");
        gasMetano.setWidth(60);

        label = new LabelField("Gas Metano ");
        label.setWidth(100);
        cGas.add(label);
        cGas.add(gasMetano, flex);
        cGas.add(new LabelField("m<sup>3</sup>"), flex);

        panel.add(cGas, new FormData("100%"));

        LayoutContainer cGasoline = new LayoutContainer();
        HBoxLayout layoutGasoline = new HBoxLayout();
        layoutGasoline.setPadding(new Padding(10));
        layoutGasoline.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.BOTTOM);
        cGasoline.setLayout(layoutGasoline);

        NumberField gasolio = new NumberField();
        gasolio.setName("gasolio");
        gasolio.setWidth(60);

        label = new LabelField("Gasolio ");
        label.setWidth(100);
        cGasoline.add(label);
        cGasoline.add(gasolio, flex);
        cGasoline.add(new LabelField("Litri"), flex);

        panel.add(cGasoline, new FormData("100%"));

        binding = new FormBinding(panel, true);
        binding.autoBind();
      //  model = BeanModelLookup.get().getFactory(TrasportoPersoneModel.class).createModel(eventoCalcoloDTO);
        binding.bind(trasportoPersoneModel);

    }

    public void clear() {
        if (model != null) {
            model = BeanModelLookup.get().getFactory(TrasportoPersoneModel.class).createModel(new TrasportoPersoneModel());
            binding.bind(model);
        }
    }
    public void setModelObject(TrasportoPersoneModel trasportoPersoneModel){
       this.trasportoPersoneModel =trasportoPersoneModel;
    }
}

