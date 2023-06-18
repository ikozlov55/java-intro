package exercises.ch15;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Locale;

public class Exercise15_20 extends Application {
    private final Circle c1 = new Circle(40, 40, 15);
    private final Circle c2 = new Circle(120, 150, 15);
    private final Circle c3 = new Circle(220, 60, 15);
    private final Label label1 = new Label();
    private final Label label2 = new Label();
    private final Label label3 = new Label();

    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Pane root = new Pane();
        c1.setOnMouseDragged(this::handleMouseDrag);
        c2.setOnMouseDragged(this::handleMouseDrag);
        c3.setOnMouseDragged(this::handleMouseDrag);

        Line line1 = new Line();
        line1.startXProperty().bind(c1.centerXProperty());
        line1.startYProperty().bind(c1.centerYProperty());
        line1.endXProperty().bind(c2.centerXProperty());
        line1.endYProperty().bind(c2.centerYProperty());
        Line line2 = new Line();
        line2.startXProperty().bind(c2.centerXProperty());
        line2.startYProperty().bind(c2.centerYProperty());
        line2.endXProperty().bind(c3.centerXProperty());
        line2.endYProperty().bind(c3.centerYProperty());
        Line line3 = new Line();
        line3.startXProperty().bind(c3.centerXProperty());
        line3.startYProperty().bind(c3.centerYProperty());
        line3.endXProperty().bind(c1.centerXProperty());
        line3.endYProperty().bind(c1.centerYProperty());

        label1.setFont(new Font(16));
        label1.layoutXProperty().bind(c1.centerXProperty().add(10));
        label1.layoutYProperty().bind(c1.centerYProperty().subtract(30));
        label2.setFont(new Font(16));
        label2.layoutXProperty().bind(c2.centerXProperty().add(10));
        label2.layoutYProperty().bind(c2.centerYProperty().subtract(30));
        label3.setFont(new Font(16));
        label3.layoutXProperty().bind(c3.centerXProperty().add(10));
        label3.layoutYProperty().bind(c3.centerYProperty().subtract(30));

        root.getChildren().addAll(line1, line2, line3, label1, label2, label3, c1, c2, c3);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle(getClass().getSimpleName());
        stage.setWidth(400);
        stage.setHeight(300);
        stage.show();
        updateLabels();
    }

    private void handleMouseDrag(MouseEvent e) {
        Circle c = (Circle) e.getSource();
        c.setCenterX(e.getX());
        c.setCenterY(e.getY());
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
