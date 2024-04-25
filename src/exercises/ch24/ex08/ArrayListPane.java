package exercises.ch24.ex08;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class ArrayListPane extends BorderPane {
    private final MyArrayList<Integer> arrayList;

    public ArrayListPane() {
        arrayList = new MyArrayList<>();
        draw();
    }

    public void search(int value) {
        int index = arrayList.indexOf(value);
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

    public void add(int value) {
        add(arrayList.size(), value);
    }

    public void add(int index, int value) {
        arrayList.add(index, value);
        draw();
    }

    public void deleteValue(int value) {
        int index = arrayList.indexOf(value);
        if (index >= 0) {
            deleteIndex(index);
        }
    }

    public void deleteIndex(int index) {
        arrayList.remove(index);
        draw();
    }

    public void trimToSize() {
        arrayList.trim();
        draw();
    }


    private void draw() {
        double w = 35;
        double h = 20;
        getChildren().clear();
        String s = String.format("Array list %s size = %d and capacity is %d",
                arrayList.isEmpty() ? "is empty" : "is not empty",
                arrayList.size(),
                arrayList.getCapacity());
        Label statusLabel = new Label(s);
        BorderPane.setMargin(statusLabel, new Insets(5));
        setTop(statusLabel);

        HBox hBox = new HBox();
        hBox.setMaxHeight(h);
        BorderPane.setMargin(hBox, new Insets(10));
        setCenter(hBox);

        for (int i = 0; i < arrayList.size(); i++) {
            Label label = new Label(Integer.toString(arrayList.get(i)));
            label.setFont(Font.font(15));
            label.setPrefSize(w, h);
            label.setAlignment(Pos.BASELINE_CENTER);

            Rectangle rec = new Rectangle(0, 0, w, h);
            rec.setStroke(Color.BLACK);
            rec.setFill(null);

            Pane cell = new Pane();
            cell.getChildren().addAll(label, rec);
            hBox.getChildren().add(cell);
        }
        for (int i = arrayList.size(); i < arrayList.getCapacity(); i++) {
            Rectangle rec = new Rectangle(0, 0, w, h);
            rec.setStroke(Color.BLACK);
            rec.setFill(null);
            Line line = new Line(0, h, w, 0);

            Pane cell = new Pane();
            cell.getChildren().addAll(rec, line);
            hBox.getChildren().add(cell);
        }
    }
}
