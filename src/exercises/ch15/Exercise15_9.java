package exercises.ch15;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Exercise15_9 extends Application {
    private double x = 100;
    private double y = 100;
    private final double step = 1;

    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Pane root = new Pane();
        root.setOnKeyPressed(e -> {
            double nextX = x;
            double nextY = y;
            switch (e.getCode()) {
                case UP -> nextY -= step;
                case DOWN -> nextY += step;
                case LEFT -> nextX -= step;
                case RIGHT -> nextX += step;
            }
            Line line = new Line(x, y, nextX, nextY);
            line.setStroke(Color.BLACK);
            root.getChildren().add(line);
            x = nextX;
            y = nextY;
        });
        Scene scene = new Scene(root);
        root.requestFocus();
        stage.setScene(scene);
        stage.setWidth(300);
        stage.setHeight(300);
        stage.setTitle(getClass().getSimpleName());
        stage.show();
    }

}


