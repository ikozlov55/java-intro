package exercises.ch24.ex07;

import exercises.utils.UtilsFx;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
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
                Group headArrow = UtilsFx.arrowLine(10, -h * 2, w / 2, 0);
                cell.getChildren().addAll(headArrow, new Text(0, -h * 2 - 4, "head"));
            }
            if (i == linkedList.size() - 1) {
                Group tailArrow = UtilsFx.arrowLine(20, -h * 2, w / 2, 0);
                cell.getChildren().addAll(tailArrow, new Text(10, -h * 2 - 4, "tails"));
            }
            if (i != linkedList.size() - 1) {
                cell.getChildren().add(UtilsFx.arrowLine(w + h / 2, h / 2, w * 2, h / 2));
            }
        }
    }


}
