package exercises.ch24;

import exercises.ch19.ex1.GenericStack;
import exercises.ch24.ex01.Exercise24_01;
import exercises.ch24.ex02.Exercise24_02;
import exercises.ch24.ex03.TwoWayLinkedList;
import exercises.ch24.ex05.GenericQueue;
import exercises.ch24.ex06.MyPriorityQueue;
import exercises.ch24.ex06.PriorityQueueUsingSortedArrayList;
import exercises.ch24.ex07.Exercise24_07;
import exercises.ch24.ex08.Exercise24_08;
import exercises.ch24.ex09.Exercise24_09;
import exercises.ch24.ex10.Exercise24_10;
import exercises.ch24.ex11.Exercise24_11;
import exercises.ch24.ex12.Exercise24_12;

import java.util.ListIterator;

import static exercises.utils.Utils.testTime;

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
        (Use the GenericStack class) Write a program that displays the first 50 prime
        numbers in descending order. Use a stack to store the prime numbers.
     */
    public static void ch24_4() {
        GenericStack<Integer> stack = new GenericStack<>();
        int number = 2;
        while (number <= 50) {
            boolean isPrime = true;
            for (int divisor = 2; divisor <= (int) Math.sqrt(number); divisor++) {
                if (number % divisor == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                stack.push(number);
            }
            number++;
        }
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
    }

    /*
        (Implement GenericQueue using inheritance) In Section 24.5, Stacks and
        Queues, GenericQueue is implemented using composition. Define a new queue
        class that extends java.util.LinkedList.
     */
    public static void ch24_5() {
        GenericQueue<Integer> queue = new GenericQueue<>();
        for (int i = 1; i <= 10; i++) {
            queue.enqueue(i);
        }
        while (!queue.isEmpty()) {
            System.out.print(queue.dequeue() + " ");
        }
    }

    /*
        (Revise MyPriorityQueue) Listing 24.8, uses a heap to implement the priority
        queue. Revise the implementation using a sorted array list to store the elements and
        name the new class PriorityQueueUsingSortedArrayList. The elements
        in the array list are sorted in increasing order of their priority with the last element
        having the highest priority. Write a test program that generates 5 million integers
        and enqueues them to the priority and dequeues from the queue. Use the same
        numbers for MyPriorityQueue and PriorityQueueUsingSortedArraList
        and display their execution times.
     */
    public static void ch24_6() {
        final int n = 1_000_000;
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = (int) (Math.random() * 100 + 1);
        }
        double time1 = testTime(() -> {
            PriorityQueueUsingSortedArrayList<Integer> queue = new PriorityQueueUsingSortedArrayList<>();
            for (int value : numbers) {
                queue.enqueue(value);
            }
            while (!queue.isEmpty()) {
                queue.dequeue();
            }
        });
        double time2 = testTime(() -> {
            MyPriorityQueue<Integer> queue = new MyPriorityQueue<>();
            for (int value : numbers) {
                queue.enqueue(value);
            }
            while (!queue.isEmpty()) {
                queue.dequeue();
            }
        });
        System.out.printf("%-15s| %-40s %-40s\n",
                "Queue Type", "PriorityQueueUsingSortedArrayList", "MyPriorityQueue");
        System.out.printf("%s┼%s\n", "─".repeat(15), "─".repeat(80));
        System.out.printf("%-15s| %-40.2f %-40.2f\n", "Time", time1, time2);
    }

    /*
        (Animation: linked list) Write a program to animate search, insertion, and dele-
        tion in a linked list, as shown in Figure 24.1b. The Search button searches the
        specified value in the list. The Delete button deletes the specified value from the
        list. The Insert button appends the value into the list if the index is not specified;
        otherwise, it inserts the value into the specified index in the list.
     */
    public static void ch24_7() {
        Exercise24_07.run();
    }

    /*
        (Animation: array list) Write a program to animate search, insertion, and deletion
        in an array list, as shown in Figure 24.1a. The Search button searches the speci-
        fied value in the list. The Delete button deletes the specified value from the list.
        The Insert button appends the value into the list if the index is not specified;
        otherwise, it inserts the value into the specified index in the list.
     */
    public static void ch24_8() {
        Exercise24_08.run();
    }

    /*
        (Animation: array list in slow motion) Improve the animation in the preceding
        programming exercise by showing the insertion and deletion operations in a slow
        motion.
     */
    public static void ch24_9() {
        Exercise24_09.run();
    }

    /*
        (Animation: stack) Write a program to animate push and pop in a stack, as shown
        in Figure 24.20a.
     */
    public static void ch24_10() {
        Exercise24_10.run();
    }

    /*
        (Animation: doubly linked list) Write a program to animate search, insertion,
        and deletion in a doubly linked list, as shown in Figure 24.24. The Search button
        searches the specified value in the list. The Delete button deletes the specified
        value from the list. The Insert button appends the value into the list if the index
        is not specified; otherwise, it inserts the value into the specified index in the list.
        Also add two buttons named Forward Traversal and Backward Traversal for
        displaying the elements in a forward and backward order, respectively, using
        iterators, as shown in Figure 24.24. The elements are displayed in a label.
     */
    public static void ch24_11() {
        Exercise24_11.run();
    }

    /*
        (Animation: queue) Write a program to animate the enqueue and dequeue
        operations on a queue, as shown in Figure 24.20b.
     */
    public static void ch24_12() {
        Exercise24_12.run();
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
