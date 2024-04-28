package exercises.ch24.ex12;

import exercises.utils.UtilsFx;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class QueuePane extends BorderPane {
    final double w = 35;
    final double h = 20;
    private double endX;
    private final Label label = new Label();
    private List<Integer> list;


    public QueuePane() {
        restart();
    }

    public void enqueue(int value) {
        list.add(value);
        endX += w;
        draw();
    }

    public void dequeue() {
        if (list.isEmpty()) return;
        list.remove(0);
        draw();
    }

    public void restart() {
        list = new ArrayList<>();
        endX = 30;
        draw();
    }

    private void draw() {
        getChildren().clear();
        if (list.isEmpty()) {
            BorderPane.setMargin(label, new Insets(5));
            label.setText("Queue is empty");
            setTop(label);
            return;
        }
        Pane itemsPane = new Pane();
        setCenter(itemsPane);
        BorderPane.setAlignment(itemsPane, Pos.CENTER);
        BorderPane.setMargin(itemsPane, new Insets(30));
        double x = endX;
        double y = getHeight() / 2 - h;
        for (int i = list.size() - 1; i >= 0; i--) {
            Label label = new Label(Integer.toString(list.get(i)));
            label.setFont(Font.font(15));
            label.setStyle("-fx-border-color: black;");
            label.setPrefSize(w, h);
            label.setAlignment(Pos.BASELINE_CENTER);
            label.setLayoutX(x);
            label.setLayoutY(y);
            itemsPane.getChildren().add(label);
            if (i == 0) {
                Group headArrow = UtilsFx.arrowLine(x - w / 2, y - 2 * h, x + w / 2, y);
                itemsPane.getChildren().addAll(headArrow, new Text(x - w / 2 - 15, y - 2 * h - 5, "head"));
            }
            if (i == list.size() - 1) {
                Group tailArrow = UtilsFx.arrowLine(x + w + 10, y - 2 * h, x + w / 2, y);
                itemsPane.getChildren().addAll(tailArrow, new Text(x + w, y - 2 * h - 5, "tail"));
            }
            x -= w;
        }
    }
}
