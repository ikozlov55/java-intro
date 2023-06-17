package exercises.ch15;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Exercise15_14 extends Application {
    private final Label label = new Label();
    private final Polygon polygon = new Polygon(40, 20, 70, 40, 60, 80, 45, 45, 20, 60);

    private final String textInside = "Mouse point is inside the polygon";
    private final String textOutside = "Mouse point is outside the polygon";

    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Pane root = new Pane();

        polygon.setStroke(Color.BLACK);
        polygon.setFill(Color.WHITE);

        label.layoutXProperty().bind(stage.widthProperty().multiply(0.15));
        label.layoutYProperty().bind(stage.heightProperty().divide(2));

        root.setOnMouseMoved(this::handleInput);

        root.getChildren().addAll(polygon, label);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setWidth(300);
        stage.setHeight(200);
        stage.setTitle(getClass().getSimpleName());
        stage.show();
    }

    private void handleInput(MouseEvent e) {
        String text = polygon.contains(e.getX(), e.getY()) ? textInside : textOutside;
        label.setText(text);
    }

}
