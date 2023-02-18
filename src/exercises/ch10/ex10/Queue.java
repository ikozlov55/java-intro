package exercises.ch10.ex10;

import java.util.Arrays;
import java.util.stream.Collectors;


public class Queue {
    private int[] data;
    private int size;
    private int capacity = 8;

    public Queue() {
        data = new int[capacity];
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public void enqueue(int v) {
        if (size >= capacity) {
            capacity *= 2;
            data = Arrays.copyOf(data, capacity);
        }
        data[size] = v;
        size++;
    }

    public int dequeue() {
        if (empty()) {
            throw new IllegalStateException();
        }
        int first = data[0];
        for (int i = 1; i < size; i++) {
            data[i - 1] = data[i];
        }
        size--;
        return first;
    }

    public boolean empty() {
        return size == 0;
    }

    @Override
    public String toString() {
        return "[" + Arrays.stream(data)
                .mapToObj(Integer::toString)
                .limit(size)
                .collect(Collectors.joining(", ")) + "]";
    }
}
