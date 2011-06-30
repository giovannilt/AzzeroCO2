package it.agilis.mens.azzeroCO2.client.forms;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.ColumnData;
import com.extjs.gxt.ui.client.widget.layout.ColumnLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.google.gwt.user.client.Element;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 6/19/11
 * Time: 5:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class EventoFormDettaglio extends FormPanel {

   // private EventoDTO eventoDTO= new EventoDTO();

    public EventoFormDettaglio() {
		setHeaderVisible(false);
	}

    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);

        FormData formData = new FormData("100%");

        VerticalPanel vp = new VerticalPanel();
        vp.setSpacing(10);
        add(vp);

       setFrame(true);

        setHeading("Dettagli EventoPanel");
        setSize(600, -1);
        setLabelAlign(LabelAlign.TOP);
        setButtonAlign(Style.HorizontalAlignment.CENTER);

        LayoutContainer main = new LayoutContainer();
        main.setLayout(new ColumnLayout());

        LayoutContainer left = new LayoutContainer();
        left.setStyleAttribute("paddingRight", "10px");
        FormLayout layout = new FormLayout();
        layout.setLabelAlign(LabelAlign.TOP);
        left.setLayout(layout);

        TextField<String> nomeEvento = new TextField<String>();
        nomeEvento.setFieldLabel("Nome dell'evento");
        nomeEvento.setName("nome");
        left.add(nomeEvento, formData);

        DateField dataInizio = new DateField();
        dataInizio.setFieldLabel("Data inizio");
        dataInizio.setName("inizio");
        left.add(dataInizio, formData);

        TextArea note = new TextArea();
        note.setPreventScrollbars(true);
        note.setFieldLabel("Note");
        note.setName("note");
        left.add(note, formData);

        LayoutContainer right = new LayoutContainer();
        right.setStyleAttribute("paddingLeft", "10px");
        layout = new FormLayout();
        layout.setLabelAlign(LabelAlign.TOP);
        right.setLayout(layout);

        TextField<String> dove = new TextField<String>();
        dove.setFieldLabel("Dove");
        dove.setName("dove");
        right.add(dove, formData);

        DateField dataFine = new DateField();
        dataFine.setFieldLabel("Data fine");
        dataFine.setName("fine");
        right.add(dataFine, formData);

        main.add(left, new ColumnData(.5));
        main.add(right, new ColumnData(.5));

        add(main, formData);
        vp.add(this);


      /*
       FormBinding binding = new FormBinding(this);
        binding.autoBind();
        BeanModel model = BeanModelLookup.get().getFactory(
        EventoDTO.class).createModel(eventoDTO);
        binding.bind(model);*/

    }

}
