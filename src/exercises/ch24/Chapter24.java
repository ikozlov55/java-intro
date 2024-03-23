package exercises.ch24;

import exercises.ch24.ex01.Exercise24_01;
import exercises.ch24.ex02.Exercise24_02;
import exercises.ch24.ex03.TwoWayLinkedList;

import java.util.ListIterator;

public class Chapter24 {

    /*
        (Implement set operations in MyList) The implementations of the methods
        addAll, removeAll, retainAll, toArray(), and toArray(T[]) are omitted
        in the MyList interface. Implement these methods. Test your new MyList class
        using the code at https://liveexample.pearsoncmg.com/test/Exercise24_01.txt.
     */
    public static void ch24_1() {
        Exercise24_01.run();
    }

    /*
        (Implement MyLinkedList) The implementations of the methods contains(E
        e), get(int index), indexOf(E e), lastIndexOf(E e), and set(int
        index, E e) are omitted in the MyLinkedList class. Implement these methods.
        Define a new class named MyLinkedListExtra that extends MyLinkedList
        to override these methods. Test your new MyList class using the code at https://
        liveexample.pearsoncmg.com/test/Exercise24_02.txt.
     */
    public static void ch24_2() {
        Exercise24_02.run();
    }

    /*
    (Implement a doubly linked list) The MyLinkedList class used in Listing 24.5
    is a one-way directional linked list that enables one-way traversal of the list.
    Modify the Node class to add the new data field name previous to refer to the
    previous node in the list, as follows:
    public class Node<E> {
        E element;
        Node<E> next;
        Node<E> previous;
        public Node(E e) {
            element = e;
        }
    }
    Implement a new class named TwoWayLinkedList that uses a doubly linked list
    to store elements. Define TwoWayLinkedList to implements MyList. You need
    to implement all the methods defined in MyLinkedList as well as the methods
    listIterator() and listIterator(int index). Both return an instance
    of java.util.ListIterator<E> (see Figure 20.4). The former sets the cur-
    sor to the head of the list and the latter to the element at the specified index. Test
    your new class using this code from https://liveexample.pearsoncmg.com/test/
    Exercise24_03.txt.
}
     */
    public static void ch24_3() {
        TwoWayLinkedList<Integer> list = new TwoWayLinkedList<>();
        for (int i = 1; i < 10; i++) {
            list.add(i, i);
        }
        System.out.print("The list in forward order: ");
        for (Integer e : list) {
            System.out.print(e + " ");
        }

        ListIterator<Integer> iterator2 = list.iterator(list.size() - 1);
        System.out.print("\nThe list in backward order: ");
        while (iterator2.hasPrevious())
            System.out.print(iterator2.previous() + " ");
    }

    /*

     */
    public static void ch24_4() {

    }

    /*

     */
    public static void ch24_5() {

    }

    /*

     */
    public static void ch24_6() {

    }

    /*

     */
    public static void ch24_7() {

    }

    /*

     */
    public static void ch24_8() {

    }

    /*

     */
    public static void ch24_9() {

    }

    /*

     */
    public static void ch24_10() {

    }

    /*

     */
    public static void ch24_11() {

    }

    /*

     */
    public static void ch24_12() {

    }

    /*

     */
    public static void ch24_13() {

    }

    /*

     */
    public static void ch24_14() {

    }

    /*

     */
    public static void ch24_15() {

    }

    /*

     */
    public static void ch24_16() {

    }
}
