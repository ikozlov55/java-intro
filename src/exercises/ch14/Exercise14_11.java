package exercises.ch14;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;

public class Exercise14_11 extends Application {
    public static double width = 400;
    public static double height = 400;
    public static double centerX = width / 2;
    public static double centerY = height / 2;

    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Circle head = new Circle();
        head.centerXProperty().bind(stage.widthProperty().divide(2));
        head.centerYProperty().bind(stage.heightProperty().divide(2));
        head.radiusProperty().bind(stage.heightProperty().multiply(0.45));
        head.setFill(null);
        head.setStroke(Color.BLACK);

        Polygon nose = new Polygon(
                centerX, centerY - 40,
                centerX - 40, centerY + 40,
                centerX + 40, centerY + 40
        );
        nose.setFill(null);
        nose.setStroke(Color.BLACK);

        Arc smile = new Arc(
                centerX, centerY + 80,
                width / 4, height / 10,
                180, 180
        );
        smile.setType(ArcType.OPEN);
        smile.setFill(null);
        smile.setStroke(Color.BLACK);

        Ellipse leftEye = new Ellipse(
                width * 0.33, height / 3,
                50, 30
        );
        leftEye.setFill(null);
        leftEye.setStroke(Color.BLACK);

        Ellipse rightEye = new Ellipse(
                width * 0.66, height / 3,
                50, 30
        );
        rightEye.setFill(null);
        rightEye.setStroke(Color.BLACK);

        Circle leftPupil = new Circle(leftEye.getCenterX(), leftEye.getCenterY(), 20);
        leftPupil.setFill(Color.BLACK);

        Circle rightPupil = new Circle(rightEye.getCenterX(), rightEye.getCenterY(), 20);
        rightPupil.setFill(Color.BLACK);

        Group group = new Group(head, nose, smile, leftEye, rightEye, leftPupil, rightPupil);
        BorderPane root = new BorderPane();
        root.setCenter(group);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setWidth(width);
        stage.setHeight(height);
        stage.setResizable(true);
        stage.setTitle(getClass().getSimpleName());
        stage.show();
    }

}
