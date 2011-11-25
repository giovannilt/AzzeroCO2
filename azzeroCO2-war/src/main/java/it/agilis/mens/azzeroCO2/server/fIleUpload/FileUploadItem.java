package it.agilis.mens.azzeroCO2.server.fIleUpload;

import java.io.Serializable;

public class FileUploadItem implements Serializable {

	private String name;
	private String state;
	private String message;
	
	public FileUploadItem() {
		
	}
	
	public FileUploadItem(String name, String state, String message) {
		this.name = name;
		this.state = state;
		this.message = message;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getState() {
		return state;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	@Override
	public String toString() {
		return "FileUploadItem [name=" + name + ", state=" + state + ", message=" + message + "]";
	}
	
}
