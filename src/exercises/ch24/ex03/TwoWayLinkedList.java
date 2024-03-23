package exercises.ch24.ex03;

import exercises.ch24.base.MyList;

import java.util.ListIterator;

public class TwoWayLinkedList<E> implements MyList<E> {
    private Node<E> head, tail;
    private int size = 0;

    public TwoWayLinkedList() {
    }

    public TwoWayLinkedList(E[] objects) {
        for (int i = 0; i < objects.length; i++) {
            add(objects[i]);
        }
    }


    @Override
    public void add(int index, E e) {
        if (index == 0) addFirst(e);
        else if (index >= size) addLast(e);
        else {
            Node<E> current = head;
            for (int i = 1; i < index; i++) {
                current = current.next;
            }
            Node<E> temp = current.next;
            current.next = new Node<>(e);
            current.next.previous = current;
            current.next.next = temp;
            temp.previous = current.next;
            size++;
        }
    }

    public void addFirst(E e) {
        Node<E> newNode = new Node<>(e);
        if (head == null) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head.previous = newNode;
            head = newNode;
        }
        size++;

        if (tail == null) {
            tail = head;
        }
    }

    public void addLast(E e) {
        Node<E> newNode = new Node<>(e);
        if (tail == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.previous = tail;
            tail = newNode;
        }

        size++;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size) return null;
        else if (index == 0) return getFirst();
        else if (index == size - 1) return getLast();
        else {
            Node<E> current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            return current.element;
        }
    }

    public E getFirst() {
        if (size == 0) {
            return null;
        } else {
            return head.element;
        }
    }

    public E getLast() {
        if (size == 0) {
            return null;
        } else {
            return tail.element;
        }
    }

    @Override
    public E remove(int index) {
        if (index == 0) return removeFirst();
        else if (index >= size) return removeLast();
        else {
            Node<E> current = head;
            for (int i = 1; i < index; i++) {
                current = current.next;
            }
            Node<E> temp = current.next;
            current.next.next.previous = current;
            current.next = current.next.next;
            size--;
            return temp.element;
        }
    }

    public E removeFirst() {
        if (size == 0) return null;
        else {
            Node<E> temp = head;
            head = head.next;
            head.previous = null;
            size--;
            if (head == null) tail = null;
            return temp.element;
        }
    }

    public E removeLast() {
        if (size == 0 || size == 1) {
            return removeFirst();
        } else {
            E temp = tail.element;
            tail = tail.previous;
            tail.next = null;
            size--;
            return temp;
        }
    }

    @Override
    public int indexOf(Object e) {
        if (size != 0) {
            Node<E> current = head;
            for (int i = 0; i < size; i++) {
                if (current.element.equals(e)) {
                    return i;
                }
                current = current.next;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(E e) {
        if (size != 0) {
            Node<E> current = tail;
            for (int i = size - 1; i >= 0; i--) {
                if (current.element.equals(e)) {
                    return i;
                }
                current = current.previous;
            }
        }
        return -1;
    }


    @Override
    public E set(int index, E e) {
        Node<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        E temp = current.element;
        current.element = e;
        return temp;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean contains(Object o) {
        if (size != 0) {
            Node<E> current = head;
            for (int i = 0; i < size; i++) {
                if (current.element.equals(o)) {
                    return true;
                }
                current = current.next;
            }
        }
        return false;
    }

    @Override
    public void clear() {
        size = 0;
        head = tail = null;
    }

    @Override
    public ListIterator<E> iterator() {
        return new LinkedListIterator();
    }

    public ListIterator<E> iterator(int index) {
        return new LinkedListIterator(index);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < size; i++) {
            builder.append(String.format("[%s]", get(i).toString()));
            if (i != size - 1) {
                builder.append("⇆");
            }
        }
        return builder.toString();
    }

    private class LinkedListIterator implements ListIterator<E> {
        private Node<E> next;
        private int index;

        public LinkedListIterator() {
            next = head;
            index = 0;
        }

        public LinkedListIterator(int index) {
            this.index = index;
            next = head;
            for (int i = 1; i <= index; i++) {
                next = next.next;
            }
        }

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public E next() {
            E e = next.element;
            next = next.next;
            index++;
            return e;
        }

        @Override
        public boolean hasPrevious() {
            return index >= 0;
        }

        @Override
        public E previous() {
            E e = next.element;
            next = next.previous;
            index--;
            return e;
        }

        @Override
        public int nextIndex() {
            return index + 1;
        }

        @Override
        public int previousIndex() {
            return index - 1;
        }

        @Override
        public void remove() {

        }

        @Override
        public void set(E e) {

        }

        @Override
        public void add(E e) {

        }
    }

    private static class Node<E> {
        E element;
        Node<E> next;
        Node<E> previous;

        public Node(E element) {
            this.element = element;
        }
    }
}
