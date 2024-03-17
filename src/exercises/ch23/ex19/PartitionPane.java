package exercises.ch23.ex19;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class PartitionPane extends Pane {
    private int[] array;
    private int high;
    private int low;
    private int pivot;


    public PartitionPane() {
        reset();
    }

    public void reset() {
        array = new int[20];
        pivot = 0;
        high = array.length - 1;
        low = 1;
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * 999 + 1);
        }
        draw();
    }

    public boolean step() {
        if (low < high) {
            if (array[low] <= array[pivot]) {
                low++;
                draw();
                return false;
            }
            if (array[high] > array[pivot]) {
                high--;
                draw();
                return false;
            }
            int temp = array[low];
            array[low] = array[high];
            array[high] = temp;
            draw();
            return false;
        }
        if (high > pivot && array[high] > array[pivot]) {
            high--;
            draw();
            return false;
        }
        if (high > pivot) {
            int temp = array[pivot];
            array[pivot] = array[high];
            array[high] = temp;
            pivot = high;
        }
        draw();
        return true;
    }

    private void draw() {
        getChildren().clear();
        setStyle("-fx-border-color: black");
        Background bg = new Background(new BackgroundFill(Color.WHITE, null, null));
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.BASELINE_CENTER);
        for (int i = 0; i < array.length; i++) {
            Label label = new Label(Integer.toString(array[i]));
            label.setBackground(bg);
            label.setAlignment(Pos.BASELINE_CENTER);
            label.setPrefSize(40, 25);
            label.setStyle(String.format("-fx-border-color: black; -fx-border-width: 1 %d 1 1;",
                    i == array.length - 1 ? 1 : 0));
            label.setFont(Font.font(16));
            hBox.getChildren().add(label);
        }
        hBox.layoutYProperty().bind(heightProperty().divide(2).subtract(25));
        hBox.layoutXProperty().bind(widthProperty().divide(2).subtract(hBox.widthProperty().divide(2)));

        double xLow = low * 40 + 65;
        Group lowArrow = new Group(
                new Line(xLow, 0, xLow, 30),
                new Line(xLow, 30, xLow + 10, 20),
                new Line(xLow, 30, xLow - 10, 20)
        );
        Text lowLabel = new Text(xLow - 10, -5, "low");
        lowLabel.setFont(Font.font(16));
        lowArrow.getChildren().add(lowLabel);
        lowArrow.layoutYProperty().bind(hBox.layoutYProperty().subtract(30));

        double xHigh = high * 40 + 65;
        Group highArrow = new Group(
                new Line(xHigh, 0, xHigh, 70),
                new Line(xHigh, 70, xHigh + 10, 60),
                new Line(xHigh, 70, xHigh - 10, 60)
        );
        Text highLabel = new Text(xHigh - 15, -7, "high");
        highLabel.setFont(Font.font(16));
        highArrow.getChildren().add(highLabel);
        highArrow.layoutYProperty().bind(hBox.layoutYProperty().subtract(70));

        double xPivot = pivot * 40 + 65;
        Group pivotArrow = new Group(
                new Line(xPivot, 0, xPivot, 70),
                new Line(xPivot, 0, xPivot + 10, 10),
                new Line(xPivot, 0, xPivot - 10, 10)
        );
        Text pivotLabel = new Text(xPivot - 15, 82, "pivot");
        pivotLabel.setFont(Font.font(16));
        pivotArrow.getChildren().add(pivotLabel);
        pivotArrow.layoutYProperty().bind(hBox.layoutYProperty().add(27));

        getChildren().addAll(hBox, lowArrow, highArrow, pivotArrow);
    }
}
