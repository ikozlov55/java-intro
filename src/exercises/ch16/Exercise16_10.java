package exercises.ch16;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Exercise16_10 extends Application {
    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root = new BorderPane();

        TextArea textArea = new TextArea();
        textArea.setEditable(false);
        root.setCenter(textArea);

        HBox controls = new HBox(5);
        TextField textField = new TextField();
        Button viewButton = new Button("View");
        viewButton.setOnAction(e -> {
            try {
                File file = new File(textField.getText());
                Scanner scanner = new Scanner(file);
                StringBuffer buffer = new StringBuffer();
                while (scanner.hasNext()) {
                    buffer.append(scanner.nextLine() + "\n");
                }
                textArea.setText(buffer.toString());
            } catch (FileNotFoundException ex) {
                textArea.setText("File not found!");
                return;
            }

        });
        controls.getChildren().addAll(new Label("Filename"), textField, viewButton);
        controls.setAlignment(Pos.BASELINE_CENTER);
        controls.setSpacing(5);
        controls.setPadding(new Insets(5));
        root.setBottom(controls);

        Scene scene = new Scene(root, 400, 200);
        stage.setScene(scene);
        stage.setTitle(getClass().getSimpleName());
        stage.show();
    }
}
