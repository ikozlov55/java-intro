package exercises.ch24.ex05;

import java.util.LinkedList;

public class GenericQueue<E> extends LinkedList<E> {
    public void enqueue(E e) {
        add(e);
    }

    public E dequeue() {
        return remove();
    }

    public int getSize() {
        return size();
    }
}
