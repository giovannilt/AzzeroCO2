package it.agilis.mens.azzeroCO2.client.forms.amministrazione;

import com.apple.laf.AquaButtonBorder;
import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.data.DataField;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.DateWrapper;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.*;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Element;
import com.extjs.gxt.ui.client.widget.form.NumberField;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 6/19/11
 * Time: 5:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class Coupon extends LayoutContainer {




    protected it.agilis.mens.azzeroCO2.shared.model.amministrazione.Coupon createCoupon() {
    it.agilis.mens.azzeroCO2.shared.model.amministrazione.Coupon coupon = new it.agilis.mens.azzeroCO2.shared.model.amministrazione.Coupon();
    coupon.setValore(0.00);
    coupon.setCodice("Codice");
    coupon.setDescrizione("nuovo coupon");
    coupon.setAttivo("Attivo");
    coupon.setDataInizio(null);
    coupon.setDataFine(null);
    coupon.setAttivo("Si");
    return coupon;
  }



    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);

        BorderLayout layout = new BorderLayout();
        setLayout(layout);

         ContentPanel centre =createCentre();
        centre.setHeading("Coupon");
      //  centre.setHeight(650);

        BorderLayoutData centerData = new BorderLayoutData(Style.LayoutRegion.CENTER);
        centerData.setMargins(new Margins(0));
        add(centre, centerData);

    }

    private ContentPanel createCentre() {
        ContentPanel centre = new ContentPanel();
        final ListStore<it.agilis.mens.azzeroCO2.shared.model.amministrazione.Coupon> store = new ListStore<it.agilis.mens.azzeroCO2.shared.model.amministrazione.Coupon>();
        {  //TODO
            store.add(new it.agilis.mens.azzeroCO2.shared.model.amministrazione.Coupon("765489000", "Sconto 10% sig. Rossi", "%",10.0,new Date() ,new Date(),"Si"));
            store.add(new it.agilis.mens.azzeroCO2.shared.model.amministrazione.Coupon("98UUGB765", "Sconto 250 euro a Cesare", "%",250.0,null,null,null));
            //store.add(new Coupon("Manifesti, pieghevoli, fogli / programma", "Energia Elettrica XX <br> Gasolio YY", 10.0));
        }

        final NumberFormat number = NumberFormat.getFormat("0.00");

        List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

        ColumnConfig column = new ColumnConfig("codice", "Codice", 100);
        TextField<String> textCodice = new TextField<String>();
        column.setEditor(new CellEditor (textCodice));
        configs.add(column);

        column = new ColumnConfig("descrizione", "Descrizione", 200);
        TextField<String> textDescr = new TextField<String>();
        column.setEditor(new CellEditor (textDescr));
        configs.add(column);

        column = new ColumnConfig("tipo", "Tipo", 70);
        TextField<String> textTipo = new TextField<String>();
        column.setEditor(new CellEditor(textTipo));
        configs.add(column);


        column = new ColumnConfig("valore", "Valore", 100);
        column.setAlignment(Style.HorizontalAlignment.RIGHT);
        column.setEditor(new CellEditor(new NumberField()));
        configs.add(column);

        DateField dateStart = new DateField();
        dateStart.getPropertyEditor().setFormat(DateTimeFormat.getFormat("dd/MM/y"));

        column = new ColumnConfig("dataInizio", "Inizio validita'", 100);
        column.setEditor(new CellEditor(dateStart));
        configs.add(column);

        DateField dateEndld = new DateField();
        dateStart.getPropertyEditor().setFormat(DateTimeFormat.getFormat("dd/MM/y"));

        column = new ColumnConfig("dataFine", "Fine Validità", 100);
        column.setEditor(new CellEditor(dateEndld));
        configs.add(column);

        column = new ColumnConfig("attivo", "Attivo", 70);
        TextField<String> textAttivo=new TextField<String>();
        column.setEditor(new CellEditor(textAttivo));
        configs.add(column);

        final RowEditor<it.agilis.mens.azzeroCO2.shared.model.amministrazione.Coupon> re = new RowEditor<it.agilis.mens.azzeroCO2.shared.model.amministrazione.Coupon>();
        re.getMessages().setSaveText("Salva");
        re.getMessages().setCancelText("Annulla");
        re.setClicksToEdit(EditorGrid.ClicksToEdit.TWO);



        ColumnModel cm = new ColumnModel(configs);






        final Grid<it.agilis.mens.azzeroCO2.shared.model.amministrazione.Coupon> grid = new Grid<it.agilis.mens.azzeroCO2.shared.model.amministrazione.Coupon>(store, cm);
        grid.setBorders(true);
        grid.setAutoHeight(true);
        grid.addPlugin(re);
        centre.add(grid);

        ToolBar toolbar = new ToolBar();
        Button add = new Button("Aggiungi coupon");
        add.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                it.agilis.mens.azzeroCO2.shared.model.amministrazione.Coupon coup = new it.agilis.mens.azzeroCO2.shared.model.amministrazione.Coupon("Codice","Nuovo coupon","",0.00,new Date(),new Date(),"");
                re.stopEditing(false);
                store.insert(createCoupon(),0);
                re.startEditing(store.indexOf(coup),true);




            }
        });
        centre.setButtonAlign(Style.HorizontalAlignment.CENTER);



        toolbar.add(add);
        centre.setBottomComponent(toolbar);





        return centre;



















    }

    public void clear() {
    }
}


