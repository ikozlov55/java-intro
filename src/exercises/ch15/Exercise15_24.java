package exercises.ch15;

import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Exercise15_24 extends Application {
    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        PendulumSwingPane root = new PendulumSwingPane();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setWidth(400);
        stage.setHeight(400);
        stage.setTitle(getClass().getSimpleName());
        stage.show();
    }
}

class PendulumSwingPane extends Pane {
    private void paint() {
        double rx = getWidth() / 3;
        double ry = rx / 2;
        double x = getWidth() / 2;
        double y = getHeight() / 2 - rx / 2;
        double length = 140;
        double angle = 180 + (180 - length) / 2;
        Arc arc = new Arc(x, y, rx, ry, angle, length);
        arc.setStroke(Color.BLACK);
        arc.setFill(null);
        arc.setType(ArcType.OPEN);

        Circle circle = new Circle(15);
        circle.setFill(Color.ORANGE);

        PathTransition animation = new PathTransition(new Duration(1000), arc, circle);
        animation.setAutoReverse(true);
        animation.setCycleCount(Animation.INDEFINITE);
        animation.play();
        setOnMousePressed(e -> animation.stop());
        setOnMouseReleased(e -> animation.play());

        getChildren().clear();
        getChildren().addAll(arc, circle);
    }

    @Override
    protected void setHeight(double v) {
        super.setHeight(v);
        paint();
    }

    @Override
    protected void setWidth(double v) {
        super.setWidth(v);
        paint();
    }
}
