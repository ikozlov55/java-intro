package exercises.ch16;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Exercise16_13 extends Application {
    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root = new BorderPane();
        TextField amountField = new TextField();
        amountField.setPrefColumnCount(3);
        TextField yearsField = new TextField();
        yearsField.setPrefColumnCount(1);
        Button showTableButton = new Button("Show Table");
        HBox controls = new HBox(10,
                new Label("Loan Amount"),
                amountField,
                new Label("Number Of Years"),
                yearsField,
                showTableButton);
        controls.setPadding(new Insets(5));
        controls.setAlignment(Pos.BASELINE_CENTER);
        root.setTop(controls);
        TextArea textArea = new TextArea();
        root.setCenter(textArea);

        showTableButton.setOnAction(e -> {
            try {
                double amount = Double.parseDouble(amountField.getText());
                int years = Integer.parseInt(yearsField.getText());
                textArea.setText(makeTable(amount, years));
            } catch (NumberFormatException ex) {
                textArea.setText("Invalid input!");
            }
        });

        stage.setScene(new Scene(root, 500, 300));
        stage.setTitle(getClass().getSimpleName());
        stage.show();
    }

    private String makeTable(double amount, int years) {
        final double INCREMENT = 0.125;
        final double INTEREST_MIN = 5;
        final double INTEREST_MAX = 8;
        StringBuilder result = new StringBuilder();
        result.append(String.format("%-18s%-18s%-18s\n", "Interest Rate", "Monthly Payment", "Total Payment"));
        for (double interest = INTEREST_MIN; interest <= INTEREST_MAX; interest += INCREMENT) {
            double months = years * 12;
            double r = interest / 100 / 12;
            double n = years * 12;
            double monthlyPayment = amount * (r * Math.pow(1 + r, n)) / (Math.pow(1 + r, n) - 1);
            double totalPayment = monthlyPayment * months;
            result.append(String.format("%-22.2f%-27.2f%-22.2f\n", interest, monthlyPayment, totalPayment));
        }
        return result.toString();
    }
}
