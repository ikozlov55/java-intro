package exercises.ch14;

import javafx.application.Application;
import javafx.beans.binding.DoubleBinding;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Exercise14_14 extends Application {

    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        DoubleBinding Ax = stage.widthProperty().multiply(0.2);
        DoubleBinding Ay = stage.heightProperty().multiply(0.1);
        DoubleBinding Bx = stage.widthProperty().multiply(0.8);
        DoubleBinding By = Ay;
        DoubleBinding Cx = stage.widthProperty().multiply(0.1);
        DoubleBinding Cy = stage.heightProperty().multiply(0.2);
        DoubleBinding Dx = stage.widthProperty().multiply(0.7);
        DoubleBinding Dy = Cy;
        DoubleBinding Ex = Ax;
        DoubleBinding Ey = stage.heightProperty().multiply(0.7);
        DoubleBinding Fx = Bx;
        DoubleBinding Fy = Ey;
        DoubleBinding Gx = Cx;
        DoubleBinding Gy = stage.heightProperty().multiply(0.8);
        DoubleBinding Hx = Dx;
        DoubleBinding Hy = Gy;


        Group root = new Group(
                makeLine(Ax, Ay, Bx, By),
                makeLine(Cx, Cy, Dx, Dy),
                makeLine(Ex, Ey, Fx, Fy),
                makeLine(Gx, Gy, Hx, Hy),
                makeLine(Ax, Ay, Ex, Ey),
                makeLine(Bx, By, Fx, Fy),
                makeLine(Cx, Cy, Gx, Gy),
                makeLine(Dx, Dy, Hx, Hy),
                makeLine(Ax, Ay, Cx, Cy),
                makeLine(Bx, By, Dx, Dy),
                makeLine(Ex, Ey, Gx, Gy),
                makeLine(Fx, Fy, Hx, Hy)
        );

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setWidth(500);
        stage.setHeight(500);
        stage.setTitle(getClass().getSimpleName());
        stage.show();
    }

    private Line makeLine(DoubleBinding fromX, DoubleBinding fromY, DoubleBinding toX, DoubleBinding toY) {
        Line line = new Line();
        line.startXProperty().bind(fromX);
        line.startYProperty().bind(fromY);
        line.endXProperty().bind(toX);
        line.endYProperty().bind(toY);

        return line;
    }

}

