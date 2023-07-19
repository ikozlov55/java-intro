package exercises.ch16;

import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Exercise16_17 extends Application {

    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root = new BorderPane();
        Label label = new Label("Show Colors");
        label.setPadding(new Insets(20));
        label.setFont(Font.font(20));
        root.setCenter(label);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.BASELINE_CENTER);
        grid.setHgap(5);
        grid.setVgap(5);
        grid.setPadding(new Insets(10));
        Slider redSlider = new Slider(0, 255, 0);
        redSlider.setBlockIncrement(1);
        Slider greenSlider = new Slider(0, 255, 0);
        greenSlider.setBlockIncrement(1);
        Slider blueSlider = new Slider(0, 255, 0);
        blueSlider.setBlockIncrement(1);
        Slider opacitySlider = new Slider(0, 1, 1);
        opacitySlider.setBlockIncrement(0.1);
        grid.add(new Label("Red"), 0, 0);
        grid.add(redSlider, 1, 0);
        grid.add(new Label("Green"), 0, 1);
        grid.add(greenSlider, 1, 1);
        grid.add(new Label("Blue"), 0, 2);
        grid.add(blueSlider, 1, 2);
        grid.add(new Label("Opacity"), 0, 3);
        grid.add(opacitySlider, 1, 3);
        root.setBottom(grid);

        InvalidationListener listener = ov -> {
            Color color = Color.rgb(
                    (int) redSlider.getValue(),
                    (int) greenSlider.getValue(),
                    (int) blueSlider.getValue(),
                    opacitySlider.getValue()
            );
            label.setTextFill(color);
        };

        redSlider.valueProperty().addListener(listener);
        greenSlider.valueProperty().addListener(listener);
        blueSlider.valueProperty().addListener(listener);
        opacitySlider.valueProperty().addListener(listener);

        stage.setScene(new Scene(root, 300, 200));
        stage.setTitle(getClass().getSimpleName());
        stage.show();
    }
}
