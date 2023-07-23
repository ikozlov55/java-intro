package exercises.ch16;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Exercise16_25 extends Application {
    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root = new BorderPane();

        TextField carSpeedTF1 = new TextField("0");
        TextField carSpeedTF2 = new TextField("0");
        TextField carSpeedTF3 = new TextField("0");
        TextField carSpeedTF4 = new TextField("0");
        for (TextField tf : new TextField[]{carSpeedTF1, carSpeedTF2, carSpeedTF3, carSpeedTF4}) {
            tf.setPrefColumnCount(2);
        }
        HBox controls = new HBox(5,
                new Label("Car 1: "), carSpeedTF1,
                new Label("Car 2: "), carSpeedTF2,
                new Label("Car 3: "), carSpeedTF3,
                new Label("Car 4: "), carSpeedTF4
        );
        controls.setAlignment(Pos.BASELINE_CENTER);
        controls.setPadding(new Insets(5));
        root.setTop(controls);

        CarTrack carTrack1 = new CarTrack();
        CarTrack carTrack2 = new CarTrack();
        CarTrack carTrack3 = new CarTrack();
        CarTrack carTrack4 = new CarTrack();
        VBox tracks = new VBox(5, carTrack1, carTrack2, carTrack3, carTrack4);
        tracks.setSpacing(5);
        root.setCenter(tracks);

        carSpeedTF1.textProperty().addListener((ov, oldValue, newValue) -> {
            try {
                double speed = Double.parseDouble(newValue);
                if (speed > 100) {
                    speed = 100;
                    carSpeedTF1.setText(Integer.toString((int) speed));
                }
                carTrack1.setSpeed(speed / 10);
            } catch (NumberFormatException ignore) {
            }
        });
        carSpeedTF2.textProperty().addListener((ov, oldValue, newValue) -> {
            try {
                double speed = Double.parseDouble(newValue);
                if (speed > 100) {
                    speed = 100;
                    carSpeedTF2.setText(Integer.toString((int) speed));
                }
                carTrack2.setSpeed(speed / 10);
            } catch (NumberFormatException ignore) {
            }
        });
        carSpeedTF3.textProperty().addListener((ov, oldValue, newValue) -> {
            try {
                double speed = Double.parseDouble(newValue);
                if (speed > 100) {
                    speed = 100;
                    carSpeedTF3.setText(Integer.toString((int) speed));
                }
                carTrack3.setSpeed(speed / 10);
            } catch (NumberFormatException ignore) {
            }
        });
        carSpeedTF4.textProperty().addListener((ov, oldValue, newValue) -> {
            try {
                double speed = Double.parseDouble(newValue);
                if (speed > 100) {
                    speed = 100;
                    carSpeedTF4.setText(Integer.toString((int) speed));
                }
                carTrack4.setSpeed(speed / 10);
            } catch (NumberFormatException ignore) {
            }
        });

        Timeline animation = new Timeline(new KeyFrame(Duration.millis(10), e -> {
            carTrack1.move();
            carTrack2.move();
            carTrack3.move();
            carTrack4.move();
        }));
        animation.setCycleCount(Animation.INDEFINITE);
        animation.play();

        stage.setScene(new Scene(root));
        stage.setWidth(500);
        stage.setTitle(getClass().getSimpleName());
        stage.show();
    }
}

class CarTrack extends Pane {
    Car car = new Car();
    private double x;
    private double increment = 0;

    public CarTrack() {
        getChildren().add(car);
        setPadding(new Insets(10, 0, 0, 0));
        x = 0;
        car.draw(x, getHeight());
        setStyle("-fx-border-color: black");
    }

    public void move() {
        x += increment;
        if (x >= getWidth() - car.getWidth()) {
            x = 0;
        }
        car.draw(x, getHeight());
    }

    public void setSpeed(double speed) {
        increment = speed;
    }

    @Override
    protected void setHeight(double v) {
        super.setHeight(v);
        car.draw(x, getHeight());
    }

    @Override
    protected void setWidth(double v) {
        super.setWidth(v);
        car.draw(x, getHeight());
    }
}

class Car extends Group {
    private final double l = 10;

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