
package it.agilis.mens.azzeroCO2.pages.events.evento.tabs;
//package com.extjs.gxt.samples.client.examples.forms;

//import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.form.*;
import com.google.gwt.user.client.Element;
//import com.extjs.gxt.samples.resources.client.Resources;
import com.extjs.gxt.ui.client.GXT;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.aria.FocusManager;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.layout.ColumnData;
import com.extjs.gxt.ui.client.widget.layout.ColumnLayout;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.google.gwt.user.client.Element;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 6/19/11
 * Time: 5:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class EventoItemDettaglio extends TabItem {

        private VerticalPanel vp;

        @Override
        protected void onRender(Element parent, int index) {
        super.onRender(parent, index);
        vp = new VerticalPanel();
        vp.setSpacing(10);
        createColumnForm();
        createTabForm();
        add(vp);
      }

      private void createTabForm() {
        FormData formData = new FormData("100%");
        FormPanel panel = new FormPanel();
        panel.setBodyStyleName("example-bg");
        panel.setPadding(0);
        panel.setFrame(false);
        panel.setHeaderVisible(false);
        panel.setBodyBorder(false);
        panel.setButtonAlign(HorizontalAlignment.CENTER);
        panel.setLayout(new FitLayout());

        final TabPanel tabs = new TabPanel();

        TabItem evento = new TabItem();
        //personal.setStyleAttribute("padding", "10px");
        //personal.setText("Personal Details");
        //personal.setLayout(new FormLayout());



        panel.setSize(340, 200);



        vp.add(panel);
      }

      private void createColumnForm() {
        FormData formData = new FormData("100%");
        FormPanel panel = new FormPanel();
        panel.setFrame(true);

        panel.setHeading("Dettagli Evento");
        panel.setSize(600, -1);
        panel.setLabelAlign(LabelAlign.TOP);
        panel.setButtonAlign(HorizontalAlignment.CENTER);

        LayoutContainer main = new LayoutContainer();
        main.setLayout(new ColumnLayout());

        LayoutContainer left = new LayoutContainer();
        left.setStyleAttribute("paddingRight", "10px");
        FormLayout layout = new FormLayout();
        layout.setLabelAlign(LabelAlign.TOP);
        left.setLayout(layout);

        TextField<String> nomeEvento = new TextField<String>();
        nomeEvento.setFieldLabel("Nome dell'evento");
        left.add(nomeEvento, formData);




        DateField dataInizio = new DateField();
        dataInizio.setFieldLabel("Data inizio");
        left.add(dataInizio, formData);


        TextArea note = new TextArea();
        note.setPreventScrollbars(true);
        note.setFieldLabel("Note");
        left.add(note, formData);

        LayoutContainer right = new LayoutContainer();
        right.setStyleAttribute("paddingLeft", "10px");
        layout = new FormLayout();
        layout.setLabelAlign(LabelAlign.TOP);
        right.setLayout(layout);


        TextField<String> dove = new TextField<String>();
        dove.setFieldLabel("Dove");
        right.add(dove, formData);


        DateField dataFine = new DateField();
        dataFine.setFieldLabel("Data fine");
        right.add(dataFine, formData);


        Radio radio1 = new Radio();
        radio1.setBoxLabel("Yes");



        main.add(left, new ColumnData(.5));
        main.add(right, new ColumnData(.5));

        panel.add(main, new FormData("100%"));


        vp.add(panel);
      }

    }
