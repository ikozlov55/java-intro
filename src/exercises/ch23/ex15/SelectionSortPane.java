package exercises.ch23.ex15;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class SelectionSortPane extends Pane {
    private int[] array;
    int currentIndex;

    public SelectionSortPane() {
        array = initArray();
        currentIndex = -1;
    }

    public void reset() {
        array = initArray();
        currentIndex = -1;
        draw();
    }

    public int step() {
        currentIndex = currentIndex == -1 ? 0 : currentIndex;
        if (currentIndex >= array.length - 1) return 1;
        int minIndex = currentIndex;
        int min = array[minIndex];
        for (int i = currentIndex + 1; i < array.length; i++) {
            if (array[i] < min) {
                minIndex = i;
                min = array[i];
            }
        }
        if (minIndex != currentIndex) {
            array[minIndex] = array[currentIndex];
            array[currentIndex] = min;
        }
        draw();
        currentIndex++;
        System.out.println(currentIndex);
        return currentIndex >= array.length - 1 ? 1 : 0;
    }

    private void draw() {
        getChildren().clear();
        setStyle("-fx-border-color: black");
        double barWidth = (getWidth() - (array.length + 1) * 5) / array.length;
        double n = (getHeight() * 0.9) / 20;
        for (int i = 0; i < array.length; i++) {
            double barHeight = array[i] * n;
            double y = getHeight() - barHeight - 1;
            double x = barWidth * i + 5 * i + 5;
            Rectangle rec = new Rectangle(x, y, barWidth, barHeight);
            rec.setFill(i == currentIndex - 1 ? Color.RED : Color.WHITE);
            rec.setStroke(Color.BLACK);
            Text label = new Text(x + barWidth / (array[i] < 10 ? 4 : 8), y - 5, Integer.toString(array[i]));
            label.setFont(Font.font(15));
            getChildren().addAll(rec, label);
        }
    }

    @Override
    protected void setWidth(double v) {
        super.setWidth(v);
        draw();
    }

    @Override
    protected void setHeight(double v) {
        super.setHeight(v);
        draw();
    }

    private int[] initArray() {
        int[] array = new int[20];
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1;
        }
        for (int i = 0; i < array.length; i++) {
            int index = (int) (Math.random() * array.length);
            int temp = array[i];
            array[i] = array[index];
            array[index] = temp;
        }
        return array;
    }
}
