package it.agilis.mens.azzeroCO2.client.components.uploadFiles.utils;

public class MultiUploadUtils {

	private MultiUploadUtils() {
	}
	
	// TODO odstranit slash aj pre Linux/Unix
	public static String removeFilePath(String value) {
		String[] filePathElements = value.split("\\\\|/");
		return filePathElements[filePathElements.length - 1];
	}
}
