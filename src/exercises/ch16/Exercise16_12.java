package exercises.ch16;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Exercise16_12 extends Application {
    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root = new BorderPane();
        TextArea textArea = new TextArea();
        root.setCenter(textArea);
        CheckBox editableBox = new CheckBox("Editable");
        editableBox.setSelected(true);
        editableBox.setOnAction(e -> textArea.setEditable(editableBox.isSelected()));
        CheckBox wrapBox = new CheckBox("Wrap");
        wrapBox.setOnAction(e -> textArea.setWrapText(wrapBox.isSelected()));
        HBox controls = new HBox(10, editableBox, wrapBox);
        controls.setAlignment(Pos.BASELINE_CENTER);
        controls.setPadding(new Insets(5));
        root.setBottom(controls);
        stage.setScene(new Scene(root, 400, 300));
        stage.setTitle(getClass().getSimpleName());
        stage.show();
    }
}
