package exercises.ch14;

import exercises.ch10.ex13.MyRectangle2D;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.Scanner;

public class Exercise14_23 extends Application {

    private static final Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        System.out.println("Enter center coordinates, width, and height оf rectangular 1:");
        double x1 = scanner.nextDouble();
        double y1 = scanner.nextDouble();
        double width1 = scanner.nextDouble();
        double height1 = scanner.nextDouble();
        MyRectangle2D r1 = new MyRectangle2D(x1, y1, width1, height1);
        System.out.println("Enter center coordinates, width, and height оf rectangular 2:");
        double x2 = scanner.nextDouble();
        double y2 = scanner.nextDouble();
        double width2 = scanner.nextDouble();
        double height2 = scanner.nextDouble();
        MyRectangle2D r2 = new MyRectangle2D(x2, y2, width2, height2);
        String text = "";
        if (r1.contains(r2) || r2.contains(r1)) {
            text = "One rectangle is contained in another";
        } else if (r1.overlaps(r2) || r2.overlaps(r1)) {
            text = "The rectangles overlap";
        } else {
            text = "The rectangles do not overlap";
        }

        Pane root = new Pane();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setWidth(300);
        stage.setHeight(300);
        Rectangle rec1 = new Rectangle(x1, y1, width1, height1);
        rec1.setStroke(Color.BLACK);
        rec1.setFill(null);
        Rectangle rec2 = new Rectangle(x2, y2, width2, height2);
        rec2.setStroke(Color.BLACK);
        rec2.setFill(null);
        Text label = new Text(stage.getWidth() * 0.2, stage.getHeight() * 0.8, text);

        root.getChildren().addAll(rec1, rec2, label);
        stage.setResizable(false);
        stage.setTitle(getClass().getSimpleName());
        stage.show();
    }

}



