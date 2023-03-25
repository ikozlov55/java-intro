package exercises.ch14;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Exercise14_9 extends Application {

    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setVgap(20);
        grid.setHgap(20);
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                Group fan = new Group();
                Circle circle = new Circle(50);
                circle.setFill(Color.WHITE);
                circle.setStroke(Color.BLACK);
                circle.setStrokeWidth(2);
                fan.getChildren().add(circle);
                for (int k = 0; k < 4; k++) {
                    Arc arc = new Arc(
                            circle.getCenterX(),
                            circle.getCenterY(),
                            circle.getRadius() - 5,
                            circle.getRadius() - 5,
                            90 * k + 25,
                            40
                    );
                    arc.setFill(Color.RED);
                    arc.setType(ArcType.ROUND);
                    fan.getChildren().add(arc);
                }
                grid.add(fan, i, j);
            }
        }
        Scene scene = new Scene(grid);
        stage.setScene(scene);
        stage.setTitle("Exercise14_09");
        stage.show();
    }

}
