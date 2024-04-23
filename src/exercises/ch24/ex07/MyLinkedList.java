package exercises.ch24.ex07;


import exercises.ch24.base.MyList;

import java.util.Iterator;

public class MyLinkedList<E> implements MyList<E> {
    private Node<E> head, tail;
    private int size;

    public MyLinkedList() {
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean contains(Object o) {
        if (size == 0) return false;
        Node<E> current = head;
        while (current.next != null) {
            if (current.element.equals(o)) return true;
            current = current.next;
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new MyIterator<>(head);
    }

    @Override
    public void clear() {
        size = 0;
        head = tail = null;
    }

    @Override
    public void add(int index, E e) {
        if (size == 0) {
            head = tail = new Node<>(e);
            size = 1;
            return;
        }
        if (index >= size) {
            tail.next = new Node<>(e);
            tail = tail.next;
            size++;
            return;
        }
        if (index == 0) {
            Node<E> newNode = new Node<>(e);
            newNode.next = head;
            head = newNode;
            size++;
            return;
        }
        Node<E> current = head;
        for (int i = 1; i < index; i++) {
            current = current.next;
        }
        Node<E> temp = current.next;
        current.next = new Node<>(e);
        current.next.next = temp;
        size++;
    }

    @Override
    public E get(int index) {
        if (index >= size) return null;
        Node<E> current = head;
        for (int i = 1; i <= index; i++) {
            current = current.next;
        }
        return current.element;
    }

    @Override
    public int indexOf(Object e) {
        if (size == 0) return -1;
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
        if (size == 0) return -1;
        Node<E> current = head;
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (current.element.equals(e)) {
                index = i;
            }
            current = current.next;
        }
        return index;
    }

    @Override
    public E remove(int index) {
        if (index >= size) return null;
        if (index == 0) {
            E result = head.element;
            if (size == 1) {
                clear();
            } else {
                head = head.next;
                size--;
            }
            return result;
        }
        if (index == size - 1) {
            E result = tail.element;
            Node<E> current = head;
            for (int i = 1; i < size - 1; i++) {
                current = current.next;
            }
            current.next = null;
            tail = current;
            size--;
            return result;
        }

        Node<E> current = head;
        for (int i = 1; i < index; i++) {
            current = current.next;
        }
        E result = current.next.element;
        current.next = current.next.next;
        size--;
        return result;
    }

    @Override
    public E set(int index, E e) {
        if (index >= size) return null;
        Node<E> current = head;
        for (int i = 1; i <= index; i++) {
            current = current.next;
        }
        current.element = e;
        return e;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        Node<E> current = head;
        String sep = "";
        while (current != null) {
            String s = String.format("%s[%s]", sep, current.element.toString());
            builder.append(s);
            sep = "->";
            current = current.next;
        }
        return builder.toString();
    }
}

class Node<E> {
    E element;
    Node<E> next;

    public Node(E element) {
        this.element = element;
    }
}

class MyIterator<E> implements Iterator<E> {
    Node<E> current;

    public MyIterator(Node<E> head) {
        current = head;
    }

    @Override
    public boolean hasNext() {
        return current != null;
    }

    @Override
    public E next() {
        E e = current.element;
        current = current.next;
        return e;
    }
}
