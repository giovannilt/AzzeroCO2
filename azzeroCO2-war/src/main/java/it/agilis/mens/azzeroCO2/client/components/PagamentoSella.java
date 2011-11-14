package it.agilis.mens.azzeroCO2.client.components;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.toolbar.FillToolItem;
import it.agilis.mens.azzeroCO2.shared.model.pagamento.PagamentoModel;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 9/6/11
 * Time: 11:25 AM
 * To change this template use File | Settings | File Templates.
 */
public class PagamentoSella extends Dialog {
    private PagamentoModel model = new PagamentoModel("0.01");
    private ContentPanel panel = new ContentPanel();

    public PagamentoSella() {

        setModal(true);
        setButtonAlign(Style.HorizontalAlignment.LEFT);
        setButtons("");

        final BorderLayout layout = new BorderLayout();
        setLayout(layout);


        panel.setUrl("http://www.giovannilt.it/");
        panel.setHeaderVisible(false);
        panel.setBorders(false);
        panel.setBodyBorder(false);
        panel.setSize(400,400);

        BorderLayoutData centerData = new BorderLayoutData(Style.LayoutRegion.CENTER);
        centerData.setMargins(new Margins(0));

        add(panel, centerData);

        createButtons();
    }

    @Override
    protected void createButtons() {
        super.createButtons();
        getButtonBar().add(new FillToolItem());
        Button submit = new Button("Compensa");
        submit.addSelectionListener(new SelectionListener<ButtonEvent>() {
            public void componentSelected(ButtonEvent ce) {
                //   form.submit();
            }
        });
        addButton(submit);
    }

    public PagamentoModel getModel() {
        return model;
    }

    public void setModel(PagamentoModel model) {
        this.model = model;
        panel.setUrl(model.toString());


    }


}
