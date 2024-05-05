package exercises.ch18.ex27;

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
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class Exercise18_27 extends Application {
    private final TextField tfOrder = new TextField();
    private final KochSnowflakePane fractal = new KochSnowflakePane();
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
            fractal.setOrder(newOrder);
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

        stage.setScene(new Scene(root, 400, 410));
        stage.setTitle(getClass().getSimpleName());
        stage.show();
        root.requestFocus();
    }

    private BorderPane makeRootPane() {
        fractal.widthProperty().addListener(ov -> fractal.paint());
        fractal.heightProperty().addListener(ov -> fractal.paint());
        tfOrder.setPrefColumnCount(4);

        HBox hBox = new HBox(10);
        hBox.getChildren().addAll(new Label("Enter an order: "), tfOrder);
        hBox.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(5));

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(fractal);
        BorderPane.setMargin(fractal, new Insets(20, 50, 80, 50));
        borderPane.setBottom(hBox);

        return borderPane;
    }


}

class KochSnowflakePane extends Pane {
    private int order = 0;

    KochSnowflakePane() {
    }

    public void setOrder(int order) {
        this.order = order;
        paint();
    }

    public void paint() {
        Point2D p1 = new Point2D(getWidth() / 2, 0);
        Point2D p2 = new Point2D(0, getHeight());
        Point2D p3 = new Point2D(getWidth(), getHeight());
        getChildren().clear();
        for (Point2D[] points : new Point2D[][]{{p1, p2}, {p2, p3}, {p3, p1}}) {
            drawVertex(order, points[0], points[1]);
        }
    }

    private void drawVertex(int order, Point2D p1, Point2D p2) {
        if (order == 0) {
            getChildren().add(new Line(p1.getX(), p1.getY(), p2.getX(), p2.getY()));
            return;
        }
        double d = p1.distance(p2);
        double d2 = d / 3;
        double d3 = d * (2.0 / 3);
        double x1 = p1.getX() - (d2 * (p1.getX() - p2.getX())) / d;
        double y1 = p1.getY() - (d2 * (p1.getY() - p2.getY())) / d;
        double x2 = p1.getX() - (d3 * (p1.getX() - p2.getX())) / d;
        double y2 = p1.getY() - (d3 * (p1.getY() - p2.getY())) / d;
        Point2D p11 = new Point2D(x1, y1);
        Point2D p22 = new Point2D(x2, y2);
        Point2D p33 = findNextPoint(p11, p22);
        drawVertex(order - 1, p1, p11);
        drawVertex(order - 1, p11, p33);
        drawVertex(order - 1, p33, p22);
        drawVertex(order - 1, p22, p2);
    }

    private Point2D findNextPoint(Point2D p1, Point2D p2) {
        double a = Math.PI / 3;
        double dX = p2.getX() - p1.getX();
        double dY = p2.getY() - p1.getY();
        double x = Math.cos(a) * dX - Math.sin(a) * dY + p1.getX();
        double y = Math.sin(a) * dX + Math.cos(a) * dY + p1.getY();
        return new Point2D(x, y);
    }

}



































