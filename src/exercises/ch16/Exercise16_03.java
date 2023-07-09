package exercises.ch16;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Exercise16_03 extends Application {
    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root = new BorderPane();
        TrafficLight trafficLight = new TrafficLight();
        root.setCenter(trafficLight);

        ToggleGroup group = new ToggleGroup();
        RadioButton redButton = new RadioButton("Red");
        redButton.setToggleGroup(group);
        RadioButton yellowButton = new RadioButton("Yellow");
        yellowButton.setToggleGroup(group);
        RadioButton greenButton = new RadioButton("Green");
        greenButton.setToggleGroup(group);
        HBox buttons = new HBox(5, redButton, yellowButton, greenButton);
        buttons.setAlignment(Pos.BASELINE_CENTER);
        buttons.setPadding(new Insets(5));
        root.setBottom(buttons);

        EventHandler<ActionEvent> handler = e -> {
            if (redButton.isSelected()) {
                trafficLight.flashRed();
            } else if (yellowButton.isSelected()) {
                trafficLight.flashYellow();
            } else if (greenButton.isSelected()) {
                trafficLight.flashGreen();
            }
        };
        redButton.setOnAction(handler);
        yellowButton.setOnAction(handler);
        greenButton.setOnAction(handler);

        Scene scene = new Scene(root, 300, 250);
        stage.setScene(scene);
        stage.setTitle(getClass().getSimpleName());
        stage.show();
    }
}

class TrafficLight extends Pane {
    private Circle yellowLight = new Circle(20, Color.WHITE);
    private Circle redLight = new Circle(20, Color.WHITE);
    private Circle greenLight = new Circle(20, Color.WHITE);

    private void draw() {
        getChildren().clear();
        Rectangle rectangle = new Rectangle(50, 160, Color.WHITE);
        rectangle.setStroke(Color.BLACK);
        rectangle.xProperty().bind(widthProperty().divide(2).subtract(rectangle.getWidth() / 2));
        rectangle.yProperty().bind(heightProperty().divide(2).subtract(rectangle.getHeight() / 2));

        yellowLight.centerXProperty().bind(widthProperty().divide(2));
        yellowLight.centerYProperty().bind(heightProperty().divide(2));
        yellowLight.setStroke(Color.BLACK);

        redLight.setStroke(Color.BLACK);
        redLight.centerXProperty().bind(widthProperty().divide(2));
        redLight.centerYProperty().bind(yellowLight.centerYProperty().subtract(50));

        greenLight.centerXProperty().bind(widthProperty().divide(2));
        greenLight.centerYProperty().bind(yellowLight.centerYProperty().add(50));
        greenLight.setStroke(Color.BLACK);

        getChildren().addAll(rectangle, redLight, yellowLight, greenLight);
    }

    public void flashRed() {
        redLight.setFill(Color.RED);
        yellowLight.setFill(Color.WHITE);
        greenLight.setFill(Color.WHITE);
    }

    public void flashYellow() {
        redLight.setFill(Color.WHITE);
        yellowLight.setFill(Color.YELLOW);
        greenLight.setFill(Color.WHITE);
    }

    public void flashGreen() {
        redLight.setFill(Color.WHITE);
        yellowLight.setFill(Color.WHITE);
        greenLight.setFill(Color.GREEN);
    }

    @Override
    protected void setWidth(double v) {
        super.setWidth(v);
        draw();
    }

    @Override
    protected void setHeight(double v) {
        super.setHeight(v);
        draw();
    }
}