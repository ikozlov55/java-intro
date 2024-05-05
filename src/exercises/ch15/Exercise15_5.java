package exercises.ch15;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Exercise15_5 extends Application {
    private final TextField tfInvestmentAmount = new TextField();
    private final TextField tfNumberOfYears = new TextField();
    private final TextField tfAnnualInterestRate = new TextField();
    private final TextField tfFutureValue = new TextField();

    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Button btnCalculate = new Button("Calculate");
        btnCalculate.setOnAction(e -> calculate());

        GridPane root = new GridPane();
        root.setHgap(5);
        root.setVgap(5);
        root.setPadding(new Insets(10));
        root.addColumn(
                0,
                new Label("Investment Amount: "),
                new Label("Number of Years: "),
                new Label("Annual Interest Rate: "),
                new Label("Future value: ")
        );
        root.addColumn(1, tfInvestmentAmount, tfNumberOfYears, tfAnnualInterestRate, tfFutureValue, btnCalculate);
        tfInvestmentAmount.setAlignment(Pos.BOTTOM_RIGHT);
        tfNumberOfYears.setAlignment(Pos.BOTTOM_RIGHT);
        tfAnnualInterestRate.setAlignment(Pos.BOTTOM_RIGHT);
        tfFutureValue.setAlignment(Pos.BOTTOM_RIGHT);
        tfFutureValue.setEditable(false);
        GridPane.setHalignment(btnCalculate, HPos.RIGHT);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle(getClass().getSimpleName());
        stage.show();
    }

    private void calculate() {
        try {
            double investmentAmount = Double.parseDouble(tfInvestmentAmount.getText());
            double numberOfYears = Double.parseDouble(tfNumberOfYears.getText());
            double annualInterestRate = Double.parseDouble(tfAnnualInterestRate.getText());
            double monthlyInterestRate = annualInterestRate / 1200;
            double futureValue = investmentAmount * Math.pow(1 + monthlyInterestRate, numberOfYears * 12);
            tfFutureValue.setText(String.format("$ %.2f", futureValue));
        } catch (NumberFormatException ignored) {
        }
    }
}
