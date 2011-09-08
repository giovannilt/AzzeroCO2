package it.agilis.mens.azzeroCO2.client.components;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.layout.RowData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;


/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 9/8/11
 * Time: 12:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class InfoDialog extends Dialog {

    private Text text = new Text();

    public InfoDialog() {

        setLayout(new RowLayout(Style.Orientation.VERTICAL));
        setSize(350, 220);

        setHeading("Info");
        setModal(true);
        setButtons("");
        setBodyBorder(true);
        setBodyStyle("padding: 8px;background: none");
        setResizable(false);

        add(text, new RowData(1, 1, new Margins(0, 4, 0, 4)));

    }

    public void setInfo(String info) {
        text.setText(info);
    }
}
