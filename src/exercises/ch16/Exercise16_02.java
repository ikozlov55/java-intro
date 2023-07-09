package exercises.ch16;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

public class Exercise16_02 extends Application {
    private Shape figure;
    private boolean isFilled = false;

    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root = new BorderPane();
        StackPane pane = new StackPane();
        pane.setStyle("-fx-border-color: gray");
        root.setCenter(pane);

        ToggleGroup group = new ToggleGroup();
        RadioButton circleButton = new RadioButton("Circle");
        circleButton.setToggleGroup(group);
        RadioButton rectangleButton = new RadioButton("Rectangle");
        rectangleButton.setToggleGroup(group);
        RadioButton ellipseButton = new RadioButton("Ellipse");
        ellipseButton.setToggleGroup(group);

        EventHandler<ActionEvent> handler = e -> {
            pane.getChildren().remove(figure);
            if (circleButton.isSelected()) {
                Circle circle = new Circle(60);
                circle.setFill(Color.WHITE);
                circle.setStroke(Color.BLACK);
                circle.centerXProperty().bind(pane.widthProperty().divide(2));
                circle.centerYProperty().bind(pane.heightProperty().divide(2));
                figure = circle;
            } else if (rectangleButton.isSelected()) {
                Rectangle rectangle = new Rectangle(100, 70, Color.WHITE);
                rectangle.setStroke(Color.BLACK);
                rectangle.setX(pane.getWidth() / 2 - rectangle.getWidth() / 2);
                rectangle.setY(pane.getHeight() / 2 - rectangle.getHeight() / 2);
                figure = rectangle;
            } else if (ellipseButton.isSelected()) {
                Ellipse ellipse = new Ellipse();
                ellipse.setFill(Color.WHITE);
                ellipse.setStroke(Color.BLACK);
                ellipse.setRadiusX(70);
                ellipse.setRadiusY(50);
                ellipse.centerXProperty().bind(pane.widthProperty().divide(2));
                ellipse.centerYProperty().bind(pane.heightProperty().divide(2));
                figure = ellipse;
            }
            if (isFilled) figure.setFill(Color.BLACK);
            pane.getChildren().add(figure);
        };
        circleButton.setOnAction(handler);
        rectangleButton.setOnAction(handler);
        ellipseButton.setOnAction(handler);

        CheckBox fillCheckBox = new CheckBox("Fill");
        fillCheckBox.setOnAction(e -> {
            isFilled = fillCheckBox.isSelected();
            if (figure == null) return;
            figure.setFill(isFilled ? Color.BLACK : Color.WHITE);
        });

        HBox toggles = new HBox(5);
        toggles.getChildren().addAll(circleButton, rectangleButton, ellipseButton, fillCheckBox);
        toggles.setAlignment(Pos.BASELINE_CENTER);
        toggles.setPadding(new Insets(5));
        root.setBottom(toggles);

        Scene scene = new Scene(root, 400, 200);
        stage.setScene(scene);
        stage.setTitle(getClass().getSimpleName());
        stage.show();
    }
    
}
