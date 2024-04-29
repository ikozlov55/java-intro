package exercises.ch24.ex13;

import java.util.Iterator;

public class FibonacciIterator<E extends Integer> implements Iterator<E> {
    private final int limit;
    private int n1;
    private int n2;
    private int i;

    public FibonacciIterator(int limit) {
        this.limit = limit;
        this.n2 = 0;
        this.n1 = 1;
        this.i = 0;
    }

    @Override
    public boolean hasNext() {
        return n1 + n2 <= limit;
    }

    @Override
    public E next() {
        int result;
        if (i == 0) {
            result = 0;
        } else if (i == 1) {
            result = 1;
        } else {
            result = n1 + n2;
            n2 = n1;
            n1 = result;
        }
        i++;
        return (E) Integer.valueOf(result);
    }
}
