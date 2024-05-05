package exercises.ch24.ex06;

import exercises.ch23.ex10.Heap;

public class MyPriorityQueue<E> {
    private final Heap<E> heap;

    public MyPriorityQueue() {
        heap = new Heap<E>();
    }

    public MyPriorityQueue(java.util.Comparator<E> c) {
        heap = new Heap<E>(c);
    }

    public void enqueue(E newObject) {
        heap.add(newObject);
    }

    public E dequeue() {
        return heap.remove();
    }

    public int getSize() {
        return heap.getSize();
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }
}
