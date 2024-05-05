package exercises.ch24.ex10;

import exercises.utils.UtilsFx;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class StackPane extends Pane {
    ArrayList<StackItemPane> cells;
    private final double w = 35;
    private final double h = 20;

    public StackPane() {
        cells = new ArrayList<>();
        draw();
    }

    public void push(int value) {
        cells.add(new StackItemPane(value));
        draw();
    }

    public void pop() {
        if (cells.isEmpty()) return;
        cells.remove(cells.size() - 1);
        draw();
    }

    public void peek() {
        if (cells.isEmpty()) return;
        String msg = String.format("The top element is %d", cells.get(cells.size() - 1).getValue());
        Alert alert = new Alert(Alert.AlertType.INFORMATION, msg);
        alert.setHeaderText("");
        alert.showAndWait();
    }

    private void draw() {
        getChildren().clear();
        if (cells.isEmpty()) return;
        double x = getWidth() / 2 - w / 2;
        for (int i = 0; i < cells.size(); i++) {
            StackItemPane cell = cells.get(i);
            cell.setLayoutX(x);
            cell.setLayoutY(getHeight() / 2 + 2 * h - i * h);
        }
        getChildren().addAll(cells);

        StackItemPane topCell = cells.get(cells.size() - 1);
        double y = getHeight() / 2 + 2 * h - (cells.size() - 1) * h + h / 2;
        Group arrow = UtilsFx.arrowLine(x - w, y, topCell.getLayoutX(), y);
        Text text = new Text(x - w * 1.8, y + 2, "Top");
        getChildren().addAll(arrow, text);
    }

    private class StackItemPane extends Pane {
        private final int value;

        private StackItemPane(int value) {
            this.value = value;
            draw();
        }

        private void draw() {
            Label label = new Label(Integer.toString(this.value));
            label.setFont(Font.font(15));
            label.setPrefSize(w, h);
            label.setAlignment(Pos.BASELINE_CENTER);

            Rectangle rec = new Rectangle(0, 0, w, h);
            rec.setStroke(Color.BLACK);
            rec.setFill(null);

            getChildren().addAll(label, rec);
        }

        public int getValue() {
            return value;
        }
    }
}
