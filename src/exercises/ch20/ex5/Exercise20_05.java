package exercises.ch20.ex5;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Exercise20_05 extends Application {
    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        MultipleBallPane ballPane = new MultipleBallPane();
        ballPane.setStyle("−fx−border−color: yellow");

        Button btSuspend = new Button("Suspend");
        Button btResume = new Button("Resume");
        Button btAdd = new Button("+");
        Button btSubtract = new Button("−");
        HBox hBox = new HBox(10);
        hBox.getChildren().addAll(btSuspend, btResume, btAdd, btSubtract);
        hBox.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(5));

        // Add or remove a ball
        btAdd.setOnAction(e -> ballPane.add());
        btSubtract.setOnAction(e -> ballPane.subtract());
        // Pause and resume animation
        btSuspend.setOnAction(e -> ballPane.pause());
        btResume.setOnAction(e -> ballPane.play());

        // Use a scroll bar to control animation speed
        ScrollBar sbSpeed = new ScrollBar();
        sbSpeed.setMax(20);
        sbSpeed.setValue(10);
        ballPane.rateProperty().bind(sbSpeed.valueProperty());

        BorderPane pane = new BorderPane();
        pane.setCenter(ballPane);
        pane.setTop(sbSpeed);
        pane.setBottom(hBox);

        stage.setScene(new Scene(pane, 400, 400));
        stage.setTitle(getClass().getSimpleName());
        stage.show();
    }
}

class MultipleBallPane extends Pane {
    private final Timeline animation;

    public MultipleBallPane() {
        // Create an animation for moving the ball
        animation = new Timeline(new KeyFrame(Duration.millis(50), e -> moveBall()));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play(); // Start animation
    }

    public void add() {
        Color color = new Color(Math.random(), Math.random(), Math.random(), 0.5);
        Ball ball = new Ball(30, 30, 10, color);
        ball.setOnMouseClicked(e -> getChildren().remove(ball));
        getChildren().add(ball);
    }

    public void subtract() {
        if (!getChildren().isEmpty()) {
            getChildren().remove(getChildren().size() - 1);
        }
    }

    public void play() {
        animation.play();
    }

    public void pause() {
        animation.pause();
    }

    public void increaseSpeed() {
        animation.setRate(animation.getRate() + 0.1);
    }

    public void decreaseSpeed() {
        animation.setRate(animation.getRate() > 0 ? animation.getRate() - 0.1 : 0);
    }

    public DoubleProperty rateProperty() {
        return animation.rateProperty();
    }

    protected void moveBall() {
        for (Node node : this.getChildren()) {
            Ball ball = (Ball) node;
            // Check boundaries
            if (ball.getCenterX() < ball.getRadius() ||
                    ball.getCenterX() > getWidth() - ball.getRadius()) {
                ball.dx *= -1; // Change ball move direction
            }
            if (ball.getCenterY() < ball.getRadius() ||
                    ball.getCenterY() > getHeight() - ball.getRadius()) {
                ball.dy *= -1; // Change ball move direction
            }

            // Adjust ball position
            ball.setCenterX(ball.dx + ball.getCenterX());
            ball.setCenterY(ball.dy + ball.getCenterY());
            for (Node n : this.getChildren()) {
                Ball other = (Ball) n;
                if (other == ball) continue;
                if (ball.intersects(other)) {
                    combineBalls(ball, other);
                }
            }
        }
    }

    protected void combineBalls(Ball b1, Ball b2) {
        int i1 = getChildren().indexOf(b1);
        int i2 = getChildren().indexOf(b2);
        Ball toRemove = i1 > i2 ? b1 : b2;
        Ball toKeep = toRemove == b1 ? b2 : b1;
        toKeep.setRadius(toKeep.getRadius() + toRemove.getRadius());
        getChildren().remove(toRemove);
    }

    class Ball extends Circle {
        private double dx = 1, dy = 1;

        Ball(double x, double y, double radius, Color color) {
            super(x, y, radius);
            setFill(color);
        }

        public boolean intersects(Ball other) {
            Point2D p1 = new Point2D(getCenterX(), getCenterY());
            Point2D p2 = new Point2D(other.getCenterX(), other.getCenterY());
            return p1.distance(p2) < getRadius() + other.getRadius();
        }
    }
}


