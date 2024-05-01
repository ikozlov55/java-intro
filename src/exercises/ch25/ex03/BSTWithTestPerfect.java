package exercises.ch25.ex03;

import exercises.ch25.base.BST;
import exercises.ch25.base.TreeNode;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Queue;

public class BSTWithTestPerfect<E> extends BST<E> {

    public BSTWithTestPerfect() {
        super();
    }

    public BSTWithTestPerfect(Comparator<E> c) {
        super(c);
    }

    public BSTWithTestPerfect(E[] objects) {
        super(objects);
    }

    public boolean isPerfectBST() {
        if (root == null) return false;
        Queue<TreeNode<E>> currentLevel = new ArrayDeque<>();
        currentLevel.add(root);
        int i = 0;
        while (true) {
            if (currentLevel.size() != Math.pow(2, i)) {
                return false;
            }
            Queue<TreeNode<E>> nextLevel = new ArrayDeque<>();
            while (!currentLevel.isEmpty()) {
                TreeNode<E> tree = currentLevel.poll();
                if (tree.getLeft() != null) nextLevel.add(tree.getLeft());
                if (tree.getRight() != null) nextLevel.add(tree.getRight());
            }
            if (nextLevel.isEmpty()) {
                return true;
            }
            i++;
            currentLevel = new ArrayDeque<>(nextLevel);
        }
    }
}
