package exercises.ch14;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Exercise14_12 extends Application {


    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        HBox root = new HBox();
        root.setPadding(new Insets(10));
        root.setAlignment(Pos.BOTTOM_CENTER);
        root.setSpacing(10);
        root.getChildren().addAll(
                makeBar("Project", 20, Color.RED, stage),
                makeBar("Quiz", 10, Color.BLUE, stage),
                makeBar("Midterm", 30, Color.GREEN, stage),
                makeBar("Final", 40, Color.ORANGE, stage)
        );

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle(getClass().getSimpleName());
        stage.show();
    }

    private VBox makeBar(String name, int percentage, Color color, Stage stage) {
        VBox result = new VBox();
        result.setAlignment(Pos.BOTTOM_CENTER);
        Text title = new Text(String.format("%s -- %d%%", name, percentage));
        title.setFont(Font.font("", FontWeight.BOLD, 20));
        Rectangle bar = new Rectangle();
        bar.setFill(color);
        bar.widthProperty().bind(stage.widthProperty().divide(5));
        bar.heightProperty().bind(stage.heightProperty().multiply(percentage / 100.0));
        result.getChildren().addAll(title, bar);
        return result;
    }

}
