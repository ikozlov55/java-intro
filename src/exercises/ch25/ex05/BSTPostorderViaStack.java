package exercises.ch25.ex05;

import exercises.ch25.base.BST;
import exercises.ch25.base.TreeNode;

import java.util.*;

public class BSTPostorderViaStack<E> extends BST<E> {
    public BSTPostorderViaStack() {
        super();
    }

    public BSTPostorderViaStack(Comparator<E> c) {
        super(c);
    }

    public BSTPostorderViaStack(E[] objects) {
        super(objects);
    }

    @Override
    public void postorder() {
        if (size == 0) return;
        List<TreeNode<E>> result = new ArrayList<>();
        Deque<TreeNode<E>> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode<E> current = stack.peek();
            TreeNode<E> left = current.getLeft();
            TreeNode<E> right = current.getRight();

            if (left != null && !result.contains(left)) {
                stack.push(left);
            } else if (right != null && !result.contains(right)) {
                stack.push(right);
            } else {
                result.add(stack.pop());
            }
        }
        result.forEach(t -> System.out.print(t.getElement() + " "));
        System.out.println();
    }
}
