package exercises.ch16;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Exercise16_06 extends Application {
    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        GridPane root = new GridPane();
        root.setPadding(new Insets(10));
        root.setVgap(10);
        root.setHgap(5);

        TextField textField = new TextField("JavaFX");
        textField.setPrefColumnCount(10);
        HBox textFieldBox = new HBox(5, new Label("Text Field"), textField);
        textFieldBox.setAlignment(Pos.BASELINE_CENTER);
        root.add(textFieldBox, 0, 0);

        ToggleGroup group = new ToggleGroup();
        RadioButton leftButton = new RadioButton("Left");
        leftButton.setToggleGroup(group);
        RadioButton centerButton = new RadioButton("Center");
        centerButton.setToggleGroup(group);
        RadioButton rightButton = new RadioButton("Right");
        rightButton.setToggleGroup(group);

        EventHandler<ActionEvent> handler = e -> {
            if (leftButton.isSelected()) {
                textField.setAlignment(Pos.BASELINE_LEFT);
            } else if (centerButton.isSelected()) {
                textField.setAlignment(Pos.BASELINE_CENTER);
            } else if (rightButton.isSelected()) {
                textField.setAlignment(Pos.BASELINE_RIGHT);
            }
        };
        leftButton.setOnAction(handler);
        rightButton.setOnAction(handler);
        centerButton.setOnAction(handler);

        TextField columnSizeField = new TextField();
        columnSizeField.setAlignment(Pos.BASELINE_RIGHT);
        columnSizeField.setPrefColumnCount(2);
        columnSizeField.setOnKeyPressed(e -> {
            if (e.getCode() != KeyCode.ENTER) return;
            try {
                int size = Integer.parseInt(columnSizeField.getText());
                textField.setPrefColumnCount(size);
            } catch (NumberFormatException ex) {
                columnSizeField.setText("");
            }
        });

        HBox controlsBox = new HBox(5, leftButton, centerButton, rightButton,
                new Label("Column Size"), columnSizeField);
        root.add(controlsBox, 0, 1);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle(getClass().getSimpleName());
        stage.show();
    }


}
