package it.agilis.mens.azzeroCO2.client.forms.amministrazione;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.data.*;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.*;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.toolbar.PagingToolBar;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Element;
import it.agilis.mens.azzeroCO2.shared.model.amministrazione.CouponModel;
import it.agilis.mens.azzeroCO2.shared.model.amministrazione.ProgettiDiCompensazione;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 6/19/11
 * Time: 5:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class ProgrammiDiCompensazione extends LayoutContainer {

    protected ProgettiDiCompensazione createProgetto() {
        ProgettiDiCompensazione progetto = new ProgettiDiCompensazione();
        progetto.setName("Nuovo progetto");
        progetto.setAttivo(false);
        progetto.setType("Tipo");
        progetto.setKgCO2(0.00);
        return progetto;
    }


    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);

        BorderLayout layout = new BorderLayout();
        setLayout(layout);

        ContentPanel centre = createCentre();
        centre.setHeading("Programmi Di Compensazione");
        //  centre.setHeight(650);

        BorderLayoutData centerData = new BorderLayoutData(Style.LayoutRegion.CENTER);
        centerData.setMargins(new Margins(0));
        add(centre, centerData);

    }

    private ContentPanel createCentre() {
        ContentPanel centre = new ContentPanel();
        final ListStore<ProgettiDiCompensazione> store = new ListStore<ProgettiDiCompensazione>();
        {  //TODO
            store.add(new ProgettiDiCompensazione("Eolico a Vigata", "Enargia", 10.0, true));
            store.add(new ProgettiDiCompensazione("Foresta a Fela", "Vegetazione", 15.0, true));
            store.add(new ProgettiDiCompensazione("Solare a Montelusa", "Energia", 20.0, false));
        }


        // add paging support for a local collection of models
        PagingModelMemoryProxy proxy = new PagingModelMemoryProxy(CouponModel.class);

        // loader
        PagingLoader<PagingLoadResult<ModelData>> loader = new BasePagingLoader<PagingLoadResult<ModelData>>(proxy);
        loader.setRemoteSort(true);

        //ListStore<it.agilis.mens.azzeroCO2.shared.model.amministrazione.CouponModel> store = new ListStore<it.agilis.mens.azzeroCO2.shared.model.amministrazione.CouponModel>(loader);

        final PagingToolBar toolBar = new PagingToolBar(10);
        toolBar.bind(loader);

        loader.load(0, 10);


        final NumberFormat number = NumberFormat.getFormat("0.00");

        List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

        ColumnConfig column = new ColumnConfig("type", "Tipo progetto", 300);
        TextField<String> textTipoProg = new TextField<String>();
        column.setEditor(new CellEditor(textTipoProg));
        configs.add(column);

        column = new ColumnConfig("name", "Progetto", 200);
        TextField<String> textProg = new TextField<String>();
        column.setEditor(new CellEditor(textProg));
        configs.add(column);

        column = new ColumnConfig("kgCO2", "Prezzo kg/CO2", 100);
        column.setAlignment(Style.HorizontalAlignment.RIGHT);
        column.setEditor(new CellEditor(new NumberField()));
        configs.add(column);

        CheckColumnConfig columnCh = new CheckColumnConfig("attivo", "Attivo", 55);
        CellEditor checkBoxEditor = new CellEditor(new CheckBox());
        columnCh.setEditor(checkBoxEditor);
        configs.add(columnCh);

        final RowEditor<ProgettiDiCompensazione> re = new RowEditor<ProgettiDiCompensazione>();
        re.getMessages().setSaveText("Salva");
        re.getMessages().setCancelText("Annulla");
        re.setClicksToEdit(EditorGrid.ClicksToEdit.TWO);


        ColumnModel cm = new ColumnModel(configs);


        Grid<ProgettiDiCompensazione> grid = new Grid<ProgettiDiCompensazione>(store, cm);
        grid.setBorders(true);
        grid.setAutoHeight(true);
        grid.addPlugin(re);

        centre.add(grid);

        ToolBar toolbar = new ToolBar();
        Button add = new Button("Aggiungi progetto");
        add.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                ProgettiDiCompensazione prog = new ProgettiDiCompensazione("Codice", "Nuovo progetto", 0.00, true);
                re.stopEditing(false);
                store.insert(createProgetto(), 0);
                re.startEditing(store.indexOf(prog), true);


            }
        });
        centre.setButtonAlign(Style.HorizontalAlignment.CENTER);


        toolbar.add(add);
        centre.setTopComponent(toolbar);
        centre.setBottomComponent(toolBar);


        return centre;
    }

    public void clear() {
    }
}


