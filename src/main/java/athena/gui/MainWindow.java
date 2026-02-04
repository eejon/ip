package athena.gui;

import athena.Athena;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Main GUI window for the Athena application.
 * Extends JavaFX Application to provide the graphical user interface.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Athena athena;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image athenaImage = new Image(this.getClass().getResourceAsStream("/images/DaAthena.png"));

    /**
     * Initializes the MainWindow after FXML components are loaded.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets the Athena instance for this window.
     *
     * @param athena The Athena chatbot instance.
     */
    public void setAthena(Athena athena) {
        this.athena = athena;
    }

    /**
     * Handles user input when the send button is clicked or Enter is pressed.
     * Creates dialog boxes for user input and Athena's response.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = athena.getResponse(input);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getAthenaDialog(response.trim(), athenaImage)
        );
        userInput.clear();
    }

    /**
     * Shows a message from Athena (for welcome, initialization, etc.)
     *
     * @param message The message to display.
     */
    public void showMessage(String message) {
        dialogContainer.getChildren().add(
            DialogBox.getAthenaDialog(message, athenaImage)
        );
    }
}
