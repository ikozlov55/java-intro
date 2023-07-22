package exercises.ch16;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Exercise16_18 extends Application {

    private final Button pauseButton = new Button("Pause");
    private final Button resumeButton = new Button("Resume");
    private final Button reverseButton = new Button("Reverse");
    private final Slider speedSlider = new Slider(0, 20, 10);
    private final Fan fan = new Fan();

    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root = new BorderPane();
        root.setCenter(fan);

        HBox buttons = new HBox(10, pauseButton, resumeButton, reverseButton);
        buttons.setPadding(new Insets(5));
        buttons.setAlignment(Pos.BASELINE_CENTER);
        root.setTop(buttons);

        speedSlider.setPadding(new Insets(5, 50, 5, 50));
        root.setBottom(speedSlider);

        Timeline animation = new Timeline(
                new KeyFrame(Duration.millis(100), e -> fan.move())
        );
        animation.setCycleCount(Animation.INDEFINITE);
        animation.rateProperty().bind(speedSlider.valueProperty());
        animation.play();

        pauseButton.setOnAction(e -> animation.stop());
        resumeButton.setOnAction(e -> animation.play());
        reverseButton.setOnAction(e -> fan.reverse());

        stage.setScene(new Scene(root, 300, 300));
        stage.setTitle(getClass().getSimpleName());
        stage.show();
    }
}

class Fan extends Pane {
    private double startAngle = 90;
    private double increment = 5;

    public void move() {
        startAngle += increment;
        draw();
    }

    private void draw() {
        getChildren().clear();
        Circle circle = new Circle((getWidth() + getHeight()) / 5);
        circle.setStroke(Color.BLACK);
        circle.setStrokeWidth(2);
        circle.setFill(Color.WHITE);
        circle.setCenterX(getWidth() / 2);
        circle.setCenterY(getHeight() / 2);

        Group arcs = new Group();
        for (int i = 0; i < 4; i++) {
            double r = circle.getRadius() - 5;
            double length = 35;
            double angle = 90 * i + startAngle - length / 2;
            Arc arc = new Arc(circle.getCenterX(), circle.getCenterY(), r, r, angle, length);
            arc.setFill(Color.RED);
            arc.setType(ArcType.ROUND);
            arcs.getChildren().add(arc);
        }
        getChildren().addAll(circle, arcs);
    }

    public void reverse() {
        increment = -increment;
    }

    @Override
    protected void setHeight(double v) {
        super.setHeight(v);
        draw();
    }

    @Override
    protected void setWidth(double v) {
        super.setWidth(v);
        draw();
    }

}
