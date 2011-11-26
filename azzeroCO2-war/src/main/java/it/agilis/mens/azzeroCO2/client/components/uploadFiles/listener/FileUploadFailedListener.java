package it.agilis.mens.azzeroCO2.client.components.uploadFiles.listener;

/**
 * Listener for observing failure of file upload.
 * 
 * @author Tomas Klempa
 *
 */
public interface FileUploadFailedListener {
	
	/**
	 * Fires after file upload failed.
	 * 
	 * @param filename filename of uploaded file.
	 */
	void onFileUploadFailed(String filename);
	
}
