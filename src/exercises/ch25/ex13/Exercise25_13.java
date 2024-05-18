package exercises.ch25.ex13;


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

public class Exercise25_13 extends Application {
    private BST<Integer> tree = new BST<>();

    private final BSTView bstView = new BSTView(tree);
    private final Label traversalLabel = new Label("");
    private final TextField keyTF = new TextField();
    private final Button insertBTN = new Button("Insert");
    private final Button deleteBTN = new Button("Delete");
    private final Button showInorderBTN = new Button("Show Inorder");
    private final Button showPreorderBTN = new Button("Show Preorder");
    private final Button showPostorderBTN = new Button("Show Postorder");

    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root = makeRoot();
        insertBTN.setOnAction(this::insert);
        deleteBTN.setOnAction(this::delete);
        showInorderBTN.setOnAction(this::showInorder);
        showPreorderBTN.setOnAction(this::showPreorder);
        showPostorderBTN.setOnAction(this::showPostorder);
        stage.setScene(new Scene(root, 600, 500));
        stage.setTitle(getClass().getSimpleName());
        stage.show();
    }

    private BorderPane makeRoot() {
        BorderPane root = new BorderPane();
        root.setTop(traversalLabel);
        BorderPane.setMargin(traversalLabel, new Insets(5));
        BorderPane.setAlignment(traversalLabel, Pos.CENTER);
        root.setCenter(bstView);
        BorderPane.setMargin(bstView, new Insets(5));
        keyTF.setPrefColumnCount(3);
        HBox controls = new HBox(5, new Label("Enter a key:"), keyTF, insertBTN, deleteBTN, showInorderBTN,
                showPreorderBTN, showPostorderBTN);
        controls.setPadding(new Insets(5));
        controls.setAlignment(Pos.BASELINE_CENTER);
        root.setBottom(controls);

        return root;
    }

    private void insert(ActionEvent e) {
        try {
            int key = Integer.parseInt(keyTF.getText());
            String msg;
            if (tree.search(key)) {
                msg = String.format("%d is already in the tree", key);
            } else {
                tree.insert(key);
                msg = String.format("%d is inserted in the tree", key);
            }
            bstView.displayTree();
            bstView.setStatus(msg);
        } catch (NumberFormatException ignored) {
        } finally {
            keyTF.clear();
        }
    }

    private void delete(ActionEvent e) {
        if (keyTF.getText().isEmpty()) return;
        try {
            int key = Integer.parseInt(keyTF.getText());
            String msg;
            if (!tree.search(key)) {
                msg = String.format("%d is not in the tree", key);
            } else {
                tree.delete(key);
                msg = String.format("%d is deleted in the tree", key);
            }
            bstView.displayTree();
            bstView.setStatus(msg);
        } catch (NumberFormatException ignored) {
        } finally {
            keyTF.clear();
        }
    }

    private void showInorder(ActionEvent e) {
        String text = String.format("Inorder: [%s]", bstView.getInorderTraversal());
        traversalLabel.setText(text);
    }

    private void showPreorder(ActionEvent e) {
        String text = String.format("Preorder: [%s]", bstView.getPreorderTraversal());
        traversalLabel.setText(text);
    }

    private void showPostorder(ActionEvent e) {
        String text = String.format("Postorder: [%s]", bstView.getPostorderTraversal());
        traversalLabel.setText(text);
    }
}
