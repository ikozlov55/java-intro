package exercises.ch25.ex06;

import exercises.ch25.base.BST;
import exercises.ch25.base.TreeNode;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;

public class BSTWithNumberOfLeaves<E> extends BST<E> {
    public BSTWithNumberOfLeaves() {
        super();
    }

    public BSTWithNumberOfLeaves(Comparator<E> c) {
        super(c);
    }

    public BSTWithNumberOfLeaves(E[] objects) {
        super(objects);
    }

    public int getNumberOfLeaves() {
        if (root == null) return 0;
        Deque<TreeNode<E>> stack = new ArrayDeque<>();
        stack.push(root);
        int count = 0;

        while (!stack.isEmpty()) {
            TreeNode<E> current = stack.pop();
            TreeNode<E> left = current.getLeft();
            TreeNode<E> right = current.getRight();
            if (current.isLeaf()) {
                count++;
                continue;
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
