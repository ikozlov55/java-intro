package exercises.ch16;

import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

import java.text.NumberFormat;

public class Exercise16_08 extends Application {
    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(5));
        Label intersectLabel = new Label("Yes");
        HBox topBox = new HBox(5, new Label("Two Circles intersect? "), intersectLabel);
        topBox.setPadding(new Insets(5));
        topBox.setAlignment(Pos.BASELINE_CENTER);

        Pane canvas = new Pane();
        canvas.setPrefSize(300, 200);

        Circle circle1 = new Circle(70, 50, 50, Color.TRANSPARENT);
        circle1.setStroke(Color.BLACK);
        Circle circle2 = new Circle(160, 50, 50, Color.TRANSPARENT);
        circle2.setStroke(Color.BLACK);
        canvas.getChildren().addAll(circle1, circle2);
        EventHandler<MouseEvent> dragHandler = e -> {
            Circle circle = (Circle) e.getSource();
            circle.setCenterX(e.getX());
            circle.setCenterY(e.getY());
        };
        circle1.setOnMouseDragged(dragHandler);
        circle2.setOnMouseDragged(dragHandler);

        CircleInfoPane circleInfo1 = new CircleInfoPane("circle 1", circle1);
        CircleInfoPane circleInfo2 = new CircleInfoPane("circle 2", circle2);
        HBox circleInfoBox = new HBox(10, circleInfo1, circleInfo2);
        circleInfoBox.setPadding(new Insets(5));
        circleInfoBox.setAlignment(Pos.BASELINE_CENTER);
        InvalidationListener listener = e -> intersectLabel.setText(intersects(circle1, circle2) ? "Yes" : "No");
        circleInfo1.addListener(listener);
        circleInfo2.addListener(listener);

        Button redrawButton = new Button("Redraw Circles");
        redrawButton.setOnAction(e -> {
            try {
                start(stage);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        root.getChildren().addAll(topBox, canvas, circleInfoBox, redrawButton);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle(getClass().getSimpleName());
        stage.show();
    }

    private static boolean intersects(Circle c1, Circle c2) {
        double distance = Math.sqrt(Math.pow(c1.getCenterX() - c2.getCenterX(), 2) +
                Math.pow(c1.getCenterY() - c2.getCenterY(), 2));
        return distance < c1.getRadius() + c2.getRadius();
    }
}


class CircleInfoPane extends GridPane implements Observable {
    private String name;
    private TextField xField = new TextField();
    private TextField yField = new TextField();
    private TextField radiusField = new TextField();
    private Circle circle;

    public CircleInfoPane(String name, Circle circle) {
        this.name = name;
        this.circle = circle;
        draw();
    }

    @Override
    public void addListener(InvalidationListener listener) {
        circle.centerXProperty().addListener(listener);
        circle.centerYProperty().addListener(listener);
        circle.radiusProperty().addListener(listener);
    }

    @Override
    public void removeListener(InvalidationListener listener) {
        circle.centerXProperty().removeListener(listener);
        circle.centerYProperty().removeListener(listener);
        circle.radiusProperty().removeListener(listener);
    }

    private void draw() {
        setPadding(new Insets(5));
        xField.setPrefColumnCount(2);
        xField.setAlignment(Pos.BASELINE_RIGHT);
        yField.setPrefColumnCount(2);
        yField.setAlignment(Pos.BASELINE_RIGHT);
        radiusField.setPrefColumnCount(2);
        radiusField.setAlignment(Pos.BASELINE_RIGHT);
        setStyle("-fx-border-color: black");
        add(new Label(String.format("Enter %s info:", name)), 0, 0, 2, 1);
        add(new Label("Center x: "), 0, 1);
        add(xField, 1, 1);
        add(new Label("Center y: "), 0, 2);
        add(yField, 1, 2);
        add(new Label("Radius: "), 0, 3);
        add(radiusField, 1, 3);
        NumberStringConverter convertor = new NumberStringConverter(NumberFormat.getIntegerInstance());
        xField.textProperty().bindBidirectional(circle.centerXProperty(), convertor);
        yField.textProperty().bindBidirectional(circle.centerYProperty(), convertor);
        radiusField.textProperty().bindBidirectional(circle.radiusProperty(), convertor);
    }
}