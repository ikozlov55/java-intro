package exercises.ch25.ex02;

import exercises.ch25.base.BST;
import exercises.ch25.base.TreeNode;

import java.util.*;

public class BSTInorderViaStack<E> extends BST<E> {
    public BSTInorderViaStack() {
        super();
    }

    public BSTInorderViaStack(Comparator<E> c) {
        super(c);
    }

    public BSTInorderViaStack(E[] objects) {
        super(objects);
    }

    @Override
    public void inorder() {
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
            } else {
                stack.pop();
                result.add(current);
                if (right != null) {
                    stack.push(right);
                }
            }
        }
        result.forEach(t -> System.out.print(t.getElement() + " "));
        System.out.println();
    }
}
