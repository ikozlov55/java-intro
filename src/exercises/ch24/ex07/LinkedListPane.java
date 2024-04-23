package exercises.ch24.ex07;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class LinkedListPane extends HBox {
    private final MyLinkedList<Integer> linkedList;

    public LinkedListPane() {
        linkedList = new MyLinkedList<>();
        draw();
    }

    public void add(int value) {
        add(linkedList.size(), value);
    }

    public void add(int index, int value) {
        linkedList.add(index, value);
        draw();
    }

    public void deleteValue(int value) {
        int index = linkedList.indexOf(value);
        if (index >= 0) {
            deleteIndex(index);
        }
    }

    public void deleteIndex(int index) {
        linkedList.remove(index);
        draw();
    }

    public void search(int value) {
        int index = linkedList.indexOf(value);
        String msg;
        if (index < 0) {
            msg = String.format("The value %d is not in the list", value);
        } else {
            msg = String.format("The value %d is at index %d in the list", value, index);
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION, msg);
        alert.setHeaderText("");
        alert.showAndWait();

    }

    private void draw() {
        double w = 35;
        double h = 20;
        getChildren().clear();
        for (int i = 0; i < linkedList.size(); i++) {
            Label label = new Label(Integer.toString(linkedList.get(i)));
            label.setFont(Font.font(15));
            label.setStyle("-fx-border-color: black;");
            label.setPrefSize(w, h);
            label.setAlignment(Pos.BASELINE_CENTER);

            Rectangle rec = new Rectangle(w, 1, w / 2, h);
            rec.setStroke(Color.BLACK);
            rec.setFill(null);

            Pane cell = new Pane();
            cell.getChildren().addAll(label, rec);
            getChildren().add(cell);

            if (i == 0) {
                Group headArrow = arrowLine(10, -h * 2, w / 2, 0);
                cell.getChildren().addAll(headArrow, new Text(0, -h * 2 - 4, "head"));
            }
            if (i == linkedList.size() - 1) {
                Group tailArrow = arrowLine(20, -h * 2, w / 2, 0);
                cell.getChildren().addAll(tailArrow, new Text(10, -h * 2 - 4, "tails"));
            }
            if (i != linkedList.size() - 1) {
                cell.getChildren().add(arrowLine(w + h / 2, h / 2, w * 2, h / 2));
            }
        }
    }

    private Group arrowLine(double startX, double startY, double endX, double endY) {
        Line line = new Line(startX, startY, endX, endY);
        double distance = Math.sqrt(Math.pow(startX - endX, 2) + Math.pow(startY - endY, 2));
        double lineAngle;
        double addAngle;
        if (startY > endY) {
            lineAngle = Math.asin((endX - startX) / distance);
            addAngle = Math.PI * 0.75;
        } else {
            lineAngle = Math.asin((startX - endX) / distance);
            addAngle = Math.PI * 0.25;
        }
        Line head1 = new Line(
                endX,
                endY,
                endX + 10 * Math.sin(lineAngle + addAngle),
                endY - 10 * Math.cos(lineAngle + addAngle)
        );
        Line head2 = new Line(
                endX,
                endY,
                endX + 10 * Math.sin(lineAngle - addAngle),
                endY - 10 * Math.cos(lineAngle - addAngle)
        );
        return new Group(line, head1, head2);
    }

}
