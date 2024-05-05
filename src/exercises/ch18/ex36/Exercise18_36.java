package exercises.ch18.ex36;

import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class Exercise18_36 extends Application {
    private final TextField tfOrder = new TextField();
    private final SierpinskiTrianglePane pane = new SierpinskiTrianglePane();

    private final SimpleIntegerProperty order = new SimpleIntegerProperty();

    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root = makeRootPane();

        order.addListener((ov, oldValue, newValue) -> {
            int newOrder = newValue.intValue();
            if (newOrder < 0) {
                order.set(0);
                return;
            }
            pane.setOrder(newOrder);
        });

        root.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case UP, ADD, EQUALS -> order.set(order.get() + 1);
                case DOWN, MINUS, SUBTRACT -> order.set(order.get() - 1);
            }
        });

        root.setOnMouseClicked(e -> {
            switch (e.getButton()) {
                case PRIMARY -> order.set(order.get() + 1);
                case SECONDARY -> order.set(order.get() - 1);
            }
        });

        tfOrder.textProperty().bindBidirectional(order, new StringConverter<>() {
            @Override
            public String toString(Number number) {
                return Integer.toString(number.intValue());
            }

            @Override
            public Number fromString(String s) {
                try {
                    return Integer.parseInt(s);
                } catch (NumberFormatException e) {
                    return 0;
                }
            }
        });

        order.set(0);

        stage.setTitle(getClass().getSimpleName());
        stage.setScene(new Scene(root, 400, 410));
        stage.show();
        root.requestFocus();
    }

    private BorderPane makeRootPane() {
        pane.widthProperty().addListener(ov -> pane.paint());
        pane.heightProperty().addListener(ov -> pane.paint());
        tfOrder.setPrefColumnCount(4);
        tfOrder.setDisable(true);

        HBox hBox = new HBox(10);
        hBox.getChildren().addAll(new Label("Enter an order: "), tfOrder);
        hBox.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(5));

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(pane);
        borderPane.setBottom(hBox);

        return borderPane;
    }


}

/**
 * Pane for displaying triangles
 */
class SierpinskiTrianglePane extends Pane {
    private int order = 0;

    SierpinskiTrianglePane() {
    }

    public void setOrder(int order) {
        this.order = order;
        paint();
    }

    protected void paint() {
        // Select three points in proportion to the pane size
        Point2D p1 = new Point2D(getWidth() / 2, 10);
        Point2D p2 = new Point2D(10, getHeight() - 10);
        Point2D p3 = new Point2D(getWidth() - 10, getHeight() - 10);

        this.getChildren().clear(); // Clear the pane before redisplay

        displayTriangles(order, p1, p2, p3);
    }

    private void displayTriangles(int order, Point2D p1, Point2D p2, Point2D p3) {
        if (order == 0) {
            // Draw a triangle to connect three points
            Polygon triangle = new Polygon();
            triangle.getPoints().addAll(p1.getX(), p1.getY(), p2.getX(), p2.getY(), p3.getX(), p3.getY());
            triangle.setStroke(Color.BLACK);
            triangle.setFill(Color.BLACK);

            this.getChildren().add(triangle);
        } else {
            // Get the midpoint on each edge in the triangle
            Point2D p12 = p1.midpoint(p2);
            Point2D p23 = p2.midpoint(p3);
            Point2D p31 = p3.midpoint(p1);

            // Recursively display three triangles
            displayTriangles(order - 1, p1, p12, p31);
            displayTriangles(order - 1, p12, p2, p23);
            displayTriangles(order - 1, p31, p23, p3);
        }
    }
}
