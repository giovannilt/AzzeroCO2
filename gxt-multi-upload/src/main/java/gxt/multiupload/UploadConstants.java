package gxt.multiupload;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Constants;

public interface UploadConstants extends Constants {

	public static final UploadConstants INSTANCE = GWT.create(UploadConstants.class);
	
	@DefaultStringValue("File upload")
	String dialogTitle();
	
	@DefaultStringValue("Add")
	String add();
	
	@DefaultStringValue("Remove")
	String remove();
	
	@DefaultStringValue("Upload")
	String upload();
	
	@DefaultStringValue("In queue")
    String statusQueued();

	@DefaultStringValue("Uploading..")
	String statusUploading();

	@DefaultStringValue("Ok")
	String statusOk();
	
	@DefaultStringValue("Error")
	String statusError();
}
