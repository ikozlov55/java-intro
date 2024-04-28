package exercises.ch24.ex11;

import exercises.utils.UtilsFx;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class DoublyLinkedListPane extends BorderPane {

    private final DoublyLinkedList<Integer> linkedList;
    private final Label label = new Label("");

    public DoublyLinkedListPane() {
        linkedList = new DoublyLinkedList<>();
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

    public void insert(int value) {
        insert(linkedList.size(), value);
    }

    public void insert(int index, int value) {
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

    public void forwardTraversal() {
        List<String> values = List.copyOf(linkedList).stream()
                .map(i -> Integer.toString(i))
                .toList();
        String s = String.join(" ", values);
        label.setText("Forward Traversal: " + s);
    }

    public void backwardTraversal() {
        ArrayList<String> values = new ArrayList<>();
        ListIterator<Integer> iterator = linkedList.iterator();
        while (iterator.hasNext()) {
            iterator.next();
        }
        while (iterator.hasPrevious()) {
            values.add(Integer.toString(iterator.previous()));
        }
        String s = String.join(" ", values);
        label.setText("Backward Traversal: " + s);
    }

    private void draw() {
        double w = 60;
        double h = 20;
        getChildren().clear();
        label.setText("");
        setTop(label);
        BorderPane.setMargin(label, new Insets(5));
        BorderPane.setAlignment(label, Pos.CENTER);
        Pane listPane = new Pane();
        setCenter(listPane);
        BorderPane.setAlignment(listPane, Pos.CENTER);
        BorderPane.setMargin(listPane, new Insets(30));


        for (int i = 0; i < linkedList.size(); i++) {
            Label numberLabel = new Label(Integer.toString(linkedList.get(i)));
            numberLabel.setFont(Font.font(15));
            numberLabel.setStyle("-fx-border-color: black; -fx-border-width: 1 1 1 1;");
            numberLabel.setPrefSize(w, h);
            numberLabel.setAlignment(Pos.BASELINE_CENTER);
            Label nextLabel = new Label("next");
            nextLabel.setFont(Font.font(15));
            nextLabel.setStyle("-fx-border-color: black; -fx-border-width: 0 1 1 1;");
            nextLabel.setPrefSize(w, h);
            nextLabel.setAlignment(Pos.BASELINE_CENTER);
            Label previousLabel = new Label("previous");
            previousLabel.setFont(Font.font(15));
            previousLabel.setStyle("-fx-border-color: black; -fx-border-width: 0 1 1 1;");
            previousLabel.setPrefSize(w, h);
            previousLabel.setAlignment(Pos.BASELINE_CENTER);
            VBox vBox = new VBox(numberLabel, nextLabel, previousLabel);
            vBox.setPrefWidth(w);
            double x = i * w + i * 30;
            vBox.setLayoutX(x);
            vBox.setLayoutY(20);
            listPane.getChildren().add(vBox);

            if (i == 0) {
                Group headArrow = UtilsFx.arrowLine(-10, -10, 0, h);
                listPane.getChildren().addAll(headArrow, new Text(-20, -12, "head"));
            }
            if (i != 0) {
                Group previousArrow = UtilsFx.arrowLine(x, h * 3.5 + 4, x - 30, h * 1.5 + 4);
                listPane.getChildren().add(previousArrow);
            }
            if (i == linkedList.size() - 1) {
                Group tailArrow = UtilsFx.arrowLine(x + w + 10, -10, x + w, h);
                listPane.getChildren().addAll(tailArrow, new Text(x + w, -12, "tail"));
            }
            if (i != linkedList.size() - 1) {
                Group nextArrow = UtilsFx.arrowLine(x + w, h * 2.5 + 4, x + w + 30, h * 1.5 + 4);
                listPane.getChildren().add(nextArrow);
            }
        }
    }


}
