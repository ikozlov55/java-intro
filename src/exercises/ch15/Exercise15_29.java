package exercises.ch15;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Exercise15_29 extends Application {

    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Pane root = new Pane();
        root.setPrefSize(400, 200);
        Car car = new Car();
        CarMoveEventHandler handler = new CarMoveEventHandler(car, 0, 200, 400);
        root.getChildren().add(car);

        Timeline animation = new Timeline(new KeyFrame(new Duration(10), handler));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();

        root.setOnMousePressed(e -> animation.pause());
        root.setOnMouseReleased(e -> animation.play());
        root.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case UP -> handler.increaseSpeed();
                case DOWN -> handler.decreaseSpeed();
            }
        });

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle(getClass().getSimpleName());
        stage.setResizable(false);
        stage.show();
        root.requestFocus();
    }
}

class CarMoveEventHandler implements EventHandler<ActionEvent> {
    private final Car car;
    private double x;
    private double y;
    private double length;
    private double increment = 1;

    public CarMoveEventHandler(Car car, double x, double y, double length) {
        this.car = car;
        this.x = x;
        this.y = y;
        this.length = length;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        x += increment;
        if (x >= length - car.getWidth()) {
            x = 0;
        }
        car.draw(x, y);
    }

    public void increaseSpeed() {
        increment++;
    }

    public void decreaseSpeed() {
        if (increment > 0) {
            increment--;
        }
    }
}

class Car extends Group {
    private final double l = 20;

    public void draw(double x, double y) {
        getChildren().clear();
        Circle c1 = new Circle(x + l * 1.5, y - l / 2, l / 2, Color.BLACK);
        Circle c2 = new Circle(x + l * 3.5, y - l / 2, l / 2, Color.BLACK);
        Rectangle r = new Rectangle(x, y - 2 * l, l * 5, l);
        r.setFill(Color.GREEN);
        Polygon p = new Polygon(
                x + 2 * l, y - 3 * l,
                x + 3 * l, y - 3 * l,
                x + 4 * l, y - 2 * l,
                x + l, y - 2 * l
        );
        p.setFill(Color.RED);
        getChildren().addAll(c1, c2, r, p);
    }

    public double getWidth() {
        return 5 * l;
    }

    public double getHeight() {
        return 3 * l;
    }
}