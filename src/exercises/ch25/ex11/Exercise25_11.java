package exercises.ch25.ex11;

import exercises.ch25.base.BST;
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

public class Exercise25_11 extends Application {
    private final BSTView<Integer> bstView = new BSTView<>(new BST<>());
    private final TextField keyTF = new TextField();
    private final Button insertBTN = new Button("Insert");
    private final Button deleteBTN = new Button("Delete");

    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root = makeRoot();
        insertBTN.setOnAction(this::insert);
        deleteBTN.setOnAction(this::delete);
        stage.setScene(new Scene(root, 450, 350));
        stage.setTitle(getClass().getSimpleName());
        stage.show();
    }

    private BorderPane makeRoot() {
        BorderPane root = new BorderPane();
        root.setCenter(bstView);
        BorderPane.setMargin(bstView, new Insets(5));
        keyTF.setPrefColumnCount(3);
        HBox controls = new HBox(5, new Label("Enter a key:"), keyTF, insertBTN, deleteBTN);
        controls.setPadding(new Insets(5));
        controls.setAlignment(Pos.BASELINE_CENTER);
        root.setBottom(controls);

        return root;
    }

    private void insert(ActionEvent e) {
        try {
            int value = Integer.parseInt(keyTF.getText());
            bstView.insert(value);
        } catch (NumberFormatException ignored) {
        } finally {
            keyTF.clear();
        }
    }

    private void delete(ActionEvent e) {
        try {
            int value = Integer.parseInt(keyTF.getText());
            bstView.delete(value);
        } catch (NumberFormatException ignored) {
        } finally {
            keyTF.clear();
        }
    }
}
