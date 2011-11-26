package it.agilis.mens.azzeroCO2.client.components.uploadFiles;

public enum UploadState {

	IN_QUEUE(UploadConstants.INSTANCE.statusQueued()),
	UPLOADING(UploadConstants.INSTANCE.statusUploading()),
	OK(UploadConstants.INSTANCE.statusOk()),
	ERROR(UploadConstants.INSTANCE.statusError());
	
	private String label;
	
	private UploadState(String label) {
		this.label = label;
	}
	
	public String getLabel() {
		return label;
	}
}
