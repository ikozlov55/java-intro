package exercises.ch15;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Exercise15_13 extends Application {
    private final Label label = new Label();
    private final Rectangle rectangle = new Rectangle(100, 60, 100, 40);

    private final String textInside = "Mouse point is inside the rectangle";
    private final String textOutside = "Mouse point is outside the rectangle";

    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        rectangle.setStroke(Color.BLACK);
        rectangle.setFill(Color.WHITE);
        label.layoutXProperty().bind(stage.widthProperty().multiply(0.25));
        label.layoutYProperty().bind(stage.heightProperty().divide(2));

        Pane root = new Pane();
        root.getChildren().addAll(rectangle, label);
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
        String text = isInsideRectangle(e.getX(), e.getY()) ? textInside : textOutside;
        label.setText(text);
    }

    private boolean isInsideRectangle(double x, double y) {
        return rectangle.contains(new Point2D(x, y));
    }
}


