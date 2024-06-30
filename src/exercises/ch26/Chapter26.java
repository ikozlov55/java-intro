package exercises.ch26;

import exercises.ch25.base.BST;
import exercises.ch25.base.TreeNode;
import exercises.ch26.base.AVLTree;
import exercises.ch26.ex01.Exercise26_1;
import exercises.ch26.ex03.Exercise26_3;
import exercises.utils.Utils;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;


public class Chapter26 {

    /*
        (Display AVL tree graphically) Write a program that displays an AVL tree along
        with its balance factor for each node.
     */
    public static void ex1() {
        Exercise26_1.run();
    }

    /*
        (Compare performance) Write a test program that randomly generates 500,000
        numbers and inserts them into a BST, reshuffles the 500,000 numbers and per-
        forms a search, and reshuffles the numbers again before deleting them from
        the tree. Write another test program that does the same thing for an AVLTree.
        Compare the execution times of these two programs.
     */
    public static void ex2() {
        BST<Integer> bsTree = new BST<>();
        AVLTree<Integer> avlTree = new AVLTree<>();
        double t1 = Utils.testTime(() -> treeTest(bsTree));
        System.out.printf("BST time: %.2f\n", t1);
        double t2 = Utils.testTime(() -> treeTest(avlTree));
        System.out.printf("AVL Tree time: %.2f\n", t2);
    }

    private static void treeTest(BST<Integer> tree) {
        int n = 500_000;
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = (int) (Math.random() * 100);
            tree.insert(numbers[i]);
        }
        shuffle(numbers);
        for (int i = 0; i < n; i++) {
            tree.search(numbers[i]);
        }
        shuffle(numbers);
        for (int i = 0; i < n; i++) {
            tree.delete(numbers[i]);
        }
    }

    private static void shuffle(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int index = (int) (Math.random() * array.length);
            int temp = array[i];
            array[i] = array[index];
            array[index] = temp;
        }
    }

    /*
        (AVL tree animation) Write a program that animates the AVL tree insert,
        delete, and search methods, as shown in Figure 26.2.
     */
    public static void ex3() {
        Exercise26_3.run();
    }

    /*
        (Parent reference for BST) Suppose the TreeNode class defined in BST con-
        tains a reference to the node’s parent, as shown in Programming Exercise
        25.15. Implement the AVLTree class to support this change. Write a test
        program that adds numbers 1, 2, . . . , 100 to the tree and displays the paths
        for all leaf nodes.
     */
    public static void ex4() {
        exercises.ch26.ex04.AVLTree<Integer> tree = new exercises.ch26.ex04.AVLTree<>();
        for (int i = 1; i <= 100; i++) {
            tree.insert(i);
        }
        List<TreeNode<Integer>> leafs = new ArrayList<>();
        Deque<TreeNode<Integer>> stack = new ArrayDeque<>();
        stack.push(tree.getRoot());
        while (!stack.isEmpty()) {
            TreeNode<Integer> current = stack.pop();
            if (current.getLeft() != null) {
                stack.push(current.getLeft());
            }
            if (current.getRight() != null) {
                stack.push(current.getRight());
            }
            if (current.isLeaf()) {
                leafs.add(current);
            }
        }

        for (TreeNode<Integer> node : leafs) {
            String s = tree.path(node.getElement()).stream()
                    .map(n -> Integer.toString(n.getElement()))
                    .collect(Collectors.joining(" - "));
            System.out.println(s);
        }
    }

    /*

     */
    public static void ex5() {

    }

    /*

     */
    public static void ex6() {

    }

    /*

     */
    public static void ex7() {

    }
}
