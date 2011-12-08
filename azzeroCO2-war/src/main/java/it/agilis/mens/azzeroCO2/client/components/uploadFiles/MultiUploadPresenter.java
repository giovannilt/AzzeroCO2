package it.agilis.mens.azzeroCO2.client.components.uploadFiles;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.FieldEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteHandler;
import com.google.gwt.user.client.ui.FormPanel.SubmitEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitHandler;
import it.agilis.mens.azzeroCO2.client.components.uploadFiles.listener.FileAddedListener;
import it.agilis.mens.azzeroCO2.client.components.uploadFiles.listener.FileBeforeSubmitListener;
import it.agilis.mens.azzeroCO2.client.components.uploadFiles.listener.FileUploadFailedListener;
import it.agilis.mens.azzeroCO2.client.components.uploadFiles.listener.FileUploadSucceedListener;
import it.agilis.mens.azzeroCO2.client.components.uploadFiles.model.FileUploadModel;
import it.agilis.mens.azzeroCO2.client.components.uploadFiles.model.Model;
import it.agilis.mens.azzeroCO2.client.components.uploadFiles.panel.FilePanelDecorator;
import it.agilis.mens.azzeroCO2.client.components.uploadFiles.panel.FormPanelDecorator;
import it.agilis.mens.azzeroCO2.client.components.uploadFiles.utils.MultiUploadUtils;
import it.agilis.mens.azzeroCO2.client.mvc.events.AmministrazioneEvents;

import java.util.ArrayList;
import java.util.List;

public class MultiUploadPresenter implements Presenter {

    protected final Display display;
    private List<FileBeforeSubmitListener> fileBeforeSubmitListeners = new ArrayList<FileBeforeSubmitListener>();
    private List<FileUploadSucceedListener> fileUploadSucceedListeners = new ArrayList<FileUploadSucceedListener>();
    private List<FileUploadFailedListener> fileUploadFailedListeners = new ArrayList<FileUploadFailedListener>();
    private List<FileAddedListener> fileAddedListeners = new ArrayList<FileAddedListener>();

    private Long idProgetto;
    private String IMGorPDF;

    public interface Display {
        public void setAddButtonListener(Listener<FieldEvent> listener);

        public void setUploadButtonListener(SelectionListener<ButtonEvent> listener);

        public void exchangeAddButton();

        public String getCurrentFile();

        public void addModel(Model model);

        public void removeModel(Model model);

        public Model findModel(String filename);

        public Model parseModel(String json);

        public Model getSelectedModel();

        public FormPanelDecorator getFormPanel();

        public FilePanelDecorator getFileStackPanel();

        public void upload();

        public void show();

        public void close();
    }

    public MultiUploadPresenter(Display display, Long idProgetto, String IMGorPDF) {
        this.display = display;
        this.idProgetto = idProgetto;
        this.IMGorPDF = IMGorPDF;
    }

    public void addFileAddedListener(FileAddedListener listener) {
        fileAddedListeners.add(listener);
    }

    public void addFileBeforeSubmitListener(FileBeforeSubmitListener listener) {
        fileBeforeSubmitListeners.add(listener);
    }

    public void addFileUploadFailedListener(FileUploadFailedListener listener) {
        fileUploadFailedListeners.add(listener);
    }

    public void addFileUploadSucceedListener(FileUploadSucceedListener listener) {
        fileUploadSucceedListeners.add(listener);
    }

    @Override
    public void go() {
        bind();
        display.show();
    }

    protected Model assemblyModel(String name, UploadState state) {
        FileUploadModel model = new FileUploadModel(name, state, state.getLabel());
        return model;
    }

    private void bind() {
        bindAddButtonListener();

        bindUploadButtonListener();
        bindSubmitBeforeHandler();
        bindSubmitCompleteHandler();
    }

    private void bindAddButtonListener() {
        Listener<FieldEvent> addButtonClickListener = new Listener<FieldEvent>() {
            @Override
            public void handleEvent(FieldEvent be) {
                String name = MultiUploadUtils.removeFilePath(display.getCurrentFile());
                Model model = assemblyModel(name, UploadState.IN_QUEUE);
                if (display.findModel(name) == null) {
                    display.addModel(model);
                    display.exchangeAddButton();
                    notifyFileAddedListener(name);
                }
            }
        };
        display.setAddButtonListener(addButtonClickListener);
    }

    private void bindUploadButtonListener() {
        SelectionListener<ButtonEvent> uploadButtonListener = new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                display.upload();
            }
        };
        display.setUploadButtonListener(uploadButtonListener);
    }

    private void bindSubmitBeforeHandler() {
        SubmitHandler submitHandler = new SubmitHandler() {
            @Override
            public void onSubmit(SubmitEvent event) {
                notifyBeforeSubmitListener(MultiUploadUtils.removeFilePath(display.getFormPanel().getFilename()));
            }
        };
        display.getFormPanel().addSubmitHandler(submitHandler);
    }

    private void bindSubmitCompleteHandler() {
        SubmitCompleteHandler submitCompleteHandler = new SubmitCompleteHandler() {
            @Override
            public void onSubmitComplete(SubmitCompleteEvent event) {
                processResult(event.getResults());
            }
        };
        display.getFormPanel().addSubmitCompleteHandler(submitCompleteHandler);
    }

    private void processResult(String json) {
        Model model = display.parseModel(json);

        Model foundModel = display.findModel(model.getName());
        foundModel.setState(model.getState());
        foundModel.setMessage(model.getMessage());

        notifyListeners(model);

        display.getFormPanel().removeFileInput(model.getName());
        display.upload();
    }

    private void notifyFileAddedListener(String filename) {
        for (FileAddedListener listener : fileAddedListeners) {
            listener.onFileAdded(filename);
        }
    }

    private void notifyListeners(Model model) {
        if (model.getState().equals(UploadState.OK)) {
            notifyFileUploadSucceedListener(model.getName());
            model.setIdProgetto(idProgetto);
            model.setIMGorPDF(IMGorPDF);

            Dispatcher.forwardEvent(AmministrazioneEvents.SaveProgrammiDiCompensazione, model);
            display.close();


        } else {
            notifyFileUploadFailedListener(model.getName());
        }
    }

    private void notifyBeforeSubmitListener(String filename) {
        for (FileBeforeSubmitListener listener : fileBeforeSubmitListeners) {
            listener.onBeforeSubmit(filename);
        }
    }

    private void notifyFileUploadSucceedListener(String filename) {
        for (FileUploadSucceedListener listener : fileUploadSucceedListeners) {
            listener.onFileUploadSucceed(filename);
        }
    }

    private void notifyFileUploadFailedListener(String filename) {
        for (FileUploadFailedListener listener : fileUploadFailedListeners) {
            listener.onFileUploadFailed(filename);
        }
    }

}
