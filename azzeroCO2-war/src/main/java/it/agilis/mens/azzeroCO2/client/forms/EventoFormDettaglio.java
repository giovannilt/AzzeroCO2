package it.agilis.mens.azzeroCO2.client.forms;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.binding.FormBinding;
import com.extjs.gxt.ui.client.data.BeanModel;
import com.extjs.gxt.ui.client.data.BeanModelLookup;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.*;
import com.google.gwt.user.client.Element;
import it.agilis.mens.azzeroCO2.shared.model.EventoDettaglioDTO;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 6/19/11
 * Time: 5:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class EventoFormDettaglio extends TabItem {

    private EventoDettaglioDTO eventoDettaglioDTO = new EventoDettaglioDTO();
    private FormBinding binding = null;
    private BeanModel model = null;

    public EventoFormDettaglio(EventoDettaglioDTO eventoDettaglioDTO) {
        this.eventoDettaglioDTO = eventoDettaglioDTO;
    }

    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);
        // VerticalPanel vp = new VerticalPanel();
        //  vp.setSpacing(10);
        //  add(vp);
        {
            FormData formData = new FormData("100%");
            FormPanel panel = new FormPanel();
            // vp.add(panel);
            // add(panel);
            {
                panel.setFrame(true);

                panel.setHeading("Dettagli EventoPanel");
                panel.setLabelAlign(LabelAlign.TOP);
                panel.setButtonAlign(HorizontalAlignment.CENTER);
                panel.setHeight(630);
                setLayout(new BorderLayout());
                BorderLayoutData centerData = new BorderLayoutData(Style.LayoutRegion.CENTER);
                centerData.setMargins(new Margins(0));
                add(panel, centerData);

                LayoutContainer main = new LayoutContainer();
                panel.add(main, new FormData("100%"));
                {
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

                }
            }

            binding = new FormBinding(panel, true);
            binding.autoBind();
            model = BeanModelLookup.get().getFactory(EventoDettaglioDTO.class).createModel(eventoDettaglioDTO);
            binding.bind(model);
        }

    }

    public void clear() {
        eventoDettaglioDTO = new EventoDettaglioDTO();
        model = BeanModelLookup.get().getFactory(EventoDettaglioDTO.class).createModel(eventoDettaglioDTO);
        binding.bind(model);
    }
}
