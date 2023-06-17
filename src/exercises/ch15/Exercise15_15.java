package exercises.ch15;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Exercise15_15 extends Application {
    private final Pane root = new Pane();

    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        root.setOnMouseClicked(this::addPoint);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        root.requestFocus();
        stage.setWidth(300);
        stage.setHeight(300);
        stage.setTitle(getClass().getSimpleName());
        stage.show();
    }

    private void addPoint(MouseEvent e) {
        if (e.getButton() != MouseButton.PRIMARY) return;
        Circle point = new Circle(e.getX(), e.getY(), 10);
        point.setStroke(Color.BLACK);
        point.setFill(Color.WHITE);
        point.setOnMouseClicked(this::removePoint);
        root.getChildren().add(point);
    }

    private void removePoint(MouseEvent e) {
        if (e.getButton() != MouseButton.SECONDARY) return;
        Circle point = (Circle) e.getSource();
        root.getChildren().remove(point);
    }
}
