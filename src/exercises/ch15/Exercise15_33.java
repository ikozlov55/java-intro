package exercises.ch15;

import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Exercise15_33 extends Application {
    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        BeanMachine machine = new BeanMachine(8);
        Scene scene = new Scene(machine);
        stage.setScene(scene);
        stage.setWidth(400);
        stage.setHeight(500);
        stage.setTitle(getClass().getSimpleName());
        stage.show();
        Timeline timeline = new Timeline(
                new KeyFrame(new Duration(500), e -> machine.dropBall())
        );
        timeline.setCycleCount(10);
        timeline.play();
    }
}

class BeanMachine extends Pane {

    private final int numberOfSlots;
    private final int[] slots;

    public BeanMachine(int numberOfSlots) {
        this.numberOfSlots = numberOfSlots;
        this.slots = new int[numberOfSlots];
    }

    private void paint() {
        getChildren().clear();
        double radius = 10;
        double step = radius * 4;
        double y = radius * 7;

        for (int i = 0; i < numberOfSlots - 1; i++) {
            double x = getWidth() / 2 - radius * 2 * i;
            for (int j = 0; j <= i; j++) {
                Circle pin = new Circle(x, y, radius);
                pin.setFill(Color.GREEN);
                x += radius * 4;
                if (i == numberOfSlots - 2) {
                    Line line = new Line(pin.getCenterX(), pin.getCenterY(), pin.getCenterX(), getHeight() - step);
                    line.setStroke(Color.BLACK);
                    getChildren().add(line);
                }
                getChildren().add(pin);
            }
            y += step;
        }
        Polyline polyline = new Polyline(getWidth() / 2 - radius * 2, 5, getWidth() / 2 - radius * 2, step, getWidth() / 2 - radius * 2 * numberOfSlots, y - step + radius, getWidth() / 2 - radius * 2 * numberOfSlots, getHeight() - step, getWidth() / 2 + radius * 2 * numberOfSlots, getHeight() - step, getWidth() / 2 + radius * 2 * numberOfSlots, y - step + radius, getWidth() / 2 + radius * 2, step, getWidth() / 2 + radius * 2, 5);
        polyline.setStroke(Color.BLACK);
        getChildren().add(polyline);
    }

    public void dropBall() {
        double radius = 10;
        double step = radius * 4;
        Polyline path = new Polyline();
        path.setStrokeWidth(0);
        path.getPoints().addAll(getWidth() / 2, -radius * 2, getWidth() / 2, step);
        double x = getWidth() / 2;
        double y = step;
        int n = 0;
        for (int i = 0; i < numberOfSlots - 1; i++) {
            int direction;
            if (Math.random() > 0.5) {
                direction = 1;
                n++;
            } else {
                direction = -1;
            }
            x += direction * radius * 2;
            y += radius * 2;
            path.getPoints().addAll(x, y);
            y += radius * 2;
            path.getPoints().addAll(x, y);
        }
        path.getPoints().addAll(x, getHeight() - step - radius - slots[n] * radius * 2);
        slots[n]++;

        Circle ball = new Circle(-radius * 2, 0, radius, Color.RED);
        PathTransition animation = new PathTransition(new Duration(5000), path, ball);
        animation.setCycleCount(1);
        animation.play();
        getChildren().addAll(path, ball);
    }

    @Override
    public void setWidth(double width) {
        super.setWidth(width);
        paint();
    }

    @Override
    public void setHeight(double height) {
        super.setHeight(height);
        paint();
    }
}
