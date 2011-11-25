package it.agilis.mens.azzeroCO2.server.fIleUpload;


public class FileExceedsLimitException extends Exception {
	
	public FileExceedsLimitException() {
		super();
	}
	
	public FileExceedsLimitException(String message) {
		super(message);
	}
}
