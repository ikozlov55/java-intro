package exercises.ch14;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Exercise14_13 extends Application {
    private double currentAngle = 0;

    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Group chart = new Group(
                makePie(20, Color.RED, stage),
                makePie(10, Color.BLUE, stage),
                makePie(30, Color.GREEN, stage),
                makePie(40, Color.ORANGE, stage)
        );
        BorderPane root = new BorderPane();

        Text title1 = new Text(300, 150, "Project -- 20%");
        title1.setFont(Font.font("", FontWeight.BOLD, 20));
        Text title2 = new Text(200, 20, "Quiz -- 10%");
        title2.setFont(Font.font("", FontWeight.BOLD, 20));
        Text title3 = new Text(50, 200, "Midterm -- 30%");
        title3.setFont(Font.font("", FontWeight.BOLD, 20));
        Text title4 = new Text(200, 350, "Final -- 40%");
        title4.setFont(Font.font("", FontWeight.BOLD, 20));
        root.setCenter(chart);
        root.getChildren().addAll(title1, title2, title3, title4);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setWidth(500);
        stage.setHeight(500);
        stage.setResizable(false);
        stage.setTitle(getClass().getSimpleName());
        stage.show();
    }

    private Arc makePie(int percentage, Color color, Stage stage) {
        Arc arc = new Arc();
        arc.centerXProperty().bind(stage.widthProperty().divide(2));
        arc.centerYProperty().bind(stage.heightProperty().divide(2));
        arc.radiusXProperty().bind(stage.widthProperty().multiply(0.4));
        arc.radiusYProperty().bind(arc.radiusXProperty());
        arc.setStartAngle(currentAngle);
        arc.setType(ArcType.ROUND);
        double length = 360 * (percentage / 100.0);
        arc.setLength(length);
        currentAngle += length;
        arc.setFill(color);

        return arc;
    }

}
