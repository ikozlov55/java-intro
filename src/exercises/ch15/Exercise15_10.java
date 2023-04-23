package exercises.ch15;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Exercise15_10 extends Application {
    private final StringBuilder input = new StringBuilder();
    private final Text label = new Text();

    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root = new BorderPane();
        root.setCenter(label);
        root.setOnKeyPressed(this::handleInput);

        Scene scene = new Scene(root);
        root.requestFocus();
        stage.setScene(scene);
        stage.setWidth(300);
        stage.setHeight(300);
        stage.setTitle(getClass().getSimpleName());
        stage.show();
    }

    private void handleInput(KeyEvent e) {
        if (e.getCode() == KeyCode.ENTER) {
            label.setText(input.toString());
            input.append('\n');
        } else {
            input.append(e.getText());
        }
    }
}


