package exercises.ch19.ex1;

import java.util.Arrays;

public class GenericStack<E> {
    private int capacity = 2;
    private int size = 0;
    private E[] array = (E[]) new Object[capacity];

    public int getSize() {
        return size;
    }

    public E peek() {
        return array[size - 1];
    }

    public void push(E o) {
        if (size == capacity) {
            capacity *= 2;
            array = Arrays.copyOf(array, capacity);
        }
        array[size] = o;
        size++;
    }

    public E pop() {
        E o = array[size - 1];
        array[size - 1] = null;
        size--;
        return o;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        return String.format("stack: %s size: %d capacity: %d", Arrays.toString(array), size, capacity);
    }
}

