package exercises.ch15;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.Arrays;

public class Exercise15_17 extends Application {
    private final double r = 10;
    private final Pane root = new Pane();
    private Rectangle rectangle = null;

    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        root.setOnMouseClicked(this::addPoint);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        root.requestFocus();
        stage.setWidth(400);
        stage.setHeight(300);
        stage.setTitle(getClass().getSimpleName());
        stage.show();
    }

    private void addPoint(MouseEvent e) {
        if (e.getButton() != MouseButton.PRIMARY) return;
        Circle point = new Circle(e.getX(), e.getY(), r);
        point.setStroke(Color.BLACK);
        point.setFill(Color.WHITE);
        point.setOnMouseClicked(this::removePoint);
        root.getChildren().add(point);
        drawRectangle();
    }

    private void removePoint(MouseEvent e) {
        if (e.getButton() != MouseButton.SECONDARY) return;
        Circle point = (Circle) e.getSource();
        root.getChildren().remove(point);
        drawRectangle();
    }

    private void drawRectangle() {
        root.getChildren().remove(rectangle);
        Circle[] points = root.getChildren().stream()
                .filter(x -> x instanceof Circle)
                .map(Circle.class::cast)
                .toArray(Circle[]::new);
        if (points.length < 3) return;
        Double[] xValues = Arrays.stream(points).map(Circle::getCenterX).toArray(Double[]::new);
        Double[] yValues = Arrays.stream(points).map(Circle::getCenterY).toArray(Double[]::new);
        double minX = Arrays.stream(xValues).min(Double::compare).get() - r;
        double maxX = Arrays.stream(xValues).max(Double::compare).get() + r;
        double minY = Arrays.stream(yValues).min(Double::compare).get() - r;
        double maxY = Arrays.stream(yValues).max(Double::compare).get() + r;
        double width = maxX - minX;
        double height = maxY - minY;
        double x = (minX + maxX) / 2 - width / 2;
        double y = (minY + maxY) / 2 - height / 2;
        rectangle = new Rectangle(x, y, width, height);
        rectangle.setStroke(Color.BLACK);
        rectangle.setFill(null);
        root.getChildren().add(rectangle);
    }
}
