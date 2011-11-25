package gxt.multiupload.panel;

import com.google.gwt.dom.client.InputElement;

/**
 * Adds a new behaviour to existing GWT/GXT widgets.
 * 
 * @author Tomas Klempa
 *
 */
public interface FilePanelDecorator {

	/**
	 * Adds input file element to widget.
	 * 
	 * @param input input element to add.
	 */
	public void addFileInput(InputElement input);
	
	/**
	 * Removes input file element with specified value (filename).
	 * 
	 * @param filename value of input file element to remove.
	 */
	public void removeFileInput(String filename);
	
}
