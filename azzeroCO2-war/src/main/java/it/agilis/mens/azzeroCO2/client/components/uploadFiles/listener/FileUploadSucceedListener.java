package it.agilis.mens.azzeroCO2.client.components.uploadFiles.listener;

/**
 * Listener for observing of successful file upload.
 * 
 * @author Tomas Klempa
 *
 */
public interface FileUploadSucceedListener {

	/**
	 * Fires after file upload succeed.
	 * 
	 * @param filename filename of uploaded file.
	 */
	void onFileUploadSucceed(String filename);
	
}
