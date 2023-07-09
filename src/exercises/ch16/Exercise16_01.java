package exercises.ch16;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.List;

public class Exercise16_01 extends Application {
    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root = new BorderPane();
        Text text = new Text(50, 60, "Programming is fun");
        text.setFont(Font.font("", FontWeight.BOLD, 25));
        Pane textPane = new Pane(text);
        textPane.setStyle("-fx-border-color: gray");
        root.setCenter(textPane);

        HBox toggles = new HBox(10);
        toggles.setAlignment(Pos.CENTER);
        ToggleGroup group = new ToggleGroup();
        for (String s : List.of("Red", "Yellow", "Black", "Orange", "Green")) {
            RadioButton button = new RadioButton(s);
            button.setToggleGroup(group);
            button.setOnAction(e -> {
                if (button.isSelected()) {
                    text.setFill(Color.valueOf(s));
                }
            });
            toggles.getChildren().add(button);
        }
        root.setTop(toggles);

        Button buttonLeft = new Button("<=");
        buttonLeft.setOnAction(e -> text.setX(text.getX() - 5));
        Button buttonRight = new Button("=>");
        buttonRight.setOnAction(e -> text.setX(text.getX() + 5));
        HBox buttonsBox = new HBox(5);
        buttonsBox.getChildren().addAll(buttonLeft, buttonRight);
        buttonsBox.setAlignment(Pos.CENTER);
        root.setBottom(buttonsBox);

        Scene scene = new Scene(root, 400, 150);
        stage.setScene(scene);
        stage.setTitle(getClass().getSimpleName());
        stage.show();
    }
}
