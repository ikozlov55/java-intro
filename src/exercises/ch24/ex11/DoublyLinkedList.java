package exercises.ch24.ex11;

import exercises.ch24.base.MyList;

import java.util.ListIterator;

public class DoublyLinkedList<E> implements MyList<E> {
    private Node<E> head, tail;
    private int size;

    public DoublyLinkedList() {
        size = 0;
    }

    @Override
    public void add(int index, E e) {
        if (size == 0) {
            head = tail = new Node<>(e);
            size++;
            return;
        }
        if (index == 0) {
            Node<E> newNode = new Node<>(e);
            newNode.next = head;
            head.previous = newNode;
            head = newNode;
            size++;
            return;
        }
        if (index >= size) {
            Node<E> newNode = new Node<>(e);
            newNode.previous = tail;
            tail.next = newNode;
            tail = newNode;
            size++;
            return;
        }
        Node<E> current = head;
        for (int i = 1; i <= index; i++) {
            current = current.next;
        }
        Node<E> newNode = new Node<>(e);
        current.previous.next = newNode;
        newNode.previous = current.previous;
        current.previous = newNode;
        newNode.next = current;
        size++;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size) return null;
        Node<E> current = head;
        for (int i = 1; i <= index; i++) {
            current = current.next;
        }
        return current.element;
    }

    @Override
    public int indexOf(Object e) {
        Node<E> current = head;
        for (int i = 0; i < size; i++) {
            if (current.element.equals(e)) {
                return i;
            }
            current = current.next;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(E e) {
        Node<E> current = tail;
        for (int i = size - 1; i >= 0; i--) {
            if (current.element.equals(e)) {
                return i;
            }
            current = current.previous;
        }
        return -1;
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) return null;
        if (index == 0) {
            E temp = head.element;
            if (size == 1) {
                clear();
                return temp;
            }
            head.next.previous = null;
            head = head.next;
            size--;
            return temp;
        }
        if (index == size - 1) {
            E temp = tail.element;
            tail.previous.next = null;
            tail = tail.previous;
            size--;
            return temp;
        }
        // x current x
        Node<E> current = head;
        for (int i = 1; i <= index; i++) {
            current = current.next;
        }
        E temp = current.element;
        current.previous.next = current.next;
        current.next.previous = current.previous;
        size--;
        return temp;
    }

    @Override
    public E set(int index, E e) {
        if (index < 0 || index >= size) return null;
        Node<E> current = head;
        for (int i = 1; i <= index; i++) {
            current = current.next;
        }
        current.element = e;
        return current.element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    @Override
    public ListIterator<E> iterator() {
        return new MyListIterator();
    }

    @Override
    public void clear() {
        head = tail = null;
        size = 0;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        Node<E> current = head;
        String sep = "";
        while (current != null) {
            String s = String.format("%s[%s]", sep, current.element.toString());
            builder.append(s);
            sep = "⇆";
            current = current.next;
        }
        return builder.toString();
    }

    class MyListIterator implements ListIterator<E> {
        private Node<E> next;
        private int index;

        public MyListIterator() {
            next = head;
            index = -1;
        }

        @Override
        public boolean hasNext() {
            return size > 0 && index < size - 1;
        }

        @Override
        public E next() {
            E temp = next.element;
            next = next.next;
            index++;
            return temp;
        }

        @Override
        public boolean hasPrevious() {
            return size > 0 && index >= 0;
        }

        @Override
        public E previous() {
            if (next == null) {
                next = tail;
                index--;
                return tail.element;
            }
            E temp = next.previous.element;
            next = next.previous;
            index--;
            return temp;
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
}

class Node<E> {
    E element;
    Node<E> next;
    Node<E> previous;

    public Node(E element) {
        this.element = element;
    }
}


