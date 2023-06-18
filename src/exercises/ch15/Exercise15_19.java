package exercises.ch15;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Exercise15_19 extends Application {

    private final double r = 10;
    private final int maxCount = 20;
    private int count = 0;
    private long startMillis = 0;

    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Pane root = new Pane();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setWidth(400);
        stage.setHeight(400);
        stage.setTitle(getClass().getSimpleName());
        stage.show();
        drawCircle(root);
        startMillis = System.currentTimeMillis();
    }

    private void drawCircle(Pane pane) {
        count++;
        double x = (int) (Math.random() * (pane.getWidth() - r) + r);
        double y = (int) (Math.random() * (pane.getHeight() - r) + r);
        int R = (int) (Math.random() * 255);
        int G = (int) (Math.random() * 255);
        int B = (int) (Math.random() * 255);
        Paint paint = Color.rgb(R, G, B);
        Circle circle = new Circle(x, y, r);
        circle.setFill(paint);
        pane.getChildren().add(circle);
        circle.setOnMouseClicked(e -> {
            if (e.getButton() != MouseButton.PRIMARY) return;
            pane.getChildren().remove(circle);
            if (count == maxCount) {
                end(pane);
                return;
            }
            drawCircle(pane);
        });
    }

    private void end(Pane pane) {
        long spent = System.currentTimeMillis() - startMillis;
        Label label = new Label(String.format("Time spent is %d milliseconds", spent));
        label.setFont(Font.font(18));
        label.setLayoutY(10);
        label.setLayoutX(10);
        pane.getChildren().add(label);
    }

}
