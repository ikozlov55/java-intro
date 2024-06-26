package exercises.ch23.ex07;

import java.util.ArrayList;
import java.util.Comparator;

public class MinHeap<E> {
    private final ArrayList<E> list = new ArrayList<>();
    private final Comparator<? super E> c;

    /**
     * Create a default heap
     */
    public MinHeap() {
        this.c = (e1, e2) -> ((Comparable<E>) e1).compareTo(e2);
    }

    /**
     * Create a heap with a specified comparator
     */
    public MinHeap(Comparator<? super E> c) {
        this.c = c;
    }

    /**
     * Create a heap from an array of objects
     */
    public MinHeap(E[] objects) {
        this.c = (e1, e2) -> ((Comparable<E>) e1).compareTo(e2);
        for (E object : objects) add(object);
    }

    /**
     * Add a new object into the heap
     */
    public void add(E newObject) {
        list.add(newObject); // Append to the heap
        int currentIndex = list.size() - 1; // The index of the last node

        while (currentIndex > 0) {
            int parentIndex = (currentIndex - 1) / 2;
            // Swap if the current object is less than its parent
            if (c.compare(list.get(currentIndex), list.get(parentIndex)) < 0) {
                E temp = list.get(currentIndex);
                list.set(currentIndex, list.get(parentIndex));
                list.set(parentIndex, temp);
            } else {
                break; // The tree is a heap now
            }
            currentIndex = parentIndex;
        }
    }

    /**
     * Remove the root from the heap
     */
    public E remove() {
        if (list.isEmpty()) return null;

        E removedObject = list.get(0);
        list.set(0, list.get(list.size() - 1));
        list.remove(list.size() - 1);

        int currentIndex = 0;
        while (currentIndex < list.size()) {
            int leftChildIndex = 2 * currentIndex + 1;
            int rightChildIndex = 2 * currentIndex + 2;

            // Find the minimum between two children
            if (leftChildIndex >= list.size()) break; // The tree is a heap
            int minIndex = leftChildIndex;
            if (rightChildIndex < list.size()) {
                if (c.compare(list.get(minIndex), list.get(rightChildIndex)) > 0) {
                    minIndex = rightChildIndex;
                }
            }

            // Swap if the current node is greater than the minimum
            if (c.compare(list.get(currentIndex), list.get(minIndex)) > 0) {
                E temp = list.get(minIndex);
                list.set(minIndex, list.get(currentIndex));
                list.set(currentIndex, temp);
                currentIndex = minIndex;
            } else {
                break; // The tree is a heap
            }
        }

        return removedObject;
    }

    /**
     * Get the number of nodes in the tree
     */
    public int getSize() {
        return list.size();
    }

    /**
     * Return true if heap is empty
     */
    public boolean isEmpty() {
        return list.isEmpty();
    }
}
