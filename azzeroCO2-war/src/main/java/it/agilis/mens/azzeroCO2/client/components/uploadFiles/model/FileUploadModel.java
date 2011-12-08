package it.agilis.mens.azzeroCO2.client.components.uploadFiles.model;

import it.agilis.mens.azzeroCO2.client.components.uploadFiles.UploadState;

import com.extjs.gxt.ui.client.data.BaseModel;
import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Default implementation of upload grid's {@link Model} interface.
 *
 * @author Tomas Klempa
 */
public class FileUploadModel extends BaseModel implements IsSerializable, Model {

    public static final String NAME = "name";
    public static final String STATE = "state";
    public static final String MESSAGE = "message";

    @SuppressWarnings("unused")
    private UploadState state;

    public FileUploadModel() {

    }

    public FileUploadModel(String name) {
        setName(name);
    }

    public FileUploadModel(String name, UploadState state, String message) {
        setName(name);
        setState(state);
        setMessage(message);
    }

    public void setName(String name) {
        set(NAME, name);
    }

    public String getName() {
        return get(NAME);
    }

    public void setState(UploadState state) {
        set(STATE, state);
    }

    public UploadState getState() {
        return get(STATE);
    }

    public void setMessage(String message) {
        set(MESSAGE, message);
    }

    public String getMessage() {
        return get(MESSAGE);
    }

    public void setIdProgetto(Long idProgetto) {
        set("idProgetto", idProgetto);
    }

    public Long getIdProgetto() {
        return get("idProgetto");
    }

    public String getIMGorPDF() {
        return get("IMGorPDF");
    }

    public void setIMGorPDF(String IMGorPDF) {
        set("IMGorPDF", IMGorPDF);
    }


    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (!(other instanceof FileUploadModel)) {
            return false;
        }
        FileUploadModel otherModel = (FileUploadModel) other;
        if (!otherModel.getName().equals(getName())) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "FileUploadModel [getName()=" + getName() + ", getState()=" + getState() + " ]";
    }

}