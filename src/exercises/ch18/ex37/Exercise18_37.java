package exercises.ch18.ex37;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polyline;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Exercise18_37 extends Application {
    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        HBox controls = new HBox();
        controls.setAlignment(Pos.BASELINE_CENTER);
        controls.setPadding(new Insets(5));
        TextField orderTF = new TextField("1");
        orderTF.setPrefColumnCount(2);
        controls.getChildren().addAll(new Label("Enter an order: "), orderTF);
        HilbertCurvePane hilbertCurve = new HilbertCurvePane();

        orderTF.textProperty().addListener(ov -> {
            try {
                int order = Integer.parseInt(orderTF.getText());
                hilbertCurve.setOrder(order);
            } catch (NumberFormatException ignored) {
            }
        });

        BorderPane root = new BorderPane();
        root.setBottom(controls);
        root.setCenter(hilbertCurve);
        BorderPane.setMargin(hilbertCurve, new Insets(5));

        stage.setScene(new Scene(root, 400, 400));
        stage.setTitle(getClass().getSimpleName());
        stage.show();
    }
}

class HilbertCurvePane extends Pane {
    private int order = 1;
    ArrayList<Point2D> points = new ArrayList<>();

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
        paint();
    }

    private void paint() {
        getChildren().clear();
        points.clear();
        Polyline polyline = new Polyline();
        double length = (getWidth() + getHeight()) / 4;
        drawQuadrant(Quadrant.C, getWidth() / 2, getHeight() / 2, order, length);
        points.forEach(p -> polyline.getPoints().addAll(p.getX(), p.getY()));
        getChildren().add(polyline);
    }

    private void drawQuadrant(Quadrant type, double x, double y, int order, double length) {
        if (order == 0) {
            points.add(new Point2D(x, y));
            return;
        }
        length /= 2;
        order--;
        switch (type) {
            case A -> {
                drawQuadrant(Quadrant.D, x - length, y + length, order, length);
                drawQuadrant(Quadrant.A, x - length, y - length, order, length);
                drawQuadrant(Quadrant.A, x + length, y - length, order, length);
                drawQuadrant(Quadrant.B, x + length, y + length, order, length);
            }
            case B -> {
                drawQuadrant(Quadrant.C, x + length, y - length, order, length);
                drawQuadrant(Quadrant.B, x - length, y - length, order, length);
                drawQuadrant(Quadrant.B, x - length, y + length, order, length);
                drawQuadrant(Quadrant.A, x + length, y + length, order, length);
            }
            case C -> {
                drawQuadrant(Quadrant.B, x + length, y - length, order, length);
                drawQuadrant(Quadrant.C, x + length, y + length, order, length);
                drawQuadrant(Quadrant.C, x - length, y + length, order, length);
                drawQuadrant(Quadrant.D, x - length, y - length, order, length);
            }
            case D -> {
                drawQuadrant(Quadrant.A, x - length, y + length, order, length);
                drawQuadrant(Quadrant.D, x + length, y + length, order, length);
                drawQuadrant(Quadrant.D, x + length, y - length, order, length);
                drawQuadrant(Quadrant.C, x - length, y - length, order, length);
            }
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

enum Quadrant {
    A, B, C, D
}