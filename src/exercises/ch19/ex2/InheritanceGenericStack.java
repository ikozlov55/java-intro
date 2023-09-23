package exercises.ch19.ex2;

import java.util.ArrayList;

public class InheritanceGenericStack<E> extends ArrayList<E> {

    public int getSize() {
        return size();
    }

    public E peek() {
        return get(getSize() - 1);
    }

    public void push(E o) {
        add(o);
    }

    public E pop() {
        E o = get(getSize() - 1);
        remove(getSize() - 1);
        return o;
    }

    @Override
    public String toString() {
        return "stack: " + super.toString();
    }
}
