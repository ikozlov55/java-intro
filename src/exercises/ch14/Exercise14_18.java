package exercises.ch14;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Exercise14_18 extends Application {

    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Plot plot = new Plot();
        Scene scene = new Scene(plot);
        stage.setScene(scene);
        stage.setWidth(800);
        stage.setHeight(488);
        stage.setResizable(false);
        stage.setTitle(getClass().getSimpleName());
        stage.show();
    }


}

class Plot extends Pane {

    private void paint() {
        getChildren().clear();
        Line axisX = new Line(
                10, getHeight() * 0.9,
                getWidth() - 10, getHeight() * 0.9
        );
        Text markX = new Text(axisX.getEndX() - 20, axisX.getEndY() - 20, "X");
        markX.setFont(Font.font(20));
        Line arrowXTop = new Line(
                axisX.getEndX(), axisX.getEndY(),
                axisX.getEndX() - 20, axisX.getEndY() - 10
        );
        Line arrowXBottom = new Line(
                axisX.getEndX(), axisX.getEndY(),
                axisX.getEndX() - 20, axisX.getEndY() + 10
        );
        Line axisY = new Line(
                getWidth() / 2, getHeight(),
                getWidth() / 2, 0
        );
        Text markY = new Text(axisY.getEndX() + 20, axisY.getEndY() + 20, "Y");
        markY.setFont(Font.font(20));
        Line arrowYLeft = new Line(
                axisY.getEndX(), axisY.getEndY(),
                axisY.getEndX() - 10, axisY.getEndY() + 20
        );
        Line arrowYRight = new Line(
                axisY.getEndX(), axisY.getEndY(),
                axisY.getEndX() + 10, axisY.getEndY() + 20
        );
        Polyline polyline = new Polyline();
        ObservableList<Double> list = polyline.getPoints();
        double scaleFactor = 0.0125;
        int plot = (int) (getHeight()-90) / 2;
        for (int x = -plot; x <= plot; x++) {
            list.add(x + getWidth() / 2);
            list.add(scaleFactor * x * x);
        }


        polyline.setRotate(180);
        getChildren().addAll(axisX, axisY, markX, markY, arrowXTop, arrowXBottom, arrowYLeft, arrowYRight, polyline);
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



