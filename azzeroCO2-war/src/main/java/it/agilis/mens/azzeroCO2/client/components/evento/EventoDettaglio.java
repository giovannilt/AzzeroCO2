package it.agilis.mens.azzeroCO2.client.components.evento;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.*;
import com.extjs.gxt.ui.client.widget.layout.*;
import com.google.gwt.user.client.Element;
import it.agilis.mens.azzeroCO2.client.forms.evento.*;
import it.agilis.mens.azzeroCO2.shared.model.evento.DettaglioModel;
import it.agilis.mens.azzeroCO2.shared.model.evento.ManifestiPieghevoliFogliModel;
import it.agilis.mens.azzeroCO2.shared.model.evento.PubblicazioniRilegateModel;
import it.agilis.mens.azzeroCO2.shared.model.evento.TrasportoPersoneModel;


/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 6/19/11
 * Time: 12:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class EventoDettaglio extends LayoutContainer {

    private final TabPanel eventoTab = new TabPanel();

    //   private BeanModelFactory beanModelFactory = BeanModelLookup.get().getFactory(EventoCategoriePersoneDTO.class);
//    private ListStore<BeanModel> storeTutteLePersone = new ListStore<BeanModel>();
    private ListStore<TrasportoPersoneModel> storeCustom = new ListStore<TrasportoPersoneModel>();
    private ListStore<PubblicazioniRilegateModel> storePubblicazioniRilegate = new ListStore<PubblicazioniRilegateModel>();
    private ListStore<ManifestiPieghevoliFogliModel> storeManifestiPieghevoliFogliModel = new ListStore<ManifestiPieghevoliFogliModel>();
    private DettaglioModel dettaglioModel = new DettaglioModel();

    private EventoFormDettaglio formDettaglio = null;

    private final EventoFormEnergia formEnergia = new EventoFormEnergia();
    private final EventoFormTrasportoPersone formTrasportoPersone = new EventoFormTrasportoPersone(storeCustom);
    private final EventoFormPernottamenti formPernottamenti = new EventoFormPernottamenti();
    private final EventoFormTrasportoMerci formTrasportoMerci = new EventoFormTrasportoMerci();
    private final EventoFormPubblicazioniRilegate formPubblicazioniRilegate = new EventoFormPubblicazioniRilegate(storePubblicazioniRilegate);
    private final EventoFormManifestiPieghevoliFogli formManifestiPiegevoliFogli = new EventoFormManifestiPieghevoliFogli(storeManifestiPieghevoliFogliModel);

    private final EventoFormRiepilogo eventoFormRiepilogo = new EventoFormRiepilogo();
    private final EventoFormAcquisto eventoFormAcquisto = new EventoFormAcquisto();
    private final EventoFormConferma eventoFormConferma = new EventoFormConferma();

    @Override
    protected void onRender(Element target, int index) {
        super.onRender(target, index);

        {
            // TODO
            TrasportoPersoneModel categorie = new TrasportoPersoneModel();
            categorie.setCategoria("Staff");
            storeCustom.add(categorie);
            categorie = new TrasportoPersoneModel();
            categorie.setCategoria("Relatori");
            storeCustom.add(categorie);

            storePubblicazioniRilegate.add(new PubblicazioniRilegateModel("Catalogo"));
            storePubblicazioniRilegate.add(new PubblicazioniRilegateModel("Bilancio"));
        }

        setLayout(new RowLayout(Style.Orientation.HORIZONTAL));

        TabItem dettaglio = new TabItem("Dettaglio");
        dettaglio.setLayout(new BorderLayout());
        formDettaglio = new EventoFormDettaglio(dettaglioModel);
        dettaglio.add(formDettaglio, new BorderLayoutData(Style.LayoutRegion.CENTER));

        eventoTab.add(dettaglio);

        TabItem calcolo = new TabItem("Calcolo");
        createCalcoloTabs();
        calcolo.add(createCalcoloTabs(), new BorderLayoutData(Style.LayoutRegion.CENTER));
        calcolo.setEnabled(false);
        eventoTab.add(calcolo);

        TabItem riepilogo = new TabItem("Riepilogo");
        riepilogo.add(eventoFormRiepilogo, new BorderLayoutData(Style.LayoutRegion.CENTER));
        riepilogo.setEnabled(false);
        eventoTab.add(riepilogo);

        TabItem acquisto = new TabItem("Acquisto");
        acquisto.add(eventoFormAcquisto, new BorderLayoutData(Style.LayoutRegion.CENTER));
        acquisto.setEnabled(false);
        eventoTab.add(acquisto);

        TabItem conferma = new TabItem("Conferma");
        conferma.add(eventoFormConferma, new BorderLayoutData(Style.LayoutRegion.CENTER));
        conferma.setEnabled(false);
        eventoTab.add(conferma);

        add(eventoTab, new RowData(1, 1));
    }


    public ContentPanel createCalcoloTabs() {
        ContentPanel calcoloCardPanel = new ContentPanel();
        calcoloCardPanel.setHeight(620);
        final CardLayout layout = new CardLayout();
        calcoloCardPanel.setLayout(layout);
        calcoloCardPanel.setHeaderVisible(false);

        formEnergia.setTitle("formEnergia");
        calcoloCardPanel.add(formEnergia, new BorderLayoutData(Style.LayoutRegion.CENTER));
        formTrasportoPersone.setTitle("formTrasportoPersone");
        calcoloCardPanel.add(formTrasportoPersone, new BorderLayoutData(Style.LayoutRegion.CENTER));
        formPernottamenti.setTitle("formPernottamenti");
        calcoloCardPanel.add(formPernottamenti, new BorderLayoutData(Style.LayoutRegion.CENTER));
        formTrasportoMerci.setTitle("formTrasportoMerci");
        calcoloCardPanel.add(formTrasportoMerci, new BorderLayoutData(Style.LayoutRegion.CENTER));
        formPubblicazioniRilegate.setTitle("formPubblicazioniRilegate");
        calcoloCardPanel.add(formPubblicazioniRilegate, new BorderLayoutData(Style.LayoutRegion.CENTER));
        formManifestiPiegevoliFogli.setTitle("formManifestiPiegevoliFogli");
        calcoloCardPanel.add(formManifestiPiegevoliFogli, new BorderLayoutData(Style.LayoutRegion.CENTER));

        return calcoloCardPanel;
    }

    public void previusTab(AppEvent event) {
        for (int i = eventoTab.getItems().size() - 1; i >= 0; i--) {
            TabItem item = eventoTab.getItems().get(i);

            if (eventoTab.getSelectedItem().getText().equalsIgnoreCase(item.getText())) {
                if (item.getText().equalsIgnoreCase("Calcolo")) {
                    ContentPanel calcolo = (ContentPanel) item.getItem(0);
                    CardLayout layout = (CardLayout) calcolo.getLayout();

                    for (int j = calcolo.getItems().size() - 1; j >= 0; j--) {
                        Component subItem = calcolo.getItems().get(j);
                        if (layout.getActiveItem().getTitle().equalsIgnoreCase(subItem.getTitle())) {
                            if (j > 0) {
                                layout.setActiveItem(calcolo.getItem(j - 1));
                                return;
                            } else {
                                item.setEnabled(false);
                                eventoTab.getItems().get(i - 1).setEnabled(true);
                                eventoTab.setSelection(eventoTab.getItems().get(i - 1));
                                return;
                            }
                        }
                    }
                } else {
                    if (i > 0) {
                        item.setEnabled(false);
                        eventoTab.getItems().get(i - 1).setEnabled(true);
                        eventoTab.setSelection(eventoTab.getItems().get(i - 1));
                        return;
                    }
                }
            }

        }
    }

    public void nextTab(AppEvent event) {
        int i = 0;
        for (TabItem item : eventoTab.getItems()) {
            i++;
            if (eventoTab.getSelectedItem().getText().equalsIgnoreCase(item.getText())) {
                if (item.getText().equalsIgnoreCase("Calcolo")) {
                    ContentPanel calcolo = (ContentPanel) item.getItem(0);
                    CardLayout layout = (CardLayout) calcolo.getLayout();

                    int j = 0;
                    for (Component subItem : calcolo.getItems()) {
                        j++;
                        if (layout.getActiveItem().getTitle().equalsIgnoreCase(subItem.getTitle())) {
                            if (j < calcolo.getItems().size()) {
                                layout.setActiveItem(calcolo.getItem(j));
                                return;
                            } else {
                                item.setEnabled(false);
                                eventoTab.getItems().get(i).setEnabled(true);
                                eventoTab.setSelection(eventoTab.getItems().get(i));
                                return;
                            }
                        }
                    }
                } else {
                    if (i < eventoTab.getItems().size()) {

                        item.setEnabled(false);
                        eventoTab.getItems().get(i).setEnabled(true);
                        eventoTab.setSelection(eventoTab.getItems().get(i));
                        return;
                    }
                }
            }

        }

    }

    public void clearPanel() {

        formDettaglio.clear();
        formEnergia.clear();
        formTrasportoPersone.clear();
        formPernottamenti.clear();
        formTrasportoMerci.clear();
        formPubblicazioniRilegate.clear();
        formManifestiPiegevoliFogli.clear();

        eventoFormRiepilogo.clear();
        eventoFormAcquisto.clear();
        eventoFormConferma.clear();

    }
}