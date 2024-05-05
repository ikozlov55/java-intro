package exercises.ch25.ex11;

import exercises.ch25.base.BST;
import exercises.ch25.base.TreeNode;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class BSTView<E> extends Pane {
    private final Label label = new Label("XXX");
    BST<E> tree;
    private final double radius = 16;

    public BSTView() {
        tree = new BST<>();
        draw();
    }

    public BSTView(BST<E> tree) {
        this.tree = tree;
        draw();
    }

    public void insert(E value) {
        tree.insert(value);
        label.setText(String.format("%s is inserted in the tree", value));
        draw();
    }

    public void delete(E value) {
        boolean result = tree.delete(value);
        if (result) {
            label.setText(String.format("%s is removed from the tree", value));
            draw();
        } else {
            label.setText(String.format("%s is not on the tree", value));
        }
    }

    private void draw() {
        getChildren().clear();
        getChildren().add(label);
        if (tree.getRoot() == null) return;
        drawNode(tree.getRoot(), radius, getHeight() / 2);
    }

    private void drawNode(TreeNode<E> root, double x, double y) {
        double gap = 50;
        if (root.getLeft() != null) {
            getChildren().add(new Line(x, y, x + gap, y + gap));
            drawNode(root.getLeft(), x + gap, y + gap);
        }
        if (root.getRight() != null) {
            getChildren().add(new Line(x, y, x + gap, y - gap));
            drawNode(root.getRight(), x + gap, y - gap);
        }

        Circle circle = new Circle(x, y, radius);
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);
        String s = root.getElement().toString();
        double length = s.length() * 7;
        getChildren().addAll(circle, new Text(x - length / 2, y + 4, s));
    }
}
