package exercises.ch26.ex01;

import exercises.ch26.base.AVLTree;
import exercises.ch26.base.AVLTreeNode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class AVLTreeView<E> extends Pane {
    AVLTree<E> tree;
    private final double vGap = 50;

    public AVLTreeView() {

    }

    public void displayTree(AVLTree<E> tree) {
        this.tree = tree;
        AVLTreeNode<E> root = (AVLTreeNode<E>) tree.getRoot();
        getChildren().clear();
        draw(root, getWidth() / 2, vGap, getWidth() / 4);
    }

    private void draw(AVLTreeNode<E> root, double x, double y, double hGap) {
        if (root.getLeft() != null) {
            Line line = new Line(x - hGap, y + vGap, x, y);
            getChildren().add(line);
            draw(root.getLeft(), x - hGap, y + vGap, hGap / 2);
        }

        if (root.getRight() != null) {
            Line line = new Line(x + hGap, y + vGap, x, y);
            getChildren().add(line);
            draw(root.getRight(), x + hGap, y + vGap, hGap / 2);
        }

        double radius = 15;
        Circle circle = new Circle(x, y, radius);
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);
        String s = String.valueOf(root.getElement());
        double length = s.length() * 7;
        Text elementLabel = new Text(x - length / 2, y + 4, String.valueOf(root.getElement()));
        Text balanceFactorLabel = new Text(x - radius * 2, y + 4, String.valueOf(tree.balanceFactor(root)));
        getChildren().addAll(circle, elementLabel, balanceFactorLabel);
    }
}
