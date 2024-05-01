package exercises.ch25.ex04;

import exercises.ch25.base.BST;
import exercises.ch25.base.TreeNode;

import java.util.*;

public class BSTPreorderViaStack<E> extends BST<E> {
    public BSTPreorderViaStack() {
        super();
    }

    public BSTPreorderViaStack(Comparator<E> c) {
        super(c);
    }

    public BSTPreorderViaStack(E[] objects) {
        super(objects);
    }

    @Override
    public void preorder() {
        if (size == 0) return;
        List<TreeNode<E>> result = new ArrayList<>();
        Deque<TreeNode<E>> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode<E> current = stack.pop();
            TreeNode<E> left = current.getLeft();
            TreeNode<E> right = current.getRight();

            result.add(current);
            if (right != null && !result.contains(right)) {
                stack.push(right);
            }
            if (left != null && !result.contains(left)) {
                stack.push(left);
            }
        }
        result.forEach(t -> System.out.print(t.getElement() + " "));
        System.out.println();
    }
}
