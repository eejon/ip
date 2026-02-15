package athena.gui;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

/**
 * Represents a dialog box consisting of an ImageView to represent the speaker's face
 * and a label containing text from the speaker.
 */
public class DialogBox extends HBox {
    private final double PROFILE_RADIUS = 27.5;
    private final double WIDTH_MULTIPLIER = 0.7;
    private final int WIDTH_TRIM_LENGTH = 20; 
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;
    @FXML
    private VBox dialogBubble;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();

            dialogBubble.maxWidthProperty().bind(this.widthProperty().multiply(WIDTH_MULTIPLIER));

            dialog.maxWidthProperty().bind(dialogBubble.maxWidthProperty().subtract(WIDTH_TRIM_LENGTH));
            dialog.setWrapText(true);

        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);

        // Make avatar circular with rounded clip
        Circle clip = new Circle(PROFILE_RADIUS, PROFILE_RADIUS, PROFILE_RADIUS);
        displayPicture.setClip(clip);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    /**
     * Creates a user dialog with user styling.
     */
    public static DialogBox getUserDialog(String s, Image i) {
        var db = new DialogBox(s, i);
        db.dialogBubble.getStyleClass().add("user-bubble");
        db.dialog.getStyleClass().add("user-label");
        return db;
    }

    /**
     * Creates an Athena dialog with Athena styling.
     */
    public static DialogBox getAthenaDialog(String s, Image i) {
        var db = new DialogBox(s, i);
        db.dialogBubble.getStyleClass().add("athena-bubble");
        db.dialog.getStyleClass().add("athena-label");
        db.flip();
        return db;
    }

    /**
     * Creates an error dialog with error styling.
     */
    public static DialogBox getErrorDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        db.dialogBubble.getStyleClass().add("error-bubble");
        db.dialog.getStyleClass().add("error-label");
        return db;
    }

    /**
     * Creates a success dialog with success styling.
     */
    public static DialogBox getSuccessDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        db.dialogBubble.getStyleClass().add("success-bubble");
        db.dialog.getStyleClass().add("success-label");
        return db;
    }
}
