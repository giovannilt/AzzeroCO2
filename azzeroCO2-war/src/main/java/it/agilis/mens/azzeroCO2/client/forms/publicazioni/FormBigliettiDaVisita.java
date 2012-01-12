package it.agilis.mens.azzeroCO2.client.forms.publicazioni;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.binding.FormBinding;
import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.util.Padding;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.ToolButton;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.layout.*;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.user.client.Element;
import it.agilis.mens.azzeroCO2.client.mvc.events.AzzeroCO2Events;
import it.agilis.mens.azzeroCO2.shared.model.evento.TipoDiCartaModel;
import it.agilis.mens.azzeroCO2.shared.model.unaPubblicazione.BigliettiDaVisitaModel;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 7/2/11
 * Time: 12:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class FormBigliettiDaVisita extends LayoutContainer {
    private BigliettiDaVisitaModel bigliettiDaVisitaModel = new BigliettiDaVisitaModel();
    private ToolBar toolBar = new ToolBar();
    private ListStore<TipoDiCartaModel> tipoDiCartaModelListStore = new ListStore<TipoDiCartaModel>();
    private final FormPanel panel;
    private final FormBinding binding;

    //private ContentPanel cpEst = new ContentPanel();
    public FormBigliettiDaVisita() {
        panel = createGroupForm();
        binding = new FormBinding(panel, true);
        binding.bind(bigliettiDaVisitaModel);
    }

    @SuppressWarnings("rawtypes")
    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);
        setLayout(new RowLayout(Style.Orientation.HORIZONTAL));
        panel.setHeading("Biglietti da visita e cartelline");
        ToolButton tool1 = new ToolButton("x-tool-help");
        panel.getHeader().addTool(tool1);
        tool1.addSelectionListener(new SelectionListener<IconButtonEvent>() {
            @Override
            public void componentSelected(IconButtonEvent ce) {
                Dispatcher.forwardEvent(AzzeroCO2Events.ShowInfoDialog, "Si fa riferimento alle emissioni relative al ciclo di produzione di un grammo di carta.");
            }
        });
        ToolButton tool = new ToolButton("x-tool-refresh");
        panel.getHeader().addTool(tool);
        tool.addSelectionListener(new SelectionListener<IconButtonEvent>() {
            @Override
            public void componentSelected(IconButtonEvent ce) {
                //clear(true);
            }
        });

        add(panel, new RowData(1, .97));
    }

    @Override
    protected void onLoad() {
        super.onLoad();
        toolBar.setStyleAttribute("border-bottom", "3px solid orange");
        if (panel != null && panel.getBody() != null) {
            panel.getBody().setStyleAttribute("border-bottom", "3px solid orange");
            panel.getBody().setStyleAttribute("border-style", "solid");
            panel.getBody().setStyleAttribute("border-top", "3px solid orange");
            panel.getBody().setStyleAttribute("border-width", "3px 0");
            panel.getBody().setStyleAttribute("margin-bottom", "0");
        }
    }

    private FormPanel createGroupForm() {
        FormPanel panel = new FormPanel();
        panel.setFrame(true);

        panel.setLabelAlign(FormPanel.LabelAlign.LEFT);
        HBoxLayoutData flex = new HBoxLayoutData(new Margins(0, 5, 0, 0));

        {
            LayoutContainer c2 = new LayoutContainer();
            HBoxLayout layout2 = new HBoxLayout();
            layout2.setPadding(new Padding(10));
            layout2.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.BOTTOM);
            c2.setLayout(layout2);

            LabelField istruzioni = new LabelField("Inserisci le caratteristiche.");
            istruzioni.setStyleAttribute("font-weight", "bolder");
            c2.add(istruzioni, flex);

            panel.add(c2);
        }
        { // Formato Aperto
            {
                LayoutContainer c = new LayoutContainer();
                HBoxLayout layout = new HBoxLayout();
                layout.setPadding(new Padding(10));
                layout.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.BOTTOM);
                c.setLayout(layout);

                NumberField tiraturaBiglietti = new NumberField();
                tiraturaBiglietti.setWidth(60);
                tiraturaBiglietti.setName("tiraturaBiglietti");
                tiraturaBiglietti.setRegex("[0-9]+");
                tiraturaBiglietti.getMessages().setRegexText("Inserisci un numero intero");
                tiraturaBiglietti.setPropertyEditorType(Integer.class);


                LabelField label = new LabelField("Biglietti da visita ");
                label.setWidth(100);
                c.add(label);
                c.add(tiraturaBiglietti, flex);
                c.add(new LabelField("Tiratura"), flex);


                panel.add(c, new FormData("100%"));
            }
        }
        {   // Materiale
            {
                LayoutContainer c = new LayoutContainer();
                HBoxLayout layout = new HBoxLayout();
                layout.setPadding(new Padding(10));
                layout.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.BOTTOM);
                c.setLayout(layout);

                ComboBox<TipoDiCartaModel> tipoDiCarta = new ComboBox<TipoDiCartaModel>();
                tipoDiCarta.setEmptyText("Seleziona tipo di carta");
                // tipoDiCarta.setToolTip("TipoDiCarta");
                tipoDiCarta.setDisplayField("parametro");
                tipoDiCarta.setWidth(200);
                tipoDiCarta.setDisplayField("nome");
                tipoDiCarta.setName("tipoDiCartaBiglietti");
                tipoDiCarta.setTriggerAction(ComboBox.TriggerAction.ALL);
                tipoDiCarta.setStore(tipoDiCartaModelListStore);

                LabelField label = new LabelField(" ");//("Materiale ");
                label.setWidth(100);
                c.add(label);
                c.add(tipoDiCarta, flex);

                panel.add(c, new FormData("100%"));
            }

            {
                LayoutContainer c = new LayoutContainer();
                HBoxLayout layout = new HBoxLayout();
                layout.setPadding(new Padding(10));
                layout.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.BOTTOM);
                c.setLayout(layout);

                NumberField grammatura = new NumberField();
                grammatura.setWidth(60);
                grammatura.setName("grammaturaBiglietti");

                LabelField label = new LabelField("");
                label.setWidth(100);
                c.add(label);
                c.add(grammatura, flex);
                c.add(new LabelField("Grammatura"), flex);

                panel.add(c, new FormData("100%"));
            }
        }

        { // Tiratura
            {
                LayoutContainer c = new LayoutContainer();
                HBoxLayout layout = new HBoxLayout();
                layout.setPadding(new Padding(10));
                layout.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.BOTTOM);
                c.setLayout(layout);

                NumberField tiratura = new NumberField();
                tiratura.setWidth(60);
                tiratura.setName("tiraturaCartelline");
                tiratura.setRegex("[0-9]+");
                tiratura.getMessages().setRegexText("Inserisci un numero intero");
                tiratura.setPropertyEditorType(Integer.class);

                LabelField label = new LabelField("Cartelline ");
                label.setWidth(100);
                c.add(label);
                c.add(tiratura, flex);
                c.add(new LabelField("Tiratura"), flex);
                c.setStyleAttribute("padding-top", "50px");
                panel.add(c, new FormData("100%"));
            }
            {
                LayoutContainer c = new LayoutContainer();
                HBoxLayout layout = new HBoxLayout();
                layout.setPadding(new Padding(10));
                layout.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.BOTTOM);
                c.setLayout(layout);

                ComboBox<TipoDiCartaModel> tipoDiCarta = new ComboBox<TipoDiCartaModel>();
                tipoDiCarta.setEmptyText("Seleziona tipo di carta");
                //  tipoDiCarta.setToolTip("TipoDiCarta");
                tipoDiCarta.setDisplayField("parametro");
                tipoDiCarta.setWidth(200);
                tipoDiCarta.setDisplayField("nome");
                tipoDiCarta.setName("tipoDiCartaCartelline");
                tipoDiCarta.setTriggerAction(ComboBox.TriggerAction.ALL);
                tipoDiCarta.setStore(tipoDiCartaModelListStore);

                LabelField label = new LabelField(" ");//("Materiale ");
                label.setWidth(100);
                c.add(label);
                c.add(tipoDiCarta, flex);

                panel.add(c, new FormData("100%"));
            }

            {
                LayoutContainer c = new LayoutContainer();
                HBoxLayout layout = new HBoxLayout();
                layout.setPadding(new Padding(10));
                layout.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.BOTTOM);
                c.setLayout(layout);

                NumberField grammatura = new NumberField();
                grammatura.setWidth(60);
                grammatura.setName("grammaturaCartelline");

                LabelField label = new LabelField("");
                label.setWidth(100);
                c.add(label);
                c.add(grammatura, flex);
                c.add(new LabelField("Grammatura"), flex);

                panel.add(c, new FormData("100%"));
            }
        }
        return panel;
    }

    public BigliettiDaVisitaModel getBigliettiDaVisitaModel() {
        return bigliettiDaVisitaModel;
    }

    public void setBigliettiDaVisitaModel(BigliettiDaVisitaModel bigliettiDaVisitaModel) {
        this.bigliettiDaVisitaModel = bigliettiDaVisitaModel;
        binding.bind(bigliettiDaVisitaModel);
    }

    public void setTipoDiCartaModel(List<TipoDiCartaModel> tipoDiCarta) {
        for(TipoDiCartaModel t: tipoDiCarta){
               if(t.getNome().equalsIgnoreCase("Carta da giornale") || t.getNome().equalsIgnoreCase("Carta da rivista")){
                   tipoDiCarta.remove(t);
               }
        }
        tipoDiCartaModelListStore.add(tipoDiCarta);
        
    }

    public void clear() {
        binding.clear();
        bigliettiDaVisitaModel = new BigliettiDaVisitaModel();
        binding.bind(bigliettiDaVisitaModel);
    }
}