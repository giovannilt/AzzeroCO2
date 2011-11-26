package it.agilis.mens.azzeroCO2.client.components.uploadFiles;

import com.extjs.gxt.ui.client.event.*;
import com.extjs.gxt.ui.client.store.StoreEvent;
import com.extjs.gxt.ui.client.store.StoreListener;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FileUploadField;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.dom.client.Node;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.FormPanel;
import it.agilis.mens.azzeroCO2.client.components.uploadFiles.icons.UploadIcons;
import it.agilis.mens.azzeroCO2.client.components.uploadFiles.model.FileUploadModel;
import it.agilis.mens.azzeroCO2.client.components.uploadFiles.model.Model;
import it.agilis.mens.azzeroCO2.client.components.uploadFiles.panel.FilePanelDecorator;
import it.agilis.mens.azzeroCO2.client.components.uploadFiles.panel.FileStackPanel;
import it.agilis.mens.azzeroCO2.client.components.uploadFiles.panel.FormPanelDecorator;
import it.agilis.mens.azzeroCO2.client.components.uploadFiles.panel.UploadFormPanel;
import it.agilis.mens.azzeroCO2.client.components.uploadFiles.utils.MultiUploadUtils;

public class MultiUploadView extends Window implements MultiUploadPresenter.Display {

	private static final int ADD_BUTTON_WIDTH = 78;
	private UploadFormPanel formPanel;
	protected Grid<Model> grid;
	private FileUploadField addButton;
	private Button uploadButton;
	private FileStackPanel filePanel;
	private ToolBar toolBar;
	private Listener<FieldEvent> addClickListener;

  	public MultiUploadView(Grid<Model> grid) {
		setBodyBorder(true);
		setWidth(550);
		setHeight(300);
		setMinWidth(550);
		setMinHeight(300);
		setIcon(UploadIcons.INSTANCE.upload());
		setHeading(UploadConstants.INSTANCE.dialogTitle());
		setModal(true);
		setPlain(false);
		setLayout(new FitLayout());

		this.grid = grid;
		add(grid);
		addGridListeners();

		setBottomComponent(createToolbar());
		add(createFormPanel());
		add(createFilePanel());

	}
	
	public FileUploadModel parseModel(String json) {
		JSONObject jsonObject = (JSONObject) JSONParser.parseLenient(json);
		String name = getPropertyValue(jsonObject, FileUploadModel.NAME);
		String state = getPropertyValue(jsonObject, FileUploadModel.STATE);
		String message = getPropertyValue(jsonObject, FileUploadModel.MESSAGE);
		return new FileUploadModel(name, UploadState.valueOf(state), message);
	}
	
	public void exchangeAddButton() {
		FileUploadField oldAddButton = addButton;
		addButton.removeFromParent();
		toolBar.insert(createAddButton(), 0);
		filePanel.addFileInput(oldAddButton.getFileInput());
	}
	
	public void upload() {
		uploadButton.disable();
		Node firstChild = filePanel.getElement().getFirstChild();
		if (firstChild != null) {
			InputElement input = firstChild.cast();
			formPanel.addFileInput(input);
			formPanel.submit();
			updateModelState(MultiUploadUtils.removeFilePath(input.getValue()), UploadState.UPLOADING);
		}
	}
	
	public void setAccept(String accept) {
		addButton.setAccept(accept);
	}
	
	@Override
	public void addModel(Model model) {
		grid.getStore().add(model);
	}
	
	@Override
	public void removeModel(Model model) {
		grid.getStore().remove(model);
	}

	@Override
	public Model findModel(String filename) {
		return grid.getStore().findModel(FileUploadModel.NAME, filename);
	}
	
	@Override
	public Model getSelectedModel() {
		return grid.getSelectionModel().getSelectedItem();
	}

	@Override
	public void setAddButtonListener(Listener<FieldEvent> listener) {
		addClickListener = listener;
		addButton.addListener(Events.OnChange, listener);
	}

	@Override
	public String getCurrentFile() {
		return addButton.getValue();
	}
	
	@Override
	public void setUploadButtonListener(SelectionListener<ButtonEvent> listener) {
		uploadButton.addSelectionListener(listener);
	}

	@Override
	public FilePanelDecorator getFileStackPanel() {
		return filePanel;
	}

	@Override
	public FormPanelDecorator getFormPanel() {
		return formPanel;
	}

	private FormPanel createFormPanel() {
		formPanel = new UploadFormPanel();
		formPanel.setMethod(FormPanel.METHOD_POST);
		formPanel.setEncoding(FormPanel.ENCODING_MULTIPART);
		formPanel.setVisible(false);
		return formPanel;
	}

	private AbsolutePanel createFilePanel() {
		filePanel = new FileStackPanel();
		filePanel.setVisible(false);
		return filePanel;
	}

	private String getPropertyValue(JSONObject object, String property) {
		JSONString state = (JSONString) object.get(property);
		return state.stringValue();
	}

	private ToolBar createToolbar() {
		toolBar = new ToolBar();
		toolBar.setBorders(false);
		toolBar.add(createAddButton());
		toolBar.add(createUploadButton());
        return toolBar;
	}

	private FileUploadField createAddButton() {
		addButton = new FileUploadField();
		addButton.setName("file");
		addButton.setWidth(ADD_BUTTON_WIDTH);
		addButton.setButtonIcon(UploadIcons.INSTANCE.add());
		addButton.setInputStyleAttribute("display", "none");
		addButton.addListener(Events.OnChange, addClickListener);
		return addButton;
	}

	private void addGridListeners() {
		grid.getStore().addStoreListener(new StoreListener<Model>() {
			@Override
			public void storeAdd(StoreEvent<Model> se) {
				uploadButton.enable();
			}

		});
	}

	private Button createUploadButton() {
		uploadButton = new Button(UploadConstants.INSTANCE.upload());
		uploadButton.setIcon(UploadIcons.INSTANCE.upload());
		uploadButton.disable();
		return uploadButton;
	}

	private void updateModelState(String fileName, UploadState state) {
		Model model = findModel(fileName);
		model.setState(state);
		model.setMessage(state.getLabel());
	}

}
