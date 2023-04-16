package exercises.ch15;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Exercise15_7 extends Application {

    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Circle circle = new Circle(50);
        circle.setFill(null);
        circle.setStroke(Color.BLACK);

        BorderPane root = new BorderPane();
        root.setCenter(circle);
        root.setOnMousePressed(e->circle.setFill(Color.BLACK));
        root.setOnMouseReleased(e->circle.setFill(Color.WHITE));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setWidth(300);
        stage.setHeight(300);
        stage.setTitle(getClass().getSimpleName());
        stage.show();
    }

}
