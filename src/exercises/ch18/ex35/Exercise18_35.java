package exercises.ch18.ex35;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Exercise18_35 extends Application {
    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        HBox controls = new HBox();
        controls.setAlignment(Pos.BASELINE_CENTER);
        controls.setPadding(new Insets(5));
        TextField orderTF = new TextField();
        orderTF.setPrefColumnCount(2);
        controls.getChildren().addAll(new Label("Enter an order: "), orderTF);
        HTreePane hTree = new HTreePane();

        orderTF.textProperty().addListener(ov -> {
            try {
                int order = Integer.parseInt(orderTF.getText());
                hTree.setOrder(order);
            } catch (NumberFormatException ignored) {}
        });

        BorderPane root = new BorderPane();
        root.setBottom(controls);
        root.setCenter(hTree);

        stage.setScene(new Scene(root, 400, 400));
        stage.setTitle(getClass().getSimpleName());
        stage.show();
    }
}

class HTreePane extends Pane {
    private int order = 0;

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
        paint();
    }

    private void paint() {
        getChildren().clear();
        drawFractal(getWidth() / 2, getHeight() / 2, order, getWidth() / 3);
    }

    private void drawFractal(double x, double y, int order, double length) {
        if (order < 0) {
            return;
        }
        double half = length / 2;
        Line line1 = new Line(x - half, y, x + half, y);
        Line line2 = new Line(x - half, y - half, x - half, y + half);
        Line line3 = new Line(x + half, y - half, x + half, y + half);
        getChildren().addAll(line1, line2, line3);
        drawFractal(line2.getStartX(), line2.getStartY(), order - 1, half);
        drawFractal(line2.getEndX(), line2.getEndY(), order - 1, half);
        drawFractal(line3.getStartX(), line2.getStartY(), order - 1, half);
        drawFractal(line3.getEndX(), line2.getEndY(), order - 1, half);
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