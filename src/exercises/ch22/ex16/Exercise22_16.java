package exercises.ch22.ex16;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Arrays;

public class Exercise22_16 extends Application {

    private double[] numbers = new double[20];
    private final Label statusLabel = new Label();
    private final TextField keyField = new TextField();
    private HistogramPane histogram = new HistogramPane(numbers);
    private final Button stepButton = new Button("Step");
    private final Button resetButton = new Button("Reset");

    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        initNumbers();
        BorderPane root = makeRoot();
        stepButton.setOnAction(this::step);
        resetButton.setOnAction(this::reset);
        stage.setScene(new Scene(root, 600, 400));
        stage.setTitle(getClass().getSimpleName());
        stage.show();
    }

    private void initNumbers() {
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = i + 1;
        }
        for (int i = 0; i < numbers.length; i++) {
            int index = (int) (Math.random() * numbers.length);
            double temp = numbers[i];
            numbers[i] = numbers[index];
            numbers[index] = temp;
        }
    }

    private BorderPane makeRoot() {
        BorderPane root = new BorderPane();
        BorderPane.setAlignment(statusLabel, Pos.CENTER);
        BorderPane.setMargin(statusLabel, new Insets(5));

        HBox controls = new HBox(5, new Label("Key (in double)"), keyField, stepButton, resetButton);
        controls.setPadding(new Insets(5));
        controls.setAlignment(Pos.BASELINE_CENTER);

        root.setTop(statusLabel);
        root.setCenter(histogram);
        root.setBottom(controls);
        return root;
    }

    private void step(ActionEvent e) {
        try {
            double key = Double.parseDouble(keyField.getText());
            keyField.setDisable(true);
            int index = histogram.getActiveBarIndex() + 1;
            histogram.setActiveBarIndex(index);
            if (numbers[index] == key) {
                statusLabel.setText(String.format("The key is found in the array at index %d", index));
                stepButton.setDisable(true);
            }
        } catch (NumberFormatException ignored) {
        }
    }

    private void reset(ActionEvent e) {
        statusLabel.setText("");
        initNumbers();
        histogram.setData(numbers);
        keyField.setDisable(false);
        keyField.clear();
        stepButton.setDisable(false);
    }
}

class HistogramPane extends Pane {
    private double[] data;

    private int activeBarIndex;

    public HistogramPane(double[] data) {
        setData(data);
    }

    public int getActiveBarIndex() {
        return activeBarIndex;
    }

    public void setActiveBarIndex(int activeBarIndex) {
        this.activeBarIndex = activeBarIndex;
        draw();
    }

    public void setData(double[] data) {
        this.data = data;
        this.activeBarIndex = -1;
        draw();
    }

    private void draw() {
        getChildren().clear();
        setStyle("-fx-border-color: black");
        double barWidth = (getWidth() - (data.length + 1) * 5) / data.length;
        double n = (getHeight() * 0.9) / Arrays.stream(data).max().orElse(20);
        for (int i = 0; i < data.length; i++) {
            double barHeight = data[i] * n;
            double y = getHeight() - barHeight - 1;
            double x = barWidth * i + 5 * i + 5;
            Rectangle rec = new Rectangle(x, y, barWidth, barHeight);
            rec.setFill(i == activeBarIndex ? Color.RED : Color.WHITE);
            rec.setStroke(Color.BLACK);

            Text label = new Text(x + barWidth / 4, y - 5, String.format("%.0f", data[i]));
            label.setFont(Font.font(15));

            getChildren().addAll(rec, label);
        }
    }

    @Override
    protected void setHeight(double v) {
        super.setHeight(v);
        draw();
    }

    @Override
    protected void setWidth(double v) {
        super.setWidth(v);
        draw();
    }
}