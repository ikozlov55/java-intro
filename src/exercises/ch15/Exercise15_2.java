package exercises.ch15;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Exercise15_2 extends Application {
    private double angle = 0;

    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Rectangle rec = new Rectangle(100, 50);
        rec.setStroke(Color.BLACK);
        rec.setFill(Color.WHITE);

        Button button = new Button("Rotate");
        button.setMinSize(100, 20);
        button.setOnAction(e -> {
            angle -= 15;
            rec.setRotate(angle);
        });

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(20));
        root.setCenter(rec);
        root.setBottom(button);
        BorderPane.setAlignment(button, Pos.CENTER);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setHeight(300);
        stage.setWidth(400);
        stage.setTitle(getClass().getSimpleName());
        stage.show();
    }


}
