package gxt.multiupload.model;

import com.extjs.gxt.ui.client.data.ModelData;

import gxt.multiupload.UploadState;

/**
 * This model represents item in file upload grid.
 * Every grid model implementation has to implements this interface.
 * The filename has to be unique within grid, so it is important to 
 * provide proper <code>equals()</code> method behavior.
 * 
 * @author Tomas Klempa
 *
 */
public interface Model extends ModelData {

	/**
	 * Sets the filename.
	 * 
	 * @param name filename.
	 */
	public void setName(String name);

	/**
	 * Gets the filename.
	 * 
	 * @return filename.
	 */
	public String getName();
	
	/**
	 * Sets the file state.
	 * 
	 * @param state file state.
	 */
	public void setState(UploadState state);

	/**
	 * Gets the file state.
	 * 
	 * @return file state.
	 */
	public UploadState getState();


	/**
	 * Gets id Progetto.
	 *
	 * @return id Progetto.
	 */
    public Long getIdProgetto();
	
	/**
	 * Sets the user friendly message about file state.
	 * 
	 * @param message.
	 */
	public void setMessage(String message);

	/**
	 * Gets the user friendly message about file state.
	 * 
	 * @return message.
	 */
	public String getMessage();
	
}
