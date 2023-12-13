package exercises.ch22.ex13;

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

public class Exercise22_13 extends Application {
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
            drawConvexHull();
        }
    }

    private void removePoint(MouseEvent e) {
        if (e.getButton() != MouseButton.SECONDARY) return;
        Circle circle = (Circle) e.getSource();
        root.getChildren().remove(circle);
        points.remove(new Point2D(circle.getCenterX(), circle.getCenterY()));
        if (points.size() > 2) {
            drawConvexHull();
        } else {
            polyline.getPoints().clear();
        }
    }

    private void drawConvexHull() {
        polyline.getPoints().clear();
        ArrayList<Point2D> convexHull = getConvexHullGiftWrapping();
        convexHull.forEach(p -> polyline.getPoints().addAll(p.getX(), p.getY()));
        Point2D first = convexHull.get(0);
        polyline.getPoints().addAll(first.getX(), first.getY());
    }

    private ArrayList<Point2D> getConvexHullGiftWrapping() {
        ArrayList<Point2D> H = new ArrayList<>();
        ArrayList<Point2D> S = new ArrayList<>(points);
        Point2D h0 = S.stream().max(Comparator.comparing(Point2D::getY).thenComparing(Point2D::getX)).get();
        H.add(h0);
        Point2D t0 = h0;
        while (true) {
            Point2D t1 = S.get(0);
            for (Point2D p : S) {
                double d = (t1.getX() - t0.getX()) * (p.getY() - t0.getY()) - (p.getX() - t0.getX()) * (t1.getY() - t0.getY());
                if (d < 0) {
                    t1 = p;
                } else if ((d == 0 || t1.equals(t0)) && t0.distance(p) > t0.distance(t1)) {
                    t1 = p;
                }
            }
            if (t1.equals(h0)) {
                return H;
            } else {
                H.add(t1);
                t0 = t1;
            }
        }
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
