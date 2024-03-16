package exercises.ch23.ex17;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;


public class RadixSortPane extends BorderPane {
    private int[] array;
    List<ArrayDeque<Integer>> buckets;
    int currentIndex;
    int base;
    boolean sorted;
    int bucketHighlight;
    private final Background bg = new Background(new BackgroundFill(Color.WHITE, null, null));

    public RadixSortPane() {
        reset();
    }

    public void reset() {
        array = initArray();
        currentIndex = 0;
        buckets = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            buckets.add(new ArrayDeque<>());
        }
        base = 1;
        sorted = false;
        bucketHighlight = -1;
        draw();
    }

    public int step() {
        if (sorted()) return 1;
        if (currentIndex == array.length) {
            base *= 10;
            int i = 0;
            for (ArrayDeque<Integer> bucket : buckets) {
                while (!bucket.isEmpty()) {
                    array[i++] = bucket.remove();
                }
            }
            currentIndex = 0;
            draw();
            return sorted() ? 1 : 0;
        }
        int radix = (array[currentIndex] / base) % 10;
        bucketHighlight = radix;
        buckets.get(radix).add(array[currentIndex]);
        currentIndex++;
        draw();
        return 0;
    }

    private void draw() {
        getChildren().clear();
        setStyle("-fx-border-color: black");
        HBox arrayBox = new HBox();
        arrayBox.setAlignment(Pos.CENTER);
        arrayBox.setPadding(new Insets(30, 40, 10, 40));
        for (int i = 0; i < array.length; i++) {
            Label label = new Label(Integer.toString(array[i]));
            label.setBackground(bg);
            label.setAlignment(Pos.BASELINE_CENTER);
            label.setPrefSize(40, 25);
            label.setStyle(String.format("-fx-border-color: black; -fx-border-width: 1 %d 1 1;",
                    i == array.length - 1 ? 1 : 0));
            label.setFont(Font.font(16));
            if (i == currentIndex - 1) {
                label.setTextFill(Color.RED);
            }
            arrayBox.getChildren().add(label);
        }
        setTop(arrayBox);

        HBox bucketsBox = new HBox(20);
        bucketsBox.setAlignment(Pos.CENTER);
        bucketsBox.setPadding(new Insets(10, 40, 10, 40));
        for (int i = 0; i < buckets.size(); i++) {
            VBox vBox = new VBox();
            vBox.setMinWidth(50);
            vBox.minHeightProperty().bind(heightProperty().divide(2));
            vBox.setBackground(bg);

            List<Integer> bucket = new ArrayList<>(buckets.get(i));
            for (int j = 0; j < bucket.size(); j++) {
                int value = bucket.get(j);
                Label label = new Label(Integer.toString(value));
                label.setAlignment(Pos.BASELINE_CENTER);
                label.setPrefSize(40, 25);
                label.setFont(Font.font(16));
                if (j == bucket.size() - 1 && i == bucketHighlight) {
                    label.setTextFill(Color.RED);
                }
                vBox.getChildren().add(label);
            }
            Label label = new Label(String.format("bucket[%d]", i), vBox);
            label.setAlignment(Pos.BASELINE_CENTER);
            label.setContentDisplay(ContentDisplay.TOP);
            vBox.setStyle("-fx-border-color: black");
            bucketsBox.getChildren().addAll(vBox, label);
        }
        setCenter(bucketsBox);
    }


    private int[] initArray() {
        int[] array = new int[20];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * 1000 + 1);
        }
        for (int i = 0; i < array.length; i++) {
            int index = (int) (Math.random() * array.length);
            int temp = array[i];
            array[i] = array[index];
            array[index] = temp;
        }
        return array;
    }

    private boolean sorted() {
        if (array.length == 0) return true;
        int previous = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < previous) {
                return false;
            }
            previous = array[i];
        }
        return true;
    }
}
