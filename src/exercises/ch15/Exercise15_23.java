package exercises.ch15;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Exercise15_23 extends Application {

    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        StopSign stopSign = new StopSign();
        Scene scene = new Scene(stopSign);
        stage.setScene(scene);
        stage.setWidth(500);
        stage.setHeight(500);
        stage.setTitle(getClass().getSimpleName());
        stage.show();
    }

}

class StopSign extends StackPane {
    private void paint() {
        Polygon polygon = new Polygon();
        polygon.setFill(Color.RED);
        double centerX = getWidth() / 2;
        double centerY = getHeight() / 2;
        double radius = Math.min(getWidth(), getHeight()) / 2;
        ObservableList<Double> points = polygon.getPoints();

        for (int i = 0; i < 8; i++) {
            points.add(centerX + radius * Math.cos(2 * i * Math.PI / 8 - Math.PI / 8));
            points.add(centerY - radius * Math.sin(2 * i * Math.PI / 8 - Math.PI / 8));
        }

        Text text = new Text("STOP");
        text.setFill(Color.WHITE);
        text.setFont(Font.font("Times New Roman", FontWeight.NORMAL, radius * 0.65));

        getChildren().clear();
        getChildren().addAll(polygon, text);
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