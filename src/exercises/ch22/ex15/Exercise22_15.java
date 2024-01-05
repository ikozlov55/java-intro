package exercises.ch22.ex15;

import javafx.application.Application;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polyline;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Comparator;

public class Exercise22_15 extends Application {
    Pane root = new Pane();
    ArrayList<Point2D> points = new ArrayList<>();
    Polyline polyline = new Polyline();

    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        root.getChildren().addAll(new InstructionPane(), polyline);
        root.setOnMouseClicked(this::addPoint);

        stage.setScene(new Scene(root, 400, 400));
        stage.setTitle(getClass().getSimpleName());
        stage.show();
    }

    private void addPoint(MouseEvent e) {
        if (e.getButton() != MouseButton.PRIMARY) return;
        Circle circle = new Circle(e.getX(), e.getY(), 5);
        circle.setOnMouseClicked(this::removePoint);
        root.getChildren().add(circle);
        points.add(new Point2D(circle.getCenterX(), circle.getCenterY()));
        if (points.size() > 2) {
            drawPolygon();
        }
    }

    private void removePoint(MouseEvent e) {
        if (e.getButton() != MouseButton.SECONDARY) return;
        Circle circle = (Circle) e.getSource();
        root.getChildren().remove(circle);
        points.remove(new Point2D(circle.getCenterX(), circle.getCenterY()));
        if (points.size() > 2) {
            drawPolygon();
        } else {
            polyline.getPoints().clear();
        }
    }

    private void drawPolygon() {
        polyline.getPoints().clear();
        ArrayList<Point2D> polygon = getNoncrossedPolygon();
        polygon.forEach(p -> polyline.getPoints().addAll(p.getX(), p.getY()));
        Point2D first = polygon.get(0);
        polyline.getPoints().addAll(first.getX(), first.getY());
    }

    private ArrayList<Point2D> getNoncrossedPolygon() {
        ArrayList<Point2D> S = new ArrayList<>(points);
        Point2D p0 = S.stream().max(Comparator.comparing(Point2D::getY).thenComparing(Point2D::getX)).get();
        S.remove(p0);
        S.sort((p1, p2) -> {
            double d = (p1.getX() - p0.getX()) * (p2.getY() - p0.getY()) - (p2.getX() - p0.getX()) * (p1.getY() - p0.getY());
            if (d > 0) {
                return 1;
            } else if (d < 0) {
                return -1;
            } else {
                double d1 = p0.distance(p1);
                double d2 = p0.distance(p2);
                if (d2 > d1) {
                    return 1;
                } else if (d2 < d1) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        S.add(0, p0);
        return S;
    }
}

class InstructionPane extends Pane {
    public InstructionPane() {
        setLayoutX(10);
        setLayoutY(10);
        setStyle("-fx-border-color: gray; -fx-border-width: 2px");
        VBox vBox = new VBox(
                new Label("INSTRUCTION"),
                new Label("Add: Left Click"),
                new Label("Remove: Right Click")
        );
        vBox.setAlignment(Pos.BASELINE_LEFT);
        vBox.setPadding(new Insets(3));
        getChildren().add(vBox);
        setOnMouseClicked(Event::consume);
    }
}
