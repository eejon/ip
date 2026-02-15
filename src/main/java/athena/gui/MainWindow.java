package athena.gui;

import athena.Athena;
import athena.ui.Response;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

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
        Response response = athena.getResponse(input);
        String message = response.getMessage();
        boolean isError = response.isError();
        boolean isSuccess = response.isSuccess();
        boolean shouldExit = response.shouldExit();

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage)
        );

        if (isError) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getErrorDialog(message.trim(), athenaImage)
            );
        } else if (isSuccess) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getSuccessDialog(message.trim(), athenaImage)
            );
        } else {
            dialogContainer.getChildren().addAll(
                    DialogBox.getAthenaDialog(message.trim(), athenaImage)
            );
        }

        if (shouldExit) {
            startShutdownCountdown(5);
        }

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

    // Solution below adapted from https://stackoverflow.com/questions/77445754/countdown-timer-java-javafx
    private void startShutdownCountdown(int seconds) {
        userInput.setDisable(true);
        sendButton.setDisable(true);

        final int[] remaining = { seconds };
        userInput.setText("Closing in " + remaining[0]);

        Timeline countdown = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            remaining[0]--;
            if (remaining[0] > 0) {
                userInput.setText("Closing in " + remaining[0]);
            } else {
                Platform.exit();
            }
        }));
        countdown.setCycleCount(seconds);
        countdown.play();
    }
}
