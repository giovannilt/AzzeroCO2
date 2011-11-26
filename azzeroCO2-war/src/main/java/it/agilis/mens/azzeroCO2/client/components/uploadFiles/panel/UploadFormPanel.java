package it.agilis.mens.azzeroCO2.client.components.uploadFiles.panel;

import com.google.gwt.dom.client.InputElement;
import com.google.gwt.dom.client.Node;
import com.google.gwt.user.client.ui.FormPanel;

public class UploadFormPanel extends FormPanel implements FormPanelDecorator {

	private UploadPanel uploadPanel = new UploadPanel(getElement());
	
	public void addFileInput(InputElement input) {
		uploadPanel.addFileInput(input);
	}
	
	public void removeFileInput(String filename) {
		uploadPanel.removeFileInput(filename);
	}
	
	public String getFilename() {
		Node firstChild = getElement().getFirstChild();
		if (firstChild != null) {
			InputElement inputFile = firstChild.cast();
			return inputFile.getValue();
		} else {
			return null;
		}
	}
	
}
