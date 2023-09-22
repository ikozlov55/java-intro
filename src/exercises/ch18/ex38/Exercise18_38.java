package exercises.ch18.ex38;

import javafx.application.Application;
import javafx.beans.Observable;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Exercise18_38 extends Application {
    private final TextField orderTF = new TextField("0");
    private final RecursiveTree tree = new RecursiveTree();

    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        orderTF.setPrefColumnCount(2);
        orderTF.textProperty().addListener(this::orderTFListener);
        HBox controls = new HBox(5, new Label("Enter an order: "), orderTF);
        controls.setAlignment(Pos.BASELINE_CENTER);
        BorderPane.setMargin(controls, new Insets(5));
        BorderPane.setAlignment(controls, Pos.CENTER);

        tree.setOnMouseClicked(this::handleClick);
        BorderPane.setMargin(tree, new Insets(5));

        BorderPane root = new BorderPane();
        root.setBottom(controls);
        root.setCenter(tree);
        stage.setScene(new Scene(root, 400, 400));
        stage.setTitle(getClass().getSimpleName());
        stage.show();
    }

    private void handleClick(MouseEvent e) {
        int order;
        try {
            order = Integer.parseInt(orderTF.getText());
        } catch (NumberFormatException ex) {
            return;
        }
        switch (e.getButton()) {
            case PRIMARY -> {
                orderTF.setText(Integer.toString(order + 1));
            }
            case SECONDARY -> {
                if (order == 0) return;
                orderTF.setText(Integer.toString(order - 1));
            }

        }
    }

    private void orderTFListener(Observable o) {
        int order;
        try {
            order = Integer.parseInt(orderTF.getText());
        } catch (NumberFormatException ex) {
            return;
        }
        if (order < 0) return;
        tree.setOrder(order);
    }
}

class RecursiveTree extends Pane {
    private int order = 0;

    public void setOrder(int order) {
        this.order = order;
        paint();
    }

    private void paint() {
        getChildren().clear();
        double length = (getWidth() + getHeight()) / 6;
        drawFractal(order, new Point2D(getWidth() / 2, getHeight()), 90, length);
    }

    private void drawFractal(int order, Point2D start, double angle, double length) {
        if (order < 0) {
            return;
        }
        Point2D p = new Point2D(start.getX() + length, start.getY());
        double a = Math.toRadians(angle);
        double dX = p.getX() - start.getX();
        double dY = p.getY() - start.getY();
        double x = Math.cos(a) * dX + Math.sin(a) * dY + start.getX();
        double y = Math.cos(a) * dY - Math.sin(a) * dX + start.getY();
        Point2D end = new Point2D(x, y);
        getChildren().add(new Line(start.getX(), start.getY(), end.getX(), end.getY()));
        drawFractal(order - 1, end, angle - 35, length * 0.6);
        drawFractal(order - 1, end, angle + 35, length * 0.6);
    }

    @Override
    protected void setWidth(double v) {
        super.setWidth(v);
        paint();
    }

    @Override
    protected void setHeight(double v) {
        super.setHeight(v);
        paint();
    }
}