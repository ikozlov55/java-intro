package exercises.ch15;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Exercise15_6 extends Application {
    private final String msg1 = "Java is fun";
    private final String msg2 = "Java is powerful";

    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root = new BorderPane();
        Text label = new Text();
        root.setCenter(label);

        root.setOnMouseClicked(e -> label.setText(switch (label.getText()) {
            case "", msg2 -> msg1;
            case msg1 -> msg2;
            default -> "";
        }));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setWidth(200);
        stage.setHeight(100);
        stage.setTitle(getClass().getSimpleName());
        stage.show();
    }

}
