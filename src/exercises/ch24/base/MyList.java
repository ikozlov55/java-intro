package exercises.ch24.base;

import java.util.Collection;

public interface MyList<E> extends Collection<E> {
    /**
     * Add a new element at the specified index in this list
     */
    void add(int index, E e);

    /**
     * Return the element from this list at the specified index
     */
    E get(int index);

    /**
     * Return the index of the first matching element in this list.
     * 11 * Return −1 if no match.
     */
    int indexOf(Object e);

    /**
     * Return the index of the last matching element in this list
     * 15 * Return −1 if no match.
     */
    int lastIndexOf(E e);

    /**
     * Remove the element at the specified position in this list
     * 19 * Shift any subsequent elements to the left.
     * 20 * Return the element that was removed from the list.
     */
    E remove(int index);

    /**
     * Replace the element at the specified position in this list
     * 24 * with the specified element and returns the new set.
     */
    E set(int index, E e);

    @Override
    /** Add a new element at the end of this list */
    default boolean add(E e) {
        add(size(), e);
        return true;
    }

    @Override
    /** Return true if this list contains no elements */
    default boolean isEmpty() {
        return size() == 0;
    }

    @Override
    /** Remove the first occurrence of the element e
     39 * from this list. Shift any subsequent elements to the left.
     40 * Return true if the element is removed. */
    default boolean remove(Object e) {
        if (indexOf(e) >= 0) {
            remove(indexOf(e));
            return true;
        } else
            return false;
    }

    @Override
    default boolean containsAll(Collection<?> c) {
        for (Object item : c) {
            if (!contains(item)) {
                return false;
            }
        }
        return true;
    }

    @Override
    default boolean addAll(Collection<? extends E> c) {
        for (E item : c) {
            add(item);
        }
        return true;
    }

    @Override
    default boolean removeAll(Collection<?> c) {
        for (Object item : c) {
            remove(item);
        }
        return true;
    }

    @Override
    default boolean retainAll(Collection<?> c) {
        for (Object item : this.toArray()) {
            if (!c.contains(item)) {
                remove(item);
            }
        }
        return true;
    }

    @Override
    default Object[] toArray() {
        Object[] result = new Object[size()];
        for (int i = 0; i < size(); i++) {
            result[i] = get(i);
        }
        return result;
    }

    @Override
    default <T> T[] toArray(T[] array) {
        for (int i = 0; i < size(); i++) {
            array[i] = (T) get(i);
        }
        return array;
    }
}
