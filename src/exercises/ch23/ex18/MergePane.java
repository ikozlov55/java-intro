package exercises.ch23.ex18;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.Arrays;

public class MergePane extends BorderPane {
    ListPane list1Pane;
    ListPane list2Pane;
    ListPane tempPane;

    public MergePane() {
        reset();
    }

    public void reset() {
        int[] list1 = new int[8];
        int[] list2 = new int[8];
        int[] temp = new int[16];
        for (int i = 0; i < 8; i++) {
            list1[i] = (int) (Math.random() * 999 + 1);
            list2[i] = (int) (Math.random() * 999 + 1);
        }
        Arrays.sort(list1);
        Arrays.sort(list2);
        list1Pane = new ListPane(list1, "list1", "current1", ArrowStyle.TOP);
        list2Pane = new ListPane(list2, "list2", "current1", ArrowStyle.TOP);
        tempPane = new ListPane(temp, "temp", "current3", ArrowStyle.BOTTOM);
        draw();
    }

    public boolean step() {
        if (list1Pane.finished() && list2Pane.finished()) {
            return true;
        }
        if (list2Pane.finished()) {
            tempPane.set(list1Pane.get());
            list1Pane.increment();
        } else if (list1Pane.finished()) {
            tempPane.set(list2Pane.get());
            list2Pane.increment();
        } else if (list1Pane.get() <= list2Pane.get()) {
            tempPane.set(list1Pane.get());
            list1Pane.increment();
        } else {
            tempPane.set(list2Pane.get());
            list2Pane.increment();
        }
        tempPane.increment();
        return list1Pane.finished() && list2Pane.finished();
    }

    private void draw() {
        getChildren().clear();
        setStyle("-fx-border-color: black");
        HBox hBox1 = new HBox(40);
        hBox1.setAlignment(Pos.BASELINE_CENTER);
        hBox1.setPadding(new Insets(40, 40, 0, 40));
        hBox1.getChildren().addAll(list1Pane, list2Pane);
        setTop(hBox1);

        HBox hBox2 = new HBox(40);
        hBox2.setAlignment(Pos.BASELINE_CENTER);
        hBox2.setPadding(new Insets(0, 40, 0, 40));
        hBox2.getChildren().addAll(tempPane);
        setCenter(hBox2);
    }
}

class ListPane extends Pane {
    int[] list;
    int index;
    String name;
    String indexName;
    ArrowStyle style;
    private final Background bg = new Background(new BackgroundFill(Color.WHITE, null, null));

    public ListPane(int[] list, String name, String indexName, ArrowStyle style) {
        this.list = list;
        index = 0;
        this.name = name;
        this.indexName = indexName;
        this.style = style;
        draw();
    }

    public boolean finished() {
        return index >= list.length;
    }

    public int get() {
        return list[index];
    }

    public void set(int value) {
        list[index] = value;
        draw();
    }

    public void increment() {
        index++;
        draw();
    }

    private void draw() {
        getChildren().clear();
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(5));
        Label listLabel = new Label(name);
        listLabel.setFont(Font.font(16));
        listLabel.setPrefWidth(40);
        hBox.getChildren().add(listLabel);
        for (int i = 0; i < list.length; i++) {
            String s = list[i] == 0 ? "" : Integer.toString(list[i]);
            Label label = new Label(s);
            label.setBackground(bg);
            label.setAlignment(Pos.BASELINE_CENTER);
            label.setPrefSize(40, 25);
            label.setStyle(String.format("-fx-border-color: black; -fx-border-width: 1 %d 1 1;",
                    i == list.length - 1 ? 1 : 0));
            label.setFont(Font.font(16));
            hBox.getChildren().add(label);
        }

        getChildren().addAll(hBox, makeArrow());
    }

    private Group makeArrow() {
        double x = index * 40 + 65;
        if (index >= list.length) {
            x -= 20;
        }
        Group arrow = new Group();
        Text indexLabel = new Text(indexName);
        indexLabel.setX(x - 25);
        if (style == ArrowStyle.TOP) {
            arrow.getChildren().addAll(
                    new Line(x, -30, x, 5),
                    new Line(x, 5, x - 10, -5),
                    new Line(x, 5, x + 10, -5)
            );
            indexLabel.setY(-35);
        } else {
            arrow.getChildren().addAll(
                    new Line(x, 67, x, 32),
                    new Line(x, 32, x - 10, 42),
                    new Line(x, 32, x + 10, 42)
            );
            indexLabel.setY(79);
        }

        indexLabel.setFont(Font.font(16));
        arrow.getChildren().add(indexLabel);
        return arrow;
    }


}

enum ArrowStyle {
    TOP, BOTTOM
}