package exercises.ch26.ex07;

import exercises.ch25.base.BST;
import exercises.ch25.base.TreeNode;

public class MyBST<E> extends BST<E> {
    public int height() {
        return height(root);
    }

    public int height(TreeNode<E> root) {
        if (root == null)
            return -1;
        else
            return 1 + Math.max(height(root.getLeft()), height(root.getRight()));
    }

    public boolean isAVLTree() {
        return isAVLTree(root);
    }

    public boolean isAVLTree(TreeNode<E> root) {
        if (root.isLeaf()) {
            return true;
        }
        return Math.abs(height(root.getRight()) - height(root.getLeft())) <= 1;
    }
}
