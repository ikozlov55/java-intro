package exercises.ch16;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.List;
import java.util.stream.IntStream;

public class Exercise16_14 extends Application {
    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root = new BorderPane();
        Label label = new Label("Programming is fun");
        root.setCenter(label);

        HBox controls = new HBox(5);
        controls.setPadding(new Insets(5));
        controls.setAlignment(Pos.BASELINE_CENTER);
        ComboBox<String> fontNameBox = new ComboBox<>(FXCollections.observableArrayList(Font.getFontNames()));
        List<Integer> fontSizes = IntStream.rangeClosed(1, 100).boxed().toList();
        ComboBox<Integer> fontSizeBox = new ComboBox<>(FXCollections.observableArrayList(fontSizes));
        controls.getChildren().addAll(new Label("Font Name"), fontNameBox, new Label("Font Size"), fontSizeBox);
        root.setTop(controls);

        CheckBox boldBox = new CheckBox("Bold");
        CheckBox italicBox = new CheckBox("Italic");
        HBox checkBoxes = new HBox(5, boldBox, italicBox);
        checkBoxes.setPadding(new Insets(5));
        checkBoxes.setAlignment(Pos.BASELINE_CENTER);
        root.setBottom(checkBoxes);

        EventHandler<ActionEvent> handler = e -> {
            label.setFont(Font.font(
                    fontNameBox.getValue(),
                    boldBox.isSelected() ? FontWeight.BOLD : FontWeight.NORMAL,
                    italicBox.isSelected() ? FontPosture.ITALIC : FontPosture.REGULAR,
                    fontSizeBox.getValue() != null ? fontSizeBox.getValue() : label.getFont().getSize()
            ));
        };
        boldBox.setOnAction(handler);
        italicBox.setOnAction(handler);
        fontNameBox.setOnAction(handler);
        fontSizeBox.setOnAction(handler);

        stage.setScene(new Scene(root));
        stage.setHeight(200);
        stage.setTitle(getClass().getSimpleName());
        stage.show();
    }
}
