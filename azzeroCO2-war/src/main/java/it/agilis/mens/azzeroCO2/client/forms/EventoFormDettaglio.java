package it.agilis.mens.azzeroCO2.client.forms;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.binding.FormBinding;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.*;
import com.google.gwt.user.client.Element;
import it.agilis.mens.azzeroCO2.shared.model.evento.DettaglioModel;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 6/19/11
 * Time: 5:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class EventoFormDettaglio extends LayoutContainer {

    private DettaglioModel dettaglioModel = new DettaglioModel();
    private FormBinding binding = null;

    public EventoFormDettaglio(DettaglioModel dettaglioModel) {
        this.dettaglioModel = dettaglioModel;
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

        FormPanel formPanel = createForm();
        cp.add(formPanel, new RowData(1, 1));

        binding = new FormBinding(formPanel, true);
        binding.bind(dettaglioModel);

        BorderLayoutData centerData = new BorderLayoutData(Style.LayoutRegion.CENTER);
        add(cp, centerData);
    }


    private FormPanel createForm() {
        FormPanel panel = new FormPanel();

        panel.setFrame(true);
        panel.setHeading("Dettagli EventoPanel");

        panel.setButtonAlign(Style.HorizontalAlignment.CENTER);
        panel.setLayout(new ColumnLayout());

        LayoutContainer left = new LayoutContainer();
        left.setStyleAttribute("paddingRight", "10px");
        FormLayout layout = new FormLayout();
        layout.setLabelAlign(FormPanel.LabelAlign.LEFT);
        left.setLayout(layout);

        TextField<String> nomeEvento = new TextField<String>();
        nomeEvento.setFieldLabel("Nome");
        nomeEvento.setName("nome");
        left.add(nomeEvento);

        DateField dataInizio = new DateField();
        dataInizio.setFieldLabel("Data inizio");
        dataInizio.setName("inizio");
        left.add(dataInizio);

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
        dataFine.setName("fine");
        right.add(dataFine);

        panel.add(left, new ColumnData(.5));
        panel.add(right, new ColumnData(.5));

        return panel;
    }



    public void clear() {
        binding.unbind();
    }
}
