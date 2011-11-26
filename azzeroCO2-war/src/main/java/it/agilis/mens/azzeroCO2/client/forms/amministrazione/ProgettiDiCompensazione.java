package it.agilis.mens.azzeroCO2.client.forms.amministrazione;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.event.*;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.BoxComponent;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.*;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.user.client.Element;
import it.agilis.mens.azzeroCO2.client.components.uploadFiles.MultiUploadPresenter;
import it.agilis.mens.azzeroCO2.client.components.uploadFiles.MultiUploadView;
import it.agilis.mens.azzeroCO2.client.components.uploadFiles.model.FileUploadModel;
import it.agilis.mens.azzeroCO2.client.components.amministrazione.FileUploadGrid;
import it.agilis.mens.azzeroCO2.client.mvc.events.AmministrazioneEvents;
import it.agilis.mens.azzeroCO2.shared.model.amministrazione.ProgettoDiCompensazioneModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 6/19/11
 * Time: 5:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class ProgettiDiCompensazione extends LayoutContainer {

    final ListStore<ProgettoDiCompensazioneModel> store = new ListStore<ProgettoDiCompensazioneModel>();
    private static final String UPLOAD_URL = "upload";

    protected ProgettoDiCompensazioneModel createProgetto() {
        ProgettoDiCompensazioneModel progetto = new ProgettoDiCompensazioneModel();
        progetto.setNome("Nuovo Progetto");
        progetto.setAttivo(false);
        progetto.setPrezzo(0.00);
        //progetto.setKgCO2(0.00);
        return progetto;
    }


    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);

        BorderLayout layout = new BorderLayout();
        setLayout(layout);

        ContentPanel centre = createCentre();
        centre.setHeaderVisible(false);
        //   centre.setHeading("Programmi Di Compensazione");
        centre.setHeight(477);
        centre.setFrame(true);

        BorderLayoutData centerData = new BorderLayoutData(Style.LayoutRegion.CENTER);
        centerData.setMargins(new Margins(0));
        add(centre, centerData);

    }

    private ContentPanel createCentre() {
        GridCellRenderer<ProgettoDiCompensazioneModel> buttonRenderer = new GridCellRenderer<ProgettoDiCompensazioneModel>() {

            private boolean init;

            public Object render(final ProgettoDiCompensazioneModel model, String property, ColumnData config, final int rowIndex,
                                 final int colIndex, ListStore<ProgettoDiCompensazioneModel> store, Grid<ProgettoDiCompensazioneModel> grid) {
                if (!init) {
                    init = true;
                    grid.addListener(Events.ColumnResize, new Listener<GridEvent<ProgettoDiCompensazioneModel>>() {

                        public void handleEvent(GridEvent<ProgettoDiCompensazioneModel> be) {
                            for (int i = 0; i < be.getGrid().getStore().getCount(); i++) {
                                if (be.getGrid().getView().getWidget(i, be.getColIndex()) != null
                                        && be.getGrid().getView().getWidget(i, be.getColIndex()) instanceof BoxComponent) {
                                    ((BoxComponent) be.getGrid().getView().getWidget(i, be.getColIndex())).setWidth(be.getWidth() - 10);


                                }
                            }
                        }
                    });
                }
                Button b = new Button((String) model.get(property), new SelectionListener<ButtonEvent>() {
                    @Override
                    public void componentSelected(ButtonEvent ce) {
                        ListStore<FileUploadModel> fileUploadModelListStore = new ListStore<FileUploadModel>();

                        MultiUploadView view = new MultiUploadView(new FileUploadGrid(fileUploadModelListStore));
                        view.getFormPanel().setAction(UPLOAD_URL);
                        view.getFormPanel();
                        MultiUploadPresenter presenter = new MultiUploadPresenter(view, model.getId());
                        presenter.go();
                    }
                });
                b.setWidth(grid.getColumnModel().getColumnWidth(colIndex) - 10);
                b.setToolTip("Click for upload");

                return b;
            }
        };


        ContentPanel centre = new ContentPanel();

        List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

        ColumnConfig column = new ColumnConfig("nome", "Progetto", 200);
        column.setEditor(new CellEditor(new TextField<String>()));
        configs.add(column);

        column = new ColumnConfig("Upload File", 100);
        column.setRenderer(buttonRenderer);
        configs.add(column);

        column = new ColumnConfig("prezzo", "Prezzo kg/CO2", 100);
        column.setAlignment(Style.HorizontalAlignment.RIGHT);
        column.setEditor(new CellEditor(new NumberField()));
        configs.add(column);

        CheckColumnConfig columnCh = new CheckColumnConfig("attivo", "Attivo", 55);
        CellEditor checkBoxEditor = new CellEditor(new CheckBox());
        columnCh.setEditor(checkBoxEditor);
        configs.add(columnCh);

        final RowEditor<ProgettoDiCompensazioneModel> re = new RowEditor<ProgettoDiCompensazioneModel>();
        re.getMessages().setSaveText("Salva");
        re.getMessages().setCancelText("Annulla");
        re.setClicksToEdit(EditorGrid.ClicksToEdit.TWO);

        ColumnModel cm = new ColumnModel(configs);

        Grid<ProgettoDiCompensazioneModel> grid = new Grid<ProgettoDiCompensazioneModel>(store, cm);
        grid.setBorders(true);
        //    grid.setAutoHeight(true);
        grid.addPlugin(re);
        grid.setHeight(430);

        centre.add(grid);

        ToolBar toolbar = new ToolBar();
        Button add = new Button("Nuovo Progetto");
        add.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                re.stopEditing(false);
                store.insert(createProgetto(), 0);
                re.startEditing(0, true);
            }
        });

        Button saveButton = new Button("Salva");
        saveButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                /*List<CouponModel> coupons = new ArrayList<CouponModel>();
                for (Record r : store.getModifiedRecords()) {
                    coupons.add((CouponModel) r.getModel());
                }*/
                Dispatcher.forwardEvent(AmministrazioneEvents.SaveProgrammiDiCompensazione, store.getModels());

            }
        });
        centre.setButtonAlign(Style.HorizontalAlignment.CENTER);

        toolbar.add(add);
        toolbar.add(saveButton);
        centre.setTopComponent(toolbar);

        return centre;
    }

    public void setProgettiDicompensazioneInStore(List<ProgettoDiCompensazioneModel> progettiDiCompensazioneModels) {
        try {
            store.removeAll();
            store.add(progettiDiCompensazioneModels);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clear() {
    }
}


