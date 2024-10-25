package exercises.ch27.ex5;

import exercises.ch27.base.MyHashMap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class MyHashSetHM<E> implements Collection<E> {
    private MyHashMap<E, E> map;

    public MyHashSetHM() {
        map = new MyHashMap<>();
    }

    public MyHashSetHM(int initialCapacity) {
        map = new MyHashMap<>(initialCapacity);
    }

    public MyHashSetHM(int initialCapacity, float loadFactorThreshold) {
        map = new MyHashMap<>(initialCapacity, loadFactorThreshold);
    }

    @Override
    /** Remove all elements from this set */
    public void clear() {
        map.clear();
    }

    @Override
    /** Return true if the element is in the set */
    public boolean contains(Object e) {
        return map.containsKey((E) e);
    }

    @Override
    /** Add an element to the set */
    public boolean add(E e) {
        if (contains(e)) // Duplicate element not stored
            return false;
        map.put(e, e);
        return true;
    }

    @Override
    /** Remove the element from the set */
    public boolean remove(Object e) {
        if (!contains(e))
            return false;
        map.remove((E) e);
        return true;
    }

    @Override
    /** Return true if the set contain no elements */
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    /** Return the number of elements in the set */
    public int size() {
        return map.size();
    }

    @Override
    /** Return an iterator for the elements in this set */
    public Iterator<E> iterator() {
        return new MyHashSetIterator(this);
    }

    /**
     * Inner class for iterator
     */
    private class MyHashSetIterator implements Iterator<E> {
        // Store the elements in a list
        private ArrayList<E> list;
        private int current = 0; // Point to the current element in list
        private MyHashSetHM<E> set;

        /**
         * Create a list from the set
         */
        public MyHashSetIterator(MyHashSetHM<E> set) {
            this.set = set;
            list = setToList();
        }

        @Override
        /** Next element for traversing? */
        public boolean hasNext() {
            return current < list.size();
        }

        @Override
        /** Get current element and move cursor to the next */
        public E next() {
            return list.get(current++);
        }

        /**
         * Remove the current element returned by the last next()
         */
        public void remove() {
            // Left as an exercise
            // You need to remove the element from the set
            // You also need to remove it from the list
        }
    }

    /**
     * Remove all e from each bucket
     */
    private void removeElements() {
        map.clear();
    }

    /**
     * Copy elements in the hash set to an array list
     */
    private ArrayList<E> setToList() {
        return new ArrayList<>(map.keySet());
    }

    @Override
    /** Return a string representation for this set */
    public String toString() {
        ArrayList<E> list = setToList();
        StringBuilder builder = new StringBuilder("[");
        for (int i = 0; i < list.size() - 1; i++) {
            builder.append(list.get(i) + ", ");
        }
        // Add the last element in the list to the string builder
        if (list.size() == 0)
            builder.append("]");
        else
            builder.append(list.get(list.size() - 1) + "]");
        return builder.toString();
    }

    @Override
    public boolean addAll(Collection<? extends E> arg0) {
        // Left as an exercise
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> arg0) {
        // Left as an exercise
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> arg0) {
        // Left as an exercise
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> arg0) {
        // Left as an exercise
        return false;
    }

    @Override
    public Object[] toArray() {
        // Left as an exercise
        return null;
    }

    @Override
    public <T> T[] toArray(T[] arg0) {
        // Left as an exercise
        return null;
    }
}
