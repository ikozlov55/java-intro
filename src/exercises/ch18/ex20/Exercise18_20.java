package exercises.ch18.ex20;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Exercise18_20 extends Application {
    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root = new BorderPane();
        root.setCenter(new Circles());

        stage.setScene(new Scene(root, 400, 400));
        stage.setTitle(getClass().getSimpleName());
        stage.show();
    }
}

class Circles extends Pane {

    private void paint() {
        getChildren().clear();
        drawCircle(10);
    }

    private void drawCircle(double r) {
        if (r >= getWidth() / 2 - 20) return;
        Circle c = new Circle(r);
        c.centerXProperty().bind(widthProperty().divide(2));
        c.centerYProperty().bind(heightProperty().divide(2));
        c.setFill(Color.TRANSPARENT);
        c.setStroke(Color.BLACK);
        getChildren().add(c);
        drawCircle(r + 10);
    }

    @Override
    protected void setHeight(double v) {
        super.setHeight(v);
        paint();
    }

    @Override
    protected void setWidth(double v) {
        super.setWidth(v);
        paint();
    }
}