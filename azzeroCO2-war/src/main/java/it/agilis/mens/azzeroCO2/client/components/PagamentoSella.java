package it.agilis.mens.azzeroCO2.client.components;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.binding.FormBinding;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.toolbar.FillToolItem;
import com.google.gwt.user.client.Window;
import it.agilis.mens.azzeroCO2.client.PopupBlockerController;
import it.agilis.mens.azzeroCO2.client.mvc.events.*;
import it.agilis.mens.azzeroCO2.shared.Eventi;
import it.agilis.mens.azzeroCO2.shared.model.OrdineModel;
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
    private PagamentoModel pagamentoModel = new PagamentoModel("0.0");
    private FormBinding binding;
    public final Button submit;
    private OrdineModel ordineModel;


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
        binding.bind(pagamentoModel);
        setBlinkModal(true);
        setResizable(false);
        //  setOnEsc(false);
        //  setClosable(false);


        setHeight(200);
        setWidth(350);
        setHeading("Nota bene");
        setStyleAttribute("padding", "0px");
        add(getForm());
        add(getTesto());
        createButtons();

        submit = new Button("Continua");
        submit.addSelectionListener(new SelectionListener<ButtonEvent>() {
            public void componentSelected(ButtonEvent ce) {
                if (PopupBlockerController.isPopupBlocked()) {
                    Window.alert("Per procedere con il pagamento sbloccare il blocco pop-up");
                } else {
                    form.submit();
                    if (Eventi.valueOf(ordineModel.getEventiType()) == Eventi.EVENTO) {
                        Dispatcher.forwardEvent(EventoEvents.Save, ordineModel);
                        Dispatcher.forwardEvent(EventoEvents.InAttesaDiConfermaPagamento);
                    } else if (Eventi.valueOf(ordineModel.getEventiType()) == Eventi.ANNO_DI_ATTIVITA) {
                        Dispatcher.forwardEvent(UnAnnoDiAttivitaEvents.Save, ordineModel);
                        Dispatcher.forwardEvent(UnAnnoDiAttivitaEvents.InAttesaDiConfermaPagamento);
                    } else if (Eventi.valueOf(ordineModel.getEventiType()) == Eventi.WEB) {
                        Dispatcher.forwardEvent(SitoWebEvents.Save, ordineModel);
                        Dispatcher.forwardEvent(SitoWebEvents.InAttesaDiConfermaPagamento);
                    } else if (Eventi.valueOf(ordineModel.getEventiType()) == Eventi.UNA_PUBBLICAZIONE) {
                        Dispatcher.forwardEvent(PubblicazioniEvents.Save, ordineModel);
                        Dispatcher.forwardEvent(PubblicazioniEvents.InAttesaDiConfermaPagamento);
                    } else if (Eventi.valueOf(ordineModel.getEventiType()) == Eventi.CONOSCI_CO2) {
                        Dispatcher.forwardEvent(ConoscoCO2Events.Save, ordineModel);
                        Dispatcher.forwardEvent(ConoscoCO2Events.InAttesaDiConfermaPagamento);
                    }
                    submit.disable();
                }
                PopupBlockerController.detect();
            }
        });
        addButton(submit);

    }

    private FormPanel getForm() {

        form.setVisible(false);
        form.setAction("https://www.payment.fccrt.it/CheckOutEGIPSy.asp");

        form.setHeaderVisible(false);
        form.setMethod(FormPanel.Method.POST);

        TextField<String> TIPO_PAGAMENTO = new TextField<String>();
        TIPO_PAGAMENTO.setValue("CC");
        TIPO_PAGAMENTO.setName("TIPO_PAGAMENTO");
        TIPO_PAGAMENTO.setVisible(false);
        form.add(TIPO_PAGAMENTO);

        TextField<String> MERCHANT_ID = new TextField<String>();   //" value="396870600001"/
        MERCHANT_ID.setValue("396870600001");
        MERCHANT_ID.setName("MERCHANT_ID");
        MERCHANT_ID.setVisible(false);
        form.add(MERCHANT_ID);

        TextField<String> ORDER_ID = new TextField<String>(); //      " value="<?=$ORDER_ID?>"/>
        ORDER_ID.setName("ORDER_ID");
        ORDER_ID.setVisible(false);
        form.add(ORDER_ID);

        TextField<String> IMPORTO = new TextField<String>(); //      " value="<?=$IMPORTO?>"/>
        IMPORTO.setName("IMPORTO");
        IMPORTO.setVisible(false);
        form.add(IMPORTO);

        TextField<String> DIVISA = new TextField<String>();
        DIVISA.setName("DIVISA");
        DIVISA.setValue("EUR");

        DIVISA.setVisible(false);
        form.add(DIVISA);

        TextField<String> ABI = new TextField<String>();   //    " value="03599"/>
        ABI.setValue("03599");
        ABI.setName("ABI");
        ABI.setVisible(false);
        form.add(ABI);

        TextField<String> ITEMS = new TextField<String>();  //     " value="<?=$ITEMS?>"/>
        ITEMS.setValue("....");
        ITEMS.setName("ITEMS");
        ITEMS.setVisible(false);
        form.add(ITEMS);

        TextField<String> URLOK = new TextField<String>();
        URLOK.setValue("....");
        URLOK.setName("URLOK");
        URLOK.setVisible(false);
        form.add(URLOK);

        TextField<String> URLKO = new TextField<String>();
        URLKO.setValue("....");
        URLKO.setName("URLKO");
        URLKO.setVisible(false);
        form.add(URLKO);

        TextField<String> URLACK = new TextField<String>();
        URLACK.setValue("....");
        URLACK.setName("URLACK");

        URLACK.setVisible(false);
        form.add(URLACK);

        TextField<String> MAC = new TextField<String>();   //    " value="<?=$codiceMAC?>"/>
        MAC.setValue("....");
        MAC.setName("MAC");
        MAC.setVisible(false);
        form.add(MAC);


        return form;

    }

    @Override
    protected void createButtons() {
        super.createButtons();
        getButtonBar().add(new FillToolItem());

    }

    public PagamentoModel getModel() {
        return pagamentoModel;
    }

    public void setModel(OrdineModel model) {
        this.pagamentoModel = model.getPagamentoModel();
        this.ordineModel = model;
        binding = new FormBinding(form, true);
        // pagamentoModel.init();
        binding.bind(pagamentoModel);
    }

    public Text getTesto() {
        Text testo = new Text("Prima di premere conferma assicurati di aver disattivato il blocco delle popup.<br>Alla fine del processo di pagamento, sulla pagina della banca, ricordati di premere il pulsante <u><b>fine</b></u>. <br>Solo in questo modo AzzeroCO2 riceverà conferma dell'avvenuto pagamento.");
        //Text testo = new Text("Premendo il tasto procedi con il pagamento verrai reindirizzato in modo automatico alla pagina di cassa sul server sicuro (SSL) della banca con la quale operiamo.</br>In questa pagina dovranno essere inseriti i codici della tua carta di credito.</br> Tutte le informazioni inserite nella pagina di cassa vengono trasmesse in modalità sicura, cioè utilizzando un sistema di cifratura dei dati basato sul protocollo SSL, direttamente ed esclusivamente, ai server della banca.</br>A questo punto i tuoi dati verranno elaborati dalla banca, terminato il processo di pagamento PREMI IL TASTO FINE e verrai reindirizzato nuovamente sul nostro sito.</br> Se la transazione ha avuto esito positivo, verrai reindirizzato ad una pagina di conferma dell'avvenuto pagamento altrimenti, se la transazione ha avuto esito negativo (o è stata annullata), verrà visualizzato un messaggio di avviso su ciò che si è verificato.</br>L'ordine viene evaso se la transazione bancaria ha esito positivo.</br>Accertati di aver ricevuto l'email di conferma dell'avvenuta transazione da parte della banca nonché la certificazione di AzzeroCO2 e, qualora l'esito sia negativo, ti invitiamo a riprovare, rieffettuando l'ordine e controllando l'esattezza dei dati inseriti (Visa/Mastercard).</br></br>NB: RICORDATI DI PREMERE IL PULSANTE FINE SULLA PAGINA DELLA BANCA ALLA FINE DEL PROCESSO. SOLO IN QUESTO MODO AZZEROCO2 RICEVERA' CONFERMA DELL'AVVENUTO PAGAMENTO.</br>");
        testo.setStyleAttribute("padding", "5px");

        return testo;
    }

    @Override
    protected void onHide() {
        super.onHide();
    }

    public void enableButton() {
        submit.enable();
    }
}
