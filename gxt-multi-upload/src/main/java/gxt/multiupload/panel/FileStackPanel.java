package gxt.multiupload.panel;

import com.google.gwt.dom.client.InputElement;
import com.google.gwt.user.client.ui.AbsolutePanel;

public class FileStackPanel extends AbsolutePanel implements FilePanelDecorator {

	UploadPanel uploadPanel = new UploadPanel(getElement());
	
	public void addFileInput(InputElement input) {
		uploadPanel.addFileInput(input);
	}
	
	public void removeFileInput(String filename) {
		uploadPanel.removeFileInput(filename);
	}

}
