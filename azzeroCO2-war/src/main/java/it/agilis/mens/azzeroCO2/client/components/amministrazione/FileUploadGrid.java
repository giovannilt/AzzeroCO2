package it.agilis.mens.azzeroCO2.client.components.amministrazione;

import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.EditorGrid;
import it.agilis.mens.azzeroCO2.client.components.uploadFiles.model.FileUploadModel;
import it.agilis.mens.azzeroCO2.client.components.uploadFiles.model.Model;

import java.util.ArrayList;
import java.util.List;

public class FileUploadGrid extends EditorGrid<Model> {
	
	private static final String NAME = "Name";
	private static final String STATE = "State";
	private static final ColumnModel columnModel = createColumnsModel();
	
	public FileUploadGrid(ListStore<FileUploadModel> fileUploadModelListStore) {
		super(fileUploadModelListStore, columnModel);
		getView().setForceFit(true);
		getView().setAutoFill(true);
		getView().setShowDirtyCells(false);
		setBorders(false);
		getStore().setMonitorChanges(true);
	}

	public static ColumnModel createColumnsModel() {
		List<ColumnConfig> columns = new ArrayList<ColumnConfig>();
		columns.add(new ColumnConfig(FileUploadModel.NAME, NAME, 250));
		columns.add(new ColumnConfig(FileUploadModel.MESSAGE, STATE, 100));
		ColumnModel columnsModel = new ColumnModel(columns);
		return columnsModel;
	}

}
