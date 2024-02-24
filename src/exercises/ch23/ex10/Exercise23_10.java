package exercises.ch23.ex10;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Exercise23_10 extends Application {
    private final TextField keyField = new TextField();
    private final Button insertButton = new Button("Insert");
    private final Button removeButton = new Button("Remove the root");
    private final HeapPane<Integer> heapPane = new HeapPane<>();

    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root = makeRoot();
        insertButton.setOnAction(this::insertHandler);
        removeButton.setOnAction(this::removeHandler);
        stage.setScene(new Scene(root, 600, 400));
        stage.setTitle(getClass().getSimpleName());
        stage.show();
    }

    private BorderPane makeRoot() {
        BorderPane root = new BorderPane();
        root.setCenter(heapPane);
        keyField.setPrefColumnCount(3);
        HBox controls = new HBox(5, new Label("Enter a key:"), keyField, insertButton, removeButton);
        controls.setPadding(new Insets(5));
        controls.setAlignment(Pos.BASELINE_CENTER);
        root.setBottom(controls);
        return root;
    }

    private void insertHandler(ActionEvent e) {
        try {
            int value = Integer.parseInt(keyField.getText());
            heapPane.add(value);
        } catch (NumberFormatException ignored) {
        } finally {
            keyField.clear();
        }
    }

    private void removeHandler(ActionEvent e) {
        heapPane.remove();
    }
}
