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
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

import java.text.NumberFormat;

public class Exercise16_09 extends Application {
    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(5));
        Label intersectLabel = new Label("Yes");
        HBox topBox = new HBox(5, new Label("Two rectangles intersect? "), intersectLabel);
        topBox.setPadding(new Insets(5));
        topBox.setAlignment(Pos.BASELINE_CENTER);

        Pane canvas = new Pane();
        canvas.setPrefSize(300, 200);
        Rectangle rectangle1 = new Rectangle(50, 10, 40, 50);
        rectangle1.setFill(Color.TRANSPARENT);
        rectangle1.setStroke(Color.BLACK);
        Rectangle rectangle2 = new Rectangle(160, 50, 50, 20);
        rectangle2.setFill(Color.TRANSPARENT);
        rectangle2.setStroke(Color.BLACK);
        canvas.getChildren().addAll(rectangle1, rectangle2);
        EventHandler<MouseEvent> dragHandler = e -> {
            Rectangle rectangle = (Rectangle) e.getSource();
            rectangle.setX(e.getX() - rectangle.getWidth() / 2);
            rectangle.setY(e.getY() - rectangle.getHeight() / 2);
        };
        rectangle1.setOnMouseDragged(dragHandler);
        rectangle2.setOnMouseDragged(dragHandler);

        RectangleInfoPane rectangleInfo1 = new RectangleInfoPane("rectangle 1", rectangle1);
        RectangleInfoPane rectangleInfo2 = new RectangleInfoPane("rectangle 2", rectangle2);
        HBox rectangleInfoBox = new HBox(10, rectangleInfo1, rectangleInfo2);
        rectangleInfoBox.setPadding(new Insets(5));
        rectangleInfoBox.setAlignment(Pos.BASELINE_CENTER);
        InvalidationListener listener = e -> intersectLabel.setText(rectangle1.intersects(rectangle2.getLayoutBounds()) ? "Yes" : "No");
        rectangleInfo1.addListener(listener);
        rectangleInfo2.addListener(listener);

        Button redrawButton = new Button("Redraw Rectangles");
        redrawButton.setOnAction(e -> {
            try {
                start(stage);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        root.getChildren().addAll(topBox, canvas, rectangleInfoBox, redrawButton);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle(getClass().getSimpleName());
        stage.show();
    }

}


class RectangleInfoPane extends GridPane implements Observable {
    private String name;
    private TextField xField = new TextField();
    private TextField yField = new TextField();
    private TextField widthField = new TextField();
    private TextField heightField = new TextField();
    private Rectangle rectangle;

    public RectangleInfoPane(String name, Rectangle rectangle) {
        this.name = name;
        this.rectangle = rectangle;
        draw();
    }

    @Override
    public void addListener(InvalidationListener listener) {
        rectangle.xProperty().addListener(listener);
        rectangle.yProperty().addListener(listener);
        rectangle.widthProperty().addListener(listener);
        rectangle.heightProperty().addListener(listener);
    }

    @Override
    public void removeListener(InvalidationListener listener) {
        rectangle.xProperty().removeListener(listener);
        rectangle.yProperty().removeListener(listener);
        rectangle.widthProperty().removeListener(listener);
        rectangle.heightProperty().removeListener(listener);
    }

    private void draw() {
        setPadding(new Insets(5));
        xField.setPrefColumnCount(2);
        xField.setAlignment(Pos.BASELINE_RIGHT);
        yField.setPrefColumnCount(2);
        yField.setAlignment(Pos.BASELINE_RIGHT);
        widthField.setPrefColumnCount(2);
        widthField.setAlignment(Pos.BASELINE_RIGHT);
        heightField.setPrefColumnCount(2);
        heightField.setAlignment(Pos.BASELINE_RIGHT);
        setStyle("-fx-border-color: black");
        add(new Label(String.format("Enter %s info:", name)), 0, 0, 2, 1);
        add(new Label("X: "), 0, 1);
        add(xField, 1, 1);
        add(new Label("Y: "), 0, 2);
        add(yField, 1, 2);
        add(new Label("Width: "), 0, 3);
        add(widthField, 1, 3);
        add(new Label("Height: "), 0, 4);
        add(heightField, 1, 4);
        NumberStringConverter convertor = new NumberStringConverter(NumberFormat.getIntegerInstance());
        xField.textProperty().bindBidirectional(rectangle.xProperty(), convertor);
        yField.textProperty().bindBidirectional(rectangle.yProperty(), convertor);
        widthField.textProperty().bindBidirectional(rectangle.widthProperty(), convertor);
        heightField.textProperty().bindBidirectional(rectangle.heightProperty(), convertor);
    }
}