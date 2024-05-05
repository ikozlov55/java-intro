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
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Exercise15_16 extends Application {
    private final Circle c1 = new Circle(40, 40, 15);
    private final Circle c2 = new Circle(120, 150, 15);
    private final Label label = new Label();

    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Pane root = new Pane();
        c1.setStroke(Color.BLACK);
        c1.setFill(Color.WHITE);
        c1.setOnMouseDragged(this::handleMouseDrag);
        c2.setStroke(Color.BLACK);
        c2.setFill(Color.WHITE);
        c2.setOnMouseDragged(this::handleMouseDrag);

        Line line = new Line();
        line.startXProperty().bind(c1.centerXProperty());
        line.startYProperty().bind(c1.centerYProperty());
        line.endXProperty().bind(c2.centerXProperty());
        line.endYProperty().bind(c2.centerYProperty());

        label.layoutXProperty().bind(line.startXProperty().add(line.endXProperty()).divide(2));
        label.layoutYProperty().bind(line.startYProperty().add(line.endYProperty()).divide(2).subtract(18));
        label.setText(Integer.toString(distance()));
        label.setFont(Font.font("", FontWeight.BOLD, 16));

        root.getChildren().addAll(line, c1, c2, label);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle(getClass().getSimpleName());
        stage.setWidth(400);
        stage.setHeight(300);
        stage.show();
    }

    private void updateLabel() {
        label.setText(Integer.toString(distance()));
    }

    private int distance() {
        return (int) Math.sqrt(Math.pow(c1.getCenterX() - c2.getCenterX(), 2) +
                Math.pow(c1.getCenterY() - c2.getCenterY(), 2));
    }

    private void handleMouseDrag(MouseEvent e) {
        Circle c = (Circle) e.getSource();
        c.setCenterX(e.getX());
        c.setCenterY(e.getY());
        updateLabel();
    }
}
