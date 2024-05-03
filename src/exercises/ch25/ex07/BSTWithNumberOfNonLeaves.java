package exercises.ch25.ex07;

import exercises.ch25.base.BST;
import exercises.ch25.base.TreeNode;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;

public class BSTWithNumberOfNonLeaves<E> extends BST<E> {
    public BSTWithNumberOfNonLeaves() {
        super();
    }

    public BSTWithNumberOfNonLeaves(Comparator<E> c) {
        super(c);
    }

    public BSTWithNumberOfNonLeaves(E[] objects) {
        super(objects);
    }

    public int getNumberOfNonLeaves() {
        if (root == null) return 0;
        Deque<TreeNode<E>> stack = new ArrayDeque<>();
        stack.push(root);
        int count = 0;

        while (!stack.isEmpty()) {
            TreeNode<E> current = stack.pop();
            TreeNode<E> left = current.getLeft();
            TreeNode<E> right = current.getRight();
            if (!current.isLeaf()) {
                count++;
            }
            if (left != null) {
                stack.push(left);
            }
            if (right != null) {
                stack.push(right);
            }
        }
        return count;
    }
}
