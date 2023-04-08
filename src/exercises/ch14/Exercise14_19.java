package exercises.ch14;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Exercise14_19 extends Application {

    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        SinCosPlot plot = new SinCosPlot();
        Scene scene = new Scene(plot);
        stage.setScene(scene);
        stage.setWidth(500);
        stage.setHeight(250);
        stage.setResizable(false);
        stage.setTitle(getClass().getSimpleName());
        stage.show();
    }


}

class SinCosPlot extends Pane {

    private void paint() {
        getChildren().clear();
        Line axisX = new Line(
                10, getHeight() / 2,
                getWidth() - 10, getHeight() / 2
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
        getChildren().addAll(axisX, markX, arrowXTop, arrowXBottom);

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
        getChildren().addAll(axisY, markY, arrowYLeft, arrowYRight);

        Polyline sinPolyline = new Polyline();
        ObservableList<Double> sinList = sinPolyline.getPoints();
        double scaleFactor = 50;
        for (int x = -170; x <= 170; x++) {
            sinList.add(x + getWidth() / 2);
            sinList.add(100 - scaleFactor * Math.sin((x / 100.0) * 2 * Math.PI));
        }
        sinPolyline.setStroke(Color.RED);
        sinPolyline.setLayoutY(10);

        Polyline cosPolyline = new Polyline();
        ObservableList<Double> cosList = cosPolyline.getPoints();
        for (int x = -170; x <= 170; x++) {
            cosList.add(x + getWidth() / 2);
            cosList.add(100 - scaleFactor * Math.cos((x / 100.0) * 2 * Math.PI));
        }
        cosPolyline.setStroke(Color.BLUE);
        cosPolyline.setLayoutY(10);

        getChildren().addAll(sinPolyline, cosPolyline);

        String[] piMarks = {"-2 \u03c0", "\u03c0", "0", "\u03c0", "2 \u03c0"};
        for (int i = 0; i < piMarks.length; i++) {
            Text mark = new Text(getWidth() * ((i + 1.0) / piMarks.length), getHeight() / 2 + 20, piMarks[i]);
            mark.setFont(Font.font(20));
            getChildren().add(mark);
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



