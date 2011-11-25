package it.agilis.mens.azzeroCO2.server.fIleUpload;

public enum UploadState {

	IN_QUEUE("In queue."),
	UPLOADING("Uploading.."),
	OK("Ok."),
	ERROR("Error: ");
	
	private String label;
	
	private UploadState(String label) {
		this.label = label;
	}
	
	public String getLabel() {
		return label;
	}
	
}
