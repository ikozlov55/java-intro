package exercises.ch14;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


public class Exercise14_4 extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(20, 5, 20, 5));
        Font font = Font.font("Times new Roman", FontWeight.BOLD, FontPosture.ITALIC, 22);
        for (int i = 0; i < 5; i++) {
            Label label = new Label("Java");
            label.setFont(font);
            label.setRotate(90);
            Color color = new Color(
                    (float) Math.random(),
                    (float) Math.random(),
                    (float) Math.random(),
                    (float) Math.random()
            );

            label.setTextFill(color);
            hbox.getChildren().add(label);
        }
        Scene scene = new Scene(hbox);
        stage.setScene(scene);
        stage.setTitle("Exercise14_5");
        stage.show();
    }

    public static void run() {
        Application.launch();
    }
}
