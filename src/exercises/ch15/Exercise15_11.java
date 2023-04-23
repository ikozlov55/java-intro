package exercises.ch15;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Exercise15_11 extends Application {

    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        MovableCirclePane pane = new MovableCirclePane();
        Scene scene = new Scene(pane);
        pane.requestFocus();
        stage.setScene(scene);
        stage.setHeight(300);
        stage.setWidth(300);
        stage.setTitle(getClass().getSimpleName());
        stage.show();
    }


}

class MovableCirclePane extends Pane {
    private double x;
    private double y;
    private final double radius = 15;
    private final double step = 10;
    private boolean init = true;


    private void paint() {
        getChildren().clear();
        if (init) {
            x = getWidth() / 2;
            y = getHeight() / 2;
        }
        Circle circle = new Circle(x, y, radius);
        circle.setFill(Color.RED);
        getChildren().add(circle);
        setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case UP -> moveUp();
                case DOWN -> moveDown();
                case LEFT -> moveLeft();
                case RIGHT -> moveRight();
            }
        });
    }

    public void moveUp() {
        init = false;
        y = y - radius - step < 0 ? radius + 1 : y - step;
        paint();
    }

    public void moveDown() {
        init = false;
        y = y + radius + step > getHeight() ? getHeight() - radius - 1 : y + step;
        paint();
    }

    public void moveLeft() {
        init = false;
        x = x - radius - step < 0 ? radius + 1 : x - step;
        paint();
    }

    public void moveRight() {
        init = false;
        x = x + radius + step > getWidth() ? getWidth() - radius - 1 : x + step;
        paint();
    }

    @Override
    protected void setWidth(double v) {
        super.setWidth(v);
        paint();
    }

    @Override
    protected void setHeight(double v) {
        super.setHeight(v);
        paint();
    }
}