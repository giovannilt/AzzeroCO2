package it.agilis.mens.azzeroCO2.client.components;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.util.Padding;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.layout.*;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import it.agilis.mens.azzeroCO2.client.AzzeroCO2Resources;
import it.agilis.mens.azzeroCO2.shared.model.RiepilogoModel;
import it.agilis.mens.azzeroCO2.shared.model.evento.DettaglioModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 11/30/11
 * Time: 6:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class EventoCompensatoDialog extends Dialog {

    private DateTimeFormat dateFormat = DateTimeFormat.getFormat("dd.MM.y");

    private ListStore<RiepilogoModel> store = new ListStore<RiepilogoModel>();

    private double totale = 0;

    private final NumberFormat number = NumberFormat.getFormat("0.00");
    private DettaglioModel dettaglioModel;

    private HTML schedaProgetto = new HTML();
    private String attestatoDiComensazioneText = GWT.getModuleBaseURL() + "downloadCertificato?certificato=";
    private HTML attestatoDiComensazione = new HTML();

    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);

        BorderLayout layout_C = new BorderLayout();
        setLayout(layout_C);

        ContentPanel cp = new ContentPanel();
        cp.setFrame(true);
        cp.setHeaderVisible(false);
        cp.setLayout(new RowLayout(Style.Orientation.HORIZONTAL));

        ContentPanel cpEst = new ContentPanel();
        cpEst.setFrame(false);
        cpEst.setHeaderVisible(false);
        cpEst.setLayout(new RowLayout(Style.Orientation.VERTICAL));

        cpEst.add(createGrid(), new RowData(1, 1));

        // TODO MIGLIORARE
      //  cp.setHeight(300);
        cp.add(cpEst, new RowData(0.60, 1));
        cp.add(createResumeProject(), new RowData(0.40, 1));
        this.setHeight(440);
        this.setWidth(695);

        BorderLayoutData centerData = new BorderLayoutData(Style.LayoutRegion.CENTER);
        add(cp, centerData);

    }

    private ContentPanel createResumeProject() {
        ContentPanel _return = new ContentPanel();
        _return.setFrame(false);
        _return.setHeaderVisible(false);
        _return.setLayout(new RowLayout(Style.Orientation.VERTICAL));

        ContentPanel status = new ContentPanel();
        status.setHeaderVisible(true);
        status.setHeading("Status");
        status.setFrame(false);
        status.setLayout(new RowLayout(Style.Orientation.VERTICAL));
        
        String testo= dateFormat.format(dettaglioModel.getPagamentoModel().getUpdateFromBanca()) + "</br>";
        testo+= " Hai compensato " + number.format(totale)+ " kgCO2.";
        
        Text testo1 = new Text(testo);
        
        status.add(testo1, new RowData(1,1));
        _return.add(status, new RowData(1, 0.30));
        ContentPanel download =  createDownload();

        _return.add(download, new RowData(1, 0.70));
        
        return _return;
    }

    private ContentPanel createDownload() {
        ContentPanel download= new ContentPanel();
        download.setFrame(false);
        download.setHeaderVisible(true);
        download.setHeading("Download");
        HBoxLayoutData flex = new HBoxLayoutData(new Margins(0, 5, 0, 0));

        {  // DESCRIZIONE
            LayoutContainer c = new LayoutContainer();
            HBoxLayout layout = new HBoxLayout();
            layout.setPadding(new Padding(2));
            layout.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.BOTTOM);
            c.setLayout(layout);
            LabelField label = new LabelField("Scarica i materiali di comunicazione <br>relativi al tuo acquisto:");
            label.setStyleAttribute("font-size", "16px");
            c.add(label, flex);

            download.add(c);
        }
        {
            {
                LayoutContainer c = new LayoutContainer();
                HBoxLayout layout = new HBoxLayout();
            //    layout.setPadding(new Padding(2));
                layout.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.MIDDLE);
                c.setLayout(layout);

                Image check = new Image(AzzeroCO2Resources.INSTANCE.check());
                c.add(check);
                c.add(attestatoDiComensazione);
                download.add(c, new FormData("100%"));
            }
            {
                LayoutContainer c = new LayoutContainer();
                HBoxLayout layout = new HBoxLayout();
                layout.setPadding(new Padding(2));
                layout.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.MIDDLE);
                c.setLayout(layout);

                Image check = new Image(AzzeroCO2Resources.INSTANCE.check());
                c.add(check);
                c.add(schedaProgetto);
                download.add(c, new FormData("100%"));
            }
            {
                LayoutContainer c = new LayoutContainer();
                HBoxLayout layout = new HBoxLayout();
                layout.setPadding(new Padding(2));
                layout.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.MIDDLE);
                c.setLayout(layout);

                Image azzeroCO2Stemp = new Image(AzzeroCO2Resources.INSTANCE.azzeroCO2Stemp());
                azzeroCO2Stemp.setAltText("AzzeroCO2");
                c.add(azzeroCO2Stemp);
                download.add(c);
            }

        }
        return download;
    }
    

    private Grid<RiepilogoModel> createGrid() {
        List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

        ColumnConfig column = new ColumnConfig("oggetto", "Oggetto", 137);
        configs.add(column);

        column = new ColumnConfig("dettagli", "Dettagli", 260);
        configs.add(column);

        ColumnModel cm = new ColumnModel(configs);
        Grid<RiepilogoModel> grid = new Grid<RiepilogoModel>(store, cm);
        grid.setBorders(true);

        return grid;
    }

    public void setInStore(List<RiepilogoModel> models) {
        this.store.removeAll();
        this.store.add(models);
    }

    public void setTotale(Double totale) {
        this.totale= totale;

    }

    @Override
    protected void createButtons() {
      //  super.createButtons();    //To change body of overridden methods use File | Settings | File Templates.
    }

    public void setDettaglioModel(DettaglioModel dettaglioModel) {
        this.dettaglioModel=dettaglioModel;

        if (dettaglioModel != null && dettaglioModel.getPagamentoModel() != null) {
            if (dettaglioModel.getPagamentoModel() != null) {

                NumberFormat numberFormat = NumberFormat.getFormat("0,00");

                attestatoDiComensazioneText += dettaglioModel.getPagamentoModel().getCertificatoPDF();
                attestatoDiComensazioneText = "<a target='_blank' href='" +
                        attestatoDiComensazioneText + "'><p style='padding:1px;font-family:arial;color:white;'>Attestato di compensazione</p></a>";

                attestatoDiComensazione.setHTML(attestatoDiComensazioneText);

                String schedaProgettoText = GWT.getHostPageBaseURL().replace("azzeroCO2", "ImmaginiProgetti") + dettaglioModel.getProgettoDiCompensazioneModel().getPdfUrl();
                schedaProgettoText = "<a target='_blank' href='" + schedaProgettoText + "'><p style='padding:1px;font-family:arial;color:white;'>Scheda progetto</p></a>";
                schedaProgetto.setHTML(schedaProgettoText);

            }
        }
    }
}
