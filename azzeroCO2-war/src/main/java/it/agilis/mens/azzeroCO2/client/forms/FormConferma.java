package it.agilis.mens.azzeroCO2.client.forms;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.util.Padding;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.layout.*;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Element;
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

    private ContentPanel east = new ContentPanel();
    private ContentPanel centre = new ContentPanel();
    private String haiCompensatoText = "Hai Compensato ";
    private Text haiCompensato = new Text(haiCompensatoText);

    private String attestatoDiComensazioneText = GWT.getModuleBaseURL() + "downloadCertificato?certificato=";//+result.getPagamentoModel().getCertificatoPDF();
    private LabelField attestatoDiComensazione = new LabelField(attestatoDiComensazioneText);

    private String schedaProgettoText = GWT.getHostPageBaseURL().replace("azzeroCO2", "ImmaginiProgetti");
    private LabelField schedaProgetto = new LabelField(schedaProgettoText);

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
        BorderLayoutData westData = new BorderLayoutData(Style.LayoutRegion.EAST, 300);
        westData.setMargins(new Margins(0));
        east.setAutoHeight(true);
        add(east, westData);

        createCentre();
        centre.setHeading("Conferma");

        BorderLayoutData centerData = new BorderLayoutData(Style.LayoutRegion.CENTER);
        centerData.setMargins(new Margins(0));
        add(centre, centerData);

    }

    private void createEast() {
        FormPanel panel = new FormPanel();
        panel.setFrame(true);
        panel.setHeaderVisible(false);

        panel.setHeight(412);
        panel.setWidth(300);
        panel.setLabelAlign(FormPanel.LabelAlign.LEFT);
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

            panel.add(c);
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

                attestatoDiComensazione.setWidth(300);
                c.add(attestatoDiComensazione);
                panel.add(c, new FormData("100%"));
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

                schedaProgetto.setWidth(300);
                c.add(schedaProgetto);
                panel.add(c, new FormData("100%"));
            }

            {    // PROGETTO SCELTO
                LayoutContainer c = new LayoutContainer();
                HBoxLayout layout = new HBoxLayout();
                layout.setPadding(new Padding(2));
                layout.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.BOTTOM);
                c.setLayout(layout);

                LabelField label = new LabelField("Riceverai Via EMAIl il marchio<br>di avvenuta compensazione.");
                label.setStyleAttribute("font-size", "16px");
                label.setWidth(300);
                c.add(label);

                panel.add(c, new FormData("100%"));
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

                panel.add(c);
            }

        }
        east.add(panel);
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
        row.setHeight(335);
        row.setLayout(new RowLayout(Style.Orientation.HORIZONTAL));
        row.add(immagine, new RowData(-1, 1, new Margins(4)));

        centre.add(row, new RowData(1, 1, new Margins(4, 0, 4, 0)));
    }

    public void clear() {
    }

    public void setDettaglioModel(DettaglioVTO model) {
        if (model != null && model.getPagamentoModel() != null) {
            haiCompensatoText += model.getPagamentoModel().getKgCO2() + " kgCO2! ";
            haiCompensato.setText(haiCompensatoText);

            attestatoDiComensazioneText += model.getPagamentoModel().getCertificatoPDF();
            attestatoDiComensazioneText = "<a target=\"_blank\" href=\"" +
                    attestatoDiComensazioneText + ">Attestato di Compensazione</a>";

            attestatoDiComensazione.(attestatoDiComensazioneText);

            schedaProgettoText += "<a target=\"_blank\" href=\"" +
                    model.getProgettoDiCompensazioneModel().getPdfUrl() + ">Scheda progetto.</a>";
            schedaProgetto.setText(schedaProgettoText);

            String baseUrl = GWT.getHostPageBaseURL().replace("azzeroCO2", "ImmaginiProgetti");
            immagine.setUrl(baseUrl + model.getProgettoDiCompensazioneModel().getImageUrl());
        }
    }
}
