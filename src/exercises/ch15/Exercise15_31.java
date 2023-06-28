package exercises.ch15;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Exercise15_31 extends Application {
    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        PendulumPane root = new PendulumPane();
        Timeline timeline = new Timeline(new KeyFrame(new Duration(10), e -> root.nextFrame()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        root.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case UP -> root.increaseSpeed();
                case DOWN -> root.decreaseSpeed();
                case S -> timeline.pause();
                case R -> timeline.play();
            }
        });

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle(getClass().getSimpleName());
        stage.setWidth(500);
        stage.setHeight(500);
        stage.show();
        root.requestFocus();
    }
}

class PendulumPane extends Pane {
    final double minAngle = 240;
    final double maxAngle = 300;
    private double angle = minAngle;
    private double increment = 0.5;

    private boolean reverse = false;

    public void nextFrame() {
        if (angle > maxAngle || angle < minAngle) {
            reverse = !reverse;
        }
        angle += reverse ? -increment : increment;
        draw();
    }

    public void increaseSpeed() {
        increment += 0.5;
    }

    public void decreaseSpeed() {
        if (increment > 0) increment -= 0.5;
    }

    private void draw() {
        getChildren().clear();
        Circle c1 = new Circle(getWidth() / 2, 20, 10, Color.BLACK);
        double r = getHeight() * 0.7;
        double x = c1.getCenterX() + r * Math.cos(Math.toRadians(angle));
        double y = c1.getCenterY() - r * Math.sin(Math.toRadians(angle));
        Circle c2 = new Circle(x, y, 20, Color.BLACK);
        Line line = new Line(c1.getCenterX(), c1.getCenterY(), c2.getCenterX(), c2.getCenterY());
        getChildren().addAll(c1, c2, line);
    }

    @Override
    protected void setWidth(double v) {
        super.setWidth(v);
        draw();
    }

    @Override
    protected void setHeight(double v) {
        super.setHeight(v);
        draw();
    }


}