package exercises.ch15;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Exercise15_12 extends Application {
    private final Label label = new Label();
    private final Circle circle = new Circle(100, 60, 50);

    private final String textInside = "Mouse point is inside the circle";
    private final String textOutside = "Mouse point is outside the circle";

    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        circle.setStroke(Color.BLACK);
        circle.setFill(null);
        label.layoutXProperty().bind(stage.widthProperty().multiply(0.25));
        label.layoutYProperty().bind(stage.heightProperty().divide(3));

        Pane root = new Pane();
        root.getChildren().addAll(circle, label);
        root.setOnMouseMoved(this::handleInput);

        Scene scene = new Scene(root);
        root.requestFocus();
        stage.setScene(scene);
        stage.setWidth(300);
        stage.setHeight(200);
        stage.setTitle(getClass().getSimpleName());
        stage.show();
    }

    private void handleInput(MouseEvent e) {
        String text = isInsideCircle(e.getX(), e.getY()) ? textInside : textOutside;
        label.setText(text);
    }

    private boolean isInsideCircle(double x, double y) {
        double distance = Math.sqrt(Math.pow(x - circle.getCenterX(), 2) + Math.pow(y - circle.getCenterY(), 2));
        return distance < circle.getRadius();
    }
}


