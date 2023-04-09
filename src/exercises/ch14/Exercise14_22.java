package exercises.ch14;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Exercise14_22 extends Application {

    private static final double radius = 15;

    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Pane root = new Pane();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setWidth(300);
        stage.setHeight(300);
        double x1 = Math.random() * stage.getWidth() * 0.9;
        double y1 = Math.random() * stage.getHeight() * 0.9;
        double x2 = Math.random() * stage.getWidth() * 0.9;
        double y2 = Math.random() * stage.getHeight() * 0.9;
        Circle circle1 = new Circle(x1, y1, radius);
        circle1.setStroke(Color.BLACK);
        circle1.setFill(Color.WHITE);
        Circle circle2 = new Circle(x2, y2, radius);
        circle2.setStroke(Color.BLACK);
        circle2.setFill(Color.WHITE);
        Line line = new Line(x1, y1, x2, y2);
        Text text1 = new Text(x1, y1, "1");
        text1.setFont(Font.font("", FontWeight.SEMI_BOLD, 15));
        Text text2 = new Text(x2, y2, "2");
        text2.setFont(Font.font("", FontWeight.SEMI_BOLD, 15));
        root.getChildren().addAll(line, circle1, circle2, text1, text2);
        stage.setResizable(false);
        stage.setTitle(getClass().getSimpleName());
        stage.show();
    }

}



