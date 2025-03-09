package exercises.ch27.ex1;

import exercises.ch27.base.MyMap;

import java.util.HashSet;
import java.util.Set;

public class MyHashMapLP<K, V> implements MyMap<K, V> {
    private static final int DEFAULT_INITIAL_CAPACITY = 4;
    private static final int MAXIMUM_CAPACITY = 1 << 30;
    private static final float DEFAULT_MAX_LOAD_FACTOR = 0.5f;

    private int capacity;
    private float loadFactorThreshold;
    private int size = 0;
    MyMap.Entry<K, V>[] table;

    public MyHashMapLP() {
        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_MAX_LOAD_FACTOR);
    }

    public MyHashMapLP(int initialCapacity) {
        this(initialCapacity, DEFAULT_MAX_LOAD_FACTOR);
    }

    public MyHashMapLP(int initialCapacity, float loadFactorThreshold) {
        this.capacity = initialCapacity;
        this.loadFactorThreshold = loadFactorThreshold;
        table = new Entry[capacity];
    }

    public void clear() {
        size = 0;
        removeEntries();
    }

    public boolean containsKey(K key) {
        return get(key) != null;
    }

    public boolean containsValue(V value) {
        for (Entry<K, V> kvEntry : table)
            if (kvEntry != null && kvEntry.getValue().equals(value))
                return true;

        return false;
    }

    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> set = new HashSet<>();
        for (int i = 0; i < capacity; i++)
            if (table[i] != null)
                set.add(table[i]);

        return set;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Return a set consisting of the keys in this map
     */
    public Set<K> keySet() {
        Set<K> set = new HashSet<K>();

        for (int i = 0; i < capacity; i++)
            if (table[i] != null)
                set.add(table[i].getKey());

        return set;
    }

    /**
     * Remove the element for the specified key
     */
    public void remove(K key) {
        int i = hash(key.hashCode());

        while (table[i] != null && (table[i].getKey() == null || !table[i].getKey().equals(key)))
            i = (i + 1) % table.length;

        if (table[i] != null && table[i].getKey().equals(key)) {
            // A special marker Entry(null, null) is placed for the deleted entry
            table[i] = new Entry<K, V>(null, null);
            size--;
        }
    }

    public int size() {
        return size;
    }

    public Set<V> values() {
        Set<V> set = new HashSet<V>();

        for (int i = 0; i < capacity; i++)
            if (table[i] != null)
                set.add(table[i].getValue());

        return set;
    }

    private int hash(int hashCode) {
        return hashCode % capacity;
//      return supplementalHash(hashCode) & (capacity - 1);
    }

    /**
     * Ensure the hashing is evenly distributed
     */
    private static int supplementalHash(int h) {
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }

    /**
     * Remove all entries from each bucket
     */
    private void removeEntries() {
        for (int i = 0; i < capacity; i++)
            table[i] = null;
    }

    /**
     * Rehash the map
     */
    private void rehash() {
        Set<Entry<K, V>> set = entrySet(); // Get entries
        capacity <<= 1; // Double capacity
        table = new Entry[capacity]; // Create a new hash table

        size = 0; // Clear size

        for (Entry<K, V> entry : set) {
            put(entry.getKey(), entry.getValue()); // Store to new table
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");

        for (int i = 0; i < capacity; i++) {
            if (table[i] != null && table[i].getKey() != null)
                builder.append(table[i].toString());
        }

        return builder.append("]").toString();
    }


    /**
     * Return the first value that matches the specified key
     */
    public V get(K key) {
        int index = hash(key.hashCode());
        if (table[index] == null || table[index].getKey() == null) {
            return null;
        }
        while (table[index].getKey() != key) {
            index = (index + 1) % capacity;
        }
        return table[index].getValue();
    }

    /**
     * Add an entry (key, value) into the map
     */
    public V put(K key, V value) {
        int index = hash(key.hashCode());
        if (get(key) != null) {
            table[index] = new Entry<>(key, value);
            return value;
        }

        if (size >= capacity * loadFactorThreshold) {
            if (capacity > MAXIMUM_CAPACITY) {
                throw new RuntimeException("Exceeding maximum capacity");
            }
            rehash();
        }
        index = hash(key.hashCode());
        if (table[index] != null) {
            while (table[index] != null) {
                index = (index + 1) % capacity;
            }
        }
        table[index] = new Entry<>(key, value);
        size++;
        return value;
    }
}
