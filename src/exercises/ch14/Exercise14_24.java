package exercises.ch14;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.Scanner;

public class Exercise14_24 extends Application {

    private static final Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

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
        stage.setResizable(false);
        stage.setTitle(getClass().getSimpleName());

        System.out.print("Enter five points: ");
        double[] polygonPoints = new double[8];
        for (int i = 0; i < polygonPoints.length; i++) {
            polygonPoints[i] = scanner.nextDouble();
        }
        Polygon polygon = new Polygon(polygonPoints);
        polygon.setFill(Color.WHITE);
        polygon.setStroke(Color.BLACK);
        double x = scanner.nextDouble();
        double y = scanner.nextDouble();
        Circle circle = new Circle(x, y, 5);
        String text;
        if (polygon.contains(x, y)) {
            text = "The point is inside the polygon";
        } else {
            text = "The point is not inside the polygon";
        }
        Text label = new Text(stage.getWidth() * 0.2, stage.getHeight() * 0.8, text);

        root.getChildren().addAll(polygon, circle, label);
        stage.show();
    }

}




