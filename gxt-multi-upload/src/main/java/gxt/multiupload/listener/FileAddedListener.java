package gxt.multiupload.listener;

/**
 * Listener for observing file addition.
 * 
 * @author Tomas Klempa
 *
 */
public interface FileAddedListener {

	/**
	 * Fired after file added into grid model.
	 * 
	 * @param filename name of the added file.
	 */
	void onFileAdded(String filename);
	
}
