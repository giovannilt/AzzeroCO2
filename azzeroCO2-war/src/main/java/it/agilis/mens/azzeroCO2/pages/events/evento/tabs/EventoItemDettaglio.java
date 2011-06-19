package it.agilis.mens.azzeroCO2.pages.events.evento.tabs;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.core.El;
import com.extjs.gxt.ui.client.core.XDOM;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.ComponentPlugin;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.button.ToolButton;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.google.gwt.user.client.Element;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 6/19/11
 * Time: 5:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class EventoItemDettaglio extends TabItem {
    //  private VerticalPanel vp;
    private FormData formData;


    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);

        FormPanel formPanel = new FormPanel();
        formPanel.setHeading("Dettagli Evento");


        ToolButton clear = new ToolButton("x-tool-refresh");
        ToolButton help = new ToolButton("x-tool-help");

        formPanel.getHeader().addTool(help);
        formPanel.getHeader().addTool(clear);


        add(formPanel);

        formData = new FormData("-20");

        ComponentPlugin plugin = new ComponentPlugin() {
            public void init(Component component) {
                component.addListener(Events.Render, new Listener<ComponentEvent>() {
                    public void handleEvent(ComponentEvent be) {
                        El elem = be.getComponent().el().findParent(".x-form-element", 3);
                        // should style in external CSS  rather than directly
                        elem.appendChild(XDOM.create("<div style='color: #615f5f;padding: 1 0 2 0px;'>" + be.getComponent().getData("text") + "</div>"));
                    }
                });
            }
        };

        TextField<String> nome = new TextField<String>();
        nome.setFieldLabel("Nome Evento");
        nome.setAllowBlank(false);
        nome.addPlugin(plugin);
        nome.setData("text", "Digita nome evento");
        formPanel.add(nome, formData);

        DateField da = new DateField();
        da.setFieldLabel("da");
        da.addPlugin(plugin);
        da.setData("text", "Digita Da.");
        formPanel.add(da, formData);

        DateField a = new DateField();
        a.setFieldLabel("a");
        a.addPlugin(plugin);
        a.setData("text", "Digita a.");
        formPanel.add(a, formData);

        TextField<String> dove = new TextField<String>();
        dove.setFieldLabel("Dove");
        dove.setAllowBlank(false);
        dove.addPlugin(plugin);
        dove.setData("text", "Digita Dove.");
        formPanel.add(dove, formData);

        TextArea note = new TextArea();
        note.setPreventScrollbars(true);
        note.setFieldLabel("Note");
        formPanel.add(note, formData);

        formPanel.setButtonAlign(Style.HorizontalAlignment.CENTER);

    }

}
