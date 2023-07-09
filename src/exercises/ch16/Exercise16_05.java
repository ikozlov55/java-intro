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

public class Exercise16_05 extends Application {

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
        root.add(new Label("Decimal"), 0, 0);
        root.add(new Label("Hex"), 0, 1);
        root.add(new Label("Binary"), 0, 2);
        TextField decimalFiled = new TextField();
        decimalFiled.setAlignment(Pos.BASELINE_RIGHT);
        root.add(decimalFiled, 1, 0);
        TextField hexField = new TextField();
        hexField.setAlignment(Pos.BASELINE_RIGHT);
        root.add(hexField, 1, 1);
        TextField binaryField = new TextField();
        binaryField.setAlignment(Pos.BASELINE_RIGHT);
        root.add(binaryField, 1, 2);

        decimalFiled.setOnKeyPressed(e -> {
            if (e.getCode() != KeyCode.ENTER) return;
            try {
                int decimal = Integer.parseInt(decimalFiled.getText());
                hexField.setText(Integer.toHexString(decimal));
                binaryField.setText(Integer.toBinaryString(decimal));
            } catch (NumberFormatException ex) {
                decimalFiled.setText("");
            }
        });

        hexField.setOnKeyPressed(e -> {
            if (e.getCode() != KeyCode.ENTER) return;
            try {
                int decimal = Integer.parseInt(hexField.getText(), 16);
                decimalFiled.setText(Integer.toString(decimal));
                binaryField.setText(Integer.toBinaryString(decimal));
            } catch (NumberFormatException ex) {
                hexField.setText("");
            }
        });

        binaryField.setOnKeyPressed(e -> {
            if (e.getCode() != KeyCode.ENTER) return;
            try {
                int decimal = Integer.parseInt(binaryField.getText(), 2);
                decimalFiled.setText(Integer.toString(decimal));
                hexField.setText(Integer.toHexString(decimal));
            } catch (NumberFormatException ex) {
                binaryField.setText("");
            }
        });

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle(getClass().getSimpleName());
        stage.show();
    }

}
