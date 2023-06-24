package exercises.ch15;

import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Exercise15_27 extends Application {
    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Pane root = new Pane();
        Label label = new Label("Programming is fun");
        label.setFont(Font.font(40));

        Line line = new Line(-170, 80, 570, 80);
        line.setStroke(Color.BLACK);

        PathTransition animation = new PathTransition(new Duration(2000), line, label);
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
        root.setOnMousePressed(e -> animation.pause());
        root.setOnMouseReleased(e -> animation.play());

        root.getChildren().addAll(label);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle(getClass().getSimpleName());
        stage.setWidth(400);
        stage.setHeight(200);
        stage.show();
    }
}
