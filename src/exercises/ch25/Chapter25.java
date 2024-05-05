package exercises.ch25;

import exercises.ch25.base.BST;
import exercises.ch25.ex01.BSTWithHeight;
import exercises.ch25.ex02.BSTInorderViaStack;
import exercises.ch25.ex03.BSTWithTestPerfect;
import exercises.ch25.ex04.BSTPreorderViaStack;
import exercises.ch25.ex05.BSTPostorderViaStack;
import exercises.ch25.ex06.BSTWithNumberOfLeaves;
import exercises.ch25.ex07.BSTWithNumberOfNonLeaves;
import exercises.ch25.ex08.BSTWithListIterator;
import exercises.ch25.ex11.Exercise25_11;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.Scanner;

public class Chapter25 {
    /*
        (Tree height) Define a new class named BSTWithHeight that extends BST with
        the following method:
        // Return the height of this binary tree
        public int height()
        Use https://liveexample.pearsoncmg.com/test/Exercise25_01.txt to test
        your code.
     */
    public static void ex1() {
        BSTWithHeight<String> tree = new BSTWithHeight<>();
        System.out.print("The height of an empty tree is " + tree.height());
        tree.insert("Green");
        System.out.print("\nThe height of the tree with one node is " + tree.height());
        tree.insert("Red");
        System.out.print("\nThe height of the tree with two nodes is " + tree.height());

        Scanner input = new Scanner(System.in);
        System.out.print("\nEnter six strings: ");
        for (int i = 0; i < 6; i++) {
            String s = input.next();
            tree.insert(s.trim());
        }
        System.out.print("The height of tree is " + tree.height());

        BSTWithHeight<String> tree1 = new BSTWithHeight<>(new String[]{
                "Tom", "George", "Jean", "Jane",
                "Kevin", "Peter", "Susan", "Jen",
                "Kim", "Michael", "Michelle"});
        System.out.print("\nThe height of tree1 is " + tree1.height());

        BSTWithHeight<Integer> tree2 = new BSTWithHeight<>(new Integer[]{50, 45, 35, 48, 59, 51, 58});
        System.out.print("\nThe height of tree2 is " + tree2.height());
    }

    /*
        (Implement inorder traversal without using recursion) Implement the inorder
        method in BST using a stack instead of recursion. Write a test program that
        prompts the user to enter 10 integers, stores them in a BST, and invokes the
        inorder method to display the elements.
     */
    public static void ex2() {
        int size = 10;
        Scanner scanner = new Scanner(System.in);
        BSTInorderViaStack<Integer> tree = new BSTInorderViaStack<>();
        System.out.printf("Enter %d integers: ", size);
        for (int i = 0; i < size; i++) {
            tree.add(scanner.nextInt());
        }
        tree.inorder();
    }

    /*
        (Test perfect binary tree) A perfect binary tree is a complete binary tree with
        all levels fully filled. Define a new class named BSTWithTestPerfect that
        extends BST with the following methods: (Hint: The number of nodes in a
        perfect binary tree is 2height + 1 - 1).
        // Returns true if the tree is a perfect binary tree
            public boolean isPerfectBST()
            Use https://liveexample.pearsoncmg.com/test/Exercise25_03.txt to test
            your code.
     */
    public static void ex3() {
        BSTWithTestPerfect<String> tree = new BSTWithTestPerfect<>();
        System.out.println("Is an empty tree perfect? " + tree.isPerfectBST());

        tree.insert("Green");
        System.out.println("Is a one-node tree perfect? " + tree.isPerfectBST());

        tree.insert("Red");
        System.out.println("Is a two-node tree perfect? " + tree.isPerfectBST());

        tree.insert("Blue");
        System.out.println("Is this tree perfect? " + tree.isPerfectBST());

        BSTWithTestPerfect<String> tree1 = new BSTWithTestPerfect<>(new String[]
                {"Tom", "George", "Jean", "Jane", "Kevin", "Peter", "Susan",
                        "Jen", "Kim", "Michael", "Michelle"});
        System.out.println("Is tree1 perfect? " + tree1.isPerfectBST());

        BSTWithTestPerfect<Integer> tree2 = new BSTWithTestPerfect<>(new Integer[]{50, 45, 75, 18, 49, 51, 98});
        System.out.println("Is tree2 perfect? " + tree2.isPerfectBST());

        BSTWithTestPerfect<Integer> tree3 = new BSTWithTestPerfect<>(new Integer[]{60, 55, 45, 57, 100, 67, 107});
        System.out.println("Is tree3 perfect? " + tree3.isPerfectBST());

        BSTWithTestPerfect<Integer> tree4 = new BSTWithTestPerfect<>(new Integer[]{60, 55, 45, 57, 59, 100, 67, 107, 101});
        System.out.println("Is tree4 perfect? " + tree4.isPerfectBST());
    }

    /*
        (Implement preorder traversal without using recursion) Implement the pre-
        order method in BST using a stack instead of recursion. Write a test program
        that prompts the user to enter 10 integers, stores them in a BST, and invokes the
        preorder method to display the elements.
     */
    public static void ex4() {
        int size = 10;
        Scanner scanner = new Scanner(System.in);
        BSTPreorderViaStack<Integer> tree = new BSTPreorderViaStack<>();
        System.out.printf("Enter %d integers: ", size);
        for (int i = 0; i < size; i++) {
            tree.add(scanner.nextInt());
        }
        tree.preorder();
    }

    /*
        (Implement postorder traversal without using recursion) Implement the
        postorder method in BST using a stack instead of recursion. Write a test
        program that prompts the user to enter 10 integers, stores them in a BST, and
        invokes the postorder method to display the elements.
     */
    public static void ex5() {
        int size = 10;
        Scanner scanner = new Scanner(System.in);
        BSTPostorderViaStack<Integer> tree = new BSTPostorderViaStack<>();
        System.out.printf("Enter %d integers: ", size);
        for (int i = 0; i < size; i++) {
            tree.add(scanner.nextInt());
        }
        tree.postorder();
    }

    /*
        (Find the leaves) Define a new class named BSTWithNumberOfLeaves that
        extends BST with the following methods:
        //Return the number of leaf nodes
            public int getNumberOfLeaves()
            Use https://liveexample.pearsoncmg.com/test/Exercise25_06.txt to test
            your code.
     */
    public static void ex6() {
        BSTWithNumberOfLeaves<String> tree = new BSTWithNumberOfLeaves<>();
        Scanner input = new Scanner(System.in);
        System.out.print("Enter six strings: ");
        for (int i = 0; i < 6; i++) {
            String s = input.next();
            tree.insert(s.trim());
        }
        System.out.println("The number of leaves in the tree is " + tree.getNumberOfLeaves());
    }


    /*
        (Find the nonleaves) Define a new class named BSTWithNumberOfNonLeaves
        that extends BST with the following methods:
        // Return the number of nonleaf nodes
            public int getNumberofNonLeaves()
            Use https://liveexample.pearsoncmg.com/test/Exercise25_07.txt to test
            your code.
     */
    public static void ex7() {
        BSTWithNumberOfNonLeaves<String> tree = new BSTWithNumberOfNonLeaves<>();
        Scanner input = new Scanner(System.in);
        System.out.print("Enter six strings: ");
        for (int i = 0; i < 6; i++) {
            String s = input.next();
            tree.insert(s.trim());
        }
        System.out.println("The number of non-leaves in the tree is " + tree.getNumberOfNonLeaves());
    }

    /*
        (Implement bidirectional iterator) The java.util.Iterator interface defines
        a forward iterator. The Java API also provides the java.util.ListIterator
        interface that defines a bidirectional iterator. Study ListIterator and define
        a bidirectional iterator for the BST class.
     */
    public static void ex8() {
        Integer[] array = new Integer[]{60, 55, 45, 57, 59, 100, 67, 107, 101};
        BSTWithListIterator<Integer> tree = new BSTWithListIterator<>(array);
        ListIterator<Integer> iterator = tree.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();
        while (iterator.hasPrevious()) {
            System.out.print(iterator.previous() + " ");
        }
    }

    /*
        (Tree clone and equals) Implement the clone and equals methods in the
        BST class. Two BST trees are equal if they contain the same elements. The clone
        method returns an identical copy of a BST
     */
    public static void ex9() {
        Integer[] array = new Integer[]{60, 55, 45, 57, 59, 100, 67, 107, 101};
        BST<Integer> tree1 = new BST<>(array);
        BST<Integer> tree2 = (BST<Integer>) tree1.clone();
        tree1.preorder();
        tree2.preorder();
        System.out.println("tree1.equals(tree2): " + tree1.equals(tree2));
        tree2.add(999);
        tree1.preorder();
        tree2.preorder();
        System.out.println("tree1.equals(tree2): " + tree1.equals(tree2));
        BST<Integer> tree3 = new BST<>(new Integer[]{60, 55, 45, 57, 59, 100, 67, 107, 102});
        System.out.println("tree1.equals(tree3): " + tree1.equals(tree3));
    }

    /*
        (Preorder iterator) Add the following method in the BST class that returns an
        iterator for traversing the elements in a BST in preorder.
        // Return an iterator for traversing the elements in preorder
            java.util.Iterator<E> preorderIterator()
     */
    public static void ex10() {
        Integer[] array = new Integer[]{60, 55, 45, 57, 59, 100, 67, 107, 101};
        BST<Integer> tree = new BST<>(array);
        Iterator<Integer> iterator = tree.preorderIterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
    }

    /*
        (Display tree) Write a new view class that displays the tree horizontally with the
        root on the left as shown in Figure 25.21.
     */
    public static void ex11() {
        Exercise25_11.run();
    }

    /*
        (Test BST) Design and write a complete test program to test if the BST class in
        Listing 25.4 meets all requirements.
     */
    public static void ex12() {
        // run jUnit in ex12/TestBST
    }

    /*

     */
    public static void ex13() {

    }

    /*

     */
    public static void ex14() {

    }

    /*

     */
    public static void ex15() {

    }

    /*

     */
    public static void ex16() {

    }

    /*

     */
    public static void ex17() {

    }

    /*

     */
    public static void ex18() {

    }

    /*

     */
    public static void ex19() {

    }
}
