package exercises.ch24.ex09;

import exercises.ch24.ex08.MyArrayList;
import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Optional;

public class AnimatedArrayListPane extends Pane {
    private final MyArrayList<Integer> arrayList;
    ArrayList<Pane> cells;

    public AnimatedArrayListPane() {
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
        Optional<Animation> lastAnimation = Optional.empty();
        for (int i = index; i < cells.size(); i++) {
            TranslateTransition animation = new TranslateTransition(Duration.seconds(1));
            animation.setByX(35);
            animation.setNode(cells.get(i));
            animation.play();
            lastAnimation = Optional.of(animation);
        }
        lastAnimation.ifPresentOrElse(
                animation -> animation.setOnFinished(e -> draw()),
                this::draw
        );
    }

    public void deleteValue(int value) {
        int index = arrayList.indexOf(value);
        if (index >= 0) {
            deleteIndex(index);
        }
    }

    public void deleteIndex(int index) {
        arrayList.remove(index);
        getChildren().remove(cells.get(index));
        Optional<Animation> lastAnimation = Optional.empty();
        for (int i = index; i < cells.size(); i++) {
            TranslateTransition animation = new TranslateTransition(Duration.seconds(1));
            animation.setByX(-35);
            animation.setNode(cells.get(i));
            animation.play();
            lastAnimation = Optional.of(animation);
        }
        lastAnimation.ifPresentOrElse(
                animation -> animation.setOnFinished(e -> draw()),
                this::draw
        );
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
        Text statusLabel = new Text(20, 20, s);
        getChildren().add(statusLabel);

        cells = new ArrayList<>();
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
            cell.layoutYProperty().bind(heightProperty().divide(2));
            cell.setLayoutX(10 + i * w);
            cells.add(cell);
        }
        for (int i = arrayList.size(); i < arrayList.getCapacity(); i++) {
            Rectangle rec = new Rectangle(0, 0, w, h);
            rec.setStroke(Color.BLACK);
            rec.setFill(null);
            Line line = new Line(0, h, w, 0);

            Pane cell = new Pane();
            cell.getChildren().addAll(rec, line);
            cell.layoutYProperty().bind(heightProperty().divide(2));
            cell.setLayoutX(10 + i * w);
            cells.add(cell);
        }
        getChildren().addAll(cells);

    }
}
