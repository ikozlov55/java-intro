package exercises.ch22.ex17;

import exercises.ch22.ex07.ClosestPair;
import exercises.ch22.ex07.Pair;
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

public class Exercise22_17 extends Application {
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
        if (points.size() >= 2) {
            showClosestPair();
        }
    }

    private void removePoint(MouseEvent e) {
        if (e.getButton() != MouseButton.SECONDARY) return;
        Circle circle = (Circle) e.getSource();
        root.getChildren().remove(circle);
        points.remove(new Point2D(circle.getCenterX(), circle.getCenterY()));
        if (points.size() >= 2) {
            showClosestPair();
        } else {
            polyline.getPoints().clear();
        }
    }

    private void showClosestPair() {
        polyline.getPoints().clear();
        Pair pair = ClosestPair.getClosestPair(points.toArray(Point2D[]::new));
        Point2D p1 = pair.getP1();
        Point2D p2 = pair.getP2();
        polyline.getPoints().addAll(p1.getX(), p1.getY(), p2.getX(), p2.getY());
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
