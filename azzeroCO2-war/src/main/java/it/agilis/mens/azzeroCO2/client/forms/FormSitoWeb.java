package it.agilis.mens.azzeroCO2.client.forms;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.binding.FormBinding;
import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.util.Padding;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.ToolButton;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.layout.*;
import com.google.gwt.user.client.Element;
import it.agilis.mens.azzeroCO2.client.mvc.events.AzzeroCO2Events;
import it.agilis.mens.azzeroCO2.shared.model.sitoWeb.SitoWebModel;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 7/2/11
 * Time: 12:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class FormSitoWeb extends LayoutContainer {

    private SitoWebModel sitoWebModel = new SitoWebModel();
    private FormBinding binding = null;
    private FormPanel formPanel;

    public FormSitoWeb() {
        formPanel = createForm();
        binding = new FormBinding(formPanel, true);
        binding.bind(sitoWebModel);

    }

    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);

        setLayout(new RowLayout(Style.Orientation.HORIZONTAL));
        add(formPanel, new RowData(0.981, 0.95));

    }

    private FormPanel createForm() {
        FormPanel panel = new FormPanel();
        panel.setFrame(true);

        panel.setHeading("Sito web");
        panel.setLabelAlign(FormPanel.LabelAlign.LEFT);

        ToolButton tool1 = new ToolButton("x-tool-help");
        panel.getHeader().addTool(tool1);
        tool1.addSelectionListener(new SelectionListener<IconButtonEvent>() {
            @Override
            public void componentSelected(IconButtonEvent ce) {
                Dispatcher.forwardEvent(AzzeroCO2Events.ShowInfoDialog, "BLA BLA BLA");
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

        HBoxLayoutData flex = new HBoxLayoutData(new Margins(0, 5, 0, 0));

        LayoutContainer c1 = new LayoutContainer();
        HBoxLayout layout1 = new HBoxLayout();
        layout1.setPadding(new Padding(5));
        layout1.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.MIDDLE);
        c1.setLayout(layout1);

        LabelField istruzioni = new LabelField("Insirisci il numero di utenti che annualmente visitano il tuo sito web. ");
        istruzioni.setStyleAttribute("font-weight", "bolder");


        LayoutContainer visitatori = new LayoutContainer();
        HBoxLayout layoutVisitatori = new HBoxLayout();
        layoutVisitatori.setPadding(new Padding(10));
        layoutVisitatori.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.BOTTOM);
        visitatori.setLayout(layoutVisitatori);

        NumberField visitatoriField = new NumberField();
        visitatoriField.setRegex("[0-9]+");
        visitatoriField.getMessages().setRegexText("Sito web");
        visitatoriField.setName("visitatori");
        visitatoriField.setWidth(80);
        visitatoriField.setPropertyEditorType(Integer.class);

        LayoutContainer c3 = new LayoutContainer();
        HBoxLayout layout3 = new HBoxLayout();
        layout3.setPadding(new Padding(5));
        layout3.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.BOTTOM);
        c3.setLayout(layout3);

        LabelField suggerimenti = new LabelField("<br>Questo dato è facilmente reperibile dalle statistiche di traffico del sito in analisi.");
        suggerimenti.setStyleAttribute("color", "gray");
        suggerimenti.setStyleAttribute("form-variant", "normal");

        visitatori.add(new LabelField("numero di utenti"), flex);
        visitatori.add(visitatoriField);

        c1.add(istruzioni);
        panel.add(c1);

        c3.add(suggerimenti);
        panel.add(visitatori, new FormData("100%"));
        panel.add(c3, new FormData("100%"));
        return panel;
    }

    public void clear() {
        binding.clear();
        sitoWebModel = new SitoWebModel();
        binding.bind(sitoWebModel);

    }

    public SitoWebModel getSitoWebModel() {
        return sitoWebModel;
    }

    public void setSitoWebModel(SitoWebModel sitoWebModel) {
        if (sitoWebModel != null) {
            this.sitoWebModel = sitoWebModel;
            binding.bind(sitoWebModel);
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
