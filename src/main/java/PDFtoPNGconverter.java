import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PDFtoPNGconverter extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
        Parent root = loader.load();
        Controller mController = loader.getController();
        mController.setStageAndSetListeners(primaryStage);
        primaryStage.setTitle("PDF to PNG converter");
        primaryStage.setScene(new Scene(root, 500, 150));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
