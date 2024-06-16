package exercises.ch26.ex01;

import exercises.ch26.base.AVLTree;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Exercise26_1 extends Application {
    private final AVLTreeView<Integer> treeView = new AVLTreeView<>();

    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root = makeRoot();
        stage.setScene(new Scene(root, 600, 500));
        stage.setTitle(getClass().getSimpleName());
        stage.show();
        AVLTree<Integer> tree = new AVLTree<>(new Integer[]{60, 55, 100, 45, 67, 107, 87});
        treeView.displayTree(tree);
    }

    private BorderPane makeRoot() {
        BorderPane root = new BorderPane();
        root.setCenter(treeView);
        return root;
    }
}
