package it.agilis.mens.azzeroCO2.client.forms.amministrazione;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.binding.FormBinding;
import com.extjs.gxt.ui.client.data.*;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.KeyListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.store.Record;
import com.extjs.gxt.ui.client.util.IconHelper;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.*;
import com.extjs.gxt.ui.client.widget.grid.*;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.toolbar.PagingToolBar;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import it.agilis.mens.azzeroCO2.client.services.AzzeroCO2Constants;
import it.agilis.mens.azzeroCO2.client.services.HustonServiceAsync;
import it.agilis.mens.azzeroCO2.shared.model.registrazione.UserInfoModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: serenadimaida
 * Date: 26/08/11
 * Time: 22:25
 * To change this template use File | Settings | File Templates.
 */



public class UserInfo extends LayoutContainer{
    private FormBinding binding;

protected TextField<String> userName;
    protected TextField<String> password;
    protected TextField<String> repassword;
    protected TextField<String> nome;
    protected TextField<String> cognome;
    protected TextField<String> ragioneSoc;
    protected TextField<String> indirizzo;
    protected TextField<String> citta;
    protected TextField<String> provincia;
    protected TextField<String> cap;
    protected TextField<String> telefono;
    protected TextField<String> fax;
    protected TextField<String> cellulare;
    protected TextField<String> email;
    protected TextField<String> reemail;
    protected TextField<String> partitaIvaCF;




    private ListStore<UserInfoModel> store = new ListStore<UserInfoModel>();

    public UserInfo(ListStore<UserInfoModel> store) {
        this.store=store;
    }








    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);

        BorderLayout layout = new BorderLayout();
        setLayout(layout);

        ContentPanel centre = createCentre(store);
        centre.setHeading("CouponModel");
        //  centre.setHeight(650);

        BorderLayoutData centerData = new BorderLayoutData(Style.LayoutRegion.CENTER);
        centerData.setMargins(new Margins(0));
        add(centre, centerData);


    }




       private FormPanel createForm() {
        FormPanel formPanel = new FormPanel();
        formPanel.setHeaderVisible(false);

        formPanel.setButtonAlign(Style.HorizontalAlignment.LEFT);


        KeyListener keyListener = new KeyListener() {
            public void componentKeyUp(ComponentEvent event) {
                validate();
            }

        };

        userName = new TextField<String>();
        userName.setFieldLabel("Username");
        userName.setName("userName");
        userName.setMinLength(6);
        userName.getMessages().setMinLengthText("la username deve essere di almeno 6 caratteri");

        userName.addKeyListener(keyListener);
        formPanel.add(userName);

        password = new TextField<String>();
        password.setMinLength(6);
        password.getMessages().setMinLengthText("la password deve essere di almeno 6 caratteri");
        password.setPassword(true);
        password.setFieldLabel("Password");
        password.setName("password");
        password.addKeyListener(keyListener);
        formPanel.add(password);


        repassword = new TextField<String>();
        repassword.setMinLength(6);
        repassword.getMessages().setMinLengthText("Ripeti la password");
        repassword.setPassword(true);
        repassword.setFieldLabel("Ripeti password");
        repassword.setName("repassword");
        repassword.addKeyListener(keyListener);
        formPanel.add(repassword);


        nome = new TextField<String>();
        nome.setMinLength(2);
        nome.getMessages().setMinLengthText("Nome troppo breve");

        nome.setFieldLabel("Nome");
        nome.setName("nome");
        nome.addKeyListener(keyListener);
        formPanel.add(nome);

        cognome = new TextField<String>();
        cognome.setMinLength(2);
        cognome.getMessages().setMinLengthText("Cognome troppo breve");

        cognome.setFieldLabel("Cognome");
        cognome.setName("cognome");
        cognome.addKeyListener(keyListener);
        formPanel.add(cognome);

        ragioneSoc = new TextField<String>();
        ragioneSoc.setMinLength(2);
        ragioneSoc.getMessages().setMinLengthText("Ragione sociale troppo breve");

        ragioneSoc.setFieldLabel("Rag. sociale");
        ragioneSoc.setName("ragioneSoc");
        ragioneSoc.addKeyListener(keyListener);
        formPanel.add(ragioneSoc);

        indirizzo = new TextField<String>();
        indirizzo.setMinLength(2);
        indirizzo.getMessages().setMinLengthText("Indirizzo troppo breve");

        indirizzo.setFieldLabel("Indirizzo");
        indirizzo.setName("indirizzo");
        indirizzo.addKeyListener(keyListener);
        formPanel.add(indirizzo);


        citta = new TextField<String>();
        citta.setMinLength(2);
        citta.getMessages().setMinLengthText("Nome città troppo breve");

        citta.setFieldLabel("Città");
        citta.setName("citta");
        citta.addKeyListener(keyListener);
        formPanel.add(citta);

        provincia = new TextField<String>();
        provincia.setMinLength(2);
        provincia.setMaxLength(2);
        provincia.getMessages().setMaxLengthText("Inserisci la sigla della provincia");

        provincia.setFieldLabel("Provincia");
        provincia.setName("provincia");
        provincia.addKeyListener(keyListener);
        formPanel.add(provincia);

        cap = new TextField<String>();
        cap.setMinLength(5);
        cap.setMaxLength(5);
        cap.getMessages().setMinLengthText("Il CAP deve essere di 5 cifre");
        cap.setRegex("[0-9]+");
        cap.getMessages().setRegexText("Inserisci 5 cifre");
        cap.setFieldLabel("CAP");
        cap.setName("cap");
        cap.addKeyListener(keyListener);
        formPanel.add(cap);

        partitaIvaCF = new TextField<String>();
        partitaIvaCF.setMinLength(11);
        partitaIvaCF.setMaxLength(17);
        partitaIvaCF.setName("partitaIvaCF");
        partitaIvaCF.getMessages().setMinLengthText("La partita iva deve essere lunga 11 cifre e il codice fiscale 17 caratteri");
        partitaIvaCF.setFieldLabel("P.Iva/CF");
        partitaIvaCF.addKeyListener(keyListener);
        formPanel.add(partitaIvaCF);

        telefono = new TextField<String>();
        telefono.setMinLength(2);
        telefono.getMessages().setMinLengthText("Numero troppo corto");
        telefono.setFieldLabel("Telefono");
        telefono.setName("telefono");
        telefono.addKeyListener(keyListener);
        telefono.setRegex("[0-9]+");
        telefono.getMessages().setRegexText("Solo valori numerici");
        formPanel.add(telefono);

        fax = new TextField<String>();
        fax.setMinLength(2);
        fax.getMessages().setMinLengthText("Numero fax troppo corto");
        fax.setFieldLabel("Fax");
        fax.setName("fax");
        fax.addKeyListener(keyListener);
        fax.setRegex("^[0-9]+");
        fax.getMessages().setRegexText("Solo valori numerici");
        formPanel.add(fax);

        cellulare = new TextField<String>();
        cellulare.setMinLength(2);
        cellulare.getMessages().setMinLengthText("Numero cellulare troppo breve");
        cellulare.setFieldLabel("Cellulare");
        cellulare.setName("cellulare");
        cellulare.addKeyListener(keyListener);
        cellulare.setRegex("^[0-9]+");
        cellulare.getMessages().setRegexText("Solo valori numerici");
        formPanel.add(cellulare);

        email = new TextField<String>();
        email.setMinLength(2);
        email.setFieldLabel("Email");
        email.setName("email");
        email.addKeyListener(keyListener);
        email.setRegex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
        email.getMessages().setRegexText("Email errata");
        formPanel.add(email);


        reemail = new TextField<String>();
        reemail.setMinLength(2);
        reemail.setFieldLabel("Ripeti email");
        reemail.setName("reemail");
        reemail.addKeyListener(keyListener);
        reemail.setRegex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
        reemail.getMessages().setRegexText("Ripeti emil");
        formPanel.add(reemail);



        return formPanel;
    }


    protected boolean hasValue(TextField<String> field) {
        return field.getValue() != null && field.getValue().length() > 0;
    }


    protected void validate() {
        Button saveButton = new Button("Modifica profilo");
        saveButton.setEnabled(
                hasValue(userName)
                        && hasValue(password)
                        && hasValue(partitaIvaCF)
                        && hasValue(reemail)
                        && hasValue(repassword)
                        && hasValue(citta)
                        && hasValue(email)
                        && hasValue(cap)
                        && hasValue(indirizzo)
                        && hasValue(provincia)
                        && ((hasValue(nome) && hasValue(cognome)) || hasValue(ragioneSoc))
                        && email.getValue().equals(reemail.getValue())
                        && password.getValue().equals(repassword.getValue())
        );


    }




    private ContentPanel createCentre( final ListStore<UserInfoModel> store ) {
        ContentPanel centre = new ContentPanel();


        // add paging support for a local collection of models
        PagingModelMemoryProxy proxy = new PagingModelMemoryProxy(UserInfoModel.class);

        // loader
        PagingLoader<PagingLoadResult<ModelData>> loader = new BasePagingLoader<PagingLoadResult<ModelData>>(proxy);
        loader.setRemoteSort(true);

        //ListStore<it.agilis.mens.azzeroCO2.shared.model.amministrazione.CouponModel> store = new ListStore<it.agilis.mens.azzeroCO2.shared.model.amministrazione.CouponModel>(loader);

        final PagingToolBar toolBar = new PagingToolBar(10);
        toolBar.bind(loader);

        loader.load(0, 10);


        final NumberFormat number = NumberFormat.getFormat("0.00");

        List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

        ColumnConfig column = new ColumnConfig("username", "Username", 100);
        TextField<String> textUsername = new TextField<String>();
        column.setEditor(new CellEditor(textUsername));
        configs.add(column);

        column = new ColumnConfig("password", "Password", 200);
        TextField<String> textPass = new TextField<String>();
        column.setEditor(new CellEditor(textPass));
        configs.add(column);







        //final RowEditor<UserInfoModel> re = new RowEditor<UserInfoModel>();
        //re.getMessages().setSaveText("Salva");
        //re.getMessages().setCancelText("Annulla");


        FormPanel formPanel = createForm();
        binding = new FormBinding(formPanel, true);
        //TODO binding.bind(store);

        FormLayout layout = new FormLayout();
        layout.setLabelWidth(150);
        layout.setDefaultWidth(155);
        setLayout(layout);

        setWidth(400);

        add(formPanel);












        //Button saveButton = new Button("Modifica profilo");
    /*    saveButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                List<UserInfoModel> coupons = new ArrayList<UserInfoModel>();
                for (Record r : store.getModifiedRecords()) {
                    coupons.add((UserInfoModel) r.getModel());
                }
                HustonServiceAsync hustonService = Registry.get(AzzeroCO2Constants.HUSTON_SERVICE);

                AsyncCallback<Boolean> aCallback = new AsyncCallback<Boolean>() {
                    public void onFailure(Throwable caught) {
                    }

                    @Override
                    public void onSuccess(Boolean result) {
                          Info.display("Info", "Coupons Salvati");
                    }
                };
                //hustonService.getUserInfo();
            }
        }

      */
    //);


        //centre.setButtonAlign(Style.HorizontalAlignment.CENTER);
        //grid.getAriaSupport().setDescribedBy(toolBar.getId() + "-display");

        return centre;

    }






    public void clear() {
    }

}
