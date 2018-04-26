import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * GUI prototype using JavaFX.
 */
public class JavaFXPrototype extends Application {

    private static final int WIDTH  = 600;
    private static final int HEIGHT = 500;

    @Override
    public void start (Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Consudoku");
        btn.setOnAction( e ->
            System.out.println("hey")
        );

        StackPane root = new StackPane();
        root.getChildren().add(btn);

        Scene scene = new Scene(root, WIDTH, HEIGHT);

        primaryStage.setTitle("Consudoku");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main (String[] args) {
        launch(args);
    }

}
