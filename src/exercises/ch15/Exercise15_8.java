package exercises.ch15;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Locale;

public class Exercise15_8 extends Application {
    private final Text label = new Text();

    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        label.setFont(Font.font(20));
        BorderPane root = new BorderPane();
        root.setCenter(label);
        //root.setOnMouseClicked(this::showMousePosition);

        root.setOnMousePressed(this::showMousePosition);
        root.setOnMouseReleased(e -> label.setText(""));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setWidth(300);
        stage.setHeight(300);
        stage.setTitle(getClass().getSimpleName());
        stage.show();
    }

    private void showMousePosition(MouseEvent e) {
        String text = String.format(Locale.US, "(%.2f, %.2f)", e.getSceneX(), e.getSceneY());
        label.setText(text);
    }

}
