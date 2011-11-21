package it.agilis.mens.azzeroCO2.client.components;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.binding.FormBinding;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
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

    private FormPanel form;
    private PagamentoModel model = new PagamentoModel("0.0");
    private FormBinding binding;

    public PagamentoSella() {

        FormLayout layout = new FormLayout();
        layout.setLabelWidth(200);
        layout.setDefaultWidth(600);
        setLayout(layout);
        setModal(true);
        setButtonAlign(Style.HorizontalAlignment.LEFT);
        setButtons("");


        form = new FormPanel();
        binding = new FormBinding(form, true);
        binding.bind(model);

        add(getForm());

        createButtons();
    }

    private FormPanel getForm() {

        form.setAction("https://www.payment.fccrt.it/CheckOutEGIPSy.asp");
        form.setHeaderVisible(false);

        TextField<String> TIPO_PAGAMENTO = new TextField<String>();
        //   TIPO_PAGAMENTO.setValue("CC");
        TIPO_PAGAMENTO.setName("TIPO_PAGAMENTO");
        //       TIPO_PAGAMENTO.setVisible(false);
        form.add(TIPO_PAGAMENTO);

        TextField<String> MERCHANT_ID = new TextField<String>();   //" value="396870600001"/
        //  MERCHANT_ID.setValue("396870600001");
        MERCHANT_ID.setName("MERCHANT_ID");
        //      MERCHANT_ID.setVisible(false);
        form.add(MERCHANT_ID);

        TextField<String> ORDER_ID = new TextField<String>(); //      " value="<?=$ORDER_ID?>"/>
        ORDER_ID.setName("ORDER_ID");
        //      ORDER_ID.setVisible(false);
        form.add(ORDER_ID);

        TextField<String> IMPORTO = new TextField<String>(); //      " value="<?=$IMPORTO?>"/>
        IMPORTO.setName("IMPORTO");
        //      IMPORTO.setVisible(false);
        form.add(IMPORTO);

        TextField<String> DIVISA = new TextField<String>();
        DIVISA.setName("DIVISA");
        DIVISA.setValue("EUR");

        //   DIVISA.setVisible(false);
        form.add(DIVISA);

        TextField<String> ABI = new TextField<String>();   //    " value="03599"/>
        ABI.setValue("03599");
        ABI.setName("ABI");
        ABI.setVisible(false);
        form.add(ABI);

        TextField<String> ITEMS = new TextField<String>();  //     " value="<?=$ITEMS?>"/>
        ITEMS.setValue("....");
        ITEMS.setName("ITEMS");
        //    ITEMS.setVisible(false);
        form.add(ITEMS);

        TextField<String> URLOK = new TextField<String>();
        URLOK.setValue("....");
        URLOK.setName("URLOK");
        //    URLOK.setVisible(false);
        form.add(URLOK);

        TextField<String> URLKO = new TextField<String>();
        URLKO.setValue("....");
        URLKO.setName("URLKO");
        //    URLKO.setVisible(false);
        form.add(URLKO);

        TextField<String> URLACK = new TextField<String>();
        URLACK.setValue("....");
        URLACK.setName("URLACK");

        //    URLACK.setVisible(false);
        form.add(URLACK);

        TextField<String> MAC = new TextField<String>();   //    " value="<?=$codiceMAC?>"/>
        MAC.setValue("....");
        MAC.setName("MAC");
        //     MAC.setVisible(false);
        form.add(MAC);


        return form;
    }

    @Override
    protected void createButtons() {
        super.createButtons();
        getButtonBar().add(new FillToolItem());
        Button submit = new Button("Compensa");
        submit.addSelectionListener(new SelectionListener<ButtonEvent>() {
            public void componentSelected(ButtonEvent ce) {
                form.submit();
            }
        });
        addButton(submit);
    }

    public PagamentoModel getModel() {
        return model;
    }

    public void setModel(PagamentoModel model) {
        this.model = model;
        binding = new FormBinding(form, true);
        binding.bind(model);

    }


}
