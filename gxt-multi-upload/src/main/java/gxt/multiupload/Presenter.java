package gxt.multiupload;

/**
 * Presenter interface within MVP pattern.<br>
 * <a href="http://code.google.com/intl/sk/webtoolkit/articles/mvp-architecture.html">More</a>
 * info about MVP pattern.
 * 
 * @author Tomas Klempa
 *
 */
public interface Presenter {
	
	/**
	 * Binds Presenter on View and shows the dialog.
	 */
	public void go();
	
}
