package exercises.ch24.ex08;

import exercises.ch24.base.MyList;

import java.util.Iterator;

public class MyArrayList<E> implements MyList<E> {
    private final int INITIAL_CAPACITY = 4;
    private E[] array;
    private int size;
    private int capacity;

    public MyArrayList() {
        size = 0;
        capacity = INITIAL_CAPACITY;
        array = (E[]) new Object[INITIAL_CAPACITY];
    }


    @Override
    public void add(int index, E e) {
        if (index > size) return;
        if (size >= capacity) {
            capacity = capacity == 0 ? INITIAL_CAPACITY : capacity * 2;
            E[] newArray = (E[]) new Object[capacity];
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
        }
        for (int i = size; i > index; i--) {
            array[i] = array[i - 1];
        }
        array[index] = e;
        size++;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size) return null;
        return array[index];
    }

    @Override
    public int indexOf(Object e) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(E e) {
        for (int i = size - 1; i >= 0; i--) {
            if (array[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) return null;
        E temp = array[index];
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        size--;
        return temp;
    }

    @Override
    public E set(int index, E e) {
        if (index < 0 || index >= size) return null;
        array[index] = e;
        return array[index];
    }

    @Override
    public int size() {
        return size;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new MyArrayListIterator();
    }

    @Override
    public void clear() {
        size = 0;
        capacity = INITIAL_CAPACITY;
        array = (E[]) new Object[INITIAL_CAPACITY];
    }


    public void trim() {
        if (size == capacity) return;
        capacity = size;
        E[] newArray = (E[]) new Object[size];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        int i = 0;
        for (; i < size; i++) {
            String s = String.format("[%s]", array[i]);
            builder.append(s);
        }
        for (; i < capacity; i++) {
            builder.append("[x]");
        }
        return builder.toString();
    }

    class MyArrayListIterator implements Iterator<E> {

        private int index = 0;

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public E next() {
            return array[index++];
        }
    }
}


