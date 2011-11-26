package it.agilis.mens.azzeroCO2.client.components.uploadFiles.listener;

/**
 * Listener for observing form submission.
 * 
 * @author Tomas Klempa
 *
 */
public interface FileBeforeSubmitListener {

	/**
	 * Fires before form being submit.
	 * 
	 * @param filename
	 */
	void onBeforeSubmit(String filename);
	
}
