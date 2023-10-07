package exercises.ch20.ex12;

import java.util.Collection;
import java.util.PriorityQueue;

public class MyPriorityQueue<T> extends PriorityQueue<T> implements Cloneable {

    public MyPriorityQueue() {
        super();
    }

    public MyPriorityQueue(Collection<? extends T> c) {
        super(c);
    }

    @Override
    public Object clone() {
        MyPriorityQueue<T> clone = new MyPriorityQueue<>();
        clone.addAll(this);
        return clone;
    }
}
