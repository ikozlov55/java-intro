package exercises.ch14;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import javafx.stage.Stage;

public class Exercise14_29 extends Application {
    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        BeanMachine machine = new BeanMachine(8);
        Scene scene = new Scene(machine);
        stage.setScene(scene);
        stage.setWidth(400);
        stage.setHeight(400);
        stage.setTitle(getClass().getSimpleName());
        stage.show();
    }
}

class BeanMachine extends Pane {

    private final int slots;

    public BeanMachine(int slots) {
        this.slots = slots;
    }

    private void paint() {
        getChildren().clear();
        double step = getHeight() * 0.75 / slots;
        double y = getHeight() * 0.15;
        double radius = 10;
        for (int i = 0; i < slots - 1; i++) {
            double x = getWidth() / 2 - radius * 2 * i;
            for (int j = 0; j <= i; j++) {
                Circle pin = new Circle(x, y, radius);
                pin.setFill(Color.GREEN);
                x += radius * 4;
                if (i == slots - 2) {
                    Line line = new Line(pin.getCenterX(), pin.getCenterY(), pin.getCenterX(), getHeight() - 30);
                    line.setStroke(Color.BLACK);
                    getChildren().add(line);
                }
                getChildren().add(pin);
            }
            y += step;
        }
        Polyline polyline = new Polyline(
                getWidth() / 2 - 20, 5,
                getWidth() / 2 - 20, 35,
                getWidth() / 2 - radius * 2 * slots, y - step + radius,
                getWidth() / 2 - radius * 2 * slots, getHeight() - 30,
                getWidth() / 2 + radius * 2 * slots, getHeight() - 30,
                getWidth() / 2 + radius * 2 * slots, y - step + radius,
                getWidth() / 2 + 20, 35,
                getWidth() / 2 + 20, 5
        );
        polyline.setStroke(Color.BLACK);

        getChildren().add(polyline);
    }

    @Override
    public void setWidth(double width) {
        super.setWidth(width);
        paint();
    }

    @Override
    public void setHeight(double height) {
        super.setHeight(height);
        paint();
    }
}
