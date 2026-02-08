package athena.gui;

import java.io.IOException;

import athena.Athena;
import athena.exceptions.AthenaException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * GUI for Athena using FXML
 */
public class AthenaGui extends Application {

    private Athena athena = new Athena();

    @Override
    public void start(Stage stage) {
        // Setting up required components
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(AthenaGui.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);

            MainWindow controller = fxmlLoader.<MainWindow>getController();
            controller.setAthena(athena);
            controller.showMessage(athena.getGreeting());
            
            try {
                athena.initialize();
            } catch (AthenaException e) {
                controller.showMessage(e.getMessage());
            }
            
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
