package exercises.ch25.ex13;

import exercises.ch25.base.TreeNode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

import java.util.stream.Collectors;

public class BSTView extends Pane {
    private BST<Integer> tree = new BST<>();
    private double radius = 15; // Tree node radius
    private double vGap = 50;

    public BSTView(BST<Integer> tree) {
        this.tree = tree;
        setStatus("Tree is empty");
    }

    public void setStatus(String msg) {
        getChildren().add(new Text(20, 20, msg));
    }

    public void displayTree() {
        this.getChildren().clear(); // Clear the pane
        if (tree.getRoot() != null) {
            // Display tree recursively
            displayTree(tree.getRoot(), getWidth() / 2, vGap, getWidth() / 4);
        }
    }

    public String getInorderTraversal() {
        return tree.inorder().stream()
                .map(e -> Integer.toString(e))
                .collect(Collectors.joining(", "));
    }

    public String getPreorderTraversal() {
        return tree.preorder().stream()
                .map(e -> Integer.toString(e))
                .collect(Collectors.joining(", "));
    }

    public String getPostorderTraversal() {
        return tree.postorder().stream()
                .map(e -> Integer.toString(e))
                .collect(Collectors.joining(", "));
    }

    /**
     * Display a subtree rooted at position (x, y)
     */
    private void displayTree(TreeNode<Integer> root, double x, double y, double hGap) {
        if (root.getLeft() != null) {
            // Draw a line to the left node
            getChildren().add(new Line(x - hGap, y + vGap, x, y));
            // Draw the left subtree recursively
            displayTree(root.getLeft(), x - hGap, y + vGap, hGap / 2);
        }

        if (root.getRight() != null) {
            // Draw a line to the right node
            getChildren().add(new Line(x + hGap, y + vGap, x, y));
            // Draw the right subtree recursively
            displayTree(root.getRight(), x + hGap, y + vGap, hGap / 2);
        }

        // Display a node
        Circle circle = new Circle(x, y, radius);
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);
        String s = root.getElement().toString();
        double length = s.length() * 7;
        getChildren().addAll(circle, new Text(x - length / 2, y + 4, s));
    }
}
