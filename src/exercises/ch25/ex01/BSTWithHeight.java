package exercises.ch25.ex01;

import exercises.ch25.base.BST;
import exercises.ch25.base.TreeNode;

import java.util.Comparator;

public class BSTWithHeight<E> extends BST<E> {
    public BSTWithHeight() {
        super();
    }

    public BSTWithHeight(Comparator<E> c) {
        super(c);
    }

    public BSTWithHeight(E[] objects) {
        super(objects);
    }

    public int height() {
        return height(root, 0);
    }

    private int height(TreeNode<E> root, int n) {
        if (root == null) {
            return -1;
        } else if (root.getLeft() == null && root.getRight() == null) {
            return n;
        }
        return Math.max(height(root.getLeft(), n + 1), height(root.getRight(), n + 1));
    }
}
