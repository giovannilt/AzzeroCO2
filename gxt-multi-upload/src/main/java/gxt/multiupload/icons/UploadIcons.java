package gxt.multiupload.icons;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.ImageBundle;

@SuppressWarnings("deprecation")
public interface UploadIcons extends ImageBundle {
    
    public static final UploadIcons INSTANCE = GWT.create(UploadIcons.class);
    
    @Resource("add.png")
    public AbstractImagePrototype add();
    
    @Resource("delete.png")
    public AbstractImagePrototype delete();
    
    @Resource("page_white_get.png")
    public AbstractImagePrototype upload();
    
}
