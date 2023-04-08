package exercises.ch14;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Exercise14_21 extends Application {

    private static final double radius = 15;

    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Pane root = new Pane();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setWidth(500);
        stage.setHeight(500);
        double x1 = Math.random() * stage.getWidth();
        double y1 = Math.random() * stage.getHeight();
        double x2 = Math.random() * stage.getWidth();
        double y2 = Math.random() * stage.getHeight();
        Circle circle1 = new Circle(x1, y1, radius);
        Circle circle2 = new Circle(x2, y2, radius);
        Line line = new Line(x1, y1, x2, y2);
        double distance = Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
        Text text = new Text((x1 + x2) / 2, (y1 + y2) / 2, Double.toString(distance));
        text.setFont(Font.font("", FontWeight.SEMI_BOLD, 15));
        root.getChildren().addAll(circle1, circle2, line, text);
        stage.setResizable(false);
        stage.setTitle(getClass().getSimpleName());
        stage.show();
    }

}



