package it.agilis.mens.azzeroCO2.client.forms;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.util.Padding;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.layout.*;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import it.agilis.mens.azzeroCO2.client.AzzeroCO2Resources;
import it.agilis.mens.azzeroCO2.shared.vto.DettaglioVTO;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 6/19/11
 * Time: 5:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class FormConferma extends LayoutContainer {

    private ContentPanel east = new ContentPanel() {
        @Override
        protected void onLoad() {
            super.onLoad();
            east.getBody().setStyleAttribute("border-style", "solid");
            east.getBody().setStyleAttribute("border-top", "3px solid orange");
            east.getBody().setStyleAttribute("margin-bottom", "0");
            east.getBody().setStyleAttribute("padding-top", "1px");
            east.getBody().setStyleAttribute("border-bottom", "3px solid orange");
        }
    };
    private ContentPanel centre = new ContentPanel() {
        @Override
        protected void onLoad() {
            super.onLoad();
            centre.getBody().setStyleAttribute("border-bottom", "3px solid orange");
            centre.getBody().setStyleAttribute("border-style", "solid");
            centre.getBody().setStyleAttribute("border-top", "3px solid orange");
            centre.getBody().setStyleAttribute("margin-bottom", "0");
            centre.getHeader().setStyleAttribute("border-right-width", "15px");
        }
    };
    private String haiCompensatoText = "Hai Compensato ";
    private Text haiCompensato = new Text(haiCompensatoText);

    private String attestatoDiComensazioneText = GWT.getModuleBaseURL() + "downloadCertificato?certificato=";
    private HTML attestatoDiComensazione = new HTML();

    private String schedaProgettoText;
    private HTML schedaProgetto = new HTML();

    private final Image immagine = new Image();


    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);

        BorderLayout layout = new BorderLayout();
        setLayout(layout);
        layout.setEnableState(false);
        setStyleAttribute("padding", "0px");

        createEast();
        east.setHeading("Download");
        BorderLayoutData westData = new BorderLayoutData(Style.LayoutRegion.EAST, 250);
        //  westData.setMargins(new Margins(0));
        east.setHeight(439);
        east.setWidth(250);
        //east.setStyleAttribute("border-bottom-color","orange");
        //east.setStyleAttribute("padding-bottom","3px");
        //east.setStyleAttribute("border-bottom-width","3px");
        //east.setAutoHeight(true);
        add(east, westData);

        createCentre();
        centre.setHeading("Conferma");
        centre.setHeight(434);
        //  centre.setWidth(400);

        BorderLayoutData centerData = new BorderLayoutData(Style.LayoutRegion.CENTER);
        //  centerData.setMargins(new Margins(0));
        add(centre, centerData);

    }


    private void createEast() {
        east.setFrame(true);
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

            east.add(c);
        }
        {
            {
                LayoutContainer c = new LayoutContainer();
                HBoxLayout layout = new HBoxLayout();
                layout.setPadding(new Padding(2));
                layout.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.MIDDLE);
                c.setLayout(layout);

                Image check = new Image(AzzeroCO2Resources.INSTANCE.check());
                check.setAltText("Bus");
                c.add(check);
                c.add(attestatoDiComensazione);
                east.add(c, new FormData("100%"));
            }
            {
                LayoutContainer c = new LayoutContainer();
                HBoxLayout layout = new HBoxLayout();
                layout.setPadding(new Padding(2));
                layout.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.MIDDLE);
                c.setLayout(layout);

                Image check = new Image(AzzeroCO2Resources.INSTANCE.check());
                check.setAltText("Bus");
                c.add(check);
                c.add(schedaProgetto);
                east.add(c, new FormData("100%"));
            }

            {    // PROGETTO SCELTO
                LayoutContainer c = new LayoutContainer();
                HBoxLayout layout = new HBoxLayout();
                layout.setPadding(new Padding(2));
                layout.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.BOTTOM);
                c.setLayout(layout);

                LabelField label = new LabelField("Riceverai via email il marchio<br>di avvenuta compensazione.");
                label.setStyleAttribute("font-size", "16px");
                label.setWidth(300);
                c.add(label);

                east.add(c, new FormData("100%"));
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
                east.add(c);
            }

        }
        // east.add(panel);
    }


    private void createCentre() {
        centre.setLayout(new RowLayout(Style.Orientation.VERTICAL));


        ContentPanel row = new ContentPanel();
        row.setHeaderVisible(false);
        row.setHeight(75);
        row.setLayout(new RowLayout(Style.Orientation.HORIZONTAL));

        haiCompensato.setBorders(true);

        row.add(new Image(AzzeroCO2Resources.INSTANCE.check()), new RowData(-1, 1, new Margins(4)));
        row.add(haiCompensato, new RowData(1, 1, new Margins(4, 0, 4, 0)));

        centre.add(row, new RowData(1, -1, new Margins(4)));

        row = new ContentPanel();
        row.setHeaderVisible(false);
        row.setLayout(new RowLayout(Style.Orientation.HORIZONTAL));
        row.add(immagine, new RowData(1, 1));

        centre.add(row, new RowData(1, 1, new Margins(4, 0, 4, 0)));
    }

    public void clear() {
    }

    public void setDettaglioModel(DettaglioVTO model) {
        if (model != null && model.getPagamentoModel() != null) {
            if (model.getPagamentoModel() != null) {

                NumberFormat numberFormat= NumberFormat.getFormat("0,00");

                haiCompensatoText += numberFormat.format(model.getPagamentoModel().getKgCO2()) + " kgCO2! ";
                haiCompensato.setText(haiCompensatoText);

                attestatoDiComensazioneText += model.getPagamentoModel().getCertificatoPDF();
                attestatoDiComensazioneText = "<a target='_blank' href='" +
                        attestatoDiComensazioneText + "'><p style='padding:1px;font-family:tahoma,arial,verdana,sans-serif;color:white;'>Attestato di Compensazione</p></a>";

                attestatoDiComensazione.setHTML(attestatoDiComensazioneText);

                schedaProgettoText = GWT.getHostPageBaseURL().replace("azzeroCO2", "ImmaginiProgetti") + model.getProgettoDiCompensazioneModel().getPdfUrl();
                schedaProgettoText = "<a target='_blank' href='" + schedaProgettoText + "'><p style='padding:1px;font-family:tahoma,arial,verdana,sans-serif;color:white;'>Scheda progetto.</p></a>";
                schedaProgetto.setHTML(schedaProgettoText);

                String baseUrl = GWT.getHostPageBaseURL().replace("azzeroCO2", "ImmaginiProgetti");
                immagine.setUrl(baseUrl + model.getProgettoDiCompensazioneModel().getImageUrl());
            }
        }
    }


}

