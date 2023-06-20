package exercises.ch15;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Locale;

public class Exercise15_21 extends Application {

    private final Circle c1 = new Circle(15);
    private final Circle c2 = new Circle(15);
    private final Circle c3 = new Circle(15);
    private final Circle circle = new Circle();
    private final Label label1 = new Label();
    private final Label label2 = new Label();
    private final Label label3 = new Label();

    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Pane root = new Pane();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle(getClass().getSimpleName());
        stage.setWidth(400);
        stage.setHeight(400);
        stage.setResizable(false);
        stage.show();
        initLayout(root);
    }

    private void initLayout(Pane root) {
        circle.radiusProperty().bind(root.widthProperty().divide(3));
        circle.centerXProperty().bind(root.widthProperty().divide(2));
        circle.centerYProperty().bind(root.heightProperty().divide(2));
        circle.setStroke(Color.BLACK);
        circle.setFill(null);

        for (Circle c : new Circle[]{c1, c2, c3}) {
            double angle = Math.random() * (2 * Math.PI);
            c.setCenterX(circle.getCenterX() + circle.getRadius() * Math.cos(angle));
            c.setCenterY(circle.getCenterY() + circle.getRadius() * Math.sin(angle));
            c.setOnMouseDragged(this::handleMouseDrag);
        }

        for (Circle[] points : new Circle[][]{{c1, c2}, {c2, c3}, {c3, c1}}) {
            Line line = new Line();
            line.startXProperty().bind(points[0].centerXProperty());
            line.startYProperty().bind(points[0].centerYProperty());
            line.endXProperty().bind(points[1].centerXProperty());
            line.endYProperty().bind(points[1].centerYProperty());
            root.getChildren().add(line);
        }

        label1.setFont(new Font(16));
        label1.layoutXProperty().bind(c1.centerXProperty().add(10));
        label1.layoutYProperty().bind(c1.centerYProperty().subtract(30));
        label2.setFont(new Font(16));
        label2.layoutXProperty().bind(c2.centerXProperty().add(10));
        label2.layoutYProperty().bind(c2.centerYProperty().subtract(30));
        label3.setFont(new Font(16));
        label3.layoutXProperty().bind(c3.centerXProperty().add(10));
        label3.layoutYProperty().bind(c3.centerYProperty().subtract(30));

        root.getChildren().addAll(circle, label1, label2, label3, c1, c2, c3);
        updateLabels();
    }

    private void handleMouseDrag(MouseEvent e) {
        Circle c = (Circle) e.getSource();
        double ex = e.getX();
        double ey = e.getY();
        double cx = circle.getCenterX();
        double cy = circle.getCenterY();
        double r = circle.getRadius();
        double a = Math.pow(ex - cx, 2) + Math.pow(ey - cy, 2);
        double x = cx + r * ((ex - cx) / Math.sqrt(a));
        double y = cy + r * ((ey - cy) / Math.sqrt(a));
        c.setCenterX(x);
        c.setCenterY(y);
        updateLabels();
    }

    private void updateLabels() {
        double a = distance(c2, c3);
        double b = distance(c3, c1);
        double c = distance(c1, c2);
        double A = Math.toDegrees(Math.acos((a * a - b * b - c * c) / (-2 * b * c)));
        double B = Math.toDegrees(Math.acos((b * b - a * a - c * c) / (-2 * a * c)));
        double C = Math.toDegrees(Math.acos((c * c - b * b - a * a) / (-2 * a * b)));
        label1.setText(String.format(Locale.US, "%.2f", A));
        label2.setText(String.format(Locale.US, "%.2f", B));
        label3.setText(String.format(Locale.US, "%.2f", C));
    }

    private double distance(Circle c1, Circle c2) {
        return Math.sqrt(Math.pow(c1.getCenterX() - c2.getCenterX(), 2) +
                Math.pow(c1.getCenterY() - c2.getCenterY(), 2));
    }

}
