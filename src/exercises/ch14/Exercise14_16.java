package exercises.ch14;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Exercise14_16 extends Application {

    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        MyGrid grid = new MyGrid(3);
        Scene scene = new Scene(grid);
        stage.setScene(scene);
        stage.setWidth(500);
        stage.setHeight(500);
        stage.setTitle(getClass().getSimpleName());
        stage.show();
    }


}

class MyGrid extends Pane {
    private final int n;

    public MyGrid(int n) {
        super();
        this.n = n;
    }

    private void paint() {
        getChildren().clear();
        for (int i = 1; i < n; i++) {
            Line horizontalLine = new Line(
                    0, getHeight() * ((double) i / n),
                    getWidth(), getHeight() * ((double) i / n)
            );
            horizontalLine.setStroke(Color.BLUE);
            horizontalLine.setStrokeWidth(2);
            Line verticalLine = new Line(
                    getWidth() * ((double) i / n), 0,
                    getWidth() * ((double) i / n), getHeight()
            );
            verticalLine.setStroke(Color.RED);
            verticalLine.setStrokeWidth(2);
            getChildren().addAll(horizontalLine, verticalLine);
        }
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

