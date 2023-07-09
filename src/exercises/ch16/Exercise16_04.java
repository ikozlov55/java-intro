package exercises.ch16;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Exercise16_04 extends Application {

    private final double MILES_PER_KM = 1.60934;

    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        GridPane root = new GridPane();
        root.setPadding(new Insets(10));
        root.setHgap(5);
        root.setVgap(5);
        root.add(new Label("Mile"), 0, 0);
        root.add(new Label("Kilometer"), 0, 1);
        TextField mileFiled = new TextField();
        mileFiled.setAlignment(Pos.BASELINE_RIGHT);
        root.add(mileFiled, 1, 0);
        TextField kilometerField = new TextField();
        kilometerField.setAlignment(Pos.BASELINE_RIGHT);
        root.add(kilometerField, 1, 1);

        mileFiled.setOnKeyPressed(e -> {
            if (e.getCode() != KeyCode.ENTER) return;
            try {
                double miles = Double.parseDouble(mileFiled.getText());
                kilometerField.setText(Double.toString(miles * MILES_PER_KM));
            } catch (NumberFormatException ex) {
                mileFiled.setText("");
            }
        });

        kilometerField.setOnKeyPressed(e -> {
            if (e.getCode() != KeyCode.ENTER) return;
            try {
                double kilometers = Double.parseDouble(kilometerField.getText());
                mileFiled.setText(Double.toString(kilometers / MILES_PER_KM));
            } catch (NumberFormatException ex) {
                kilometerField.setText("");
            }
        });

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle(getClass().getSimpleName());
        stage.show();
    }
}
