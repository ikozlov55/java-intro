package exercises.ch15;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Exercise15_4 extends Application {
    private final TextField tfNumber1 = new TextField();
    private final TextField tfNumber2 = new TextField();
    private final TextField tfResult = new TextField();

    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        tfNumber1.setPrefWidth(50);
        tfNumber2.setPrefWidth(50);
        tfResult.setPrefWidth(50);
        HBox form = new HBox(
                new Label("Number 1: "),
                tfNumber1,
                new Label("Number 2: "),
                tfNumber2,
                new Label("Result: "),
                tfResult
        );
        form.setSpacing(10);
        form.setAlignment(Pos.BASELINE_CENTER);

        Button btnAdd = new Button("Add");
        btnAdd.setOnAction(e -> calculate(Operator.addition));
        Button btnSubtract = new Button("Subtract");
        btnSubtract.setOnAction(e -> calculate(Operator.subtraction));
        Button btnMultiply = new Button("Multiply");
        btnMultiply.setOnAction(e -> calculate(Operator.multiplication));
        Button btnDivide = new Button("Divide");
        btnDivide.setOnAction(e -> calculate(Operator.division));
        HBox controls = new HBox(btnAdd, btnSubtract, btnMultiply, btnDivide);
        controls.setSpacing(10);
        controls.setAlignment(Pos.CENTER);

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));
        root.setCenter(form);
        root.setBottom(controls);
        root.setPrefHeight(80);
        BorderPane.setAlignment(controls, Pos.CENTER);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle(getClass().getSimpleName());
        stage.show();
    }

    private void calculate(Operator op) {
        double number1 = Double.parseDouble(tfNumber1.getText());
        double number2 = Double.parseDouble(tfNumber2.getText());
        double result = switch (op) {
            case addition -> number1 + number2;
            case subtraction -> number1 - number2;
            case multiplication -> number1 * number2;
            case division -> number1 / number2;
        };
        tfResult.setText(Double.toString(result));
    }

    enum Operator {
        addition,
        subtraction,
        multiplication,
        division
    }

}
