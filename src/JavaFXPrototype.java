import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * GUI prototype using JavaFX.
 */
public class JavaFXPrototype extends Application {

    private static final String FXMLSource = "FXMLDocument.fxml";
    //private static final int WIDTH  = 600;
    //private static final int HEIGHT = 500;

    @Override
    public void start (Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(FXMLSource));
        Scene scene = new Scene(root);//, WIDTH, HEIGHT);

        stage.setTitle("FXML Test");
        stage.setScene(scene);
        stage.show();
    }

    public static void main (String[] args) {
        launch(args);
    }

}
