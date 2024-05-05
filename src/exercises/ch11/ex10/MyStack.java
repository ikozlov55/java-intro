package exercises.ch11.ex10;

import java.util.ArrayList;


public class MyStack<Type> extends ArrayList<Type> {
    public int getSize() {
        return size();
    }

    public Type peek() {
        if (isEmpty()) {
            return null;
        } else {
            return get(size() - 1);
        }
    }

    public Type pop() {
        return remove(size() - 1);
    }

    public void push(Type s) {
        add(s);
    }
}
