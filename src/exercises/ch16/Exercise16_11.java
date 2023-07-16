package exercises.ch16;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Exercise16_11 extends Application {
    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root = new BorderPane();

        Histogram histogram = new Histogram();
        root.setCenter(histogram);

        HBox controls = new HBox(5);
        TextField textField = new TextField();
        Button viewButton = new Button("View");

        viewButton.setOnAction(e -> {
            String text = readFile(textField.getText());
            histogram.setCounts(getCounts(text));
        });
        textField.setOnKeyPressed(e -> {
            if (e.getCode() != KeyCode.ENTER) return;
            String text = readFile(textField.getText());
            histogram.setCounts(getCounts(text));
        });

        controls.getChildren().addAll(new Label("Filename"), textField, viewButton);
        controls.setAlignment(Pos.BASELINE_CENTER);
        controls.setSpacing(5);
        controls.setPadding(new Insets(5));
        root.setBottom(controls);

        Scene scene = new Scene(root, 500, 400);
        stage.setScene(scene);
        stage.setTitle(getClass().getSimpleName());
        stage.show();
    }

    private int[] getCounts(String text) {
        int[] counts = new int[26];
        for (char c : text.toCharArray()) {
            if (!Character.isLetter(c)) continue;
            c = Character.toUpperCase(c);
            counts[c - 'A']++;
        }
        return counts;
    }

    private String readFile(String path) {
        File file = new File(path);
        try {
            Scanner scanner = new Scanner(file);
            StringBuilder buffer = new StringBuilder();
            while (scanner.hasNext()) {
                buffer.append(scanner.nextLine()).append("\n");
            }
            return buffer.toString();
        } catch (FileNotFoundException e) {
            return "";
        }
    }
}

class Histogram extends Pane {
    private int[] counts = new int[26];

    public void setCounts(int[] counts) {
        this.counts = counts;
        draw();
    }

    private void draw() {
        getChildren().clear();
        double unit = getHeight() * 0.9 / Arrays.stream(counts).max().orElse(26);
        for (int i = 0; i < counts.length; i++) {
            double x = 15 + i * ((getWidth() - 20) / counts.length);
            double y = getHeight() * 0.9 - unit * counts[i];
            Rectangle rectangle = new Rectangle(x, y, 10, getHeight() * 0.9 - y);
            rectangle.setFill(Color.WHITE);
            rectangle.setStroke(Color.BLACK);
            Text letter = new Text(x, getHeight() * 0.9 + 14, Character.toString((char) i + 'A'));
            letter.setFont(Font.font("", FontWeight.BOLD, 15));
            getChildren().addAll(letter, rectangle);
        }

        getChildren().add(new Line(10, getHeight() * 0.9, getWidth() - 10, getHeight() * 0.9));
    }

    @Override
    protected void setWidth(double v) {
        super.setWidth(v);
        draw();
    }

    @Override
    protected void setHeight(double v) {
        super.setHeight(v);
        draw();
    }
}