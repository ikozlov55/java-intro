package exercises.ch26;


import exercises.ch22.ex07.ClosestPair;
import exercises.ch22.ex07.Pair;
import exercises.ch25.base.BST;
import exercises.ch25.base.TreeNode;
import exercises.ch26.base.AVLTree;
import exercises.ch26.ex01.Exercise26_1;
import exercises.ch26.ex03.Exercise26_3;
import exercises.ch26.ex06.ClosestPairAVL;
import exercises.ch26.ex07.MyBST;
import exercises.utils.Utils;
import javafx.geometry.Point2D;

import java.util.*;
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
        (The kth smallest element) You can find the kth smallest element in a BST in
        O(n) time from an inorder iterator. For an AVL tree, you can find it in O(log n)
        time. To achieve this, add a new data field named size in AVLTreeNode to
        store the number of nodes in the subtree rooted at this node. Note the size of a
        node v is one more than the sum of the sizes of its two children. Figure 26.12
        shows an AVL tree and the size value for each node in the tree.
        In the AVLTree class, add the following method to return the kth smallest element
        in the tree:
            public E find(int k)
        The method returns null if k < 1 or k > the size of the tree. This
        method can be implemented using the recursive method find(k, root), which
        returns the kth smallest element in the tree with the specified root. Let A and B
        be the left and right children of the root, respectively. Assuming the tree is not
        empty and k … root.size, find(k, root) can be recursively defined as follows:
            find(k, root) = E
                root.element, if A is null and k is 1;
                B.element, if A is null and k is 2;
                find(k, A), if k <= A.size;
                root.element, if k = A.size + 1;
                find(k - A.size - 1, B), if k > A.size + 1;
        Modify the insert and delete methods in AVLTree to set the correct value
        for the size property in each node. The insert and delete methods will still
        be in O(log n) time. The find(k) method can be implemented in O(log n) time.
        Therefore, you can find the kth smallest element in an AVL tree in O(log n) time.
        Test your program using the code at https://liveexample.pearsoncmg.com/test/Exercise26_05.txt.
     */
    public static void ex5() {
        exercises.ch26.ex05.AVLTree<Double> tree = new exercises.ch26.ex05.AVLTree<>();
        Scanner input = new Scanner(System.in);
        System.out.print("Enter 15 numbers: ");
        for (int i = 0; i < 15; i++) {
            tree.insert(input.nextDouble());
        }

        System.out.print("Enter k: ");
        int k = input.nextInt();
        System.out.println("The " + k + "th smallest number is " + tree.find(k));
    }

    /*
        (Closest pair of points) Section 22.8 introduced an algorithm for finding a clos-
        est pair of points in O(nlogn) time using a divide-and-conquer approach. The
        algorithm was implemented using recursion with a lot of overhead. Using an
        AVL tree, you can solve the same problem in O(nlogn) time. Implement the
        algorithm using an AVLTree.
     */
    public static void ex6() {
        Point2D[] points = {
                new Point2D(0, 0),
                new Point2D(2, 7),
                new Point2D(1, 5),
                new Point2D(3, 2),
                new Point2D(2, 5),
                new Point2D(2, 0),
                new Point2D(5, 5),
                new Point2D(3, 8),
        };

        Pair closestPair = ClosestPair.getClosestPair(points);
        System.out.printf("Closest pair found using Divide-and-Conquer algorithm is: %s\n", closestPair);
        Pair closestPairBruteForce = ClosestPair.getClosestPairBruteForce(points);
        System.out.printf("Closest pair found using brute force algorithm is: %s\n", closestPairBruteForce);
        Pair closestPairAVL = ClosestPairAVL.getClosestPair(points);
        System.out.printf("Closest pair found using AVL tree is: %s\n", closestPairAVL);
    }

    /*
        (Test AVL tree) Define a new class named MyBST that extends the BST class with
        the following method:
        // Returns true if the tree is an AVL tree
        public boolean isAVLTree()
        Use https://liveexample.pearsoncmg.com/test/Exercise26_07.txt to test your code.
     */
    public static void ex7() {
        MyBST<Integer> tree = new MyBST<>();
        System.out.print("How many integers to be inserted into the tree? ");
        Scanner input = new Scanner(System.in);
        int number = input.nextInt();
        System.out.print("Enter " + number + " integers: ");
        int count = 0;
        while (count++ < number) {
            int e = input.nextInt();
            tree.insert(e);
        }
        System.out.println("Is this tree an AVL tree? " + tree.isAVLTree());
    }
}
