package exercises.ch16;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.Arrays;

public class Exercise16_15 extends Application {

    private final ComboBox<String> contentDisplayCB = new ComboBox<>(
            FXCollections.observableArrayList(
                    Arrays.stream(ContentDisplay.values()).map(ContentDisplay::toString).toList()
            )
    );
    private final TextField textGapTF = new TextField();
    private final Label label = new Label("Grapes", new ImageView(new Image("resources/image/grapes.gif")));

    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root = new BorderPane();
        textGapTF.setPrefColumnCount(3);
        contentDisplayCB.setValue(label.getContentDisplay().toString());
        HBox controls = new HBox(
                5,
                new Label("Content Display: "),
                contentDisplayCB,
                new Label("Graphic Text Gap: "), textGapTF
        );
        controls.setAlignment(Pos.BASELINE_CENTER);
        controls.setPadding(new Insets(5));
        root.setTop(controls);

        contentDisplayCB.setOnAction(this::setContentDisplay);
        textGapTF.setOnAction(this::setTextGap);

        root.setCenter(label);
        stage.setScene(new Scene(root, 400, 200));
        stage.setTitle(getClass().getSimpleName());
        stage.show();
    }


    private void setContentDisplay(ActionEvent e) {
        label.setContentDisplay(ContentDisplay.valueOf(contentDisplayCB.getValue()));
    }

    private void setTextGap(ActionEvent e) {
        label.setGraphicTextGap(Double.parseDouble(textGapTF.getText()));
    }
}
