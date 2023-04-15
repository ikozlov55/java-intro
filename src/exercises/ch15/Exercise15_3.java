package exercises.ch15;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Exercise15_3 extends Application {

    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        BallPane pane = new BallPane();

        Button buttonLeft = new Button("Left");
        buttonLeft.setOnAction(e -> pane.moveLeft());
        Button buttonRight = new Button("Right");
        buttonRight.setOnAction(e -> pane.moveRight());
        Button buttonUp = new Button("Up");
        buttonUp.setOnAction(e -> pane.moveUp());
        Button buttonDown = new Button("Down");
        buttonDown.setOnAction(e -> pane.moveDown());

        HBox buttons = new HBox(buttonLeft, buttonRight, buttonUp, buttonDown);
        buttons.setAlignment(Pos.CENTER);
        buttons.setSpacing(10);

        BorderPane root = new BorderPane();
        root.setCenter(pane);
        root.setBottom(buttons);
        BorderPane.setAlignment(buttons, Pos.CENTER);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setHeight(200);
        stage.setWidth(300);
        stage.setTitle(getClass().getSimpleName());
        stage.show();
    }


}

class BallPane extends Pane {
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
        circle.setStroke(Color.BLACK);
        circle.setFill(Color.WHITE);
        getChildren().add(circle);
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