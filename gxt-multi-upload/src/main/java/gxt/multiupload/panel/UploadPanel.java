package gxt.multiupload.panel;

import gxt.multiupload.utils.MultiUploadUtils;

import com.google.gwt.dom.client.InputElement;
import com.google.gwt.user.client.Element;

public class UploadPanel {

	private Element element;

	public UploadPanel(Element element) {
		this.element = element;
	}
	
	public void addFileInput(InputElement input) {
		element.appendChild(input);
	}
	
	public void removeFileInput(String filename) {
		InputElement elementToRemove = findFileInput(filename);
		element.removeChild(elementToRemove);
	}
	
	public InputElement findFileInput(String filename) {
		InputElement elementToFind = null;
		Element formElement = element;
		for (int i = 0; i < formElement.getChildNodes().getLength(); i++) {
			InputElement inputElement = formElement.getChild(i).cast();
			String fileName = MultiUploadUtils.removeFilePath(inputElement.getValue());
			if (fileName.equals(filename)) {
				elementToFind = inputElement;
				break;
			}
		}
		return elementToFind;
	}
}
