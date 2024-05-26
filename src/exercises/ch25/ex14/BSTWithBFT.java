package exercises.ch25.ex14;

import exercises.ch25.base.BST;
import exercises.ch25.base.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class BSTWithBFT<E> extends BST<E> {
    public BSTWithBFT() {
        super();
    }

    public BSTWithBFT(E[] array) {
        super();
        for (E item : array) {
            insert(item);
        }
    }

    public void breadthFirstTraversal() {
        if (size == 0) return;
        List<E> result = new ArrayList<>();
        Queue<TreeNode<E>> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode<E> current = queue.poll();
            result.add(current.getElement());
            if (current.getLeft() != null) {
                queue.add(current.getLeft());
            }
            if (current.getRight() != null) {
                queue.add(current.getRight());
            }
        }
        for (E e : result) {
            System.out.print(e + " ");
        }
    }
}
