package it.agilis.mens.azzeroCO2.pages.events.evento;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.core.El;
import com.extjs.gxt.ui.client.core.XDOM;
import com.extjs.gxt.ui.client.data.BaseModel;
import com.extjs.gxt.ui.client.event.*;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.*;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.*;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.google.gwt.user.client.Element;
import it.agilis.mens.azzeroCO2.core.entity.Calcolo;
import it.agilis.mens.azzeroCO2.pages.events.evento.controller.EventoEvents;
import it.agilis.mens.azzeroCO2.pages.login.controller.LoginEvents;
import it.agilis.mens.azzeroCO2.pages.main.CenterPanel;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 3/15/11
 * Time: 9:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class Evento extends LayoutContainer {

    private VerticalPanel vp;
    private FormData formData;
    private CenterPanel centerPanel;

    public Evento(CenterPanel centerPanel) {
        this.centerPanel=centerPanel;

    }

    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);
        formData = new FormData("-20");
        vp = new VerticalPanel();
        vp.setSpacing(10);
        createForm();
        add(vp);
    }

    private void createForm() {

        FormPanel simple = new FormPanel();

        simple.setHeading("Custom Form");
        simple.setFrame(true);
        simple.setWidth(350);

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

        final TextField<String> firstName = new TextField<String>();
        firstName.setFieldLabel("Name");
        firstName.setAllowBlank(false);
        firstName.addPlugin(plugin);
        firstName.setData("text", "Enter your fist name");
        simple.add(firstName, formData);

        TextField<String> email = new TextField<String>();
        email.setFieldLabel("Email");
        email.setAllowBlank(false);
        email.addPlugin(plugin);
        email.setData("text", "Enter you email (required)");
        simple.add(email, formData);

        List<Stock> stocks = getStocks();
        Collections.sort(stocks, new Comparator<Stock>() {
            public int compare(Stock arg0, Stock arg1) {
                return arg0.getName().compareTo(arg1.getName());
            }
        });

        ListStore<Stock> store = new ListStore<Stock>();
        store.add(stocks);

        ComboBox<Stock> combo = new ComboBox<Stock>();
        combo.setFieldLabel("Company");
        combo.setDisplayField("name");
        combo.setTriggerAction(ComboBox.TriggerAction.ALL);
        combo.setStore(store);
        combo.addPlugin(plugin);
        combo.setData("text", "Choose the company");
        simple.add(combo, formData);

        DateField date = new DateField();
        date.setFieldLabel("Birthday");
        date.addPlugin(plugin);
        date.setData("text", "Enter your birthday");
        simple.add(date, formData);

        TimeField time = new TimeField();
        time.setFieldLabel("Time");
        time.addPlugin(plugin);
        time.setData("text", "Enter the time");
        simple.add(time, formData);

        Slider slider = new Slider();
        slider.setMinValue(40);
        slider.setMaxValue(90);
        slider.setValue(60);
        slider.setIncrement(1);
        slider.setMessage("{0} inches tall");
        slider.addPlugin(plugin);
        slider.setData("text", "Select your height");

        final SliderField sf = new SliderField(slider);
        sf.setFieldLabel("Size");
        simple.add(sf, formData);

        CheckBox check1 = new CheckBox();
        check1.setBoxLabel("Classical");

        CheckBox check2 = new CheckBox();
        check2.setBoxLabel("Rock");
        check2.setValue(true);

        CheckBox check3 = new CheckBox();
        check3.setBoxLabel("Blues");

        CheckBoxGroup checkGroup = new CheckBoxGroup();
        checkGroup.setFieldLabel("Music");
        checkGroup.add(check1);
        checkGroup.add(check2);
        checkGroup.add(check3);
        checkGroup.addPlugin(plugin);
        checkGroup.setData("text", "Select your favorite music type");
        simple.add(checkGroup, formData);

        Radio radio = new Radio();
        radio.setBoxLabel("Red");
        radio.setValue(true);

        Radio radio2 = new Radio();
        radio2.setBoxLabel("Blue");

        RadioGroup radioGroup = new RadioGroup();
        radioGroup.setFieldLabel("Favorite Color");
        radioGroup.add(radio);
        radioGroup.add(radio2);
        radioGroup.addPlugin(plugin);
        radioGroup.setData("text", "Select your favorite color");
        simple.add(radioGroup, formData);

        Radio radio3 = new Radio();
        radio3.setBoxLabel("Apple");
        radio3.setValue(true);

        Radio radio4 = new Radio();
        radio4.setBoxLabel("Banana");

        RadioGroup radioGroup2 = new RadioGroup();
        radioGroup2.setFieldLabel("Favorite Fruit");
        radioGroup2.add(radio3);
        radioGroup2.add(radio4);
        radioGroup2.addPlugin(plugin);
        radioGroup2.setData("text", "Select you favorite fruit");
        simple.add(radioGroup2, formData);

        TextArea description = new TextArea();
        description.setPreventScrollbars(true);
        description.setFieldLabel("Description");
        simple.add(description, formData);

        Button b = new Button("Submit");
        b.addSelectionListener(new SelectionListener<ButtonEvent>() {
            public void componentSelected(ButtonEvent ce) {
                AppEvent event= new AppEvent(LoginEvents.doLogin, new Calcolo());

                event.setData("calcolo1", firstName.getValue());

                Dispatcher.forwardEvent(EventoEvents.doSubmit, event);
            }
        });
        simple.addButton(b);
        simple.addButton(new Button("Cancel"));

        simple.setButtonAlign(Style.HorizontalAlignment.CENTER);

        FormButtonBinding binding = new FormButtonBinding(simple);
        binding.addButton(b);

        vp.add(simple);
    }

    private List<Stock> getStocks() {
        return Arrays.asList(new Stock("A"), new Stock("B"));
    }

    private class Stock extends BaseModel {
        public Stock(String name) {
            set("name", name);
        }

        public String getName() {
            return (String) get("name");
        }
    }
}
