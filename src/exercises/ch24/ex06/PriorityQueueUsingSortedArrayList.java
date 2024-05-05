package exercises.ch24.ex06;


import java.util.ArrayList;
import java.util.Comparator;

public class PriorityQueueUsingSortedArrayList<E extends Comparable<E>> {
    private final ArrayList<E> list;
    private final Comparator<E> comparator;

    public PriorityQueueUsingSortedArrayList() {
        list = new ArrayList<>();
        comparator = Comparator.naturalOrder();
    }

    public PriorityQueueUsingSortedArrayList(Comparator<E> comparator) {
        list = new ArrayList<>();
        this.comparator = comparator;
    }

    public void enqueue(E newObject) {
        for (int i = 0; i < list.size(); i++) {
            if (comparator.compare(newObject, list.get(i)) < 0) {
                list.add(i, newObject);
                return;
            }
        }
        list.add(newObject);
    }

    public E dequeue() {
        return list.remove(list.size() - 1);
    }

    public int getSize() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }
}
