package exercises.ch14;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.List;
import java.util.stream.IntStream;

public class Exercise14_5 extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        String s = "Welcome to Java ".toUpperCase();
        Font font = Font.font("Times new Roman", FontWeight.BOLD, 30);
        Pane pane = new Pane();
        pane.setPrefSize(200, 200);
        double centerX = pane.getPrefWidth() / 2 - 10;
        double centerY = pane.getPrefHeight() / 2 + 10;
        double radius = centerX * 0.85;
        List<Text> nodes = IntStream.range(0, s.length()).mapToObj(i -> {
            double x = centerX + radius * Math.sin(Math.PI / 2 + i * 2 * Math.PI / s.length());
            double y = centerY - radius * Math.cos(Math.PI / 2 + i * 2 * Math.PI / s.length());
            Text c = new Text(x, y, s.substring(i, i + 1));
            c.setFont(font);
            c.setRotate(90 + 360.0 * i / s.length());
            return c;
        }).toList();
        pane.getChildren().addAll(nodes);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setTitle("Exercise14_05");
        stage.show();
    }

    public static void run() {
        Application.launch();
    }
}
