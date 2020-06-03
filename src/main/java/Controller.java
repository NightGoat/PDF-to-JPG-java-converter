import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class Controller {

    @FXML
    public AnchorPane anchorPane;

    @FXML
    private Button chooseFileBtn;

    @FXML
    private Button chooseFolderBtn;

    @FXML
    private Button convertFileBtn;

    @FXML
    private Button convertFolderBtn;

    @FXML
    private TextField addressEdit;

    @FXML
    void initialize() {
    }

    void setStageAndSetListeners(Stage stage){
        PDFtoJPEG pdFtoJPEG = new PDFtoJPEG();
        DirectoryChooser directoryChooser = new DirectoryChooser();
        FileChooser fileChooser = new FileChooser();
        directoryChooser.setInitialDirectory(new File("src"));
        fileChooser.setInitialDirectory(new File("src"));
        chooseFolderBtn.setOnAction(actionEvent -> {
            File selectedDirectory = directoryChooser.showDialog(stage);
            if (selectedDirectory != null) addressEdit.setText(selectedDirectory.getAbsolutePath());
        });
        chooseFileBtn.setOnAction(actionEvent -> {
            File selectedDirectory = fileChooser.showOpenDialog(stage);
            if (selectedDirectory != null) addressEdit.setText(selectedDirectory.getAbsolutePath());
        });
        convertFileBtn.setOnAction(actionEvent -> pdFtoJPEG.pdfToImage(addressEdit.getText()));
        convertFolderBtn.setOnAction(actionEvent -> {
            try {
                pdFtoJPEG.pdfToImageAllFilesInFolder(addressEdit.getText());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
