package gxt.multiupload.listener;

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
