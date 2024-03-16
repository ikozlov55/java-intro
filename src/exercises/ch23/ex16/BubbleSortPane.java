package exercises.ch23.ex16;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class BubbleSortPane extends Pane {
    private int[] array;
    int loopNumber;
    int currentIndex;

    public BubbleSortPane() {
        array = initArray();
        currentIndex = 0;
        loopNumber = 1;
    }

    public void reset() {
        array = initArray();
        currentIndex = 0;
        loopNumber = 1;
        draw();
    }

    public int step() {
        if (loopNumber >= array.length) return 1;

        if (array[currentIndex] > array[currentIndex + 1]) {
            int temp = array[currentIndex + 1];
            array[currentIndex + 1] = array[currentIndex];
            array[currentIndex] = temp;
        }
        currentIndex++;
        if (currentIndex >= array.length - loopNumber) {
            currentIndex = 0;
            loopNumber++;
        }
        draw();
        return 0;
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
            rec.setFill(i == currentIndex + 1 ? Color.RED : Color.WHITE);
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
