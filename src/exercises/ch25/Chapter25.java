package exercises.ch25;

import exercises.ch25.base.BST;
import exercises.ch25.ex01.BSTWithHeight;
import exercises.ch25.ex02.BSTInorderViaStack;

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
        int size = 9;
        Scanner scanner = new Scanner(System.in);
        BSTInorderViaStack<Integer> tree = new BSTInorderViaStack<>();
        System.out.printf("Enter %d integers: ", size);
        for (int i = 0; i < size; i++) {
            tree.add(scanner.nextInt());
        }
        tree.inorder();
    }

    /*

     */
    public static void ex3() {

    }

    /*

     */
    public static void ex4() {

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

    /*

     */
    public static void ex8() {

    }

    /*

     */
    public static void ex9() {

    }

    /*

     */
    public static void ex10() {

    }

    /*

     */
    public static void ex11() {

    }

    /*

     */
    public static void ex12() {

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
