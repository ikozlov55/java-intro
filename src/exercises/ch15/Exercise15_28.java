package exercises.ch15;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Exercise15_28 extends Application {

    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root = new BorderPane();
        initLayout(root);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle(getClass().getSimpleName());
        stage.setWidth(300);
        stage.setHeight(300);
        stage.show();
    }

    private void initLayout(BorderPane root) {
        Fan fan = new Fan();
        root.setCenter(fan);
        Button pause = new Button("Pause");
        pause.setOnAction(e -> fan.stop());
        Button resume = new Button("Resume");
        resume.setOnAction(e -> fan.start());
        Button reverse = new Button("Reverse");
        reverse.setOnAction(e -> fan.reverse());
        HBox buttons = new HBox(10, pause, resume, reverse);
        buttons.setAlignment(Pos.BASELINE_CENTER);
        buttons.setPadding(new Insets(10));
        root.setBottom(buttons);
    }
}

class Fan extends Pane {
    private Timeline animation;
    private boolean reversed = false;

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
            double angle = 90 * i + 90 - length / 2;
            Arc arc = new Arc(circle.getCenterX(), circle.getCenterY(), r, r, angle, length);
            arc.setFill(Color.RED);
            arc.setType(ArcType.ROUND);
            arcs.getChildren().add(arc);
        }
        getChildren().addAll(circle, arcs);

        animation = new Timeline();
        KeyValue value = new KeyValue(arcs.rotateProperty(), reversed ? -360 : 360);
        animation.getKeyFrames().add(new KeyFrame(new Duration(1000), value));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
    }

    public void start() {
        animation.play();
    }

    public void stop() {
        animation.pause();
    }

    public void reverse() {
        reversed = !reversed;
        draw();
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
