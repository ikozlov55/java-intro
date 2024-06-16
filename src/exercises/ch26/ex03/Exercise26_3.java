package exercises.ch26.ex03;

import exercises.ch26.base.AVLTree;
import exercises.ch26.ex01.AVLTreeView;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Exercise26_3 extends Application {
    AVLTree<Integer> tree = new AVLTree<>();
    private final AVLTreeView<Integer> treeView = new AVLTreeView<>();
    private final TextField keyTF = new TextField();
    private final Button searchBTN = new Button("Search");
    private final Button insertBTN = new Button("Insert");
    private final Button removeBTN = new Button("Remove");

    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root = makeRoot();
        searchBTN.setOnAction(this::search);
        insertBTN.setOnAction(this::insert);
        removeBTN.setOnAction(this::remove);
        stage.setScene(new Scene(root, 600, 500));
        stage.setTitle(getClass().getSimpleName());
        stage.show();
    }

    private BorderPane makeRoot() {
        BorderPane root = new BorderPane();
        keyTF.setPrefColumnCount(4);
        HBox controls = new HBox(5, new Label("Enter a key:"), keyTF, searchBTN, insertBTN, removeBTN);
        controls.setAlignment(Pos.BASELINE_CENTER);
        BorderPane.setAlignment(controls, Pos.CENTER);
        BorderPane.setMargin(controls, new Insets(5));
        root.setCenter(treeView);
        root.setBottom(controls);
        return root;
    }

    private void search(ActionEvent e) {
        try {
            int key = Integer.parseInt(keyTF.getText());
            String msg;
            if (tree.search(key)) {
                msg = String.format("%d is in the tree", key);
            } else {
                msg = String.format("%d is not in the tree", key);
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION, msg);
            alert.setHeaderText("");
            alert.showAndWait();
        } catch (NumberFormatException ignored) {
        } finally {
            keyTF.clear();
        }
    }

    private void insert(ActionEvent e) {
        try {
            int key = Integer.parseInt(keyTF.getText());
            tree.insert(key);
            treeView.displayTree(tree);
        } catch (NumberFormatException ignored) {
        } finally {
            keyTF.clear();
        }
    }

    private void remove(ActionEvent e) {
        try {
            int key = Integer.parseInt(keyTF.getText());
            tree.delete(key);
            treeView.displayTree(tree);
        } catch (NumberFormatException ignored) {
        } finally {
            keyTF.clear();
        }
    }
}
