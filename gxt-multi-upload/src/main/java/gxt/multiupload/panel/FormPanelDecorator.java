package gxt.multiupload.panel;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteHandler;
import com.google.gwt.user.client.ui.FormPanel.SubmitHandler;

/**
 * Adds new behaviour for {@link com.google.gwt.user.client.ui.FormPanel}.
 * 
 * It also declares existing FormPanel's method for reduce of coupling between
 * {@link MultiUploadPresenter} and {@link MultiUploadView}.
 * 
 * @author Tomas Klempa
 *
 */
public interface FormPanelDecorator extends FilePanelDecorator {

	/**
	 * @see com.google.gwt.user.client.ui.FormPanel#addSubmitCompleteHandler(SubmitCompleteHandler)
	 */
	public HandlerRegistration addSubmitCompleteHandler(SubmitCompleteHandler handler);
	
	/**
	 * @see com.google.gwt.user.client.ui.FormPanel#addSubmitHandler(SubmitHandler)
	 */
	public HandlerRegistration addSubmitHandler(SubmitHandler handler);
	
	/**
	 * @see com.google.gwt.user.client.ui.FormPanel#getAction()
	 */
	public String getAction();
	
	/**
	 * @see com.google.gwt.user.client.ui.FormPanel#setAction(String)
	 */
	public void setAction(String action);
	
	/**
	 * Gets the file input element value (filename). Should returns null.
	 * 
	 * @return filename, or null, when FormPanel doesn't cotain any input file element.
	 */
	public String getFilename();
	
}
