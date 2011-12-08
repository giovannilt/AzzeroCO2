package it.agilis.mens.azzeroCO2.client.components.uploadFiles.model;

import com.extjs.gxt.ui.client.data.ModelData;

import it.agilis.mens.azzeroCO2.client.components.uploadFiles.UploadState;

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

	void setName(String name);

	String getName();

	void setState(UploadState state);

	UploadState getState();

    Long getIdProgetto();

	void setMessage(String message);

	String getMessage();

    void setIdProgetto(Long idProgetto);

    String getIMGorPDF();

    void setIMGorPDF(String IMGorPDF);
}
