package exercises.ch14;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Exercise14_25 extends Application {


    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        PolygonInCircle pane = new PolygonInCircle();
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setWidth(400);
        stage.setHeight(400);
        stage.setResizable(false);
        stage.setTitle(getClass().getSimpleName());
        stage.show();
    }

}

class PolygonInCircle extends Pane {
    private void paint() {
        getChildren().clear();
        double centerX = getWidth() / 2;
        double centerY = getHeight() / 2;
        double radius = getWidth() / 2 - 20;
        Circle circle = new Circle(centerX, centerY, radius);
        circle.setFill(null);
        circle.setStroke(Color.BLACK);

        ArrayList<Double> angles = IntStream.range(0, 6)
                .mapToDouble(x -> Math.random() * (2 * Math.PI))
                .boxed()
                .sorted()
                .collect(Collectors.toCollection(ArrayList::new));
        double[] points = new double[6 * 2];
        for (int i = 0; i < points.length; i += 2) {
            double angle = angles.remove(0);
            points[i] = centerX + radius * Math.cos(angle);
            points[i + 1] = centerY + radius * Math.sin(angle);
        }

        Polygon polygon = new Polygon(points);
        polygon.setFill(null);
        polygon.setStroke(Color.BLACK);

        getChildren().addAll(circle, polygon);
    }

    @Override
    protected void setHeight(double v) {
        super.setHeight(v);
        paint();
    }

    @Override
    protected void setWidth(double v) {
        super.setWidth(v);
        paint();
    }
}




